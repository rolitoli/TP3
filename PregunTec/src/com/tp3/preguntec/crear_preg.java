package com.tp3.preguntec;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class crear_preg extends Activity implements OnClickListener{
	String textPregunta;
	Button crear;
	EditText preg;
	TextView usr;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crear_preg);
		preg= (EditText)findViewById(R.id.textPreg);
		usr= (TextView)findViewById(R.id.nomb_usr);
		crear = (Button)findViewById(R.id.P_lista);
		info.estado="";
		usr.setText(info.usuario);
	}
	@Override
	public void onClick(View arg0) {
		textPregunta= preg.getText().toString();
		if(arg0.getId()==crear.getId()){
			if (textPregunta!=""){
				
			}
			else{
				Toast.makeText(getBaseContext(), "Debe de escribir la pregunta", Toast.LENGTH_LONG).show();
			}
		}
		
	}
	
}
