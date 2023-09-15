/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Application.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
;
import javafx.stage.Stage;
import model.*;
import javafx.stage.Stage;
import model.*;
import javafx.stage.Stage;
import model.*;
import javafx.stage.Stage;
import model.*;
import javafx.stage.Stage;
import model.*;
import javafx.stage.Stage;
import model.*;
import javafx.stage.Stage;
import model.*;
import javafx.stage.Stage;
import model.*;
import javafx.stage.Stage;
import model.*;
import javafx.stage.Stage;
import model.*;
import javafx.stage.Stage;
import model.*;
import javafx.stage.Stage;
import model.*;
import javafx.stage.Stage;
import model.*;
import javafx.stage.Stage;
import model.*;
import javafx.stage.Stage;
import model.*;
import javafx.stage.Stage;
import model.*;

/**
 * FXML Controller class
 *
 *
 */
public class principalController implements Initializable {

    @FXML
    private Text userName;
    @FXML
    private ComboBox<Integer> dayCombo;
    @FXML
    private ComboBox<String> showBy;
    @FXML
    private ToggleButton hourButton9;
    @FXML
    private ToggleButton hourButton10;
    @FXML
    private ToggleButton hourButton11;
    @FXML
    private ToggleButton hourButton12;
    @FXML
    private ToggleButton hourButton13;
    @FXML
    private ToggleButton hourButton14;
    @FXML
    private ToggleButton hourButton15;
    @FXML
    private ToggleButton hourButton16;
    @FXML
    private ToggleButton hourButton17;
    @FXML
    private ToggleButton hourButton18;
    @FXML
    private ToggleButton hourButton19;
    @FXML
    private ToggleButton hourButton20;
    @FXML
    private ToggleButton hourButton21;
    @FXML
    private Text smallText1;
    @FXML
    private Label bigLabel1;
    @FXML
    private Button bookButton1;
    @FXML
    private Text smallText2;
    @FXML
    private Label bigLabel2;
    @FXML
    private Button bookButton2;
    @FXML
    private Text smallText3;
    @FXML
    private Label bigLabel3;
    @FXML
    private Button bookButton3;
    @FXML
    private Text smallText4;
    @FXML
    private Label bigLabel4;
    @FXML
    private Button bookButton4;
    @FXML
    private Text smallText5;
    @FXML
    private Label bigLabel5;
    @FXML
    private Button bookButton5;
    @FXML
    private Text smallText6;
    @FXML
    private Label bigLabel6;
    @FXML
    private Button bookButton6;
    
    private ToggleButton[] hourButtons;
    private Button[] bookButtons;
    private Label[] bigLabels;
    private Text[] smallTexts;
    @FXML
    private Circle circleAvatar;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private Member currentUser;
    private LocalTime currentHour;
    private int currentDayIndex;
    private List<Booking> dayBookings; 
    private List<Booking>[] hourBookings;
    private LocalDate[] week;
    @FXML
    private HBox hBox;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Obtener usuario
        currentUser = Data.getCurrentUser();
        
        //Configurar perfil
        userName.textProperty().set(currentUser.getNickName());
        userName.setWrappingWidth(userName.getBoundsInLocal().getWidth());
        hBox.setPrefWidth(userName.getBoundsInLocal().getWidth() + 98);
        ImagePattern image = new ImagePattern(currentUser.getImage());
        circleAvatar.setFill(image);
        
        //Configurar combo boxes
        LocalDate today = LocalDate.now();
        week = new LocalDate[8];
        
        for(int i = 0; i < week.length; i++) {
            week[i] = today.plusDays(i);
            dayCombo.getItems().add(week[i].getDayOfMonth());
        }
        
        currentHour = Data.leftHour;
        currentDayIndex = Data.leftDayIndex;
        LocalTime now = LocalTime.now();
        
        if(Data.firstH) {
            Data.firstH = false;
            
            if(now.isAfter(LocalTime.of(21,0))) currentDayIndex = week[1].getDayOfMonth();
            else currentHour = LocalTime.of(now.getHour() + 1, 0);
        }
      
