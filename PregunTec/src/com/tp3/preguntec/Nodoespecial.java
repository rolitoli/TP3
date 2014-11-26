package com.tp3.preguntec;

//Clase de Nodo Lista Doble
public class Nodoespecial{
	//Acceso directo
	String noob;
	String pregunta;
	String id;
	Lista tags;
	
	Nodoespecial siguiente;
	Nodoespecial anterior;
	
//Constructor: Crea un Nodo tipo int
Nodoespecial (String usr_p,String pregunta_usr,String id_p,Lista L_tags){
	noob=usr_p;
	pregunta= pregunta_usr;
	id =id_p;
	tags= L_tags;
	siguiente=null;
	anterior = null;
}
//Constructor: Crea un nodo del tipo int y al siguiente nodo de la lista
Nodoespecial (String usr_p,String pregunta_usr,String id_p,Lista L_tags, Nodoespecial signodo){
	noob=usr_p;
	pregunta= pregunta_usr;
	id=id_p;
	tags= L_tags;
	siguiente=signodo;
}

//Retorna el dato que se encuentra en el Nodo

//Retorna el siguiente nodo
Nodoespecial getnext(){
	return siguiente;
}
}
