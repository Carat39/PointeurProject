package views;

import views.utils.Variables;

import javax.swing.*;
import java.util.ArrayList;

/**
 * VIEW – Page « Clocked in Now »
 * On utilise cette page pour afficher les employés qui sont
 * actuellement entrain de travailler. On sélectionnera l'employé
 * grâce au jcombobox "employeeList"
 */
public class Page3 {

    private final JPanel panel;
    private final JButton closeBtn;
    private final JButton backBtn;
    private final JComboBox<String> employeeList;
    private final JTextArea infoArea;

    public Page3(ArrayList<String> employees) {
        panel = new JPanel(null);
        panel.setBackground(Variables.PASTEL_YELLOW);

        closeBtn = Variables.createCloseBtn();
        backBtn = Variables.createBackBtn();
        employeeList = Variables.createEmployeeList(employees);

        infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 13));
        infoArea.setBackground(java.awt.Color.WHITE);
        infoArea.setForeground(java.awt.Color.DARK_GRAY);
        infoArea.setText("Select an employee.");

        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setBounds(Variables.WIDTH / 2 - 300, Variables.HEIGHT / 2 + 50, 600, 200);
        scrollPane.setBorder(BorderFactory.createLineBorder(java.awt.Color.GRAY));

        panel.add(Variables.createTitle("Clocked in Now"));
        panel.add(closeBtn);
        panel.add(backBtn);
        panel.add(employeeList);
        panel.add(scrollPane);
    }

    public void displayEmployee(String name, String surname, String arrivalHour) {
        infoArea.setText(name + " " + surname + " - " + arrivalHour);
    }

    public JPanel getPanel(){return panel;}
    public JButton getCloseBtn(){return closeBtn;}
    public JButton getBackBtn(){return backBtn;}
    public JComboBox<String> getEmployeeList(){return employeeList;}
}