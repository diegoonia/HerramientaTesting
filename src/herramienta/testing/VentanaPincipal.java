package herramienta.testing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMessages;


public class VentanaPincipal extends JFrame implements ListSelectionListener {

	private Recorrer rec = new Recorrer();
	private static final long serialVersionUID = 1L;
	private static File pathWorkSpace; // aca poner tu path
	private static VentanaPincipal frame;
	private JList lP = null;
	private JList lC = null;
	private JList lM = null;
	private String proySeleccionado = null;
	ArrayList<String> arrayRutas = null;
	ArrayList<String> arrayRutasClases = null;
	ArrayList<String> metodos = null;
	private JScrollPane listClases = null;
	private JScrollPane listMetodos = null;
	  
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pathWorkSpace = new File(" ");
					frame = new VentanaPincipal();
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
		
		JLabel lblSeleccione = new JLabel("Seleccione un proyecto de la lista");
		lblSeleccione.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSeleccione.setForeground(new Color(112, 128, 144));
		lblSeleccione.setBounds(10, 11, 190, 21);
		getContentPane().add(lblSeleccione);
		
		ArrayList<String> arrayProyectos = new ArrayList<String>();
		ArrayList<String> clases = new ArrayList<String>();
		ArrayList<String> metodos = new ArrayList<String>();
		arrayRutas = new ArrayList<String>();
		arrayRutasClases = new ArrayList<String>();
		
		if (pathWorkSpace.exists())
		{
			Recorrer r = new Recorrer();
			r.getAllFiles(pathWorkSpace, arrayProyectos, arrayRutas);
		}
		else
		{
			arrayProyectos.add("Elija un WorkSpace");
		}

		lP = new JList(arrayProyectos.toArray());
		lP.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//Ponemos la lista a la escucha de cambios
		lP.addListSelectionListener(this);
	    
		JScrollPane listProyectos = new JScrollPane();
		listProyectos.setBounds(10, 31, 518, 145);
		listProyectos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	    listProyectos.setViewportView(lP);
		getContentPane().add(listProyectos);
		
	    listClases = new JScrollPane();
		listClases.setBounds(10, 202, 253, 145);
		listClases.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	    
		listMetodos = new JScrollPane();
		listMetodos.setBounds(272, 200, 255, 145);
		listMetodos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	    
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
		
		JButton btnAnalizar = new JButton();  
		btnAnalizar.setSize(245,50);
		btnAnalizar.setBounds(534, 40, 245, 50);
		btnAnalizar.setVisible(true);
		btnAnalizar.setText("Analizar");
	    getContentPane().add(btnAnalizar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 370, 518, 265);
		getContentPane().add(scrollPane);
		
		JTextArea txtCodigo = new JTextArea();
		txtCodigo.setEditable(false);
		scrollPane.setViewportView(txtCodigo);
		
		
		JPanel pnlAnalisis = new JPanel();
		pnlAnalisis.setBackground(new Color(245, 245, 245));
		pnlAnalisis.setBorder(new LineBorder(new Color(230, 230, 250), 1, true));
		pnlAnalisis.setBounds(544, 31, 244, 604);
		getContentPane().add(pnlAnalisis);
		pnlAnalisis.setLayout(null);
		
		JLabel lblTituloCantCase = new JLabel("Case: ");
		lblTituloCantCase.setBounds(10, 319, 33, 21);
		pnlAnalisis.add(lblTituloCantCase);
		lblTituloCantCase.setForeground(new Color(112, 128, 144));
		lblTituloCantCase.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblTituloComplej = new JLabel("Complejidad Ciclomatica:");
		lblTituloComplej.setBounds(6, 492, 150, 21);
		pnlAnalisis.add(lblTituloComplej);
		lblTituloComplej.setForeground(Color.BLACK);
		lblTituloComplej.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblCantCase = new JLabel("-");
		lblCantCase.setBounds(53, 319, 26, 21);
		pnlAnalisis.add(lblCantCase);
		lblCantCase.setForeground(new Color(0, 0, 0));
		lblCantCase.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblComplej = new JLabel("-");
		lblComplej.setBounds(166, 492, 190, 21);
		pnlAnalisis.add(lblComplej);
		lblComplej.setForeground(new Color(0, 0, 0));
		lblComplej.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblLongitudHalstead = new JLabel("Longitud Halstead:");
		lblLongitudHalstead.setForeground(Color.BLACK);
		lblLongitudHalstead.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLongitudHalstead.setBounds(6, 515, 112, 21);
		pnlAnalisis.add(lblLongitudHalstead);
		
