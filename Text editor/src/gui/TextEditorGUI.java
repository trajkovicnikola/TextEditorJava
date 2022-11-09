package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;

import javax.swing.ScrollPaneConstants;
import javax.swing.JSpinner;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.ComponentOrientation;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TextEditorGUI extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JSpinner spinnerSize;
	private JButton btnColor;
	private JLabel lblNewLabel;
	private JComboBox comboBox;
	private JLabel lblNewLabel_1;
	private String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	private JMenuItem menuItemSave;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem menuSave;
	private JMenuItem menuOpen;
	private JMenuItem menuExit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextEditorGUI frame = new TextEditorGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TextEditorGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int option = JOptionPane.showConfirmDialog(contentPane,
						"Are you sure you want to exit?" + "\n" + "Any unsaved changes will be lost.", "Exit",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		setTitle("Text editor");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 710, 447);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel_1(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setAlignmentX(Component.LEFT_ALIGNMENT);
			panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			panel.setSize(new Dimension(100, 10));
			panel.add(getLblNewLabel());
			panel.add(getSpinnerSize());
			panel.add(getBtnColor());
			panel.add(getLblNewLabel_1());
			panel.add(getComboBox());
		}
		return panel;
	}

	private JSpinner getSpinnerSize() {
		if (spinnerSize == null) {
			spinnerSize = new JSpinner();
			spinnerSize.setValue(20);
			spinnerSize.setSize(new Dimension(25, 50));
			spinnerSize.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					textArea.setFont(
							new Font(textArea.getFont().getFamily(), Font.PLAIN, (int) spinnerSize.getValue()));
				}
			});
		}
		return spinnerSize;
	}

	private JButton getBtnColor() {
		if (btnColor == null) {
			btnColor = new JButton("Color");
			btnColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JColorChooser colorChooser = new JColorChooser();
					Color color = colorChooser.showDialog(contentPane, "Choose a color", Color.BLACK);
					textArea.setForeground(color);
				}
			});
		}
		return btnColor;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Size:");
		}
		return lblNewLabel;
	}

	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox(fonts);
			comboBox.setSelectedItem("Arial");
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textArea.setFont(
							new Font((String) comboBox.getSelectedItem(), Font.PLAIN, (int) spinnerSize.getValue()));
				}
			});
		}
		return comboBox;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Style:");
		}
		return lblNewLabel_1;
	}

	public JMenuItem getMenuItemSave() {

		return menuItemSave;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(getScrollPane_1(), BorderLayout.CENTER);
			panel_1.add(getPanel(), BorderLayout.NORTH);
		}
		return panel_1;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTextArea_1());
		}
		return scrollPane;
	}

	private JTextArea getTextArea_1() {
		if (textArea == null) {
			textArea = new JTextArea();
		}
		return textArea;
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnNewMenu());
		}
		return menuBar;
	}

	private JMenu getMnNewMenu() {
		if (mnNewMenu == null) {
			mnNewMenu = new JMenu("File");
			mnNewMenu.add(getMenuSave());
			mnNewMenu.add(getMenuOpen());
			mnNewMenu.add(getMenuExit());
		}
		return mnNewMenu;
	}

	private JMenuItem getMenuSave() {
		if (menuSave == null) {
			menuSave = new JMenuItem("Save");
			menuSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						JFileChooser fileChooser = new JFileChooser();
						FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
						fileChooser.setFileFilter(filter);
						int option = fileChooser.showSaveDialog(null);
						if (option == fileChooser.APPROVE_OPTION) {
							String path = fileChooser.getSelectedFile().getAbsolutePath();
							saveText(path);
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return menuSave;
	}

	private JMenuItem getMenuOpen() {
		if (menuOpen == null) {
			menuOpen = new JMenuItem("Open");
			menuOpen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						JFileChooser fileChooser = new JFileChooser();
						FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
						fileChooser.setFileFilter(filter);
						int option = fileChooser.showOpenDialog(null);
						if (option == fileChooser.APPROVE_OPTION) {
							String path = fileChooser.getSelectedFile().getAbsolutePath();
							String text = readText(path);
							textArea.setText(text);
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return menuOpen;
	}

	private JMenuItem getMenuExit() {
		if (menuExit == null) {
			menuExit = new JMenuItem("Exit");
			menuExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int option = JOptionPane.showConfirmDialog(contentPane,
							"Are you sure you want to exit?" + "\n" + "Any unsaved changes will be lost.", "Exit",
							JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION)
						System.exit(0);
				}
			});
		}
		return menuExit;
	}

	private void saveText(String path) throws Exception {
		BufferedWriter bw = new BufferedWriter(new FileWriter(path));
		bw.write(textArea.getText());
		bw.close();
	}

	private String readText(String path) throws Exception {
		String text = "";
		BufferedReader br = new BufferedReader(new FileReader(path));
		while (true) {
			String line = br.readLine();
			if (line == null)
				break;
			text = text + line + "\n";
		}
		return text;
	}
}
