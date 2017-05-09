package herramienta.testing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollBar;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

public class VentanaPincipal extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPincipal frame = new VentanaPincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public VentanaPincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		getContentPane().setLayout(null);
		
		JLabel lblSeleccione = new JLabel("Seleccione un archivo de la lista");
		lblSeleccione.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSeleccione.setForeground(new Color(112, 128, 144));
		lblSeleccione.setBounds(10, 11, 190, 21);
		getContentPane().add(lblSeleccione);
		
		String[] archivos = {"Archivo","Archivo","Archivo","Archivo","Archivo","Archivo","Archivo","Archivo","Archivo","Archivo",};
	     
		JList LA = new JList(archivos);
		LA.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		LA.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	    JScrollPane listAchivos = new JScrollPane();
		listAchivos.setBounds(10, 31, 518, 145);
		listAchivos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	    listAchivos.setViewportView(LA);
	    
		getContentPane().add(listAchivos);
		
		
		String[] clases = {"Clase","Clase","Clase","Clase","Clase","Clase","Clase","Clase","Clase","Clase","Clase","Clase"};
		
		JList LC = new JList(clases);
		LC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		LC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	    JScrollPane listClases = new JScrollPane();
		listClases.setBounds(10, 202, 253, 145);
		listClases.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	    listClases.setViewportView(LC);
	    
		getContentPane().add(listClases);
		
		
		
		String[] metodos = {"Metodo","Metodo","Metodo","Metodo","Metodo","Metodo","Metodo","Metodo","Metodo","Metodo","Metodo",};
		
		JList LM = new JList(metodos);
		LM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		LM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	    JScrollPane listMetodos = new JScrollPane();
		listMetodos.setBounds(272, 200, 255, 145);
		listMetodos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	    listMetodos.setViewportView(LM);
	    
		getContentPane().add(listMetodos);
		
		
		JLabel lblSeleccioneLaClase = new JLabel("Seleccione la clase");
		lblSeleccioneLaClase.setForeground(new Color(112, 128, 144));
		lblSeleccioneLaClase.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSeleccioneLaClase.setBounds(10, 180, 190, 21);
		getContentPane().add(lblSeleccioneLaClase);
		
		JLabel lblSeleccioneElMetodo = new JLabel("Seleccione el metodo");
		lblSeleccioneElMetodo.setForeground(new Color(112, 128, 144));
		lblSeleccioneElMetodo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSeleccioneElMetodo.setBounds(272, 180, 190, 21);
		getContentPane().add(lblSeleccioneElMetodo);
		
		JLabel lblCodigoFuente = new JLabel("Codigo Fuente");
		lblCodigoFuente.setForeground(new Color(112, 128, 144));
		lblCodigoFuente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCodigoFuente.setBounds(10, 351, 190, 21);
		getContentPane().add(lblCodigoFuente);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setContentType("text/html");
		textPane.setBounds(10, 370, 518, 265);
		textPane.setBorder(new LineBorder(new Color(220, 220, 220)));
		getContentPane().add(textPane);
		
		JPanel pnlAnalisis = new JPanel();
		pnlAnalisis.setBackground(new Color(245, 245, 245));
		pnlAnalisis.setBorder(new LineBorder(new Color(230, 230, 250), 1, true));
		pnlAnalisis.setBounds(534, 31, 244, 604);
		getContentPane().add(pnlAnalisis);
		pnlAnalisis.setLayout(null);
		
		JLabel lblAnalisisDelMetodo = new JLabel("Analisis del Metodo");
		lblAnalisisDelMetodo.setForeground(new Color(112, 128, 144));
		lblAnalisisDelMetodo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAnalisisDelMetodo.setBounds(534, 11, 190, 21);
		getContentPane().add(lblAnalisisDelMetodo);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Analizar\r\n");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Abrir Proyecto...");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Salir");
		mnNewMenu.add(mntmNewMenuItem_1);


	}
}
