package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.WorkDayDelegate;
import ca.ubc.cs304.model.Applicant;
import ca.ubc.cs304.model.Application;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class ApplicationsView extends JFrame implements ActionListener {
    protected final JPanel contentPane = new JPanel(new GridBagLayout());
    protected final GridBagConstraints gbc = new GridBagConstraints();
    private final JButton applicantStatsButton = new JButton("Applicant Stats");

    protected final JButton filterButton = new JButton("Filter...");

    public WorkDayDelegate delegate;
    public int applicantId;
    public int recruiterID;

    public ApplicationsView() {
        super("Applications");
    }

    public void showFrame(WorkDayDelegate delegate) {
        this.delegate = delegate;
        ArrayList<Application> applications = new ArrayList<>();
        if (applicantId != 0) {
            applications.addAll(delegate.selectApplicationsByApplicantID(applicantId));
        } else {
            // show all applicants and their applications to a recruiter
            int companyID = delegate.findRecruiterCompanyID(recruiterID);
            applications = delegate.selectApplicationsByCompanyID(companyID);
        }

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPane = new JPanel(new BorderLayout());

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        buttonPane.add(Box.createHorizontalGlue());

        filterButton.addActionListener(this);
        buttonPane.add(filterButton);
        mainPane.add(buttonPane, BorderLayout.PAGE_START);

        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JScrollPane scrollPane = new JScrollPane(contentPane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mainPane.add(scrollPane, BorderLayout.CENTER);

        applicantStatsButton.addActionListener(this);
        buttonPane.add(applicantStatsButton, BorderLayout.PAGE_END);

        this.setContentPane(mainPane);

        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        for (Application application : applications) {
            addApplicationPanel(application);
        }

        this.setSize(500, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    protected abstract void addApplicationPanel(Application applications);

    protected class ApplicationPanel extends JPanel implements ActionListener {
        private final int id;

        public int applicationID;

        public int recruiterID;

        private final GridBagConstraints gbc = new GridBagConstraints();

        private final JButton viewButton = new JButton("View");
        private final JButton withdrawButton = new JButton("Withdraw");
        private final JButton statusButton = new JButton("Change status...");

        public ApplicationPanel(Application application) {
            super(new GridBagLayout());
            this.id = application.applicantID;
            this.applicationID = application.applicationID;
            this.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;


            // TODO: using delegate, populate application info here
            JLabel idLabel = new JLabel(String.valueOf(application.jobID));
            this.add(idLabel, gbc);

            JLabel title = new JLabel(application.positionName);
            title.setFont(title.getFont().deriveFont(16f));
            gbc.gridy = 1;
            this.add(title, gbc);

            JLabel company = new JLabel(application.company);
            gbc.gridy = 2;
            gbc.insets = new Insets(0, 0, 30, 0);
            this.add(company, gbc);

            JLabel status = new JLabel(application.status);
            gbc.gridy = 3;
            gbc.insets = new Insets(0, 0, 0, 0);
            this.add(status, gbc);

            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.PAGE_AXIS));

            viewButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
            viewButton.addActionListener(this);
            buttonPane.add(viewButton);

            withdrawButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
            withdrawButton.addActionListener(this);
            buttonPane.add(withdrawButton);

            statusButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
            statusButton.addActionListener(this);
            buttonPane.add(statusButton);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridheight = 4;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(0, 50, 0, 0);
            this.add(buttonPane, gbc);
        }

        protected class ChangeStatusWindow extends JFrame implements ActionListener {
            private final JPanel pane = new JPanel(new GridBagLayout());
            private final GridBagConstraints gbc = new GridBagConstraints();

            private final JButton cancelButton = new JButton("Cancel");
            private final JButton confirmButton = new JButton("Confirm");

            private final JRadioButton needsReview = new JRadioButton("Submitted");
            private final JRadioButton inProgress = new JRadioButton("In Progress");
            private final JRadioButton rejected = new JRadioButton("Rejected");
            private final JRadioButton withdrawn = new JRadioButton("Withdrawn");
            private final JRadioButton hired = new JRadioButton("Hired");

            public ChangeStatusWindow() {
                super("Change Status");
                this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                this.setContentPane(pane);

                pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                ButtonGroup statusGroup = new ButtonGroup();

                cancelButton.addActionListener(this);
                confirmButton.addActionListener(this);

                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 2;

                statusGroup.add(needsReview);
                pane.add(needsReview, gbc);

                gbc.gridy++;
                statusGroup.add(inProgress);
                pane.add(inProgress, gbc);

                gbc.gridy++;
                statusGroup.add(rejected);
                pane.add(rejected, gbc);

                gbc.gridy++;
                statusGroup.add(withdrawn);
                pane.add(withdrawn, gbc);

                gbc.gridy++;
                statusGroup.add(hired);
                pane.add(hired, gbc);

                gbc.gridx = 1;
                gbc.gridy++;
                gbc.gridwidth = 1;
                gbc.insets = new Insets(30, 0, 0, 0);
                pane.add(cancelButton, gbc);

                gbc.gridx = 2;
                pane.add(confirmButton, gbc);

                needsReview.setSelected(true);

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
                    this.dispose();
                }
            }
        }

        public void hideWithdrawButton() {
            withdrawButton.setVisible(false);
        }

        public void hideStatusButton() {
            statusButton.setVisible(false);
        }

        public void hideFilterButton() {
            filterButton.setVisible(false);
        }

        public void hideApplicantStatsButton() {applicantStatsButton.setVisible(false);}


        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source.equals(viewButton)) {
                ApplicationWindow application = new ApplicationWindow(applicationID);
                application.showFrame(delegate);
            } else if (source.equals(withdrawButton)) {

            } else if (source.equals(statusButton)) {
                new ChangeStatusWindow();
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(applicantStatsButton)) {
            ApplicantStats applicantStats = new ApplicantStats(recruiterID);
            applicantStats.showFrame(delegate);
        }

    }
}
