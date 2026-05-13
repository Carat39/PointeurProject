package Time;


import java.time.LocalDate;
import java.time.LocalTime;

public class TimeManagement {
    //Attributes
    private static LocalTime hour;
    private static LocalDate date;

    //Constructors


    //Methods

    public static void setHour(){ hour= LocalTime.now(); }

    public static void setDate(){ date = LocalDate.now(); }

    public static LocalTime getHour(){ return hour; }

    public static LocalDate getDate(){ return date; }

    public static LocalTime round(LocalTime time){
        int m= time.getMinute();
        LocalTime newTime= time;
        //Si la minute n'est pas déjà arrondie à la quinzaine :
        if (m%15!=0){
            //On doit réduire à la quinzaine la plus proche
            if((m%15)<8){
                newTime= time.minusMinutes(m%15);
                newTime= newTime.minusSeconds(newTime.getSecond());
                newTime= newTime.minusNanos(newTime.getNano());
            }
            //On doit augmenter à la quinzaine la plus proche
            else{
                newTime= time.plusMinutes(15-m%15);
                newTime= newTime.minusSeconds(newTime.getSecond());
                newTime= newTime.minusNanos(newTime.getNano());
            }
        }
        return newTime;
    }

    public static void roundtest(){
        LocalTime heuretest= LocalTime.now();
        System.out.println("Il est : " + heuretest);
        heuretest= TimeManagement.round(heuretest);
        System.out.println("Arrondis à la quinzaine : " + heuretest);
    }
}