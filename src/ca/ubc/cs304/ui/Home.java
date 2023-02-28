package ca.ubc.cs304.ui;

import ca.ubc.cs304.ui.applicant.ApplicantLogin;
import ca.ubc.cs304.ui.company.CompanyLogin;
import ca.ubc.cs304.ui.recruiter.RecruiterLogin;

import ca.ubc.cs304.delegates.WorkDayDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {
    private final JPanel pane = new JPanel(new GridBagLayout());
    private final GridBagConstraints gbc = new GridBagConstraints();

    private final JButton applicantLogin = new JButton("Applicant Login");
    private final JButton recruiterLogin = new JButton("Recruiter Login");
    private final JButton companyLogin = new JButton("Company Login");
    private WorkDayDelegate delegate;

    public Home(WorkDayDelegate delegate) {
        super("Home");
        this.delegate = delegate;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(pane);

        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        addButton(applicantLogin);
        addButton(recruiterLogin);
        addButton(companyLogin);

        this.setSize(500, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addButton(JButton button) {
        button.addActionListener(this);

        gbc.gridy++;
        pane.add(button, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        LoginWindow login = null;

        if (source.equals(applicantLogin)) {
            login = new ApplicantLogin();
        } else if (source.equals(recruiterLogin)) {
            login = new RecruiterLogin();
        } else if (source.equals(companyLogin)) {
            login = new CompanyLogin();
        }

        if (login != null) login.showFrame(delegate);

        this.dispose();
    }
}
