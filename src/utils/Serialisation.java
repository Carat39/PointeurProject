import mainApplication.management.*;
import pointingApplication.Pointing;
import Time.TimeManagement;

import java.awt.*;

void main() throws Exception{
    //TimeManagement.roundtest();
    //Pointing.serialisationtest();
    //PointingList.testArrayPointing();
    //StaffList.testStaffList();
    //Staff.testStaffPointing();

    //Test Globale

    LocalTime[] tab_worSchedule1 = {LocalTime.MIN.plusHours(1),LocalTime.MIN.plusHours(8),LocalTime.MIN.plusHours(2),
            LocalTime.MIN.plusHours(9),LocalTime.MIN.plusHours(3), LocalTime.MIN.plusHours(10),
            LocalTime.MIN.plusHours(4),LocalTime.MIN.plusHours(11),LocalTime.MIN.plusHours(5),
            LocalTime.MIN.plusHours(12),LocalTime.MIN.plusHours(6),LocalTime.MIN.plusHours(13),
            LocalTime.MIN.plusHours(7),LocalTime.MIN.plusHours(14)};

    WorkSchedule workSchedule1 = new WorkSchedule(tab_worSchedule1);
    Staff Romain = new Staff("Romain","Blaquart", Departement.WORKERS,workSchedule1);
    Staff Arthur = new Staff("Arthur","Brajot",Departement.ADMINISTRATION,workSchedule1);
    Staff Clement = new Staff("Cl�ment","Sauger",Departement.COMMUNICATION,workSchedule1);
    Staff Nino = new Staff("Nino","Maigret",Departement.MANAGEMENT,workSchedule1);
    Staff Michel = new Staff("Michel", "Jean", Departement.MANAGEMENT,workSchedule1);
    Staff[] theStaff = {Romain, Arthur,Clement,Nino,Michel};

    StaffList theStaffList = new StaffList(theStaff);

    //StaffList theStaffList = new StaffList();

    PointingList pointingList = new PointingList();

    Pointing pointing1 = new Pointing(Arthur.getuuidStaff());
    Pointing pointing2 = new Pointing(Arthur.getuuidStaff());
    Arthur.addPointing(pointing1);
    Arthur.addPointing(pointing2);


    pointingList.addElem(pointing1);
    pointingList.addElem(pointing2);
    pointingList.serialisation("pointingList.ser");
    theStaffList.serialisationStaffList("staffList.ser");

    //PointingList thePointingList = PointingList.deserialisation("pointingList.ser"); // Sera dans la version final de l'API de Romain!!!
    /*
     * Choses � faire:
     * Je dois a tout pris acceder aux 2 API (surtout celle de Romain pour tester les envoient avec s�rialisation et tout entre les applis)
     * Ensuite, voir pour les doublons possible
     *
     * //theStaffList = StaffList.deserialisationStaffList("staffList.ser"); // Au d�but de l'API de Romain
     * thePointingList.serialisation("pointingList.ser"); //Quand on quitte la pointeuse avec la croix
     * */


    //PointingList thePointingList = new PointingList();
    // Pointing.Jepointe("Blaquart","Romain",theStaffList,thePointingList,"192.168.1.19",1234);

    /*Pointing.Jepointe("Blaquart","Romain",theStaffList);
    try {
        Pointing.Jepointe("Blaquart","Romain",theStaffList);
    }
    catch (Exception e){
        System.out.println("no problem");
    }
    System.out.println("Romain a point� " + Romain.getnbpointage() + " fois depuis qu'il travaille ici");
    Pointing.Jepointe("Maigret","Nino",theStaffList);
    Pointing.Jepointe("Sauger","Cl�ment",theStaffList);

    //Gerer les pairs de tableau dans pointingList
    StaffList theStaffWorkingList = new StaffList();

    //Petits tests pour Arthur
    //Enregistre puis affiche les travailleurs qui n'ont pas point� aujourd'hui
    System.out.println("qui n'a pas embauch�:");
    theStaffWorkingList = theStaffList.saveworkersfilter(0);
    theStaffWorkingList.showAllWorkers();

    //Enregistre puis affiche les travailleurs qui sont en train de travailler
    System.out.println("qui bosse encore:");
    theStaffWorkingList = theStaffList.saveworkersfilter(1);
    theStaffWorkingList.showAllWorkers();
    //Enregistre puis affiche les travailleurs qui ont finit de travailler
    System.out.println("qui a finit:");
    theStaffWorkingList = theStaffList.saveworkersfilter(2);
    theStaffWorkingList.showAllWorkers();
    */


}