import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Time;
import java.util.HashMap;

public class GameController {

    private Socket messageSendingSocket;

    private DataInputStream messageSendingDIS;

    private DataOutputStream messageSendingDOS;

    private Socket messageListenerSocket;

    private DataOutputStream messageListenerDOS;

    private DataInputStream messageListenerDIS;

    private Socket socket;

    private DataOutputStream dos;

    private DataInputStream dis;

    private static boolean isChatBlocked;

    private Time time;

    private boolean isWhit;

    private boolean isHisTurn;

    private int previousePlace;

    private int newPlace;

    private String text;

    @FXML
    Button chatButton;

    @FXML
    ImageView sendImageView;

    @FXML
    TextField textField;

    @FXML
    ListView<String> chatListView;

    @FXML
    Button abandonButton;

    @FXML
    Button mainPageButton;

    @FXML
    TextField turnTextField;

    @FXML
    ImageView upperImageView;

    @FXML
    ImageView downerImageView;

    @FXML
    ImageView refreshImageView;

    @FXML
    ImageView p11;

    @FXML
    ImageView p21;

    @FXML
    ImageView p31;

    @FXML
    ImageView p41;

    @FXML
    ImageView p51;

    @FXML
    ImageView p61;

    @FXML
    ImageView p71;

    @FXML
    ImageView p81;

    @FXML
    ImageView p12;

    @FXML
    ImageView p22;

    @FXML
    ImageView p32;

    @FXML
    ImageView p42;

    @FXML
    ImageView p52;

    @FXML
    ImageView p62;

    @FXML
    ImageView p72;

    @FXML
    ImageView p82;

    @FXML
    ImageView p13;

    @FXML
    ImageView p23;

    @FXML
    ImageView p33;

    @FXML
    ImageView p43;

    @FXML
    ImageView p53;

    @FXML
    ImageView p63;

    @FXML
    ImageView p73;

    @FXML
    ImageView p83;

    @FXML
    ImageView p14;

    @FXML
    ImageView p24;

    @FXML
    ImageView p34;

    @FXML
    ImageView p44;

    @FXML
    ImageView p54;

    @FXML
    ImageView p64;

    @FXML
    ImageView p74;

    @FXML
    ImageView p84;

    @FXML
    ImageView p15;

    @FXML
    ImageView p25;

    @FXML
    ImageView p35;

    @FXML
    ImageView p45;

    @FXML
    ImageView p55;

    @FXML
    ImageView p65;

    @FXML
    ImageView p75;

    @FXML
    ImageView p85;

    @FXML
    ImageView p16;

    @FXML
    ImageView p26;

    @FXML
    ImageView p36;

    @FXML
    ImageView p46;

    @FXML
    ImageView p56;

    @FXML
    ImageView p66;

    @FXML
    ImageView p76;

    @FXML
    ImageView p86;

    @FXML
    ImageView p17;

    @FXML
    ImageView p27;

    @FXML
    ImageView p37;

    @FXML
    ImageView p47;

    @FXML
    ImageView p57;

    @FXML
    ImageView p67;

    @FXML
    ImageView p77;

    @FXML
    ImageView p87;

    @FXML
    ImageView p18;

    @FXML
    ImageView p28;

    @FXML
    ImageView p38;

    @FXML
    ImageView p48;

    @FXML
    ImageView p58;

    @FXML
    ImageView p68;

    @FXML
    ImageView p78;

    @FXML
    ImageView p88;

    ImageView[][] imageViews = new ImageView[9][9];

    Image bKing = new Image("BKing.png");

    Image bVazir = new Image("BVazir.png");

    Image bFil = new Image("BFil.png");

    Image bHorse = new Image("BHorse.png");

    Image bRokh = new Image("BRokh.png");

    Image bSoldier = new Image("BSoldier.png");

    Image wKing = new Image("WKing.png");

    Image wVazir = new Image("WVazir.png");

    Image wFil = new Image("WFil.png");

    Image wHorse = new Image("WHorse.png");

    Image wRokh = new Image("WRokh.png");

    Image wSoldier = new Image("WSoldier.png");

    Image validImage = new Image("green.jpg");

    HashMap<Integer, String> blackMap = new HashMap<>();

    HashMap<Integer, String> whiteMap = new HashMap<>();

