package ca.ubc.cs304.ui.company;

import ca.ubc.cs304.ui.LoginWindow;
import ca.ubc.cs304.delegates.WorkDayDelegate;
import ca.ubc.cs304.ui.applicant.ApplicantMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CompanyLogin extends LoginWindow {
    public CompanyLogin() {
        super("Company Login");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        boolean success = delegate.checkCompanyLogin(Integer.parseInt(idField.getText()), String.valueOf(passwordField.getPassword()));
        if (success) {
            this.dispose();

            CompanyMenu menu = new CompanyMenu(delegate.selectCompanyByCompanyID(Integer.parseInt(idField.getText())));
            menu.showFrame(delegate, Integer.parseInt(idField.getText()));
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect ID or Password");
        }
    }
}
