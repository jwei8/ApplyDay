package ca.ubc.cs304.ui.applicant;

import ca.ubc.cs304.delegates.WorkDayDelegate;
import ca.ubc.cs304.model.Education;
import ca.ubc.cs304.model.WorkExperience;
import ca.ubc.cs304.ui.EducationCard;
import ca.ubc.cs304.ui.WorkExperienceCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ApplyWindow extends JFrame implements ActionListener {
    private final JFrame parent;
    private final JPanel contentPane = new JPanel(new GridBagLayout());
    private final GridBagConstraints gbc = new GridBagConstraints();

    private final JButton referralButton = new JButton("Attach referral...");
    private final JButton submitButton = new JButton("Submit");

    private final HashMap<JCheckBox, WorkExperienceCard> workExperiences = new HashMap<>();
    private final HashMap<JCheckBox, EducationCard> educations = new HashMap<>();
    private final HashMap<JRadioButton, Integer> coverLetters = new HashMap<>();

    private int referralCompanyId = -1;
    private String referralEmployee = null;

    public WorkDayDelegate delegate;

    public ArrayList<WorkExperience> experiences;

    public ArrayList<Education> educationList;

    public int applicantID;


    public ApplyWindow(JFrame parent) {
        super("Apply to Job Posting");
        this.parent = parent;
    }

    public void showFrame(WorkDayDelegate delegate) {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.delegate = delegate;

        experiences = delegate.selectWorkExperienceByApplicantID(applicantID);

        educationList = delegate.selectEducationByApplicantID(applicantID);

        JPanel mainPane = new JPanel(new BorderLayout());

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        referralButton.addActionListener(this);
        submitButton.addActionListener(this);

        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(referralButton);
        buttonPane.add(Box.createRigidArea(new Dimension(5, 0)));
        buttonPane.add(submitButton);

        mainPane.add(buttonPane, BorderLayout.PAGE_END);

        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(contentPane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mainPane.add(scrollPane, BorderLayout.CENTER);

        this.setContentPane(mainPane);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 0, 5, 0);

        JLabel workHeader = new JLabel("Work Experience");
        workHeader.setFont(workHeader.getFont().deriveFont(16f));
        contentPane.add(workHeader, gbc);

        gbc.gridwidth = 1;

        // TODO: for each work experience that the applicant has (use delegate to populate info)
        for (WorkExperience exp : experiences) {
            JCheckBox checkBox = new JCheckBox();
            gbc.gridx = 0;
            gbc.gridy++;
            contentPane.add(checkBox, gbc);

            WorkExperienceCard work = new WorkExperienceCard(exp);
            gbc.gridx = 1;
            contentPane.add(work, gbc);

            workExperiences.put(checkBox, work);
        }

        JLabel eduHeader = new JLabel("Education");
        eduHeader.setFont(workHeader.getFont().deriveFont(16f));
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 0, 5, 0);
        contentPane.add(eduHeader, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 0, 5, 0);

        // TODO: for each education that the applicant has (use delegate to populate info)
        for (Education edu : educationList) {
            JCheckBox checkBox = new JCheckBox();
            gbc.gridx = 0;
            gbc.gridy++;
            contentPane.add(checkBox, gbc);

            EducationCard education = new EducationCard(edu);
            gbc.gridx = 1;
            contentPane.add(education, gbc);

            educations.put(checkBox, education);
        }

        JLabel coverHeader = new JLabel("Cover Letter");
        coverHeader.setFont(workHeader.getFont().deriveFont(16f));
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 0, 10, 0);
        contentPane.add(coverHeader, gbc);

        gbc.insets = new Insets(0, 0, 0, 0);

        ButtonGroup coverLetterGroup = new ButtonGroup();

        // TODO: for each cover letter that the applicant has (use delegate to populate info)
        for (int i = 0; i < 4; i++) {
            JRadioButton radioButton = new JRadioButton("Cover letter title " + i);
            coverLetterGroup.add(radioButton);
            gbc.gridy++;
            contentPane.add(radioButton, gbc);

            coverLetters.put(radioButton, i /* supposed to be cover letter id */);
        }

        this.setSize(500, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private class AttachReferral extends JFrame implements ActionListener {
        private final JPanel pane = new JPanel(new GridBagLayout());
        private final GridBagConstraints gbc = new GridBagConstraints();

        private final JButton cancelButton = new JButton("Cancel");
        private final JButton confirmButton = new JButton("Confirm");

        private final JTextField companyField = new JTextField();
        private final JTextField employeeField = new JTextField();

        public AttachReferral() {
            super("Attach Referral");
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.setContentPane(pane);

            pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            cancelButton.addActionListener(this);
            confirmButton.addActionListener(this);

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;

            JLabel companyLabel = new JLabel("Company ID: ");
            gbc.ipadx = 20;
            pane.add(companyLabel, gbc);

            gbc.gridx = 1;
            gbc.gridwidth = 2;
            gbc.ipadx = 0;
            pane.add(companyField, gbc);

            JLabel employeeLabel = new JLabel("Name of employee: ");
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 1;
            gbc.ipadx = 20;
            pane.add(employeeLabel, gbc);

            gbc.gridx = 1;
            gbc.gridwidth = 2;
            gbc.ipadx = 0;
            pane.add(employeeField, gbc);

            gbc.gridx = 1;
            gbc.gridy++;
            gbc.gridwidth = 1;
            gbc.insets = new Insets(30, 0, 0, 0);
            pane.add(cancelButton, gbc);

            gbc.gridx = 2;
            pane.add(confirmButton, gbc);

            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source.equals(cancelButton)) {
                this.dispose();
            } else if (source.equals(confirmButton)) {
                int id = Integer.parseInt(companyField.getText());

                if (false/* TODO: validate that the company ID actually exists */) {
                    companyField.setText("");
                    employeeField.setText("");
                } else {
                    referralCompanyId = Integer.parseInt(companyField.getText());
                    referralEmployee = employeeField.getText();
                    this.dispose();
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(referralButton)) {
            new AttachReferral();
        } else if (source.equals(submitButton)) {
            /*
             TODO: validate fields and create new application
                   also create new referral if referralCompanyId != -1 && referralEmployee != null
             */

            parent.dispose();
            this.dispose();
        }
    }
}
