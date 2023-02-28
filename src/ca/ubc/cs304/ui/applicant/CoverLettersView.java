package ca.ubc.cs304.ui.applicant;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoverLettersView extends JFrame implements ActionListener {
    private final JPanel contentPane = new JPanel(new GridBagLayout());
    private final GridBagConstraints gbc = new GridBagConstraints();

    private final JButton newButton = new JButton("Add new...");

    public CoverLettersView() {
        super("Cover Letters");
    }

    public void showFrame(/* TODO: delegate */) {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPane = new JPanel(new BorderLayout());

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        buttonPane.add(Box.createHorizontalGlue());

        newButton.addActionListener(this);
        buttonPane.add(newButton);
        mainPane.add(buttonPane, BorderLayout.PAGE_START);

        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JScrollPane scrollPane = new JScrollPane(contentPane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mainPane.add(scrollPane, BorderLayout.CENTER);

        this.setContentPane(mainPane);

        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // TODO: create cover letter panels using delegate
        for (int i = 0; i < 8; i++) {
            CoverLetterPanel panel = new CoverLetterPanel(null/* cover letter object */);
            contentPane.add(panel, gbc);
            gbc.gridy++;
        }

        this.setSize(500, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EditCoverLetterWindow coverLetter = new EditCoverLetterWindow(null);
        coverLetter.showFrame();
    }

    private class CoverLetterPanel extends JPanel implements ActionListener {
        private final Object coverLetter;

        private final GridBagConstraints gbc = new GridBagConstraints();
        private final JButton editButton = new JButton("Edit");

        public CoverLetterPanel(Object coverLetter/* TODO: replace with cover letter object */) {
            super(new GridBagLayout());
            this.coverLetter = coverLetter;
            this.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            editButton.addActionListener(this);

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;

            // TODO: using delegate, populate cover letter info here
            JLabel titleLabel = new JLabel("Cover Letter Title");
            gbc.ipadx = 40;
            this.add(titleLabel, gbc);

            gbc.gridx = 1;
            gbc.ipadx = 0;
            this.add(editButton, gbc);

            JTextArea content = new JTextArea();
            String contentText = "cover letter content here";

            content.setText(contentText.length() > 150 ? contentText.substring(0, 150) + "..." : contentText);
            content.setLineWrap(true);
            content.setEditable(false);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(10, 0 , 0, 0);
            this.add(content, gbc);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            EditCoverLetterWindow cl = new EditCoverLetterWindow(coverLetter);
            cl.showFrame();
        }
    }
}
