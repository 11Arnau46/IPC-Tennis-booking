/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Application.Controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author arnau1146
 */
public class registroController implements Initializable {
    
    @FXML
    private Label incorrectPass;
    @FXML
    private ComboBox<String> imagesComboBox;
    @FXML
    private TextField userTextField;
    @FXML
    private PasswordField passPasswordField;
    @FXML
    private PasswordField pass2PasswordField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField tarjetaTextField;
    @FXML
    private TextField csvTextField;
    @FXML
    private Button buttonRegistrar1;
    private ImageView avatarImageView;
    
    ArrayList<String> misImagenes;
    private ObservableList<String> datos = null;
    
    
    @FXML
    private Label errorPasswordDifferent;
    @FXML
    private Label errorIncorrectName;
    @FXML
    private Label errorIncorrectNumber;
    @FXML
    private Label errorSurname;
    @FXML
    private Label errorTarjeta;
    
    
    private BooleanProperty validPassword;
    private BooleanProperty matchPassword;
    private BooleanProperty validName;
    private BooleanProperty validSurname;
    private BooleanProperty validUsername;
    private BooleanProperty validPhone;
    private BooleanProperty validCardNumber;
    private BooleanProperty validCardCsv;
    private BooleanProperty validHiddenPassword;
    private BooleanProperty matchPassword2;

    @FXML
    private Label errorUsuarioRepetido;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox contentContainer;
    @FXML
    private Button uploadImageButton;
    @FXML
    private Button buttonBack;
    @FXML
    private TextField hiddenTextField;
    @FXML
    private TextField hiddenTextField2;
    private boolean pulsadoOK = false;
    private boolean pulsadoOK2 = false;
    @FXML
    private ImageView imageEye;
    @FXML
    private ImageView imageEye2;
    @FXML
    private Button showPassword;
    @FXML
    private Circle circleAvatar;
    
    
    class ImagenTabCell extends ListCell<String> {
        private ImageView view = new ImageView();
        private Image imagen;

        @Override
        protected void updateItem(String t, boolean bln) {
            super.updateItem(t, bln);
            
            if (t == null || bln) {
            setText(null);
            setGraphic(null);
            } else {
            imagen = new Image(t, 40, 40, true, true);
            Circle circle = new Circle(40 / 2); // Set the desired radius of the circle
            circle.setFill(new ImagePattern(imagen));
            setGraphic(circle);
            }
            onSelectImageClicked2();
        }
        private void onSelectImageClicked2() {
            String selectedImage = imagesComboBox.getSelectionModel().getSelectedItem();
            if (selectedImage != null) {
                ImagePattern imagePattern = new ImagePattern(new Image(selectedImage));
                circleAvatar.setFill(imagePattern);
            }
        }
        
    }
       
