package com.tp3.preguntec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class preguntas extends Activity implements OnClickListener{
	Button preguntar;
	Button carga;
	TextView usr;
	ListView lista;
	
	ArrayAdapter<String> adaptador;
	static ArrayList<String> coleccion;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preguntas);
		usr= (TextView)findViewById(R.id.nombre);
		usr.setText(info.usuario);
		preguntar = (Button)findViewById(R.id.bt_cr_preg);
		carga = (Button)findViewById(R.id.bt_carga);
		preguntar.setOnClickListener(this);
		preguntar.setOnClickListener(this);
		lista= (ListView)findViewById(R.id.listView1);
		coleccion= new ArrayList<String>();
		adaptador= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,coleccion);
		lista.setAdapter(adaptador);
		info.estado="";
		new HttpAsyncTask().execute("https://fast-wildwood-5373.herokuapp.com/mostrarPreguntas");
		
		separar(info.estado);
		llenaLV();
		
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
        	Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
       }
    }

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.bt_cr_preg:
			crear();
			break;
		case R.id.bt_carga:
			llenaLV();
			lista.setAdapter(adaptador);
			break;
		}
	}
	
	public void crear(){
		Intent intent= new Intent(this, crear_preg.class);
		finish();
		startActivity(intent);
	}
	
	public static void llenaLV (){
		if (!info.preguntas.Vacialista()){
			Nodoespecial actual= info.preguntas.PrimerNodo;
			while(actual!= null){
				coleccion.add(actual.pregunta.toString()+"  Realizada por: "+ actual.noob.toString());
			}
		}
	}
	public static void separar(String cadena){
		StringTokenizer token= new StringTokenizer(cadena,"/");
		while (token.hasMoreTokens()){
			String pregActual= token.nextToken();
			StringTokenizer datos= new StringTokenizer(pregActual,"-");
			while (datos.hasMoreTokens()){
				String usr= datos.nextToken();
				String preg= datos.nextToken();
				String id= datos.nextToken();
				Lista tag = new Lista();
				StringTokenizer tags= new StringTokenizer(datos.nextToken(),",");
				while (tags.hasMoreTokens()){
					tag.InsertaInicio(tags.nextToken());
				}
				info.preguntas.InsertaFinal(usr, preg, id, tag);
			}
		}
		info.preguntas.Imprimir();
	}
	
}