    public void initialize() {
        imageViews[1][1] = p11;
        imageViews[1][2] = p12;
        imageViews[1][3] = p13;
        imageViews[1][4] = p14;
        imageViews[1][5] = p15;
        imageViews[1][6] = p16;
        imageViews[1][7] = p17;
        imageViews[1][8] = p18;
        imageViews[2][1] = p21;
        imageViews[2][2] = p22;
        imageViews[2][3] = p23;
        imageViews[2][4] = p24;
        imageViews[2][5] = p25;
        imageViews[2][6] = p26;
        imageViews[2][7] = p27;
        imageViews[2][8] = p28;
        imageViews[3][1] = p31;
        imageViews[3][2] = p32;
        imageViews[3][3] = p33;
        imageViews[3][4] = p34;
        imageViews[3][5] = p35;
        imageViews[3][6] = p36;
        imageViews[3][7] = p37;
        imageViews[3][8] = p38;
        imageViews[4][1] = p41;
        imageViews[4][2] = p42;
        imageViews[4][3] = p43;
        imageViews[4][4] = p44;
        imageViews[4][5] = p45;
        imageViews[4][6] = p46;
        imageViews[4][7] = p47;
        imageViews[4][8] = p48;
        imageViews[5][1] = p51;
        imageViews[5][2] = p52;
        imageViews[5][3] = p53;
        imageViews[5][4] = p54;
        imageViews[5][5] = p55;
        imageViews[5][6] = p56;
        imageViews[5][7] = p57;
        imageViews[5][8] = p58;
        imageViews[6][1] = p61;
        imageViews[6][2] = p62;
        imageViews[6][3] = p63;
        imageViews[6][4] = p64;
        imageViews[6][5] = p65;
        imageViews[6][6] = p66;
        imageViews[6][7] = p67;
        imageViews[6][8] = p68;
        imageViews[7][1] = p71;
        imageViews[7][2] = p72;
        imageViews[7][3] = p73;
        imageViews[7][4] = p74;
        imageViews[7][5] = p75;
        imageViews[7][6] = p76;
        imageViews[7][7] = p77;
        imageViews[7][8] = p78;
        imageViews[8][1] = p81;
        imageViews[8][2] = p82;
        imageViews[8][3] = p83;
        imageViews[8][4] = p84;
        imageViews[8][5] = p85;
        imageViews[8][6] = p86;
        imageViews[8][7] = p87;
        imageViews[8][8] = p88;
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                imageViews[i][j].setImage(null);
            }
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(Main.ip, 8888);
                    dis = new DataInputStream(socket.getInputStream());
                    dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeUTF("GAME PAGE");
                    dos.flush();
                    dos.writeUTF(LoginPageController.user);
                    dos.flush();
                    dos.writeUTF("INITIALIZE");
                    dos.flush();
                    isWhit=dis.readBoolean();
                    boolean rivralImage = dis.readBoolean();
                    Image image;
                    if (isWhit) {
                        if (LoginPageController.profileImage != null) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    upperImageView.setImage(LoginPageController.profileImage);
                                }
                            });
                        }
                        if (rivralImage) {
                            ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
                            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                            image = (Image) ois.readObject();
                            dis = new DataInputStream(socket.getInputStream());
                            dos = new DataOutputStream(socket.getOutputStream());
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    downerImageView.setImage(image);
                                }
                            });
                        }
                    } else {
                        if (rivralImage) {
                            ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
                            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                            image = (Image) ois.readObject();
                            dis = new DataInputStream(socket.getInputStream());
                            dos = new DataOutputStream(socket.getOutputStream());
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    upperImageView.setImage(image);
                                    if (LoginPageController.profileImage != null) {
                                        downerImageView.setImage(LoginPageController.profileImage);
                                    }
                                }
                            });
                        }
                    }
                    isHisTurn = dis.readBoolean();
                    if (isHisTurn) {
                        previousePlace = 0;
                    }
                    int counter = dis.readInt();
                    int x, y, mapPlace;
                    String type;
                    for (int i = 0; i < counter; i++) {
                        type = dis.readUTF();
                        x = dis.readInt();
                        y = dis.readInt();
                        mapPlace = (10 * x) + y;
                        whiteMap.put(mapPlace, type);
                    }
                    counter = dis.readInt();
                    for (int i = 0; i < counter; i++) {
                        type = dis.readUTF();
                        x = dis.readInt();
                        y = dis.readInt();
                        mapPlace = (10 * x) + y;
                        blackMap.put(mapPlace, type);
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (isHisTurn) {
                                turnTextField.setText("Your Turn");
                            } else {
                                turnTextField.setText("Rival Turn");
                            }
                            int placeNumber;
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    placeNumber = (i * 10) + j;
                                    if (whiteMap.containsKey(placeNumber)) {
                                        switch (whiteMap.get(placeNumber)) {
                                            case "ROKH": {
                                                imageViews[i][j].setImage(wRokh);
                                            }
                                            break;
                                            case "ASB": {
                                                imageViews[i][j].setImage(wHorse);
                                            }
                                            break;
                                            case "FIL": {
                                                imageViews[i][j].setImage(wFil);
                                            }
                                            break;
                                            case "SHAH": {
                                                imageViews[i][j].setImage(wKing);
                                            }
                                            break;
                                            case "VAZIR": {
                                                imageViews[i][j].setImage(wVazir);
                                            }
                                            break;
                                            case "SARBAZ": {
                                                imageViews[i][j].setImage(wSoldier);
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    placeNumber = (i * 10) + j;
                                    if (blackMap.containsKey(placeNumber)) {
                                        switch (blackMap.get(placeNumber)) {
                                            case "ROKH": {
                                                imageViews[i][j].setImage(bRokh);
                                            }
                                            break;
                                            case "ASB": {
                                                imageViews[i][j].setImage(bHorse);
                                            }
                                            break;
                                            case "FIL": {
                                                imageViews[i][j].setImage(bFil);
                                            }
                                            break;
                                            case "SHAH": {
                                                imageViews[i][j].setImage(bKing);
                                            }
                                            break;
                                            case "VAZIR": {
                                                imageViews[i][j].setImage(bVazir);
                                            }
                                            break;
                                            case "SARBAZ": {
                                                imageViews[i][j].setImage(bSoldier);
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    messageListenerSocket = new Socket(Main.ip, 8888);
                    messageListenerDIS = new DataInputStream(messageListenerSocket.getInputStream());
                    messageListenerDOS = new DataOutputStream(messageListenerSocket.getOutputStream());
                    messageListenerDOS.writeUTF("GAME PAGE");
                    messageListenerDOS.flush();
                    messageListenerDOS.writeUTF(LoginPageController.user);
                    messageListenerDOS.flush();
                    if (!isChatBlocked) {
                        messageListenerDOS.writeUTF("LISTENNING FOR MESSAGE");
                        messageListenerDOS.flush();
                        loop:
                        while (true) {
                            String newText = messageListenerDIS.readUTF();
                            if (newText.equals("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH")) {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        refresh();
                                    }
                                });
                            } else if (newText.equals("LOOOOOOOOOOSseESES!@@##$$%^&&*(()`~::;;'!1@2LosSeE")){
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        Alert alert=new Alert(Alert.AlertType.INFORMATION,"You Losed :("
                                                ,ButtonType.CLOSE);
                                        alert.showAndWait();
                                        try {
                                            new PageLoader().load("MainPage.fxml");
                                        }catch (Exception e){e.printStackTrace();}
                                    }
                                });
                            }else if (newText.equals("&*^&%$^###%#@$%^$$%^&(%*(&%&^*($&^#$%@@#@#$@WON")){
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        Alert alert=new Alert(Alert.AlertType.INFORMATION,"You wone :)",ButtonType.OK);
                                        alert.showAndWait();
                                        try{
                                            new PageLoader().load("MainPage.fxml");
                                        }catch (Exception e){e.printStackTrace();}
                                    }
                                });
                            }else if (newText.equals("****&^&^**%%$^#@!@$%%$#@@12242344AbaANDONEDDDDDdD!@#$")){
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        Alert alert=new Alert(Alert.AlertType.INFORMATION,"The Other " +
                                                "User Abandoned\nYou Wone.",ButtonType.OK);
                                        alert.showAndWait();
                                        try {
                                            new PageLoader().load("MainPage.fxml");
                                        }catch (Exception e){e.printStackTrace();}
                                    }
                                });
                            }else {
                                if (!isChatBlocked) {
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            chatListView.getItems().add("RIVAL :" + newText);
                                        }
                                    });
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void chatBlockUnblock(MouseEvent mouseEvent) {
        isChatBlocked = !isChatBlocked;
        if (isChatBlocked) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
//                        socket = new Socket(Main.ip, 8888);
//                        dis=new DataInputStream(socket.getInputStream());
//                        dos=new DataOutputStream(socket.getOutputStream());
//                        dos.writeUTF("GAME PAGE");
//                        dos.flush();
//                        dos.writeUTF(LoginPageController.user);
//                        dos.flush();
                        dos.writeUTF("BLOCK CHAT");
                        dos.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        messageListenerSocket = new Socket(Main.ip, 8888);
                        messageListenerDIS = new DataInputStream(messageListenerSocket.getInputStream());
                        messageListenerDOS = new DataOutputStream(messageListenerSocket.getOutputStream());
                        messageListenerDOS.writeUTF("GAME PAGE");
                        messageListenerDOS.flush();
                        messageListenerDOS.writeUTF(LoginPageController.user);
                        messageListenerDOS.flush();
                        if (!isChatBlocked) {
                            messageListenerDOS.writeUTF("LISTENNING FOR MESSAGE");
                            messageListenerDOS.flush();
                            loop:
                            while (true) {
                                String newText = messageListenerDIS.readUTF();
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        chatListView.getItems().add("RIVAL :" + newText.substring(4));
                                    }
                                });
                                if (isChatBlocked) {
                                    break loop;
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void sendText() {
        if (!textField.getText().equals("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH")) {
            if (isChatBlocked) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "UNBLOCK THE CHAT FIRST!", ButtonType.OK);
                alert.showAndWait();
                return;
            }
        }
        if (textField.getText() == null || textField.getText().length() == 0) return;
        if (!textField.getText().equals("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH")) {
            chatListView.getItems().add("ME :" + textField.getText());
        }
        text = null;
        if (!textField.getText().equals("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH")) {
            text = "ME :" + textField.getText();
        } else {
            text = "REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH";
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    messageSendingSocket = new Socket(Main.ip, 8888);
                    messageSendingDIS = new DataInputStream(messageSendingSocket.getInputStream());
                    messageSendingDOS = new DataOutputStream(messageSendingSocket.getOutputStream());
                    messageSendingDOS.writeUTF("GAME PAGE");
                    messageSendingDOS.flush();
                    messageSendingDOS.writeUTF(LoginPageController.user);
                    messageSendingDOS.flush();
                    messageSendingDOS.writeUTF("SENDING MESSAGE");
                    messageSendingDOS.flush();
                    messageSendingDOS.writeUTF(text);
                    messageSendingDOS.flush();
                    messageSendingDOS.writeUTF(LoginPageController.user);
                    messageSendingDOS.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        textField.setText(null);
    }

    public void abanden(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "ARE YOU SURE ?!!!!!!!", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.NO) {
            return;
        } else if (alert.getResult() == ButtonType.YES) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
