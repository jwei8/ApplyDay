package ca.ubc.cs304.ui.applicant;

import ca.ubc.cs304.model.JobPosting;
import ca.ubc.cs304.ui.JobPostingWindow;
import ca.ubc.cs304.ui.JobPostingsView;

import java.awt.*;

public class ApplicantJobPostingsView extends JobPostingsView {
    @Override
    public void addPostingPanel(JobPosting posting) {
        JobPostingPanel panel = new JobPostingPanel(posting);
        panel.setAlignmentY(Component.TOP_ALIGNMENT);
        panel.hideEditButton();
        panel.hideDeleteButton();

        contentPane.add(panel, gbc);
        gbc.gridy++;
    }

    @Override
    public void openPostingWindow(int jobID) {
        JobPostingWindow posting = new JobPostingWindow(jobID);
        posting.applicantID = applicantID;
        posting.showFrame(delegate);
    }
}
