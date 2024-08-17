import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class AutherController {

    @FXML
    Button backButton;

    @FXML
    TextArea textArea;

    public void initialize(){
        textArea.setText("Auther : PARSA Mohammadpour\nShomare daneshjoei : 98243050\n" +
                "Tozih khasi nist faghat inke vaghti register \nmikone , oon user va pass baraye dastresi sari , dar yek file\n" +
                "dar smt client save mishe va seri bad ke\n dobare bekhad vared she (Login) , ghab vared kardan\n" +
                "user va pass , dar field ha neshto shode");
    }


    public void back(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("MainPage.fxml");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
