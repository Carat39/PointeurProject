package controller;

import mainApplication.management.Departement;
import mainApplication.management.Staff;
import mainApplication.management.StaffList;
import Time.TimeManagement;
import view.MainView;
import view.ConfirmView;
import view.ExceptView;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class IhmInterface {

    private MainView mainView;

    public IhmInterface() {
        mainView = new MainView();

        // Creation de la liste on verra plus tard pour deserialiser
        StaffList staffList = new StaffList();
        staffList.add(new Staff("Blaquart", "Romain", Departement.WORKERS, null));
        staffList.add(new Staff("Brajot", "Arthur", Departement.ADMINISTRATION, null));
        staffList.add(new Staff("Sauger", "Clement", Departement.COMMUNICATION, null));
        staffList.add(new Staff("Maigret", "Nino", Departement.MANAGEMENT, null));

        // Afficher la liste
        for (Staff s : staffList.getStaffList()) {
            mainView.getComboEmployees().addItem(s.getname() + " " + s.getsurname());
        }

        // Timer 1 seconde
        Timer timer = new Timer(1000, e -> {
            TimeManagement.setDate();
            TimeManagement.setHour();
            // on applique la date et l'heure calcule et on convertit dans le bon format
            mainView.setDate(TimeManagement.getDate().format(DateTimeFormatter.ofPattern("EEEE d MMMM yyyy")));
            mainView.setHour(TimeManagement.getHour().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            mainView.setRounded("Arrondi: " + TimeManagement.round(TimeManagement.getHour()).format(DateTimeFormatter.ofPattern("HH:mm")));
        });
        timer.start();

        // Bouton Check in/out
        mainView.getCheckButton().addActionListener(e -> {
            // calcul de l'heure actuelle et de la date pour fournir a ConfirmView
            LocalDate currentDate = TimeManagement.getDate();
            LocalTime currentRoundHour = TimeManagement.round(TimeManagement.getHour());
            String selectedEmployee = (String) mainView.getComboEmployees().getSelectedItem();

            // Ouvrir la fenêtre de confirmation
            ConfirmView confirmView = new ConfirmView(currentDate, currentRoundHour, selectedEmployee);

            // Bouton retour au menu pour ConfirmView
            confirmView.getButtonMenu().addActionListener(back -> {
                confirmView.close();
            });
            // sert pendant le dev pour voir l'heure et la personne select
            System.out.println(currentDate.format(DateTimeFormatter.ofPattern("EEEE d MMMM yyyy")));
            System.out.println(currentRoundHour.format(DateTimeFormatter.ofPattern("HH:mm")));
        });


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(IhmInterface::new);
    }
}