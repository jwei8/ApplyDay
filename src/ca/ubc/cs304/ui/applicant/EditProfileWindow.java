package ca.ubc.cs304.ui.applicant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProfileWindow extends JFrame implements ActionListener {
    private final JPanel pane = new JPanel(new GridBagLayout());
    private final GridBagConstraints gbc = new GridBagConstraints();

    private final JButton cancelButton = new JButton("Cancel");
    private final JButton confirmButton = new JButton("Confirm");

    private final JTextField nameField = new JTextField();
    private final JTextField addressField = new JTextField();
    private final JTextField phoneField = new JTextField();
    private final JTextField emailField = new JTextField();

    private final JRadioButton visaYesButton = new JRadioButton("Yes");
    private final JRadioButton visaNoButton = new JRadioButton("No");

    public EditProfileWindow() {
        super("Edit Profile");
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

        JLabel nameLabel = new JLabel("Name: ");
        gbc.ipadx = 20;
        pane.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;
        pane.add(nameField, gbc);

        JLabel addressLabel = new JLabel("Address: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        gbc.ipadx = 20;
        gbc.insets = new Insets(5, 0, 5, 0);
        pane.add(addressLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);
        pane.add(addressField, gbc);

        JLabel phoneLabel = new JLabel("Phone: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.ipadx = 20;
        pane.add(phoneLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;
        pane.add(phoneField, gbc);

        JLabel emailLabel = new JLabel("Email: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.ipadx = 20;
        pane.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;
        pane.add(emailField, gbc);

        JLabel visaLabel = new JLabel("Visa sponsor needed: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.ipadx = 20;
        pane.add(visaLabel, gbc);

        ButtonGroup visaGroup = new ButtonGroup();
        visaGroup.add(visaYesButton);
        visaGroup.add(visaNoButton);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.ipadx = 0;
        pane.add(visaYesButton, gbc);

        gbc.gridy++;
        pane.add(visaNoButton, gbc);

        populateFields();

        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.ipady = 0;
        gbc.insets = new Insets(30, 0, 0, 0);
        pane.add(cancelButton, gbc);

        gbc.gridx = 2;
        pane.add(confirmButton, gbc);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void populateFields() {
        nameField.setText("");
        addressField.setText("");
        phoneField.setText("");
        emailField.setText("");

        visaYesButton.setSelected(true);
        visaNoButton.setSelected(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(cancelButton)) {
            this.dispose();
        } else if (source.equals(confirmButton)) {
            // TODO: validate and update record

            this.dispose();
        }
    }
}
