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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class crear_usr extends Activity implements OnClickListener {
	static String text;
	EditText nombre;
	EditText pass;
	Button crear_usr;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crear_usr);
		nombre= (EditText)findViewById(R.id.nombre);
		pass= (EditText)findViewById(R.id.pass);
		crear_usr = (Button)findViewById(R.id.btcrear);
		
		nombre.setText(info.usuario);
		pass.setText(info.pass);
		info.clear();
		crear_usr.setOnClickListener(this);
	}
	
public void onClick(View arg0) {
		if (arg0.getId()==crear_usr.getId()){
			crear ();
		}
	}

public void crear(){
	info.usuario= nombre.getText().toString();
	info.pass= pass.getText().toString();
	new HttpAsyncTask().execute("https://fast-wildwood-5373.herokuapp.com/ingresar/"+info.usuario+"/"+info.pass);
	
	if (info.getEstado().equals("anadido")){
		info.estado="";
		Intent intent= new Intent(this, preguntas.class);
		finish();
		startActivity(intent);
	}
	else if (info.getEstado().equals("noanadido")){
		Toast.makeText(getBaseContext(), "El Usuario ya existe.", Toast.LENGTH_SHORT).show();
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

// convert inputstream to String
private static String convertInputStreamToString(InputStream inputStream) throws IOException{
    BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
    String line = "";
    String result = "";
    while((line = bufferedReader.readLine()) != null)
        result += line;

    inputStream.close();        
    info.estado= result;
    return result;

}

// check network connection
public boolean isConnected(){
    ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) 
            return true;
        else
            return false;   
}
	private class HttpAsyncTask extends AsyncTask<String, Void, String> {
	
    @Override
    protected String doInBackground(String... urls) {
        return GET(urls[0]);
    }
    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result){
        Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
        }
    }  
}
