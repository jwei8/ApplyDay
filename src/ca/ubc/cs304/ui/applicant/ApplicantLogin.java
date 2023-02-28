package ca.ubc.cs304.ui.applicant;

import ca.ubc.cs304.controller.WorkDay;
import ca.ubc.cs304.delegates.WorkDayDelegate;
import ca.ubc.cs304.ui.LoginWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ApplicantLogin extends LoginWindow {

    public ApplicantLogin() {
        super("Applicant Login");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        boolean success = delegate.checkApplicantLogin(Integer.parseInt(idField.getText()), String.valueOf(passwordField.getPassword()));
        if (success) {
            this.dispose();
            ApplicantMenu menu = new ApplicantMenu();
            menu.showFrame(delegate, Integer.parseInt(idField.getText()));
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect ID or Password");
        }
    }
}
