import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class SettingPageController {
    private Socket socket;

    private DataInputStream dis;

    private DataOutputStream dos;

    private ObjectInputStream objectInputStream;

    private ObjectOutputStream objectOutputStream;

    @FXML
    ListView<String> blockedListView;

    @FXML
    Button unblockButton;

    @FXML
    Button changeFieldButton;

    @FXML
    ChoiceBox<String> changeFieldChoiceBox;

    @FXML
    ImageView profileImageView;

    @FXML
    TextField textField1;

    @FXML
    TextField textField2;

    @FXML
    Label invalidPasswordLabel;

    @FXML
    Label invalidUsernameLabel;

    @FXML
    Label notSameLabel;

    @FXML
    Label nullLAbel;

    @FXML
    Button backButton;

    @FXML
    Label doneLabel;

    @FXML
    Label pathLabel;

    @FXML
    TextField pathTextField;

    @FXML
    Button setImageButton;

    @FXML
    Button removeImageButton;

    public void initialize() {
        changeFieldChoiceBox.getItems().add("PASSWORD");
        changeFieldChoiceBox.getItems().add("USERNAME");
        changeFieldChoiceBox.getItems().add("NAME");
        changeFieldChoiceBox.getItems().add("LASTNAME");
        notSameLabel.setVisible(false);
        invalidPasswordLabel.setVisible(false);
        doneLabel.setVisible(false);
        invalidUsernameLabel.setVisible(false);
        nullLAbel.setVisible(false);
        ArrayList<String>blocked=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(Main.ip, 8888);
                    dis = new DataInputStream(socket.getInputStream());
                    dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeUTF("SETTING PAGE");
                    dos.flush();
                    dos.writeUTF(LoginPageController.user);
                    dos.flush();
                    int counter = dis.readInt();
                    for (int i = 0; i < counter; i++) {
                        blocked.add(dis.readUTF());
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            blockedListView.getItems().clear();
                            for (int i = 0; i < blocked.size(); i++) {
                                blockedListView.getItems().add(blocked.get(i));
                            }
                            if (LoginPageController.profileImage!=null){
                                profileImageView.setImage(LoginPageController.profileImage);
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void refresh() {
        notSameLabel.setVisible(false);
        invalidPasswordLabel.setVisible(false);
        doneLabel.setVisible(false);
        invalidUsernameLabel.setVisible(false);
        nullLAbel.setVisible(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("REFRESH");
                    dos.flush();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                blockedListView.getItems().clear();
                                int counter = dis.readInt();
                                for (int i = 0; i < counter; i++) {
                                    blockedListView.getItems().add(dis.readUTF());
                                }
                            }catch (Exception e){e.printStackTrace();}
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void unblock(MouseEvent mouseEvent) {
        notSameLabel.setVisible(false);
        invalidPasswordLabel.setVisible(false);
        doneLabel.setVisible(false);
        invalidUsernameLabel.setVisible(false);
        nullLAbel.setVisible(false);
        if (blockedListView.getSelectionModel().getSelectedItem() == null) return;
        ArrayList<String>copy=new ArrayList<>();
        for (int i = 0; i < blockedListView.getItems().size(); i++) {
            copy.add(blockedListView.getItems().get(i));
        }
        String value=blockedListView.getSelectionModel().getSelectedItem();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("UNBLOCK");
                    dos.flush();
                    dos.writeUTF(value);
                    dos.flush();
                    copy.clear();
                    int counter = dis.readInt();
                    for (int i = 0; i < counter; i++) {
                        copy.add(dis.readUTF());
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            blockedListView.getItems().clear();
                            for (int i = 0; i < copy.size(); i++) {
                                blockedListView.getItems().add(copy.get(i));
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void changeField(MouseEvent mouseEvent) {
        notSameLabel.setVisible(false);
        invalidPasswordLabel.setVisible(false);
        doneLabel.setVisible(false);
        invalidUsernameLabel.setVisible(false);
        nullLAbel.setVisible(false);
        if (changeFieldChoiceBox.getValue() == null) return;
        if (!textField1.getText().equals(textField2.getText())) {
            notSameLabel.setVisible(true);
            return;
        }
        if (changeFieldChoiceBox.getValue().equals("PASSWORD")) {
            if (textField1.getText().length() < 8) {
                invalidPasswordLabel.setVisible(true);
                return;
            }
        }
        if (textField2.getText() == null) {
            nullLAbel.setVisible(true);
            return;
        }
        if (textField1.getText() == null) {
            nullLAbel.setVisible(true);
            return;
        }
        if (textField1.getText().length() == 0 || textField2.getText().length() == 0) {
            nullLAbel.setVisible(true);
            return;
        }
        String value=textField1.getText();
        String change=changeFieldChoiceBox.getValue();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String answer;
                    dos.writeUTF("CHANGE FIELD");
                    dos.flush();
                    switch (changeFieldChoiceBox.getValue()) {
                        case "PASSWORD": {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Formatter formatter = new Formatter(Paths.get("UserData.txt").toFile());
                                        formatter.format(LoginPageController.user + "\n" + value);
                                        formatter.flush();
                                        formatter.close();
                                    }catch (Exception e){e.printStackTrace();}
                                }
                            });
                        }
                        case "NAME":
                        case "LASTNAME": {
                            dos.writeUTF(change);
                            dos.flush();
                            dos.writeUTF(value);
                            dos.flush();
                        }
                        break;
                        case "USERNAME": {
                            dos.writeUTF(value);
                            dos.flush();
                            answer = dis.readUTF();
                            if (answer.equals("FAILED")) {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        invalidUsernameLabel.setVisible(true);
                                    }
                                });
                            } else {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Formatter formatter = new Formatter(Paths.get("UserData.txt").toFile());
                                            formatter.format(textField1.getText() + "\n" + answer);
                                            formatter.flush();
                                            formatter.close();
                                        }catch (Exception e){e.printStackTrace();}
                                    }
                                });
                            }
                        }
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void back(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("MainPage.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setImage(MouseEvent mouseEvent) {
        pathLabel.setVisible(false);
        if (pathTextField.getText() == null || pathTextField.getText().length() == 0) {
            pathLabel.setVisible(true);
            return;
        }
        if (Paths.get(pathTextField.getText()).toFile().exists()){}else {
            pathLabel.setVisible(true);
            return;
        }
        String path=pathTextField.getText();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Image profileImage=new Image(path);
                    dos.writeUTF("CHANGE PROFILE IMAGE");
                    dos.flush();
                    ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
                    oos.writeObject(profileImage);
                    oos.flush();
                    dis=new DataInputStream(socket.getInputStream());
                    dos=new DataOutputStream(socket.getOutputStream());
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            LoginPageController.profileImage=profileImage;
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void removeImage(MouseEvent mouseEvent) {
        if (LoginPageController.profileImage==null || profileImageView.getImage()==null){
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            profileImageView.setImage(null);
                            LoginPageController.profileImage = null;
                        }
                    });
                    dos.writeUTF("REMOVEPROFILEIMAGE");
                    dos.flush();
                }catch (Exception e){e.printStackTrace();}
            }
        }).start();
    }

    public void deleteAccount(MouseEvent mouseEvent) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"ARE YOU SURE ?!\nDO YOU REALLY WANT TO DELETE YOURE ACCOUNT?!!\n" +
                "YOUR ONGOING GAME WILL BE ABANDONED :(",ButtonType.YES,ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult()==ButtonType.YES){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        dos.writeUTF("DELETEACCOUNT");
                        dos.flush();
                        LoginPageController.profileImage=null;
                        LoginPageController.user=null;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    new PageLoader().load("LoginPage.fxml");
                                }catch (Exception e){e.printStackTrace();}
                            }
                        });
                    }catch (Exception e){e.printStackTrace();}
                }
            }).start();
            try (Scanner scanner=new Scanner(Paths.get("UserData.txt").toFile())){
                String username=scanner.nextLine();
                if (username.equals(LoginPageController.user)){
                    boolean isDeleted=Paths.get("UserData.txt").toFile().delete();
                }
            }catch (Exception e){e.printStackTrace();}
        }else if (alert.getResult()==ButtonType.NO){
            return;
        }
    }
}