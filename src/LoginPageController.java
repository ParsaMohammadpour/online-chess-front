import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.*;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.Scanner;

public class LoginPageController {
    private Socket socket;

    public static String user;

    private DataInputStream dis;

    private DataOutputStream dos;

    public static Image profileImage=null;


    @FXML
    Button loginButton;

    @FXML
    TextField usernameField;

    @FXML
    PasswordField passwordField;

    @FXML
    TextField textFieldPassword;

    @FXML
    Label wrongUsernameLabel;

    @FXML
    Label wrongPasswordLabel;

    @FXML
    ImageView visibilityImage;

    @FXML
    Label usernameEnterLabel;

    @FXML
    Label passwordEnterLabel;

    @FXML
    ImageView unvisibilityImage;

    @FXML
    Button registerPageButton;

    @FXML
    Label passwordCharacterLabel;

    public void initialize() {
        TranslateTransition translateTransition = new TranslateTransition
                (Duration.millis(1300), loginButton);
        translateTransition.setToY(-230);
        translateTransition.playFromStart();
        passwordField.setVisible(true);
        textFieldPassword.setVisible(false);
        visibilityImage.setVisible(false);
        unvisibilityImage.setVisible(true);
        user = null;
        passwordCharacterLabel.setVisible(false);
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    socket = new Socket(Main.ip, 8888);
                    dis = new DataInputStream(socket.getInputStream());
                    dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeUTF("LOGIN");
                    dos.flush();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
        if (Paths.get("UserData.txt").toFile().exists()) {
            try (Scanner Filescanner = new Scanner(Paths.get("UserData.txt").toFile())) {
                usernameField.setText(Filescanner.nextLine());
                passwordField.setText(Filescanner.nextLine());
                textFieldPassword.setText(passwordField.getText());
            } catch (Exception e) {
                e.getMessage();
                e.printStackTrace();
            }
        }
    }

    public void login(ActionEvent actionEvent) throws Exception {
        wrongUsernameLabel.setVisible(false);
        wrongPasswordLabel.setVisible(false);
        usernameEnterLabel.setVisible(false);
        passwordEnterLabel.setVisible(false);
        passwordCharacterLabel.setVisible(false);
        if (usernameField.getText() == null || usernameField.getText().length() == 0) {
            usernameEnterLabel.setVisible(true);
            return;
        }
        if (passwordField.isVisible() && (passwordField.getText() == null) || (passwordField.getText().length() == 0)) {
            passwordEnterLabel.setVisible(true);
            return;
        }
        if (textFieldPassword.isVisible() && (textFieldPassword.getText() == null) || (textFieldPassword.getText().length() == 0)) {
            passwordEnterLabel.setVisible(true);
            return;
        }
        if (passwordField.getText().length() < 8) {
            passwordCharacterLabel.setVisible(true);
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF(usernameField.getText());
                    dos.flush();
                    if (passwordField.isVisible()) {
                        dos.writeUTF(passwordField.getText());
                        dos.flush();
                    } else {
                        dos.writeUTF(textFieldPassword.getText());
                        dos.flush();
                    }
                    String answer = dis.readUTF();
                    switch (answer) {
                        case "WRONG USERNAME": {
                            wrongUsernameLabel.setVisible(true);
                        }
                        break;
                        case "LOGEDIN": {
                            user=usernameField.getText();
                            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
                            profileImage= (Image) ois.readObject();
                            dis=new DataInputStream(socket.getInputStream());
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
                        case "WRONG PASSWORD": {
                            wrongPasswordLabel.setVisible(true);
                        }
                        break;
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void showPassword() {
        if (passwordField.isVisible()) {
            textFieldPassword.setText(passwordField.getText());
            passwordField.setVisible(false);
            textFieldPassword.setVisible(true);
        } else {
            passwordField.setText(textFieldPassword.getText());
            passwordField.setVisible(true);
            textFieldPassword.setVisible(false);
        }
    }

    public void clickVisible(MouseEvent mouseEvent) {
        showPassword();
        if (unvisibilityImage.isVisible()) {
            unvisibilityImage.setVisible(false);
            visibilityImage.setVisible(true);
        } else {
            unvisibilityImage.setVisible(true);
            visibilityImage.setVisible(false);
        }
    }

    public void gotoRegisterPage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("RegisterPage.fxml");
    }

    public void changeColorEnter(MouseEvent mouseEvent) {
        registerPageButton.setUnderline(true);
    }

    public void changeColorExit(MouseEvent mouseEvent) {
        registerPageButton.setUnderline(false);
    }
}