package herramienta.testing;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

public class Recorrer {
	public int CantIf;
	public int CantComent;
	public int CantFor;
	public int CantLineas;
	public int CantWhile;
	public int CompCicl;
	public float porcComent;
	public ArrayList<String> codigo = new ArrayList<String>();

	//M�todo para obtener todas las carpetas que contiene la ruta seleccionada
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
	

	//M�todo para obtener todos los .java de un proyecto
	public static void getAllJavaFiles(File path, ArrayList<String> clases, ArrayList<String> rutasClases) {
		rutasClases.clear();
		clases.clear();
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
	
	//M�todo para obtener los nombres de los metodos de la clase
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
	public void analizarMetodo(String pathIn, String metodo)
	{
		CantIf=0; CantFor=0; CantLineas=0; CantComent=0; CantWhile=0; CompCicl =0;
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
					}
					if(cadena.contains("for"))
					{
						String[] a3 = cadena.split("&&");
						CantFor++;
					}
						
					if(cadena.contains("while"))
					{
						String[] a4 = cadena.split("&&");
						CantWhile++;
					}
					
					if(cadena.contains("//"))
						CantComent++;
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
					
					CompCicl += StringUtils.countMatches(cadena, "if (")
							 + StringUtils.countMatches(cadena, "while (")
							 + StringUtils.countMatches(cadena, "for (")
							 + StringUtils.countMatches(cadena, " && ")
							 + StringUtils.countMatches(cadena, " || ")
							 + StringUtils.countMatches(cadena, " ? ")
							 + StringUtils.countMatches(cadena, "case ")
							 + StringUtils.countMatches(cadena, "catch (");
											
					CantLineas++;
					cadena = sc.nextLine();
					
					if(ContadorLlaves==1)
						break;
				}
				
				CompCicl = CompCicl + 1;
			}
		}

		porcComent = (float)(( 100 * CantComent ) / (float)CantLineas);
		
	}
	
}
