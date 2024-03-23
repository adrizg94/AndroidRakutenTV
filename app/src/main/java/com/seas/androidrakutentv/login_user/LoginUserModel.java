package com.seas.androidrakutentv.login_user;

import com.seas.androidrakutentv.beans.User;
import com.seas.androidrakutentv.tasks.LoginUserTask;
import com.seas.androidrakutentv.utils.Statics;

public class LoginUserModel implements LoginUserContract.Model {

    private LoginUserTask tarea;
    @Override
    public void getUserService(OnLoginUserListener onLoginUserListener, User user) {

//        HashMap<String, String> params = new HashMap<>();
//        params.put("email", user.getEmail());
//        params.put("password", user.getPassword());

        String url = Statics.url + "login/" + user.getEmail() + "/" + user.getPassword();
        tarea = new LoginUserTask(onLoginUserListener);
        tarea.execute(url);

    }
}
