package com.seas.androidrakutentv.new_user;

import com.seas.androidrakutentv.beans.User;
import com.seas.androidrakutentv.tasks.NewUserTask;
import com.seas.androidrakutentv.utils.Statics;
import org.json.JSONObject;

import java.util.HashMap;

public class NewUserModel implements NewUserContract.Model {
    private NewUserTask task;
    private JSONObject newUser;
    @Override
    public void addUserService(OnNewUserListener onNewUserListener, User user) {

        newUser = Statics.userJSONBuilder(user);
        String url = Statics.url + "nuevo";
        task = new NewUserTask(onNewUserListener, newUser);
        task.execute(url);
    }
}
