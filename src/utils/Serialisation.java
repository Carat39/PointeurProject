import mainApplication.management.*;
import pointingApplication.Pointing;
import Time.TimeManagement;

import java.time.DayOfWeek;
import java.util.Random;

void main() throws Exception {

    java.time.LocalTime[] tab_workSchedule1 = {
            java.time.LocalTime.of(8, 0),  java.time.LocalTime.of(17, 0),
            java.time.LocalTime.of(8, 0),  java.time.LocalTime.of(17, 0),
            java.time.LocalTime.of(8, 0),  java.time.LocalTime.of(17, 0),
            java.time.LocalTime.of(8, 0),  java.time.LocalTime.of(17, 0),
            java.time.LocalTime.of(8, 0),  java.time.LocalTime.of(16, 0),
            java.time.LocalTime.MIN,       java.time.LocalTime.MIN,
            java.time.LocalTime.MIN,       java.time.LocalTime.MIN
    };

    WorkSchedule workSchedule1 = new WorkSchedule(tab_workSchedule1);

    Staff Romain  = new Staff("Romain",  "Blaquart", Departement.WORKERS,        workSchedule1);
    Staff Arthur  = new Staff("Arthur",  "Brajot",   Departement.ADMINISTRATION,  workSchedule1);
    Staff Clement = new Staff("Clément", "Sauger",   Departement.COMMUNICATION,   workSchedule1);
    Staff Nino    = new Staff("Nino",    "Maigret",  Departement.MANAGEMENT,      workSchedule1);
    Staff Michel  = new Staff("Michel",  "Jean",     Departement.MANAGEMENT,      workSchedule1);
    Staff[] theStaff = {Romain, Arthur, Clement, Nino, Michel};

    StaffList theStaffList = new StaffList(theStaff);
    PointingList pointingList = new PointingList();

    Random rand = new Random(42);

    java.time.LocalDate startDate = java.time.LocalDate.of(2024, 1, 1);
    java.time.LocalDate endDate   = java.time.LocalDate.of(2026, 12, 31);

    for (java.time.LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {

        DayOfWeek dow = date.getDayOfWeek();
        if (dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY) continue;

        for (Staff staff : theStaff) {

            if (rand.nextInt(10) == 0) continue;

            int arrivalOffset = rand.nextInt(16) - 5;
            java.time.LocalTime arrival = java.time.LocalTime.of(8, 0).plusMinutes(arrivalOffset);

            boolean overtime = rand.nextInt(10) < 3;
            int overtimeMinutes = overtime ? (30 + rand.nextInt(91)) : 0;
            java.time.LocalTime baseEnd = (dow == DayOfWeek.FRIDAY)
                    ? java.time.LocalTime.of(16, 0)
                    : java.time.LocalTime.of(17, 0);
            java.time.LocalTime departure = baseEnd.plusMinutes(overtimeMinutes);

            Pointing pointingIn  = new Pointing(staff.getuuidStaff(), arrival, date);
            Pointing pointingOut = new Pointing(staff.getuuidStaff(), departure, date);

            staff.addPointing(pointingIn);
            staff.addPointing(pointingOut);

            pointingList.addElem(pointingIn);
            pointingList.addElem(pointingOut);
        }
    }

    pointingList.serialisation("pointingList.ser");
    theStaffList.serialisationStaffList("staffList.ser");

}