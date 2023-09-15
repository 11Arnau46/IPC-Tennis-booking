/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Controller;

import java.time.LocalTime;
import model.*;

public class Data {
    public static Member currentUser;
    public static int leftDayIndex = 0;
    public static LocalTime leftHour = LocalTime.of(9, 0);
    public static Court leftCourt = new Court("Pista 1");
    public static boolean firstH = true;
    public static boolean firstC = true;
    public static boolean firstP = true;
    //0 inicio sesion; 1 principalHoras; 2 principalPistas
    public static int scene = 0;
    
    public static Member getCurrentUser() {
        return currentUser;
    }
    
    public static void setCurrentUser(Member m) {
        currentUser = m;
    }
}
