package mainApplication.management;

import pointingApplication.Pointing;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.UUID;


public class PointingList {
    //Attributes
    private static ArrayList<Pointing> thelist = new ArrayList<Pointing>();

    private static ArrayList<ArrayList<Pointing>> theDoubleList = new ArrayList<ArrayList<Pointing>>();

    //Constructors

    //Methods
    public Pointing getElemList(UUID l_id, LocalDate The_date){
        Pointing the_pointing = null;
        boolean find = false;
        int indice = 0;
        while(indice < thelist.size() && !find){
            the_pointing = thelist.get(indice);
            if (the_pointing.getDate() == The_date){
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

    //Ajoute à partir d'un flux de donnée ou lève une exception après deserialisation
    public void addElem(String filename) throws Exception{
        Pointing pointing = deserialisation(filename);
        thelist.add(pointing);
    }

    //déserialise à partir d'un flux de données
    public Pointing deserialisation(String filename) throws Exception{
        Pointing les_donnees = null;
        try(FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file)
        ) {
            les_donnees = (Pointing) in.readObject();
        }
        return les_donnees;
    }

    public static void testArrayPointing(){
        PointingList TheList = new PointingList();
        for(int indice = 0; indice < 10; indice++){
            Pointing Thepoint1 = new Pointing();
            TheList.addElem(Thepoint1);
        }
        UUID stock = thelist.getFirst().getUuidStaff(); ;
        /*Pointing lemploye = TheList.getElemList(stock, LocalDate.now());
        System.out.println(lemploye.getUuidStaff());
        System.out.println(lemploye.getStartHour());
        System.out.println(lemploye.getEndHour());
        System.out.println(lemploye.getDate());
        */
    }

}