		JLabel lblVolumenHalstead = new JLabel("Volumen Halstead:");
		lblVolumenHalstead.setForeground(Color.BLACK);
		lblVolumenHalstead.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVolumenHalstead.setBounds(6, 536, 112, 21);
		pnlAnalisis.add(lblVolumenHalstead);
		
		JLabel lblFanIn = new JLabel("Fan In:");
		lblFanIn.setForeground(Color.BLACK);
		lblFanIn.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFanIn.setBounds(6, 559, 92, 21);
		pnlAnalisis.add(lblFanIn);
		
		JLabel lblFanOut = new JLabel("Fan Out:");
		lblFanOut.setForeground(Color.BLACK);
		lblFanOut.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFanOut.setBounds(6, 583, 92, 21);
		pnlAnalisis.add(lblFanOut);
		
		JLabel valorLongHalst = new JLabel("-");
		valorLongHalst.setForeground(Color.BLACK);
		valorLongHalst.setFont(new Font("Tahoma", Font.BOLD, 11));
		valorLongHalst.setBounds(166, 515, 190, 21);
		pnlAnalisis.add(valorLongHalst);
		
		JLabel valorVolHals = new JLabel("-");
		valorVolHals.setForeground(Color.BLACK);
		valorVolHals.setFont(new Font("Tahoma", Font.BOLD, 11));
		valorVolHals.setBounds(166, 536, 190, 21);
		pnlAnalisis.add(valorVolHals);
		
		JLabel valorFanIn = new JLabel("-");
		valorFanIn.setForeground(Color.BLACK);
		valorFanIn.setFont(new Font("Tahoma", Font.BOLD, 11));
		valorFanIn.setBounds(166, 559, 190, 21);
		pnlAnalisis.add(valorFanIn);
		
		JLabel valorFanOut = new JLabel("-");
		valorFanOut.setForeground(Color.BLACK);
		valorFanOut.setFont(new Font("Tahoma", Font.BOLD, 11));
		valorFanOut.setBounds(166, 583, 190, 21);
		pnlAnalisis.add(valorFanOut);
		
		JLabel lblCantIf = new JLabel("-");
		lblCantIf.setBounds(37, 222, 27, 21);
		pnlAnalisis.add(lblCantIf);
		lblCantIf.setForeground(new Color(0, 0, 0));
		lblCantIf.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblCantFor = new JLabel("-");
		lblCantFor.setBounds(37, 254, 27, 21);
		pnlAnalisis.add(lblCantFor);
		lblCantFor.setForeground(new Color(0, 0, 0));
		lblCantFor.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblCantWhile = new JLabel("-");
		lblCantWhile.setBounds(48, 286, 27, 21);
		pnlAnalisis.add(lblCantWhile);
		lblCantWhile.setForeground(new Color(0, 0, 0));
		lblCantWhile.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblCantidades = new JLabel("Cantidades:");
		lblCantidades.setForeground(new Color(112, 128, 144));
		lblCantidades.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantidades.setBounds(6, 190, 91, 21);
		pnlAnalisis.add(lblCantidades);
		
		JLabel lblTituloCantIf = new JLabel("If:");
		lblTituloCantIf.setBounds(10, 222, 23, 21);
		pnlAnalisis.add(lblTituloCantIf);
		lblTituloCantIf.setForeground(new Color(112, 128, 144));
		lblTituloCantIf.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblTituloCantWhile = new JLabel("While:");
		lblTituloCantWhile.setBounds(10, 286, 34, 21);
		pnlAnalisis.add(lblTituloCantWhile);
		lblTituloCantWhile.setForeground(new Color(112, 128, 144));
		lblTituloCantWhile.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblTitPlus = new JLabel("+ :");
		lblTitPlus.setForeground(new Color(112, 128, 144));
		lblTitPlus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitPlus.setBounds(10, 351, 33, 21);
		pnlAnalisis.add(lblTitPlus);
		
