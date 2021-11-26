/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import features.FileExtensions;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.DataShare;
import models.Files;
import models.Folders;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class SharePeople extends javax.swing.JDialog {

    private ClientUI index;
    private DefaultTableModel tblFileShareModel;
    private DefaultTableModel tblFolderShareModel;

    private static String selectedTypeShare = null;
    private static List<String> listIdFileSelected = new ArrayList<>();
    private static String folderIdSelected = null;
    private static String permissionSelected = null;
    private static String fromEmail = null;

    public SharePeople(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setColumnIdentifiers();

        index = (ClientUI) parent;

        // set data
        setFromEmail();
        setDataTable();
        setDataPermission();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Cấu hình header table">
    private void setColumnIdentifiers() {
        tblFileShareModel = (DefaultTableModel) tblFileShare.getModel();
        tblFileShareModel.setColumnIdentifiers(new Object[]{
            "FileId", "File Name", "File Size", "File Extension"
        });

        tblFolderShareModel = (DefaultTableModel) tblFolderShare.getModel();
        tblFolderShareModel.setColumnIdentifiers(new Object[]{
            "FolderId", "Folder Name", "Create At"
        });

        // ẩn column đầu tiên của table
        hiddenColumnFirst(tblFileShare);
        hiddenColumnFirst(tblFolderShare);
    }
    // </editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Load data">
    private void setDataTable() {
        tblFileShareModel.setRowCount(0);
        List<Files> listFileShare = index.getListFileInfo();
        listFileShare.forEach((file) -> {
            if (!file.getPrexEmail().trim().equals("anonymous")) {
                tblFileShareModel.addRow(new Object[]{
                    file.getFileId(), file.getFileName(),
                    FileExtensions.convertSizeFromSizeString(file.getFileSize(), "MB") + " MB",
                    file.getFileExtension()
                });
            }
        });

        tblFolderShareModel.setRowCount(0);
        List<Folders> listFolderShare = new ArrayList<>();
        listFolderShare.add(index.getFolderInfo());
        listFolderShare.addAll(index.getListFolderChildInfo());
        listFolderShare.forEach((folder) -> {
            if (!folder.getFolderName().trim().equals(fromEmail.split("@")[0])) {
                tblFolderShareModel.addRow(new Object[]{
                    folder.getFolderId(), folder.getFolderName(), folder.getCreateAt()
                });
            }
        });
    }

    private void setDataPermission() {
        cmbPermissions.removeAll();
        index.getListPermissionInfo().forEach((item) -> {
            cmbPermissions.addItem(item.getPermissionId().trim() + " | " + item.getPermissionName().trim());
        });
    }

    private void setFromEmail() {
        fromEmail = index.getUserInfo().getEmail().trim();
    }
    // </editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbTypeShare = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cmbPermissions = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPeopleShares = new javax.swing.JTextArea();
        btnCancel = new javax.swing.JButton();
        btnShare = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        pnlShares = new javax.swing.JPanel();
        pnlShareFile = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFileShare = new javax.swing.JTable();
        btnUnSelectAllFile = new javax.swing.JButton();
        btnUnSelectFile = new javax.swing.JButton();
        btnSelectFile = new javax.swing.JButton();
        pnlShareFolder = new javax.swing.JPanel();
        btnUnSelectFolder = new javax.swing.JButton();
        btnSelectFolder = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblFolderShare = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Peoples shared:");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(jLabel1);
        jLabel1.setBounds(270, 11, 100, 30);

        cmbTypeShare.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbTypeShare.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Choose type --", "File", "Folder" }));
        cmbTypeShare.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbTypeShare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTypeShareActionPerformed(evt);
            }
        });
        jPanel1.add(cmbTypeShare);
        cmbTypeShare.setBounds(100, 10, 130, 30);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Permissions:");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(jLabel2);
        jLabel2.setBounds(700, 11, 80, 30);

        cmbPermissions.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbPermissions.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbPermissions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPermissionsActionPerformed(evt);
            }
        });
        jPanel1.add(cmbPermissions);
        cmbPermissions.setBounds(790, 11, 130, 30);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Type Shares:");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 11, 82, 30);

        txtPeopleShares.setColumns(20);
        txtPeopleShares.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtPeopleShares.setRows(3);
        jScrollPane2.setViewportView(txtPeopleShares);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(380, 10, 280, 70);

        btnCancel.setBackground(new java.awt.Color(255, 0, 0));
        btnCancel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancel");
        btnCancel.setBorderPainted(false);
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancel);
        btnCancel.setBounds(365, 456, 100, 33);

        btnShare.setBackground(new java.awt.Color(102, 204, 0));
        btnShare.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnShare.setText("Share");
        btnShare.setBorderPainted(false);
        btnShare.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnShare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShareActionPerformed(evt);
            }
        });
        jPanel1.add(btnShare);
        btnShare.setBounds(490, 456, 100, 33);

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Note: Peoples shared must be separated by semicolons");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(380, 85, 280, 14);

        pnlShares.setLayout(new java.awt.CardLayout());

        pnlShareFile.setBackground(new java.awt.Color(255, 255, 255));

        tblFileShare.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblFileShare.setRowHeight(30);
        jScrollPane1.setViewportView(tblFileShare);

        btnUnSelectAllFile.setBackground(new java.awt.Color(255, 255, 0));
        btnUnSelectAllFile.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnUnSelectAllFile.setText("Unselect all");
        btnUnSelectAllFile.setBorderPainted(false);
        btnUnSelectAllFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUnSelectAllFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnSelectAllFileActionPerformed(evt);
            }
        });

        btnUnSelectFile.setBackground(new java.awt.Color(255, 255, 255));
        btnUnSelectFile.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnUnSelectFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-round-22.png"))); // NOI18N
        btnUnSelectFile.setText("Unselect");
        btnUnSelectFile.setBorderPainted(false);
        btnUnSelectFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUnSelectFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnSelectFileActionPerformed(evt);
            }
        });

        btnSelectFile.setBackground(new java.awt.Color(255, 255, 255));
        btnSelectFile.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnSelectFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-checked-22.png"))); // NOI18N
        btnSelectFile.setText("Select");
        btnSelectFile.setBorderPainted(false);
        btnSelectFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSelectFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlShareFileLayout = new javax.swing.GroupLayout(pnlShareFile);
        pnlShareFile.setLayout(pnlShareFileLayout);
        pnlShareFileLayout.setHorizontalGroup(
            pnlShareFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
            .addGroup(pnlShareFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlShareFileLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(pnlShareFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlShareFileLayout.createSequentialGroup()
                            .addGap(577, 577, 577)
                            .addComponent(btnUnSelectAllFile, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(14, 14, 14)
                            .addComponent(btnUnSelectFile)
                            .addGap(10, 10, 10)
                            .addComponent(btnSelectFile, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlShareFileLayout.setVerticalGroup(
            pnlShareFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
            .addGroup(pnlShareFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlShareFileLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(pnlShareFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnUnSelectAllFile, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUnSelectFile, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSelectFile, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnlShares.add(pnlShareFile, "pnlShareFile");

        pnlShareFolder.setBackground(new java.awt.Color(255, 255, 255));

        btnUnSelectFolder.setBackground(new java.awt.Color(255, 255, 0));
        btnUnSelectFolder.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnUnSelectFolder.setText("Unselect all");
        btnUnSelectFolder.setBorderPainted(false);
        btnUnSelectFolder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnSelectFolder.setBackground(new java.awt.Color(255, 255, 255));
        btnSelectFolder.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnSelectFolder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon-checked-22.png"))); // NOI18N
        btnSelectFolder.setText("Select");
        btnSelectFolder.setBorderPainted(false);
        btnSelectFolder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSelectFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectFolderActionPerformed(evt);
            }
        });

        tblFolderShare.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblFolderShare.setRowHeight(30);
        jScrollPane3.setViewportView(tblFolderShare);

        javax.swing.GroupLayout pnlShareFolderLayout = new javax.swing.GroupLayout(pnlShareFolder);
        pnlShareFolder.setLayout(pnlShareFolderLayout);
        pnlShareFolderLayout.setHorizontalGroup(
            pnlShareFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
            .addGroup(pnlShareFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlShareFolderLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(pnlShareFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlShareFolderLayout.createSequentialGroup()
                            .addGap(701, 701, 701)
                            .addComponent(btnUnSelectFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnSelectFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlShareFolderLayout.setVerticalGroup(
            pnlShareFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
            .addGroup(pnlShareFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlShareFolderLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(pnlShareFolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnUnSelectFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSelectFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnlShares.add(pnlShareFolder, "pnlShareFolder");

        jPanel1.add(pnlShares);
        pnlShares.setBounds(8, 110, 930, 340);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTypeShareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTypeShareActionPerformed
        try {
            if (cmbTypeShare.getSelectedItem().toString() != null) {
                String selected = cmbTypeShare.getSelectedItem().toString();
                switch (selected) {
                    case "File":
                        selectedTypeShare = selected;
                        tranferLayout("pnlShareFile");
                        break;
                    case "Folder":
                        selectedTypeShare = selected;
                        tranferLayout("pnlShareFolder");
                        break;
                    default:
                        selectedTypeShare = "file";
                        tranferLayout("pnlShareFile");
                        break;
                }
            }
        } catch (Exception ex) {
            System.err.println("cmbTypeShare nạp lấy lần đầu s bị null" + ex);
        }
    }//GEN-LAST:event_cmbTypeShareActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSelectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectFileActionPerformed
        int selectedRow = tblFileShare.getSelectedRow();
        if (selectedRow != -1) {
            // Lấy ra fileId
            String fileId = tblFileShareModel.getValueAt(selectedRow, 0).toString();
            if (AddToListIdFileSelected(fileId)) {
                Message("Đã chọn chia sẻ file này.!!!");
            } else {
                Message("File này đã được chọn rồi.!!!");
            }
            return;
        }
        Message("Vui lòng chọn file cần chia sẻ.!!!");
    }//GEN-LAST:event_btnSelectFileActionPerformed

    private void cmbPermissionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPermissionsActionPerformed
        try {
            if (cmbPermissions.getSelectedItem().toString() != null) {
                permissionSelected = cmbPermissions.getSelectedItem().toString();
            }
        } catch (Exception ex) {
            System.err.println("permissionSelected nạp lấy lần đầu s bị null" + ex);
        }
    }//GEN-LAST:event_cmbPermissionsActionPerformed

    private void btnUnSelectAllFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnSelectAllFileActionPerformed
        listIdFileSelected.removeAll(listIdFileSelected);
        Message("Đã hủy chọn toàn bộ file.!!!");
    }//GEN-LAST:event_btnUnSelectAllFileActionPerformed

    private void btnUnSelectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnSelectFileActionPerformed
        int selectedRow = tblFileShare.getSelectedRow();
        if (selectedRow != -1) {
            // Lấy ra fileId
            String fileId = tblFileShareModel.getValueAt(selectedRow, 0).toString();
            if (RemoveFromListIdFileSelected(fileId)) {
                Message("Đã hủy chia sẻ file này.!!!");
            } else {
                Message("File này chưa được chọn nên làm gì có mà hủy.!!!");
            }
            return;
        }
        Message("Vui lòng chọn file cần hủy chia sẻ.!!!");
    }//GEN-LAST:event_btnUnSelectFileActionPerformed

    private void btnShareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShareActionPerformed

        if (selectedTypeShare != null) {
            String peopleShares = txtPeopleShares.getText();
            if (peopleShares.trim().equals("")) {
                Message("Vui lòng nhập email người dùng cần chia sẻ.!!!");
                return;
            } else {
                // tạo 1 list chứ email người đc chia sẻ
                List<String> listPeopleShare = new ArrayList<>();
                // tách mảng và add vào list email ng đc chia sẻ

                for (String item : peopleShares.split(";")) {
                    listPeopleShare.add(item);
                }
                permissionSelected = permissionSelected.split("|")[0].trim();
                switch (selectedTypeShare) {
                    case "File": {
                        if (listIdFileSelected.size() > 0) {
                            DataShare dataShare = new DataShare(listIdFileSelected, listPeopleShare, permissionSelected, fromEmail);
                            ClientThread.request("share_files", dataShare);
                            Message("Chia sẻ file thành công.!!!");
                            this.dispose();
                        } else {
                            Message("Vui lòng chọn file cần chia sẻ.!!!");
                        }
                        break;
                    }
                    case "Folder": {
                        if (folderIdSelected != null) {
                            DataShare dataShare = new DataShare(folderIdSelected, listPeopleShare, permissionSelected, fromEmail);
                            ClientThread.request("share_folder", dataShare);
                            Message("Chia sẻ thư mục thành công.!!!");
                            this.dispose();
                        } else {
                            Message("Vui lòng chọn folder cần chia sẻ.!!!");
                        }
                        break;
                    }
                }
            }
        } else {
            Message("Vui lòng chọn loại hình cần chia sẻ.!!!");
        }
    }//GEN-LAST:event_btnShareActionPerformed

    private void btnSelectFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectFolderActionPerformed
        int selectedRow = tblFolderShare.getSelectedRow();
        if (selectedRow != -1) {
            // Lấy ra fileId
            String folderId = tblFolderShareModel.getValueAt(selectedRow, 0).toString().trim();
            folderIdSelected = folderId;
            Message("Đã chọn chia sẻ folder này.!!!");
            return;
        }
        Message("Vui lòng chọn folder cần chia sẻ.!!!");
    }//GEN-LAST:event_btnSelectFolderActionPerformed

    private boolean AddToListIdFileSelected(String fileId) {
        for (String id : listIdFileSelected) {
            if (fileId.trim().equals(id.trim())) {
                return false;
            }
        }
        listIdFileSelected.add(fileId);
        return true;
    }

    private boolean RemoveFromListIdFileSelected(String fileId) {
        for (String id : listIdFileSelected) {
            if (fileId.trim().equals(id.trim())) {
                listIdFileSelected.remove(id);
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="chuyển đổi layout">
    private void tranferLayout(String panelName) {
        CardLayout layout = (CardLayout) pnlShares.getLayout();
        layout.show(pnlShares, panelName);
    }
    // </editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Ẩn cột đầu tiên của table">
    private void hiddenColumnFirst(JTable table) {
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);
    }
    // </editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Hiển thị thông báo">
    private void Message(String message) {
        JOptionPane.showMessageDialog(rootPane, message);
    }
    // </editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Main">
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SharePeople.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SharePeople.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SharePeople.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SharePeople.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SharePeople dialog = new SharePeople(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // </editor-fold>

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSelectFile;
    private javax.swing.JButton btnSelectFolder;
    private javax.swing.JButton btnShare;
    private javax.swing.JButton btnUnSelectAllFile;
    private javax.swing.JButton btnUnSelectFile;
    private javax.swing.JButton btnUnSelectFolder;
    private javax.swing.JComboBox<String> cmbPermissions;
    private javax.swing.JComboBox<String> cmbTypeShare;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnlShareFile;
    private javax.swing.JPanel pnlShareFolder;
    private javax.swing.JPanel pnlShares;
    private javax.swing.JTable tblFileShare;
    private javax.swing.JTable tblFolderShare;
    private javax.swing.JTextArea txtPeopleShares;
    // End of variables declaration//GEN-END:variables
}
