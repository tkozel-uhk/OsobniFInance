package cz.uhk.finance.gui;

import cz.uhk.finance.data.Evidence;
import cz.uhk.finance.data.EvidenceImpl;
import cz.uhk.finance.data.Polozka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class FinanceFrame extends JFrame {
    private JTextArea taVystup = new JTextArea(25,40);

    private Evidence evidence = new EvidenceImpl();

    public FinanceFrame() {
        super("Osobni finance");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initGui();
    }

    private void initGui() {
        createToolbar();

        add(new JScrollPane(taVystup), BorderLayout.CENTER);

        pack();
    }

    private void createToolbar() {
        JToolBar tb = new JToolBar(JToolBar.HORIZONTAL);
        add(tb, BorderLayout.NORTH);

        JButton btPridej = new JButton("Nova polozka");
        JButton btVypis = new JButton("Vypsat");

        btPridej.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novaPolozka();
            }
        });

        btVypis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vypisPolozek();
            }
        });

        tb.add(btPridej);
        tb.add(btVypis);
    }

    private void vypisPolozek() {
        taVystup.setText("");
        for(Polozka p : evidence.getPolozky()) {
            String dats = p.getDatum().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
            taVystup.append(String.format("%s\t%s\t%.2f\n", dats, p.getNazev(), p.getCastka()));
        }
    }

    private void novaPolozka() {
        PolozkaDialog dlg = new PolozkaDialog(FinanceFrame.this);
        Polozka p = dlg.vytvorNovouPolozku();
        if (p != null) {
            evidence.pridatPolozku(p);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FinanceFrame().setVisible(true);
            }
        });
    }
}
