package com.windraman.joyremote;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by windraman on 11/02/2015.
 */
public class PilihActivity extends ActionBarActivity {

   Button btnKyorugi, btnPoomsae;

   EditText edTIP, edTCourt;
   Spinner spinJuri;
   CheckBox cBOffline;

    private SharedPreferences preferenceSetting;
    private SharedPreferences.Editor preferenceEditor;

    private static final int PREFERENCE_MODE_PRIVATE = 0;

    DatabaseOLManager dm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pilihmode);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        dm = new DatabaseOLManager();

        setupView();
        fungsiTombol();


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.juri_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinJuri.setAdapter(adapter);

        // spinJuri.setOnItemSelectedListener(new MyOnItemSelectedListener());

        getPref();

    }

    public void setupView(){
        btnKyorugi = (Button) findViewById(R.id.btnKyorugi);
        btnPoomsae = (Button) findViewById(R.id.btnPoomsae);
        edTIP = (EditText) findViewById(R.id.edTIP);
        edTCourt = (EditText) findViewById(R.id.edTCourt);
        spinJuri = (Spinner) findViewById(R.id.spinJuri);
        cBOffline = (CheckBox) findViewById(R.id.cBOffline);
    }

    public void fungsiTombol() {
        btnKyorugi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                savePref();

                if (cBOffline.isChecked() ==false) {
                    dm.InsertCustomSQL("INSERT INTO `perintah`(`dari`,`kepada`,`division`,`pesan`,`status`) VALUES('" + spinJuri.getSelectedItem().toString() + "','kyorugi','" + edTCourt.getText().toString() + "','login', 1)", edTIP.getText().toString());
                }
                Intent KyorugiIntent = new Intent(getApplicationContext(), KyorugiActivity.class);
                KyorugiIntent.putExtra("ipserver",edTIP.getText().toString());
                KyorugiIntent.putExtra("juri",spinJuri.getSelectedItem().toString());
                KyorugiIntent.putExtra("court",edTCourt.getText().toString());
                KyorugiIntent.putExtra("offline",String.valueOf(cBOffline.isChecked()));
                startActivity(KyorugiIntent);
            }
        });
        btnPoomsae.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                savePref();
                int adajuri = 0;

                if (cBOffline.isChecked() == false) {
                    Toast.makeText(PilihActivity.this,spinJuri.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
                    if (adajuri < 1) {
                        dm.InsertCustomSQL("INSERT INTO `perintah`(`dari`,`kepada`,`court`,`pesan`,`status`) VALUES('" + spinJuri.getSelectedItem().toString() + "','poomsae','" + edTCourt.getText().toString() + "','login', 1)", edTIP.getText().toString());
                        Intent PoomsaeIntent = new Intent(getApplicationContext(), PoomsaeActivity.class);
                        PoomsaeIntent.putExtra("ipserver", edTIP.getText().toString());
                        PoomsaeIntent.putExtra("juri", spinJuri.getSelectedItem().toString());
                        PoomsaeIntent.putExtra("court", edTCourt.getText().toString());
                        PoomsaeIntent.putExtra("offline", String.valueOf(cBOffline.isChecked()));
                        startActivity(PoomsaeIntent);
                    }else{
                        Toast.makeText(PilihActivity.this,"Juri sudah login, pilih posisi juri lain",Toast.LENGTH_LONG).show();
                    }

                 }else {
                    Intent PoomsaeIntent = new Intent(getApplicationContext(), PoomsaeActivity.class);
                    PoomsaeIntent.putExtra("ipserver", edTIP.getText().toString());
                    PoomsaeIntent.putExtra("juri", spinJuri.getSelectedItem().toString());
                    PoomsaeIntent.putExtra("court", edTCourt.getText().toString());
                    PoomsaeIntent.putExtra("offline", String.valueOf(cBOffline.isChecked()));
                    startActivity(PoomsaeIntent);
                }
            }
        });
    }

    public void ambilPesan() {
        ArrayList<ArrayList<Object>> data = dm.LoadCustomOrder("SELECT * FROM perintah WHERE status = 1 AND kepada = 'poomsaejoy' AND court = '"+edTCourt.getText().toString()+"' ORDER BY waktu DESC", edTIP.getText().toString());
        for (int posisi = 0; posisi < data.size(); posisi++) {
            ArrayList<Object> baris = data.get(posisi);

            if (baris.get(3).toString().equals("juristat")) {
                dm.InsertCustomSQL("UPDATE perintah SET status = 2 WHERE id = " + baris.get(0).toString(), edTIP.getText().toString());
            }
            if (baris.get(3).toString().equals("juristat")) {
                dm.InsertCustomSQL("UPDATE perintah SET status = 2 WHERE id = " + baris.get(0).toString(), edTIP.getText().toString());
            }
        }
    }

    public void savePref(){
        preferenceSetting = getPreferences(PREFERENCE_MODE_PRIVATE);
        preferenceEditor = preferenceSetting.edit();

        preferenceEditor.putString("ipserver", edTIP.getText().toString());
        preferenceEditor.putString("court", edTCourt.getText().toString());
        preferenceEditor.putInt("juri",spinJuri.getSelectedItemPosition());
        preferenceEditor.commit();
    }

    public void getPref(){
        preferenceSetting = getPreferences(PREFERENCE_MODE_PRIVATE);
        edTIP.setText(preferenceSetting.getString("ipserver","10.0.2.2"));
        edTCourt.setText(preferenceSetting.getString("court","1"));
        spinJuri.setSelection(preferenceSetting.getInt("juri", 0));
    }

}

