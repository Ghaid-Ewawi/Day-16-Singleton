
/*
 *
 *  Copyright (C) 2019.  PSEU - Palestine Polytechnic University - All Rights Reserved
 *  * SPDX-License-Identifier: Apache-2.0
 *
 */

/*
 * Class source: https://www.javacodex.com/Swing/Swing-Calendar
 * Created by Basel.Altamimi on 8/4/2019.
 */

package Forms;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class SwingCalendar extends JFrame {

    DefaultTableModel model;
    Calendar cal = new GregorianCalendar();
    JLabel label;

    private static SwingCalendar instance;

    public static SwingCalendar getInstance() {
        if (instance == null)
            instance = new SwingCalendar();
        return instance;
    }

    // Todo: Refactor this constructor so the program will create only one copy of this object
    private SwingCalendar() { // Constructor

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Swing Calendar");
        this.setSize(300, 200);
        this.setLayout(new BorderLayout());
        this.setVisible(true);


        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton b1 = new JButton("<-");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cal.add(Calendar.MONTH, -1);
                updateMonth();
            }
        });

        JButton b2 = new JButton("->");
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cal.add(Calendar.MONTH, +1);
                updateMonth();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(b1, BorderLayout.WEST);
        panel.add(label, BorderLayout.CENTER);
        panel.add(b2, BorderLayout.EAST);


        String[] columns = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        model = new DefaultTableModel(null, columns);
        JTable table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);

        this.add(panel, BorderLayout.NORTH);
        this.add(pane, BorderLayout.CENTER);

        this.updateMonth();

    }

    void updateMonth() {
        cal.set(Calendar.DAY_OF_MONTH, 1);

        String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        int year = cal.get(Calendar.YEAR);
        label.setText(month + " " + year);

        int startDay = cal.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);

        model.setRowCount(0);
        model.setRowCount(weeks);

        int i = startDay - 1;
        for (int day = 1; day <= numberOfDays; day++) {
            model.setValueAt(day, i / 7, i % 7);
            i = i + 1;
        }

    }
}