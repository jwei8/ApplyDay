package ca.ubc.cs304.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ca.ubc.cs304.delegates.WorkDayDelegate;

/**
 * This class references code from the Sample Project given to us in class/tutorial
 *
 * The class is only responsible for displaying and handling the login GUI.
 */
public class LoginWindow extends JFrame implements ActionListener {
    private static final int TEXT_FIELD_WIDTH = 10;
    private static final int MAX_LOGIN_ATTEMPTS = 3;

    // number of login attempts
    private int loginAttempts;

    // components of the login window
    public JTextField idField;
    public JPasswordField passwordField;

    // delegate
    public WorkDayDelegate delegate;

    public LoginWindow(String name) {
        super(name);
    }

    public void showFrame(WorkDayDelegate delegate) {
        this.delegate = delegate;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        loginAttempts = 0;

        JLabel usernameLabel = new JLabel("ID: ");
        JLabel passwordLabel = new JLabel("Password: ");

        idField = new JTextField(TEXT_FIELD_WIDTH);
        passwordField = new JPasswordField(TEXT_FIELD_WIDTH);
        passwordField.setEchoChar('*');

        JButton loginButton = new JButton("Log In");

        JPanel pane = new JPanel(new GridBagLayout());
        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setContentPane(pane);

        // layout components using the GridBag layout manager
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // place the username label
        pane.add(usernameLabel, gbc);

        // place the text field for the username
        gbc.gridx = 1;
        pane.add(idField, gbc);

        // place password label
        gbc.gridx = 0;
        gbc.gridy++;
        pane.add(passwordLabel, gbc);

        // place the password field
        gbc.gridx = 1;
        pane.add(passwordField, gbc);

        // place the login button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(12, 40, 0, 40);
        pane.add(loginButton, gbc);

        // register login button with action event handler
        loginButton.addActionListener(this);

        // make the window visible
        this.setSize(500, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        // place the cursor in the text field for the username
        idField.requestFocus();
    }

    public void handleLoginFailed() {
        loginAttempts++;
        passwordField.setText(""); // clear password field
    }

    public boolean hasReachedMaxLoginAttempts() {
        return (loginAttempts >= MAX_LOGIN_ATTEMPTS);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
