package mainApplication.management;

import pointingApplication.Pointing;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.UUID;


public class PointingList {
    //Attributes
    private static ArrayList<Pointing> thelist = new ArrayList<Pointing>();

    //Constructors

    //Methods
    public Pointing getElemList(UUID l_id, LocalDate The_date){
        Pointing the_pointing = null;
        boolean find = false;
        int indice = 0;
        while(indice < thelist.size() && !find){
            the_pointing = thelist.get(indice);
            if (the_pointing.getUuidStaff() == l_id && the_pointing.getDate() == The_date){
                find = true;
            }
            else{
                indice ++;
            }
        }
        return thelist.get(indice);
    }

    public void addElem(Pointing pointing){
        // Tester exception on sait jamais
        thelist.add(pointing);
    }

    public static void testArrayPointing(){
        PointingList TheList = new PointingList();
        for(int indice = 0; indice < 10; indice++){
            Pointing Thepoint1 = new Pointing();
            TheList.addElem(Thepoint1);
        }
        UUID stock = thelist.getFirst().getUuidStaff(); ;
        Pointing lemploye = TheList.getElemList(stock, LocalDate.now());
        System.out.println(lemploye.getUuidStaff());
        System.out.println(lemploye.getStartHour());
        System.out.println(lemploye.getEndHour());
        System.out.println(lemploye.getDate());

    }

}
