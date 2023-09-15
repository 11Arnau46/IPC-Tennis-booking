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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Court;
import model.Member;

/**
 * FXML Controller class
 *
 * @author pepis
 */
public class principalPistasController implements Initializable {

    @FXML
    private ComboBox<Integer> dayCombo;
    @FXML
    private Text userName;
    @FXML
    private ToggleButton courtButton1;
    @FXML
    private ToggleButton courtButton2;
    @FXML
    private ToggleButton courtButton3;
    @FXML
    private ToggleButton courtButton4;
    @FXML
    private ToggleButton courtButton5;
    @FXML
    private ToggleButton courtButton6;
    @FXML
    private ComboBox<String> showBy;
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
    @FXML
    private Text smallText7;
    @FXML
    private Label bigLabel7;
    @FXML
    private Button bookButton7;
    @FXML
    private Text smallText8;
    @FXML
    private Label bigLabel8;
    @FXML
    private Button bookButton8;
    @FXML
    private Text smallText9;
    @FXML
    private Label bigLabel9;
    @FXML
    private Button bookButton9;
    @FXML
    private Text smallText10;
    @FXML
    private Label bigLabel10;
    @FXML
    private Button bookButton10;
    @FXML
    private Text smallText11;
    @FXML
    private Label bigLabel11;
    @FXML
    private Button bookButton11;
    @FXML
    private Text smallText12;
    @FXML
    private Label bigLabel12;
    @FXML
    private Button bookButton12;
    @FXML
    private Text smallText13;
    @FXML
    private Label bigLabel13;
    @FXML
    private Button bookButton13;
    @FXML
    private ImageView courtImage;
    @FXML
    private Label courtName;
    @FXML
    private Circle circleAvatar;
    
    private ToggleButton[] courtButtons;
    private Button[] bookButtons;
    private Label[] bigLabels;
    private Text[] smallTexts;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private Member currentUser;
    private Court currentCourt;
    private int currentDayIndex;
    private List<Booking> dayBookings;
    private List<Booking>[] courtBookings;
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
        
        currentCourt = Data.leftCourt;
        currentDayIndex = Data.leftDayIndex;
        dayCombo.setValue(week[currentDayIndex].getDayOfMonth());
        
        showBy.getItems().add("Hora");
        showBy.getItems().add("Pista");
        showBy.setValue("Pista");
        
        //Inicializar arrays
        courtButtons = new ToggleButton[6];
        courtButtons[0] = courtButton1;
        courtButtons[1] = courtButton2;
        courtButtons[2] = courtButton3;
        courtButtons[3] = courtButton4;
        courtButtons[4] = courtButton5;
        courtButtons[5] = courtButton6;
        
        bookButtons = new Button[13];
        bookButtons[0] = bookButton1;
        bookButtons[1] = bookButton2;
        bookButtons[2] = bookButton3;
        bookButtons[3] = bookButton4;
        bookButtons[4] = bookButton5;
        bookButtons[5] = bookButton6;
        bookButtons[6] = bookButton7;
        bookButtons[7] = bookButton8;
        bookButtons[8] = bookButton9;
        bookButtons[9] = bookButton10;
        bookButtons[10] = bookButton11;
        bookButtons[11] = bookButton12;
        bookButtons[12] = bookButton13;
        
        bigLabels = new Label[13];
        bigLabels[0] = bigLabel1;
        bigLabels[1] = bigLabel2;
        bigLabels[2] = bigLabel3;
        bigLabels[3] = bigLabel4;
        bigLabels[4] = bigLabel5;
        bigLabels[5] = bigLabel6;
        bigLabels[6] = bigLabel7;
        bigLabels[7] = bigLabel8;
        bigLabels[8] = bigLabel9;
        bigLabels[9] = bigLabel10;
        bigLabels[10] = bigLabel11;
        bigLabels[11] = bigLabel12;
        bigLabels[12] = bigLabel13;
        
