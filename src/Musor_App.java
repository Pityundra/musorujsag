
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.List;

public class Musor_App extends JFrame {

	private JPanel contentPane;
	private JTextField cimTextField;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable table;

	private JPanel panel_gombok;
	private Musor_Muveletek musor_muveletek;
	private JButton addMusorbt;
	private JButton updateMusorbt;
	private JButton deleteMusorbt;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Musor_App frame = new Musor_App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Musor_App() {

		try {
			musor_muveletek = new Musor_Muveletek();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
		
		setTitle("Műsorújság");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(new Color(240, 128, 128));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(new Color(240, 128 ,128));
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblEnterMusor = new JLabel("Írd be a műsor címét: ");
		panel.add(lblEnterMusor);
		
		cimTextField = new JTextField();
		panel.add(cimTextField);
		cimTextField.setColumns(10);
		
		btnSearch = new JButton("Keresés");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String cime = cimTextField.getText();

					List<Musor> musorok = null;

					if (cime != null && cime.trim().length() > 0) {
						musorok = musor_muveletek.musorKereso(cime);
					} else {
						musorok = musor_muveletek.musorokListazasa();
					}

					Musor_Table model = new Musor_Table(musorok);
					
					table.setModel(model);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(Musor_App.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		panel.add(btnSearch);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.ITALIC, 14));
		table.setBackground(new Color(230, 232, 250));
		scrollPane.setViewportView(table);

		panel_gombok = new JPanel();
		Box box = Box.createVerticalBox();
		panel_gombok.add(box);
		panel_gombok.setBackground(new Color(240, 128, 128));
		contentPane.add(panel_gombok, BorderLayout.WEST);

		addMusorbt = new JButton("Műsor felvétele");
		addMusorbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MusorHozzadoAblak dialog = new MusorHozzadoAblak(Musor_App.this, musor_muveletek);

				dialog.setVisible(true);
			}
		});
		box.add(addMusorbt);

		updateMusorbt = new JButton("Műsor változtatása");
		updateMusorbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int sor = table.getSelectedRow();

				if (sor < 0) {
					JOptionPane.showMessageDialog(Musor_App.this, "Ki kell választanod egy műsort!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				Musor musor = (Musor) table.getValueAt(sor, Musor_Table.OBJECT_COL);

				MusorHozzadoAblak ablak = new MusorHozzadoAblak(Musor_App.this, musor_muveletek,
						musor, true);

				ablak.setVisible(true);

			}
		});
		box.add(Box.createVerticalStrut(40));
		box.add(updateMusorbt);

		deleteMusorbt = new JButton("Műsor törlése");
		deleteMusorbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int sor = table.getSelectedRow();

					if(sor<0) {
						JOptionPane.showMessageDialog(Musor_App.this, "Ki kell " +
								"választanod egy műsort!", "Hiba", JOptionPane.ERROR_MESSAGE );
						return;
					}

					int valasz = JOptionPane.showConfirmDialog(Musor_App.this, "Biztos " +
							"törölni akarod ezt a műsort?", "Megerősítés", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(valasz != JOptionPane.YES_OPTION) {
						return;
					}

					Musor musor = (Musor) table.getValueAt(sor, Musor_Table.OBJECT_COL);

					musor_muveletek.RemoveMusor(musor.getCime());

					refreshMusorView();

					JOptionPane.showMessageDialog(Musor_App.this, "Műsor sikeresen törölve!",
							"Műsor törölve", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception exc){
					JOptionPane.showMessageDialog(Musor_App.this, "Error: " + exc.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		box.add(Box.createVerticalStrut(40));
		box.add(deleteMusorbt);

	}

	public void refreshMusorView() {

		try {
			List<Musor> musorok = musor_muveletek.musorokListazasa();

			Musor_Table model = new Musor_Table(musorok);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}
