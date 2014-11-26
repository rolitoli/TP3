require 'sinatra'
require './usuarios.rb'
require './preguntas.rb'
require './respuestas.rb'

get '/' do
	"Bienvenido a la aplicacion de Vuelvase loco entreprise!"
end

#Caso para ingresar un usuario nuevo
get '/ingresar/:nombre/:pass' do
	actual = Usuario.new(params[:nombre],params[:pass]) #Se crea un objeto con los datos del usuario
	imprimir = actual.ingresarUsuario #Es el valor de respuesta a la aplicacion
	"" + imprimir + ""
end


#Caso para loguearse 
get '/loguear/:nombre/:pass' do 
	actual = Usuario.new(params[:nombre],params[:pass]) #Se crea un objeto con los datos del usuario
	imprimir = actual.loguear #Es el valor de respuesta a la aplicacion
	"" + imprimir + ""
end

#caso para preguntas
get '/pregunta/:usuario/:pregunta/:id/:tag' do
	actual = Preguntas.new(params[:usuario],params[:pregunta],params[:id],params[:tag])
	imprimir = actual.ingresarPregunta
	""+imprimir+""
end

#caso para mostrar las respuestas
get '/mostrarPreguntas' do
	actual = Preguntas.new("","","","")
	imprimir = actual.mostrarPreguntas
	""+imprimir+""
end

#caso para mostrar las preguntas por tags 
get '/buscarTag/:tag' do
	actual = Preguntas.new("","","",params[:tag])
	imprimir = actual.buscarTag
	""+imprimir+""
end

#caso para guardar una respuesta
get '/respuesta/:usuario/:id/:respuesta' do
	actual = Respuesta.new(params[:usuario],params[:id],params[:respuesta])
	imprimir = actual.guardarRespuesta
	""+imprimir+""
end

#caso para buscar respuestas por pregunta
get '/mostrarRespuestas/:id' do
	actual = Respuesta.new("",params[:id],"")
	imprimir = actual.buscarRespuestasPregunta
	""+imprimir+""
end