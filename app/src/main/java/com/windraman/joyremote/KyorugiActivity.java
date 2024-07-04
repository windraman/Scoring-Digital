package com.windraman.joyremote;

import static android.os.Build.VERSION_CODES.R;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.windraman.joyremote.media.Effects;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import inputmanagercompat.InputManagerCompat;


public class KyorugiActivity extends ActionBarActivity {
    DatabaseOLManager dm;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 1000L;
    Boolean timerTick = true;
    Button btn1Biru, btn2Biru, btn3Biru, btn4Biru, btn1Merah, btn2Merah, btn3Merah, btn4Merah, btnExit;
    TextView scoreBiru, scoreMerah, monBiru, monMerah;
    String tekMerah = "-", tekBiru = "-", liBiru, liMerah, ipserver, juri, court, offline;
    Timer myTimer;
    MyTimerTask myTimerTask;
    private long startTime = 0L;
    private Handler customHandler = new Handler();

    protected Vibrator vibe;

    long[] once = { 0, 50 };
    long[] twice = { 0, 50, 100, 50 };

    private InputManagerCompat mInputManager;


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.kyorugi);

        vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        if (vibe == null) {
            Log.d("vibrate : ", "No vibration service exists.");
        }

        Intent i = getIntent();
        ipserver = i.getStringExtra("ipserver");
        juri = i.getStringExtra("juri");
        court = i.getStringExtra("court");
        offline = i.getStringExtra("offline");

        Effects.getInstance().init(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        dm = new DatabaseOLManager();

        setupView();
        fungsiTombol();

        myTimerTask = new MyTimerTask();
        myTimer = new Timer();

        myTimer.schedule(myTimerTask, 1000, 1000);



    }



    void findControllersAndAttachShips() {
        int[] deviceIds = mInputManager.getInputDeviceIds();
        for (int deviceId : deviceIds) {
            InputDevice dev = mInputManager.getInputDevice(deviceId);
            int sources = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD) {
                sources = dev.getSources();
            }
            // if the device is a gamepad/joystick, create a ship to represent it
            if (((sources & InputDevice.SOURCE_GAMEPAD) == InputDevice.SOURCE_GAMEPAD) ||
                    ((sources & InputDevice.SOURCE_JOYSTICK) == InputDevice.SOURCE_JOYSTICK)) {
                // if the device has a gamepad or joystick
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int deviceId = event.getDeviceId();
        if (deviceId != -1) {
                return true;

        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        int deviceId = event.getDeviceId();
        if (deviceId != -1) {
            //Toast.makeText(KyorugiActivity.this,String.valueOf(keyCode) , Toast.LENGTH_SHORT).show();
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    btn1Merah.performClick();
                    break;
                case KeyEvent.KEYCODE_DPAD_UP:
                    btn2Merah.performClick();
                    break;
                case 192:
                    btn3Merah.performClick();
                    break;
                case 194:
                    btn4Merah.performClick();
                    break;
                case 190:
                    btn1Biru.performClick();
                    break;
                case 188:
                    btn2Biru.performClick();
                    break;
                case 193:
                    btn3Biru.performClick();
                    break;
                case 195:
                    btn4Biru.performClick();
                    break;
            }
        }

        return super.onKeyUp(keyCode, event);
    }



    public void setupView() {
        btn1Biru = (Button) findViewById(R.id.btn1Blue);
        btn2Biru = (Button) findViewById(R.id.btn2Blue);
        btn3Biru = (Button) findViewById(R.id.btn3Blue);
        btn4Biru = (Button) findViewById(R.id.btn4Blue);
        btn1Merah = (Button) findViewById(R.id.btn1Merah);
        btn2Merah = (Button) findViewById(R.id.btn2Merah);
        btn3Merah = (Button) findViewById(R.id.btn3Merah);
        btn4Merah = (Button) findViewById(R.id.btn4Merah);
        scoreBiru = (TextView) findViewById(R.id.scoreBiru);
        scoreMerah = (TextView) findViewById(R.id.scoreMerah);
        monBiru = (TextView) findViewById((R.id.tvMonBiru));
        monMerah = (TextView) findViewById(R.id.tvMonMerah);
        btnExit = (Button) findViewById(R.id.btnExit);
    }

    public void fungsiTombol() {
        btn1Biru.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //scoreBiru.setText(String.valueOf(Long.parseLong(scoreBiru.getText().toString())+ 1));
                tekBiru = "1";
                kirimPesan("biru", "1");
                updateMon();
                //vibe.vibrate(once,-1);
            }
        });
        btn2Biru.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //scoreBiru.setText(String.valueOf(Long.parseLong(scoreBiru.getText().toString())+ 2));
                tekBiru = "2";
                kirimPesan("biru", "2");
                updateMon();
                //vibe.vibrate(twice,-1);
            }
        });
        btn3Biru.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //scoreBiru.setText(String.valueOf(Long.parseLong(scoreBiru.getText().toString())+ 3));
                tekBiru = "3";
                kirimPesan("biru", "3");
                updateMon();
                //vibe.vibrate(once,-1);
            }
        });
        btn4Biru.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //scoreBiru.setText(String.valueOf(Long.parseLong(scoreBiru.getText().toString())+ 4));
                tekBiru = "4";
                kirimPesan("biru", "4");
                updateMon();
                //vibe.vibrate(twice,-1);
            }
        });
        btn1Merah.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //scoreMerah.setText(String.valueOf(Long.parseLong(scoreMerah.getText().toString())+ 1));
                tekMerah = "1";
                kirimPesan("merah", "1");
                updateMon();
                //vibe.vibrate(once,-1);
            }
        });
        btn2Merah.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //scoreMerah.setText(String.valueOf(Long.parseLong(scoreMerah.getText().toString())+ 2));
                tekMerah = "2";
                kirimPesan("merah", "2");
                updateMon();
                //vibe.vibrate(twice,-1);
            }
        });
        btn3Merah.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //scoreMerah.setText(String.valueOf(Long.parseLong(scoreMerah.getText().toString())+ 3));
                tekMerah = "3";
                kirimPesan("merah", "3");
                updateMon();
                //vibe.vibrate(once,-1);
            }
        });
        btn4Merah.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //scoreMerah.setText(String.valueOf(Long.parseLong(scoreMerah.getText().toString())+ 4));
                tekMerah = "4";
                kirimPesan("merah", "4");
                updateMon();
                //vibe.vibrate(twice,-1);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void ambilPesan() {
        ArrayList<ArrayList<Object>> data = dm.LoadCustomOrder("SELECT * FROM perintah WHERE status = 1 AND kepada = 'kyorugijoy' AND division = "+court+" ORDER BY waktu DESC", ipserver);
        for (int posisi = 0; posisi < data.size(); posisi++) {
            ArrayList<Object> baris = data.get(posisi);

            if (baris.get(3).toString().equals("poinbiru")) {
                scoreBiru.setText(baris.get(4).toString());
                dm.InsertCustomSQL("UPDATE perintah SET status = 2 WHERE  id = " + baris.get(0).toString(), ipserver);
            }
            if (baris.get(3).toString().equals("poinmerah")) {
                scoreMerah.setText(baris.get(4).toString());
                dm.InsertCustomSQL("UPDATE perintah SET status = 2 WHERE id = " + baris.get(0).toString(), ipserver);

            }
        }
    }

    public void kirimPesan(String warna, String pesan) {
        if (pesan.equals("1") || pesan.equals("2")) {
            Effects.getInstance().playSound(Effects.SOUND_1);
        }
        if (pesan.equals("3") || pesan.equals("4")) {
            Effects.getInstance().playSound(Effects.SOUND_2);
        }
        if (offline.equals("false")) {
            dm.InsertCustomSQL("INSERT INTO `perintah`(`dari`,`kepada`,`pesan`,`status`) VALUES('" + juri + "','" + warna + "','" + pesan + "', 1)", ipserver);
        }
     }

    public void updateMon() {

        liBiru = monBiru.getText().toString() + tekBiru + "-";
        monBiru.setText(liBiru.substring(1, 25));
        tekBiru = "-";
        liMerah = monMerah.getText().toString() + tekMerah + "-";
        monMerah.setText(liMerah.substring(1, 25));
        tekMerah = "-";
    }

    //@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
           // return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    //@Override
    public void onBackPressed() {
        finish();
       //System.exit(0);
    }

    class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id) {
//            Toast.makeText(parent.getContext(), "Item is " +
//                    parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
            if (offline.equals("false")) {
                dm.LoadCustomUser("SELECT * FROM user WHERE userid = '" + parent.getItemAtPosition(pos).toString() + "'", ipserver);
                dm.InsertCustomSQL("UPDATE user SET status = 1 WHERE userid = '" + parent.getItemAtPosition(pos).toString() + "'", ipserver);
            }
        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }

    class MyTimerTask extends TimerTask {

        //@Override
        public void run() {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (offline.equals("false")) {
                        ambilPesan();
                    }
                    updateMon();

                }
            });
        }

    }

}
