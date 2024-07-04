package com.windraman.joyremote;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseOLManager {

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_TERBARU = "terbaru";

    private static final String ROW_ID = "_id";
    private static final String ROW_CODE = "code";
    private static final String ROW_NAMAMENU = "namaMenu";
    private static final String ROW_HARGA = "harga";
    private static final String ROW_KATEGORI = "kategori";
    private static final String ROW_INFO = "info";
    private static final String ROW_STOCK = "stock";


    private static final String ROW_NOTRX = "noTrx";
    private static final String ROW_TGLTRX = "tglTrx";
    private static final String ROW_WAKTUTRX = "waktuTrx";
    private static final String ROW_USERID = "userid";
    private static final String ROW_JUMLAH = "jumlah";
    private static final String ROW_TOTAL = "total";
    //private static final String ROW_FLOW = "flow";
    private static final String ROW_BAYAR = "bayar";
    private static final String ROW_KEMBALI = "kembali";
    private static final String ROW_KODEPLG = "kodePlg";

    private static final String ROW_WAKTULOG = "waktu";
    private static final String ROW_MEJA = "meja";
    private static final String ROW_SEAT = "seat";
    private static final String ROW_LAYAN = "layan";
    private static final String ROW_PESAN = "pesan";
    private static final String ROW_SAJI = "saji";
    private static final String ROW_KEPADA = "kepada";
    private static final String ROW_MSG = "msg";
    private static final String ROW_SELESAI = "selesai";
    private static final String ROW_WAKTU = "waktu";
    private static final String ROW_TARGET = "target";
    private static final String ROW_SIAP = "siap";
    private static final String ROW_PANGGIL = "panggil";

    private static final String ROW_ONLINE = "online";
    private static final String ROW_LASTLOG = "lastlogid";
    private static final String ROW_IP = "ip";

    private static final String ROW_LOAD = "load";
    private static final String ROW_SINGKATAN = "singkatan";
    private static final String ROW_GAMBAR_URL = "gambarurl";
    private static final String ROW_JENIS = "jenis";

    private static final String TAG_LOGS = "logs";
    private static final String TAG_TRANSIT = "transit";
    private static final String TAG_TRANSIT_DETAIL = "transitdetail";
    private static final String TAG_MEJA = "meja";
    private static final String TAG_TRANSAKSI = "transaksi";
    private static final String TAG_MENU = "makanan";


    private static final String NAMA_DB = "remote";// nama database
    private static final String NAMA_TABEL_MENU = "menu";
    private static final String NAMA_TABEL_TRX = "transaksi";
    private static final String NAMA_TABEL_TRXDETAIL = "transaksidetail";
    private static final String NAMA_TABEL_TRANSIT = "transit";// nama tabel
    private static final String NAMA_TABEL_LOG = "log";
    private static final String NAMA_TABEL_SETUP = "setup";

    String url_add_trx;

    JSONParser jParser = new JSONParser();

    JSONArray logs = null;

    JSONArray order = null;

    JSONArray transaksi = null;

    JSONArray transit = null;

    JSONArray mejaarr = null;

    JSONArray user = null;

    JSONArray totalitem = null;

    JSONArray menu = null;

    JSONArray paket = null;

    JSONArray kategori = null;

    JSONArray juri = null;

    ArrayList<HashMap<String, String>> logList;

    int terbaru;

    //DatabaseManager dm;

    public ArrayList<ArrayList<Object>> LoadMenu(String cari, String ipadd) {

        String url_get_menu = "http://" + ipadd + "/restoran_android/ambil_menu.php";

        ArrayList<ArrayList<Object>> menuListArray = new ArrayList<ArrayList<Object>>();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("cari", cari));
        JSONObject json = jParser.makeHttpRequest(url_get_menu, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("All menu : ", json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                menu = json.getJSONArray(TAG_MENU);

                // looping through All Products

                for (int i = 0; i < menu.length(); i++) {
                    JSONObject c = menu.getJSONObject(i);

                    // Storing each json item in variable

                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(c.getString(ROW_ID));
                    dataList.add(c.getString(ROW_CODE));
                    dataList.add(c.getString(ROW_NAMAMENU));
                    dataList.add(c.getString(ROW_HARGA));
                    dataList.add(c.getString(ROW_KATEGORI));
                    dataList.add(c.getString(ROW_INFO));
                    dataList.add(c.getString(ROW_STOCK));
                    dataList.add(c.getString(ROW_LOAD));
                    dataList.add(c.getString(ROW_TARGET));
                    dataList.add(c.getString(ROW_GAMBAR_URL));
                    dataList.add(c.getString(ROW_SINGKATAN));

                    menuListArray.add(dataList);

                }
            } else {
                // no products found

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return menuListArray;
    }

    public ArrayList<ArrayList<Object>> LoadMenuDetail(String cari, String ipadd) {

        String url_get_menu = "http://" + ipadd + "/restoran_android/ambil_menu_detail.php";

        ArrayList<ArrayList<Object>> menuListArray = new ArrayList<ArrayList<Object>>();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("cari", cari));
        JSONObject json = jParser.makeHttpRequest(url_get_menu, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("All menu : ", json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                menu = json.getJSONArray(TAG_MENU);

                // looping through All Products

                for (int i = 0; i < menu.length(); i++) {
                    JSONObject c = menu.getJSONObject(i);

                    // Storing each json item in variable

                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(c.getString(ROW_ID));
                    dataList.add(c.getString(ROW_CODE));
                    dataList.add(c.getString(ROW_NAMAMENU));
                    dataList.add(c.getString(ROW_HARGA));
                    dataList.add(c.getString(ROW_KATEGORI));
                    dataList.add(c.getString(ROW_INFO));
                    dataList.add(c.getString(ROW_STOCK));
                    dataList.add(c.getString(ROW_LOAD));
                    dataList.add(c.getString(ROW_TARGET));
                    dataList.add(c.getString(ROW_GAMBAR_URL));
                    dataList.add(c.getString(ROW_SINGKATAN));

                    menuListArray.add(dataList);

                }
            } else {
                // no products found

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return menuListArray;
    }

    public ArrayList<ArrayList<Object>> LoadMenuCustom(String sql, String ipadd) {
        // Building Parameters

        String url_get_transitcust = "http://" + ipadd + "/restoran_android/select_custom_menu.php";

        ArrayList<ArrayList<Object>> menuListArray = new ArrayList<ArrayList<Object>>();
        //String closed = "false";
        //String to = "nizam";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sql", sql));
        JSONObject json = jParser.makeHttpRequest(url_get_transitcust, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("All menu custom : ", json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                menu = json.getJSONArray(TAG_MENU);

                // looping through All Products

                for (int i = 0; i < menu.length(); i++) {
                    JSONObject c = menu.getJSONObject(i);

                    // Storing each json item in variable


                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(c.getString(ROW_ID));
                    dataList.add(c.getString(ROW_CODE));
                    dataList.add(c.getString(ROW_NAMAMENU));
                    dataList.add(c.getString(ROW_HARGA));
                    dataList.add(c.getString(ROW_KATEGORI));
                    dataList.add(c.getString(ROW_INFO));
                    dataList.add(c.getString(ROW_STOCK));
                    dataList.add(c.getString(ROW_LOAD));
                    dataList.add(c.getString(ROW_HARGA));
                    dataList.add(c.getString(ROW_TARGET));
                    dataList.add(c.getString(ROW_GAMBAR_URL));
                    dataList.add(c.getString(ROW_SINGKATAN));
                    dataList.add(c.getString(ROW_JENIS));

                    menuListArray.add(dataList);

                }
            } else {
                // no products found
                // Launch Add New product Activity
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return menuListArray;
    }


    public void addTransaksi(Long noTrx, Long meja, String userid, Long jumlah, Long total, Long bayar, Long kembali, String kodePlg, Boolean selesai, String info, String ipadd) {
        //Toast.makeText(getBaseContext(),  userid, Toast.LENGTH_LONG).show();
        url_add_trx = "http://" + ipadd + "/restoran_android/add_transaksi.php";
        //url_add_trx = "http://10.0.2.2/restoran_android/add_transaksi.php";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ROW_NOTRX, String.valueOf(noTrx)));
        params.add(new BasicNameValuePair(ROW_MEJA, String.valueOf(meja)));
        params.add(new BasicNameValuePair(ROW_USERID, userid));
        params.add(new BasicNameValuePair(ROW_JUMLAH, String.valueOf(jumlah)));
        params.add(new BasicNameValuePair(ROW_TOTAL, String.valueOf(total)));
        params.add(new BasicNameValuePair(ROW_BAYAR, String.valueOf(bayar)));
        params.add(new BasicNameValuePair(ROW_KEMBALI, String.valueOf(kembali)));
        params.add(new BasicNameValuePair(ROW_KODEPLG, kodePlg));
        params.add(new BasicNameValuePair(ROW_SELESAI, String.valueOf(selesai)));
        params.add(new BasicNameValuePair(ROW_INFO, info));


        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jParser.makeHttpRequest(url_add_trx, "POST", params);

        // check log cat from response
        Log.d("Transaksi berhasil ", json.toString());

        // check for success tag
        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {

                params.clear();

            } else {
                //finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void DeleteTransaksi(Long noTrx, String ipadd) {

        String url_delete_transaksi = "http://" + ipadd + "/restoran_android/delete_transaksi.php";
        int success;
        try {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("noTrx", String.valueOf(noTrx)));

            // getting product details by making HTTP request
            JSONObject json = jParser.makeHttpRequest(url_delete_transaksi, "POST", params);

            // check your log for json response
            Log.d("Delete transaksi", json.toString());

            // json success tag
            success = json.getInt(TAG_SUCCESS);
            if (success == 1) {
                // product successfully deleted
                // notify previous activity by sending code 100

                // send result code 100 to notify about product deletion

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<ArrayList<Object>> LoadTransaksi(String cari, String ipadd) {
        // Building Parameters

        String url_get_transaksi = "http://" + ipadd + "/restoran_android/ambil_transaksi.php";

        ArrayList<ArrayList<Object>> transaksiListArray = new ArrayList<ArrayList<Object>>();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("cari", cari));
        JSONObject json = jParser.makeHttpRequest(url_get_transaksi, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("All transaksi ", json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                transaksi = json.getJSONArray(TAG_TRANSAKSI);
                //Toast.makeText(Context, logs.length(), Toast.LENGTH_LONG).show();
                // looping through All Products

                for (int i = 0; i < transaksi.length(); i++) {
                    JSONObject c = transaksi.getJSONObject(i);

                    // Storing each json item in variable

                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(c.getString(ROW_ID));
                    dataList.add(c.getString(ROW_NOTRX));
                    dataList.add(c.getString(ROW_MEJA));
                    dataList.add(c.getString(ROW_TGLTRX));
                    dataList.add(c.getString(ROW_USERID));
                    dataList.add(c.getString(ROW_JUMLAH));
                    dataList.add(c.getString(ROW_TOTAL));
                    dataList.add(c.getString(ROW_BAYAR));
                    dataList.add(c.getString(ROW_KEMBALI));
                    dataList.add(c.getString(ROW_KODEPLG));
                    dataList.add(c.getString(ROW_SELESAI));

                    transaksiListArray.add(dataList);

                }
            } else {
                // no products found

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return transaksiListArray;
    }

    public int LoadJTransaksi(String ipadd) {
        // Building Parameters

        String url_get_transaksicount = "http://" + ipadd + "/restoran_android/ambil_record_count.php";
        int jtransaksi = 0;

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        JSONObject json = jParser.makeHttpRequest(url_get_transaksicount, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("All transaksi record ", json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                transaksi = json.getJSONArray(TAG_TRANSAKSI);
                //Toast.makeText(Context, logs.length(), Toast.LENGTH_LONG).show();
                // looping through All Products

                JSONObject c = transaksi.getJSONObject(0);

                jtransaksi = c.getInt("jtransaksi");

            } else {
                // no products found

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jtransaksi;
    }

    public int LoadJProses(Long target, String ipadd) {
        // Building Parameters

        String url_get_prosescount = "http://" + ipadd + "/restoran_android/ambil_proses_count.php";
        int jtransaksi = 0;

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("target", String.valueOf(target)));
        JSONObject json = jParser.makeHttpRequest(url_get_prosescount, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("Jumlah proses ", json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                transit = json.getJSONArray("proses");
                //Toast.makeText(Context, logs.length(), Toast.LENGTH_LONG).show();
                // looping through All Products

                JSONObject c = transit.getJSONObject(0);

                jtransaksi = c.getInt("jproses");

            } else {
                // no products found

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jtransaksi;
    }

    public int LoadJCustom(String sql, String ipadd) {
        // Building Parameters

        String url_get_customcount = "http://" + ipadd + "/remote/select_custom_count.php";
        int jjuri = 0;

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sql", sql));
        JSONObject json = jParser.makeHttpRequest(url_get_customcount, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("Jumlah custom : ", json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                juri = json.getJSONArray("jumlah");
                // looping through All Products

                JSONObject c = juri.getJSONObject(0);

                jjuri = c.getInt("jcustom");

            } else {
                // no products found

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jjuri;
    }

    public Long LoadPersenSelesai(Long noTrx, Long meja, String ipadd) {
        // Building Parameters

        String url_get_persenselesai = "http://" + ipadd + "/restoran_android/presentasi_selesai.php";
        Long jtransaksi = (long) 0;

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("noTrx", String.valueOf(noTrx)));
        params.add(new BasicNameValuePair("meja", String.valueOf(meja)));
        JSONObject json = jParser.makeHttpRequest(url_get_persenselesai, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("Persentasi meja" + meja + " :", json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                transit = json.getJSONArray("transit");
                //Toast.makeText(Context, logs.length(), Toast.LENGTH_LONG).show();
                // looping through All Products

                JSONObject c = transit.getJSONObject(0);

                jtransaksi = c.getLong("persenselesai");

            } else {
                // no products found

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jtransaksi;
    }

    public void updateTransaksi(Long noTrx, Long meja, String userid, Long jumlah, Long total, Long bayar, Long kembali, String kodePlg, Boolean selesai, String info, String ipadd) {
        //Toast.makeText(getBaseContext(),  userid, Toast.LENGTH_LONG).show();

        String url_update_transaksi = "http://" + ipadd + "/restoran_android/update_transaksi.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("noTrx", String.valueOf(noTrx)));
        params.add(new BasicNameValuePair("meja", String.valueOf(meja)));
        params.add(new BasicNameValuePair("userid", userid));
        params.add(new BasicNameValuePair("jumlah", String.valueOf(jumlah)));
        params.add(new BasicNameValuePair("total", String.valueOf(total)));
        params.add(new BasicNameValuePair("bayar", String.valueOf(bayar)));
        params.add(new BasicNameValuePair("kembali", String.valueOf(kembali)));
        params.add(new BasicNameValuePair("selesai", String.valueOf(selesai)));
        params.add(new BasicNameValuePair("info", info));

        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jParser.makeHttpRequest(url_update_transaksi, "POST", params);

        // check log cat from response
        Log.d("Create Response", json.toString());

        // check for success tag
        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {

                params.clear();

            } else {
                //finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public int addTransaksiDetail(Long noTrx, String code, Long harga, Long jumlah, Long total, String info, String ipadd) {
        //Toast.makeText(getBaseContext(),  userid, Toast.LENGTH_LONG).show();
        String url_add_trxdetail = "http://" + ipadd + "/restoran_android/add_detail_trx.php";

        int berhasil = 0;
        //String url_add_trx = "http://10.0.2.2/restoran_android/add_transaksi.php";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ROW_NOTRX, String.valueOf(noTrx)));
        params.add(new BasicNameValuePair("code", code));
        params.add(new BasicNameValuePair("harga", String.valueOf(harga)));
        params.add(new BasicNameValuePair("jumlah", String.valueOf(jumlah)));
        params.add(new BasicNameValuePair(ROW_TOTAL, String.valueOf(total)));
        params.add(new BasicNameValuePair("info", info));

        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jParser.makeHttpRequest(url_add_trxdetail, "POST", params);

        // check log cat from response
        Log.d("Create Response", json.toString());

        // check for success tag
        try {
            int success = json.getInt("success");

            if (success == 1) {
                berhasil = success;
                params.clear();

            } else {
                berhasil = success;
                //finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return berhasil;
    }

    public void addLog(String userid, String kepada, String msg, String info, String ipadd) {
        //Toast.makeText(getBaseContext(),  userid, Toast.LENGTH_LONG).show();

        String url_add_log = "http://" + ipadd + "/restoran_android/add_log.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userid", userid));
        params.add(new BasicNameValuePair("kepada", kepada));
        params.add(new BasicNameValuePair("msg", msg));
        params.add(new BasicNameValuePair("info", info));


        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jParser.makeHttpRequest(url_add_log, "POST", params);

        // check log cat from response
        Log.d("Log :", json.toString());

        // check for success tag
        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {

                params.clear();

            } else {
                //finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<HashMap<String, String>> LoadLogs(String to1, String to2, String to3, String ipadd) {
        // Building Parameters

        String url_get_log = "http://" + ipadd + "/restoran_android/ambil_log.php";

        logList = new ArrayList<HashMap<String, String>>();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("to1", to1));
        params.add(new BasicNameValuePair("to2", to2));
        params.add(new BasicNameValuePair("to3", to3));
        JSONObject json = jParser.makeHttpRequest(url_get_log, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("All Logs: ", json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                logs = json.getJSONArray(TAG_LOGS);

                // looping through All Products

                for (int i = 0; i < logs.length(); i++) {
                    JSONObject c = logs.getJSONObject(i);

                    // Storing each json item in variable
                    String id = c.getString(ROW_ID);
                    String waktu = c.getString(ROW_WAKTU);
                    String userid = c.getString(ROW_USERID);
                    String kepada = c.getString(ROW_KEPADA);
                    String msg = c.getString(ROW_MSG);
                    String info = c.getString(ROW_INFO);


                    // creating new HashMap
                    HashMap<String, String> map = new HashMap<String, String>();


                    // adding each child node to HashMap key => value
                    map.put(ROW_ID, id);
                    map.put(ROW_WAKTU, waktu);
                    map.put(ROW_USERID, userid);
                    map.put(ROW_KEPADA, kepada);
                    map.put(ROW_MSG, msg);
                    map.put(ROW_INFO, info);

                    // adding HashList to ArrayList
                    logList.add(map);
                }
            } else {
                // no products found
                // Launch Add New product Activity
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return logList;
    }

    public int cekBaru(String to, String info, String ipadd) {
        // Building Parameters

        String url_cek_log = "http://" + ipadd + "/restoran_android/cek_log_baru.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("to", to));
        params.add(new BasicNameValuePair("info", info));
        JSONObject json = jParser.makeHttpRequest(url_cek_log, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("Terbaru : ", json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int baru = json.getInt(TAG_TERBARU);

            terbaru = baru;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return terbaru;
    }

    public ArrayList<ArrayList<Object>> LoadLogs2Array(String to, String info, String ipadd) {
        // Building Parameters

        String url_get_log = "http://" + ipadd + "/restoran_android/ambil_log.php";

        ArrayList<ArrayList<Object>> logListArray = new ArrayList<ArrayList<Object>>();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("to", to));
        params.add(new BasicNameValuePair("info", info));
        JSONObject json = jParser.makeHttpRequest(url_get_log, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("All Logs: ", json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                logs = json.getJSONArray(TAG_LOGS);

                // looping through All Products

                for (int i = 0; i < logs.length(); i++) {
                    JSONObject c = logs.getJSONObject(i);

                    // Storing each json item in variable


                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(c.getString(ROW_ID));
                    dataList.add(c.getString(ROW_WAKTU));
                    dataList.add(c.getString(ROW_USERID));
                    dataList.add(c.getString(ROW_KEPADA));
                    dataList.add(c.getString(ROW_MSG));
                    dataList.add(c.getString(ROW_INFO));

                    logListArray.add(dataList);

                }
            } else {
                // no products found
                // Launch Add New product Activity
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return logListArray;
    }

    public ArrayList<ArrayList<Object>> LoadCustomLog(String sql, String ipadd) {
        // Building Parameters

        String url_get_logcust = "http://" + ipadd + "/restoran_android/select_custom_log.php";

        ArrayList<ArrayList<Object>> logListArray = new ArrayList<ArrayList<Object>>();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sql", sql));
        JSONObject json = jParser.makeHttpRequest(url_get_logcust, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("All custom Logs: ", json.toString());


        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                logs = json.getJSONArray(TAG_LOGS);

                // looping through All Products

                for (int i = 0; i < logs.length(); i++) {
                    JSONObject c = logs.getJSONObject(i);

                    // Storing each json item in variable


                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(c.getString(ROW_ID));
                    dataList.add(c.getString(ROW_WAKTU));
                    dataList.add(c.getString(ROW_USERID));
                    dataList.add(c.getString(ROW_KEPADA));
                    dataList.add(c.getString(ROW_MSG));
                    dataList.add(c.getString(ROW_INFO));

                    logListArray.add(dataList);

                }
            } else {
                // no products found
                // Launch Add New product Activity
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return logListArray;
    }

    public void addTransit(Long noTrx, String userid, Long meja, Long seat, String code, String namaMenu, Long harga, Long jumlah, Long total, Boolean selesai, String info, String bungkus, Long target, String kategori, String ipadd) {
        //Toast.makeText(getBaseContext(),  userid, Toast.LENGTH_LONG).show();

        String url_add_transit = "http://" + ipadd + "/restoran_android/add_transit.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("noTrx", String.valueOf(noTrx)));
        params.add(new BasicNameValuePair("userid", userid));
        params.add(new BasicNameValuePair("meja", String.valueOf(meja)));
        params.add(new BasicNameValuePair("seat", String.valueOf(seat)));
        params.add(new BasicNameValuePair("code", code));
        params.add(new BasicNameValuePair("namaMenu", namaMenu));
        params.add(new BasicNameValuePair("harga", String.valueOf(harga)));
        params.add(new BasicNameValuePair("jumlah", String.valueOf(jumlah)));
        params.add(new BasicNameValuePair("total", String.valueOf(total)));
        params.add(new BasicNameValuePair("selesai", String.valueOf(selesai)));
        params.add(new BasicNameValuePair("info", info));
        params.add(new BasicNameValuePair("bungkus", bungkus));
        params.add(new BasicNameValuePair("target", String.valueOf(target)));
        params.add(new BasicNameValuePair("kategori", kategori));

        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jParser.makeHttpRequest(url_add_transit, "POST", params);

        // check log cat from response
        Log.d("Create Response", json.toString());

        // check for success tag
        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {

                params.clear();

            } else {
                //finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public ArrayList<ArrayList<Object>> LoadTransit(String meja, String userid, String selesai, String info, String info2, String info3, String ipadd) {
        // Building Parameters

        String url_get_transit = "http://" + ipadd + "/restoran_android/ambil_transit.php";

        ArrayList<ArrayList<Object>> transitListArray = new ArrayList<ArrayList<Object>>();
        //String closed = "false";
        //String to = "nizam";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("meja", meja));
        params.add(new BasicNameValuePair("userid", userid));
        params.add(new BasicNameValuePair("selesai", selesai));
        params.add(new BasicNameValuePair("info", info));
        params.add(new BasicNameValuePair("info2", info2));
        params.add(new BasicNameValuePair("info3", info3));
        JSONObject json = jParser.makeHttpRequest(url_get_transit, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("All transit: ", json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                transit = json.getJSONArray(TAG_TRANSIT);

                // looping through All Products

                for (int i = 0; i < transit.length(); i++) {
                    JSONObject c = transit.getJSONObject(i);

                    // Storing each json item in variable


                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(c.getString(ROW_ID));
                    dataList.add(c.getString(ROW_NOTRX));
                    dataList.add(c.getString(ROW_TGLTRX));
                    dataList.add(c.getString(ROW_USERID));
                    dataList.add(c.getString(ROW_MEJA));
                    dataList.add(c.getString(ROW_SEAT));
                    dataList.add(c.getString(ROW_CODE));
                    dataList.add(c.getString(ROW_NAMAMENU));
                    dataList.add(c.getString(ROW_HARGA));
                    dataList.add(c.getString(ROW_JUMLAH));
                    dataList.add(c.getString(ROW_TOTAL));
                    dataList.add(c.getString(ROW_SELESAI));
                    dataList.add(c.getString(ROW_INFO));
                    dataList.add(c.getString("bungkus"));

                    transitListArray.add(dataList);

                }
            } else {
                // no products found
                // Launch Add New product Activity
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return transitListArray;
    }

    public ArrayList<ArrayList<Object>> LoadTransitnoTrx(String noTrx, String meja, String selesai, String ipadd) {
        // Building Parameters

        String url_get_transitaktif = "http://" + ipadd + "/restoran_android/ambil_transit_aktif.php";

        ArrayList<ArrayList<Object>> transitListArray = new ArrayList<ArrayList<Object>>();
        //String closed = "false";
        //String to = "nizam";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("noTrx", noTrx));
        params.add(new BasicNameValuePair("meja", meja));
        params.add(new BasicNameValuePair("selesai", selesai));
        JSONObject json = jParser.makeHttpRequest(url_get_transitaktif, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("All transit noTrx : " + noTrx, json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                transit = json.getJSONArray(TAG_TRANSIT);

                // looping through All Products

                for (int i = 0; i < transit.length(); i++) {
                    JSONObject c = transit.getJSONObject(i);

                    // Storing each json item in variable


                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(c.getString(ROW_ID));
                    dataList.add(c.getString(ROW_NOTRX));
                    dataList.add(c.getString(ROW_TGLTRX));
                    dataList.add(c.getString(ROW_USERID));
                    dataList.add(c.getString(ROW_MEJA));
                    dataList.add(c.getString(ROW_SEAT));
                    dataList.add(c.getString(ROW_CODE));
                    dataList.add(c.getString(ROW_NAMAMENU));
                    dataList.add(c.getString(ROW_HARGA));
                    dataList.add(c.getString(ROW_JUMLAH));
                    dataList.add(c.getString(ROW_TOTAL));
                    dataList.add(c.getString(ROW_SELESAI));
                    dataList.add(c.getString(ROW_INFO));
                    dataList.add(c.getString("bungkus"));

                    transitListArray.add(dataList);

                }
            } else {
                // no products found
                // Launch Add New product Activity
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return transitListArray;
    }

    public ArrayList<ArrayList<Object>> LoadTransitDapur(Boolean selesai, String info, String target, String ipadd) {
        // Building Parameters

        String url_get_transitdp = "http://" + ipadd + "/restoran_android/ambil_transit_dapur.php";

        ArrayList<ArrayList<Object>> transitListArray = new ArrayList<ArrayList<Object>>();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("selesai", String.valueOf(selesai)));
        params.add(new BasicNameValuePair("info", info));
        params.add(new BasicNameValuePair("target", target));
        JSONObject json = jParser.makeHttpRequest(url_get_transitdp, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("All transit dapur: ", json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                transit = json.getJSONArray(TAG_TRANSIT);

                // looping through All Products

                for (int i = 0; i < transit.length(); i++) {
                    JSONObject c = transit.getJSONObject(i);

                    // Storing each json item in variable


                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(c.getString(ROW_ID));
                    dataList.add(c.getString(ROW_NOTRX));
                    dataList.add(c.getString(ROW_TGLTRX));
                    dataList.add(c.getString(ROW_USERID));
                    dataList.add(c.getString(ROW_MEJA));
                    dataList.add(c.getString(ROW_SEAT));
                    dataList.add(c.getString(ROW_CODE));
                    dataList.add(c.getString(ROW_NAMAMENU));
                    dataList.add(c.getString(ROW_HARGA));
                    dataList.add(c.getString(ROW_JUMLAH));
                    dataList.add(c.getString(ROW_TOTAL));
                    dataList.add(c.getString(ROW_SELESAI));
                    dataList.add(c.getString(ROW_INFO));
                    dataList.add(c.getString("bungkus"));
                    dataList.add(c.getString("urutan"));
                    dataList.add(c.getString("target"));
                    dataList.add(c.getString("kategori"));

                    transitListArray.add(dataList);

                }
            } else {
                // no products found
                // Launch Add New product Activity
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return transitListArray;
    }


    public ArrayList<Object> LoadTransitDetail(String tid, String ipadd) {
        // Building Parameters

        String url_get_transit_detail = "http://" + ipadd + "/restoran_android/ambil_transit_detail.php";

        ArrayList<Object> transitListArray = new ArrayList<Object>();
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        //params.add(new BasicNameValuePair("noTrx", String.valueOf(noTrx)));
        params.add(new BasicNameValuePair("tid", tid));
        JSONObject json = jParser.makeHttpRequest(url_get_transit_detail, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("transit detail : ", json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                transit = json.getJSONArray(TAG_TRANSIT_DETAIL);

                // looping through All Products

                for (int i = 0; i < transit.length(); i++) {
                    JSONObject c = transit.getJSONObject(i);

                    // Storing each json item in variable


                    transitListArray.add(c.getString(ROW_ID));
                    transitListArray.add(c.getString(ROW_NOTRX));
                    transitListArray.add(c.getString(ROW_TGLTRX));
                    transitListArray.add(c.getString(ROW_USERID));
                    transitListArray.add(c.getString(ROW_MEJA));
                    transitListArray.add(c.getString(ROW_SEAT));
                    transitListArray.add(c.getString(ROW_CODE));
                    transitListArray.add(c.getString(ROW_NAMAMENU));
                    transitListArray.add(c.getString(ROW_HARGA));
                    transitListArray.add(c.getString(ROW_JUMLAH));
                    transitListArray.add(c.getString(ROW_TOTAL));
                    transitListArray.add(c.getString(ROW_SELESAI));
                    transitListArray.add(c.getString(ROW_INFO));
                    transitListArray.add(c.getString("bungkus"));
                    transitListArray.add(c.getString("urutan"));
                    transitListArray.add(c.getString("target"));
                    transitListArray.add(c.getString("kategori"));

                }
            } else {
                // no products found
                // Launch Add New product Activity
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return transitListArray;
    }

    public ArrayList<ArrayList<Object>> LoadTransitCustom(String sql, String ipadd) {
        // Building Parameters

        String url_get_transitcust = "http://" + ipadd + "/restoran_android/select_custom_transit.php";

        ArrayList<ArrayList<Object>> transitListArray = new ArrayList<ArrayList<Object>>();
        //String closed = "false";
        //String to = "nizam";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sql", sql));
        JSONObject json = jParser.makeHttpRequest(url_get_transitcust, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("All transit custom : ", json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                transit = json.getJSONArray(TAG_TRANSIT);

                // looping through All Products

                for (int i = 0; i < transit.length(); i++) {
                    JSONObject c = transit.getJSONObject(i);

                    // Storing each json item in variable


                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(c.getString(ROW_ID));
                    dataList.add(c.getString(ROW_NOTRX));
                    dataList.add(c.getString(ROW_TGLTRX));
                    dataList.add(c.getString(ROW_USERID));
                    dataList.add(c.getString(ROW_MEJA));
                    dataList.add(c.getString(ROW_SEAT));
                    dataList.add(c.getString(ROW_CODE));
                    dataList.add(c.getString(ROW_NAMAMENU));
                    dataList.add(c.getString(ROW_HARGA));
                    dataList.add(c.getString(ROW_JUMLAH));
                    dataList.add(c.getString(ROW_TOTAL));
                    dataList.add(c.getString(ROW_SELESAI));
                    dataList.add(c.getString(ROW_INFO));
                    dataList.add(c.getString("bungkus"));
                    dataList.add(c.getString("urutan"));
                    dataList.add(c.getString("target"));
                    dataList.add(c.getString(ROW_KATEGORI));

                    transitListArray.add(dataList);

                }
            } else {
                // no products found
                // Launch Add New product Activity
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return transitListArray;
    }

    public ArrayList<ArrayList<Object>> CariTotalItem(String noTrx, String meja, String ipadd) {
        // Building Parameters

        String url_get_totalitem = "http://" + ipadd + "/restoran_android/cari_total_item.php";

        ArrayList<ArrayList<Object>> totalArray = new ArrayList<ArrayList<Object>>();
        //String closed = "false";
        //String to = "nizam";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("noTrx", noTrx));
        params.add(new BasicNameValuePair("meja", meja));
        JSONObject json = jParser.makeHttpRequest(url_get_totalitem, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("Total Item : ", json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                totalitem = json.getJSONArray("sumary");

                // looping through All Products

                for (int i = 0; i < totalitem.length(); i++) {
                    JSONObject c = totalitem.getJSONObject(i);

                    // Storing each json item in variable

                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(c.getString("sumtotal"));
                    dataList.add(c.getString("sumjumlah"));

                    totalArray.add(dataList);

                }
            } else {
                // no products found
                // Launch Add New product Activity
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return totalArray;
    }


    public void updateTransit(Long _id, Long noTrx, String userid, Long meja, Long seat, String code, String namaMenu, Long harga, Long jumlah, Long total, Boolean selesai, String info, Long bungkus, String ipadd) {
        //Toast.makeText(getBaseContext(),  userid, Toast.LENGTH_LONG).show();

        String url_update_transit = "http://" + ipadd + "/restoran_android/update_transit.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tid", String.valueOf(_id)));
        params.add(new BasicNameValuePair("noTrx", String.valueOf(noTrx)));
        params.add(new BasicNameValuePair("userid", userid));
        params.add(new BasicNameValuePair("meja", String.valueOf(meja)));
        params.add(new BasicNameValuePair("seat", String.valueOf(seat)));
        params.add(new BasicNameValuePair("code", code));
        params.add(new BasicNameValuePair("namaMenu", namaMenu));
        params.add(new BasicNameValuePair("harga", String.valueOf(harga)));
        params.add(new BasicNameValuePair("jumlah", String.valueOf(jumlah)));
        params.add(new BasicNameValuePair("total", String.valueOf(total)));
        params.add(new BasicNameValuePair("selesai", String.valueOf(selesai)));
        params.add(new BasicNameValuePair("info", info));
        params.add(new BasicNameValuePair("bungkus", String.valueOf(bungkus)));


        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jParser.makeHttpRequest(url_update_transit, "POST", params);

        // check log cat from response
        Log.d("Create Response", json.toString());

        // check for success tag
        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {

                params.clear();

            } else {
                //finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void updateTransitInfo(Long _id, String info, String ipadd) {
        //Toast.makeText(getBaseContext(),  userid, Toast.LENGTH_LONG).show();

        String url_update_transit_info = "http://" + ipadd + "/restoran_android/update_transit_info.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tid", String.valueOf(_id)));
        params.add(new BasicNameValuePair("info", info));


        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jParser.makeHttpRequest(url_update_transit_info, "POST", params);

        // check log cat from response
        Log.d("update transit info response : ", json.toString());

        // check for success tag
        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {

                params.clear();

            } else {
                //finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void convertTransitInfo(String info, String infochg, String target, String ipadd) {
        //Toast.makeText(getBaseContext(),  userid, Toast.LENGTH_LONG).show();

        String url_convert_transit_info = "http://" + ipadd + "/restoran_android/convert_transit_info.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("info", info));
        params.add(new BasicNameValuePair("infochg", infochg));
        params.add(new BasicNameValuePair("target", target));


        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jParser.makeHttpRequest(url_convert_transit_info, "POST", params);

        // check log cat from response
        Log.d("convert reponse", json.toString());

        // check for success tag
        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {

                params.clear();

            } else {
                //finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void convertTransitInfobyTrx(String noTrx, String info, String infochg, String ipadd) {
        //Toast.makeText(getBaseContext(),  userid, Toast.LENGTH_LONG).show();

        String url_convert_transit_info = "http://" + ipadd + "/restoran_android/convert_transit_infobytrx.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("noTrx", noTrx));
        params.add(new BasicNameValuePair("info", info));
        params.add(new BasicNameValuePair("infochg", infochg));


        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jParser.makeHttpRequest(url_convert_transit_info, "POST", params);

        // check log cat from response
        Log.d("convert reponse", json.toString());

        // check for success tag
        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {

                params.clear();

            } else {
                //finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void updateJumlahTransit(Long _id, Long jumlah, Long total, String bungkus, String ipadd) {
        //Toast.makeText(getBaseContext(),  userid, Toast.LENGTH_LONG).show();

        String url_update_transit = "http://" + ipadd + "/restoran_android/update_jumlah_transit.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tid", String.valueOf(_id)));
        params.add(new BasicNameValuePair("jumlah", String.valueOf(jumlah)));
        params.add(new BasicNameValuePair("total", String.valueOf(total)));
        params.add(new BasicNameValuePair("bungkus", bungkus));


        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jParser.makeHttpRequest(url_update_transit, "POST", params);

        // check log cat from response
        Log.d("Create Response", json.toString());

        // check for success tag
        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {

                params.clear();

            } else {
                //finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void DeleteTransit(Long noTrx, String ipadd) {

        String url_delete_transit = "http://" + ipadd + "/restoran_android/delete_transit.php";
        int success;
        try {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("noTrx", String.valueOf(noTrx)));

            // getting product details by making HTTP request
            JSONObject json = jParser.makeHttpRequest(url_delete_transit, "POST", params);

            // check your log for json response
            Log.d("Delete transit", json.toString());

            // json success tag
            success = json.getInt(TAG_SUCCESS);
            if (success == 1) {
                params.clear();
                // product successfully deleted
                // notify previous activity by sending code 100

                // send result code 100 to notify about product deletion

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void DeleteTransitId(Long mid, String ipadd) {

        String url_delete_transitid = "http://" + ipadd + "/restoran_android/delete_transit_byid.php";
        int success;
        try {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("mid", String.valueOf(mid)));

            // getting product details by making HTTP request
            JSONObject json = jParser.makeHttpRequest(url_delete_transitid, "POST", params);

            // check your log for json response
            Log.d("Delete menu ", json.toString());

            // json success tag
            success = json.getInt(TAG_SUCCESS);
            if (success == 1) {
                params.clear();
                // product successfully deleted
                // notify previous activity by sending code 100

                // send result code 100 to notify about product deletion

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void updateMeja(Long meja, Long seat, String userid, Boolean layan, Boolean pesan, Long siap, Boolean panggil, Long noTrx, String ipadd) {
        //Toast.makeText(getBaseContext(),  userid, Toast.LENGTH_LONG).show();

        String url_update_meja = "http://" + ipadd + "/restoran_android/update_meja.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("meja", String.valueOf(meja)));
        params.add(new BasicNameValuePair("seat", String.valueOf(seat)));
        params.add(new BasicNameValuePair("userid", userid));
        params.add(new BasicNameValuePair("layan", String.valueOf(layan)));
        params.add(new BasicNameValuePair("pesan", String.valueOf(pesan)));
        params.add(new BasicNameValuePair("siap", String.valueOf(siap)));
        params.add(new BasicNameValuePair("panggil", String.valueOf(panggil)));
        params.add(new BasicNameValuePair("noTrx", String.valueOf(noTrx)));


        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jParser.makeHttpRequest(url_update_meja, "POST", params);

        // check log cat from response
        Log.d("Create Response", json.toString());

        // check for success tag
        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {

                params.clear();

            } else {
                //finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<ArrayList<Object>> LoadAllMeja(String ipadd) {
        // Building Parameters

        String url_get_meja = "http://" + ipadd + "/restoran_android/ambil_semua_meja.php";

        ArrayList<ArrayList<Object>> mejaListArray = new ArrayList<ArrayList<Object>>();
        //String closed = "false";
        //String to = "nizam";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        JSONObject json = jParser.makeHttpRequest(url_get_meja, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("All meja: ", json.toString());

        //Toast.makeText(DatabaseOLManager.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                mejaarr = json.getJSONArray(TAG_MEJA);

                // looping through All Products

                for (int i = 0; i < mejaarr.length(); i++) {
                    JSONObject c = mejaarr.getJSONObject(i);

                    // Storing each json item in variable


                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(c.getLong(ROW_MEJA));
                    dataList.add(c.getLong(ROW_SEAT));
                    dataList.add(c.getString(ROW_USERID));
                    dataList.add(c.getLong(ROW_LAYAN));
                    dataList.add(c.getLong(ROW_PESAN));
                    dataList.add(c.getLong(ROW_SIAP));
                    dataList.add(c.getLong(ROW_PANGGIL));
                    dataList.add(c.getLong(ROW_NOTRX));

                    mejaListArray.add(dataList);

                }
            } else {
                // no products found
                // Launch Add New product Activity
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mejaListArray;
    }

    public ArrayList<ArrayList<Object>> LoadMeja(Long meja, String ipadd) {
        // Building Parameters

        String url_get_meja = "http://" + ipadd + "/restoran_android/ambil_meja.php";

        ArrayList<ArrayList<Object>> mejaListArray = new ArrayList<ArrayList<Object>>();
        //String closed = "false";
        //String to = "nizam";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("meja", String.valueOf(meja)));
        JSONObject json = jParser.makeHttpRequest(url_get_meja, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("meja  " + meja + " : ", json.toString());

        //Toast.makeText(DatabaseOLManager.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                mejaarr = json.getJSONArray(TAG_MEJA);

                // looping through All Products

                for (int i = 0; i < mejaarr.length(); i++) {
                    JSONObject c = mejaarr.getJSONObject(i);

                    // Storing each json item in variable


                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(c.getLong(ROW_MEJA));
                    dataList.add(c.getLong(ROW_SEAT));
                    dataList.add(c.getString(ROW_USERID));
                    dataList.add(c.getLong(ROW_LAYAN));
                    dataList.add(c.getLong(ROW_PESAN));
                    dataList.add(c.getLong(ROW_SIAP));
                    dataList.add(c.getLong(ROW_PANGGIL));
                    dataList.add(c.getLong(ROW_NOTRX));

                    mejaListArray.add(dataList);

                }
            } else {
                // no products found
                // Launch Add New product Activity
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mejaListArray;
    }

    public ArrayList<Object> LoadUser(String userid, String password, String ipadd) {
        // Building Parameters

        String url_get_user = "http://" + ipadd + "/restoran_android/ambil_user.php";

        ArrayList<Object> userListArray = new ArrayList<Object>();

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("userid", userid));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jParser.makeHttpRequest(url_get_user, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("user detail : ", json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                user = json.getJSONArray("user");

                // looping through All Products

                for (int i = 0; i < user.length(); i++) {
                    JSONObject c = user.getJSONObject(i);

                    // Storing each json item in variable


                    userListArray.add(c.getString("userid"));
                    userListArray.add(c.getString("password"));
                    userListArray.add(c.getString("level"));
                    userListArray.add(c.getString("status"));

                }
            } else {
                // no products found
                // Launch Add New product Activity
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userListArray;
    }

    public int CekUser(String userid, String password, String ipadd) {
        // Building Parameters
        int success = 0;
        String url_get_user = "http://" + ipadd + "/restoran_android/ambil_user.php";


        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("userid", userid));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jParser.makeHttpRequest(url_get_user, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("user detail : ", json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            success = json.getInt(TAG_SUCCESS);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return success;
    }

    public void updateUser(String userid, String status, String ipadd) {
        //Toast.makeText(getBaseContext(),  userid, Toast.LENGTH_LONG).show();

        String url_update_user = "http://" + ipadd + "/restoran_android/update_user.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userid", userid));
        params.add(new BasicNameValuePair("status", status));


        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jParser.makeHttpRequest(url_update_user, "POST", params);

        // check log cat from response
        Log.d("User db Response", json.toString());

        // check for success tag
        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {

                params.clear();

            } else {
                //finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void InsertCustomSQL(String sql, String ipadd) {
        //Toast.makeText(getBaseContext(),  userid, Toast.LENGTH_LONG).show();

        String url_insert_custom = "http://" + ipadd + "/remote/insert_custom.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sql", sql));


        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jParser.makeHttpRequest(url_insert_custom, "POST", params);

        // check log cat from response
        Log.d("Custom response : ", json.toString());

        // check for success tag
        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {

                params.clear();

            } else {
                params.clear();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<ArrayList<Object>> LoadCustomOrder(String sql, String ipadd) {
        // Building Parameters

        String url_get_ordercust = "http://" + ipadd + "/remote/select_custom_order.php";

        ArrayList<ArrayList<Object>> OrderListArray = new ArrayList<ArrayList<Object>>();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sql", sql));
        JSONObject json = jParser.makeHttpRequest(url_get_ordercust, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("All custom Order: ", json.toString());


        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                order = json.getJSONArray("perintah");

                // looping through All Products

                for (int i = 0; i < order.length(); i++) {
                    JSONObject c = order.getJSONObject(i);

                    // Storing each json item in variable


                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(c.getString("id"));
                    dataList.add(c.getString("waktu"));
                    dataList.add(c.getString("dari"));
                    dataList.add(c.getString("kepada"));
                    dataList.add(c.getString("pesan"));
                    dataList.add(c.getString("status"));

                    OrderListArray.add(dataList);

                }
            } else {
                // no products found
                // Launch Add New product Activity
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return OrderListArray;
    }

    public ArrayList<ArrayList<Object>> LoadCustomUser(String sql, String ipadd) {
        // Building Parameters

        String url_get_ordercust = "http://" + ipadd + "/remote/select_custom_user.php";

        ArrayList<ArrayList<Object>> UserListArray = new ArrayList<ArrayList<Object>>();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sql", sql));
        JSONObject json = jParser.makeHttpRequest(url_get_ordercust, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("All custom Order: ", json.toString());


        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                order = json.getJSONArray("user");

                // looping through All Products

                for (int i = 0; i < order.length(); i++) {
                    JSONObject c = order.getJSONObject(i);

                    // Storing each json item in variable


                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(c.getString("created"));
                    dataList.add(c.getString("userid"));
                    dataList.add(c.getString("password"));
                    dataList.add(c.getString("status"));
                    dataList.add(c.getString("lastlogin"));

                    UserListArray.add(dataList);

                }
            } else {
                // no products found
                // Launch Add New product Activity
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return UserListArray;
    }


    public ArrayList<ArrayList<Object>> LoadPaket(String pid, String ipadd) {
        // Building Parameters

        String url_get_paket = "http://" + ipadd + "/restoran_android/ambil_paket.php";

        ArrayList<ArrayList<Object>> paketListArray = new ArrayList<ArrayList<Object>>();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("pid", pid));
        JSONObject json = jParser.makeHttpRequest(url_get_paket, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("All paket : ", json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                paket = json.getJSONArray("paket");
                //Toast.makeText(Context, logs.length(), Toast.LENGTH_LONG).show();
                // looping through All Products

                for (int i = 0; i < paket.length(); i++) {
                    JSONObject c = paket.getJSONObject(i);

                    // Storing each json item in variable

                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(c.getString(ROW_ID));
                    dataList.add(c.getString(ROW_CODE));
                    dataList.add(c.getString(ROW_JUMLAH));

                    paketListArray.add(dataList);

                }
            } else {
                // no products found

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return paketListArray;
    }

    public ArrayList<ArrayList<Object>> LoadKategori(String aktif, String ipadd) {
        // Building Parameters

        String url_get_kategori = "http://" + ipadd + "/restoran_android/ambil_kategori_aktif.php";

        ArrayList<ArrayList<Object>> kategoriListArray = new ArrayList<ArrayList<Object>>();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("aktif", aktif));
        JSONObject json = jParser.makeHttpRequest(url_get_kategori, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("Kategori : ", json.toString());

        //Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_SHORT).show();

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                kategori = json.getJSONArray("kategori");

                // looping through All Products

                for (int i = 0; i < kategori.length(); i++) {
                    JSONObject c = kategori.getJSONObject(i);

                    // Storing each json item in variable
                    ArrayList<Object> katList = new ArrayList<Object>();
                    katList.add(c.getString("id"));
                    katList.add(c.getString("kategori"));
                    katList.add(c.getString("desc"));
                    katList.add(c.getString("aktif"));

                    kategoriListArray.add(katList);

                }
            } else {
                // no products found
                // Launch Add New product Activity
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return kategoriListArray;
    }
}
