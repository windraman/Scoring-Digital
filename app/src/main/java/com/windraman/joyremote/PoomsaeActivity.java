package com.windraman.joyremote;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import java.text.DecimalFormat;

/**
 * Created by windraman on 11/02/2015.
 */
public class PoomsaeActivity extends ActionBarActivity {

    SeekBar seekPS, seekCTSP, seekEoE;
    TextView tvPS, tvCTSP, tvEoE, tvAcc, tvPre, tvJudul ;
    Button btnMinor, btnMajor, btnDone;

    int p1 = 0;
    int p2 = 0;
    int p3 = 0;
    int preavg = 0;
    int Acc = 40;
    int Accint = 0;
    int preavgint = 0;

    DecimalFormat df = new DecimalFormat("#.0");
    DatabaseOLManager dm;
    String ipserver, juri, court, offline;

    protected Vibrator vibe;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.poomsae);

        StrictMode.ThreadPolicy policy = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(policy);
        }

        vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        if (vibe == null) {
            Log.d("vibrate : ", "No vibration service exists.");
        }


        dm = new DatabaseOLManager();
        //ipserver = "10.0.2.2";

        Intent i = getIntent();
        ipserver = i.getStringExtra("ipserver");
        juri = i.getStringExtra("juri");
        court = i.getStringExtra("court");
        offline = i.getStringExtra("offline");

        setupView();
        fungsi();

        tvJudul.setText("Court." + court + " - " + juri );
        enablePre(false);
        enableAcc(false);
    }

    public void setupView() {
        seekPS = (SeekBar) findViewById(R.id.seekPS);
        seekCTSP = (SeekBar) findViewById(R.id.seekCTSP);
        seekEoE = (SeekBar) findViewById(R.id.seekEoE);
        tvPS = (TextView) findViewById(R.id.tvPS);
        tvCTSP = (TextView) findViewById(R.id.tvCTSP);
        tvEoE = (TextView) findViewById(R.id.tvEoE);
        tvAcc = (TextView) findViewById(R.id.tvAcc);
        tvPre = (TextView) findViewById(R.id.tvPre);
        tvJudul = (TextView) findViewById(R.id.tvJudul);
        btnMajor = (Button) findViewById(R.id.btnMajor);
        btnMinor = (Button) findViewById(R.id.btnMinor);
        btnDone = (Button) findViewById(R.id.btnDone);


    }

    public void enablePre(Boolean val){
        seekPS.setEnabled(val);
        seekCTSP.setEnabled(val);
        seekEoE.setEnabled(val);
    }

    public void enableAcc(Boolean val){
        btnMajor.setEnabled(val);
        btnMinor.setEnabled(val);
    }

    public void fungsi(){
        btnMajor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Acc -= 3;
                tvAcc.setText(String.valueOf(df.format((double)Acc/10)));
                vibe.vibrate(80);
            }
        });

        btnMinor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Acc -= 1;
                tvAcc.setText(String.valueOf(df.format((double)Acc/10)));
                vibe.vibrate(80);
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (btnDone.getText().equals("Start")) {
                    enablePre(false);
                    enableAcc(true);
                    btnDone.setText("Acc. Done");
                }else if (btnDone.getText().equals("Acc. Done")) {

                    LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = layoutInflater.inflate(R.layout.popupqs, null);
                    final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                    Button btnKirim = (Button)popupView.findViewById(R.id.btnKirim);
                    Button btnBatal = (Button)popupView.findViewById(R.id.btnBAtal);

                    //Update TextView in PopupWindow dynamically
                    TextView textOut = (TextView) popupView.findViewById(R.id.tvScore);
                    textOut.setText(df.format((double) Acc/10));

                    btnKirim.setOnClickListener(new Button.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            kirimPesan("acc",String.valueOf(Acc));
                            enablePre(true);
                            enableAcc(false);
                            btnDone.setText("Finish");
                        }
                    });

                    btnBatal.setOnClickListener(new Button.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    });

                    popupWindow.showAsDropDown(seekPS, 50, -30);

                }else if (btnDone.getText().equals("Finish")) {

                    //finish();

                    LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = layoutInflater.inflate(R.layout.popupscore, null);
                    final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                    Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
                    Button btnBatal = (Button)popupView.findViewById(R.id.btnBatal);

                    //Update TextView in PopupWindow dynamically
                    TextView textOut = (TextView) popupView.findViewById(R.id.tvScore);
                    textOut.setText(df.format((double) Acc/10 + (double) preavg / 10));

                    btnDismiss.setOnClickListener(new Button.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                            kirimPesan("ps", String.valueOf((double) p1 / 10));
                            kirimPesan("ctsp", String.valueOf((double) p2 / 10));
                            kirimPesan("eoe", String.valueOf((double) p3 / 10));
                            kirimPesan("pre", String.valueOf(preavg));
                            enablePre(false);
                            finish();
                        }
                    });

                    btnBatal.setOnClickListener(new Button.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    });

                    popupWindow.showAsDropDown(seekPS, 50, -30);
                }
            }
        });

        seekPS.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                p1 = progress + 5;
                // Log.d("progress : ",String.valueOf(p));

                tvPS.setText(String.valueOf((double) p1 / 10));
                preavg = (p1 + p2 + p3);
                tvPre.setText(String.valueOf(df.format((double) preavg / 10)));

            }
        });

        seekCTSP.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                p2 = progress + 5;
                // Log.d("progress : ",String.valueOf(p));

                    tvCTSP.setText(String.valueOf((double)p2/10));
                    preavg = (p1+p2+p3);
                    tvPre.setText(String.valueOf(df.format((double)preavg/10)));


            }
        });

        seekEoE.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                p3 = progress + 5;
                // Log.d("progress : ",String.valueOf(p));
                    tvEoE.setText(String.valueOf((double)p3/10));
                    preavg = (p1+p2+p3);
                    tvPre.setText(String.valueOf(df.format((double)preavg/10)));

            }

        });

        tvAcc.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

               // kirimPesan("acc",String.valueOf(Acc));
            }
        });

        tvPre.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                //kirimPesan("pre",String.valueOf(preavg));
            }
        });


    }




    public void kirimPesan(String warna, String pesan) {
    if (offline.equals("false")) {
        dm.InsertCustomSQL("INSERT INTO `perintah`(`dari`,`kepada`,`court`,`pesan`,`status`) VALUES('" + juri + "','" + warna + "','" + court + "','" + pesan + "', 1)", ipserver);
    }
    }
}
