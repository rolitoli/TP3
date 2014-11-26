$listaPreguntas=[] #lista de preguntas que almacena objetos del tipo pregunta 

#creacion de la clase preguntas
class Preguntas 
	def initialize(usuario,pregunta,id,tag)
		@usuario = usuario #variable tipo string
		@pregunta = pregunta #variable listas objeto pregunta
		@id= id #variable tipo entero con un identificador para las preguntas
		@tag = tag #variable lista strings
	end

	#obtener el dato del usuario
	def getUsuario
		return @usuario
	end

	#obtener el dato
	def getPregunta
		return @pregunta
	end

	#obtener el id de la pregunta
	def getId
		return @id
	end

	#Para obtener el tag
	def getTag
		return @tag
	end

	#metodo para crear la cadena de texto con todos los tags
	def crearStringTag
		i = 0 #contador
		listaTag=getTag
		stringTag =  "" #Se almacena la cadena de texto
		while listaTag.length != i
			stringTag += listaTag[i]
			i +=1
		end
		return stringTag
	end

	#metodo para asignarle a la pregunta un identificador
	def asignarIdPregunta
		return $listaPreguntas.length
	end

	#Metodo para ingresar nuevas preguntas
	def ingresarPregunta
		pregunta = Preguntas.new(@usuario, @pregunta, asignarIdPregunta.to_s, getTag) #variable para almacenar los datos
		$listaPreguntas<<pregunta #se guarda las variables en la lista
		return "correcto"
	end

	#Metodo para buscar preguntas por algun tag 
	def buscarTag
		tag = getTag #se obtiene el tag en una variable
		i=0 #contador
		listaTag=[] # lista donde se van a almacenar la lista de tags
		cadena = ""
		#iteracion para recorrer las preguntas
		while $listaPreguntas.length != i
			listaTag = $listaPreguntas[i].getTag
			
			if listaTag.include?(tag) #pregunta si el tag esta dentro de la lista de tags de la pregunta en el indice i
				pregunta=$listaPreguntas[i] #se crea la variable para averiguar los datos del objeto pregunta
				cadena+=pregunta.getUsuario+"-"+pregunta.getPregunta+"-"+pregunta.getId+"-"+pregunta.crearStringTag+"/" #se va armando la cadena de respuesta
			end
			i += 1
		end
		return cadena

	end


	#Metodo para mostrar las preguntas
	def mostrarPreguntas
		contador=0
		cadena = ""
		while($listaPreguntas.length !=contador)
			pregunta = $listaPreguntas[contador]
			cadena+=pregunta.getUsuario+"-"+pregunta.getPregunta+"-"+pregunta.getId+"-"+pregunta.crearStringTag+"/" #se va armando la cadena de respuesta 
			contador+=1
		end
	return cadena
	end



end #fin de la clase preguntas