		JLabel label_1 = new JLabel("- :");
		label_1.setForeground(new Color(112, 128, 144));
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(10, 383, 33, 21);
		pnlAnalisis.add(label_1);
		
		JLabel label_2 = new JLabel("* :");
		label_2.setForeground(new Color(112, 128, 144));
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(10, 415, 33, 21);
		pnlAnalisis.add(label_2);
		
		JLabel label_3 = new JLabel("&& :");
		label_3.setForeground(new Color(112, 128, 144));
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(10, 447, 33, 21);
		pnlAnalisis.add(label_3);
		
		JLabel label_4 = new JLabel("|| :");
		label_4.setForeground(new Color(112, 128, 144));
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(74, 222, 33, 21);
		pnlAnalisis.add(label_4);
		
		JLabel label_5 = new JLabel("/ : ");
		label_5.setForeground(new Color(112, 128, 144));
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setBounds(74, 254, 33, 21);
		pnlAnalisis.add(label_5);
		
		JLabel label_6 = new JLabel("= :");
		label_6.setForeground(new Color(112, 128, 144));
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setBounds(74, 287, 33, 21);
		pnlAnalisis.add(label_6);
		
		JLabel label_7 = new JLabel("!= :");
		label_7.setForeground(new Color(112, 128, 144));
		label_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_7.setBounds(74, 319, 33, 21);
		pnlAnalisis.add(label_7);
		
		JLabel label_8 = new JLabel("< :");
		label_8.setForeground(new Color(112, 128, 144));
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setBounds(74, 351, 33, 21);
		pnlAnalisis.add(label_8);
		
		JLabel label_9 = new JLabel("> : ");
		label_9.setForeground(new Color(112, 128, 144));
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_9.setBounds(74, 383, 33, 21);
		pnlAnalisis.add(label_9);
		
		JLabel lblCantPlus = new JLabel("-");
		lblCantPlus.setForeground(Color.BLACK);
		lblCantPlus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantPlus.setBounds(37, 351, 26, 21);
		pnlAnalisis.add(lblCantPlus);
		
		JLabel lblCantMinus = new JLabel("-");
		lblCantMinus.setForeground(Color.BLACK);
		lblCantMinus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantMinus.setBounds(37, 383, 26, 21);
		pnlAnalisis.add(lblCantMinus);
		
		JLabel lblCantMult = new JLabel("-");
		lblCantMult.setForeground(Color.BLACK);
		lblCantMult.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantMult.setBounds(37, 415, 26, 21);
		pnlAnalisis.add(lblCantMult);
		
		JLabel lblCantO = new JLabel("-");
		lblCantO.setForeground(Color.BLACK);
		lblCantO.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantO.setBounds(99, 222, 26, 21);
		pnlAnalisis.add(lblCantO);
		
		JLabel lblCantDIv = new JLabel("-");
		lblCantDIv.setForeground(Color.BLACK);
		lblCantDIv.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantDIv.setBounds(92, 254, 26, 21);
		pnlAnalisis.add(lblCantDIv);
		
		JLabel lblCantEqual = new JLabel("-");
		lblCantEqual.setForeground(Color.BLACK);
		lblCantEqual.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantEqual.setBounds(99, 286, 26, 21);
		pnlAnalisis.add(lblCantEqual);
		
		JLabel lblCantNotEqual = new JLabel("-");
		lblCantNotEqual.setForeground(Color.BLACK);
		lblCantNotEqual.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantNotEqual.setBounds(99, 319, 26, 21);
		pnlAnalisis.add(lblCantNotEqual);
		
		JLabel lblCantMay = new JLabel("-");
		lblCantMay.setForeground(Color.BLACK);
		lblCantMay.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantMay.setBounds(99, 351, 26, 21);
		pnlAnalisis.add(lblCantMay);
		
		JLabel lblCantMen = new JLabel("-");
		lblCantMen.setForeground(Color.BLACK);
		lblCantMen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantMen.setBounds(99, 383, 26, 21);
		pnlAnalisis.add(lblCantMen);
		