        smallTexts = new Text[13];
        smallTexts[0] = smallText1;
        smallTexts[1] = smallText2;
        smallTexts[2] = smallText3;
        smallTexts[3] = smallText4;
        smallTexts[4] = smallText5;
        smallTexts[5] = smallText6;
        smallTexts[6] = smallText7;
        smallTexts[7] = smallText8;
        smallTexts[8] = smallText9;
        smallTexts[9] = smallText10;
        smallTexts[10] = smallText11;
        smallTexts[11] = smallText12;
        smallTexts[12] = smallText13;
        
        courtBookings = new List[6];
        
        for(int i = 0; i < courtBookings.length; i++) {
            courtBookings[i] = new ArrayList<Booking>();
        }
        
        //Preparar el dia
        try{setDay(currentDayIndex);}
        catch(Exception e) {System.out.println(e);}
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
        
        for(int i = 0; i < courtBookings.length; i++) {courtBookings[i].clear();}
        
        //Dividir las reservas por pistas y cambiar el color de los botones de las pistas
        for(int i = 0; i < courtBookings.length; i++) {
            int afterHourCounter = 0;
            
            for(int j = 0; j < dayBookings.size(); j++) {
                Booking b = dayBookings.get(j);
                
                if(b.getCourt().getName().equals("Pista " + (i + 1))) {
                    courtBookings[i].add(b);
                    
                    if(b.getFromTime().isAfter(LocalTime.now())) afterHourCounter++;
                }
            }
            
            if(currentDayIndex == 0) {
                if(LocalTime.now().getHour() + afterHourCounter >= 21) courtButtons[i].textFillProperty().set(Color.web("#c91e1e"));
         
                else courtButtons[i].textFillProperty().set(Color.web("#149716"));
            }
            
            else{
                if(courtBookings[i].size() == 13) courtButtons[i].textFillProperty().set(Color.web("#c91e1e"));
                
                else courtButtons[i].textFillProperty().set(Color.web("#149716"));
            }
        }
       
