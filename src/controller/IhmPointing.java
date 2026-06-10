package controller;

import mainApplication.management.PointingList;
import mainApplication.management.StaffList;
import Time.TimeManagement;
import views.MainView;
import views.ConfirmView;
import views.ExceptView;
import views.InformationView;
import pointingApplication.Pointing;
import reseaux.Client;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Main controller of the pointing application
 * Manages the graphical interface, the connection to the
 * IHM_Manager and the pointing logic for the employees
 */
public class IhmPointing {

    /**
     * MainView of the application
     */
    private MainView mainView;
    /**
     * StaffList containing all the employees data
     * including name, surname... cf StaffList.java
     */
    private StaffList staffList;
    /**
     * List of all pointings recorded
     */
    private PointingList pointingList;
    /**
     * IP address of the manager application
     */
    private String ip;
    /**
     * port number of the manager application
     */
    private int port;

    /**
     * The constructor of the IHM
     * Initializes the main view, loads serialized data,
     * connect ot the IHM manager
     */
    public IhmPointing() {
        mainView = new MainView();

        // Retrieve the serialization data
        loadData();

        // Display the staff list on the screen
        MainView.loadComboox(staffList);
        // Timer 1 second
        Timer timer = new Timer(1000, e -> {
            TimeManagement.setDate();
            TimeManagement.setHour();
            // Apply the calculated date and time and convert them to the correct format
            mainView.setDate(TimeManagement.getDate().format(DateTimeFormatter.ofPattern("EEEE d MMMM yyyy", Locale.ENGLISH)));
            mainView.setHour(TimeManagement.getHour().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            mainView.setRounded("Rounded to the closest 15m: " + TimeManagement.round(TimeManagement.getHour()).format(DateTimeFormatter.ofPattern("HH:mm")));
        });
        timer.start();
        // “Check-in” button to log in to IHM Manager and retrieve staff lists
        mainView.getCheckButtonIP().addActionListener(e-> {
            // retrieve the ip
            String ip = mainView.getTextFieldIp();;
            int port = Integer.parseInt(mainView.getTextFieldPort());
            // Checking if a connection is possible
            if(Client.testerConnexion(ip, port,pointingList)) {
                // Display a label if the connexion is successful
                mainView.changeVisibilitySuccess(true);
                // retrieve the new stafflist
                updateData();
                // Display the new stafflist contains employes
                MainView.updateCombobox(staffList);
            }
            else{
                mainView.changeVisibilitySuccess(false);
            }
        });

        // Bouton Check in/out
        mainView.getCheckButton().addActionListener(e -> {
            // Calculate the current time and date to pass to ConfirmView
            LocalDate currentDate = TimeManagement.getDate();
            LocalTime currentRoundHour = TimeManagement.round(TimeManagement.getHour());
            String selectedEmployee = (String) MainView.getComboEmployees().getSelectedItem();
            // Retrieve the user's name and destination address
            String[] tabString = selectedEmployee.split(" ");
            String surname = tabString[1];
            String firstname = tabString[0];
            String ip = mainView.getTextFieldIp();;
            int port = Integer.parseInt(mainView.getTextFieldPort());

            // Check in Management
            try{
                Pointing.Jepointe(firstname, surname, staffList, pointingList, ip, port);
                staffList.find("Arthur", "Brajot").calcExtraTime();
                //System.out.println(staffList.find("Arthur", "Brajot").getextratime());
                ConfirmView confirmView = new ConfirmView(currentDate, currentRoundHour, selectedEmployee);
                // Back to menu button for Confirmview
                confirmView.getButtonMenu().addActionListener(back -> {
                    confirmView.close();
                });
            } catch (Exception ex) {
                // retrieve the exception message and apply the decision to it
                String msg = ex.getMessage();
                System.out.println(msg);
                if ("You have already pointed 2 times today!!!".equals(msg)) {
                    // Open the corresponding view
                    ExceptView exceptView = new ExceptView(selectedEmployee);
                    System.out.println("You have already pointed 2 times today.");
                    exceptView.getButtonMenu().addActionListener(close -> {
                        exceptView.close();;
                    });
                } else if ("You haven't pointed 2 times yesterday!!!".equals(msg)) {
                    // Open the corresponding view
                    InformationView informationView = new InformationView(selectedEmployee);
                    informationView.getButtonMenu().addActionListener(close ->{
                        InformationView.close();
                    });
                    System.out.println("You haven't pointed correctly yesterday.");
                }
            }

            // sert pendant le dev pour voir l'heure et la personne select
            System.out.println(currentDate.format(DateTimeFormatter.ofPattern("EEEE d MMMM yyyy")));
            System.out.println(currentRoundHour.format(DateTimeFormatter.ofPattern("HH:mm")));
            System.out.println(surname);
            System.out.println(firstname);
            System.out.println(ip);
            System.out.println(port);
        });
    }
    /**
     * Launch the code
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(IhmPointing::new);
    }

    /**
     * Loads persistent data from serialized files.
     * Attempts to deserialize staffList.ser into {@link StaffList}
     * and pointingList.ser into {@link PointingList}.
     */
    private void loadData(){
        // On recupere la liste des staff depuis stafflist.ser
        try {
            // On deserialise la liste
            staffList = StaffList.deserialisationStaffList("staffList.ser");
        } catch (Exception e) {
            // on affiche si il y a un probleme
            System.out.println("Loading error.");
        }
        // on recuperre la liste des pointings effectues
        try {
            pointingList = PointingList.deserialisation("pointingList.ser");
        } catch (Exception e) {
            System.out.println("Pointing list loading error.");
        }
    }
    private void updateData(){
        loadData();
    }

}