package controller;

import mainApplication.management.Departement;
import mainApplication.management.Staff;
import mainApplication.management.StaffList;
import Time.TimeManagement;
import view.MainView;
import view.ConfirmView;
import view.ExceptView;
import pointingApplication.Pointing;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static pointingApplication.Pointing.deserialisation;

public class IhmInterface {

    private MainView mainView;

    public IhmInterface() {
        mainView = new MainView();

        // On recupere la liste des staff depuis stafflist.ser
        StaffList staffList = new StaffList();
        try {
            // On deserialise la liste
            staffList = StaffList.deserialisationStaffList("stafflist.ser");
        } catch (Exception e) {
            // on affiche si il y a un probleme
            System.out.println("Erreur de chargement.");
        }
        // On rajoute à l'écran la liste de staff
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
            // recuperation du port et de l'ip
            String ip = mainView.getTextFieldIp();;
            String port = mainView.getTextFieldPort();

            // Ouvrir la fenêtre de confirmation
            ConfirmView confirmView = new ConfirmView(currentDate, currentRoundHour, selectedEmployee);

            // Bouton retour au menu pour ConfirmView
            confirmView.getButtonMenu().addActionListener(back -> {
                confirmView.close();
            });

            // Gestion du pointage
            String[] tabString = selectedEmployee.split(" ");
            String surname = tabString[0];
            String firstname = tabString[1];

            // sert pendant le dev pour voir l'heure et la personne select
            System.out.println(currentDate.format(DateTimeFormatter.ofPattern("EEEE d MMMM yyyy")));
            System.out.println(currentRoundHour.format(DateTimeFormatter.ofPattern("HH:mm")));
            System.out.println(selectedEmployee);
            System.out.println(ip);
            System.out.println(port);
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(IhmInterface::new);
    }
}