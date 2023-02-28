package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.WorkDayDelegate;
import ca.ubc.cs304.model.JobPosting;
import ca.ubc.cs304.ui.company.EditJobPostingWindow;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class JobPostingsView extends JFrame implements ActionListener {
    public WorkDayDelegate delegate;

    public int companyID;
    public int applicantID;
    public String jobType;

    protected final JPanel contentPane = new JPanel(new GridBagLayout());
    protected final GridBagConstraints gbc = new GridBagConstraints();

    protected final JButton filterButton = new JButton("Filter!");
    private final JButton statsButton = new JButton("Stats");

    private final JPanel topButtonPane = new JPanel();
    private final JRadioButton allButton = new JRadioButton("All");
    private final JRadioButton internshipButton = new JRadioButton("Internship");
    private final JRadioButton fullTimeButton = new JRadioButton("FullTime");

    public ArrayList<JobPosting> postings = new ArrayList<>();

    public JobPostingsView() {
        super("Job Postings");
        JPanel mainPane = new JPanel(new BorderLayout());

        ButtonGroup filterGroup = new ButtonGroup();
        filterGroup.add(allButton);
        filterGroup.add(internshipButton);
        filterGroup.add(fullTimeButton);

        if (filterGroup.getSelection() == null) allButton.setSelected(true);

        filterButton.addActionListener(this);

        topButtonPane.setLayout(new BoxLayout(topButtonPane, BoxLayout.LINE_AXIS));
        topButtonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        topButtonPane.add(Box.createHorizontalGlue());
        topButtonPane.add(allButton);
        topButtonPane.add(internshipButton);
        topButtonPane.add(fullTimeButton);
        topButtonPane.add(filterButton);
        mainPane.add(topButtonPane, BorderLayout.PAGE_START);

        JPanel bottomButtonPane = new JPanel();
        bottomButtonPane.setLayout(new BoxLayout(bottomButtonPane, BoxLayout.LINE_AXIS));
        bottomButtonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        bottomButtonPane.add(Box.createHorizontalGlue());

        statsButton.addActionListener(this);
        bottomButtonPane.add(statsButton);
        mainPane.add(bottomButtonPane, BorderLayout.PAGE_END);

        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JScrollPane scrollPane = new JScrollPane(contentPane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mainPane.add(scrollPane, BorderLayout.CENTER);

        this.setContentPane(mainPane);

        this.setSize(500, 800);
        this.setLocationRelativeTo(null);
    }

    public void showFrame(WorkDayDelegate delegate) {
        this.delegate = delegate;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        if (companyID == 0)
            postings = delegate.selectAllJobPostings();
        else
            postings = delegate.selectJobPostingsByCompanyID(companyID);

        if (jobType != null) {
            if (!jobType.equals("All")) {
                postings = delegate.selectJobPostingsByType(jobType);
            }
        }

        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        for (JobPosting posting : postings) {
            addPostingPanel(posting);
        }

        this.setVisible(true);
    }

    public void resetPostings() {
        contentPane.removeAll();
        this.showFrame(delegate);
        this.repaint();
    }

    protected abstract void addPostingPanel(JobPosting jobPosting);

    protected abstract void openPostingWindow(int jobID);

    protected void openEditWindow(int jobID) {}

    protected class JobPostingPanel extends JPanel implements ActionListener {
        private final int id;
        private final GridBagConstraints gbc = new GridBagConstraints();

        private final JButton viewButton = new JButton("View");
        private final JButton editButton = new JButton("Edit");
        private final JButton deleteButton = new JButton("Delete");

        public JobPostingPanel(JobPosting jobPosting) {
            super(new GridBagLayout());
            this.id = jobPosting.jobID;
            this.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;

            JLabel idLabel = new JLabel(String.valueOf(id));
            this.add(idLabel, gbc);

            JLabel title = new JLabel(jobPosting.positionName);
            title.setFont(title.getFont().deriveFont(16f));
            gbc.gridy = 1;
            this.add(title, gbc);

            JLabel company = new JLabel(jobPosting.companyName);
            gbc.gridy = 2;
            gbc.insets = new Insets(0, 0, 15, 0);
            this.add(company, gbc);

            JLabel applyBy = new JLabel("Apply by: " + jobPosting.deadline);
            gbc.gridy = 3;
            gbc.insets = new Insets(0, 0, 0, 0);
            this.add(applyBy, gbc);

            JLabel postedOn = new JLabel("Posted on: " + jobPosting.datePosted);
            gbc.gridy = 4;
            this.add(postedOn, gbc);

            JLabel type = new JLabel(jobPosting.jobType);
            gbc.gridy = 5;
            this.add(type, gbc);

            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.PAGE_AXIS));

            viewButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
            viewButton.addActionListener(this);
            buttonPane.add(viewButton);

            editButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
            editButton.addActionListener(this);
            buttonPane.add(editButton);

            deleteButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
            deleteButton.addActionListener(this);
            buttonPane.add(deleteButton);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridheight = 6;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(0, 60, 0, 0);
            this.add(buttonPane, gbc);
        }

        public void hideEditButton() {
            editButton.setVisible(false);
        }

        public void hideDeleteButton() {
            deleteButton.setVisible(false);
        }

        public void hideFilter() {
            topButtonPane.setVisible(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source.equals(viewButton)) {
                openPostingWindow(id);
            } else if (source.equals(editButton)) {
                openEditWindow(id);
            } else if (source.equals(deleteButton)) {
                try {
                    delegate.deleteJobPosting(id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(statsButton)) {
            JobPostingStats jobPostingStats = new JobPostingStats();
            jobPostingStats.showFrame(delegate);
        } else if (source.equals(filterButton)) {
            if (allButton.isSelected()) jobType = allButton.getText();
            else if (internshipButton.isSelected()) jobType = internshipButton.getText();
            else if (fullTimeButton.isSelected()) jobType = fullTimeButton.getText();

            resetPostings();
        }
    }
}


