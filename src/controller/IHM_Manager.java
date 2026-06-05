package controller;

import Time.TimeManagement;
import views.Assembleur_pages;
import utils.temp_serialisation;
import mainApplication.management.Staff;
import mainApplication.management.StaffList;
import mainApplication.management.PointingList;
import pointingApplication.Pointing;
import views.utils.Variables;

import javax.swing.*;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * CONTROLLER
 *
 * It does approximatively everything that is
 * important, it binds the actions to the methods,
 * and handles errors. (hopefully)
 */

public class IHM_Manager {

    private final temp_serialisation model;
    private final Assembleur_pages view;
    private java.util.ArrayList<String> allEmployees;
    private java.util.ArrayList<String> workingEmployees;
    private StaffList allStaff; //pour les recherches sur les données des 'listeners'


    //constructeuur
    public IHM_Manager() {
        model = new temp_serialisation();
        view = new Assembleur_pages();

        loadData();
        System.out.println("Working employees: " + workingEmployees);
        view.build(allEmployees, workingEmployees, allStaff.getAvailableYears());
        bindActions();
    }

    /**
     * Loading Data after deserialisation.
     * Page 2 ~ all of the employees and their pointings ordered by years
     * Page 3 ~ employees currently working, that is to say employees that clocked in once
     * employees can only check in if they are working, so we just need to check that metric.
     */
    private void loadData() {
        try {
            allStaff = StaffList.deserialisationStaffList("staffList.ser");
            StaffList workingNow = allStaff.saveworkersfilter(1);
            allEmployees = allStaff.getStafflist();
            workingEmployees = workingNow.getStafflist();
        } catch (Exception e) {
            e.printStackTrace();
            allStaff = new StaffList();
            allEmployees = new ArrayList<>();
            allEmployees.add("Error trying to load staff list");
            workingEmployees = new ArrayList<>();
            workingEmployees.add("Error trying to load staff list");
        }
    }

    /**This method updates the data in the JComboBox after
    * we modified the employee list, or their pontings.
     * This allows us to be able to check on changes without havinig
     * to reroll the entire IHM application
    * */
    private void updateData(){
        loadData();
        Variables.updateComboBox(view.getPage2().getEmployeeList(), allEmployees);
        Variables.updateComboBox(view.getPage3().getEmployeeList(), workingEmployees);
        Variables.updateComboBox(view.getPage4().getEmployeeList(), allEmployees);
    }

    /**
     * Linking the "listeners" to the methods
     * basically this is making the buttons interaction,
     * e-> what happens when you press the button.
     */
    private void bindActions() {
        // Fermeture
        view.getPage1().getCloseBtn().addActionListener(e -> view.getFrame().dispose());
        view.getPage2().getCloseBtn().addActionListener(e -> view.getFrame().dispose());
        view.getPage3().getCloseBtn().addActionListener(e -> view.getFrame().dispose());
        view.getPage4().getCloseBtn().addActionListener(e -> view.getFrame().dispose());
        view.getPage5().getCloseBtn().addActionListener(e -> view.getFrame().dispose());
        view.getPage6().getCloseBtn().addActionListener(e -> view.getFrame().dispose());

        view.getPage2().getBackBtn().addActionListener(e -> view.getCardLayout().show(view.getCardPanel(), "page1"));
        view.getPage3().getBackBtn().addActionListener(e -> view.getCardLayout().show(view.getCardPanel(), "page1"));
        view.getPage4().getBackBtn().addActionListener(e -> view.getCardLayout().show(view.getCardPanel(), "page1"));
        view.getPage5().getBackBtn().addActionListener(e -> view.getCardLayout().show(view.getCardPanel(), "page4"));
        view.getPage6().getBackBtn().addActionListener(e -> view.getCardLayout().show(view.getCardPanel(), "page2"));

        view.getPage1().getCheckBtn().addActionListener(e -> view.getCardLayout().show(view.getCardPanel(), "page2"));
        view.getPage1().getClockedBtn().addActionListener(e -> view.getCardLayout().show(view.getCardPanel(), "page3"));
        view.getPage1().getEmployeesBtn().addActionListener(e -> view.getCardLayout().show(view.getCardPanel(), "page4"));

        view.getPage2().getManageBtn().addActionListener(e -> goToPage6());
        view.getPage6().getCreateBtn().addActionListener(e -> newPointing());
        view.getPage6().getUpdateBtn().addActionListener(e -> updatePointing());
        view.getPage6().getDeleteBtn().addActionListener(e -> deletePointing());

        view.getPage2().getEmployeeList().addActionListener(e -> refreshShifts());
        view.getPage2().getYearList().addActionListener(e -> refreshShifts());
        view.getPage3().getEmployeeList().addActionListener(e -> refreshEmployee());

        view.getPage4().getEmployeeList().addActionListener(e -> refreshPage4Employee());

        //La fonction ici est particulière car on veut garder l'employé sélectionné en page4 pour poovoir le modifier.
        view.getPage4().getManageBtn().addActionListener(e -> goToPage5());


        view.getPage5().getSaveBtn().addActionListener(e -> saveEmployee());
        view.getPage5().getDeleteBtn().addActionListener(e -> deleteEmployee());
        view.getPage5().getCreateBtn().addActionListener(e -> createFromPage5());

    }

