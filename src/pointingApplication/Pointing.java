package pointingApplication;


import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;

public class Pointing {
    //Attributes
    private int idStaff;
    private LocalTime startHour;
    private LocalTime endHour;
    private LocalDate date;

    //Constructors


    //Methods
    public void serialisation() {

    }

    public void setStartHour(){
        startHour= LocalTime.now();
    }

    public void setEndHour(){
        endHour= LocalTime.now();
    }

    public void setDate(){
        date= LocalDate.now();
    }

    public LocalTime getStartHour(){
        return startHour;
    }

    public LocalTime getEndHour(){
        return endHour;
    }

    public LocalDate getDate(){
        return date;
    }

    public LocalTime round(LocalTime time){
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
}
