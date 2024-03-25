package com.seas.androidrakutentv.tasks;

import android.os.AsyncTask;
import android.util.Log;
import com.seas.androidrakutentv.beans.User;
import com.seas.androidrakutentv.new_user.NewUserContract;
import com.seas.androidrakutentv.utils.Conn;
import org.json.JSONObject;

import java.util.HashMap;

public class NewUserTask extends AsyncTask<String, Integer, Boolean> {

    private NewUserContract.Model.OnNewUserListener onNewUserListener;
//    private HashMap<String, String> newUser;
    private JSONObject newUser;

//    public NewUserTask(NewUserContract.Model.OnNewUserListener onNewUserListener, HashMap<String, String> newUser) {
//        this.onNewUserListener = onNewUserListener;
//        this.newUser = newUser;
//    }
    public NewUserTask(NewUserContract.Model.OnNewUserListener onNewUserListener, JSONObject newUser) {
        this.onNewUserListener = onNewUserListener;
        this.newUser = newUser;
    }
    @Override
    protected Boolean doInBackground(String... params) {
        String url = params[0];
        try {
            Conn conn = new Conn();
            conn.postConnection(newUser, url);
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e);
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        try {
            if(aBoolean) {
                onNewUserListener.onFinished();
            }
        } catch (Exception e) {
            onNewUserListener.onFailure("Error in http connection " + e);
        }
    }
}
