package com.seas.androidrakutentv.tasks;

import android.os.AsyncTask;
import android.util.Log;
import com.seas.androidrakutentv.beans.Film;
import com.seas.androidrakutentv.beans.User;
import com.seas.androidrakutentv.login_user.LoginUserContract;
import com.seas.androidrakutentv.utils.Conn;
import com.seas.androidrakutentv.utils.Statics;
import org.json.JSONArray;

import java.util.ArrayList;

public class LoginUserTask extends AsyncTask<String, Integer, Boolean> {

    private LoginUserContract.Model.OnLoginUserListener onLoginUserListener;
    private User userLogin;

    public LoginUserTask(LoginUserContract.Model.OnLoginUserListener onLoginUserListener) {
        this.onLoginUserListener = onLoginUserListener;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        String url = params[0];
        try {
            Conn conn = new Conn();
            JSONArray listJSON = conn.getData(url);
            userLogin = Statics.getUserJSON(listJSON);
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e);
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        try {
            if(aBoolean && userLogin!=null) {
                onLoginUserListener.onFinished(userLogin);
            } else {
                onLoginUserListener.onFailure("Usuario incorrecto");
            }
        } catch (Exception e) {
            onLoginUserListener.onFailure("Error in http connection " + e);
        }

    }

}