				JLabel lblTituloCantLineas = new JLabel("Lineas de Codigo:");
				lblTituloCantLineas.setBounds(6, 94, 190, 21);
				pnlAnalisis.add(lblTituloCantLineas);
				lblTituloCantLineas.setForeground(new Color(112, 128, 144));
				lblTituloCantLineas.setFont(new Font("Tahoma", Font.BOLD, 11));
				
				JLabel lblCantLineas = new JLabel("-");
				lblCantLineas.setBounds(166, 94, 190, 21);
				pnlAnalisis.add(lblCantLineas);
				lblCantLineas.setForeground(new Color(0, 0, 0));
				lblCantLineas.setFont(new Font("Tahoma", Font.BOLD, 11));
				
				JLabel lblTituloCantComent = new JLabel("Lineas de Comentario:");
				lblTituloCantComent.setBounds(6, 126, 190, 21);
				pnlAnalisis.add(lblTituloCantComent);
				lblTituloCantComent.setForeground(new Color(112, 128, 144));
				lblTituloCantComent.setFont(new Font("Tahoma", Font.BOLD, 11));
				
				JLabel lblCantComent = new JLabel("-");
				lblCantComent.setBounds(166, 126, 190, 21);
				pnlAnalisis.add(lblCantComent);
				lblCantComent.setForeground(new Color(0, 0, 0));
				lblCantComent.setFont(new Font("Tahoma", Font.BOLD, 11));
				
				JLabel lblTituloPorcComent = new JLabel("Porcentaje comentarios:");
				lblTituloPorcComent.setBounds(6, 158, 190, 21);
				pnlAnalisis.add(lblTituloPorcComent);
				lblTituloPorcComent.setForeground(new Color(112, 128, 144));
				lblTituloPorcComent.setFont(new Font("Tahoma", Font.BOLD, 11));
				
				JLabel lblPorcComent = new JLabel("-");
				lblPorcComent.setBounds(166, 158, 190, 21);
				pnlAnalisis.add(lblPorcComent);
				lblPorcComent.setForeground(new Color(0, 0, 0));
				lblPorcComent.setFont(new Font("Tahoma", Font.BOLD, 11));
				
				JLabel lblTituloCantFor = new JLabel("For:");
				lblTituloCantFor.setBounds(10, 254, 23, 21);
				pnlAnalisis.add(lblTituloCantFor);
				lblTituloCantFor.setForeground(new Color(112, 128, 144));
				lblTituloCantFor.setFont(new Font("Tahoma", Font.BOLD, 11));
				
				JLabel lblCantY = new JLabel("-");
				lblCantY.setForeground(Color.BLACK);
				lblCantY.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblCantY.setBounds(37, 449, 26, 21);
				pnlAnalisis.add(lblCantY);
				
				JLabel label_20 = new JLabel(">= : ");
				label_20.setForeground(new Color(112, 128, 144));
				label_20.setFont(new Font("Tahoma", Font.BOLD, 11));
				label_20.setBounds(74, 415, 33, 21);
				pnlAnalisis.add(label_20);
				
				JLabel label_21 = new JLabel("<= : ");
				label_21.setForeground(new Color(112, 128, 144));
				label_21.setFont(new Font("Tahoma", Font.BOLD, 11));
				label_21.setBounds(74, 447, 33, 21);
				pnlAnalisis.add(label_21);
				
				JLabel label_22 = new JLabel("==  : ");
				label_22.setForeground(new Color(112, 128, 144));
				label_22.setFont(new Font("Tahoma", Font.BOLD, 11));
				label_22.setBounds(149, 225, 33, 21);
				pnlAnalisis.add(label_22);
				
				JLabel label_23 = new JLabel("/=  : ");
				label_23.setForeground(new Color(112, 128, 144));
				label_23.setFont(new Font("Tahoma", Font.BOLD, 11));
				label_23.setBounds(149, 257, 33, 21);
				pnlAnalisis.add(label_23);
				
