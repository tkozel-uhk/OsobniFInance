package cz.uhk.finance.gui;

import cz.uhk.finance.data.Polozka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class PolozkaDialog extends JDialog {
    private JTextField tfDatum = new JTextField(10);
    private JTextField tfNazev = new JTextField(15);
    private JTextField tfCastka = new JTextField(10);
    private JButton btOk;
    private JButton btStorno;

    private boolean ok = false;

    public PolozkaDialog(Frame owner) {
        super(owner, "Editace polozky", true);  //setModal(true)
        
        initGui();
    }

    private void initGui() {
        JPanel pnlCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel labDatum = new JLabel("Datum");
        labDatum.setLabelFor(tfDatum);
        labDatum.setDisplayedMnemonic('D');
        pnlCenter.add(labDatum);
        pnlCenter.add(tfDatum);

        JLabel labNazev = new JLabel("Nazev");
        labNazev.setLabelFor(tfNazev);
        labNazev.setDisplayedMnemonic('N');
        pnlCenter.add(labNazev);
        pnlCenter.add(tfNazev);

        JLabel labCastka = new JLabel("Castka");
        labCastka.setLabelFor(tfCastka);
        labCastka.setDisplayedMnemonic('C');
        pnlCenter.add(labCastka);
        pnlCenter.add(tfCastka);

        add(pnlCenter, BorderLayout.CENTER);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));

        btOk = new JButton("OK");
        btStorno = new JButton("Storno");

        pnlButtons.add(btOk);
        pnlButtons.add(btStorno);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ok = e.getSource() == btOk;
                if (ok && validace()) {
                    setVisible(false);
                } else if (!ok){ //storno
                    setVisible(false);
                }
            }
        };

        btOk.addActionListener(listener);
        btStorno.addActionListener(listener);

        getRootPane().setDefaultButton(btOk);

        add(pnlButtons, BorderLayout.SOUTH);

        pack();
    }

    private boolean validace() {
        boolean valid = true;

        try {
            LocalDate datum = LocalDate.parse(tfDatum.getText(), DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Chybny format datumu",
                    "CHYBA", JOptionPane.ERROR_MESSAGE);
            valid = false;
        }

        try {
            double castka = Double.parseDouble(tfCastka.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Chybny format cisla",
                    "CHYBA", JOptionPane.ERROR_MESSAGE);
            valid = false;
        }

        return valid;
    }

    public Polozka vytvorNovouPolozku() {
        tfCastka.setText("0");
        tfNazev.setText("");
        tfDatum.setText(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        setVisible(true);
        if (ok) {
            LocalDate dat = LocalDate.parse(tfDatum.getText(), DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
            Polozka p = new Polozka(dat, tfNazev.getText(), Double.valueOf(tfCastka.getText()));

            return p;
        } else {
            return null;
        }
    }

    public Polozka editujPolozku(Polozka p) {
        tfNazev.setText(p.getNazev());
        tfCastka.setText(String.valueOf(p.getCastka()));
        tfDatum.setText(p.getDatum().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        setVisible(true);
        if (ok) {
            LocalDate dat = LocalDate.parse(tfDatum.getText(), DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
            Polozka pNew = new Polozka(dat, tfNazev.getText(), Double.valueOf(tfCastka.getText()));
            return pNew;
        } else {
            return null;
        }
    }

//    public static void main(String[] args) {
//        PolozkaDialog dlg = new PolozkaDialog(null);
//
//        Polozka p = dlg.vytvorNovouPolozku();
//        if (p != null) {
//            System.out.printf("%s %.2f\n", p.getNazev(), p.getCastka());
//            Polozka p2 = dlg.editujPolozku(p);
//            if (p2 != null) {
//                System.out.printf("%s %.2f\n", p2.getNazev(), p2.getCastka());
//            }
//        }
//    }
}
