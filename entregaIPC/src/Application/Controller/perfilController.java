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
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
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
public class perfilController implements Initializable {
    
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
    private Button showPassword2;
    @FXML
    private Circle circleAvatar;
    private Member currentUser;
    @FXML
    private Button buttonEdit;
    @FXML
    private Button editName;
    @FXML
    private Button editPhone;
    @FXML
    private Button editSurname;
    @FXML
    private Button editBank;
    @FXML
    private Button buttonBack1;
    @FXML
    private Button buttonModificar;


    @FXML
    private void editName(ActionEvent event) {
        if(Data.firstP && showAlert() || !Data.firstP) nameTextField.setDisable(false);
    }

    @FXML
    private void editPhone(ActionEvent event) {
        if(Data.firstP && showAlert() || !Data.firstP) phoneTextField.setDisable(false);
    }

    @FXML
    private void editSurname(ActionEvent event) {
        if(Data.firstP && showAlert() || !Data.firstP) surnameTextField.setDisable(false);
    }

    @FXML
    private void editBank(ActionEvent event) {
        if(Data.firstP && showAlert() || !Data.firstP) {
            tarjetaTextField.setDisable(false);
            csvTextField.setDisable(false);
        }
    }
    
    private boolean showAlert() {
        //Preparar alerta
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Verificación");
        alert.setHeaderText("Introduce tu contraseña");
        alert.setContentText("Contraseña:");
        
        //Preparar css 
        DialogPane alertDialog = alert.getDialogPane();
        alertDialog.getStylesheets().add(getClass().getResource("/Application/css/alert.css").toExternalForm());
        alertDialog.getStyleClass().add("alert");
        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/Iconos/magnifying-glass.png")));
        icon.fitWidthProperty().set(60);
        icon.fitHeightProperty().set(60);
        GridPane gridPane = new GridPane();
        PasswordField password = new PasswordField();
        gridPane.add(password, 1, 0);
        alertDialog.setContent(gridPane);
        alertDialog.setGraphic(icon);
        
        //Preparar botones 
        ButtonType okButton = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);
        alertDialog.lookupButton(okButton).setId("primaryButton");
        alertDialog.lookupButton(cancelButton).setId("secondaryButton");
        
        //Mostrar alerta y procesar respuesta
        Optional<ButtonType> result = alert.showAndWait();
        
        // Obteniendo el resultado (pre Java 8)
        if (result.isPresent() && result.get().equals(okButton)){
            if(password.getText().equals(currentUser.getPassword())){
                Data.firstP = false;
                return true;
            }
            
            else{
                //Preparar alerta
                Alert wAlert = new Alert(AlertType.WARNING);
                wAlert.setTitle("Aviso");
                wAlert.setHeaderText("Contraseña incorrecta");
                wAlert.setContentText("Vuelve a intentar editar un campo e introduce la contraseña correctamente.");

                //Preparar css 
                DialogPane wAlertDialog = wAlert.getDialogPane();
                wAlertDialog.getStylesheets().add(getClass().getResource("/Application/css/alert.css").toExternalForm());
                wAlertDialog.getStyleClass().add("alert");
                ImageView wIcon = new ImageView(new Image(getClass().getResourceAsStream("/Iconos/principal/hazard-sign.png")));
                wIcon.fitWidthProperty().set(60);
                wIcon.fitHeightProperty().set(60);
                wAlertDialog.setGraphic(wIcon);

                //Preparar botones 
                ButtonType wOkButton = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
                wAlert.getButtonTypes().setAll(wOkButton);
                wAlertDialog.lookupButton(wOkButton).setId("primaryButton");

                //Mostrar alerta 
                wAlert.showAndWait();
            }
        }
        
