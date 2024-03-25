package com.seas.androidrakutentv.utils;

import android.util.Log;
import com.seas.androidrakutentv.beans.Film;
import com.seas.androidrakutentv.beans.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Statics {

    public static String url = "http://192.168.1.45:8080/rakutentv/";
    public static String urlImages = "http://192.168.1.45:8080/images/";
    public static String userName = "";
    public static Film film = new Film();

    public static User getUserJSON(JSONArray userJSON) {
        User userLogin = null;
        if (userJSON!=null) {
            try {
                userLogin = new User();
                userLogin.setId(userJSON.getJSONObject(0).getInt("id"));
                userLogin.setNombre(userJSON.getJSONObject(0).getString("nombre"));
                userLogin.setApellido(userJSON.getJSONObject(0).getString("apellido"));
                userLogin.setEmail(userJSON.getJSONObject(0).getString("email"));
                userLogin.setPassword(userJSON.getJSONObject(0).getString("password"));
                userLogin.setRegistro(userJSON.getJSONObject(0).getString("registro"));
            } catch (JSONException e) {
                Log.e("log_tag", "Invalid JSON " + e);
            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection " + e);
            }
        }
        return userLogin;
    }

    public static JSONObject userJSONBuilder(User user) {
        StringBuilder buffer = new StringBuilder();
        JSONObject userJSON = null;
        try {
            buffer.append("{\"id\":0,\"nombre\":\"" + user.getNombre()
                    + "\",\"apellido\":\"" + user.getApellido()
                    + "\",\"email\":\"" + user.getEmail()
                    + "\",\"password\":\"" + user.getPassword()
                    + "\",\"registro\":\"" + user.getRegistro() + "\"}");
            userJSON = new JSONObject(buffer.toString());
        } catch (JSONException e) {
            Log.e("log_tag", "Unable to convert in JSONOBject " + e);
        } catch (Exception e) {
            Log.e("log_tag", "Error " + e);
        }
        return userJSON;
//        return buffer.toString();
    }

    public static ArrayList<Film> getFilmsArrayJSON(JSONArray listFilmsJSON) {
        ArrayList<Film> listFilms = null;
        if (listFilmsJSON!=null) {
            try {
                listFilms = new ArrayList<>();
                for (int i=0; i<listFilmsJSON.length();i++) {
                    Film film = new Film();
                    film.setId(listFilmsJSON.getJSONObject(i).getInt("id"));
                    film.setTitulo(listFilmsJSON.getJSONObject(i).getString("titulo"));
                    film.setPrecio(listFilmsJSON.getJSONObject(i).getDouble("precio"));
                    film.setDuracion(listFilmsJSON.getJSONObject(i).getInt("duracion"));
                    film.setTrailer(listFilmsJSON.getJSONObject(i).getString("trailer"));
                    film.setSinopsis(listFilmsJSON.getJSONObject(i).getString("sinopsis"));
                    film.setVotos(listFilmsJSON.getJSONObject(i).getInt("votos"));
                    film.setPuntuacion(listFilmsJSON.getJSONObject(i).getInt("puntuacion"));
                    film.setEstreno(listFilmsJSON.getJSONObject(i).getString("estreno"));
                    film.setUrl(listFilmsJSON.getJSONObject(i).getString("url"));
                    listFilms.add(film);
                }
            } catch (JSONException e) {
                Log.e("log_tag", "Invalid JSON " + e);
            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection " + e);
            }
        }
        return listFilms;
    }



}
