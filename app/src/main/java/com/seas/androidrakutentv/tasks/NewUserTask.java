package com.seas.androidrakutentv.tasks;

import android.os.AsyncTask;
import android.util.Log;
import com.seas.androidrakutentv.beans.User;
import com.seas.androidrakutentv.new_user.NewUserContract;
import com.seas.androidrakutentv.utils.Conn;
import com.seas.androidrakutentv.utils.Statics;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class NewUserTask extends AsyncTask<String, Integer, Boolean> {

    private NewUserContract.Model.OnNewUserListener onNewUserListener;
    private JSONObject newUser;
    private User user;

    public NewUserTask(NewUserContract.Model.OnNewUserListener onNewUserListener, JSONObject newUser) {
        this.onNewUserListener = onNewUserListener;
        this.newUser = newUser;
    }
    @Override
    protected Boolean doInBackground(String... params) {
        String url = params[0];
        try {
            Conn conn = new Conn();
            JSONArray listJSON = conn.postData(newUser, url);
            user = Statics.getUserJSON(listJSON);
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e);
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        try {
            if(aBoolean && user!=null) {
                onNewUserListener.onFinished();
            } else {
                onNewUserListener.onFailure("Error al añadir a la base de datos");
            }
        } catch (Exception e) {
            onNewUserListener.onFailure("Error in http connection " + e);
        }
    }
}