        dayCombo.setValue(week[currentDayIndex].getDayOfMonth());
        
        showBy.getItems().add("Hora");
        showBy.getItems().add("Pista");
        showBy.setValue("Hora");
        
        //Inicializar arrays
        hourButtons = new ToggleButton[13];
        hourButtons[0] = hourButton9;
        hourButtons[1] = hourButton10;
        hourButtons[2] = hourButton11;
        hourButtons[3] = hourButton12;
        hourButtons[4] = hourButton13;
        hourButtons[5] = hourButton14;
        hourButtons[6] = hourButton15;
        hourButtons[7] = hourButton16;
        hourButtons[8] = hourButton17;
        hourButtons[9] = hourButton18;
        hourButtons[10] = hourButton19;
        hourButtons[11] = hourButton20;
        hourButtons[12] = hourButton21;
        
        bookButtons = new Button[6];
        bookButtons[0] = bookButton1;
        bookButtons[1] = bookButton2;
        bookButtons[2] = bookButton3;
        bookButtons[3] = bookButton4;
        bookButtons[4] = bookButton5;
        bookButtons[5] = bookButton6;
        
        bigLabels = new Label[6];
        bigLabels[0] = bigLabel1;
        bigLabels[1] = bigLabel2;
        bigLabels[2] = bigLabel3;
        bigLabels[3] = bigLabel4;
        bigLabels[4] = bigLabel5;
        bigLabels[5] = bigLabel6;
        
        smallTexts = new Text[6];
        smallTexts[0] = smallText1;
        smallTexts[1] = smallText2;
        smallTexts[2] = smallText3;
        smallTexts[3] = smallText4;
        smallTexts[4] = smallText5;
        smallTexts[5] = smallText6;
        
        hourBookings = new List[13];
        
        for(int i = 0; i < hourBookings.length; i++) {
            hourBookings[i] = new ArrayList<Booking>();
        }
        
