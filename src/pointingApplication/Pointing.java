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
        if (m%15!=0){
            if((m%15)<8){
                newTime= time.minusMinutes(m%15);
                newTime= newTime.minusSeconds(newTime.getSecond());
                newTime= newTime.minusNanos(newTime.getNano());
            }
            else{
                newTime= time.plusMinutes(15-m%15);
                newTime= newTime.minusSeconds(newTime.getSecond());
                newTime= newTime.minusNanos(newTime.getNano());
            }
        }
        return newTime;
    }
}
