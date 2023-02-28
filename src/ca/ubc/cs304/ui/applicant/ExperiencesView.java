package ca.ubc.cs304.ui.applicant;

import ca.ubc.cs304.ui.EducationCard;
import ca.ubc.cs304.ui.WorkExperienceCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExperiencesView extends JFrame implements ActionListener {
    private final JPanel pane = new JPanel(new GridBagLayout());
    private final GridBagConstraints gbc = new GridBagConstraints();

    private final JButton newWorkButton = new JButton("Add new...");
    private final JButton newEduButton = new JButton("Add new...");

    public ExperiencesView() {
        super("Experiences");
    }

    public void showFrame(/* TODO: delegate */) {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.setContentPane(scrollPane);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 10);

        JLabel workHeader = new JLabel("Work Experience");
        workHeader.setFont(workHeader.getFont().deriveFont(16f));
        pane.add(workHeader, gbc);

        newWorkButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 10, 0, 0);
        pane.add(newWorkButton, gbc);

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 0, 5, 0);

        // TODO: for each work experience in application (use delegate to populate info)
//        for (int i = 0; i < 4; i++) {
//            WorkExperienceCard work = new WorkExperienceCard(0, "", "");
//
//            gbc.gridy++;
//            pane.add(work, gbc);
//        }

        JLabel eduHeader = new JLabel("Education");
        eduHeader.setFont(workHeader.getFont().deriveFont(16f));
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(30, 0, 0, 10);
        pane.add(eduHeader, gbc);

        newEduButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(30, 10, 0, 0);
        pane.add(newEduButton, gbc);

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 0, 5, 0);

        // TODO: for each education in application (use delegate to populate info)
//        for (int i = 0; i < 4; i++) {
//            EducationCard education = new EducationCard(0, "", "");
//
//            gbc.gridy++;
//            pane.add(education, gbc);
//        }

        this.setSize(500, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(newWorkButton)) {
            EditExperienceWindow newExp = new EditExperienceWindow(-1, null, null);
            newExp.showFrame();
        } else if (source.equals(newEduButton)) {
            EditEducationWindow newEdu = new EditEducationWindow(-1, null, null);
            newEdu.showFrame();
        }
    }
}