        //Preparar el dia
        try{setDay(currentDayIndex);}
        catch(Exception e) {System.out.println(e);}
    }    
    
    
    @FXML
    private void logOut(ActionEvent event) throws IOException {
        //Preparar alerta
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("¿Seguro que quieres cerrar sesión?");
        alert.setContentText("Volverás a la pantalla de inicio de sesión.");
        
        //Preparar css 
        DialogPane alertDialog = alert.getDialogPane();
        alertDialog.getStylesheets().add(getClass().getResource("/Application/css/alert.css").toExternalForm());
        alertDialog.getStyleClass().add("alert");
        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/Iconos/principal/pngegg.png")));
        icon.fitWidthProperty().set(60);
        icon.fitHeightProperty().set(60);
        alertDialog.setGraphic(icon);
        
        //Preparar botones 
        ButtonType okButton = new ButtonType("Aceptar", ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);
        alertDialog.lookupButton(okButton).setId("primaryButton");
        alertDialog.lookupButton(cancelButton).setId("secondaryButton");
        
        //Mostrar alerta y procesar respuesta
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get().equals(okButton)) {
            //Actualizar valores de Data
            Data.scene = 0;
            Data.leftDayIndex = 0;
            Data.leftHour = LocalTime.of(9, 0);
            Data.firstH = true;
            Data.setCurrentUser(null);
            
            //Cargar escena
            root = FXMLLoader.load(getClass().getResource("/Application/FXML/inicioSesionEscena.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
        }
    }

    @FXML
    private void switchToReservas(ActionEvent event) throws IOException {
        //Actualizar valores de Data
        Data.scene = 1;
        Data.leftDayIndex = currentDayIndex;
        Data.leftHour = currentHour;
        
        //Cargar escena
        root = FXMLLoader.load(getClass().getResource("/Application/FXML/reservas.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToTabla(ActionEvent event) throws IOException {
        //Actualizar valores de Data
        Data.scene = 1;
        Data.leftDayIndex = currentDayIndex;
        Data.leftHour = currentHour;
        
        //Cargar escena
        root = FXMLLoader.load(getClass().getResource("/Application/FXML/tablaPistas.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void switchToPerfil(ActionEvent event) throws IOException{
        //Actualizar valores de Data
        Data.scene = 1;
        Data.leftDayIndex = currentDayIndex;
        Data.leftHour = currentHour;
        
        //Cargar escena
        root = FXMLLoader.load(getClass().getResource("/Application/FXML/perfil.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void setDay(ActionEvent event) throws ClubDAOException, IOException {
        int dayInt = dayCombo.getValue();
        int dayIndex = 0;
        
        for(int i = 0; i < week.length; i++) {
            if(week[i].getDayOfMonth() == dayInt) {
                dayIndex = i;
                break;
            }
        }
        
        setDay(dayIndex);
    }
    
    private void setDay(int dayIndex) throws ClubDAOException, IOException {
        currentDayIndex = dayIndex;
        dayBookings = Club.getInstance().getForDayBookings(week[dayIndex]);
        
        for(int i = 0; i < hourBookings.length; i++) {hourBookings[i].clear();}
       
        //Dividir las reservas por horas y cambiar el color de los botones de las horas
        for(int i = 0; i < hourBookings.length; i++) {
            LocalTime h = LocalTime.of(i + 9, 0);
            
            for(int j = 0; j < dayBookings.size(); j++) {
                Booking b = dayBookings.get(j);
                
                if(b.getFromTime().equals(h)) {
                    hourBookings[i].add(b);
                }
            }
            
            LocalDateTime timeOfBooking = LocalDateTime.of(week[currentDayIndex], h);
            
            if(hourBookings[i].size() == 6 || LocalDateTime.now().isAfter(timeOfBooking)) {
                hourButtons[i].textFillProperty().set(Color.web("#c91e1e"));
            }
            else hourButtons[i].textFillProperty().set(Color.web("#149716"));
        }
       
        setHour(currentHour);
    }

    @FXML
    private void setHour(ActionEvent event) throws ClubDAOException, IOException {
        
        ToggleButton b = (ToggleButton) event.getSource();
        LocalTime hour = null;
        
        for(int i = 0; i < hourButtons.length; i++) {
            if(b.equals(hourButtons[i])) {
                hour = LocalTime.of(i + 9, 0);
                break;
            }
        }
        
        setHour(hour);
    }
    
    private void setHour(LocalTime hour) throws ClubDAOException, IOException{
        currentHour = hour;
        
        //Seleccionar el boton
        for(int i = 0; i < hourButtons.length; i++) {
            hourButtons[i].selectedProperty().set(false);
        }
        
        hourButtons[currentHour.getHour() - 9].selectedProperty().set(true);
        
        //Actulizar los elementos de la UI para reflejar espacios vacios y reservas
        for(int i = 0; i < smallTexts.length; i++) {
            showFree(i);         
        }
        
        for(int i = 0; i < hourBookings[currentHour.getHour() - 9].size(); i++) {
            showBooked(hourBookings[currentHour.getHour() - 9].get(i));         
        }
    }
    
    private void showBooked(Booking b) {
        int courtIndex = Integer.parseInt(b.getCourt().getName().substring(6)) - 1;
        smallTexts[courtIndex].textProperty().set("Ocupado por");
        bigLabels[courtIndex].textProperty().set(b.getMember().getNickName());
        bigLabels[courtIndex].idProperty().set("ocupado");
        bookButtons[courtIndex].disableProperty().set(true);
            
        if(b.belongsToMember(currentUser.getNickName())) {
            bookButtons[courtIndex].textProperty().set("Anular");
            bookButtons[courtIndex].idProperty().set("secondaryButton");
            LocalDateTime bookingDate = LocalDateTime.of(b.getMadeForDay(), b.getFromTime());
            
            if(Duration.between(LocalDateTime.now(), bookingDate).toMinutes() >= 1440) {
                bookButtons[courtIndex].disableProperty().set(false);
            }
        }
            
        else {
            bookButtons[courtIndex].textProperty().set("Reservar");
            bookButtons[courtIndex].idProperty().set("primaryButton");
        }
    }
    
    private void showFree(int courtIndex) {
        smallTexts[courtIndex].textProperty().set("");
        bigLabels[courtIndex].textProperty().set("Libre");
        bigLabels[courtIndex].idProperty().set("libre");
        LocalDateTime timeOfBooking = LocalDateTime.of(week[currentDayIndex], currentHour);
        bookButtons[courtIndex].disableProperty().set(LocalDateTime.now().isAfter(timeOfBooking));
        bookButtons[courtIndex].textProperty().set("Reservar");
        bookButtons[courtIndex].idProperty().set("primaryButton");
    }

    @FXML
    private void showBy(ActionEvent event) throws IOException {
        String s = showBy.getValue();
        
        if(s.equals("Pista")) {
            //Actualizar valores de Data
            Data.scene = 2;
            Data.leftDayIndex = currentDayIndex;
            Data.leftHour = currentHour;

            //Cargar escena
            root = FXMLLoader.load(getClass().getResource("/Application/FXML/principalPistas.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void bookAction(ActionEvent event) throws ClubDAOException, IOException {
        Button b = (Button) event.getSource();
        int courtIndex = 0;
        
        for(int i = 0; i < bookButtons.length; i++) {
            if(b.equals(bookButtons[i])) {
                courtIndex = i;
                break;
            }
        }
        
        Court bookedCourt = new Court("Pista " + (courtIndex + 1));
        LocalDateTime timeOfBooking = LocalDateTime.of(week[currentDayIndex], currentHour);
        
        if(b.textProperty().get().equals("Reservar")) {
            registerBooking(bookedCourt, timeOfBooking);
        }
        
        else{
            removeBooking(bookedCourt, timeOfBooking);
        }
    }
    
    private void registerBooking(Court court, LocalDateTime timeOfBooking) throws ClubDAOException, IOException {
        Club club = Club.getInstance();
        boolean paid = false;
        
        if(currentUser.checkHasCreditInfo()) {
            paid = true;
        }
        
        if(LocalDateTime.now().isAfter(timeOfBooking)) {
            //Preparar alerta
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Esta reserva no se puede hacer");
            alert.setContentText("No puedes reservar una pista para una hora que ya ha pasado.");

            //Preparar css 
            DialogPane alertDialog = alert.getDialogPane();
            alertDialog.getStylesheets().add(getClass().getResource("/Application/css/alert.css").toExternalForm());
            alertDialog.getStyleClass().add("alert");
            ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/Iconos/principal/hazard-sign.png")));
            icon.fitWidthProperty().set(60);
            icon.fitHeightProperty().set(60);
            alertDialog.setGraphic(icon);

            //Preparar botones 
            ButtonType okButton = new ButtonType("Aceptar", ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alertDialog.lookupButton(okButton).setId("primaryButton");

            //Mostrar alerta 
            alert.showAndWait();
            return;
        }
        
        List<Booking> courtBookings = club.getCourtBookings(court.getName(), timeOfBooking.toLocalDate());
        boolean[] bookedHours = new boolean[13];
        
        for(int i = 0; i < courtBookings.size(); i++) {
            Booking b = courtBookings.get(i);
            if(b.getMember().equals(currentUser)) bookedHours[b.getFromTime().getHour() - 9] = true;
        } 
        
        bookedHours[timeOfBooking.getHour() - 9] = true;
        int consecutiveCounter = 0;
        int maxCC = 0;
        
        for(int i = 0; i < bookedHours.length; i++) {
            if(bookedHours[i]) {
                consecutiveCounter++;
                if(consecutiveCounter > maxCC) maxCC = consecutiveCounter;
            }
            
            else consecutiveCounter = 0;
        }
        
        if(maxCC > 2) {
            //Preparar alerta
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Esta reserva no se puede hacer");
            alert.setContentText("No puedes reservar una pista para más de 2 horas consecutivas.");

            //Preparar css 
            DialogPane alertDialog = alert.getDialogPane();
            alertDialog.getStylesheets().add(getClass().getResource("/Application/css/alert.css").toExternalForm());
            alertDialog.getStyleClass().add("alert");
            ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/Iconos/principal/hazard-sign.png")));
            icon.fitWidthProperty().set(60);
            icon.fitHeightProperty().set(60);
            alertDialog.setGraphic(icon);

            //Preparar botones 
            ButtonType okButton = new ButtonType("Aceptar", ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alertDialog.lookupButton(okButton).setId("primaryButton");

            //Mostrar alerta 
            alert.showAndWait();
            return;
        } 
            
        if(Duration.between(LocalDateTime.now(), timeOfBooking).toMinutes() < 1442) {
            //Preparar alerta
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("¿Seguro que quieres reservar?");
            alert.setContentText("Las reservas no se pueden cancelar cuando faltan menos de 24h para la hora de la reserva.");

            //Preparar css 
            DialogPane alertDialog = alert.getDialogPane();
            alertDialog.getStylesheets().add(getClass().getResource("/Application/css/alert.css").toExternalForm());
            alertDialog.getStyleClass().add("alert");
            ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/Iconos/principal/pngegg.png")));
            icon.fitWidthProperty().set(60);
            icon.fitHeightProperty().set(60);
            alertDialog.setGraphic(icon);

            //Preparar botones 
            ButtonType okButton = new ButtonType("Aceptar", ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(okButton, cancelButton);
            alertDialog.lookupButton(okButton).setId("primaryButton");
            alertDialog.lookupButton(cancelButton).setId("secondaryButton");

            //Mostrar alerta y procesar respuesta
            Optional<ButtonType> result = alert.showAndWait();

            if(!result.isPresent() || (result.isPresent() && result.get().equals(cancelButton))) {
               return;
            }     
        }
            
        club.registerBooking(LocalDateTime.now(), timeOfBooking.toLocalDate(), timeOfBooking.toLocalTime(), paid, court, currentUser);
        setDay(currentDayIndex);
    }
    
    private void removeBooking(Court court, LocalDateTime timeOfBooking) throws ClubDAOException, IOException {
        if(Duration.between(LocalDateTime.now(), timeOfBooking).toMinutes() < 1440) {
            //Preparar alerta
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Esta reserva no se puede anular");
            alert.setContentText("Las reservas no se pueden cancelar cuando faltan menos de 24h para la hora de la reserva.");

            //Preparar css 
            DialogPane alertDialog = alert.getDialogPane();
            alertDialog.getStylesheets().add(getClass().getResource("/Application/css/alert.css").toExternalForm());
            alertDialog.getStyleClass().add("alert");
            ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/Iconos/principal/hazard-sign.png")));
            icon.fitWidthProperty().set(60);
            icon.fitHeightProperty().set(60);
            alertDialog.setGraphic(icon);

            //Preparar botones 
            ButtonType okButton = new ButtonType("Aceptar", ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alertDialog.lookupButton(okButton).setId("primaryButton");

            //Mostrar alerta 
            alert.showAndWait();
        }
            
        else{
            List<Booking> courtBookings = Club.getInstance().getCourtBookings(court.getName(), timeOfBooking.toLocalDate());
            Booking toRemove = null;
            
            for(int i = 0; i < courtBookings.size(); i++) {  
                Booking b = courtBookings.get(i);
            
                if(b.getFromTime().equals(timeOfBooking.toLocalTime())) {
                    toRemove = b;
                    break;
                }
            }
                
            Club.getInstance().removeBooking(toRemove);
            setDay(currentDayIndex);
        }
    }
}
