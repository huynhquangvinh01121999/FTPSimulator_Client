package components;

import features.FileExtensions;
import handlers.FileHandler;
import java.awt.CardLayout;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.FileEvent;
import models.Files;
import models.HandleResult;
import models.ObjectRequest;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class ClientThread {

    private static Socket socket;
    private static BufferedWriter out;
    private static BufferedReader in;

    private static ObjectInputStream objInputStream;
    private static ObjectOutputStream objOutputStream;
    private static File dstFile;
    private static FileOutputStream fileOutputStream;
    private static boolean isDisconnect = false;

    // <editor-fold defaultstate="collapsed" desc="function connect">
    public static void connect(String host, int port) {
        try {
            socket = new Socket(host, port);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // objOutputStream dùng để gửi 1 object qua socket
            objOutputStream = new ObjectOutputStream(socket.getOutputStream());

            // objInputStream dùng để nhận 1 object từ socket
            objInputStream = new ObjectInputStream(socket.getInputStream());

            // tạo runnable để luôn chạy tiến trình lắng nghe func listen()
            Runnable runnable = () -> {
                listen();
            };
            Thread thread = new Thread(runnable);
            thread.start();
            System.out.println("ok");
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Xử lý gửi - nhận file phía Client (không dùng trong đây)">
    private static void saveFile() {
        try {
            // Bước 1: đọc đối tượng truyền từ client qua
            FileEvent fileEvent = (FileEvent) objInputStream.readObject();

            // Bước 2: Kiểm tra trạng thái có bị "Error" ko???
            if (fileEvent.getStatus().equalsIgnoreCase("Error")) {

                // Nếu có: Thông báo lỗi => Chấm dứt chương trình
                System.err.println("Xảy ra lỗi..");
            }

            // OK => 
            // kiểm tra đường dẫn đích tồn tại chưa
            if (!new File(fileEvent.getDestinationDirectory()).exists()) {
                // Nếu chưa => tạo mới đường dẫn
                new File(fileEvent.getDestinationDirectory()).mkdirs();
            }

            // tạo file
            String outputFile = fileEvent.getDestinationDirectory() + fileEvent.getFilename();
            dstFile = new File(outputFile);

            // FileOutputStream dùng để ghi dữ liệu vào file theo định dạng byte
            fileOutputStream = new FileOutputStream(dstFile);
            fileOutputStream.write(fileEvent.getFileData());    // bđ ghi vô luồng
            fileOutputStream.flush();   // đẩy luồng ghi vô file
            fileOutputStream.close();   // đóng quá trình ghi sau khi hoàn tất

            System.out.println("Output file : " + outputFile + " được ghi thành công. ");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void sendFile(String sourceFilePath, String destinationPath) {

        // tạo đối tượng FileEvent
        FileEvent fileEvent = new FileEvent();

        // lấy ra tên file
        String fileName = sourceFilePath.substring(sourceFilePath.lastIndexOf("/") + 1, sourceFilePath.length());

        fileEvent.setFilename(fileName);
        fileEvent.setSourceDirectory(sourceFilePath);
        fileEvent.setDestinationDirectory(destinationPath);

        File file = new File(sourceFilePath);
        if (file.isFile()) {
            try {
                // DataInputStream đọc dữ liệu nguyên thủy từ luồng đầu vào ( ở đây là file đầu vào)
                DataInputStream diStream = new DataInputStream(new FileInputStream(file));
                long len = (int) file.length();
                byte[] fileBytes = new byte[(int) len];
                int read = 0;
                int numRead = 0;
                while (read < fileBytes.length && (numRead = diStream.read(fileBytes, read, fileBytes.length - read)) >= 0) {
                    read = read + numRead;
                }

                fileEvent.setFileSize(len);
                fileEvent.setFileData(fileBytes);
                fileEvent.setStatus("Success");
            } catch (Exception e) {
                e.printStackTrace();
                fileEvent.setStatus("Error");
            }
        } else {
            System.out.println("Không tìm thấy tệp ở vị trí đã chỉ định.");
            fileEvent.setStatus("Error");
        }

        //Ghi đối tượng tệp vào thư mục
        try {
            objOutputStream.writeObject(fileEvent);
            System.out.println("Đang gửi file...");
            Thread.sleep(3000);
            System.out.println("Hoàn tất!");
            objOutputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // </editor-fold> 

//    public static void FileSender(Files fileInfo, File fileUpload, String destinationPath) {
//        destinationPath += "/";
//        new FileHandler().ClientSender(objOutputStream, fileUpload, destinationPath);
//        try {
//            objOutputStream.writeObject(fileInfo);
//            objOutputStream.reset();
//        } catch (IOException ex) {
//            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public static void FileSaver() {
        new FileHandler().ClientSaver(objInputStream);
    }

    public static void request(String message, Object object) {
        try {
            objOutputStream.writeObject(new ObjectRequest(message, object));
            objOutputStream.flush();
            objOutputStream.reset();
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void request(String data) {
        try {
            out.write(data);
            out.newLine();
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public static void requestFileSender(File fileUpload, String destinationPath) {
//        destinationPath += "/";
//        new FileHandler().ClientSender(objOutputStream, fileUpload, destinationPath);
//        try {
//            objOutputStream.reset();
//        } catch (IOException ex) {
//            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public static void requestFileSender(String message, Files fileInfo, File file, String destinationPath) {
        destinationPath += "/";
        // tạo đối tượng FileEvent
        FileEvent fileEvent = new FileEvent();

        fileEvent.setFilename(FileExtensions.getFileName(file));
        fileEvent.setDestinationDirectory(destinationPath);

        if (file.isFile()) {
            try {
                // DataInputStream đọc dữ liệu nguyên thủy từ luồng đầu vào ( ở đây là file đầu vào)
                DataInputStream diStream = new DataInputStream(new FileInputStream(file));
                long len = (int) file.length();
                byte[] fileBytes = new byte[(int) len];
                int read = 0;
                int numRead = 0;
                while (read < fileBytes.length && (numRead = diStream.read(fileBytes, read, fileBytes.length - read)) >= 0) {
                    read = read + numRead;
                }

                fileEvent.setFileSize(len);
                fileEvent.setFileData(fileBytes);
                fileEvent.setStatus("Success");
                System.out.println("Tải file lên file thành công");
            } catch (IOException ex) {
                System.err.println("Ghi file lên thất bại" + ex);
                fileEvent.setStatus("Error");
            }
        } else {
            System.out.println("Không tìm thấy tệp ở vị trí đã chỉ định.");
            fileEvent.setStatus("Error");
        }
        try {
            objOutputStream.writeObject(new ObjectRequest(message, fileInfo, fileEvent));
            objOutputStream.reset();
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void saveFile(FileEvent fileEvent) {
        try {

            // Bước 2: Kiểm tra trạng thái có bị "Error" ko???
            if (fileEvent.getStatus().equalsIgnoreCase("Error")) {

                // Nếu có: Thông báo lỗi => Chấm dứt chương trình
                System.err.println("Xảy ra lỗi..");
            }

            // OK => 
            // kiểm tra đường dẫn đích tồn tại chưa
            if (!new File(fileEvent.getDestinationDirectory()).exists()) {
                // Nếu chưa => tạo mới đường dẫn
                new File(fileEvent.getDestinationDirectory()).mkdirs();
            }

            // tạo file
            String outputFile = fileEvent.getDestinationDirectory() + fileEvent.getFilename();
            dstFile = new File(outputFile);

            // FileOutputStream dùng để ghi dữ liệu vào file theo định dạng byte
            fileOutputStream = new FileOutputStream(dstFile);
            fileOutputStream.write(fileEvent.getFileData());    // bđ ghi vô luồng
            fileOutputStream.flush();   // đẩy luồng ghi vô file
            fileOutputStream.close();   // đóng quá trình ghi sau khi hoàn tất

            System.out.println("Output file : " + outputFile + " download thành công. ");

        } catch (IOException ex) {
            System.err.println("Lỗi khi lưu file" + ex);
        }
    }

    public static void sendMessage(String message) {
        try {
            out.write(message);
            out.newLine();
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public static void disconnect() {
//        isDisconnect = true;
//        try {
//            out.write("disconnect" + "\n");
//            out.flush();
//        } catch (IOException ex) {
//            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public static void disconnect() {
        try {
            isDisconnect = true;
            objOutputStream.writeObject(new ObjectRequest("disconnect"));
        } catch (IOException ex) {
            System.err.println("Client xảy ra lỗi IOException thông báo DISCONNECT đến Server - " + ex);
        }
    }

    private static String getMessage() {
        String message;
        try {
            message = in.readLine().replace("\n", "").replace("\r", "");
            if (String.valueOf(message.charAt(0)).equalsIgnoreCase("y")) {
                message = message.substring(1, message.length());
            }
        } catch (Exception ex) {
            message = "ACCCEP_DISCONNECT";
        }
        return message;
    }

//    public static void sendObjectUser(Users user) {
//        try {
//            objOutputStream.writeObject(user);
//            objOutputStream.reset();
//        } catch (IOException ex) {
//            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    public static void sendObjectFolder(Folders folder) {
//        try {
//            objOutputStream.writeObject(folder);
//            objOutputStream.reset();
//        } catch (IOException ex) {
//            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public static void tranferLayout(Container parent, String panelName) {
        CardLayout layout = (CardLayout) (parent.getLayout());
        layout.show(parent, panelName);
    }

    public static void listen() {
        System.out.println("CLient is running...");
        try {
            while (!isDisconnect) {

                ObjectRequest response;
                synchronized (objInputStream) {
                    response = (ObjectRequest) objInputStream.readObject();
                }
                String message = response.getMessage();
                switch (message.toUpperCase()) {
                    case "ACCEPT_DOWNLOAD_FILE": {
                        System.out.println("Server trả lời mày là: " + message);
                        FileEvent fileEvent = (FileEvent) response.getObject();
                        saveFile(fileEvent);
                        break;
                    }
                    case "ACCCEP_DISCONNECT": {
                        System.out.println("Server said: " + message);
                        System.err.println("Disconnecting...");
                        break;
                    }
                    case "RESPONSE_VERIFY_REGISTER": {
                        System.out.println("Server said: " + message);

                        // version_1
                        // HandleResult result = (HandleResult) objInputStream.readObject();
                        HandleResult result = (HandleResult) response.getObject();
                        if (result != null) {
                            ClientUI.processHandler(result.isSuccessed(), result.getMessage());
                            if (result.isSuccessed()) {
                                ClientUI.setVerifyCode(Integer.parseInt(String.valueOf(result.getValue())));
                            }
                        }
                        break;
                    }
                    case "RESPONSE_REGISTER": {
                        System.out.println("Server said: " + message);
                        //HandleResult result = (HandleResult) objInputStream.readObject();
                        HandleResult result = (HandleResult) response.getObject();
                        if (result != null) {
                            ClientUI.processHandler(result.isSuccessed(), result.getMessage());
                        }
                        break;
                    }
                    case "RESPONSE_AUTHENTICATE":
                        System.out.println("Server said: " + message);
                        try {
//                            HandleResult result = (HandleResult) objInputStream.readObject();
                            HandleResult result = (HandleResult) response.getObject();
                            if (result != null) {
                                if (result.isSuccessed()) {
                                    HandleResult responseListData = (HandleResult) objInputStream.readObject();
                                    HandleResult responseListDataShares = (HandleResult) objInputStream.readObject();
                                    ClientUI.responseDataAfterAuthen(result.getUser(),
                                            result.getFolder(), 
                                            responseListData.getListFolderChild(),
                                            responseListData.getListFile(),
                                            responseListDataShares.getListFileShareses(),
                                            responseListDataShares.getListFolderShareses(),
                                            responseListDataShares.getListPermissionses()
                                    );
                                }
                                ClientUI.processHandler(result.isSuccessed(), result.getMessage());
                            }
                        } catch (ClassNotFoundException ex) {
                            System.err.println("Xảy ra lỗi khi RESPONSE_AUTHENTICATE - " + ex);
                            ClientUI.processHandler(false, "Vui lòng đăng nhập lại");
                        }
                        break;

                    case "RESPONSE_NOTIFICATION":
                        String test = (String) response.getObject();
                        System.out.println(test);
                        break;
                    default:
                        System.out.println("Server said: " + message);
                        break;
                }
            }
            System.out.println("Client closed connection");
            in.close();
            out.close();
            socket.close();
            Thread.sleep(3000); // ngủ 3s sau đó chấm dứt chương trình
            System.exit(0);
        } catch (InterruptedException ex) {
            System.err.println("Client đã xảy ra lỗi InterruptedException - " + ex);
        } catch (EOFException ex) {
            System.err.println("Client đã xảy ra lỗi EOFException - " + ex);
        } catch (IOException ex) {
            System.err.println("Client đã xảy ra lỗi IOException - " + ex);
        } catch (ClassNotFoundException ex) {
            System.err.println("Client đã xảy ra lỗi ClassNotFoundException - " + ex);
        }
    }
}
