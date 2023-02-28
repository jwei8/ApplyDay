package ca.ubc.cs304.ui.applicant;

import ca.ubc.cs304.model.Application;
import ca.ubc.cs304.ui.ApplicationsView;

import java.awt.*;
import java.util.ArrayList;

public class ApplicantApplicationsView extends ApplicationsView {

    @Override
    protected void addApplicationPanel(Application application) {
        ApplicationPanel panel = new ApplicationPanel(application);
        panel.setAlignmentY(Component.TOP_ALIGNMENT);
        panel.hideStatusButton();
        panel.hideApplicantStatsButton();

        contentPane.add(panel, gbc);
        gbc.gridy++;
    }
}
