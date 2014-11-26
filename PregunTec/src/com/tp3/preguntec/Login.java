package com.tp3.preguntec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {
	//variables de interfaz del activity de android
	EditText nomb_user;
	EditText pass_user;
	Button btLog;
	Button btCrea;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		nomb_user= (EditText)findViewById(R.id.nombre);
		pass_user= (EditText)findViewById(R.id.pass);
		btLog = (Button)findViewById(R.id.log);
		btCrea = (Button)findViewById(R.id.crea);
		//agregar escucha al botton de log
		btLog.setOnClickListener(this);
		btCrea.setOnClickListener(this);
		info.clear();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.List_preg) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onClick(View arg0) {
		
		//variables obtenidas de los campos de texto
		String nombre = nomb_user.getText().toString();
		String pass = pass_user.getText().toString();
		info.usuario=nombre;
		info.pass=pass;
		switch(arg0.getId()){
		//click en el boton de login
		case R.id.log:
			log (nombre, pass);
			break;
		//click en el boton de crear usuario
		case R.id.crea:
			crear(nombre,pass);
			break;
			
		}
	}
	
	//metodo para loggear
  	public void log(String nombre, String pass){
  		new HttpAsyncTask().execute("https://fast-wildwood-5373.herokuapp.com/loguear/"+nombre+"/"+pass);
		//Toast.makeText(getBaseContext(), texto, Toast.LENGTH_LONG).show();
		if (info.getEstado().equals("correcto")){
			Intent intent= new Intent(this, preguntas.class);
			finish();
			startActivity(intent);
		}
		else if(info.getEstado().equals("passincorrecto")){
			Toast.makeText(getBaseContext(), "contrasena incorecta", Toast.LENGTH_SHORT).show();
		}
		else if(info.getEstado().equals("usuarioincorrecto")){
			Toast.makeText(getBaseContext(), "Usuario incorecto", Toast.LENGTH_SHORT).show();
		}
	}
  	
  	//metodo para crear usuario
  	public void crear(String nombre, String pass){
  		info.usuario=nombre;
		info.pass=pass;
		Intent intent= new Intent(this, crear_usr.class);
		Bundle paqt= new Bundle();
		paqt.putString("resultado", info.usuario);
		intent.putExtras(paqt);
		finish();
		startActivity(intent);
	}
  	
  	public static String GET(String url){
		InputStream inputStream = null;
		String result = "";
		try {
			// create HttpClient
			HttpClient httpclient = new DefaultHttpClient();
			// make GET request to the given URL
			HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
			// receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();
			// convert inputstream to string
			if(inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = "Did not work!";
		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
		}

		return result;
	}
	
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
        
        inputStream.close();
        info.estado=result;
        return result;
        
    }
    public boolean isConnected(){
    	ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
    	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
    	    if (networkInfo != null && networkInfo.isConnected()){ 
    	    	return true;}
    	    else{
    	    	return false;}
    }
    
    public class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
       }
    }
}
