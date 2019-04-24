package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class ProgramWindow {
    private static BorderPane borderPane = null;

    ProgramWindow(Stage mainStage){
        mainStage.setTitle("DES program");
        Group root = new Group();
        Scene scene = new Scene(root, 1200, 250);
        this.borderPane = new BorderPane();
        this.setupMenu();
        root.getChildren().add(this.borderPane);
        mainStage.setScene(scene);
        mainStage.show();
    }

    private void setupMenu(){
        MenuBar menuBar = new MenuBar();
        Menu message = new Menu("Message");
        MenuItem send = new MenuItem("Send");
        MenuItem receive = new MenuItem("Receive");

        message.getItems().addAll(send, receive);
        menuBar.getMenus().addAll(message);
        borderPane.setTop(menuBar);
    }
}