        setCourt(currentCourt);
    }
    
    @FXML
    private void setCourt(ActionEvent event) {
        ToggleButton b = (ToggleButton) event.getSource();
        Court court= null;
        
        for(int i = 0; i < courtButtons.length; i++) {
            if(b.equals(courtButtons[i])) {
                court = new Court("Pista " + (i + 1));
                break;
            }
        }
        
        setCourt(court);
    }
    
    private void setCourt(Court court) {
        currentCourt = court;
        
        //Seleccionar el boton
        for(int i = 0; i < courtButtons.length; i++) {
            courtButtons[i].selectedProperty().set(false);
        }
        
        //Actulizar los elementos de la UI para reflejar espacios vacios y reservas
        int courtIndex = Integer.parseInt(currentCourt.getName().substring(6)) - 1;
        courtButtons[courtIndex].selectedProperty().set(true);
        
        for(int i = 0; i < bookButtons.length; i++) {
            showFree(i);
        }
        
        for(int i = 0; i < courtBookings[courtIndex].size(); i++) {
            showBooked(courtBookings[courtIndex].get(i));         
        }
        
        courtName.textProperty().set(court.getName());
        courtImage.imageProperty().set(new Image(getClass().getResourceAsStream("/Iconos/pistas/pista" + (courtIndex + 1) + ".png")));
    }
    
    private void showFree(int hourIndex) {
        smallTexts[hourIndex].textProperty().set("");
        bigLabels[hourIndex].textProperty().set("Libre");
        bigLabels[hourIndex].idProperty().set("libre");
        bookButtons[hourIndex].textProperty().set("Reservar");
        bookButtons[hourIndex].idProperty().set("primaryButton");
        LocalTime h = LocalTime.of(hourIndex + 9, 0);
        LocalDateTime timeOfBooking = LocalDateTime.of(week[currentDayIndex], h);
        bookButtons[hourIndex].disableProperty().set(LocalDateTime.now().isAfter(timeOfBooking));
    }
    
    private void showBooked(Booking b) {
        int hourIndex = b.getFromTime().getHour() - 9;
        smallTexts[hourIndex].textProperty().set("Ocupado por");
        bigLabels[hourIndex].textProperty().set(b.getMember().getNickName());
        bigLabels[hourIndex].idProperty().set("ocupado");
        bookButtons[hourIndex].disableProperty().set(true);
            
        if(b.belongsToMember(currentUser.getNickName())) {
            bookButtons[hourIndex].textProperty().set("Anular");
            bookButtons[hourIndex].idProperty().set("secondaryButton");
            LocalDateTime bookingDate = LocalDateTime.of(b.getMadeForDay(), b.getFromTime());
            
            if(Duration.between(LocalDateTime.now(), bookingDate).toMinutes() >= 1440) {
                bookButtons[hourIndex].disableProperty().set(false);
            }
        }
            
        else {
            bookButtons[hourIndex].textProperty().set("Reservar");
            bookButtons[hourIndex].idProperty().set("primaryButton");
        }
    }

    @FXML
    private void switchToReservas(ActionEvent event) throws IOException {
        //Actualizar valores de Data
        Data.scene = 2;
        Data.leftDayIndex = currentDayIndex;
        Data.leftCourt= currentCourt;
        
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
        Data.scene = 2;
        Data.leftDayIndex = currentDayIndex;
        Data.leftCourt= currentCourt;
        
        //Cargar escena
        root = FXMLLoader.load(getClass().getResource("/Application/FXML/tablaPistas.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToPerfil(ActionEvent event) throws IOException {
        //Actualizar valores de Data
        Data.scene = 2;
        Data.leftDayIndex = currentDayIndex;
        Data.leftCourt= currentCourt;
        
        //Cargar escena
        root = FXMLLoader.load(getClass().getResource("/Application/FXML/perfil.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException {
        //Preparar alerta
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
        ButtonType okButton = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);
        alertDialog.lookupButton(okButton).setId("primaryButton");
        alertDialog.lookupButton(cancelButton).setId("secondaryButton");
        
        //Mostrar alerta y procesar respuesta
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get().equals(okButton)) {
            //Actualizar valores de Data
            Data.scene = 0;
            Data.leftDayIndex = 0;
            Data.leftCourt = new Court("Pista 1");
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
    private void showBy(ActionEvent event) throws IOException {
        String s = showBy.getValue();
        
        if(s.equals("Hora")) {
            //Actualizar valores de Data
            Data.scene = 1;
            Data.leftDayIndex = currentDayIndex;
            Data.leftCourt = currentCourt;

            //Cargar escena
            root = FXMLLoader.load(getClass().getResource("/Application/FXML/principal.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void bookAction(ActionEvent event) throws ClubDAOException, IOException {
        Button b = (Button) event.getSource();
        int hourIndex = 0;
        
        for(int i = 0; i < bookButtons.length; i++) {
            if(b.equals(bookButtons[i])) {
                hourIndex = i;
                break;
            }
        }
        
        Court bookedCourt = currentCourt;
        LocalDateTime timeOfBooking = LocalDateTime.of(week[currentDayIndex], LocalTime.of(hourIndex + 9, 0));
        
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
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
            ButtonType okButton = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alertDialog.lookupButton(okButton).setId("primaryButton");

            //Mostrar alerta Label
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
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
            ButtonType okButton = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alertDialog.lookupButton(okButton).setId("primaryButton");

            //Mostrar alerta 
            alert.showAndWait();
            return;
        } 
            
        if(Duration.between(LocalDateTime.now(), timeOfBooking).toMinutes() < 1442) {
            
            //Preparar alerta
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
            ButtonType okButton = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
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
            ButtonType okButton = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alertDialog.lookupButton(okButton).setId("primaryButton");

            //Mostrar alerta 
            alert.showAndWait();
        }
            
        else{
            Booking toRemove = null;
            int courtIndex = Integer.parseInt(court.getName().substring(6)) - 1;
            
            for(int i = 0; i < courtBookings[courtIndex].size(); i++) {  
                Booking b = courtBookings[courtIndex].get(i);
            
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
