/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Application.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import static java.time.LocalTime.MIDNIGHT;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Booking;
import model.Club;
import model.ClubDAOException;

/**
 * FXML Controller class
 *
 * @author Carles
 */
public class tablaPistasController implements Initializable {

    @FXML
    private Label dia;
    @FXML
    private GridPane griddy;
    
    private int numHoras, numPistas;
    
    private Label[][] labels;
    
    private LocalDate diaDeHoy;
    
    Club club;
    
    private List<Booking> reservas;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            club = Club.getInstance();
        } catch (ClubDAOException ex) {
            Logger.getLogger(tablaPistasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(tablaPistasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM");  
        diaDeHoy = LocalDate.now();
        dia.setText("Hoy " + dtf.format(diaDeHoy));
        
        numHoras = griddy.getRowCount();
        numPistas = griddy.getColumnCount();
        labels = new Label[numHoras][numPistas];
        for(int row = 1; row < numHoras; row++){
            for(int col = 1; col < numPistas; col++){
               Label pal = new Label("Libre");
               pal.setId("pistaLibre");
               HBox box = new HBox();
               labels[row-1][col-1] = pal;
               box.getChildren().add(pal);
               box.alignmentProperty().set(Pos.CENTER);
               griddy.add(box, col, row);
           }
      } 
      for (int npista = 1; npista < numPistas; npista++){
          horasReservadas(npista-1);
      }
    }    
    
    private void horasReservadas(int pista){
        int truePista = pista+1;
        String nombrePista = "Pista " + truePista;
        String pistaOcupada = "pistaOcupada";
        reservas = club.getCourtBookings(nombrePista, diaDeHoy);
        for(int reserva = 0; reserva < reservas.size(); reserva++){
            String elMan = reservas.get(reserva).getMember().getNickName();
            switch(reservas.get(reserva).getFromTime().getHour()){
                case 9:
                    labels[0][pista].setText(elMan);
                    labels[0][pista].setId(pistaOcupada);
                    break;
                case 10:
                    labels[1][pista].setText(elMan);
                    labels[1][pista].setId(pistaOcupada);
                    break;
                case 11:
                    labels[2][pista].setText(elMan);
                    labels[2][pista].setId(pistaOcupada);
                    break;
                case 12:
                    labels[3][pista].setText(elMan);
                    labels[3][pista].setId(pistaOcupada);
                    break;
                case 13:
                    labels[4][pista].setText(elMan);
                    labels[4][pista].setId(pistaOcupada);
                    break;
                case 14:
                    labels[5][pista].setText(elMan);
                    labels[5][pista].setId(pistaOcupada);
                    break;
                case 15:
                    labels[6][pista].setText(elMan);
                    labels[6][pista].setId(pistaOcupada);
                    break;
                case 16:
                    labels[7][pista].setText(elMan);
                    labels[7][pista].setId(pistaOcupada);
                    break;
                case 17:
                    labels[8][pista].setText(elMan);
                    labels[8][pista].setId(pistaOcupada);
                    break;
                case 18:
                    labels[9][pista].setText(elMan);
                    labels[9][pista].setId(pistaOcupada);
                    break;
                case 19:
                    labels[10][pista].setText(elMan);
                    labels[10][pista].setId(pistaOcupada);
                    break;
                case 20:
                    labels[11][pista].setText(elMan);
                    labels[11][pista].setId(pistaOcupada);
                    break;
                case 21:
                    labels[12][pista].setText(elMan);
                    labels[12][pista].setId(pistaOcupada);
                    break;
            }
        }
    } 
    
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private void switchToInicio(ActionEvent event) throws IOException{
        switch(Data.scene){
            case 0:
                root = FXMLLoader.load(getClass().getResource("/Application/FXML/inicioSesionEscena.fxml"));
                break;
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
    
}
