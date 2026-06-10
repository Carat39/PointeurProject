import mainApplication.management.*;
import pointingApplication.Pointing;
import Time.TimeManagement;

import java.awt.*;

/**
 * Main create fake data with staff members
 */
void main() throws Exception {
    LocalTime[] tab_worSchedule1 = {LocalTime.MIN.plusHours(8), LocalTime.MIN.plusHours(16),
            LocalTime.MIN.plusHours(10), LocalTime.MIN.plusHours(18),
            LocalTime.MIN.plusHours(12), LocalTime.MIN.plusHours(20),
            LocalTime.MIN.plusHours(14), LocalTime.MIN.plusHours(22),
            LocalTime.MIN.plusHours(6), LocalTime.MIN.plusHours(14),
            LocalTime.MIN.plusHours(8), LocalTime.MIN.plusHours(16),
            LocalTime.MIN.plusHours(10), LocalTime.MIN.plusHours(18)};

    WorkSchedule workSchedule1 = new WorkSchedule(tab_worSchedule1);
    Staff Romain = new Staff("Blaquart", "Romain", Departement.WORKERS, workSchedule1);
    Staff Arthur = new Staff("Brajot", "Arthur", Departement.ADMINISTRATION, workSchedule1);
    Staff Clement = new Staff("Sauger", "Clement", Departement.COMMUNICATION, workSchedule1);
    Staff Nino = new Staff("Maigret", "Nino", Departement.MANAGEMENT, workSchedule1);
    Staff Michel = new Staff("Michel", "Jean", Departement.MANAGEMENT, workSchedule1);
    Staff Cou = new Staff("Cou", "Verture", Departement.ADMINISTRATION, workSchedule1);
    Staff Salle = new Staff("Salle", "DeBain", Departement.ADMINISTRATION, workSchedule1);
    Staff[] theStaff = {Romain, Arthur, Clement, Nino, Michel,Salle};

    StaffList theStaffList = new StaffList(theStaff);

    PointingList pointingList = new PointingList();


    for (int index = 0; index < 735; index++){
        for(Staff staff: theStaff){
            Pointing pointingstart = new Pointing(staff.getuuidStaff(),tab_worSchedule1[index%14],LocalDate.now().minusDays(index));
            Pointing pointingend = new Pointing(staff.getuuidStaff(),tab_worSchedule1[(index+1)%14],LocalDate.now().minusDays(index));
            staff.addPointing(pointingstart);
            staff.addPointing(pointingend);
            pointingList.addElem(pointingstart);
            pointingList.addElem(pointingend);
        }
    }

    //Serialisation
    pointingList.serialisation("pointingList.ser");
    theStaffList.serialisationStaffList("staffList.ser");

}