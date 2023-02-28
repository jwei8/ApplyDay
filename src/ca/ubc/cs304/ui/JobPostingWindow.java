package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.WorkDayDelegate;
import ca.ubc.cs304.model.JobPosting;
import ca.ubc.cs304.ui.applicant.ApplyWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobPostingWindow extends JFrame implements ActionListener {
    protected final JPanel pane = new JPanel(new GridBagLayout());
    protected final GridBagConstraints gbc = new GridBagConstraints();

    public WorkDayDelegate delegate;

    public int jobID;

    public int applicantID;

    protected final JButton applyButton = new JButton("Apply");

    public JobPostingWindow(int jobID) {
        super("View Job Posting");
        this.jobID = jobID;
    }

    public void showFrame(WorkDayDelegate delegate) {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.delegate = delegate;

        JobPosting job = delegate.selectJobPostingByJobID(jobID);

        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.setContentPane(scrollPane);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 10);

        JLabel title = new JLabel(job.positionName);
        title.setFont(title.getFont().deriveFont(16f));
        pane.add(title, gbc);

        JLabel type = new JLabel(job.jobType, SwingConstants.RIGHT);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        pane.add(type, gbc);

        JLabel company = new JLabel(job.companyName);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 10);
        pane.add(company, gbc);

        JLabel id = new JLabel(String.valueOf(job.jobID), SwingConstants.RIGHT);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        pane.add(id, gbc);

        JLabel location = new JLabel(job.location);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 10);
        pane.add(location, gbc);

        applyButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridheight = 2;
        gbc.insets = new Insets(0, 10, 0, 0);
        pane.add(applyButton, gbc);

        JLabel postedOn = new JLabel(job.datePosted.toString());
        gbc.gridx = 0;
        gbc.gridy += 2;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        pane.add(postedOn, gbc);

        JLabel applyBy = new JLabel("Apply by: " + job.deadline);
        gbc.gridy++;
        pane.add(applyBy, gbc);

        JLabel eduReqLabel = new JLabel("Education requirement:");
        eduReqLabel.setFont(title.getFont().deriveFont(16f));
        gbc.gridy++;
        gbc.insets = new Insets(15, 0, 0, 0);
        pane.add(eduReqLabel, gbc);

        JTextArea eduReq = new JTextArea();
        eduReq.setText(job.education);
        eduReq.setLineWrap(true);
        eduReq.setEditable(false);

        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0 , 0, 0);
        pane.add(eduReq, gbc);

        JLabel skillsReqLabel = new JLabel("Skills requirement: ");
        skillsReqLabel.setFont(title.getFont().deriveFont(16f));
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 0, 0, 0);
        pane.add(skillsReqLabel, gbc);

        JTextArea skillsReq = new JTextArea();
        skillsReq.setText(job.skills);
        skillsReq.setLineWrap(true);
        skillsReq.setEditable(false);

        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0 , 0, 0);
        pane.add(skillsReq, gbc);

        JLabel visaReqLabel = new JLabel("Visa requirement: ");
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(15, 0, 0, 0);
        pane.add(visaReqLabel, gbc);

        JLabel visaReq;

        if (job.visaRequirement == 1) {
            visaReq = new JLabel("Must be a citizen/permanent resident");
        } else {
            visaReq = new JLabel("no requirement");
        }

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);
        pane.add(visaReq, gbc);

        JLabel startDate = new JLabel("Start date: " + job.startDate);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 0, 0, 0);
        pane.add(startDate, gbc);

        JLabel endDate = new JLabel("End date: " + job.endDate);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);
        pane.add(endDate, gbc);

        JLabel salary = new JLabel("Salary: $" + job.salary);
        gbc.gridy++;
        gbc.insets = new Insets(15, 0, 0, 0);
        pane.add(salary, gbc);

        this.setSize(500, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void hideApplyButton() {
        applyButton.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ApplyWindow apply = new ApplyWindow(this);
        apply.applicantID = applicantID;
        apply.showFrame(delegate);
    }
}
