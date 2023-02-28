package ca.ubc.cs304.ui;

import ca.ubc.cs304.model.Education;
import ca.ubc.cs304.ui.applicant.EditEducationWindow;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EducationCard extends JPanel implements ActionListener {
    private final int user;
    private final String degreeName;
    private final String schoolName;

    private final int gpa;

    private final GridBagConstraints gbc = new GridBagConstraints();

    private final JPanel buttonPane = new JPanel();
    private final JButton editButton = new JButton("Edit");
    private final JButton deleteButton = new JButton("Delete");
    
    public EducationCard(Education education) {
        super(new GridBagLayout());
        this.user = education.applicantID;
        this.degreeName = education.degree;
        this.schoolName = education.schoolName;
        this.gpa = education.gpa;

        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 10);

        JLabel degreeLabel = new JLabel(education.major);
        this.add(degreeLabel, gbc);

        JLabel startDate = new JLabel("From: " + education.startDate, SwingConstants.RIGHT);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        this.add(startDate, gbc);

        JLabel major = new JLabel(education.degree);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 10);
        this.add(major, gbc);

        JLabel endDate = new JLabel("To: " + education.endDate, SwingConstants.RIGHT);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        this.add(endDate, gbc);

        JLabel schoolLabel = new JLabel(education.schoolName);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 0 , 0, 10);
        this.add(schoolLabel, gbc);

        JLabel gpa = new JLabel("GPA: " + education.gpa, SwingConstants.RIGHT);
        gbc.gridx = 1;
        gbc.insets = new Insets(20, 10 , 0, 0);
        this.add(gpa, gbc);

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
            EditEducationWindow newEdu = new EditEducationWindow(user, degreeName, schoolName);
            newEdu.showFrame();
        } else if (source.equals(deleteButton)) {

        }
    }
}
