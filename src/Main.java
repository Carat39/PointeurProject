import mainApplication.management.*;
import pointingApplication.Pointing;
import Time.TimeManagement;

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
    Staff Romain = new Staff("Blaquart","Romain", Departement.WORKERS,workSchedule1);
    Staff Arthur = new Staff("Brajot","Arthur",Departement.ADMINISTRATION,workSchedule1);
    Staff Clement = new Staff("Sauger","Clément",Departement.COMMUNICATION,workSchedule1);
    Staff Nino = new Staff("Maigret","Nino",Departement.MANAGEMENT,workSchedule1);
    Staff[] theStaff = {Romain, Arthur,Clement,Nino};

    StaffList theStaffList = new StaffList(theStaff);
    theStaffList.serialisationStaffList("staffList.ser");

    //StaffList theStaffList = new StaffList();


    //PointingList thePointingList = PointingList.deserialisation("pointingList.ser"); // Sera dans la version final de l'API de Romain!!!
    /*
    * Choses à faire:
    * Je dois a tout pris acceder aux 2 API (surtout celle de Romain pour tester les envoient avec sérialisation et tout entre les applis)
    * Ensuite, voir pour les doublons possible
    *
    * //theStaffList = StaffList.deserialisationStaffList("staffList.ser"); // Au début de l'API de Romain
    * thePointingList.serialisation("pointingList.ser"); //Quand on quitte la pointeuse avec la croix
    * */


    PointingList thePointingList = new PointingList();
    Pointing.Jepointe("Blaquart","Romain",theStaffList,thePointingList,"192.168.1.19",1234);

    /*Pointing.Jepointe("Blaquart","Romain",theStaffList);
    try {
        Pointing.Jepointe("Blaquart","Romain",theStaffList);
    }
    catch (Exception e){
        System.out.println("no problem");
    }
    System.out.println("Romain a pointé " + Romain.getnbpointage() + " fois depuis qu'il travaille ici");
    Pointing.Jepointe("Maigret","Nino",theStaffList);
    Pointing.Jepointe("Sauger","Clément",theStaffList);

    //Gerer les pairs de tableau dans pointingList
    StaffList theStaffWorkingList = new StaffList();

    //Petits tests pour Arthur
    //Enregistre puis affiche les travailleurs qui n'ont pas pointé aujourd'hui
    System.out.println("qui n'a pas embauché:");
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
