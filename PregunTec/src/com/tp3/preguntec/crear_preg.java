package com.tp3.preguntec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.tp3.preguntec.Login.HttpAsyncTask;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class crear_preg extends Activity implements OnClickListener{
	String textPregunta;
	String textTag;
	Button crear;
	EditText preg;
	EditText etiquetas;
	TextView usr;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crear_preg);
		preg= (EditText)findViewById(R.id.textPreg);
		etiquetas= (EditText)findViewById(R.id.edT_tags);
		usr= (TextView)findViewById(R.id.nomb_usr);
		crear = (Button)findViewById(R.id.P_lista);
		info.estado="";
		usr.setText(info.usuario);
		crear.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) {
		textPregunta= preg.getText().toString();
		textTag= etiquetas.getText().toString();
		if(arg0.getId()==crear.getId()){
			if (textPregunta==""){
				Toast.makeText(getBaseContext(), "Debe de escribir la pregunta", Toast.LENGTH_LONG).show();
			}
			else{
				new HttpAsyncTask().execute("https://fast-wildwood-5373.herokuapp.com/pregunta/"+info.usuario+"/"+textPregunta+"/ /"+textTag);
				Intent intent= new Intent(this, preguntas.class);
				finish();
				startActivity(intent);
			}
		}
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
	
}