    @FXML
    private void onSelectImageClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"),
            new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            ImagePattern image = new ImagePattern(new Image(selectedFile.toURI().toString()));
            circleAvatar.setFill(image);
            imagesComboBox.getSelectionModel().clearSelection();
        }
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //CODIGO CAMBIO DE CAMPO CON ENTER (TAB LO HACE AUTOMATICAMENTE)
        userTextField.setOnKeyPressed(event -> {
            if ((event.getCode() == KeyCode.ENTER)) {
                passPasswordField.requestFocus();
            }
        });
        hiddenTextField.setOnKeyPressed(event -> {
            if ((event.getCode() == KeyCode.ENTER)) {
                pass2PasswordField.requestFocus();
            }
        });
        passPasswordField.setOnKeyPressed(event -> {
            if ((event.getCode() == KeyCode.ENTER)) {
                pass2PasswordField.requestFocus();
            }
        });
        hiddenTextField2.setOnKeyPressed(event -> {
            if ((event.getCode() == KeyCode.ENTER)) {
                nameTextField.requestFocus();
            }
        });
        pass2PasswordField.setOnKeyPressed(event -> {
            if ((event.getCode() == KeyCode.ENTER)) {
                nameTextField.requestFocus();
            }
        });
        nameTextField.setOnKeyPressed(event -> {
            if ((event.getCode() == KeyCode.ENTER)) {
                surnameTextField.requestFocus();
            }
        });
        surnameTextField.setOnKeyPressed(event -> {
            if ((event.getCode() == KeyCode.ENTER)) {
                phoneTextField.requestFocus();
            }
        });
        phoneTextField.setOnKeyPressed(event -> {
            if ((event.getCode() == KeyCode.ENTER)) {
                tarjetaTextField.requestFocus();
            }
        });
        tarjetaTextField.setOnKeyPressed(event -> {
            if ((event.getCode() == KeyCode.ENTER)) {
                csvTextField.requestFocus();
            }
        });
        csvTextField.setOnKeyPressed(event -> {
            if ((event.getCode() == KeyCode.ENTER)) {
                buttonRegistrar1.requestFocus();
                buttonRegistrar1.fire();
                event.consume();
            }
        });
        
        // IMAGENES PERFIL
        ImagePattern defaultImagePattern = new ImagePattern(new Image("/Iconos/avatars/default.png"));
        circleAvatar.setFill(defaultImagePattern);
                
        misImagenes = new ArrayList<>();
        misImagenes.add("/Iconos/avatars/default.png");
        misImagenes.add("/Iconos/avatars/men.PNG");
        misImagenes.add("/Iconos/avatars/men2.PNG");
        misImagenes.add("/Iconos/avatars/men3.PNG");
        misImagenes.add("/Iconos/avatars/men4.PNG");
        misImagenes.add("/Iconos/avatars/men5.PNG");
        misImagenes.add("/Iconos/avatars/woman.PNG");
        misImagenes.add("/Iconos/avatars/woman2.PNG");
        misImagenes.add("/Iconos/avatars/woman3.PNG");
        misImagenes.add("/Iconos/avatars/woman4.PNG");
        misImagenes.add("/Iconos/avatars/woman5.PNG");
        misImagenes.add("/Iconos/avatars/woman6.PNG");
        
        datos = FXCollections.observableList(misImagenes);
        imagesComboBox.setItems(datos);
        imagesComboBox.setCellFactory((c) -> new ImagenTabCell());
        imagesComboBox.setButtonCell(new ImagenTabCell());
        
        imagesComboBox.getSelectionModel().selectFirst();
        /*
        ERRORES
        */
        passPasswordField.textProperty().addListener(new ChangeListener<String>() {
            
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Modificar hiddenTextField y pass2PasswordField
                hiddenTextField2.setText("");
                pass2PasswordField.setText("");
                // Hacer que el foco vuelva a estos campos
                passPasswordField.requestFocus();
                matchPassword.setValue(Boolean.FALSE);
            }
        });

        hiddenTextField.textProperty().addListener(new ChangeListener<String>() {
            
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Modificar pass2PasswordField
                pass2PasswordField.setText("");
                hiddenTextField2.setText("");
                // Hacer que el foco vuelva a este campo
                hiddenTextField.requestFocus();
                matchPassword.setValue(Boolean.FALSE);
            }
        });
        //ERROR contraseña
        validPassword = new SimpleBooleanProperty();
        
        validPassword.setValue(Boolean.FALSE);
        
        passPasswordField.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                checkEditPassword();
            }
        });
        validHiddenPassword = new SimpleBooleanProperty();
        
        validHiddenPassword.setValue(Boolean.FALSE);
        
        hiddenTextField.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                checkEditPassword2();
            }
        });
        
        
        //ERROR contraseña no coincide
        matchPassword = new SimpleBooleanProperty();
        
        matchPassword.setValue(Boolean.FALSE);
        
        pass2PasswordField.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                comparePassword();
            }
        });
        matchPassword2 = new SimpleBooleanProperty();
        
        matchPassword2.setValue(Boolean.FALSE);
        
        hiddenTextField2.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                comparePassword2();
            }
        });
        
        //ERROR NOMBRE
        validName = new SimpleBooleanProperty();
        
        validName.setValue(Boolean.FALSE);
        
        nameTextField.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                checkName();
            }
        });
        
        //ERROR APELLIDO
        validSurname = new SimpleBooleanProperty();
        
        validSurname.setValue(Boolean.FALSE);
        
        surnameTextField.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                checkSurname();
            }
        });
        
        //ERROR NÚMERO TELEFONO
        
        validPhone = new SimpleBooleanProperty();
        
        validPhone.setValue(Boolean.FALSE);
        
        phoneTextField.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                checkPhone();
            }
        });
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            if (change.getControlNewText().length() <= 9) {
                return change;
            }
            return null; // Reject the change if it exceeds the maximum number of characters
        });
        phoneTextField.setTextFormatter(textFormatter);
        
         //ERROR NÚMERO TARJETA
        
        validCardNumber = new SimpleBooleanProperty();
        
        validCardNumber.setValue(Boolean.FALSE);
        
        tarjetaTextField.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                checkCardNumber();
            }
        });
        TextFormatter<String> textFormatter2 = new TextFormatter<>(change -> {
            if (change.getControlNewText().length() <= 16) {
                return change;
            }
            return null; // Reject the change if it exceeds the maximum number of characters
        });
        tarjetaTextField.setTextFormatter(textFormatter2);
        
        // ERROR NÚMERO CSV
        validCardCsv = new SimpleBooleanProperty();
        
        validCardCsv.setValue(Boolean.FALSE);
        
        csvTextField.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                checkCSV();
            }
        });
        TextFormatter<String> textFormatter3 = new TextFormatter<>(change -> {
            if (change.getControlNewText().length() <= 3) {
                return change;
            }
            return null; // Reject the change if it exceeds the maximum number of characters
        });
        csvTextField.setTextFormatter(textFormatter3);
        
        //ERROR USUARIO (Text Formatter que no permita espacios)
        TextFormatter<String> textFormatter4 = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[^\\s]{0,10}")) {
            return change; // Accept the change if it matches the pattern
            }
            return null; // Reject the change if it exceeds 8 characters or contains spaces
        });

        userTextField.setTextFormatter(textFormatter4);
        // Comprobar que no haya ya algún usuario con ese nombre en la base de datos
        validUsername = new SimpleBooleanProperty();
        
        validUsername.setValue(Boolean.FALSE);
        
        userTextField.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ try {
                //focus lost.
                checkUserTaken();
                } catch (ClubDAOException ex) {
                    Logger.getLogger(registroController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(registroController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        BooleanBinding password = Bindings.createBooleanBinding( () ->
                passPasswordField.getText().isEmpty(),
                passPasswordField.textProperty()
        );
        pass2PasswordField.disableProperty().bind(password);
        
        
        
        BooleanBinding validFields = Bindings.and(validName, validPassword).and(matchPassword).and(validSurname).and(validPhone).and(validUsername);

        buttonRegistrar1.disableProperty().bind(Bindings.not(validFields));
        
        
    }
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    // CAMBIO VENTANA A INICIO
    @FXML
    private void switchToInicio(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Application/FXML/inicioSesionEscena.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    //ERRORES
    private void checkEditPassword(){
        if(passPasswordField.textProperty().getValueSafe().length()<6){
            //Incorrect password
            manageError(incorrectPass, passPasswordField, validPassword);
        } else{
            manageCorrect(incorrectPass, passPasswordField, validPassword);
    
        }if (!pass2PasswordField.getText().equals("")){
            comparePassword();
            
        }
    }
    private void checkEditPassword2(){
        if(hiddenTextField.textProperty().getValueSafe().length()<6){
            //Incorrect password
            manageError(incorrectPass, hiddenTextField, validPassword);
        } else{
            manageCorrect(incorrectPass, hiddenTextField, validPassword);
    
        }if (!hiddenTextField.getText().equals("")){
            comparePassword();
            
        }
    }
    private void comparePassword(){
     if(!pass2PasswordField.textProperty().getValueSafe().equals(passPasswordField.textProperty().getValueSafe()))
         //Incorrect password
         manageError(errorPasswordDifferent, pass2PasswordField, matchPassword);
     else
         manageCorrect(errorPasswordDifferent, pass2PasswordField, matchPassword);
    }
    private void comparePassword2(){
     if(!hiddenTextField.textProperty().getValueSafe().equals(hiddenTextField2.textProperty().getValueSafe()))
         //Incorrect password
         manageError(errorPasswordDifferent, hiddenTextField2, matchPassword);
     else
         manageCorrect(errorPasswordDifferent, hiddenTextField2, matchPassword);
    }
    private void checkName(){
     if(nameTextField.textProperty().getValueSafe().matches(".*\\d.*"))
         //Incorrect name
         manageError(errorIncorrectName, nameTextField, validName);
     else
         manageCorrect(errorIncorrectName, nameTextField, validName);
    
    }
    private void checkSurname(){
     if(surnameTextField.textProperty().getValueSafe().matches(".*\\d.*"))
         //Incorrect surname
         manageError(errorSurname, surnameTextField, validSurname);
     else
         manageCorrect(errorSurname, surnameTextField, validSurname);
    
    }
    private void checkPhone(){
     if(!(phoneTextField.textProperty().getValueSafe().matches("\\d+") && phoneTextField.textProperty().getValueSafe().length()== 9))
         //Incorrect phone
         manageError(errorIncorrectNumber, phoneTextField, validPhone);
     else
         manageCorrect(errorIncorrectNumber, phoneTextField, validPhone);
    
    }
    
    private void checkCardNumber(){
        if(!(tarjetaTextField.textProperty().getValueSafe().matches("\\d+") && tarjetaTextField.textProperty().getValueSafe().length()== 16))
         //Incorrect phone
         manageError(errorTarjeta, tarjetaTextField, validCardNumber);
     else
         manageCorrect(errorTarjeta, tarjetaTextField, validCardNumber);
    }
    
    private void checkCSV(){
        if(!(csvTextField.textProperty().getValueSafe().matches("\\d+") && csvTextField.textProperty().getValueSafe().length()== 3))
         //Incorrect phone
         manageError(errorTarjeta, csvTextField, validCardCsv);
     else
         manageCorrect(errorTarjeta, csvTextField, validCardCsv);
    }
    
    private void checkUserTaken() throws ClubDAOException, IOException{
        Club club = Club.getInstance();
        if(club.existsLogin(userTextField.textProperty().getValueSafe()))
         //Incorrect phone
         manageError(errorUsuarioRepetido, userTextField, validUsername);
     else
            manageCorrect(errorUsuarioRepetido, userTextField, validUsername);
    }
    
    //CONTROLADORES ERRORES
    
    private void manageError(Label errorLabel,TextField textField, BooleanProperty boolProp ){
        boolProp.setValue(Boolean.FALSE);
        showErrorMessage(errorLabel,textField);
        
       // textField.requestFocus();
 
    }
    /**
     * Updates the boolProp to true. Changes the background 
     * of the edit to the default value. Makes the error label invisible. 
     * @param errorLabel label added to alert the user
     * @param textField edit text added to allow user to introduce the value
     * @param boolProp property which stores if the value is correct or not
     */
    private void manageCorrect(Label errorLabel,TextField textField, BooleanProperty boolProp ){
        boolProp.setValue(Boolean.TRUE);
        hideErrorMessage(errorLabel,textField);
        
    }
    /**
     * Changes to red the background of the edit and
     * makes the error label visible
     * @param errorLabel
     * @param textField 
     */
    private void showErrorMessage(Label errorLabel,TextField textField)
    {
        errorLabel.visibleProperty().set(true);
        textField.styleProperty().setValue("-fx-background-color: transparent");
    }
    /**
     * Changes the background of the edit to the default value
     * and makes the error label invisible.
     * @param errorLabel
     * @param textField 
     */
    private void hideErrorMessage(Label errorLabel,TextField textField)
    {
        errorLabel.visibleProperty().set(false);
    }
    private void cancelarRegistro(ActionEvent e) {
        Node source = (Node) e.getSource();     //Me devuelve el elemento al que hice click
        Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
        stage.close(); 
    }
    @FXML
    private void MostrarContraseña(ActionEvent event) {
        Image imageEyeOpen = new Image("Iconos/eye.png");
        Image imageEyeClosed = new Image("Iconos/eyeClosed.png");
        if (pulsadoOK) {
            passPasswordField.setText(hiddenTextField.getText());
            passPasswordField.setVisible(true);
            hiddenTextField.setVisible(false);
            imageEye.setImage(imageEyeOpen);
            pulsadoOK=false;
        } else {
            hiddenTextField.setText(passPasswordField.getText());
            hiddenTextField.setVisible(true);
            //passwordDisplayField.setDisable(true);
            passPasswordField.setVisible(false);
            imageEye.setImage(imageEyeClosed);
            pulsadoOK=true;
        }
    }
    @FXML
    private void MostrarContraseña2(ActionEvent event) {
        Image imageEyeOpen = new Image("Iconos/eye.png");
        Image imageEyeClosed = new Image("Iconos/eyeClosed.png");
        if (pulsadoOK2) {
            pass2PasswordField.setText(hiddenTextField2.getText());
            pass2PasswordField.setVisible(true);
            hiddenTextField2.setVisible(false);
            imageEye2.setImage(imageEyeOpen);
            pulsadoOK2=false;
        } else {
            hiddenTextField2.setText(pass2PasswordField.getText());
            hiddenTextField2.setVisible(true);
            //passwordDisplayField.setDisable(true);
            pass2PasswordField.setVisible(false);
            imageEye2.setImage(imageEyeClosed);
            pulsadoOK2=true;
        }
    }
    
    
    @FXML
    private void enviarRegistro(ActionEvent e) throws IOException,ClubDAOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirmación");
        alert.setHeaderText("Registrado correctamente");
        alert.setContentText("Ahora eres un nuevo usuario. Ya puedes iniciar sesión");

        //Preparar css 
        DialogPane alertDialog = alert.getDialogPane();
        alertDialog.getStylesheets().add(getClass().getResource("/Application/css/alert.css").toExternalForm());
        alertDialog.getStyleClass().add("alert");
        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/Iconos/thumb-up.png")));
        icon.fitWidthProperty().set(60);
        icon.fitHeightProperty().set(60);
        alertDialog.setGraphic(icon);

        //Preparar botones 
        ButtonType okButton = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);
        alertDialog.lookupButton(okButton).setId("primaryButton");

        //Mostrar alerta 
        alert.showAndWait();
        Club club = Club.getInstance();
        String nickName = userTextField.getText();
        String password = passPasswordField.getText();
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String creditC = tarjetaTextField.getText();
        int svc;
        if(csvTextField.getText().equals("")){
             svc =000;
        }else{
            svc= Integer.parseInt(csvTextField.getText());
        }
        
        String tel = phoneTextField.getText();
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = circleAvatar.snapshot(parameters, null);
        
        club.registerMember(name,surname,tel,nickName,password,creditC,svc,image);
        switchToInicio(e);
        
    }
}