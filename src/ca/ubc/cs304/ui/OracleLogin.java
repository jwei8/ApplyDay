package ca.ubc.cs304.ui;

import java.awt.event.ActionEvent;

public class OracleLogin extends LoginWindow {

    public OracleLogin() {
        super("Oracle Login");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        delegate.login(idField.getText(), String.valueOf(passwordField.getPassword()));
        this.dispose();
    }
}
