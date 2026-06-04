package controller;

import mainApplication.management.PointingList;
import mainApplication.management.Staff;
import mainApplication.management.StaffList;
import Time.TimeManagement;
import views.MainView;
import views.ConfirmView;
import views.ExceptView;
import views.InformationView;
import pointingApplication.Pointing;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class IHM_Pointing {

    private MainView mainView;
    private StaffList staffList;
    private PointingList pointingList;
    public IHM_Pointing() {
        mainView = new MainView();

        // On recupere la liste des staff depuis stafflist.ser
        staffList = new StaffList();
        try {
            // On deserialise la liste
            staffList = StaffList.deserialisationStaffList("staffList.ser");
        } catch (Exception e) {
            // on affiche si il y a un probleme
            System.out.println("Erreur de chargement.");
        }
        // on recuperre la liste des pointings effectués
        try {
            pointingList = PointingList.deserialisation("pointingList.ser");
        } catch (Exception e) {
            System.out.println("Erreur chargement pointings.");
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
        // Bouton chekcIp pour se connecter à IHM manager et recuperer els stafflist
        mainView.getCheckButtonIP().addActionListener(e-> {
            // on affiche un label si on est bien connnecté
            mainView.changeVisibilitySuccess();
        });

        // Bouton Check in/out
        mainView.getCheckButton().addActionListener(e -> {
            // calcul de l'heure actuelle et de la date pour fournir a ConfirmView
            LocalDate currentDate = TimeManagement.getDate();
            LocalTime currentRoundHour = TimeManagement.round(TimeManagement.getHour());
            String selectedEmployee = (String) mainView.getComboEmployees().getSelectedItem();
            // Extraction du nom de l'utilisateur et l'adresse de destination
            String[] tabString = selectedEmployee.split(" ");
            String surname = tabString[1];
            String firstname = tabString[0];
            System.out.println("surname"+surname);
            System.out.println(selectedEmployee);
            String ip = mainView.getTextFieldIp();;
            int port = Integer.parseInt(mainView.getTextFieldPort());

            // Gestion du pointage
            try{
                Pointing.Jepointe(firstname, surname, staffList, pointingList, ip, port);
                ConfirmView confirmView = new ConfirmView(currentDate, currentRoundHour, selectedEmployee);
                // Bouton retour au menu pour ConfirmView
                confirmView.getButtonMenu().addActionListener(back -> {
                    confirmView.close();
                });
            } catch (Exception ex) {
                // on recupere le message de l'exxception et on y applique la decision
                String msg = ex.getMessage();
                System.out.println(msg);
                if ("You have already pointed 2 times today!!!".equals(msg)) {
                    // on lance la fenetre correspondante
                    ExceptView exceptView = new ExceptView(selectedEmployee);
                    System.out.println("Vous avez déjà pointé 2 fois aujourd'hui");
                    exceptView.getButtonMenu().addActionListener(close -> {
                        exceptView.close();;
                    });
                } else if ("You haven't pointed 2 times yesterday!!!".equals(msg)) {
                    // on lance la fenetre correspondante
                    InformationView informationView = new InformationView(selectedEmployee);
                    informationView.getButtonMenu().addActionListener(close ->{
                        InformationView.close();
                    });
                    System.out.println("Vous n'avez pas pointé correctement hier");
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
    public static void main(String[] args) {
        SwingUtilities.invokeLater(IHM_Pointing::new);
    }


    // il faut pour la fin faire en sorte de tester la connection avec l'ihm manager
    // si la connexion est bonne envoyer les pointings qui sont en attente puis envoyer le pointing
    // realisé par l'utilisateur
    // sinon enregistrer le pointing dans un fichier serialiable qui sera essayer à la prochaine
    // connexion
}