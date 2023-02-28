package ca.ubc.cs304.ui.applicant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditEducationWindow extends JFrame implements ActionListener {
    private final int user;
    private final String degreeName;
    private final String schoolName;

    private final JPanel pane = new JPanel(new GridBagLayout());
    private final GridBagConstraints gbc = new GridBagConstraints();

    private final JButton cancelButton = new JButton("Cancel");
    private final JButton confirmButton = new JButton("Confirm");

    private final JTextField degreeField = new JTextField();
    private final JTextField majorField = new JTextField();
    private final JTextField schoolField = new JTextField();
    private final JTextField startDateField = new JTextField();
    private final JTextField endDateField = new JTextField();
    private final JTextField gpaField = new JTextField();

    public EditEducationWindow(int user, String degreeName, String schoolName) {
        super("Edit Education");
        this.user = user;
        this.degreeName = degreeName;
        this.schoolName = schoolName;
    }

    public void showFrame(/* TODO: delegate */) {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(pane);

        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        cancelButton.addActionListener(this);
        confirmButton.addActionListener(this);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel degreeLabel = new JLabel("Degree: ");
        gbc.ipadx = 20;
        pane.add(degreeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;
        pane.add(degreeField, gbc);

        JLabel majorLabel = new JLabel("Major: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.ipadx = 20;
        pane.add(majorLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;
        pane.add(majorField, gbc);

        JLabel schoolLabel = new JLabel("School: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.ipadx = 20;
        pane.add(schoolLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;
        pane.add(schoolField, gbc);

        JLabel startDateLabel = new JLabel("Start date: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.ipadx = 20;
        pane.add(startDateLabel, gbc);

        gbc.gridx = 2;
        gbc.ipadx = 0;
        pane.add(startDateField, gbc);

        JLabel endDateLabel = new JLabel("End date: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.ipadx = 20;
        pane.add(endDateLabel, gbc);

        gbc.gridx = 2;
        gbc.ipadx = 0;
        pane.add(endDateField, gbc);

        JLabel gpaLabel = new JLabel("GPA: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.ipadx = 20;
        pane.add(gpaLabel, gbc);

        gbc.gridx = 2;
        gbc.ipadx = 0;
        pane.add(gpaField, gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.ipady = 0;
        gbc.insets = new Insets(30, 0, 0, 0);
        pane.add(cancelButton, gbc);

        gbc.gridx = 2;
        pane.add(confirmButton, gbc);

        if (user != -1 && degreeName != null && schoolName != null)
            populateFields(); // editing existing work experience instead of creating

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void populateFields() {
        degreeField.setText("");
        majorField.setText("");
        schoolField.setText("");
        startDateField.setText("");
        endDateField.setText("");
        gpaField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(cancelButton)) {
            this.dispose();
        } else if (source.equals(confirmButton)) {

        }
    }
}
