package com.tp3.preguntec;

class Lista {
	public Nodo Primero;
	public Nodo Ultimo;
	String Nombre;

	//Constructor construye una lista vacia con un nombre 

	//Retorna True si Lista Vacía
	 public boolean Vacia (){ return Primero == null;}

	 public Lista(String nombre){
		 Nombre = nombre;
		 Primero = Ultimo =null;
	 }

	//Constructor construye una lista vacia con un nombre de List
	public Lista(){ this ("Lista");}

	//Inserta un Elemento al Frente de la Lista
	//Si esta vacía PrimerNodo y UltimoNodo se refieren al nuevo nodo. Si no PrimerNodo se refiere al nuevo nodo.

	public void InsertaInicio (String ElemInser){
		if (Vacia()){
			Primero = Ultimo = new Nodo (ElemInser);}
		else{
			Primero = new Nodo (ElemInser, Primero);}
	}
	
	//indica si el producto o supermercado con el codigo "codigo" esta en la lista
	boolean esta(String codigo){
		boolean esta= false;
		Nodo Actual = Primero;
		while (Actual!= null){
			if(Actual.cod == codigo){
				return esta= true;}
			else{
				Actual=Actual.sig;}
		}
		return esta;
		}
	void imprime(){
		Nodo Actual= Primero;
		while(Actual!= null){
			System.out.println(Actual.cod);
			Actual= Actual.sig;
		}
	}
}