package com.tp3.preguntec;


public class ListaEspecial {
	private static final Boolean Interger = null;
	public Nodoespecial PrimerNodo;
	String Nombre;

	//Constructor: construye una lista vac�a con un nombre
	
	//Retorna True cuando la lista esta vac�a
	public boolean Vacialista()
	{
		return PrimerNodo==null;
	}
	//imprime el Contenido de la lista
	public void Imprimir(){
		if (Vacialista())
		{
			System.out.println("Vacia"+Nombre);
		}
		else
		{
			System.out.println("La lista"+ Nombre + " es: ");
			Nodoespecial Actual1=PrimerNodo;
			while (Actual1 != null)
			{
				System.out.println(Actual1.id+",");
				System.out.print(Actual1.noob+",");
				System.out.println(Actual1.pregunta+",");
				Actual1.tags.imprime();
				Actual1=Actual1.siguiente;
			}
			System.out.println();
		}
	}
	
	
	public ListaEspecial(String s)
	{
		Nombre = s;
		PrimerNodo = null;
	}
	
	//Inserta un Elemento al Frente de la Lista
	public void InsertaInicio(String usr_p,String pregunta,String id,Lista tags)
	{
		//Lista Vac�a
		if (Vacialista())
		{
			PrimerNodo = new Nodoespecial (usr_p,pregunta,id,tags);
		}
		//Lista No Vac�a
		else
		{
			PrimerNodo = new Nodoespecial (usr_p,pregunta,id,tags,PrimerNodo);
		}
	}
	
	//Inserta al Final de la Lista
	public void InsertaFinal(String usr_p, String pregunta,String id,Lista tags)
	{
		//Lista Vac�a
		if (Vacialista()){
			PrimerNodo = new Nodoespecial (usr_p,pregunta,id,tags);
		}
		//lista No Vac�a
		else
		{
			Nodoespecial aux= PrimerNodo;
			while (aux.siguiente != null)
				aux = aux.siguiente;
			aux.siguiente = new Nodoespecial (usr_p,pregunta,id,tags);
			aux.siguiente.anterior = aux;
		}
	}
	
	// Inserta Posici�n Medio
	public void InsertaMedio(String usr_p,String pregunta,String id,Lista tags, int Posicion )
	{
		//Lista Vac�a
		if (Vacialista())
		{
			PrimerNodo = new Nodoespecial (usr_p,pregunta,id,tags);
		}
		//Lista No Vac�a
		else
		{
			if (Posicion <=1)
			{
				InsertaInicio(usr_p,pregunta,id,tags);
			}
			else
			{
				Nodoespecial Aux = PrimerNodo;
				int i = 2;
				while ((i != Posicion) & (Aux.siguiente != null))
				{
					i++;
					Aux = Aux.siguiente;
				}
				if (i==Posicion)
				{
					Nodoespecial Nuevo = new Nodoespecial (usr_p,pregunta,id,tags);
					Nuevo.siguiente = Aux.siguiente;
					Aux.siguiente = Nuevo;
					Nuevo.anterior = Aux;
					Nuevo.siguiente.anterior=Nuevo;
				}
				else
					InsertaFinal(usr_p,pregunta,id,tags);
			}
		}
	}
	// Elimina Inicio
	public void EliminaIncio()
	{
		if (Vacialista()){
			System.out.println("No hay Elementos");}
		else
		{
			if(PrimerNodo.siguiente == null){
				PrimerNodo = null;}
			else{
				PrimerNodo = PrimerNodo.siguiente;
				PrimerNodo.anterior = null;}
		}
	}
	// Ellimina Final
	public void EliminaFinal()
	{
		if (Vacialista())
			System.out.println( "No hay Elementos");
		else
		{
			if (PrimerNodo.siguiente == null)
				PrimerNodo = null;
			else
			{
				Nodoespecial Actual = PrimerNodo;
				while (Actual.siguiente.siguiente != null)
					Actual = Actual.siguiente;
				Actual.siguiente = null;
			}
		}
	}
	
	// Elimina Elemento en Posicion
	public void EliminaMedio (int Posicion)
	{
		if (Vacialista())
			System.out.println("Nada");
		else
		{
			Nodoespecial Actual = PrimerNodo;
			int i = 1;
			while ((i != Posicion) & (Actual.siguiente != null))
			{
				i++;
				Actual = Actual.siguiente;
			}
			if (Posicion == 1)
				EliminaIncio();
			else
			{
				if (i == Posicion)
				{
					Actual.anterior.siguiente = Actual.siguiente;
					Actual.siguiente.anterior = Actual.anterior;
				}
			}
		}
	}
	
	//Invertir
	public void Invertir()
	{
		Nodoespecial sig = PrimerNodo;
		Nodoespecial anterior = null;
		while (! (Vacialista()))
		{
			sig =PrimerNodo.siguiente = anterior;
			anterior = PrimerNodo;
			PrimerNodo = sig;
		}
	}
	public boolean contains(String cod) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
}
	
	
	


				
		






















