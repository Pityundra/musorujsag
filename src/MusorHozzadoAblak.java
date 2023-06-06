import java.awt.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class MusorHozzadoAblak extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField címeTextField;
    private JTextField forgatási_országTextField;
    private JTextField kiadás_éveTextField;
    private JTextField korhatárTextField;
    private JTextField stílusTextField;
    private JTextField időtartamTextField;

    private Musor_Muveletek musor_Muveletek;

    private Musor_App musor_App;

    private Musor elozoMusor;
    private boolean frissites;

    public MusorHozzadoAblak(Musor_App musor_App, Musor_Muveletek musor_Muveletek) {
        this();
        this.musor_Muveletek= musor_Muveletek;
        this.musor_App= musor_App;
    }

    public MusorHozzadoAblak(Musor_App musor_App, Musor_Muveletek musor_Muveletek, Musor elozoMusor, boolean frissites) {
        this();
        this.musor_Muveletek= musor_Muveletek;
        this.musor_App= musor_App;
        this.elozoMusor = elozoMusor;
        this.frissites = frissites;

        if(frissites) {
            setTitle("Műsor változtatása");
            frissitGui(elozoMusor);
        }
    }

    private void frissitGui(Musor musor) {

        címeTextField.setText(musor.getCime());
        forgatási_országTextField.setText(musor.getForgatasi_orszag());
        kiadás_éveTextField.setText(musor.getKiadas_eve());
        korhatárTextField.setText(Integer.toString(musor.getKorhatar()));
        stílusTextField.setText(musor.getStilus());
        időtartamTextField.setText(Integer.toString(musor.getIdo_tartam()));
    }

    
    public MusorHozzadoAblak() {
        if(!frissites) {
        setTitle("Műsor felvétele");
        setBounds(100, 100, 450, 256);
        setBackground(new Color(240, 128, 128));
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setBackground(new Color(240,128,128));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new FormLayout(new ColumnSpec[]{
                FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC,
                FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"),},
                new RowSpec[]{
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,}));
        {
            JLabel label_címe = new JLabel("Címe");
            contentPanel.add(label_címe, "2, 2, right, default");
        }
        {
            címeTextField = new JTextField();
            contentPanel.add(címeTextField, "4, 2, fill, default");
            címeTextField.setColumns(14);
        }
        {
            JLabel label_forgatási_ország = new JLabel("Forgatási ország");
            contentPanel.add(label_forgatási_ország, "2, 4, right, default");
        }
        {
            forgatási_országTextField = new JTextField();
            contentPanel.add(forgatási_országTextField, "4, 4, fill, default");
            forgatási_országTextField.setColumns(14);
        }
        {
            JLabel label_kiadás_éve = new JLabel("Kiadás éve");
            contentPanel.add(label_kiadás_éve, "2, 6, right, default");
        }
        {
            kiadás_éveTextField = new JTextField();
            contentPanel.add(kiadás_éveTextField, "4, 6, fill, default");
            kiadás_éveTextField.setColumns(14);
        }
        {
            JLabel label_korhatár = new JLabel("Korhatár");
            contentPanel.add(label_korhatár, "2, 8, right, default");
        }
        {
            korhatárTextField = new JTextField();
            contentPanel.add(korhatárTextField, "4, 8, fill, default");
            korhatárTextField.setColumns(10);
        }
        {
            JLabel label_stílus = new JLabel("Stílus");
            contentPanel.add(label_stílus, "2, 10, right, default");
        }
        {
            stílusTextField = new JTextField();
            contentPanel.add(stílusTextField, "4, 10, fill, default");
            stílusTextField.setColumns(14);
        }
        {
            JLabel label_idő_tartam = new JLabel("Időtartam");
            contentPanel.add(label_idő_tartam, "2, 12, right, default");
        }
        {
            időtartamTextField = new JTextField();
            contentPanel.add(időtartamTextField, "4, 12, fill, default");
            időtartamTextField.setColumns(14);
        }
        {
            JPanel gombFelulet = new JPanel();
            gombFelulet.setBackground(new Color(240, 128, 128));
            gombFelulet.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(gombFelulet, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        saveMusor();
                    }
                });
                okButton.setActionCommand("OK");
                gombFelulet.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Visszavonás");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setVisible(false);
                        dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                gombFelulet.add(cancelButton);
            }
        }
    } else {
            setTitle("Műsor felvétele");
            setBounds(100, 100, 450, 256);
            getContentPane().setLayout(new BorderLayout());
            contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            contentPanel.setBackground(new Color(240,128,128));
            getContentPane().add(contentPanel, BorderLayout.CENTER);
            contentPanel.setLayout(new FormLayout(new ColumnSpec[]{
                    FormFactory.RELATED_GAP_COLSPEC,
                    FormFactory.DEFAULT_COLSPEC,
                    FormFactory.RELATED_GAP_COLSPEC,
                    ColumnSpec.decode("default:grow"),},
                    new RowSpec[]{
                            FormFactory.RELATED_GAP_ROWSPEC,
                            FormFactory.DEFAULT_ROWSPEC,
                            FormFactory.RELATED_GAP_ROWSPEC,
                            FormFactory.DEFAULT_ROWSPEC,
                            FormFactory.RELATED_GAP_ROWSPEC,
                            FormFactory.DEFAULT_ROWSPEC,
                            FormFactory.RELATED_GAP_ROWSPEC,
                            FormFactory.DEFAULT_ROWSPEC,
                            FormFactory.RELATED_GAP_ROWSPEC,
                            FormFactory.DEFAULT_ROWSPEC,
                            FormFactory.RELATED_GAP_ROWSPEC,
                            FormFactory.DEFAULT_ROWSPEC,}));
            {
                JLabel label_címe = new JLabel("Címe");
                contentPanel.add(label_címe, "2, 2, right, default");
            }
            {
                címeTextField = new JTextField();
                contentPanel.add(címeTextField, "4, 2, fill, default");
                címeTextField.setColumns(14);
            }
            {
                JLabel label_forgatási_ország = new JLabel("Forgatási ország");
                contentPanel.add(label_forgatási_ország, "2, 4, right, default");
            }
            {
                forgatási_országTextField = new JTextField();
                contentPanel.add(forgatási_országTextField, "4, 4, fill, default");
                forgatási_országTextField.setColumns(14);
            }
            {
                JLabel label_kiadás_éve = new JLabel("Kiadás éve");
                contentPanel.add(label_kiadás_éve, "2, 6, right, default");
            }
            {
                kiadás_éveTextField = new JTextField();
                contentPanel.add(kiadás_éveTextField, "4, 6, fill, default");
                kiadás_éveTextField.setColumns(14);
            }
            {
                JLabel label_korhatár = new JLabel("Korhatár");
                contentPanel.add(label_korhatár, "2, 8, right, default");
            }
            {
                korhatárTextField = new JTextField();
                contentPanel.add(korhatárTextField, "4, 8, fill, default");
                korhatárTextField.setColumns(10);
            }
            {
                JLabel label_stílus = new JLabel("Stílus");
                contentPanel.add(label_stílus, "2, 10, right, default");
            }
            {
                stílusTextField = new JTextField();
                contentPanel.add(stílusTextField, "4, 10, fill, default");
                stílusTextField.setColumns(14);
            }
            {
                JLabel label_idő_tartam = new JLabel("Időtartam");
                contentPanel.add(label_idő_tartam, "2, 12, right, default");
            }
            {
                időtartamTextField = new JTextField();
                contentPanel.add(időtartamTextField, "4, 12, fill, default");
                időtartamTextField.setColumns(14);
            }
            {
                JPanel gombFelulet = new JPanel();
                gombFelulet.setBackground(new Color(240, 128, 128));
                gombFelulet.setLayout(new FlowLayout(FlowLayout.RIGHT));
                getContentPane().add(gombFelulet, BorderLayout.SOUTH);
                {
                    JButton okButton = new JButton("OK");
                    okButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            saveMusor();
                        }
                    });
                    okButton.setActionCommand("OK");
                    gombFelulet.add(okButton);
                    getRootPane().setDefaultButton(okButton);
                }
                {
                    JButton cancelButton = new JButton("Visszavonás");
                    cancelButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            setVisible(false);
                            dispose();
                        }
                    });
                    cancelButton.setActionCommand("Cancel");
                    gombFelulet.add(cancelButton);
                }
            }
        }
    }

    protected int convertStringToInt(String number) {

        int result;

        try {
            int numberInt = Integer.parseInt(number);

            result = Integer.valueOf(numberInt);
        } catch (Exception exc) {
            System.out.println("Hibás érték!");
            result = Integer.valueOf(0);
        }

        return result;
    }

    protected void saveMusor() {

        String címe = címeTextField.getText();
        String forgási_ország = forgatási_országTextField.getText();
        String kiadás_éve = kiadás_éveTextField.getText();
        String korhatárStr = korhatárTextField.getText(); int korhatár = convertStringToInt(korhatárStr);
        String stílus = stílusTextField.getText();
        String idő_tartamStr = időtartamTextField.getText(); int idő_tartam = convertStringToInt(idő_tartamStr);

        Musor musor = null;

        if(frissites) {
            musor = elozoMusor;
            musor.setCime(címe);
            musor.setForgatasi_orszag(forgási_ország);
            musor.setKiadas_eve(kiadás_éve);
            musor.setKorhatar(korhatár);
            musor.setStilus(stílus);
            musor.setIdo_tartam(idő_tartam);

        } else {
            musor = new Musor(címe, forgási_ország, kiadás_éve, korhatár, stílus, idő_tartam);
        }

        try {
            if(frissites) {
                musor_Muveletek.updateMusor(musor);
            } else {
                musor_Muveletek.addMusor(musor);
            }

            setVisible(false);
            dispose();

            musor_App.refreshMusorView();

            JOptionPane.showMessageDialog(musor_App,
                    "Műsor sikeresen hozzáadva.",
                    "Műsor hozzáadva",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(
                    musor_App,
                    "Hiba mentés közben:  "
                            + exc.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

}

