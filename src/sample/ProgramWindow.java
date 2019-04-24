package sample;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class ProgramWindow {
    private static BorderPane borderPane = null;
    private static TextArea textarea = null;
    private static Button actionButton = null;

    ProgramWindow(Stage mainStage){
        mainStage.setTitle("Schnorr's signature");
        Group root = new Group();
        Scene scene = new Scene(root, 700, 400);
        this.borderPane = new BorderPane();
        this.borderPane.setPrefSize(700, 400);
        this.setupMenu();
        this.setupInputs();
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

        Menu file = new Menu("File");
        MenuItem readMessage = new MenuItem("Read message");
        MenuItem importConfig = new MenuItem("Import config");
        MenuItem exportConfig = new MenuItem("Export config");
        MenuItem readSign = new MenuItem("Read signature");

        file.getItems().addAll(readMessage, importConfig, exportConfig, readSign);

        menuBar.getMenus().addAll(file, message);
        this.borderPane.setTop(menuBar);
    }

    private void setupInputs(){
        GridPane inputsGrid = new GridPane();
        inputsGrid.setPrefSize(700, 400);
        inputsGrid.setPadding(new Insets(50,150,50,150));
        inputsGrid.setVgap(10);

        this.textarea = new TextArea();
        this.textarea.setPrefSize(400, 150);
        inputsGrid.add(textarea,1, 1);

        this.actionButton = new Button("Sign");
        inputsGrid.add(actionButton, 1, 2);
        GridPane.setHalignment(this.actionButton, HPos.RIGHT);

        this.borderPane.setCenter(inputsGrid);
    }
}