				JLabel label_24 = new JLabel("*=  : ");
				label_24.setForeground(new Color(112, 128, 144));
				label_24.setFont(new Font("Tahoma", Font.BOLD, 11));
				label_24.setBounds(149, 289, 33, 21);
				pnlAnalisis.add(label_24);
				
				JLabel label_25 = new JLabel("++ : ");
				label_25.setForeground(new Color(112, 128, 144));
				label_25.setFont(new Font("Tahoma", Font.BOLD, 11));
				label_25.setBounds(149, 319, 33, 21);
				pnlAnalisis.add(label_25);
				
				JLabel label_26 = new JLabel("-- : ");
				label_26.setForeground(new Color(112, 128, 144));
				label_26.setFont(new Font("Tahoma", Font.BOLD, 11));
				label_26.setBounds(149, 354, 33, 21);
				pnlAnalisis.add(label_26);
				
				JLabel label_27 = new JLabel("+= : ");
				label_27.setForeground(new Color(112, 128, 144));
				label_27.setFont(new Font("Tahoma", Font.BOLD, 11));
				label_27.setBounds(149, 386, 33, 21);
				pnlAnalisis.add(label_27);
				
				JLabel label_28 = new JLabel("-= : ");
				label_28.setForeground(new Color(112, 128, 144));
				label_28.setFont(new Font("Tahoma", Font.BOLD, 11));
				label_28.setBounds(149, 418, 33, 21);
				pnlAnalisis.add(label_28);
				
				JLabel lblCantMayEq = new JLabel("-");
				lblCantMayEq.setForeground(Color.BLACK);
				lblCantMayEq.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblCantMayEq.setBounds(99, 415, 26, 21);
				pnlAnalisis.add(lblCantMayEq);
				
				JLabel lblCantMenEq = new JLabel("-");
				lblCantMenEq.setForeground(Color.BLACK);
				lblCantMenEq.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblCantMenEq.setBounds(99, 447, 26, 21);
				pnlAnalisis.add(lblCantMenEq);
				
				JLabel lblCantIsEqual = new JLabel("-");
				lblCantIsEqual.setForeground(Color.BLACK);
				lblCantIsEqual.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblCantIsEqual.setBounds(180, 225, 26, 21);
				pnlAnalisis.add(lblCantIsEqual);
				
				JLabel lblCantDivEqual = new JLabel("-");
				lblCantDivEqual.setForeground(Color.BLACK);
				lblCantDivEqual.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblCantDivEqual.setBounds(180, 257, 26, 21);
				pnlAnalisis.add(lblCantDivEqual);
				
				JLabel lblCantMultEqual = new JLabel("-");
				lblCantMultEqual.setForeground(Color.BLACK);
				lblCantMultEqual.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblCantMultEqual.setBounds(180, 289, 26, 21);
				pnlAnalisis.add(lblCantMultEqual);
				
				JLabel lblCantInc = new JLabel("-");
				lblCantInc.setForeground(Color.BLACK);
				lblCantInc.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblCantInc.setBounds(180, 319, 26, 21);
				pnlAnalisis.add(lblCantInc);
				
				JLabel lblCantDec = new JLabel("-");
				lblCantDec.setForeground(Color.BLACK);
				lblCantDec.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblCantDec.setBounds(170, 354, 26, 21);
				pnlAnalisis.add(lblCantDec);
				
				JLabel lblCantPlusAcum = new JLabel("-");
				lblCantPlusAcum.setForeground(Color.BLACK);
				lblCantPlusAcum.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblCantPlusAcum.setBounds(180, 386, 26, 21);
				pnlAnalisis.add(lblCantPlusAcum);
				
				JLabel lblMinusEqual = new JLabel("-");
				lblMinusEqual.setForeground(Color.BLACK);
				lblMinusEqual.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblMinusEqual.setBounds(180, 418, 26, 21);
				pnlAnalisis.add(lblMinusEqual);
				
				JLabel lblTitOperandos = new JLabel("N1: ");
				lblTitOperandos.setForeground(new Color(112, 128, 144));
				lblTitOperandos.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblTitOperandos.setBounds(10, 470, 20, 21);
				pnlAnalisis.add(lblTitOperandos);
				
