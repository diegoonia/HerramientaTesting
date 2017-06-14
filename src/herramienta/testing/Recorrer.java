package herramienta.testing;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class Recorrer {
	public int CantLineas;
	public int CantComent;
	public int CompCicl;
	public int CantIf;
	public int CantFor;
	public int CantWhile;
	public int CantY;
	public int CantO;
	public int CantPlus;
	public int CantMinus;
	public int CantMult;
	public int CantDiv;
	public int CantEqual;
	public int CantDist;
	public int CantMay;
	public int CantMen;
	public int CantInc;
	public int CantDec;
	public int CantMultEq;
	public int CantDivEq;
	public int CantIsEqual;
	public int CantMayEq;
	public int CantMenEq;
	public int CantPlusEq;
	public int CantMinusEq;
	public float porcComent;
	public int CantCase;
	public int N1 = 0;
	public int N2 = 0;
	public int n1 = 0;
	public int n2 = 0;
	private Set<String> setOperandos = new HashSet<String>();
	public int longitudHalstead;
	public float volumenHalstead;
	public int fanOut = 0;
	public int fanIn = 0;
	public ArrayList<String> codigo = new ArrayList<String>();

	//Método para obtener todas las carpetas que contiene la ruta seleccionada
	public static void getAllFiles(File path, ArrayList<String> proyect, ArrayList<String> rutas) {
        File[] filesList = path.listFiles();
        for(File f : filesList){
            if(f.isDirectory()){
            	//if(f.getName().charAt(0) != '.') //porque en la carpeta GitHub me agarraba el .metadata y .recommenders
	            	proyect.add(f.getName());
	            	rutas.add(f.getPath());
            }
        }
	}
	

	//Método para obtener todos los .java de un proyecto
	public static void getAllJavaFiles(File path, ArrayList<String> clases, ArrayList<String> rutasClases) {
		//rutasClases.clear();
		//clases.clear();
		File[] filesList = path.listFiles();
        for(File f : filesList){
            if(f.isDirectory()){
                getAllJavaFiles(f, clases, rutasClases);
            }
            else
            {
           		clases.add(f.getName());
           		rutasClases.add(f.getPath());
            }
        }
	}
	
	//Método para obtener los nombres de los metodos de la clase
	public ArrayList<String> obtenerListaMetodos(String pathIn)
	{
		Scanner sc = null;
		String cadenaParcial;
		String cadenaParcialAnterior = " ";
		String nomMetodo;
		int cant = 0;
		boolean flag = false;
		ArrayList<String> retorno = new ArrayList<String>();
		try
		{
			sc=new Scanner(new File(pathIn));
			while(sc.hasNext())
			{
				cadenaParcial = sc.nextLine();
				while(flag == false && sc.hasNext())
				{
					while(cant < 2 && flag == false && sc.hasNext())
					{
						cadenaParcialAnterior = cadenaParcial;
						cadenaParcial = sc.nextLine();
						if(cadenaParcial.contains("{"))
							cant++;
						if(cadenaParcial.contains("}"))
						{
							cant--;
							if(cant == 0)
								flag = true;
						}
					}	
					if(cadenaParcial.contains(")") && cadenaParcial.contains("{"))
					{
						nomMetodo = cadenaParcial.replace("{", "");
					}
					else
					{	
						nomMetodo = cadenaParcialAnterior;	
					}
					retorno.add(nomMetodo);
					while(cant > 1 && sc.hasNext() && flag == false)
					{
						cadenaParcial = sc.nextLine();
						if(cadenaParcial.contains("{"))
							cant++;
						if(cadenaParcial.contains("}"))
							cant--;
					}
				}
			}
			sc.close();
			return retorno;
		}
		catch(Exception e)
		{
			sc.close();
			return null;
		}
	}
	
	//Primero tengo que pararme en el metodo que viene de parametro
	//Despues contar las { y los }, cuando llega a cero dejo de scanear
	//Adentro hay que contar los if, &&, || de los if
	// los // /* */
	// for y while
	// cantidad de lineas
	public void fanIn(String clase, String metodo)
	{
		fanIn = 0;
		String cadena = null;
		Scanner sc = null;
		String met[] = metodo.split(" ");
		String me;
		if(met.length > 1)
			 me = met[met.length-1];
		else
			me = met[0];
		
		try{
			sc = new Scanner(new File(clase));	
		}
		catch(Exception e)
		{
			sc.close();
		}
		while(sc.hasNext())
		{
			cadena = sc.nextLine();
			if(cadena.contains(me) && !cadena.contains(metodo)) //veo donde esta el metodo
				fanIn++;
			
		}
		
	}
	public void fanOut(String clase, String metodo)
	{
		int ContadorLlaves = 0;
		fanOut = 0;
		String cadena = null;
		Scanner sc = null;
		String met[] = metodo.split(" ");
		String me;
		if(met.length > 1)
			 me = met[met.length-1];
		else
			me = met[0];
		try{
			sc = new Scanner(new File(clase));	
		}
		catch(Exception e)
		{
			sc.close();
		}
		while(sc.hasNext())
		{
			cadena = sc.nextLine();
			if(cadena.contains(metodo)) //veo donde esta el metodo
			{
				codigo.add(cadena);
				ContadorLlaves = 1;     //cuento la primer llave y empiezo a recorrer
				CantLineas=1;
				cadena = sc.nextLine();
				while(sc.hasNextLine() && ContadorLlaves>0)
				{
					codigo.add(cadena);
					if(cadena.contains("{"))
						ContadorLlaves++;
						
					if(cadena.contains("}"))
						ContadorLlaves--;
					cadena = sc.nextLine();
					if(ContadorLlaves==1)
						break;
				}

			}
		}
		for (String string : codigo) {
			if(cadena.contains(me) && !string.contains(metodo))
				fanOut++;
		}
		codigo.clear();
	}
	public void analizarMetodo(String pathIn, String metodo)
	{
		CantIf=0; CantFor=0; CantLineas=0; CantComent=0; CantWhile=0; CantCase = 0;
		CantY=0; CantO=0; CompCicl =0; CantPlus = 0; CantMinus = 0; CantMay = 0;
		CantMen = 0; CantEqual = 0; CantMult = 0; CantDiv = 0; CantDist = 0;
		CantInc = 0; CantDec = 0; CantMultEq = 0; CantDivEq = 0; CantIsEqual = 0;
		CantMayEq = 0; CantMenEq = 0; CantPlusEq = 0; CantMinusEq = 0; N2 = 0; n2 = 0; N1 = 0; n1 = 0;
		int ocurrenciaOperadores[] = new int [23]; 
		int CantApCierre=0;
		boolean flagComent = false;
		String cadena;
		String cadenaParcial;
		int ContadorLlaves;
		codigo.clear();
		Scanner sc = null;
		try{
			sc=new Scanner(new File(pathIn));
			
		} 
		catch(Exception e)
		{
			sc.close();
		}
		while(sc.hasNext())
		{
			cadena = sc.nextLine();
			if(cadena.contains(metodo)) //veo donde esta el metodo
			{
				codigo.add(cadena);
				ContadorLlaves = 1;     //cuento la primer llave y empiezo a recorrer
				CantLineas=1;
				cadena = sc.nextLine();
				

				while(sc.hasNextLine() && ContadorLlaves>0)
				{
					
					codigo.add(cadena);
					if(cadena.contains("{"))
						ContadorLlaves++;
						
					if(cadena.contains("}"))
						ContadorLlaves--;
				
					if(cadena.contains("if"))
					{
						String[] a2 = cadena.split("&&");
						CantIf++;
						ocurrenciaOperadores[0] = 1;
					}
					if(cadena.contains("for"))
					{
						String[] a3 = cadena.split("&&");
						CantFor++;
						ocurrenciaOperadores[1] = 1;
					}
						
					if(cadena.contains("while"))
					{
						String[] a4 = cadena.split("&&");
						CantWhile++;
						ocurrenciaOperadores[2] = 1;
					}
					
					if(cadena.contains("//"))
						CantComent++;
					if(cadena.contains("case"))
					{
						CantCase++;
						ocurrenciaOperadores[3] = 1;
					}
					if(cadena.contains("/*"))
					{
						flagComent = true;
						while(flagComent)
						{
							CantComent++;
							CantLineas++;
							if(cadena.contains("*/"))
								flagComent = false;
							
							cadena = sc.nextLine();
							codigo.add(cadena);
						}
					}
					CompCicl += StringUtils.countMatches(cadena, "if(")
							 + StringUtils.countMatches(cadena, "while(")
							 + StringUtils.countMatches(cadena, "for(")
							 + StringUtils.countMatches(cadena, " && ")
							 + StringUtils.countMatches(cadena, " || ")
							 + StringUtils.countMatches(cadena, " ? ")
							 + StringUtils.countMatches(cadena, "case")
							 + StringUtils.countMatches(cadena, "catch(");
					CantY += StringUtils.countMatches(cadena, " && ");
					if(CantY != 0)
						ocurrenciaOperadores[4] = 1;
					CantO += StringUtils.countMatches(cadena, " || ");
					if(CantO != 0)
						ocurrenciaOperadores[5] = 1;
					CantPlus += StringUtils.countMatches(cadena, " + ");
					if(CantPlus != 0)
						ocurrenciaOperadores[6] = 1;
					CantMinus += StringUtils.countMatches(cadena, " - ");
					if(CantMinus != 0)
						ocurrenciaOperadores[7] = 1;
					CantMay += StringUtils.countMatches(cadena, " > ");
					if(CantMay != 0)
						ocurrenciaOperadores[8] = 1;
					CantMen += StringUtils.countMatches(cadena, " < ");
					if(CantMen != 0)
						ocurrenciaOperadores[9] = 1;
					CantEqual += StringUtils.countMatches(cadena, " = ");
					if(CantEqual != 0)
						ocurrenciaOperadores[10] = 1;
					CantMult += StringUtils.countMatches(cadena, " * ");
					if(CantMult != 0)
						ocurrenciaOperadores[11] = 1;
					CantDiv += StringUtils.countMatches(cadena, " / ");
					if(CantDiv != 0)
						ocurrenciaOperadores[12] = 1;
					CantDist += StringUtils.countMatches(cadena, " != ");
					if(CantDist != 0)
						ocurrenciaOperadores[13] = 1;
					CantInc += StringUtils.countMatches(cadena, "++");
					if(CantInc != 0)
						ocurrenciaOperadores[14] = 1;
					CantDec += StringUtils.countMatches(cadena, "-- ");
					if(CantDec != 0)
						ocurrenciaOperadores[15] = 1;
					CantMultEq += StringUtils.countMatches(cadena, " *= ");
					if(CantMultEq != 0)
						ocurrenciaOperadores[16] = 1;
					CantDivEq += StringUtils.countMatches(cadena, " /= ");
					if(CantDivEq != 0)
						ocurrenciaOperadores[17] = 1;
					CantIsEqual += StringUtils.countMatches(cadena, " == ");
					if(CantIsEqual != 0)
						ocurrenciaOperadores[18] = 1;
					CantMayEq += StringUtils.countMatches(cadena, " <= ");
					if(CantMayEq != 0)
						ocurrenciaOperadores[19] = 1;
					CantMenEq += StringUtils.countMatches(cadena, " >= ");
					if(CantMenEq != 0)
						ocurrenciaOperadores[20] = 1;
					CantPlusEq += StringUtils.countMatches(cadena, " += ");
					if(CantPlusEq != 0)
						ocurrenciaOperadores[21] = 1;
					CantMinusEq += StringUtils.countMatches(cadena, " -= ");
					if(CantMinusEq != 0)
						ocurrenciaOperadores[22] = 1;	
					
					buscarOperandos(cadena);
					CantLineas++;
					
					cadena = sc.nextLine();
					
					if(ContadorLlaves==1)
						break;
				}
				
				CompCicl = CompCicl + 1;
			}
			
		}

		porcComent = (float)(( 100 * CantComent ) / (float)CantLineas);
		N1 = CantIf + CantFor + CantWhile + CantY + CantO + CantPlus + CantMinus + CantMult + CantDiv + CantEqual + CantDist + CantMay +
			 CantMen + CantCase + CantInc + CantDec + CantMultEq + CantDivEq + CantIsEqual + CantMayEq + CantMenEq + 
			 CantPlusEq + CantMinusEq;
		
		for (int i : ocurrenciaOperadores) {
			if(i != 0)
				n1++;
		}
		n2 = setOperandos.size();
		longitudHalstead = N1 + N2;
		volumenHalstead = (float) (longitudHalstead * (Math.log(n1+n2) / Math.log(2)));
		
	}
	//true false .
	 private void buscarOperandos(String linea) {
	    	String operandos[] = linea.split("(\\+|\"|\\[|\\]|[ \t\n\r\f]+|-|\\*|&&|true|false|\\.|/|\\|\\||=|!=|<|>|\\{|\\}|;|if\\(|for\\(|\\)|while\\(|case\\(|"
	    			+ "<|>|int|float|double|String|Integer|ArrayList)");
	    									 
	    	
	    	for(int i = 0; i < operandos.length ; i++) {
	    		String operando[] = operandos[i].split("[ \t\n\r\f]+");
	    		for(int j = 0; j < operando.length; j++)
	    		if(operando[j].length()!=0)
	    		{
	    			if(!operandos[i].contains("\\") && !operandos[i].contains("*/"))
	    			{
	    				N2 += 1;
	    				this.setOperandos.add(operandos[i]);
	    			}
	    			
	    		}
	    		
	    	}
	    }

}
