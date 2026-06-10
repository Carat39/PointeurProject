package views;

import Time.TimeManagement;
import views.utils.Style;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Confirmation view displayed after a successful check in/out
 * Shows the employee's name, the current date, and the rounded time
 * of the pointing
 */
public class ConfirmView {
    /*** main frame used to display things on it*/
    private JFrame frame;
    /** Button allowing the user to return to the main menu */
    private JButton buttonMenu;

    /**Constructs the ConfirmView and builds the confirmation interface
     * @param currentDate  the date of the pointing
     * @param currentTime  the rounded time of the pointing
     * @param employeeName the full name of the employee who pointed
     */
    public ConfirmView(LocalDate currentDate, LocalTime currentTime, String employeeName) {
        buildUI(currentDate, currentTime, employeeName);
    }

    /**
     * Main method to build the
     * graphical interface of the ConfirmView
     * @param currentDate  the date of the pointing
     * @param currentTime  the rounded time of the pointing
     * @param employeeName the full name of the employee who pointed
     */
    private void buildUI(LocalDate currentDate, LocalTime currentTime, String employeeName) {
        // Frame
        frame = new JFrame("TimeChecker");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Style.pastelPeach);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Label "You have clocked in"
        JLabel labelEnd = new JLabel("You have clocked in");
        labelEnd.setFont(new Font("Serif", Font.BOLD, 30));
        labelEnd.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Name employee
        JLabel labelEmployee = new JLabel(employeeName);
        labelEmployee.setFont(new Font("Serif", Font.BOLD, 25));
        labelEmployee.setForeground(new Color(100, 80, 120));
        labelEmployee.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Date
        JLabel labelDate = new JLabel("on " + TimeManagement.getDate().format(DateTimeFormatter.ofPattern("EEEE d MMMM yyyy", Locale.ENGLISH)));
        labelDate.setFont(new Font("Serif", Font.BOLD, 30));
        labelDate.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Hour
        JLabel labelHour = new JLabel(
                "at " + currentTime.format(DateTimeFormatter.ofPattern("HH:mm"))
        );
        labelHour.setFont(new Font("Serif", Font.BOLD, 30));
        labelHour.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Back button
        buttonMenu = new JButton("Back to menu");
        buttonMenu.setFont(new Font("Serif", Font.BOLD, 30));
        buttonMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonMenu.setPreferredSize(new Dimension(300, 70));
        buttonMenu.setBackground(Style.pastelPurple);
        buttonMenu.setFocusPainted(false);

        // Add in the panel
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(labelEmployee);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(labelEnd);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(labelDate);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(labelHour);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(buttonMenu);

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        frame.setResizable(true);
    }

    // Getter for the controller
    /**@return the menu button*/
    public JButton getButtonMenu() { return buttonMenu; }
    /** close the frame of ConfirmView*/
    public void close() { frame.dispose(); }
}