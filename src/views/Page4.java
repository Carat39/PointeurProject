package views;

import views.utils.Variables;

import javax.swing.*;
import java.util.ArrayList;

/**
 * VIEW – Page « Employees »
 * we use this page to display the employees,
 * we select one using the jcombobox "employeeList" to
 * see their informations, as in name, surname, department.
 *
 * This page is the "portal" for the "Manage Employees" page.
 */
public class Page4 {

    private final JPanel panel;
    private final JButton closeBtn;
    private final JButton backBtn;
    private final JComboBox<String> employeeList;
    private final JTextArea infoArea;
    private final JButton manageBtn;

    /**
     * Builder of the page
     * @param employees
     */
    public Page4(ArrayList<String> employees) {
        panel = new JPanel(null);
        panel.setBackground(Variables.PASTEL_YELLOW);

        closeBtn = Variables.createCloseBtn();
        backBtn = Variables.createBackBtn();
        employeeList = Variables.createEmployeeList(employees);
        manageBtn = Variables.createNavBtn("Manage Employees", Variables.HEIGHT - 11 * (Variables.HEIGHT / 20));

        infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 13));
        infoArea.setBackground(java.awt.Color.WHITE);
        infoArea.setForeground(java.awt.Color.DARK_GRAY);
        infoArea.setText("Select an employee.");

        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setBounds(Variables.WIDTH / 2 - 300, Variables.HEIGHT / 2 + 50, 600, 200);
        scrollPane.setBorder(BorderFactory.createLineBorder(java.awt.Color.GRAY));

        panel.add(Variables.createTitle("Employees"));
        panel.add(closeBtn);
        panel.add(backBtn);
        panel.add(employeeList);
        panel.add(manageBtn);
        panel.add(scrollPane);
    }

    public void displayEmployee(String name, String surname, String departement, Integer extratime) {
        infoArea.setText(name + " " + surname + " - " + departement + " - " + extratime + "m");
    }

    //getters
    public JPanel getPanel(){return panel;}
    public JButton getCloseBtn(){return closeBtn;}
    public JButton getBackBtn(){return backBtn;}
    public JComboBox<String> getEmployeeList(){return employeeList;}
    public JButton getManageBtn(){return manageBtn;}
}