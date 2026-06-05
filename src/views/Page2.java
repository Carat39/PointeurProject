package views;

import pointingApplication.Pointing;
import views.utils.Variables;
import mainApplication.management.WorkSchedule;
import java.time.LocalTime;

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

    /**
     * Display tthe pointings of the selected
     * employee on the selected year (through JComboBox)
     * We also display the time and the planned schedule for the employee
     *
     * @param pointings
     * @param year
     * @param employeeName
     * @param extratime
     * @param workSchedule
     */
    public void displayPointings(ArrayList<Pointing> pointings, int year, String employeeName, int extratime, WorkSchedule workSchedule) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-14s %-12s %-12s %-20s%n", "Date", "Arrival", "Departure", "Schedule"));
        sb.append("-".repeat(60)).append("\n");

        int i = 0;
        int shiftsCount = 0;

        while (i < pointings.size()) {
            Pointing p1 = pointings.get(i);
            String arrival = p1.getHour().toString();
            String departure = "missing";

            if (i + 1 < pointings.size() && pointings.get(i + 1).getDate().equals(p1.getDate())) {
                departure = pointings.get(i + 1).getHour().toString();
                i += 2;
            } else {
                i++;
            }

            String schedule = "missing";
            if (workSchedule != null) {
                int dayIndex = WorkSchedule.convertWeekDay(p1.getDate().getDayOfWeek());
                LocalTime schedStart = workSchedule.getSchedule()[dayIndex * 2];
                LocalTime schedEnd   = workSchedule.getSchedule()[dayIndex * 2 + 1];
                if (schedStart != null && schedEnd != null) {
                    schedule = schedStart + " - " + schedEnd;
                }
            }

            sb.append(String.format("%-14s %-12s %-12s %-20s%n",
                    p1.getDate().toString(), arrival, departure, schedule));
            shiftsCount++;
        }

        if (shiftsCount == 0) {
            updateShiftDisplay("No shift found for " + employeeName + " in " + year + ".");
        } else {
            sb.append("-".repeat(60)).append("\n");
            sb.append("Total cumulated extra-time : ").append(extratime).append(" min");
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