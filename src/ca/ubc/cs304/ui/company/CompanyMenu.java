package ca.ubc.cs304.ui.company;

import ca.ubc.cs304.delegates.WorkDayDelegate;
import ca.ubc.cs304.model.Company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompanyMenu extends JFrame implements ActionListener {
    private final Company company;

    private final JPanel pane = new JPanel(new GridBagLayout());
    private final GridBagConstraints gbc = new GridBagConstraints();

    private final JButton createButton = new JButton("Create a job posting");
    private final JButton viewButton = new JButton("View job postings");

    public WorkDayDelegate delegate;

    public int companyID;

    public CompanyMenu(Company company) {
        super("Company Menu");
        this.company = company;
    }

    public void showFrame(WorkDayDelegate delegate, int companyID) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.delegate = delegate;
        this.companyID = companyID;

        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setContentPane(pane);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        createButton.addActionListener(this);
        pane.add(createButton, gbc);

        gbc.gridy++;
        viewButton.addActionListener(this);
        pane.add(viewButton, gbc);

        this.setSize(500, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(createButton)) {
            EditJobPostingWindow editWindow = new EditJobPostingWindow(null, -1, company);
            editWindow.showFrame(delegate);
        } else if (source.equals(viewButton)) {
            CompanyJobPostingsView postingsView = new CompanyJobPostingsView(company);
            postingsView.companyID = companyID;
            postingsView.showFrame(delegate);
        }
    }
}
