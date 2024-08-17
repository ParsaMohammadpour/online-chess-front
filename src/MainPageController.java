import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MainPageController {
    private Socket socket;

    private DataInputStream dis;

    private DataOutputStream dos;

    private Socket gameListenerSocket;

    private DataInputStream gameListenerDIS;

    private DataOutputStream gameListenerDOS;

    @FXML
    ImageView settingImageView;

    @FXML
    ImageView writerImageView;

    @FXML
    ListView<String> blockedListView;

    @FXML
    ListView<String> onlineListView;

    @FXML
    ListView<String> historyListView;

    @FXML
    ImageView refreshImageView;

    @FXML
    ImageView profileImageView;

    @FXML
    ImageView sendImageView;

    @FXML
    ImageView searchImageView;

    @FXML
    TextField searchTextField;

    @FXML
    Button blockUserButton;

    public void initialize() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(Main.ip, 8888);
                    dis = new DataInputStream(socket.getInputStream());
                    dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeUTF("MAIN PAGE");
                    dos.flush();
                    dos.writeUTF(LoginPageController.user);
                    dos.flush();
                    dos.writeUTF("INITIALIZE");
                    dos.flush();
                    int counter;
                    ArrayList<String> getInputsOfOnlines=new ArrayList<>();
                    ArrayList<String>getInputOfBlocked=new ArrayList<>();
                    ArrayList<String>getInputOfHistory=new ArrayList<>();
                    counter=dis.readInt();
                    if (counter!=0){
                        for (int i = 0; i < counter; i++) {
                            getInputsOfOnlines.add(dis.readUTF());
                        }
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                onlineListView.getItems().clear();
                                for (int i = 0; i < getInputsOfOnlines.size(); i++) {
                                    onlineListView.getItems().add(getInputsOfOnlines.get(i));
                                }
                            }
                        });
                    }
                    counter=dis.readInt();
                    if (counter!=0){
                        for (int i = 0; i < counter; i++) {
                            getInputOfHistory.add(dis.readUTF());
                        }
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                historyListView.getItems().clear();
                                for (int i = 0; i < getInputOfHistory.size(); i++) {
                                    historyListView.getItems().add(getInputOfHistory.get(i));
                                }
                            }
                        });
                    }
                    counter=dis.readInt();
                    if (counter!=0){
                        for (int i = 0; i < counter; i++) {
                            getInputOfBlocked.add(dis.readUTF());
                        }
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                blockedListView.getItems().clear();
                                for (int i = 0; i < getInputOfBlocked.size(); i++) {
                                    blockedListView.getItems().add(getInputOfBlocked.get(i));
                                }
                            }
                        });
                    }
                }catch (Exception e){e.printStackTrace();}
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    gameListenerSocket = new Socket(Main.ip, 8888);
                    gameListenerDIS = new DataInputStream(gameListenerSocket.getInputStream());
                    gameListenerDOS = new DataOutputStream(gameListenerSocket.getOutputStream());
                    gameListenerDOS.writeUTF("MAIN PAGE");
                    gameListenerDOS.flush();
                    gameListenerDOS.writeUTF(LoginPageController.user);
                    gameListenerDOS.flush();
                    gameListenerDOS.writeUTF("LISTEN FOR GAME REQUEST");
                    gameListenerDOS.flush();
                    while (true) {
                        String usernameRequest = gameListenerDIS.readUTF();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                                        "REQUEST RECIEVED FROM USER : " + usernameRequest, ButtonType.NO, ButtonType.OK);
                                alert.showAndWait();
                                if (alert.getResult() == ButtonType.NO) {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                gameListenerDOS.writeUTF(usernameRequest);
                                                gameListenerDOS.flush();
                                                gameListenerDOS.writeUTF("REJECT");
                                                gameListenerDOS.flush();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }).start();
                                } else if (alert.getResult() == ButtonType.OK) {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                gameListenerDOS.writeUTF(usernameRequest);
                                                gameListenerDOS.flush();
                                                gameListenerDOS.writeUTF("ACCEPT");
                                                gameListenerDOS.flush();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }).start();
                                    try {
                                        new PageLoader().load("Game.fxml");
                                    }catch (Exception e){e.printStackTrace();}
                                }
                            }
                        });
                    }