				JLabel lblCantOperandos = new JLabel("-");
				lblCantOperandos.setForeground(Color.BLACK);
				lblCantOperandos.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblCantOperandos.setBounds(37, 470, 26, 21);
				pnlAnalisis.add(lblCantOperandos);
				
				JLabel lblCantOperadores = new JLabel("-");
				lblCantOperadores.setForeground(Color.BLACK);
				lblCantOperadores.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblCantOperadores.setBounds(92, 470, 26, 21);
				pnlAnalisis.add(lblCantOperadores);
				
				JLabel lblN = new JLabel("N2: ");
				lblN.setForeground(new Color(112, 128, 144));
				lblN.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblN.setBounds(65, 470, 20, 21);
				pnlAnalisis.add(lblN);
				
				JLabel lbln1 = new JLabel("-");
				lbln1.setForeground(Color.BLACK);
				lbln1.setFont(new Font("Tahoma", Font.BOLD, 11));
				lbln1.setBounds(136, 470, 26, 21);
				pnlAnalisis.add(lbln1);
				
				JLabel lblN_1 = new JLabel("n1: ");
				lblN_1.setForeground(new Color(112, 128, 144));
				lblN_1.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblN_1.setBounds(109, 470, 20, 21);
				pnlAnalisis.add(lblN_1);
				
				JLabel lbln2 = new JLabel("-");
				lbln2.setForeground(Color.BLACK);
				lbln2.setFont(new Font("Tahoma", Font.BOLD, 11));
				lbln2.setBounds(193, 470, 26, 21);
				pnlAnalisis.add(lbln2);
				
				JLabel lblN_2 = new JLabel("n2: ");
				lblN_2.setForeground(new Color(112, 128, 144));
				lblN_2.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblN_2.setBounds(166, 470, 20, 21);
				pnlAnalisis.add(lblN_2);
		
		JLabel lblAnalisisDelMetodo = new JLabel("Analisis del Metodo");
		lblAnalisisDelMetodo.setForeground(new Color(112, 128, 144));
		lblAnalisisDelMetodo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAnalisisDelMetodo.setBounds(534, 11, 190, 21);
		getContentPane().add(lblAnalisisDelMetodo);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Analizar\r\n");
		menuBar.add(mnNewMenu);
		
		btnAnalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				txtCodigo.setText(" ");
				for(int i=0; i<rec.codigo.size(); i++)
				{
					txtCodigo.append(rec.codigo.get(i) + "\n");
				}
				
