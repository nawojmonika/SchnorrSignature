package sample;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;


public class ProgramWindow {
    /* Form elements */
    private static BorderPane borderPane = null;
    private static TextField pInput = null;
    private static TextField qInput = null;
    private static TextField aInput = null;
    private static TextField privateKeyInput = null;
    private static TextField publicKeyInput = null;

    private static TextArea textarea = null;
    private static Button actionButton = null;

    /* Algorithm variables */
    private static SchnorrAlgorithm algorithm = null;
    private BigInteger[] constraints = null;
    private BigInteger[] keys = null;

    ProgramWindow(Stage mainStage){
        mainStage.setTitle("Schnorr's signature");
        Group root = new Group();
        Scene scene = new Scene(root, 800, 400);
        this.borderPane = new BorderPane();
        this.borderPane.setPrefSize(800, 400);
        this.setupMenu();
        this.setupInputs();
        root.getChildren().add(this.borderPane);
        mainStage.setScene(scene);
        mainStage.show();
        this.algorithm = new SchnorrAlgorithm();
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
        inputsGrid.setPrefSize(800, 400);
        inputsGrid.setVgap(10);
        inputsGrid.setPadding(new  Insets(20));

        Button generateButton = new Button("Generate values");
        inputsGrid.add(generateButton,6,1);
        GridPane.setHalignment(generateButton, HPos.RIGHT);

        Label pLabel = new Label("p: ");
        inputsGrid.add(pLabel, 1,2);
        GridPane.setHalignment(pLabel, HPos.RIGHT);

        this.pInput = new TextField();
        inputsGrid.add(this.pInput, 2,2);

        Label qLabel = new Label("q: ");
        inputsGrid.add(qLabel, 3,2);
        GridPane.setHalignment(qLabel, HPos.RIGHT);

        this.qInput = new TextField();
        inputsGrid.add(this.qInput, 4,2);

        Label aLabel = new Label("a: ");
        inputsGrid.add(aLabel, 5,2);
        GridPane.setHalignment(aLabel, HPos.RIGHT);

        this.aInput = new TextField();
        inputsGrid.add(this.aInput, 6,2);

        Label privateKeyLabel = new Label(" Private key: ");
        inputsGrid.add(privateKeyLabel, 3, 3);

        this.privateKeyInput = new TextField();
        inputsGrid.add(this.privateKeyInput, 4, 3);

        Label publicKeyLabel = new Label(" Public key: ");
        inputsGrid.add(publicKeyLabel, 5, 3);

        this.publicKeyInput = new TextField();
        inputsGrid.add(this.publicKeyInput, 6, 3);

        Label message = new Label("Message:");
        inputsGrid.add(message, 2, 4);
        GridPane.setHalignment(message, HPos.CENTER);

        this.textarea = new TextArea();
        this.textarea.setPrefSize(400, 100);
        inputsGrid.add(textarea,3, 4, 4, 1);

        this.actionButton = new Button("Sign");
        inputsGrid.add(actionButton, 6, 6);
        GridPane.setHalignment(this.actionButton, HPos.RIGHT);

//        inputsGrid.setGridLinesVisible(true);

        this.borderPane.setCenter(inputsGrid);
    }

    private void setSignMessage(){
        this.algorithm.init();
    }

    private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
        }
    }
}
