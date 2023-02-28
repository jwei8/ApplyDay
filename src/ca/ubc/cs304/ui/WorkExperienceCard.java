package ca.ubc.cs304.ui;

import ca.ubc.cs304.model.WorkExperience;
import ca.ubc.cs304.ui.applicant.EditExperienceWindow;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkExperienceCard extends JPanel implements ActionListener {
    private final int user;
    private final String companyName;
    private final String jobTitle;

    private final GridBagConstraints gbc = new GridBagConstraints();

    private final JPanel buttonPane = new JPanel();
    private final JButton editButton = new JButton("Edit");
    private final JButton deleteButton = new JButton("Delete");
    
    public WorkExperienceCard(WorkExperience experience) {
        super(new GridBagLayout());
        this.user = experience.applicantID;
        this.companyName = experience.companyName;
        this.jobTitle = experience.jobTitle;

        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 10);

        JLabel title = new JLabel(experience.jobTitle);
        this.add(title, gbc);

        JLabel startDate = new JLabel("From: " + experience.startDate, SwingConstants.RIGHT);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        this.add(startDate, gbc);

        JLabel companyLabel = new JLabel(experience.companyName);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 10);
        this.add(companyLabel, gbc);

        JLabel endDate = new JLabel("To: " + experience.endDate, SwingConstants.RIGHT);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        this.add(endDate, gbc);

        JTextArea desc = new JTextArea();
        desc.setText(experience.description);
        desc.setLineWrap(true);
        desc.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(20, 0 , 0, 0);
        this.add(desc, gbc);

        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.PAGE_AXIS));

        editButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        editButton.addActionListener(this);
        buttonPane.add(editButton);

        deleteButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        deleteButton.addActionListener(this);
        buttonPane.add(deleteButton);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 0, 0);
        this.add(buttonPane, gbc);
    }

    public void hideButtons() {
        buttonPane.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(editButton)) {
            EditExperienceWindow editExp = new EditExperienceWindow(user, jobTitle, companyName);
            editExp.showFrame();
        } else if (source.equals(deleteButton)) {

        }
    }
}
