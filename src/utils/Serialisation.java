import mainApplication.management.*;
import pointingApplication.Pointing;
import Time.TimeManagement;

import java.awt.*;

void main() throws Exception {
    //TimeManagement.roundtest();
    //Pointing.serialisationtest();
    //PointingList.testArrayPointing();
    //StaffList.testStaffList();
    //Staff.testStaffPointing();

    //Test Globale

    LocalTime[] tab_worSchedule1 = {LocalTime.MIN.plusHours(1), LocalTime.MIN.plusHours(8), LocalTime.MIN.plusHours(2),
            LocalTime.MIN.plusHours(9), LocalTime.MIN.plusHours(3), LocalTime.MIN.plusHours(10),
            LocalTime.MIN.plusHours(4), LocalTime.MIN.plusHours(11), LocalTime.MIN.plusHours(5),
            LocalTime.MIN.plusHours(12), LocalTime.MIN.plusHours(6), LocalTime.MIN.plusHours(13),
            LocalTime.MIN.plusHours(7), LocalTime.MIN.plusHours(14)};

    WorkSchedule workSchedule1 = new WorkSchedule(tab_worSchedule1);
    Staff Romain = new Staff("Blaquart", "Romain", Departement.WORKERS, workSchedule1);
    Staff Arthur = new Staff("Brajot", "Arthur", Departement.ADMINISTRATION, workSchedule1);
    Staff Clement = new Staff("Sauger", "Clement", Departement.COMMUNICATION, workSchedule1);
    Staff Nino = new Staff("Maigret", "Nino", Departement.MANAGEMENT, workSchedule1);
    Staff Michel = new Staff("Michel", "Jean", Departement.MANAGEMENT, workSchedule1);
    Staff[] theStaff = {Romain, Arthur, Clement, Nino, Michel};

    StaffList theStaffList = new StaffList(theStaff);

    PointingList pointingList = new PointingList();

    Pointing pointing1 = new Pointing(Arthur.getuuidStaff());
    pointing1.setHour("18:00");
    Pointing pointing2 = new Pointing(Arthur.getuuidStaff());
    Pointing pointing3 = new Pointing(Nino.getuuidStaff());
    Arthur.addPointing(pointing1);
    Arthur.addPointing(pointing2);
    Nino.addPointing(pointing3);


    pointingList.addElem(pointing1);
    pointingList.addElem(pointing2);
    pointingList.addElem(pointing3);
    pointingList.serialisation("pointingList.ser");
    theStaffList.serialisationStaffList("staffList.ser");

}