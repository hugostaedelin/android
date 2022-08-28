package com.example.hstaedelin.hello;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button connexion;
    EditText adresseIP;
    TextView Etat;

    Intent activite2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Etat = (TextView)findViewById(R.id.textView);
        Etat.setText("Etat : Déconnecté");
        connexion = (Button)findViewById(R.id.buttonConnexion);
        connexion.setOnClickListener(this);



        if(android.os.Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }

    public void onClick(View v)
    {
        if(v.getId() == R.id.buttonConnexion)
        {
            Etat.setText("Etat : Connexion en cours ...");
            adresseIP = (EditText)findViewById(R.id.EditText);
            Toast.makeText(getApplicationContext(),"Connexion sur l'addresse "+adresseIP.getText(),Toast.LENGTH_LONG).show();
            Log.d("TAG","Connexion en cours *** Connecting ***");
            activite2 = new Intent(MainActivity.this, Main2Activity.class);
            activite2.putExtra("ip",adresseIP.getText().toString());
            this.startActivity(activite2);
        }

    }
}
