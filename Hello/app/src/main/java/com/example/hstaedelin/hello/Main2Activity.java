package com.example.hstaedelin.hello;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    // on déclare les objets
    private TCPClient mTcpClient;
    TextView status;
    EditText message, adresse;

    Bundle bundle;
    Button connexion, envoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        message = (EditText) findViewById(R.id.editTextMessage);
        adresse = (EditText) findViewById(R.id.editTextIP);
        status = (TextView) findViewById(R.id.textViewStatus);
        status.setText("OnCreate2");

        connexion = (Button) findViewById(R.id.buttonConnexion2);
        envoi = (Button) findViewById(R.id.buttonEnvoi);
        connexion.setOnClickListener(this);
        envoi.setOnClickListener(this);

        bundle = getIntent().getExtras();
        String param = bundle.getString("ip");
        adresse.setText(param);

        // Toast.makeText(getApplicationContext(), "Demarrage activite, reussie").show();
        Log.d("TAG", "Connexion en cours *** Connecting ***");
    }

    public void onClick(View v) {

        if (v.getId() == R.id.buttonConnexion2) {
            status.setText("Connecte");
            new connectTask().execute("");

        }

        if (v.getId() == R.id.buttonEnvoi) {
            status.setText("Message envoye");
            if (mTcpClient != null) {
                String cmd = 'M' + message.getText().toString() + "\n\r";
                mTcpClient.sendMessage(cmd);
            }
        }
    }

        public class connectTask extends AsyncTask<String, String, TCPClient> {
            @Override
            protected TCPClient doInBackground(String... message) {

                //we create a TCPClient object and
                String adresseIp = adresse.getText().toString();
                int portTcp = 2502;
                Log.d("connectTask", "doItInBackGround ");

                mTcpClient = new TCPClient(adresseIp, portTcp, new TCPClient.OnMessageReceived() {
                    @Override
                    //here the messageReceived method is implemented
                    public void messageReceived(String message) {
                        //this method calls the onProgressUpdate
                        publishProgress(message);
                        Log.d("doInbackGround", "recu " + message);

                    }
                });
                mTcpClient.run();
                return null;
            }



            @Override
            protected void onProgressUpdate(String... values) {
                super.onProgressUpdate(values);
                // reponse.setText(values[0]);
            }
        }// classe connectTask
}

