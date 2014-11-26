$listaRespuestas = [] #Lista de preguntas 

#Clase para manejar las respuestas 
class Respuesta
	def initialize(usuario,id,respuesta)
		@usuario = usuario #El nombre de usuario
		@id = id #El id de la pregunta correspondiente a la respuesta
		@respuesta = respuesta  #La cadena de texto con la respuestas al id

	end

	#Metodo para obtener el Usuario
	def getUsuario
		return @usuario
	end

	#Metodo para obtener el id de la pregunta
	def getIdPregunta
		return @id
	end

	#Metodo para obtener la respuesta en el objeto Respuesta
	def getRespuesta
		return @respuesta
	end

	#metodo para guardar una respuesta en la lista respuestas
	def guardarRespuesta
		respuesta = Respuesta.new(@usuario, @id, @respuesta)
		$listaRespuestas << respuesta
		return "anadido"
	end

	#Metodo para buscar las respuestas con el id de la pregunta
	def buscarRespuestasPregunta
		i = 0 	#contador para iterar sobre listarespuestas
		cadena = "" #Cadena que va a contener la respuesta
		while $listaRespuestas.length != i
			id = $listaRespuestas[i].getIdPregunta
			if id == @id
				cadena+=$listaRespuestas[i].getUsuario+"-"+$listaRespuestas[i].getRespuesta+"/" #se crea la cadena con los datos del usuario
			end
		i+=1
		end
	return cadena 

	end



end #Fin de la clase respuesta 