				lblCantLineas.setText( Integer.toString(rec.CantLineas) );
				lblCantComent.setText( Integer.toString(rec.CantComent) );
				lblCantIf.setText( Integer.toString(rec.CantIf) );
				lblCantFor.setText( Integer.toString(rec.CantFor)  );
				lblCantWhile.setText( Integer.toString(rec.CantWhile)  );
				lblCantCase.setText( Integer.toString(rec.CantCase)  );
				lblComplej.setText( Integer.toString(rec.CompCicl) );
				String porcentaje = Float.toString(rec.porcComent);
				if(porcentaje.length()>5)
				{
					porcentaje = porcentaje.substring(0, 5);
				}
				lblPorcComent.setText(porcentaje + " %");
				lblCantDec.setText(Integer.toString(rec.CantDec));
				lblCantInc.setText(Integer.toString(rec.CantInc));
				lblCantPlus.setText(Integer.toString(rec.CantPlus));
				lblCantMinus.setText(Integer.toString(rec.CantMinus));
				lblCantMult.setText(Integer.toString(rec.CantMult));
				lblCantY.setText(Integer.toString(rec.CantY));
				lblCantO.setText(Integer.toString(rec.CantO));
				lblCantDIv.setText(Integer.toString(rec.CantDiv));
				lblCantEqual.setText(Integer.toString(rec.CantEqual));
				lblCantNotEqual.setText(Integer.toString(rec.CantDist));
				lblCantMay.setText(Integer.toString(rec.CantMen));
				lblCantMen.setText(Integer.toString(rec.CantMay));
				lblCantMultEqual.setText(Integer.toString(rec.CantMultEq));
				lblCantDivEqual.setText(Integer.toString(rec.CantDivEq));
				lblCantIsEqual.setText(Integer.toString(rec.CantIsEqual));
				lblCantMayEq.setText(Integer.toString(rec.CantMenEq));
				lblCantMenEq.setText(Integer.toString(rec.CantMayEq));
				lblCantPlusAcum.setText(Integer.toString(rec.CantPlusEq));
				lblMinusEqual.setText(Integer.toString(rec.CantMinusEq));
				lblCantOperandos.setText(Integer.toString(rec.N1));
				lblCantOperadores.setText(Integer.toString(rec.N2));
				lbln1.setText(Integer.toString(rec.n1));
				lbln2.setText(Integer.toString(rec.n2));
				valorLongHalst.setText(Integer.toString(rec.longitudHalstead));
				String vol = Float.toString(rec.volumenHalstead);
				if(vol.length()>5)
				{
					vol = vol.substring(0, 5);
				}
				valorVolHals.setText(vol);
				valorFanIn.setText(Integer.toString(rec.fanIn));
				valorFanOut.setText(Integer.toString(rec.fanOut));
				
				
				if( rec.porcComent < 10)
				{
					lblPorcComent.setForeground(new Color(200, 0, 0));
				}
				else
				{
					lblPorcComent.setForeground(new Color(0, 200, 0));
				}
			}
		});
		
		JMenuItem mntmNewMenuItem = new JMenuItem(new AbstractAction("Abrir Workspace"){
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("choosertitle");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				  pathWorkSpace = chooser.getSelectedFile(); //guardar en un path
				}
				
				frame.setVisible(false);
				frame = new VentanaPincipal();
				frame.setVisible(true);
		    }
		});

		
		mnNewMenu.add(mntmNewMenuItem);
		
		
	}

	//Metodo para escuchar JList. En este metodo puedo escuchar las 3 listas.
	//Las distingo en cada if, preguntando cual es el source
	public void valueChanged(ListSelectionEvent e)
	{
		
		//si el cambio es en la lista de Proyectos
		if (e.getSource() == lP)
        {
			if( arrayRutas.isEmpty() == false)
			{	
				 proySeleccionado = arrayRutas.get(lP.getSelectedIndex());
				 proySeleccionado += "\\src";
			}
			else
				System.out.println("Seleccione un proyecto");
        
			ArrayList<String> clases = new ArrayList<String>();
			try{
				File proy = new File(proySeleccionado);
				rec.getAllJavaFiles(proy, clases, arrayRutasClases);
				lC = new JList(clases.toArray());
				lC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				listClases.setViewportView(lC);
				lC.addListSelectionListener(this);
				getContentPane().add(listClases);
				for (String string : arrayRutasClases) {
					 System.out.println(string);
				}
			}catch(Exception e1)
			{
				clases.add("Seleccione proyecyto válido");
			}
        }
		//si el cambio es en la lista de Clases
		if (e.getSource() == lC)
		{
			if(arrayRutasClases.isEmpty() == false)
			{
				metodos = rec.obtenerListaMetodos(arrayRutasClases.get(lC.getSelectedIndex()));
				lM = new JList(metodos.toArray());
				lM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				//Ponemos la lista a la escucha de cambios
				lM.addListSelectionListener(this);
				listMetodos.setViewportView(lM);
			}
			
			System.out.println(metodos.toString());
		}
		
		//si el cambio es en la lista de Metodos
		if (e.getSource() == lM)
		{
			
			rec.fanIn((String)arrayRutasClases.get(lC.getSelectedIndex()), (String)metodos.get(lM.getSelectedIndex()) );
			rec.fanOut((String)arrayRutasClases.get(lC.getSelectedIndex()), (String)metodos.get(lM.getSelectedIndex()) );
			//va el path, y el nombre completo del metodo
			
			rec.analizarMetodo((String)arrayRutasClases.get(lC.getSelectedIndex()), (String)metodos.get(lM.getSelectedIndex()) );
			
			//me guarda todo en Recorrer rec. Despues cuando apreto btnAnalizar
			//Toma todo lo que tiene rec (cant lineas, porc coment, etc)
		}
			
    }
}
