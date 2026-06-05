package views;

import views.utils.Variables;

import javax.swing.*;

/**
 * VIEW – Time Tracker Page
 * This is the first page of the application.
 */
public class Page1 {

    private final JPanel panel;
    private final JButton closeBtn;
    private final JButton checkBtn;
    private final JButton clockedBtn;
    private final JButton employeesBtn;
    private final JButton updateDataBtn;

    /**
     * Builder of the page
     */
    public Page1() {
        panel = new JPanel(null);
        panel.setBackground(Variables.PASTEL_PEACH);

        closeBtn = Variables.createCloseBtn();
        updateDataBtn = Variables.createUpdateBtn();
        checkBtn = Variables.createNavBtn("Saved Work time", Variables.HEIGHT - 7 * (Variables.HEIGHT / 10));
        clockedBtn = Variables.createNavBtn("Clocked in Now", Variables.HEIGHT - 11 * (Variables.HEIGHT / 20));
        employeesBtn = Variables.createNavBtn("Employees", Variables.HEIGHT - 4 * (Variables.HEIGHT / 10));

        panel.add(Variables.createTitle("Time Tracker v1.7"));
        panel.add(closeBtn);
        panel.add(checkBtn);
        panel.add(clockedBtn);
        panel.add(employeesBtn);
        panel.add(updateDataBtn);
    }

    //getters
    public JPanel  getPanel(){return panel;}
    public JButton getCloseBtn(){return closeBtn;}
    public JButton getCheckBtn(){return checkBtn;}
    public JButton getClockedBtn(){return clockedBtn;}
    public JButton getEmployeesBtn(){return employeesBtn;}
    public JButton getUpdateDataBtn(){return updateDataBtn;}
}
