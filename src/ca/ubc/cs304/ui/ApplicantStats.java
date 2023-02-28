package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.WorkDayDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicantStats extends JFrame implements ActionListener {
    private final int recruiterID;

    public WorkDayDelegate delegate;
    private final JPanel pane = new JPanel(new GridBagLayout());
    private final GridBagConstraints gbc = new GridBagConstraints();


    public ApplicantStats(int recruiterID) {
        this.recruiterID = recruiterID;
    }

    public void showFrame(WorkDayDelegate delegate) {
        this.delegate = delegate;
        int companyID = delegate.findRecruiterCompanyID(recruiterID);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(pane);

        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel titleLabel = new JLabel("Applicant Stats: ");
        Font bold = new Font("Arial", Font.BOLD, 15);
        titleLabel.setFont(bold);
        gbc.ipadx = 20;
        pane.add(titleLabel, gbc);

        JLabel firstLabel = new JLabel("List of Names of Applicants with > 2 Previous Internships: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.ipadx = 20;
        pane.add(firstLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;
        JLabel secondLabel = new JLabel(delegate.findApplicantWithMultiplePrevInternships(companyID).toString());
        pane.add(secondLabel, gbc);

        JLabel thirdLabel = new JLabel("List of Names of Applicants who have applied to all of a company's job postings: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.ipadx = 20;
        pane.add(thirdLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;
        JLabel fourthLabel = new JLabel(delegate.findApplicantsAppliedToAllPostingsForACompany(companyID).toString());
        pane.add(fourthLabel, gbc);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
