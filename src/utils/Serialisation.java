import mainApplication.management.*;
import pointingApplication.Pointing;
import java.time.LocalTime;


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

    StaffList theStaffList = new StaffList();
    theStaffList.add(Romain);
    theStaffList.add(Arthur);
    theStaffList.add(Clement);
    theStaffList.add(Nino);

    PointingList thePointingList = new PointingList();
    //Petit test pour Romain
    Pointing.Jepointe("Blaquart","Romain",theStaffList);
    Pointing.Jepointe("Blaquart","Romain",theStaffList);
    try {
        //Oh non, Romain t'as déjà pointé 2 fois à l'école primaire ce matin t'as épuisé ton quota, faut aller voir le staff
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

    //Sauvegarde de la liste du personnel pour l'IHM
    theStaffList.serialisationStaffList("donnees.ser");
}