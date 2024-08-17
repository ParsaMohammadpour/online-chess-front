import javafx.application.Application;

import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {

    public static String ip;

    @Override
    public void start(Stage primaryStage) throws Exception{
        PageLoader.initStage(primaryStage);
        new PageLoader().load("LoginPage.fxml");
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        ip=scanner.nextLine();
        launch(args);
    }
}