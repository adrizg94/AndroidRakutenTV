package com.seas.androidrakutentv.utils;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Conn {

    private HttpURLConnection con = null;
    private InputStream is = null;
    private InputStreamReader isr = null;
    private BufferedReader reader = null;

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

    public JSONArray getJSON() {
        JSONArray listJSON = null;
        try {
            isr = new InputStreamReader(is, StandardCharsets.ISO_8859_1);
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
