package com.tp3.preguntec;


public class Nodo{
// atributos de la clase
   String cod;
   Nodo sig;

//Construtor  Crea un nodo del tipo Object
Nodo (String  codigo){
	cod= codigo;
	sig= null;  //siguiente con valor de nulo
	}

//Constructor Crea un nodo del Tipo Object y al siguiente nodo de la lista
Nodo (String codigo, Nodo siguiente){
	cod= codigo;
	sig = siguiente; //siguiente se refiere al siguiente nodo
	}
}