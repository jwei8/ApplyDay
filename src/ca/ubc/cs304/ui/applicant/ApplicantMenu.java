package ca.ubc.cs304.ui.applicant;

import ca.ubc.cs304.delegates.WorkDayDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicantMenu extends JFrame implements ActionListener {
    private final JPanel pane = new JPanel(new GridBagLayout());
    private final GridBagConstraints gbc = new GridBagConstraints();

    private final JButton jobsButton = new JButton("View job postings");
    private final JButton appsButton = new JButton("View applications");
    private final JButton profileButton = new JButton("Edit profile");
    private final JButton expButton = new JButton("Edit experiences");
    private final JButton coverButton = new JButton("Edit cover letters");
    public int applicantID;
    public WorkDayDelegate delegate;

    public ApplicantMenu() {
        super("Applicant Menu");
    }

    public void showFrame(WorkDayDelegate delegate, int applicantID) {
        this.delegate = delegate;
        this.applicantID = applicantID;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setContentPane(pane);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        addButton(jobsButton, 0, 0);
        addButton(appsButton, 1, 0);
        addButton(profileButton, 0, 1);
        addButton(expButton, 1, 1);

        gbc.gridwidth = 2;
        addButton(coverButton, 0, 2);

        this.setSize(500, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addButton(JButton button, int x, int y) {
        button.addActionListener(this);

        gbc.gridx = x;
        gbc.gridy = y;
        pane.add(button, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(jobsButton)) {
            ApplicantJobPostingsView postingsView = new ApplicantJobPostingsView();
            postingsView.applicantID = applicantID;
            postingsView.showFrame(delegate);
        } else if (source.equals(appsButton)) {
            ApplicantApplicationsView applicationsView = new ApplicantApplicationsView();
            applicationsView.applicantId = applicantID;
            applicationsView.showFrame(delegate);
        } else if (source.equals(profileButton)) {
            EditProfileWindow editProfile = new EditProfileWindow();
            editProfile.showFrame();
        } else if (source.equals(expButton)) {
            ExperiencesView experiences = new ExperiencesView();
            experiences.showFrame();
        } else if (source.equals(coverButton)) {
            CoverLettersView coverLetters = new CoverLettersView();
            coverLetters.showFrame();
        }
    }
}
