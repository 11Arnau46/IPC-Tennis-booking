/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Application.Controller;

import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author arnau1146
 */
public class inicioSesionController implements Initializable {

    @FXML
    private Button buttonLogIn;
    @FXML
    private TextField userTextField;
    @FXML
    private PasswordField passPasswordField;
    @FXML
    private BorderPane ventanaSesion;
    @FXML
    private Hyperlink linkRegister;
    @FXML
    private TextField passwordDisplayField;
    
    //ERROR
    private BooleanProperty validUsername;
    private BooleanProperty validPassword;
    @FXML
    private Label errorPasswordDifferent;
    @FXML
    private Button showPassword;
    /**
     * Initializes the controller class.
     */
    private static boolean pulsadoOK = false;
    @FXML
    private ImageView imageEye;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //CAMBIO DE CAMPO ENTER
        userTextField.setOnKeyPressed(event -> {
            if ((event.getCode() == KeyCode.ENTER)) {
                passPasswordField.requestFocus();
            }
        });
        passwordDisplayField.setOnKeyPressed(event -> {
            if ((event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB)) {
                buttonLogIn.requestFocus();
                buttonLogIn.fire();
                event.consume();
            }
        });
        passPasswordField.setOnKeyPressed(event -> {
            if ((event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB)) {
                buttonLogIn.requestFocus();
                buttonLogIn.fire();
                event.consume();
            }
        });
        TextFormatter<String> textFormatter4 = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[^\\s]{0,10}")) {
            return change; // Accept the change if it matches the pattern
            }
            return null; // Reject the change if it exceeds 8 characters or contains spaces
        });

        userTextField.setTextFormatter(textFormatter4);
        
        //HABILITACION BOTONES
        
        try {
            // TODO
            Club club = Club.getInstance();
        } catch (ClubDAOException ex) {
            Logger.getLogger(inicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(inicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        validPassword = new SimpleBooleanProperty();
        validPassword.setValue(Boolean.FALSE);
        validUsername = new SimpleBooleanProperty();
        validUsername.setValue(Boolean.FALSE);
        
       
       
        BooleanBinding pass2 = Bindings.and(passwordDisplayField.textProperty().isEmpty(),
                                          passPasswordField.textProperty().isEmpty() );
      
        BooleanBinding pass = Bindings.or(userTextField.textProperty().isEmpty(),
                                          pass2 );
       buttonLogIn.disableProperty().bind(pass);
       showPassword.disableProperty().bind(passPasswordField.textProperty().isEmpty());
        
        
    
    }       
    private Stage stage;
    private Scene scene;
    private Parent root;

    //buttonRegister.scaleXProperty().bind(buttonRegister.getScene().widthProperty().multiply(0.5));
    //ventanaSesion.scaleYProperty().bind(ventanaSesion.getScene().heightProperty().multiply(0.5));  
    private void switchToPrincipal(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Application/FXML/principal.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    @FXML
    private void switchToRegister(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Application/FXML/registro.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToPistas(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Application/FXML/tablaPistas.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }   

    @FXML
    private void MostrarContraseña(ActionEvent event) {
        Image imageEye2 = new Image("Iconos/eye.png");
        Image imageEyeClosed = new Image("Iconos/eyeClosed.png");
        if (pulsadoOK) {
            passPasswordField.setText(passwordDisplayField.getText());
            passPasswordField.setVisible(true);
            passwordDisplayField.setVisible(false);
            imageEye.setImage(imageEye2);
            pulsadoOK=false;
        } else {
            passwordDisplayField.setText(passPasswordField.getText());
            passwordDisplayField.setVisible(true);
            //passwordDisplayField.setDisable(true);
            passPasswordField.setVisible(false);
            imageEye.setImage(imageEyeClosed);
            pulsadoOK=true;
        }  
    }
    //ERROR
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
        textField.styleProperty().setValue("");
    }
    
    @FXML
    private void iniciarSesion(ActionEvent event) throws ClubDAOException, IOException {
        Club club = Club.getInstance();
        Member loggingIn;
        
        if(pulsadoOK){
            loggingIn = club.getMemberByCredentials(userTextField.textProperty().getValueSafe(),passwordDisplayField.textProperty().getValueSafe());
        }else{
            loggingIn = club.getMemberByCredentials(userTextField.textProperty().getValueSafe(),passPasswordField.textProperty().getValueSafe());
        }
        if(loggingIn == null){
           errorPasswordDifferent.setText("Usuario/Contraseña Incorrecto");
           manageError(errorPasswordDifferent, passPasswordField, validPassword);
           Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), evt -> {
                errorPasswordDifferent.setText("");
                manageCorrect(errorPasswordDifferent, passPasswordField, validPassword);
           }));
           timeline.play();
        }else {
           manageCorrect(errorPasswordDifferent, passPasswordField, validPassword);
           Data.setCurrentUser(loggingIn);
           switchToPrincipal(event);
        }
        
    }
         
}

    
    
    
    
