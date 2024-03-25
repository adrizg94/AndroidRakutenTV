package com.seas.androidrakutentv.utils;

import android.util.Log;
import com.seas.androidrakutentv.beans.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Conn {

    private HttpURLConnection con = null;
    private InputStream is = null;
    private InputStreamReader isr = null;
    private BufferedReader reader = null;

    private OutputStream os = null;
    private OutputStreamWriter writer = null;

    public void getConnection(String page) {
        try {
            URL url = new URL(page);
            con = (HttpURLConnection) url.openConnection();
            is = con.getInputStream();
        } catch (MalformedURLException e) {
            Log.e("log_tag", "Invalid URL " + e);
        } catch (IOException e) {
            Log.e("log_tag", "I/O Error " + e);
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e);
        }
    }



    public void postConnection(JSONObject jsonObject, String page) {
        try {
            URL url = new URL(page);
            con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setInstanceFollowRedirects(false);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(jsonObject.toString());
            writer.flush();
            is = con.getInputStream();
            isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            reader = new BufferedReader(isr);
            StringBuilder buffer = new StringBuilder();
            String readLine;
            while ((readLine = reader.readLine()) != null) {
                buffer.append(readLine).append("\n");
            }












//            BufferedWriter bufwri = new BufferedWriter(writer);
//
//            bufwri.write(String.valueOf(jsonObject));
//
//            writer.flush();
//            bufwri.flush();
//            writer.close();
//            bufwri.close();






//            OutputStream out = con.getOutputStream();
//            os.write(String.valueOf(jsonObject));






        } catch (MalformedURLException e) {
            Log.e("log_tag", "Invalid URL " + e);
        } catch (IOException e) {
            Log.e("log_tag", "I/O Error " + e);
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e);
        }
    }




//    public  void postConnection(JSONObject newUser, String page) {
//        try {
//            URL url = new URL(page);
//            con = (HttpURLConnection) url.openConnection();
//
//
//            con.setRequestMethod("POST");
//            con.setDoOutput(true);
//
//
//
//            writer = new OutputStreamWriter(con.getOutputStream());
//            writer.write(String.valueOf(newUser));
//            writer.flush();
//
//
//        } catch (MalformedURLException e) {
//            Log.e("log_tag", "Invalid URL " + e);
//        } catch (IOException e) {
//            Log.e("log_tag", "I/O Error " + e);
//        } catch (Exception e) {
//            Log.e("log_tag", "Error in http connection " + e);
//        } finally {
//            if (con!=null) {con.disconnect();}
//        }
//    }







    public JSONArray getJSON() {
        JSONArray listJSON = null;
        try {
            isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            reader = new BufferedReader(isr);
            StringBuilder buffer = new StringBuilder();
            String readLine;
            while ((readLine = reader.readLine()) != null) {
                buffer.append(readLine).append("\n");
            }
            if (buffer.indexOf("[") == 0) {
                listJSON = new JSONArray(buffer.toString());
            } else if (buffer.indexOf("{") == 0){
                JSONObject objeto = new JSONObject(buffer.toString());
                listJSON = new JSONArray();
                listJSON.put(objeto);
            }
        } catch (IOException e) {
            Log.e("log_tag", "I/O Error " + e);
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e);
        } finally {
            try {
                if (is!=null) {is.close();}
                if (isr!=null) {isr.close();}
                if (reader!=null) {reader.close();}
                if (con!=null) {con.disconnect();}
            } catch (IOException e) {
                Log.e("log_tag", "I/O Error " + e);
            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection " + e);
            }
        }
        return listJSON;
    }

    public JSONArray getData(String url) {
        getConnection(url);
        return getJSON();
    }

}
