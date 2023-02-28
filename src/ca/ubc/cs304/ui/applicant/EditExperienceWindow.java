package ca.ubc.cs304.ui.applicant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditExperienceWindow extends JFrame implements ActionListener {
    private final int user;
    private final String jobTitle;
    private final String companyName;

    private final JPanel pane = new JPanel(new GridBagLayout());
    private final GridBagConstraints gbc = new GridBagConstraints();

    private final JButton cancelButton = new JButton("Cancel");
    private final JButton confirmButton = new JButton("Confirm");

    private final JTextField titleField = new JTextField();
    private final JTextField companyField = new JTextField();
    private final JTextField startDateField = new JTextField();
    private final JTextField endDateField = new JTextField();

    private final JTextArea descField = new JTextArea();

    public EditExperienceWindow(int user, String jobTitle, String companyName) {
        super("Edit Work Experience");
        this.user = user;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
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

        JLabel titleLabel = new JLabel("Title: ");
        gbc.ipadx = 20;
        pane.add(titleLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;
        pane.add(titleField, gbc);

        JLabel companyLabel = new JLabel("Company: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.ipadx = 20;
        pane.add(companyLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;
        pane.add(companyField, gbc);

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

        JLabel descLabel = new JLabel("Description: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(5, 0, 5, 0);
        pane.add(descLabel, gbc);

        descField.setLineWrap(true);
        gbc.gridy++;
        gbc.ipady = 30;
        pane.add(descField, gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.ipady = 0;
        gbc.insets = new Insets(30, 0, 0, 0);
        pane.add(cancelButton, gbc);

        gbc.gridx = 2;
        pane.add(confirmButton, gbc);

        if (user != -1 && jobTitle != null && companyName != null)
            populateFields(); // editing existing work experience instead of creating

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void populateFields() {
        titleField.setText("");
        companyField.setText("");
        startDateField.setText("");
        endDateField.setText("");

        descField.setText("");
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
