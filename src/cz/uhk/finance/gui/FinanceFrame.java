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
        JButton btEdit = new JButton("Editace");
        JButton btStav = new JButton("Stav");
        JButton btPrijmy = new JButton("Prijmy");
        JButton btVydaje = new JButton("Vydaje");

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

        //POZOR: od Javy 8 lze to same zapsat pomoci Lambda vyrazu
        btEdit.addActionListener((e) -> editace());
        btStav.addActionListener((e) -> {
            taVystup.append(String.format("Stav uctu je %.2f\n", evidence.getStavUctu()));
        });
        btPrijmy.addActionListener((e) -> {
            taVystup.append(String.format("Soucet prijmu je %.2f\n", evidence.getPrijmy()));
        });
        btVydaje.addActionListener((e) -> {
            taVystup.append(String.format("Soucet vydaju je %.2f\n", evidence.getVydaje()));
        });

        tb.add(btPridej);
        tb.add(btVypis);
        tb.add(btEdit);
        tb.add(btStav);
        tb.add(btPrijmy);
        tb.add(btVydaje);
    }

    private void editace() {
        String poradiStr = JOptionPane.showInputDialog("Zadejte cislo polozky pro upravu");
        try {
            int poradi = Integer.valueOf(poradiStr);
            poradi--; //zmensime o 1, seznamy indexujeme od 0 !!!
            if (poradi >=0 && poradi < evidence.getPolozky().size()) { //je index ve spravnem rozsahu
                Polozka p = new PolozkaDialog(this).editujPolozku(evidence.getPolozky().get(poradi)); //-1 kvuli indexovani od 0
                if (p != null) {
                    evidence.getPolozky().set(poradi, p);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Neplatne cislo polozky", "Chyba", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {

        }
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
        //zkusime jiny motiv
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FinanceFrame().setVisible(true);
            }
        });
    }
}
