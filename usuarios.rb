$listaUsuarios=[] #Lista de objetos tipo Usuario que maneja los datos 

#Clase usuario que maneja los datos de los usuarios
class Usuario
	def initialize(nombre , pass)#metodo para inizializar la clase
		#atributos
		@nombre = nombre
		@pass = pass
	end

	#metodo para obtener el nombre de usuario
	def getNombre
		return @nombre
	end

	#Metodo para obtener la contrasena
	def getPass
		return @pass
	end

	#metodo para verificar si el usuario se encuentra en la lista de usuarios y si la contrasena corresponde
	#retorna
	def loguear
		if !(verificaUsuario)
			if verificaPass
				return "correcto" #Si puede loguearse
			else
				return "passincorrecto" #no puede loguearse por que la contrasena no es correcta
			end
		else
			return "usuarioincorrecto" #no puede loguearse por que el usuario no existe
		end
	end


	def verificaPass
		contador=0
		while (($listaUsuarios).length!=contador) #se obtiene el tamano de la lista y se itera hasta que el contador sea igual
			if @nombre == $listaUsuarios[contador].getNombre
				if @pass == $listaUsuarios[contador].getPass
					return true
				else
					return false
				end
			end
		contador+=1	
		end
	return false
	end

	#Metodo para ingresar un nuevo usuario a la base de datos
	#Devuelve anadido si lo inserto en la lista de usuarios y noanadido si ya estaba en la lista
	def ingresarUsuario
		if verificaUsuario
			usuario= Usuario.new(@nombre,@pass) # se crea el objeto para almacenarlo dentro de la lista
			$listaUsuarios<<usuario
			return "anadido"
		
		else 
			return "noanadido"
		end
	end

	#Metodo para verificar si el usuario esta en la lista Usuarios
	#Devuelve un valor booleano falso si el usuario si esta en la lista
	def verificaUsuario
		contador=0
		while (($listaUsuarios).length!=contador) #se obtiene el tamano de la lista y se itera hasta que el contador sea igual
			if @nombre == ($listaUsuarios[contador]).getNombre
				return false
			end
		contador+=1
		end
		return true
	
	end

	#metodo para imprimir la lista de usuarios
	#Devuelve una cadena de texto con los credenciales del usuario
	#metodo solo para verificar la lista
	def imprimirListaUsuarios
		contador=0
		while (($listaUsuarios).length!=contador)
			return "El usuario es "+$listaUsuarios[contador].getNombre+" con la contrasena "+$listaUsuarios[contador].getPass
			contador+=1

		end
	end


end # fin de la clase usuario