//                    if (answer.equals("WAIT")){
//                        loop:while (true){
//                            input=gameListenerDIS.readUTF();
//                            if (input.equals("A GAME REQUEST RECIEVD")){
//                                Platform.runLater(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        try {
//                                            new PageLoader().load("Game.fxml");
//                                        }catch (Exception e){e.printStackTrace();}
//                                    }
//                                });
//                                break loop;
//                            }
//                            if (input.equals("HAVE A GAME")){
//                                Platform.runLater(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        try {
//                                            new PageLoader().load("Game.fxml");
//                                        }catch (Exception e){e.printStackTrace();}
//                                    }
//                                });
//                                break loop;
//                            }
//                        }
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void goToSetting(MouseEvent mouseEvent) {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        dos.writeUTF("GOTOSETTING");
                        dos.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            new PageLoader().load("SettingPage.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToWriterPage(MouseEvent mouseEvent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("WRITERPAGE");
                    dos.flush();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                new PageLoader().load("Auther.fxml");
                            } catch (Exception e) {
                                e.printStackTrace();
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("REFRESH");
                    dos.flush();
                    int counter;
                    ArrayList<String> getInputsOfOnlines=new ArrayList<>();
                    ArrayList<String>getInputOfBlocked=new ArrayList<>();
                    ArrayList<String>getInputOfHistory=new ArrayList<>();
                    counter=dis.readInt();
                    if (counter!=0){
                        for (int i = 0; i < counter; i++) {
                            getInputsOfOnlines.add(dis.readUTF());
                        }
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                onlineListView.getItems().clear();
                                for (int i = 0; i < getInputsOfOnlines.size(); i++) {
                                    onlineListView.getItems().add(getInputsOfOnlines.get(i));
                                }
                            }
                        });
                    }
                    counter=dis.readInt();
                    if (counter!=0){
                        for (int i = 0; i < counter; i++) {
                            getInputOfHistory.add(dis.readUTF());
                        }
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                historyListView.getItems().clear();
                                for (int i = 0; i < getInputOfHistory.size(); i++) {
                                    historyListView.getItems().add(getInputOfHistory.get(i));
                                }
                            }
                        });
                    }
                    counter=dis.readInt();
                    if (counter!=0){
                        for (int i = 0; i < counter; i++) {
                            getInputOfBlocked.add(dis.readUTF());
                        }
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                blockedListView.getItems().clear();
                                for (int i = 0; i < getInputOfBlocked.size(); i++) {
                                    blockedListView.getItems().add(getInputOfBlocked.get(i));
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void send() {
        if (onlineListView.getSelectionModel().getSelectedItem() == null) return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("SEND GAME REQUEST");
                    dos.flush();
                    dos.writeUTF(onlineListView.getSelectionModel().getSelectedItem());
                    dos.flush();
                    String answer = dis.readUTF();
                    if (answer.equals("ACCEPT")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    new PageLoader().load("Game.fxml");
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                        });
                    } else if (answer.equals("REJECT")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.ERROR, "FAILED TO MAKE GAME :(",ButtonType.CLOSE);
                                alert.showAndWait();
                            }
                        });
                        refresh();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void logout(MouseEvent mouseEvent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("LOGOUT");
                    dos.flush();
                    LoginPageController.profileImage = null;
                    LoginPageController.user = null;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                new PageLoader().load("LoginPage.fxml");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void search(MouseEvent mouseEvent) {
        ArrayList<String> users = new ArrayList<>();
        if (searchTextField.getText() == null || searchTextField.getText().length() == 0) return;
        for (int i = 0; i < onlineListView.getItems().size(); i++) {
            if (onlineListView.getItems().get(i).contains(searchTextField.getText())) {
                users.add(onlineListView.getItems().get(i));
            }
        }
        onlineListView.getItems().clear();
        for (int i = 0; i < users.size(); i++) {
            onlineListView.getItems().add(users.get(i));
        }
    }


    public void blockThisUser(MouseEvent mouseEvent) {
        if (onlineListView.getSelectionModel().getSelectedItem() == null ||
                onlineListView.getSelectionModel().getSelectedItem().length() == 0) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("BLOCKUSER");
                    dos.flush();
                    dos.writeUTF(onlineListView.getSelectionModel().getSelectedItem());
                    dos.flush();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            blockedListView.getItems().add(onlineListView.getSelectionModel().getSelectedItem());
                            onlineListView.getItems().remove(onlineListView.getSelectionModel().getSelectedItem());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void goToOngoingGame(MouseEvent mouseEvent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("GO TO ONGOING GAME");
                    dos.flush();
                    String answer=dis.readUTF();
                    switch (answer){
                        case "GO TO GAME PAGE":{
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        new PageLoader().load("Game.fxml");
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }break;
                        case "SHOW ALERT":{
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Alert alert=new Alert(Alert.AlertType.ERROR,"NO ONGOING GAME",ButtonType.CLOSE);
                                    alert.showAndWait();
                                }
                            });
                        }break;
                    }
                }catch (Exception e){e.printStackTrace();}
            }
        }).start();
    }
}