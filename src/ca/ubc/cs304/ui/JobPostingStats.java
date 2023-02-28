package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.WorkDayDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobPostingStats extends JFrame {
    public WorkDayDelegate delegate;

    private final JPanel pane = new JPanel(new GridBagLayout());
    private final GridBagConstraints gbc = new GridBagConstraints();

    public JobPostingStats() {
        super("Stats");
    }

    public void showFrame(WorkDayDelegate delegate) {
        this.delegate = delegate;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(pane);

        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel averageLabel = new JLabel("Average Salaries by Job Type: ");
        Font bold = new Font("Arial", Font.BOLD, 15);
        averageLabel.setFont(bold);
        gbc.ipadx = 20;
        pane.add(averageLabel, gbc);

        JLabel internshipLabel = new JLabel("Internship Salary Average: ");
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        gbc.ipadx = 20;
        gbc.insets = new Insets(5, 0, 0, 0);
        pane.add(internshipLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;
        JLabel internshipAverageLabel = new JLabel(String.valueOf(delegate.findAveragePayByJobType().averageInternship));
        pane.add(internshipAverageLabel, gbc);

        JLabel fullTimeLabel = new JLabel("Full Time Salary Average: ");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.ipadx = 20;
        gbc.insets = new Insets(0, 0, 0, 0);
        pane.add(fullTimeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;
        JLabel fullTimeAverageLabel = new JLabel(String.valueOf(delegate.findAveragePayByJobType().averageFullTime));
        pane.add(fullTimeAverageLabel, gbc);

        JLabel titleLabel = new JLabel("Count of Above Average Salaries Per Company: ");
        titleLabel.setFont(bold);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.ipadx = 20;
        gbc.insets = new Insets(15, 0, 0, 0);
        pane.add(titleLabel, gbc);

        JLabel listLabel = new JLabel(delegate.countAboveAverageSalaryJobPostingForEachCompany().toString());
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.ipadx = 20;
        gbc.insets = new Insets(5, 0, 0, 0);
        pane.add(listLabel, gbc);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
