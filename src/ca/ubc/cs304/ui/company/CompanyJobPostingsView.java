package ca.ubc.cs304.ui.company;

import ca.ubc.cs304.model.Company;
import ca.ubc.cs304.model.JobPosting;
import ca.ubc.cs304.ui.JobPostingWindow;
import ca.ubc.cs304.ui.JobPostingsView;

import java.awt.*;

public class CompanyJobPostingsView extends JobPostingsView {
    private final Company company;

    public CompanyJobPostingsView(Company company) {
        super();
        this.company = company;
    }

    @Override
    protected void addPostingPanel(JobPosting posting) {
        JobPostingPanel panel = new JobPostingPanel(posting);
        panel.setAlignmentY(Component.TOP_ALIGNMENT);
        panel.hideFilter();

        contentPane.add(panel, gbc);
        gbc.gridy++;
    }

    @Override
    public void openPostingWindow(int jobID) {
        JobPostingWindow posting = new JobPostingWindow(jobID);
        posting.hideApplyButton();

        posting.showFrame(delegate);
    }

    @Override
    public void openEditWindow(int jobID) {
        EditJobPostingWindow editWindow = new EditJobPostingWindow(this, jobID, company);
        editWindow.showFrame(delegate);
    }
}
