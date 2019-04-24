package sample;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;


public class ProgramWindow {
    /* Form elements */
    private static Stage mainStage = null;
    private static BorderPane borderPane = null;
    private static TextField pInput = null;
    private static Label qLabel = null;
    private static TextField qInput = null;
    private static TextField aInput = null;
    private static Label privateKeyLabel = null;
    private static TextField privateKeyInput = null;
    private static TextField publicKeyInput = null;
    private static TextField signEInput = null;
    private static TextField signYInput = null;
    private static TextArea textarea = null;
    private static Button actionButton = null;

    /* Algorithm variables */
    private static SchnorrAlgorithm algorithm = null;

    ProgramWindow(Stage mainStage){
        this.mainStage = mainStage;
        this.mainStage.setTitle("Schnorr's signature");
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
        this.setSignMessage();
    }

    private void setupMenu(){
        MenuBar menuBar = new MenuBar();
        Menu message = new Menu("Message");

        MenuItem send = new MenuItem("Send");
        send.setOnAction(event -> this.setSignMessage());

        MenuItem receive = new MenuItem("Receive");
        receive.setOnAction(event -> this.setVerifySignature());

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
        generateButton.setOnAction(event -> this.generateVariables());

        this.qLabel = new Label("q: ");
        inputsGrid.add(this.qLabel, 1,2);
        GridPane.setHalignment(qLabel, HPos.RIGHT);

        this.qInput = new TextField();
        inputsGrid.add(this.qInput, 2,2);

        Label pLabel = new Label("p: ");
        inputsGrid.add(pLabel, 3,2);
        GridPane.setHalignment(pLabel, HPos.RIGHT);

        this.pInput = new TextField();
        inputsGrid.add(this.pInput, 4,2);

        Label aLabel = new Label("a: ");
        inputsGrid.add(aLabel, 5,2);
        GridPane.setHalignment(aLabel, HPos.RIGHT);

        this.aInput = new TextField();
        inputsGrid.add(this.aInput, 6,2);

        this.privateKeyLabel = new Label(" Private key: ");
        inputsGrid.add(this.privateKeyLabel, 3, 3);

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

        Label signature = new Label("Signature: ");
        inputsGrid.add(signature, 2,6);
        GridPane.setHalignment(signature, HPos.CENTER);

        Label signELabel = new Label(" e: ");
        inputsGrid.add(signELabel, 3,6);
        GridPane.setHalignment(signELabel, HPos.RIGHT);

        this.signEInput = new TextField();
        inputsGrid.add(this.signEInput,4,6);

        Label signYLabel = new Label(" y: ");
        inputsGrid.add(signYLabel, 5,6);
        GridPane.setHalignment(signYLabel, HPos.RIGHT);

        this.signYInput = new TextField();
        inputsGrid.add(this.signYInput,6,6);

        this.actionButton = new Button("Sign");
        inputsGrid.add(actionButton, 6, 7);
        GridPane.setHalignment(this.actionButton, HPos.RIGHT);

        this.borderPane.setCenter(inputsGrid);
    }

    private void generateVariables(){
        this.algorithm.init();
        BigInteger[] constraints = this.algorithm.getConstrains();
        BigInteger[] keys = this.algorithm.getKeys();
        BigInteger p = constraints[0];
        BigInteger q = constraints[1];
        BigInteger a = constraints[2];
        BigInteger s = keys[0];
        BigInteger v = keys[1];

        this.pInput.setText(p.toString());
        this.qInput.setText(q.toString());
        this.aInput.setText(a.toString());
        this.privateKeyInput.setText(s.toString());
        this.publicKeyInput.setText(v.toString());
    }

    private void setSignMessage(){
        this.actionButton.setText("Sign");
        this.qInput.setVisible(true);
        this.qLabel.setVisible(true);
        this.privateKeyLabel.setVisible(true);
        this.privateKeyInput.setVisible(true);
        this.actionButton.setOnAction(event -> this.signMessage());
    }

    private void setVerifySignature(){
        this.actionButton.setText("Verify Signature");
        this.qInput.setVisible(false);
        this.qLabel.setVisible(false);
        this.privateKeyLabel.setVisible(false);
        this.privateKeyInput.setVisible(false);
        this.actionButton.setOnAction(event -> this.signMessage());
    }

    private void signMessage(){
        String message = this.textarea.getText();
        BigInteger q = new BigInteger(this.qInput.getText());
        BigInteger a = new BigInteger(this.aInput.getText());
        BigInteger p = new BigInteger(this.pInput.getText());
        BigInteger s = new BigInteger(this.privateKeyInput.getText());

        BigInteger[] signature = algorithm.getSign(message, q, a, p, s);
        this.signEInput.setText(signature[0].toString());
        this.signYInput.setText(signature[1].toString());
    }

    private void saveToFile(BigInteger[] content){
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(mainStage);

        if(file != null){
            try {
                FileWriter fileWriter = null;

                fileWriter = new FileWriter(file);
                for(BigInteger element: content){
                    fileWriter.write(element.toString());
                    fileWriter.write(System.getProperty( "line.separator" ));
                }
                fileWriter.close();
            } catch (IOException ex) {
            }
        }
    }
}
