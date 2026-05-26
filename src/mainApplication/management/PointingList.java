package mainApplication.management;

import pointingApplication.Pointing;

import java.awt.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.UUID;


public class PointingList {
    //Attributes
    //Ne pas les mettre EN STATIC QUEL ERREUR! sinon tout le monde partage les pointages. On ne veut qu'un pointingList général
    private ArrayList<Pointing> thelist = new ArrayList<Pointing>();


    //Constructors

    //Methods
    public ArrayList<Pointing> getthelist(){return thelist;}

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
        return the_pointing;
    }

    //A-t-il pointé cond times à la date donnée ?
    public boolean pointingCondTimes(UUID l_id, LocalDate The_date, int cond){
        Pointing the_pointing = null;
        LocalTime Hourprec = LocalTime.MIN;
        boolean condPointing = true;
        int comptage = 0;
        int indice = 0;
        while(indice < thelist.size() && condPointing){
            the_pointing = thelist.get(indice);
            if (the_pointing.getHour().equals(Hourprec)){
                condPointing = false;
            }
            if (the_pointing.getDate().equals(The_date)){
                Hourprec = the_pointing.getHour();
                comptage++;
                // Il y a eu 2 pointage ou 1 pointage alors qu'on voulait observer qu'il n'y avait pas de pointage justement
                if (comptage == 2 || cond == 0){
                    condPointing = false;
                }
            }
            indice ++;
        }
        return comptage == cond;
    }


    public void addElem(Pointing pointing){
        // Tester exception on sait jamais
        thelist.add(pointing);
    }

    //Ajoute à partir d'un flux de donnée ou lève une exception après deserialisation
    public void addElem(String filename) throws Exception{
        Pointing pointing = new Pointing();
        pointing = deserialisation(filename);
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

    public void testArrayPointing(){
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
