package cz.uhk.finance.gui;

import cz.uhk.finance.data.Polozka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PolozkaDialog extends JDialog {
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
                setVisible(false);
            }
        };

        btOk.addActionListener(listener);
        btStorno.addActionListener(listener);

        getRootPane().setDefaultButton(btOk);

        add(pnlButtons, BorderLayout.SOUTH);

        pack();
    }

    public Polozka vytvorNovouPolozku() {
        tfCastka.setText("0");
        tfNazev.setText("");
        setVisible(true);
        if (ok) {
            Polozka p = new Polozka(tfNazev.getText(), Double.valueOf(tfCastka.getText()));

            return p;
        } else {
            return null;
        }
    }

    public Polozka editujPolozku(Polozka p) {
        tfNazev.setText(p.getNazev());
        tfCastka.setText(String.valueOf(p.getCastka()));
        setVisible(true);
        if (ok) {
            Polozka pNew = new Polozka(tfNazev.getText(), Double.valueOf(tfCastka.getText()));
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
