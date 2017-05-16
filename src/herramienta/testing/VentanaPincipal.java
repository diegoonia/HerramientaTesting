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

		JLabel lblTituloCantLineas = new JLabel("Lineas de Codigo:");
		lblTituloCantLineas.setForeground(new Color(112, 128, 144));
		lblTituloCantLineas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTituloCantLineas.setBounds(540, 200, 190, 21);
		getContentPane().add(lblTituloCantLineas);
		
		JLabel lblCantLineas = new JLabel("-");
		lblCantLineas.setForeground(new Color(0, 0, 0));
		lblCantLineas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantLineas.setBounds(700, 200, 190, 21);
		getContentPane().add(lblCantLineas);
		
		JLabel lblTituloCantComent = new JLabel("Lineas de Comentario:");
		lblTituloCantComent.setForeground(new Color(112, 128, 144));
		lblTituloCantComent.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTituloCantComent.setBounds(540, 240, 190, 21);
		getContentPane().add(lblTituloCantComent);
		
		JLabel lblCantComent = new JLabel("-");
		lblCantComent.setForeground(new Color(0, 0, 0));
		lblCantComent.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantComent.setBounds(700, 240, 190, 21);
		getContentPane().add(lblCantComent);
		
		JLabel lblTituloPorcComent = new JLabel("Porcentaje comentarios:");
		lblTituloPorcComent.setForeground(new Color(112, 128, 144));
		lblTituloPorcComent.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTituloPorcComent.setBounds(540, 280, 190, 21);
		getContentPane().add(lblTituloPorcComent);
		
		JLabel lblPorcComent = new JLabel("-");
		lblPorcComent.setForeground(new Color(0, 0, 0));
		lblPorcComent.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPorcComent.setBounds(700, 280, 190, 21);
		getContentPane().add(lblPorcComent);
		
		JLabel lblTituloCantIf = new JLabel("Cantidad de If:");
		lblTituloCantIf.setForeground(new Color(112, 128, 144));
		lblTituloCantIf.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTituloCantIf.setBounds(540, 320, 190, 21);
		getContentPane().add(lblTituloCantIf);
		
		JLabel lblCantIf = new JLabel("-");
		lblCantIf.setForeground(new Color(0, 0, 0));
		lblCantIf.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantIf.setBounds(700, 320, 190, 21);
		getContentPane().add(lblCantIf);
		
		JLabel lblTituloCantFor = new JLabel("Cantidad de For:");
		lblTituloCantFor.setForeground(new Color(112, 128, 144));
		lblTituloCantFor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTituloCantFor.setBounds(540, 360, 190, 21);
		getContentPane().add(lblTituloCantFor);
		
		JLabel lblCantFor = new JLabel("-");
		lblCantFor.setForeground(new Color(0, 0, 0));
		lblCantFor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantFor.setBounds(700, 360, 190, 21);
		getContentPane().add(lblCantFor);
		
		JLabel lblTituloCantWhile = new JLabel("Cantidad de For:");
		lblTituloCantWhile.setForeground(new Color(112, 128, 144));
		lblTituloCantWhile.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTituloCantWhile.setBounds(540, 400, 190, 21);
		getContentPane().add(lblTituloCantWhile);
		
		JLabel lblCantWhile = new JLabel("-");
		lblCantWhile.setForeground(new Color(0, 0, 0));
		lblCantWhile.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantWhile.setBounds(700, 400, 190, 21);
		getContentPane().add(lblCantWhile);
		
		JLabel lblTituloComplej = new JLabel("Complejidad Ciclomatica:");
		lblTituloComplej.setForeground(new Color(112, 128, 144));
		lblTituloComplej.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTituloComplej.setBounds(540, 440, 190, 21);
		getContentPane().add(lblTituloComplej);
		
		JLabel lblComplej = new JLabel("-");
		lblComplej.setForeground(new Color(0, 0, 0));
		lblComplej.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblComplej.setBounds(700, 440, 190, 21);
		getContentPane().add(lblComplej);
		
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
				
				lblPorcComent.setText( Float.toString(rec.porcComent) + " %");
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
			//va el path, y el nombre completo del metodo
			rec.analizarMetodo(arrayRutasClases.get(lC.getSelectedIndex()), metodos.get(lM.getSelectedIndex()) );
			//me guarda todo en Recorrer rec. Despues cuando apreto btnAnalizar
			//Toma todo lo que tiene rec (cant lineas, porc coment, etc)
		}
			
    }
	

	

}
