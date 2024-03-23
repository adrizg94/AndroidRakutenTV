package com.seas.androidrakutentv.tasks;

import android.os.AsyncTask;
import android.util.Log;
import com.seas.androidrakutentv.beans.Film;
import com.seas.androidrakutentv.list_films.ListFilmsContract;
import com.seas.androidrakutentv.login_user.LoginUserContract;
import com.seas.androidrakutentv.utils.Conn;
import com.seas.androidrakutentv.utils.Statics;
import org.json.JSONArray;

import java.util.ArrayList;

public class ListFilmsTask extends AsyncTask<String, Integer, Boolean> {

    private ListFilmsContract.Model.OnListFilmsListener onListFilmsListener;
    private ArrayList<Film> listFilms;

    public ListFilmsTask(ListFilmsContract.Model.OnListFilmsListener onListFilmsListener) {
        this.onListFilmsListener = onListFilmsListener;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        String url = params[0];
        try {
            Conn conn = new Conn();
            JSONArray listJSON = conn.getData(url);
            listFilms = Statics.getFilmsArrayJSON(listJSON);
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e);
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        try {
            if (aBoolean) {
                onListFilmsListener.onFinished(listFilms);
            }
        } catch (Exception e) {
            onListFilmsListener.onFailure("Error in http connection " + e);
        }
    }
}
