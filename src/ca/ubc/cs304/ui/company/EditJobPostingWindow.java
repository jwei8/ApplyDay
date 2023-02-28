package ca.ubc.cs304.ui.company;

import ca.ubc.cs304.delegates.WorkDayDelegate;
import ca.ubc.cs304.model.Company;
import ca.ubc.cs304.model.JobPosting;
import ca.ubc.cs304.ui.JobPostingsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

public class EditJobPostingWindow extends JFrame implements ActionListener {
    private final int id;
    private final Company company;
    private WorkDayDelegate delegate;

    private final JobPostingsView parent;

    private final JPanel pane = new JPanel(new GridBagLayout());
    private final GridBagConstraints gbc = new GridBagConstraints();

    private final JButton cancelButton = new JButton("Cancel");
    private final JButton publishButton = new JButton("Publish");

    private final JTextField titleField = new JTextField();
    private final JTextField deadlineField = new JTextField();
    private final JTextField startDateField = new JTextField();
    private final JTextField endDateField = new JTextField();
    private final JTextField locationField = new JTextField();
    private final JTextField salaryField = new JTextField();
    private final JTextField visaReqField = new JTextField();
    private final JTextField datePostedField = new JTextField();
    private final JTextField recruiterIDField = new JTextField();
    private final JTextArea eduReqField = new JTextArea();
    private final JTextArea skillsReqField = new JTextArea();

    private final JRadioButton internshipButton = new JRadioButton("Internship");
    private final JRadioButton fullTimeButton = new JRadioButton("Full-time");

    private JobPosting currentJobPosting;

    public EditJobPostingWindow(JobPostingsView parent, int id, Company company) {
        super("Edit Job Posting");
        this.parent = parent;
        this.id = id;
        this.company = company;
    }

    public void showFrame(WorkDayDelegate delegate) {
        this.delegate = delegate;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(pane);

        if (id != -1) {
            this.currentJobPosting = delegate.selectJobPostingByJobID(id);
        }

        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        cancelButton.addActionListener(this);
        publishButton.addActionListener(this);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel titleLabel = new JLabel("Position title: ");
        gbc.ipadx = 20;
        pane.add(titleLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;
        pane.add(titleField, gbc);

        JLabel deadlineLabel = new JLabel("Deadline: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.ipadx = 20;
        pane.add(deadlineLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;
        pane.add(deadlineField, gbc);

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

        JLabel locationLabel = new JLabel("Location: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.ipadx = 20;
        pane.add(locationLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;
        pane.add(locationField, gbc);

        JLabel salaryLabel = new JLabel("Salary: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.ipadx = 20;
        pane.add(salaryLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;
        pane.add(salaryField, gbc);

        JLabel datePostedLabel = new JLabel("Date Posted: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.ipadx = 20;
        pane.add(datePostedLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;
        pane.add(datePostedField, gbc);

        JLabel recruiterIDLabel = new JLabel("Recruiter ID: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.ipadx = 20;
        pane.add(recruiterIDLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;
        pane.add(recruiterIDField, gbc);

        JLabel typeLabel = new JLabel("Job type: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.ipadx = 20;
        pane.add(typeLabel, gbc);

        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(internshipButton);
        typeGroup.add(fullTimeButton);

        gbc.gridx = 2;
        gbc.ipadx = 0;
        pane.add(internshipButton, gbc);

        gbc.gridy++;
        pane.add(fullTimeButton, gbc);

        JLabel eduReqLabel = new JLabel("Education requirement");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(5, 0, 5, 0);
        pane.add(eduReqLabel, gbc);

        eduReqField.setLineWrap(true);
        gbc.gridy++;
        gbc.ipady = 30;
        pane.add(eduReqField, gbc);

        JLabel skillsReqLabel = new JLabel("Skills requirement");
        gbc.gridy++;
        gbc.ipady = 0;
        pane.add(skillsReqLabel, gbc);

        skillsReqField.setLineWrap(true);
        gbc.gridy++;
        gbc.ipady = 30;
        pane.add(skillsReqField, gbc);

        JLabel visaLabel = new JLabel("Visa requirement: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.ipadx = 20;
        gbc.ipady = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        pane.add(visaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;
        pane.add(visaReqField, gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.ipady = 0;
        gbc.insets = new Insets(30, 0, 0, 0);
        pane.add(cancelButton, gbc);

        gbc.gridx = 2;
        pane.add(publishButton, gbc);

        if (id != -1)
            populateFields(); // editing existing job posting instead of creating

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void populateFields() {
        titleField.setText(currentJobPosting.positionName);
        deadlineField.setText(String.valueOf(currentJobPosting.deadline));
        startDateField.setText(String.valueOf(currentJobPosting.startDate));
        endDateField.setText(String.valueOf(currentJobPosting.endDate));

        locationField.setText(currentJobPosting.location);
        locationField.setEditable(false);

        salaryField.setText(String.valueOf(currentJobPosting.salary));
        datePostedField.setText(String.valueOf(currentJobPosting.datePosted));
        recruiterIDField.setText(String.valueOf(currentJobPosting.recruiterID));
        visaReqField.setText(String.valueOf(currentJobPosting.visaRequirement));
        visaReqField.setEditable(false);

        eduReqField.setText(String.valueOf(currentJobPosting.education));
        skillsReqField.setText(String.valueOf(currentJobPosting.skills));

        internshipButton.setSelected(true);
        fullTimeButton.setSelected(false);
    }

    public String resultOfJobTypeButton() {
        String internship = "Internship";
        String fullTime = "FullTime";
        if (internshipButton.isSelected()) {
            return internship;
        } else {
            return fullTime;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(cancelButton)) {
            this.dispose();
        } else if (source.equals(publishButton)) {
            if (id == -1) {
                // insert job posting
                int jobId = ThreadLocalRandom.current().nextInt(10000, 50000 + 1);
                JobPosting jobPosting = new JobPosting(
                    jobId,
                    Date.valueOf(datePostedField.getText()),
                    Date.valueOf(deadlineField.getText()),
                    titleField.getText(),
                    company.getCompanyID(),
                    company.getName(),
                    resultOfJobTypeButton(),
                    Integer.parseInt(recruiterIDField.getText()),
                    skillsReqField.getText(),
                    locationField.getText(),
                    eduReqField.getText(),
                    Date.valueOf(startDateField.getText()),
                    Date.valueOf(endDateField.getText()),
                    Integer.parseInt(salaryField.getText()),
                    Integer.parseInt(visaReqField.getText())
                );

                try {
                    delegate.insertJobPosting(jobPosting);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                this.dispose();
            } else {
                // update job posting
                JobPosting jobPosting = new JobPosting(
                    id,
                    Date.valueOf(datePostedField.getText()),
                    Date.valueOf(deadlineField.getText()),
                    titleField.getText(),
                    company.getCompanyID(),
                    company.getName(),
                    resultOfJobTypeButton(),
                    Integer.parseInt(recruiterIDField.getText()),
                    skillsReqField.getText(),
                    currentJobPosting.location,
                    eduReqField.getText(),
                    Date.valueOf(startDateField.getText()),
                    Date.valueOf(endDateField.getText()),
                    Integer.parseInt(salaryField.getText()),
                    currentJobPosting.visaRequirement
                );

                delegate.updateJobPosting(jobPosting, currentJobPosting);
            }

            if (parent != null) parent.resetPostings();
            this.dispose();
        }
    }
}
