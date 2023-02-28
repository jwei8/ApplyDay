package ca.ubc.cs304.ui.recruiter;

import ca.ubc.cs304.ui.LoginWindow;
import ca.ubc.cs304.delegates.WorkDayDelegate;
import ca.ubc.cs304.ui.company.CompanyMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RecruiterLogin extends LoginWindow {
    public RecruiterLogin() {
        super("Recruiter Login");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        boolean success = delegate.checkRecruiterLogin(Integer.parseInt(idField.getText()), String.valueOf(passwordField.getPassword()));
        if (success) {
            this.dispose();
            RecruiterApplicationsView applicationsView = new RecruiterApplicationsView();
            applicationsView.recruiterID = Integer.parseInt(idField.getText());
            applicationsView.showFrame(delegate);
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect ID or Password");
        }
    }
}