        return false;
    }

    @FXML
    private void editPassword(ActionEvent event) {    
        if(Data.firstP && showAlert() || !Data.firstP) {
            passPasswordField.setDisable(false);
            hiddenTextField.setDisable(false);
            showPassword.setDisable(false);
            showPassword2.setDisable(false);
        }
    }

    @FXML
    private void switchToPerfil(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Application/FXML/perfil.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

   
    
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
        //INICIALIZACIÓN DE LOS CAMPOS A VALORES DE USUARIO
        currentUser = Data.getCurrentUser();
        ImagePattern image = new ImagePattern(currentUser.getImage());
        circleAvatar.setFill(image);
        userTextField.setText(currentUser.getNickName());
        //passPasswordField.setText(currentUser.getPassword());
        nameTextField.setText(currentUser.getName());
        surnameTextField.setText(currentUser.getSurname());
        phoneTextField.setText(currentUser.getTelephone());
        int svc = currentUser.getSvc();
        if(currentUser.checkHasCreditInfo()){
            tarjetaTextField.setText(currentUser.getCreditCard());
            csvTextField.setText(Integer.toString(svc));
        }
        
        
        //LISTENERS DE CAMPO CONTRASEÑA
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
        
     
        //CODIGO CAMBIO DE CAMPO CON ENTER (TAB LO HACE AUTOMATICAMENTE)
        
        nameTextField.setOnKeyPressed(event -> {
            if ((event.getCode() == KeyCode.ENTER)) {
                buttonModificar.requestFocus();
                buttonModificar.fire();
                event.consume();
            }
        });
        surnameTextField.setOnKeyPressed(event -> {
            if ((event.getCode() == KeyCode.ENTER)) {
                buttonModificar.requestFocus();
                buttonModificar.fire();
                event.consume();
            }
        });
        phoneTextField.setOnKeyPressed(event -> {
            if ((event.getCode() == KeyCode.ENTER)) {
                buttonModificar.requestFocus();
                buttonModificar.fire();
                event.consume();
            }
        });
        tarjetaTextField.setOnKeyPressed(event -> {
            if ((event.getCode() == KeyCode.ENTER)) {
                buttonModificar.requestFocus();
                buttonModificar.fire();
                event.consume();
            }
        });
        csvTextField.setOnKeyPressed(event -> {
            if ((event.getCode() == KeyCode.ENTER)) {
                buttonModificar.requestFocus();
                buttonModificar.fire();
                event.consume();
            }
        });
        
        // IMAGENES PERFIL
                
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
        
        //imagesComboBox.getSelectionModel().selectFirst();
        /*
        ERRORES
        */
        
        //ERROR contraseña
        validPassword = new SimpleBooleanProperty();
        
        validPassword.setValue(Boolean.TRUE);
        
        passPasswordField.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                checkEditPassword();
            }
        });
        //ERROR contraseña no coincide
        matchPassword = new SimpleBooleanProperty();
        
        matchPassword.setValue(Boolean.TRUE);
        
        pass2PasswordField.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                comparePassword();
            }
        });
        
        //ERROR NOMBRE
        validName = new SimpleBooleanProperty();
        
        validName.setValue(Boolean.TRUE);
        
        nameTextField.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                checkName();
            }
        });
        
        //ERROR APELLIDO
        validSurname = new SimpleBooleanProperty();
        
        validSurname.setValue(Boolean.TRUE);
        
        surnameTextField.focusedProperty().addListener((observable, oldValue,newValue)->{
            if(!newValue){ //focus lost.
                checkSurname();
            }
        });
        
        //ERROR NÚMERO TELEFONO
        
        validPhone = new SimpleBooleanProperty();
        
        validPhone.setValue(Boolean.TRUE);
        
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
        
        validCardNumber.setValue(Boolean.TRUE);
        
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
        
        validCardCsv.setValue(Boolean.TRUE);
        
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
        
        BooleanBinding password = Bindings.createBooleanBinding( () ->
                passPasswordField.getText().isEmpty(),
                passPasswordField.textProperty()
        );
        pass2PasswordField.disableProperty().bind(password);
        hiddenTextField2.disableProperty().bind(password);
        BooleanBinding validFields = Bindings.and(validName, validPassword).and(matchPassword).and(validSurname).and(validPhone);

        buttonModificar.disableProperty().bind(Bindings.not(validFields));
        
        
    }
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    // CAMBIO VENTANA A INICIO
    private void switchToInicio(ActionEvent event) throws IOException {
        Data.firstP = true;
        root = FXMLLoader.load(getClass().getResource("/Application/FXML/inicioSesionEscena.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void switchToPrincipal(ActionEvent event) throws IOException {
        switch(Data.scene) {
            case 1:
                root = FXMLLoader.load(getClass().getResource("/Application/FXML/principal.fxml"));
                break;
            case 2:
                root = FXMLLoader.load(getClass().getResource("/Application/FXML/principalPistas.fxml"));
                break;
        }
        
        Data.firstP = true;
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
    private void enviarModificacion(ActionEvent e) throws IOException,ClubDAOException {
        Club club = Club.getInstance();
        String password = passPasswordField.getText();
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String creditC = tarjetaTextField.getText();
        String phone = phoneTextField.getText();
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        
        WritableImage image = circleAvatar.snapshot(parameters, null);
        
        int svc;
        if(csvTextField.getText().equals("")){
             svc =000;
        }else{
            svc= Integer.parseInt(csvTextField.getText());
        }
        if(!password.equals("")){
            currentUser.setPassword(password);
        }
        currentUser.setName(name);
        currentUser.setSurname(surname);
        currentUser.setTelephone(phone);
        currentUser.setCreditCard(creditC);
        currentUser.setSvc(svc);
        currentUser.setImage(image);
        circleAvatar.setFill(circleAvatar.getFill());
        switchToPerfil(e);
    }
}
