package ca.ubc.cs304.ui.applicant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCoverLetterWindow extends JFrame implements ActionListener {
    private final Object coverLetter;

    private final JPanel pane = new JPanel(new GridBagLayout());
    private final GridBagConstraints gbc = new GridBagConstraints();

    private final JButton cancelButton = new JButton("Cancel");
    private final JButton confirmButton = new JButton("Confirm");

    private final JTextField titleField = new JTextField();
    private final JTextArea contentField = new JTextArea();

    public EditCoverLetterWindow(Object coverLetter /* TODO: replace with cover letter object */) {
        super("Edit Cover Letter");
        this.coverLetter = coverLetter;
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

        JLabel titleLabel = new JLabel("Cover letter title: ");
        pane.add(titleLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 3;
        pane.add(titleField, gbc);

        JLabel contentLabel = new JLabel("Content: ");
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(15, 0, 5, 0);
        pane.add(contentLabel, gbc);

        contentField.setLineWrap(true);
        gbc.gridy++;
        gbc.gridwidth = 3;
        gbc.weighty = 0.8;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 0);
        pane.add(contentField, gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.ipady = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 0, 0, 0);
        pane.add(cancelButton, gbc);

        gbc.gridx = 2;
        pane.add(confirmButton, gbc);

        if (coverLetter != null) {
            // editing existing cover letter instead of creating
            titleField.setText("");
            contentField.setText("");
        }

        this.setSize(500, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(cancelButton)) {
            this.dispose();
        } else if (source.equals(confirmButton)) {
            // TODO: validate and update/insert record

            this.dispose();
        }
    }
}
