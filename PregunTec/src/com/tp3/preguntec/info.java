package com.tp3.preguntec;

import java.util.StringTokenizer;

public class info {
	static public String usuario;
	static public String pass;
	static public String estado;
	static public ListaEspecial preguntas= new ListaEspecial ("preguntas");
	
	public static String getEstado(){
		return estado;
	}
	public static void clear(){
		usuario="";
		pass="";
		estado="";
	}
}
