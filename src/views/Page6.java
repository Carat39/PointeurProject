package views;

import mainApplication.management.Departement;
import mainApplication.management.Staff;
import views.utils.Variables;

import javax.swing.*;
import java.awt.*;

/**
 * VIEW – Page « Edit Employee »
 * This is the page that allws us to change the pointings
 * of a selected employee.
 * We can create/update/delete a pointing using this class.
 */
public class Page6 {

    private final JPanel panel;
    private final JButton closeBtn;
    private final JButton backBtn;
    private final JButton saveBtn;
    private final JButton deleteBtn;
    private final JButton createBtn;

    private final JTextField nameField;
    private final JTextField pointingdayField;

    private Staff staffRef;

    /**
     * This method create page6
     *
     * Page 6 is the page that manages poointings, basically it
     * can create a new pointer or it can modify one.
     * In a little bit this function will also be able to
     * delete a pointing, but not for now
     * Penser a enlever avant rendu pour juste dire que on a fait la partie delete!
     */
    public Page6() {
        panel = new JPanel(null);
        panel.setBackground(Variables.PASTEL_YELLOW);

        closeBtn = Variables.createCloseBtn();
        backBtn = Variables.createBackBtn();


        Font customFont = new Font("Lucida Handwriting", Font.PLAIN, 14);
        Font labelFont = new Font("Lucida Handwriting", Font.BOLD, 13);

        int centerX = Variables.WIDTH / 2;
        int topY = Variables.HEIGHT / 3;

        JLabel nameLabel = new JLabel("Poiting time : ");
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(Color.DARK_GRAY);

        JLabel pointingdayLabel = new JLabel("Pointing day:");
        pointingdayLabel.setFont(labelFont);
        pointingdayLabel.setForeground(Color.DARK_GRAY);
        

        nameField = new JTextField();
        nameField.setFont(customFont);
        nameField.setBackground(Color.WHITE);

        pointingdayField = new JTextField();
        pointingdayField.setFont(customFont);
        pointingdayField.setBackground(Color.WHITE);


        nameLabel.setBounds(centerX - 180, topY, 130, 30);
        nameField.setBounds(centerX - 30, topY, 210, 30);

        pointingdayLabel.setBounds(centerX - 180, topY + 50, 130, 30);
        pointingdayField.setBounds(centerX - 30, topY + 50, 210, 30);
        


        int buttonsY = topY + 170;
        int btnWidth = 135;
        int btnHeight = 40;

        deleteBtn = new JButton("Delete");
        deleteBtn.setFont(customFont);
        deleteBtn.setBackground(Variables.PASTEL_PEACH);
        deleteBtn.setForeground(Color.DARK_GRAY);
        deleteBtn.setFocusPainted(false);
        deleteBtn.setBounds(centerX - 215, buttonsY, btnWidth, btnHeight);


        saveBtn = new JButton("Update");
        saveBtn.setFont(customFont);
        saveBtn.setBackground(Variables.PASTEL_PEACH);
        saveBtn.setForeground(Color.DARK_GRAY);
        saveBtn.setFocusPainted(false);
        saveBtn.setBounds(centerX - 67, buttonsY, btnWidth, btnHeight);


        createBtn = new JButton("Create");
        createBtn.setFont(customFont);
        createBtn.setBackground(Variables.PASTEL_PEACH);
        createBtn.setForeground(Color.DARK_GRAY);
        createBtn.setFocusPainted(false);
        createBtn.setBounds(centerX + 80, buttonsY, btnWidth, btnHeight);


        panel.add(Variables.createTitle("Manage Pointing"));
        panel.add(closeBtn);
        panel.add(backBtn);
        panel.add(saveBtn);
        panel.add(deleteBtn);
        panel.add(createBtn);

        panel.add(nameLabel);
        panel.add(nameField);
    }


    //getters
    public JPanel getPanel() {
        return panel;
    }

    public JButton getCloseBtn() {
        return closeBtn;
    }

    public JButton getBackBtn() {
        return backBtn;
    }


    public JTextField getNameField() {
        return nameField;
    }

    public Staff getStaffRef() {
        return staffRef;
    }

    public void loadStaff(Staff s) {
        this.staffRef = s;
    }

    public JButton getUpdateBtn() {
        return saveBtn;
    }

    public JButton getDeleteBtn() {
        return deleteBtn;
    }

    public JButton getCreateBtn(){return createBtn;}

    public JTextField getPointingdayField() {
        return pointingdayField;
    }
}