//                        socket = new Socket(Main.ip, 8888);
//                        dis = new DataInputStream(socket.getInputStream());
//                        dos = new DataOutputStream(socket.getOutputStream());
//                        dos.writeUTF("GAME PAGE");
//                        dos.flush();
//                        dos.writeUTF(LoginPageController.user);
//                        dos.flush();
                        dos.writeUTF("ABANDON");
                        dos.flush();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                }catch (Exception e){e.printStackTrace();}
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void backToMainPage(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("MainPage.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendTextWithKey(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            sendText();
        }
    }

    public void moveOrSave11() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 11;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[1][1].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 11;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave12() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 12;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[1][2].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 12;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");

        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave13() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 13;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[1][3].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 13;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave14() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 14;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[1][4].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 14;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");

        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave15() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 15;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[1][5].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 15;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");

        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave16() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 16;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[1][6].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 16;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave17() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 17;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[1][7].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 17;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave18() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 18;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[1][8].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 18;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave21() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 21;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[2][1].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 21;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave22() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 22;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[2][2].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 22;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave23() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 23;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[2][3].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 23;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave24() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 24;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[2][4].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 24;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave25() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 25;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[2][5].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 25;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave26() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 26;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[2][6].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 26;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave27() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 27;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[2][7].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 27;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave28() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 28;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[2][8].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 28;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave31() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 31;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[3][1].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 31;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave32() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 32;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[3][2].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 32;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave33() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 33;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[3][3].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 33;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave34() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 34;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[3][4].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 34;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave35() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 35;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[3][5].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 35;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave36() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 36;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[3][6].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 36;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave37() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 37;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[3][7].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 37;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave38() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 38;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[3][8].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 38;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave41() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 41;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[4][1].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 41;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave42() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 42;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[4][2].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 42;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave43() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 43;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[4][3].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 43;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave44() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 44;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[4][4].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 44;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave45() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 45;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[4][5].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 45;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave46() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 46;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[4][6].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 46;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave47() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 47;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[4][7].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 47;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave48() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 48;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[4][8].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 48;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave51() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 51;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[5][1].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 51;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave52() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 52;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[5][2].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 52;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave53() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 53;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[5][3].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 53;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave54() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 54;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[5][4].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 54;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave55() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 55;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[5][5].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 55;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave56() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 56;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[5][6].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 56;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave57() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 57;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[5][7].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 57;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave58() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 58;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[5][8].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 58;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave61() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 61;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[6][1].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 61;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave62() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 62;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[6][2].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 62;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave63() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 63;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[6][3].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 63;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave64() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 64;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[6][4].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 64;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave65() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 65;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[6][5].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 65;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave66() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 66;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[6][6].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 66;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave67() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 67;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[6][7].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 67;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave68() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 68;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[6][8].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 68;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave71() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 71;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[7][1].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 71;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave72() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 72;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[7][2].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 72;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave73() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 73;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[7][3].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 73;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave74() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 74;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[7][4].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 74;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave75() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 75;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[7][5].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 75;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave76() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 76;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[7][6].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 76;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave77() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 77;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[7][7].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 77;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave78() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 78;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[7][8].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 78;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave81() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 81;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[8][1].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 81;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave82() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 82;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[8][2].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 82;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave83() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 83;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[8][3].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 83;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave84() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 84;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[8][4].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 84;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave85() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 85;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[8][5].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 85;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave86() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 86;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[8][6].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 86;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave87() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 87;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[8][7].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 87;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void moveOrSave88() {
        if (!isHisTurn) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "NOT YOUR TURN!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if (previousePlace == 0) {
            previousePlace = 88;
            showValidMoves(previousePlace);
            return;
        }
        if (!imageViews[8][8].getImage().equals(validImage)){
            previousePlace=0;
            newPlace=0;
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    imageViews[i][j].setImage(null);
                }
            }
            refresh();
            return;
        }
        newPlace = 88;
        isHisTurn = false;
        turnTextField.setText("Rival Turn");
        imageViews[newPlace / 10][newPlace % 10].setImage(imageViews[previousePlace / 10][previousePlace % 10].getImage());
        imageViews[previousePlace / 10][previousePlace % 10].setImage(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("MOVE");
                    dos.flush();
                    dos.writeInt(previousePlace);
                    dos.flush();
                    dos.writeInt(newPlace);
                    dos.flush();
                    String answer = dis.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            textField.setText("REFRESH,./?+_*&^%$#@!~`|REFRESH!@#$%^&*REFRESH");
                            sendText();
                        }
                    });
                    if (answer.equals("WON")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You Have Wone", ButtonType.OK);
                                alert.showAndWait();
                                try {
                                    new PageLoader().load("MainPage.fxml");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    imageViews[i][j].setImage(null);
                                }
                            }
                            refresh();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void refresh() {
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                imageViews[i][j].setImage(null);
            }
        }
        whiteMap.clear();
        blackMap.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dos.writeUTF("REFRESH");
                    dos.flush();
                    isHisTurn = dis.readBoolean();
                    int counter = dis.readInt();
                    int x, y, mapPlace;
                    String type;
                    for (int i = 0; i < counter; i++) {
                        type = dis.readUTF();
                        x = dis.readInt();
                        y = dis.readInt();
                        mapPlace = (10 * x) + y;
                        whiteMap.put(mapPlace, type);
                    }
                    counter = dis.readInt();
                    for (int i = 0; i < counter; i++) {
                        type = dis.readUTF();
                        x = dis.readInt();
                        y = dis.readInt();
                        mapPlace = (10 * x) + y;
                        blackMap.put(mapPlace, type);
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (isHisTurn) {
                                turnTextField.setText("Your Turn");
                                newPlace = 0;
                                previousePlace = 0;
                            } else {
                                turnTextField.setText("Rival Turn");
                                newPlace = 0;
                                previousePlace = 0;
                            }
                            int placeNumber;
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    placeNumber = (i * 10) + j;
                                    if (whiteMap.containsKey(placeNumber)) {
                                        switch (whiteMap.get(placeNumber)) {
                                            case "ROKH": {
                                                imageViews[i][j].setImage(wRokh);
                                            }
                                            break;
                                            case "ASB": {
                                                imageViews[i][j].setImage(wHorse);
                                            }
                                            break;
                                            case "FIL": {
                                                imageViews[i][j].setImage(wFil);
                                            }
                                            break;
                                            case "SHAH": {
                                                imageViews[i][j].setImage(wKing);
                                            }
                                            break;
                                            case "VAZIR": {
                                                imageViews[i][j].setImage(wVazir);
                                            }
                                            break;
                                            case "SARBAZ": {
                                                imageViews[i][j].setImage(wSoldier);
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                            for (int i = 1; i < 9; i++) {
                                for (int j = 1; j < 9; j++) {
                                    placeNumber = (i * 10) + j;
                                    if (blackMap.containsKey(placeNumber)) {
                                        switch (blackMap.get(placeNumber)) {
                                            case "ROKH": {
                                                imageViews[i][j].setImage(bRokh);
                                            }
                                            break;
                                            case "ASB": {
                                                imageViews[i][j].setImage(bHorse);
                                            }
                                            break;
                                            case "FIL": {
                                                imageViews[i][j].setImage(bFil);
                                            }
                                            break;
                                            case "SHAH": {
                                                imageViews[i][j].setImage(bKing);
                                            }
                                            break;
                                            case "VAZIR": {
                                                imageViews[i][j].setImage(bVazir);
                                            }
                                            break;
                                            case "SARBAZ": {
                                                imageViews[i][j].setImage(bSoldier);
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void showValidMoves(int position) {
        int x = position / 10;
        int y = position % 10;
        if (isWhit) {
            String type = whiteMap.get(position);
            switch (type) {
                case "ROKH": {
                    for (int i = x + 1; i < 9; i++) {
                        if (whiteMap.containsKey((i * 10) + (y))) {
                            break;
                        } else {
                            imageViews[i][y].setImage(validImage);
                        }
                        if (blackMap.containsKey((i * 10) + (y))) {
                            break;
                        }
                    }
                    for (int i = x - 1; i > 0; i--) {
                        if (whiteMap.containsKey((i * 10) + (y))) {
                            break;
                        } else {
                            imageViews[i][y].setImage(validImage);
                        }
                        if (blackMap.containsKey((i * 10) + (y))) {
                            break;
                        }
                    }
                    for (int i = y + 1; i < 9; i++) {
                        if (whiteMap.containsKey((x * 10) + (i))) {
                            break;
                        } else {
                            imageViews[x][i].setImage(validImage);
                        }
                        if (blackMap.containsKey((10 * x) + i)) {
                            break;
                        }
                    }
                    for (int i = y - 1; i > 0; i--) {
                        if (whiteMap.containsKey((10 * x) + (i))) {
                            break;
                        } else {
                            imageViews[x][i].setImage(validImage);
                        }
                        if (blackMap.containsKey((10 * x) + i)) {
                            break;
                        }
                    }
                }
                break;
                case "ASB": {
                    if ((x + 1 < 9) && (y + 2 < 9)) {
                        if (!whiteMap.containsKey(position + 12)) {
                            imageViews[x + 1][y + 2].setImage(validImage);
                        }
                    }
                    if ((x + 1 < 9) && (y - 2 > 0)) {
                        if (!whiteMap.containsKey((10 * (x + 1)) + y - 2)) {
                            imageViews[x + 1][y - 2].setImage(validImage);
                        }
                    }
                    if ((x - 1 > 0) && (y + 2 < 9)) {
                        if (!whiteMap.containsKey((10 * (x - 1)) + y + 2)) {
                            imageViews[x - 1][y + 2].setImage(validImage);
                        }
                    }
                    if ((x - 1 > 0) && (y - 2 > 0)) {
                        if (!whiteMap.containsKey((10 * (x - 1)) + y - 2)) {
                            imageViews[x - 1][y - 2].setImage(validImage);
                        }
                    }
                    if ((x + 2 < 9) && (y + 1 < 9)) {
                        if (!whiteMap.containsKey(position + 21)) {
                            imageViews[x + 2][y + 1].setImage(validImage);
                        }
                    }
                    if ((x + 2 < 9) && (y - 1 > 0)) {
                        if (!whiteMap.containsKey((10 * (x + 2)) + y - 1)) {
                            imageViews[x + 2][y - 1].setImage(validImage);
                        }
                    }
                    if ((x - 2 > 0) && (y + 1 < 9)) {
                        if (!whiteMap.containsKey((10 * (x - 2)) + y + 1)) {
                            imageViews[x - 2][y + 1].setImage(validImage);
                        }
                    }
                    if ((x - 2 > 0) && (y - 1 > 0)) {
                        if (!whiteMap.containsKey((10 * (x - 2)) + y - 1)) {
                            imageViews[x - 2][y - 1].setImage(validImage);
                        }
                    }
                }
                break;
                case "FIL": {
                    int chagingX,changingY;
                    chagingX=x+1;
                    changingY=y+1;
                    while (chagingX <9 && changingY <9){
                        if (whiteMap.containsKey((10*chagingX)+changingY)){
                            break;
                        }
                        if (blackMap.containsKey((10*chagingX)+changingY)){
                            imageViews[chagingX][changingY].setImage(validImage);
                            break;
                        }
                        imageViews[chagingX][changingY].setImage(validImage);
                        changingY++;
                        chagingX++;
                    }
                    chagingX=x-1;
                    changingY=y-1;
                    while (chagingX > 0 && changingY > 0){
                        if (whiteMap.containsKey((10*chagingX)+changingY)){
                            break;
                        }
                        if (blackMap.containsKey((10*chagingX)+changingY)){
                            imageViews[chagingX][changingY].setImage(validImage);
                            break;
                        }
                        imageViews[chagingX][changingY].setImage(validImage);
                        chagingX--;
                        changingY--;
                    }
                    chagingX=x+1;
                    changingY=y-1;
                    while (chagingX <9 && changingY > 0){
                        if (whiteMap.containsKey((10*chagingX)+changingY)){
                            break;
                        }
                        if (blackMap.containsKey((10*chagingX)+changingY)){
                            imageViews[chagingX][changingY].setImage(validImage);
                            break;
                        }
                        imageViews[chagingX][changingY].setImage(validImage);
                        chagingX++;
                        changingY--;
                    }
                    chagingX=x-1;
                    changingY=y+1;
                    while (chagingX > 0 && changingY < 9){
                        if (whiteMap.containsKey((10*chagingX)+changingY)){
                            break;
                        }
                        if (blackMap.containsKey((10*chagingX)+changingY)){
                            imageViews[chagingX][changingY].setImage(validImage);
                            break;
                        }
                        imageViews[chagingX][changingY].setImage(validImage);
                        changingY++;
                        chagingX--;
                    }
                }
                break;
                case "SHAH": {
                    if (x - 1 > 0) {
                        if (!whiteMap.containsKey((10 * x) + y - 10)) {
                            imageViews[x - 1][y].setImage(validImage);
                        }
                        if (y + 1 < 9) {
                            if (!whiteMap.containsKey(((10 * x) + y - 9))) {
                                imageViews[x - 1][y + 1].setImage(validImage);
                            }
                        }
                        if (y - 1 > 0) {
                            if (!whiteMap.containsKey((10 * x) + y - 11)) {
                                imageViews[x - 1][y - 1].setImage(validImage);
                            }
                        }
                    }
                    if (x + 1 < 9) {
                        if (!whiteMap.containsKey((10 * x) + y + 10)) {
                            imageViews[x + 1][y].setImage(validImage);
                        }
                        if (y - 1 > 0) {
                            if (!whiteMap.containsKey(((10 * x) + y + 9))) {
                                imageViews[x + 1][y - 1].setImage(validImage);
                            }
                        }
                        if (y + 1 < 9) {
                            if (!whiteMap.containsKey((10 * x) + y + 11)) {
                                imageViews[x + 1][y + 1].setImage(validImage);
                            }
                        }
                    }
                    if (y + 1 < 9) {
                        imageViews[x][y + 1].setImage(validImage);
                    }
                    if (y - 1 > 0) {
                        imageViews[x][y - 1].setImage(validImage);
                    }
                }
                break;
                case "VAZIR": {
                    int chagingX,changingY;
                    chagingX=x+1;
                    changingY=y+1;
                    while (chagingX <9 && changingY <9){
                        if (whiteMap.containsKey((10*chagingX)+changingY)){
                            break;
                        }
                        if (blackMap.containsKey((10*chagingX)+changingY)){
                            imageViews[chagingX][changingY].setImage(validImage);
                            break;
                        }
                        imageViews[chagingX][changingY].setImage(validImage);
                        changingY++;
                        chagingX++;
                    }
                    chagingX=x-1;
                    changingY=y-1;
                    while (chagingX > 0 && changingY > 0){
                        if (whiteMap.containsKey((10*chagingX)+changingY)){
                            break;
                        }
                        if (blackMap.containsKey((10*chagingX)+changingY)){
                            imageViews[chagingX][changingY].setImage(validImage);
                            break;
                        }
                        imageViews[chagingX][changingY].setImage(validImage);
                        chagingX--;
                        changingY--;
                    }
                    chagingX=x+1;
                    changingY=y-1;
                    while (chagingX <9 && changingY > 0){
                        if (whiteMap.containsKey((10*chagingX)+changingY)){
                            break;
                        }
                        if (blackMap.containsKey((10*chagingX)+changingY)){
                            imageViews[chagingX][changingY].setImage(validImage);
                            break;
                        }
                        imageViews[chagingX][changingY].setImage(validImage);
                        chagingX++;
                        changingY--;
                    }
                    chagingX=x-1;
                    changingY=y+1;
                    while (chagingX > 0 && changingY < 9){
                        if (whiteMap.containsKey((10*chagingX)+changingY)){
                            break;
                        }
                        if (blackMap.containsKey((10*chagingX)+changingY)){
                            imageViews[chagingX][changingY].setImage(validImage);
                            break;
                        }
                        imageViews[chagingX][changingY].setImage(validImage);
                        changingY++;
                        chagingX--;
                    }
                    for (int i = x + 1; i < 9; i++) {
                        if (whiteMap.containsKey((i * 10) + (y))) {
                            break;
                        } else {
                            imageViews[i][y].setImage(validImage);
                        }
                        if (blackMap.containsKey((i * 10) + (y))) {
                            break;
                        }
                    }
                    for (int i = x - 1; i > 0; i--) {
                        if (whiteMap.containsKey((i * 10) + (y))) {
                            break;
                        } else {
                            imageViews[i][y].setImage(validImage);
                        }
                        if (blackMap.containsKey((i * 10) + (y))) {
                            break;
                        }
                    }
                    for (int i = y + 1; i < 9; i++) {
                        if (whiteMap.containsKey((x * 10) + (i))) {
                            break;
                        } else {
                            imageViews[x][i].setImage(validImage);
                        }
                        if (blackMap.containsKey((10 * x) + i)) {
                            break;
                        }
                    }
                    for (int i = y - 1; i > 0; i--) {
                        if (whiteMap.containsKey((10 * x) + (i))) {
                            break;
                        } else {
                            imageViews[x][i].setImage(validImage);
                        }
                        if (blackMap.containsKey((10 * x) + i)) {
                            break;
                        }
                    }
                }
                break;
                case "SARBAZ": {
                    if (x == 2) {
                        if (whiteMap.containsKey((30+y)) || blackMap.containsKey(30+y)){
                            //nothing to do But it was easier to write in this type :)
                        }else {
                            imageViews[3][y].setImage(validImage);
                            if (whiteMap.containsKey((40+y)) || blackMap.containsKey(40+y)){
                                //nothing to do But it was easier to write in this type :)
                            }else {
                                imageViews[4][y].setImage(validImage);
                            }
                        }
                        if (y<8){
                            if (blackMap.containsKey((10*x)+y+11)){
                                imageViews[x+1][y+1].setImage(validImage);
                            }
                        }
                        if (y > 1){
                            if (blackMap.containsKey((10*x)+y+9)){
                                imageViews[x+1][y-1].setImage(validImage);
                            }
                        }
                    }
                    else if (x < 8){
                        if (whiteMap.containsKey((x*10)+y+10) || blackMap.containsKey((10*x)+y+10)){
                            //nothing to do But it was easier to write in this type :)
                        }else {
                            imageViews[x+1][y].setImage(validImage);
                        }
                        if (y<8){
                            if (blackMap.containsKey((10*x)+y+11)){
                                imageViews[x+1][y+1].setImage(validImage);
                            }
                        }
                        if (y > 1){
                            if (blackMap.containsKey((10*x)+y+9)){
                                imageViews[x+1][y-1].setImage(validImage);
                            }
                        }
                    }
                }
                break;
            }
        } else {
            String type = blackMap.get(position);
            switch (type) {
                case "ROKH": {
                    for (int i = x + 1; i < 9; i++) {
                        if (blackMap.containsKey((i * 10) + (y))) {
                            break;
                        } else {
                            imageViews[i][y].setImage(validImage);
                        }
                        if (whiteMap.containsKey((i * 10) + (y))) {
                            break;
                        }
                    }
                    for (int i = x - 1; i > 0; i--) {
                        if (blackMap.containsKey((i * 10) + (y))) {
                            break;
                        } else {
                            imageViews[i][y].setImage(validImage);
                        }
                        if (whiteMap.containsKey((i * 10) + (y))) {
                            break;
                        }
                    }
                    for (int i = y + 1; i < 9; i++) {
                        if (blackMap.containsKey((x * 10) + (i))) {
                            break;
                        } else {
                            imageViews[x][i].setImage(validImage);
                        }
                        if (whiteMap.containsKey((10 * x) + i)) {
                            break;
                        }
                    }
                    for (int i = y - 1; i > 0; i--) {
                        if (blackMap.containsKey((10 * x) + (i))) {
                            break;
                        } else {
                            imageViews[x][i].setImage(validImage);
                        }
                        if (whiteMap.containsKey((10 * x) + i)) {
                            break;
                        }
                    }
                }
                break;
                case "ASB": {
                    if ((x + 1 < 9) && (y + 2 < 9)) {
                        if (!blackMap.containsKey(position + 12)) {
                            imageViews[x + 1][y + 2].setImage(validImage);
                        }
                    }
                    if ((x + 1 < 9) && (y - 2 > 0)) {
                        if (!blackMap.containsKey((10 * (x + 1)) + y - 2)) {
                            imageViews[x + 1][y - 2].setImage(validImage);
                        }
                    }
                    if ((x - 1 > 0) && (y + 2 < 9)) {
                        if (!blackMap.containsKey((10 * (x - 1)) + y + 2)) {
                            imageViews[x - 1][y + 2].setImage(validImage);
                        }
                    }
                    if ((x - 1 > 0) && (y - 2 > 0)) {
                        if (!blackMap.containsKey((10 * (x - 1)) + y - 2)) {
                            imageViews[x - 1][y - 2].setImage(validImage);
                        }
                    }
                    if ((x + 2 < 9) && (y + 1 < 9)) {
                        if (!blackMap.containsKey(position + 21)) {
                            imageViews[x + 2][y + 1].setImage(validImage);
                        }
                    }
                    if ((x + 2 < 9) && (y - 1 > 0)) {
                        if (!blackMap.containsKey((10 * (x + 2)) + y - 1)) {
                            imageViews[x + 2][y - 1].setImage(validImage);
                        }
                    }
                    if ((x - 2 > 0) && (y + 1 < 9)) {
                        if (!blackMap.containsKey((10 * (x - 2)) + y + 1)) {
                            imageViews[x - 2][y + 1].setImage(validImage);
                        }
                    }
                    if ((x - 2 > 0) && (y - 1 > 0)) {
                        if (!blackMap.containsKey((10 * (x - 2)) + y - 1)) {
                            imageViews[x - 2][y - 1].setImage(validImage);
                        }
                    }
                }
                break;
                case "FIL": {
                    int chagingX,changingY;
                    chagingX=x+1;
                    changingY=y+1;
                    while (chagingX <9 && changingY <9){
                        if (blackMap.containsKey((10*chagingX)+changingY)){
                            break;
                        }
                        if (whiteMap.containsKey((10*chagingX)+changingY)){
                            imageViews[chagingX][changingY].setImage(validImage);
                            break;
                        }
                        imageViews[chagingX][changingY].setImage(validImage);
                        changingY++;
                        chagingX++;
                    }
                    chagingX=x-1;
                    changingY=y-1;
                    while (chagingX > 0 && changingY > 0){
                        if (blackMap.containsKey((10*chagingX)+changingY)){
                            break;
                        }
                        if (whiteMap.containsKey((10*chagingX)+changingY)){
                            imageViews[chagingX][changingY].setImage(validImage);
                            break;
                        }
                        imageViews[chagingX][changingY].setImage(validImage);
                        chagingX--;
                        changingY--;
                    }
                    chagingX=x+1;
                    changingY=y-1;
                    while (chagingX <9 && changingY > 0){
                        if (blackMap.containsKey((10*chagingX)+changingY)){
                            break;
                        }
                        if (whiteMap.containsKey((10*chagingX)+changingY)){
                            imageViews[chagingX][changingY].setImage(validImage);
                            break;
                        }
                        imageViews[chagingX][changingY].setImage(validImage);
                        chagingX++;
                        changingY--;
                    }
                    chagingX=x-1;
                    changingY=y+1;
                    while (chagingX > 0 && changingY < 9){
                        if (blackMap.containsKey((10*chagingX)+changingY)){
                            break;
                        }
                        if (whiteMap.containsKey((10*chagingX)+changingY)){
                            imageViews[chagingX][changingY].setImage(validImage);
                            break;
                        }
                        imageViews[chagingX][changingY].setImage(validImage);
                        changingY++;
                        chagingX--;
                    }
                }
                break;
                case "SHAH": {
                    if (x - 1 > 0) {
                        if (!blackMap.containsKey((10 * x) + y - 10)) {
                            imageViews[x - 1][y].setImage(validImage);
                        }
                        if (y + 1 < 9) {
                            if (!blackMap.containsKey(((10 * x) + y - 9))) {
                                imageViews[x - 1][y + 1].setImage(validImage);
                            }
                        }
                        if (y - 1 > 0) {
                            if (!blackMap.containsKey((10 * x) + y - 11)) {
                                imageViews[x - 1][y - 1].setImage(validImage);
                            }
                        }
                    }
                    if (x + 1 < 9) {
                        if (!blackMap.containsKey((10 * x) + y + 10)) {
                            imageViews[x + 1][y].setImage(validImage);
                        }
                        if (y - 1 > 0) {
                            if (!blackMap.containsKey(((10 * x) + y + 9))) {
                                imageViews[x + 1][y - 1].setImage(validImage);
                            }
                        }
                        if (y + 1 < 9) {
                            if (!blackMap.containsKey((10 * x) + y + 11)) {
                                imageViews[x + 1][y + 1].setImage(validImage);
                            }
                        }
                    }
                    if (y + 1 < 9) {
                        imageViews[x][y + 1].setImage(validImage);
                    }
                    if (y - 1 > 0) {
                        imageViews[x][y - 1].setImage(validImage);
                    }
                }
                break;
                case "VAZIR": {
                    int chagingX,changingY;
                    chagingX=x+1;
                    changingY=y+1;
                    while (chagingX <9 && changingY <9){
                        if (blackMap.containsKey((10*chagingX)+changingY)){
                            break;
                        }
                        if (whiteMap.containsKey((10*chagingX)+changingY)){
                            imageViews[chagingX][changingY].setImage(validImage);
                            break;
                        }
                        imageViews[chagingX][changingY].setImage(validImage);
                        changingY++;
                        chagingX++;
                    }
                    chagingX=x-1;
                    changingY=y-1;
                    while (chagingX > 0 && changingY > 0){
                        if (blackMap.containsKey((10*chagingX)+changingY)){
                            break;
                        }
                        if (whiteMap.containsKey((10*chagingX)+changingY)){
                            imageViews[chagingX][changingY].setImage(validImage);
                            break;
                        }
                        imageViews[chagingX][changingY].setImage(validImage);
                        chagingX--;
                        changingY--;
                    }
                    chagingX=x+1;
                    changingY=y-1;
                    while (chagingX <9 && changingY > 0){
                        if (blackMap.containsKey((10*chagingX)+changingY)){
                            break;
                        }
                        if (whiteMap.containsKey((10*chagingX)+changingY)){
                            imageViews[chagingX][changingY].setImage(validImage);
                            break;
                        }
                        imageViews[chagingX][changingY].setImage(validImage);
                        chagingX++;
                        changingY--;
                    }
                    chagingX=x-1;
                    changingY=y+1;
                    while (chagingX > 0 && changingY < 9){
                        if (blackMap.containsKey((10*chagingX)+changingY)){
                            break;
                        }
                        if (whiteMap.containsKey((10*chagingX)+changingY)){
                            imageViews[chagingX][changingY].setImage(validImage);
                            break;
                        }
                        imageViews[chagingX][changingY].setImage(validImage);
                        changingY++;
                        chagingX--;
                    }
                    for (int i = x + 1; i < 9; i++) {
                        if (blackMap.containsKey((i * 10) + (y))) {
                            break;
                        } else {
                            imageViews[i][y].setImage(validImage);
                        }
                        if (whiteMap.containsKey((i * 10) + (y))) {
                            break;
                        }
                    }
                    for (int i = x - 1; i > 0; i--) {
                        if (blackMap.containsKey((i * 10) + (y))) {
                            break;
                        } else {
                            imageViews[i][y].setImage(validImage);
                        }
                        if (whiteMap.containsKey((i * 10) + (y))) {
                            break;
                        }
                    }
                    for (int i = y + 1; i < 9; i++) {
                        if (blackMap.containsKey((x * 10) + (i))) {
                            break;
                        } else {
                            imageViews[x][i].setImage(validImage);
                        }
                        if (whiteMap.containsKey((10 * x) + i)) {
                            break;
                        }
                    }
                    for (int i = y - 1; i > 0; i--) {
                        if (blackMap.containsKey((10 * x) + (i))) {
                            break;
                        } else {
                            imageViews[x][i].setImage(validImage);
                        }
                        if (whiteMap.containsKey((10 * x) + i)) {
                            break;
                        }
                    }
                }
                break;
                case "SARBAZ": {
                    if (x == 7) {
                        if (whiteMap.containsKey(60+y) || blackMap.containsKey(60+y)){
                            //nothing to do But it was easier to write in this type :)
                        }else {
                            imageViews[6][y].setImage(validImage);
                            if (whiteMap.containsKey(50+y) || blackMap.containsKey(50+y)){
                                //nothing to do But it was easier to write in this type :)
                            }else {
                                imageViews[5][y].setImage(validImage);
                            }
                        }
                        if (y>1){
                            if (blackMap.containsKey((10*(x-1))+y-1)){
                                imageViews[x-1][y-1].setImage(validImage);
                            }
                        }
                        if (y < 8){
                            if (blackMap.containsKey((10*(x-1))+y+1)){
                                imageViews[x-1][y+1].setImage(validImage);
                            }
                        }
                    }
                    else if (x > 1){
                        if (whiteMap.containsKey(((x-1)*10)+y) || blackMap.containsKey((10*(x-1))+y)){
                            //nothing to do But it was easier to write in this type :)
                        }else {
                            imageViews[x-1][y].setImage(validImage);
                        }
                        if (y>1){
                            if (blackMap.containsKey((10*x)+y-11)){
                                imageViews[x-1][y-1].setImage(validImage);
                            }
                        }
                        if (y < 8){
                            if (blackMap.containsKey((10*x)+y-9)){
                                imageViews[x-1][y+1].setImage(validImage);
                            }
                        }
                    }
                }
                break;
            }
        }
    }
}