    /**
     * We get back the employee and its year, in order
     * to display its pointing list (his shifts)
     */
    private void refreshShifts() {
        String selectedEmployee = (String) view.getPage2().getEmployeeList().getSelectedItem();
        String selectedYear = (String) view.getPage2().getYearList().getSelectedItem();

        if (selectedEmployee == null || selectedYear == null) return;
        if (selectedEmployee.equals("Error loading staff list")) return;

        String[] parts = selectedEmployee.split(" ", 2);
        if (parts.length < 2) return;

        Staff staff;
        try {
            staff = allStaff.find(parts[0], parts[1]);
        } catch (Exception e) {
            view.getPage2().updateShiftDisplay("Can't find employee");
            return;
        }

        int year = Integer.parseInt(selectedYear);
        view.getPage2().displayPointings(staff.getPointingsByYear(year), year, selectedEmployee, staff.getextratime(), staff.getworkschedule());
    }

    /**
     * This method is used to display the employee
     * correctly based on the current date.
     */
    private void refreshEmployee() {
        String selectedEmployee = (String) view.getPage3().getEmployeeList().getSelectedItem();

        if (selectedEmployee == null) {return;}
        if (selectedEmployee.equals("Error failed to load employee")){return;}

        String[] parts = selectedEmployee.split(" ", 2);
        if (parts.length < 2){return;}

        Staff staff;
        try {
            staff = allStaff.find(parts[0], parts[1]);
        } catch (Exception e) {
            return;
        }

        ArrayList<Pointing> pointings = staff.getPointingList().getthelist();
        String arrivalHour = "";
        for (Pointing p : pointings) {
            if (p.getDate().equals(Time.TimeManagement.getDateNow())) {
                arrivalHour = p.getHour().toString();
                break;
            }
        }

        view.getPage3().displayEmployee(staff.getname(), staff.getsurname(), arrivalHour);
    }

    /**
     * This method is used to display the employee informations,
     * that is to say the name, surname, departement, and extra time.
     */
    private void refreshPage4Employee() {
        String selected = (String) view.getPage4().getEmployeeList().getSelectedItem();
        if (selected == null || selected.equals("Error, failled to load stafflist")){ return;}

        String[] parts = selected.split(" ", 2);
        if (parts.length < 2) return;

        Staff staff = allStaff.find(parts[0], parts[1]);
        view.getPage4().displayEmployee(staff.getname(), staff.getsurname(), staff.getdepartement().toString(), staff.getextratime());
    }

