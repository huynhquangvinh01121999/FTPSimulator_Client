package features.handlers;

import app.components.ClientUI;
import models.HandleResult;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class Handler extends HandleBase {

    @Override
    public void handleRequest(String[] data) {
        String message = data[0];
        switch (message.toUpperCase()) {

            case "RESPONSE_VERIFY_REGISTER": {
                System.out.println("Server said: " + message);
                HandleResult result = new HandleResult(Boolean.parseBoolean(data[1]), data[2], Integer.parseInt(data[3]));
                ClientUI.processHandler(result.isSuccessed(), result.getMessage());
                if (result.isSuccessed()) {
                    ClientUI.setVerifyCode(Integer.parseInt(String.valueOf(result.getValue())));
                }
                break;
            }
            default:
                System.out.println("Server said: " + message);
                break;
        }
    }

}
