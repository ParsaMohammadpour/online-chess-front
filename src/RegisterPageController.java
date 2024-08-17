import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;


public class RegisterPageController {

    private Socket socket;

    private DataInputStream dis;

    private DataOutputStream dos;

    @FXML
    ImageView visibleImage;

    @FXML
    ImageView unvisibleImage;

    @FXML
    TextField username;

    @FXML
    TextField name;

    @FXML
    TextField lastname;

    @FXML
    TextField passwordText1;

    @FXML
    TextField passwordText2;

    @FXML
    PasswordField password1;

    @FXML
    PasswordField password2;

    @FXML
    Button registerButton;

    @FXML
    Button loginPageButton;

    @FXML
    ImageView visibleImage2;

    @FXML
    ImageView unvisibleImage2;

    @FXML
    Label nullNmaeLabel;

    @FXML
    Label nullLastnameLabel;

    @FXML
    Label nullUsernameLabel;

    @FXML
    Label nullPasswordLabel;

    @FXML
    Label nullPassword2Label;

    @FXML
    Label invalidUsernameLabel;

    @FXML
    Label invalidPassword;

    @FXML
    Label doesntmatchPasswordLabel;

    public void initialize() {
        username.setText(null);
        name.setText(null);
        lastname.setText(null);
        password1.setText(null);
        password2.setText(null);
        passwordText1.setText(null);
        passwordText1.setVisible(false);
        passwordText2.setVisible(false);
        visibleImage2.setVisible(false);
        visibleImage.setVisible(false);
        unvisibleImage2.setVisible(true);
        unvisibleImage.setVisible(true);
        password2.setVisible(true);
        password1.setVisible(true);
        passwordText2.setText(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket=new Socket(Main.ip, 8888);
                    dis =new DataInputStream(socket.getInputStream());
                    dos =new DataOutputStream(socket.getOutputStream());
                    dos.writeUTF("REGISTER");
                    dos.flush();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void mouseEntre(MouseEvent mouseEvent) {
        loginPageButton.setUnderline(true);
    }

    public void mouseExit(MouseEvent mouseEvent) {
        loginPageButton.setUnderline(false);
    }

    public void setUnvisible(MouseEvent mouseEvent) {
        visibleImage.setVisible(false);
        unvisibleImage.setVisible(true);
        password1.setText(passwordText1.getText());
        passwordText1.setVisible(false);
        password1.setVisible(true);
    }

    public void setVisible(MouseEvent mouseEvent) {
        visibleImage.setVisible(true);
        unvisibleImage.setVisible(false);
        passwordText1.setText(password1.getText());
        passwordText1.setVisible(true);
        password1.setVisible(false);
    }

    public void setUnvisible2(MouseEvent mouseEvent) {
        visibleImage2.setVisible(false);
        unvisibleImage2.setVisible(true);
        password2.setText(passwordText2.getText());
        passwordText2.setVisible(false);
        password2.setVisible(true);
    }

    public void setVisible2(MouseEvent mouseEvent) {
        visibleImage2.setVisible(true);
        unvisibleImage2.setVisible(false);
        passwordText2.setText(password2.getText());
        passwordText2.setVisible(true);
        password2.setVisible(false);
    }

    private void setTextsNull() {
        name.setText(null);
        lastname.setText(null);
        username.setText(null);
        password2.setText(null);
        password1.setText(null);
        passwordText2.setText(null);
        passwordText1.setText(null);
    }

    public void registering(MouseEvent mouseEvent) throws IOException {
        nullNmaeLabel.setVisible(false);
        nullLastnameLabel.setVisible(false);
        nullUsernameLabel.setVisible(false);
        nullPassword2Label.setVisible(false);
        nullPasswordLabel.setVisible(false);
        invalidPassword.setVisible(false);
        invalidUsernameLabel.setVisible(false);
        doesntmatchPasswordLabel.setVisible(false);
        if (name.getText() == null || name.getText().length() == 0) {
            nullNmaeLabel.setVisible(true);
            setTextsNull();
            return;
        }
        if (lastname.getText() == null || lastname.getText().length() == 0) {
            nullLastnameLabel.setVisible(true);
            setTextsNull();
            return;
        }
        if (username.getText() == null || username.getText().length() == 0) {
            nullUsernameLabel.setVisible(true);
            setTextsNull();
            return;
        }
        if ((password1.getText() == null && passwordText1.getText() == null) ||
                (password1.getText().length() == 0 && passwordText1.getText().length() == 0)) {
            nullPasswordLabel.setVisible(true);
            setTextsNull();
            return;
        }
        if ((password2.getText() == null && passwordText2.getText() == null) ||
                (password2.getText().length() == 0 && passwordText2.getText().length() == 0)) {
            nullPassword2Label.setVisible(true);
            setTextsNull();
            return;
        }
        if (password1.isVisible() && password2.isVisible()) {
            if (!password1.getText().equals(password2.getText())){
                doesntmatchPasswordLabel.setVisible(true);
                return;
            }
        } else if (password1.isVisible()) {
            if (!password1.getText().equals(passwordText2.getText())){
                doesntmatchPasswordLabel.setVisible(true);
                return;
            }
        } else if (password2.isVisible()) {
            if (!password2.getText().equals(passwordText1.getText())){
                doesntmatchPasswordLabel.setVisible(true);
                return;
            }
        } else {
            if (!passwordText1.getText().equals(passwordText2.getText())){
                doesntmatchPasswordLabel.setVisible(true);
                return;
            }
        }
        if (password1.isVisible() && password1.getText().length() < 8){
            invalidPassword.setVisible(true);
            return;
        }
        if (passwordText1.isVisible() && passwordText1.getText().length()<8){
            invalidPassword.setVisible(true);
            return;
        }
        if (password2.isVisible() && password2.getText().length() < 8){
            invalidPassword.setVisible(true);
            return;
        }
        if (passwordText2.isVisible() && passwordText2.getText().length()<8){
            invalidPassword.setVisible(true);
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF(name.getText());
                    dos.flush();
                    dos.writeUTF(lastname.getText());
                    dos.flush();
                    dos.writeUTF(username.getText());
                    if (password1.isVisible()) {
                        dos.writeUTF(password1.getText());
                        dos.flush();
                    } else {
                        dos.writeUTF(passwordText1.getText());
                        dos.flush();
                    }
                    String answer = dis.readUTF();
                    switch (answer) {
                        case "USERNAME EXIST": {
                            invalidUsernameLabel.setVisible(true);
                        }break;
                        case "REGISTERED": {
                            try (Formatter fileFormatter = new Formatter(Paths.get("UserData.txt").toFile())) {
                                if (password1.isVisible()) {
                                    fileFormatter.format(username.getText() + "\n" + password1.getText());
                                    fileFormatter.flush();
                                } else {
                                    fileFormatter.format(username.getText() + "\n" + passwordText1.getText());
                                    fileFormatter.flush();
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage()+"%%%%%%%%%%%%%%%%%%%5");
                                e.printStackTrace();
                            }
                            LoginPageController.user=username.getText();
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        new PageLoader().load("MainPage.fxml");
                                    }catch (Exception e){e.printStackTrace();}
                                }
                            });
                        }
                        break;
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage()+"************************");
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void loginPage(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("LoginPage.fxml");
        }catch (Exception e){e.printStackTrace();}
    }

    public void reset(MouseEvent mouseEvent) {
        setTextsNull();
    }
}