    /**
     * this method is used to save the employee new informations
     * (after being modifed)
     */
    private void saveEmployee() {
        String name = view.getPage5().getNameField().getText().trim();
        String surname = view.getPage5().getSurnameField().getText().trim();

        if (name.isEmpty() || surname.isEmpty()) {
            JOptionPane.showMessageDialog(view.getFrame(), "Name and Surname empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Staff staff = view.getPage5().getStaffRef();
        staff.setname(name);
        staff.setsurname(surname);
        staff.setdepartement((mainApplication.management.Departement)
                view.getPage5().getDeptCombo().getSelectedItem());

        try {
            allStaff.serialisationStaffList("staffList.ser");
            updateData();
            view.getCardLayout().show(view.getCardPanel(), "page4");
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    /**
     * This method can be used to delete the selected employee
     */
    private void deleteEmployee() {
        Staff staff = view.getPage5().getStaffRef();
        allStaff.remove(staff.getuuidStaff());
        try {
            allStaff.serialisationStaffList("staffList.ser");
            updateData();
        } catch (Exception ex) { ex.printStackTrace(); }
        view.getCardLayout().show(view.getCardPanel(), "page4");
    }

    /**
     * This method is used to go to the page 5,
     * and doing so by keeping the employee selected.
     */
    private void goToPage5() {
        String selected = (String) view.getPage4().getEmployeeList().getSelectedItem();
        if (selected == null || selected.equals("Error, failled to load stafflist") || selected.equals("Error loading staff list")) {
            return;
        }
        String[] parts = selected.split(" ", 2);
        if (parts.length < 2) {
            return;
        }
        Staff staff = allStaff.find(parts[0], parts[1]);
        view.getPage5().loadStaff(staff);
        view.getCardLayout().show(view.getCardPanel(), "page5");
    }

    /**
     * This method allows us to create an employee
     * We can note that it is strange that we have to go
     * through an employee to create a new one, but this
     * was the best way to do it without making a very heavy
     * and hard to navigate interface
     */
    private void createFromPage5() {
        String name = view.getPage5().getNameField().getText().trim();
        String surname = view.getPage5().getSurnameField().getText().trim();

        if (name.isEmpty() || surname.isEmpty()) {
            JOptionPane.showMessageDialog(view.getFrame(), "name and surname empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        mainApplication.management.Departement dept = (mainApplication.management.Departement)
                view.getPage5().getDeptCombo().getSelectedItem();

        Staff newStaff = new Staff(name, surname, dept, null);
        allStaff.add(newStaff);
        try {
            allStaff.serialisationStaffList("staffList.ser");
            updateData();
            view.getCardLayout().show(view.getCardPanel(), "page4");
            JOptionPane.showMessageDialog(view.getFrame(), "Created new employee successfully");
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    /**
     * This method is used to go to the page 6,
     * and doing so by keeping the employee selected.
     */
    private void goToPage6() {
        String selected = (String) view.getPage2().getEmployeeList().getSelectedItem();
        if (selected == null) return;
        String[] parts = selected.split(" ", 2);
        if (parts.length < 2) return;
        Staff staff = allStaff.find(parts[0], parts[1]);
        view.getPage6().loadStaff(staff);
        view.getCardLayout().show(view.getCardPanel(), "page6");
    }

    /**
     * This method is used to add a new pointing to the employee
     */
    private void newPointing() {
        Staff staff = view.getPage6().getStaffRef();
        if (staff.condPointingADay(2, 0)) {
            JOptionPane.showMessageDialog(view.getFrame(), "Already pointed 2 times today!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String hourText = view.getPage6().getNameField().getText().trim();
        try {
            LocalTime rounded = TimeManagement.round(LocalTime.parse(hourText));
            Pointing p = new Pointing(staff.getuuidStaff(), rounded, TimeManagement.getDateNow());
            staff.addPointing(p);
            allStaff.serialisationStaffList("staffList.ser");
            updateData();
            JOptionPane.showMessageDialog(view.getFrame(), "Pointing added at " + rounded);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view.getFrame(), "Invalid time format (HH:mm)", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is used to update the last pointing of the employee
     */
    private void updatePointing() {
        Staff staff = view.getPage6().getStaffRef();
        if (staff.condPointingADay(0, 0)) {
            JOptionPane.showMessageDialog(view.getFrame(), "No pointing today to update!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String hourText = view.getPage6().getNameField().getText().trim();
        try {
            LocalTime rounded = TimeManagement.round(LocalTime.parse(hourText));
            staff.getPointingList().removeLast();
            Pointing p = new Pointing(staff.getuuidStaff(), rounded, TimeManagement.getDateNow());
            staff.addPointing(p);
            allStaff.serialisationStaffList("staffList.ser");
            updateData();
            JOptionPane.showMessageDialog(view.getFrame(), "Pointing updated at " + rounded);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view.getFrame(), "Invalid time format (HH:mm)", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method delete the last pointing of the selected employee
     */
    private void deletePointing() {
        Staff staff = view.getPage6().getStaffRef();
        if (staff.condPointingADay(0, 0)) {
            JOptionPane.showMessageDialog(view.getFrame(), "No pointing today to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            staff.getPointingList().removeLast();
            allStaff.serialisationStaffList("staffList.ser");
            updateData();
            JOptionPane.showMessageDialog(view.getFrame(), "Last pointing deleted successfully");
        } catch (Exception ex) {
            ex.getMessage();
        }
    }


    /**
     * Lance le code
     * @param args
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(IHM_Manager::new);
    }
}