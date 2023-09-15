/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Application.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import static javafx.geometry.Orientation.HORIZONTAL;
import static javafx.geometry.Orientation.VERTICAL;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Court;
import model.Member;

/**
 * FXML Controller class
 *
 * @author arnau1146
 */
public class reservasController implements Initializable {
     @FXML
    private Label nombreUsuario;
    @FXML
    private VBox ancla;
    
    private GridPane griddy;
    
    private Member currentUser;
    
    Club club;
    
    private List<Booking> reservas;
    
    private Button[] botones;
    private boolean[] botonPresionado;
    @FXML
    private Label mensajito;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
             club = Club.getInstance();
        } catch (ClubDAOException ex) {
             Logger.getLogger(reservasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
             Logger.getLogger(reservasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        currentUser = Data.getCurrentUser();
        String user = currentUser.getNickName();
        nombreUsuario.setText(user);
        reservas = club.getUserBookings(user);

        
        int nmin = Math.min(10, reservas.size());
        String mensa = "Estas son tus últimas " + nmin + " reservas,";
        mensajito.setText(mensa);
        botones = new Button[nmin];
        botonPresionado = new boolean[nmin];
        int iterator = reservas.size()-1;
        for(int i = 0; i < nmin; i++){
            griddy = new GridPane();
            griddy.hgapProperty().setValue(25);
            griddy.alignmentProperty().set(Pos.CENTER);
            addReserva(reservas.get(iterator), i, griddy);
            VBox.setVgrow(griddy, Priority.ALWAYS);
            ancla.getChildren().add(griddy);
            Separator sep = new Separator(HORIZONTAL);
            sep.setId("primaryVSeparator");
            ancla.getChildren().add(sep);
            iterator--;
        }
        
        
        
        
       // double alturaG = griddy.getHeight();
        
       // if(alturaG > 700) {ancla.setMaxHeight(alturaG);}
        //else {ancla.setMaxHeight(700);}
    }    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private void switchToInicio(ActionEvent event) throws IOException {
        switch(Data.scene) {
            case 1:
                root = FXMLLoader.load(getClass().getResource("/Application/FXML/principal.fxml"));
                break;
            case 2:
                root = FXMLLoader.load(getClass().getResource("/Application/FXML/principalPistas.fxml"));
                break;
        }
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
    private void addReserva(Booking reserva, int numero, GridPane griddy){
        for(int i = 0; i<5; i++){
            VBox vcaja = new VBox();
            vcaja.spacingProperty().set(23);
            vcaja.alignmentProperty().set(Pos.TOP_CENTER);
            DateTimeFormatter formatoDate = DateTimeFormatter.ofPattern("dd/MM");
            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
            LocalDate fecha = reserva.getMadeForDay();
            LocalTime hora = reserva.getFromTime();
            LocalDateTime bookingDate = LocalDateTime.of(reserva.getMadeForDay(), reserva.getFromTime());
            String f = fecha.format(formatoDate);
            String h = hora.format(formatoHora);
            Court pista = reserva.getCourt();
            String nombrePista = pista.getName();
            Label titulo = new Label();
            titulo.setId("titulo");
            Label base = new Label();
            base.setId("base");
            Label pagao = new Label();
            if(reserva.getPaid()){pagao.setText("Pago Realizado"); pagao.setId("pagoHecho");}
            else {pagao.setText("Pago Pendiente"); pagao.setId("pagoNoHecho");}
            int numeroPista = Integer.parseInt(nombrePista.substring(nombrePista.length() - 1)) ;
            switch(i){
                case 0:
                    ImageView imagen = new ImageView();
                    Image image = fotoPista(numeroPista);
                    imagen.setImage(image);
                    imagen.setFitHeight(100);
                    imagen.setFitWidth(150);
                    vcaja.getChildren().add(imagen);
                    griddy.addColumn(0, vcaja);
                    griddy.addColumn(1, new Separator(VERTICAL));
                    break;
                case 1:
                    titulo.setText("Día");
                    vcaja.getChildren().add(titulo);
                    base.setText(f);
                    vcaja.getChildren().add(base);
                    griddy.addColumn(2, vcaja);
                    griddy.addColumn(3, new Separator(VERTICAL));
                    break;
                case 2:
                    titulo.setText("Hora");
                    vcaja.getChildren().add(titulo);
                    base.setText(h);
                    vcaja.getChildren().add(base);
                    griddy.addColumn(4, vcaja);
                    griddy.addColumn(5, new Separator(VERTICAL));
                    break;
                case 3:
                    titulo.setText("Pista");
                    vcaja.getChildren().add(titulo);
                    base.setText(String.valueOf(numeroPista));
                    vcaja.getChildren().add(base);
                    griddy.addColumn(6, vcaja);
                    griddy.addColumn(7, new Separator(VERTICAL));
                    break;
                case 4:
                    Button boton = new Button("Anular");
                    botones[numero] = boton;
                    if(Duration.between(LocalDateTime.now(), bookingDate).toMinutes() < 1440) boton.disableProperty().set(true);
                    boton.setOnAction(event -> {
                try {
                    this.cancelarReserva(event);
                } catch (IOException ex) {
                    Logger.getLogger(reservasController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
                    boton.setId("cancelarBoton");
                    vcaja.spacingProperty().set(5);
                    vcaja.paddingProperty().set(new Insets(20, 0, 0, 0));
                    vcaja.getChildren().add(boton);
                    vcaja.getChildren().add(pagao);
                    griddy.addColumn(8, vcaja);
                    break;
            }
            
        }
    }
    
        private Image fotoPista(int num){
            File file = new File("src/Iconos/pistas/pista1.png");
            switch(num){
                case 1:
                    break;
                case 2:
                    file = new File("src/Iconos/pistas/pista2.png");
                    break;
                case 3:
                    file = new File("src/Iconos/pistas/pista3.png");
                    break;
                case 4:
                    file = new File("src/Iconos/pistas/pista4.png");
                    break;    
                case 5:
                    file = new File("src/Iconos/pistas/pista5.png");
                    break;    
                case 6:
                    file = new File("src/Iconos/pistas/pista6.png");
                    break;
            }
            Image foto = new Image(file.toURI().toString());
            return foto;
        }
        
        
        private void cancelarReserva(ActionEvent event) throws IOException {
            
            
            Button b = (Button) event.getSource();
            int ola = 0;
            boolean cancelamos = false;
            for(int i = 0; i < botones.length; i++) {
                if(b.equals(botones[i])) {
                ola = i;
                break;
                }
            }
            Booking reservita = reservas.get(reservas.size()-ola-1);
            LocalDateTime bookingDate = LocalDateTime.of(reservita.getMadeForDay(), reservita.getFromTime());
            if(Duration.between(LocalDateTime.now(), bookingDate).toMinutes() < 1440) {
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
            if(!botonPresionado[ola]){
               cancelamos = confirmarCancelacion();
            }
            if(cancelamos){
                botones[ola].disableProperty().set(true);
                try {
                  club.removeBooking(reservita);
                } catch (ClubDAOException ex) {
                  Logger.getLogger(reservasController.class.getName()).log(Level.SEVERE, null, ex);
                } 
                root = FXMLLoader.load(getClass().getResource("/Application/FXML/reservas.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            
            }
            
        }
        
        private boolean confirmarCancelacion(){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("¿Seguro que quieres cancelar la reserva?");
            alert.setContentText("Para reservar nuevamente deberás\nhacerlo desde la ventana principal.");
        
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
            
            return result.isPresent() && result.get().equals(okButton);
        }

}
