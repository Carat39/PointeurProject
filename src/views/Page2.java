package views;

import pointingApplication.Pointing;
import views.utils.Variables;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * VIEW – Page « Saved work time »
 * Showing off the people and the years via two jcombobox,
 * which will allow the IHM_Manager to display the shifts
 * of the selected employee on the selected year.
 */
public class Page2 {

    private final JPanel panel;
    private final JButton closeBtn;
    private final JButton backBtn;
    private final JComboBox<String> employeeList;
    private final JComboBox<String> yearList;
    private final JTextArea shiftArea;
    private final JButton manageBtn;

    /**
     * Builder of the page
     * @param employees
     * @param years
     */
    public Page2(ArrayList<String> employees, ArrayList<String> years) {
        panel = new JPanel(null);
        panel.setBackground(Variables.PASTEL_YELLOW);

        closeBtn = Variables.createCloseBtn();
        backBtn = Variables.createBackBtn();
        employeeList = Variables.createEmployeeList(employees);
        yearList = Variables.createYearList(years);
        manageBtn = Variables.createNavBtn("Manage Pointing", Variables.HEIGHT - 10 * (Variables.HEIGHT / 20));

        shiftArea = new JTextArea();
        shiftArea.setEditable(false);
        shiftArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 13));
        shiftArea.setBackground(Color.WHITE);
        shiftArea.setForeground(Color.DARK_GRAY);
        shiftArea.setText("Select an employee and a year.");

        JScrollPane scrollPane = new JScrollPane(shiftArea);
        scrollPane.setBounds(Variables.WIDTH / 2 - 300,  Variables.HEIGHT / 2 + 50, 600, 200);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        panel.add(Variables.createTitle("Saved work time"));
        panel.add(closeBtn);
        panel.add(backBtn);
        panel.add(employeeList);
        panel.add(yearList);
        panel.add(scrollPane);
        panel.add(manageBtn);
    }

    public void updateShiftDisplay(String content) {
        shiftArea.setText(content);
        shiftArea.setCaretPosition(0);
    }

    public void displayPointings(ArrayList<Pointing> pointings, int year, String employeeName, int extratime) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-14s %-12s %-12s%n", "Date", "Arrival", "Departure"));
        sb.append("-".repeat(40)).append("\n");

        int i = 0;
        int shiftsCount = 0;

        while (i < pointings.size()) {
            Pointing p1 = pointings.get(i);
            if (i + 1 < pointings.size() && pointings.get(i + 1).getDate().equals(p1.getDate())) {
                Pointing p2 = pointings.get(i + 1);
                sb.append(String.format("%-14s %-12s %-12s%n", p1.getDate().toString(), p1.getHour().toString(), p2.getHour().toString()));
                shiftsCount++;
                i += 2;
            } else {
                sb.append(String.format("%-14s %-12s %-12s%n", p1.getDate().toString(), p1.getHour().toString(), "(missing)"));
                shiftsCount++;
                i++;
            }
        }

        if (shiftsCount == 0) {
            updateShiftDisplay("No shift found for " + employeeName + " in " + year + ".");
        } else {
            sb.append("-".repeat(40)).append("\n");
            sb.append("Total cumulated extra-time ").append(extratime).append(" min");
            updateShiftDisplay(sb.toString());
        }
    }

    //getters
    public JPanel getPanel(){return panel;}
    public JButton getCloseBtn(){return closeBtn;}
    public JButton getBackBtn(){return backBtn;}
    public JComboBox<String>getEmployeeList(){return employeeList;}
    public JComboBox<String>getYearList(){return yearList;}
    public JButton getManageBtn(){return manageBtn;}
}