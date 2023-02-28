package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.WorkDayDelegate;
import ca.ubc.cs304.model.Applicant;
import ca.ubc.cs304.model.Application;
import ca.ubc.cs304.model.Education;
import ca.ubc.cs304.model.WorkExperience;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;

public class ApplicationWindow extends JFrame {
    private final JPanel pane = new JPanel(new GridBagLayout());
    private final GridBagConstraints gbc = new GridBagConstraints();

    public int applicationID;

    public ArrayList<WorkExperience> experiences;

    public ArrayList<Education> educations;

    public WorkDayDelegate delegate;

    public ApplicationWindow(int applicationID) {
        super("View Application");
        this.applicationID = applicationID;
    }

    public void showFrame(WorkDayDelegate delegate) {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.delegate = delegate;

        Application application = delegate.selectApplicationByApplicationID(applicationID);

        Applicant applicant = delegate.selectApplicantByApplicantID(application.applicantID);

        experiences = delegate.selectWorkExperienceByApplicantID(application.applicantID);

        educations = delegate.selectEducationByApplicantID(application.applicantID);

        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.setContentPane(scrollPane);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 10);

        // TODO: using delegate, populate application info here
        JLabel name = new JLabel(applicant.name);
        name.setFont(name.getFont().deriveFont(16f));
        pane.add(name, gbc);

        JLabel referral = new JLabel("referralID: " + application.referralID, SwingConstants.RIGHT);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        pane.add(referral, gbc);

        JLabel email = new JLabel(applicant.email);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 10);
        pane.add(email, gbc);

        JLabel status = new JLabel("Needs review/Accepted/Rejected");
        gbc.gridy = 2;
        pane.add(status, gbc);

        JLabel experienceLabel = new JLabel("Work Experience");
        experienceLabel.setFont(experienceLabel.getFont().deriveFont(16f));
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 0, 0);
        pane.add(experienceLabel, gbc);

        JPanel experience = addWorkExperience();
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 0);
        pane.add(experience, gbc);

        JLabel educationLabel = new JLabel("Education");
        educationLabel.setFont(educationLabel.getFont().deriveFont(16f));
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 0, 0, 0);
        pane.add(educationLabel, gbc);

        JPanel education = addEducation();
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 0);
        pane.add(education, gbc);

        JLabel coverLetterLabel = new JLabel("Cover Letter");
        coverLetterLabel.setFont(coverLetterLabel.getFont().deriveFont(16f));
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 0, 0, 0);
        pane.add(coverLetterLabel, gbc);

        JTextArea coverLetter = new JTextArea();
        coverLetter.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt "
                + "ut labore et dolore magna aliqua. Porta lorem mollis aliquam ut porttitor leo. Elementum pulvinar "
                + "etiam non quam lacus suspendisse faucibus interdum posuere. Convallis a cras semper auctor neque. "
                + "Vel elit scelerisque mauris pellentesque pulvinar pellentesque. Orci eu lobortis elementum nibh "
                + "tellus molestie nunc non blandit. Nisi quis eleifend quam adipiscing vitae. Habitant morbi tristique "
                + "senectus et. Vulputate sapien nec sagittis aliquam malesuada bibendum. Eros in cursus turpis mass.");
        coverLetter.setLineWrap(true);
        coverLetter.setEditable(false);

        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 0, 0);
        pane.add(coverLetter, gbc);

        this.setSize(500, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JPanel addWorkExperience() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5, 10, 5, 10);
        c.gridx = 0;
        c.gridy = 0;

        // TODO: for each work experience in application (use delegate to populate info)
        for (WorkExperience exp : experiences) {
            WorkExperienceCard work = new WorkExperienceCard(exp);
            work.hideButtons();

            panel.add(work, c);
            c.gridy++;
        }

        return panel;
    }

    private JPanel addEducation() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5, 10, 5, 10);
        c.gridx = 0;
        c.gridy = 0;

        // TODO: for each education in application (use delegate to populate info)
        for (Education edu : educations) {
            EducationCard education = new EducationCard(edu);
            education.hideButtons();

            panel.add(education, c);
            c.gridy++;
        }

        return panel;
    }
}
