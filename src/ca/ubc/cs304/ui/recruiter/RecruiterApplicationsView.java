package ca.ubc.cs304.ui.recruiter;

import ca.ubc.cs304.delegates.WorkDayDelegate;
import ca.ubc.cs304.model.Application;
import ca.ubc.cs304.ui.ApplicationsView;

import javax.swing.*;
import java.awt.*;

public class RecruiterApplicationsView extends ApplicationsView {
    @Override
    public void showFrame(WorkDayDelegate delegate) {
        this.delegate = delegate;
        super.showFrame(delegate);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    @Override
    protected void addApplicationPanel(Application application) {
        ApplicationPanel panel = new ApplicationPanel(application);
        panel.recruiterID = recruiterID;
        panel.setAlignmentY(Component.TOP_ALIGNMENT);
        panel.hideWithdrawButton();
        panel.hideFilterButton();

        contentPane.add(panel, gbc);
        gbc.gridy++;
    }
}
