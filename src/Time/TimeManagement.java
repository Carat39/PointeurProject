package Time;

import mainApplication.management.WorkSchedule;
import pointingApplication.Pointing;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This class manages a date and time (from the java.time package).
 * It contains a method to round the time to the nearest two weeks;
 * this method is called each time a time entry is created.
 *
 * @author Les Scribes de Broadway
 */
public class TimeManagement {
    //Attributes
    private static LocalTime hour;
    private static LocalDate date;

    //Constructors
    /**
     * This constructor initializes the current time and date,
     * rounding the time in the process.
     */
    public TimeManagement(){
        hour= round(LocalTime.now());
        date = LocalDate.now();
    }

    //Methods

    /**
     * Modifies the time attribute by assigning it the current time.
     */
    public static void setHour(){ hour= LocalTime.now(); }

    /**
     * Modifies the date attribute by assigning it the current date.
     */
    public static void setDate(){ date = LocalDate.now(); }

    /**
     * Returns the attribut hour.
     *
     * @return hour.
     */
    public static LocalTime getHour(){ return hour; }

    /**
     * Returns the attribut date.
     *
     * @return date.
     */
    public static LocalDate getDate(){ return date; }

    /**
     * Returns the actual hour.
     *
     * @return real time hour.
     */
    public static LocalTime getHourNow(){ return round(LocalTime.now()); }

    /**
     * Returns the actual date.
     *
     * @return real time date.z
     */
    public static LocalDate getDateNow(){ return LocalDate.now(); }


    /**
     * Round the time spent in the parameter to the nearest fortnight (+ or - 15 minutes).
     *
     * @param time The time to around.
     */
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

    /**
     * Test method on a time to around.
     */
    public static void roundtest(){
        LocalTime heuretest= LocalTime.now();
        System.out.println("Il est : " + heuretest);
        heuretest= TimeManagement.round(heuretest);
        System.out.println("Arrondis à la quinzaine : " + heuretest);
    }
}
