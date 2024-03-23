package com.seas.androidrakutentv.login_user;

import com.seas.androidrakutentv.beans.User;

public class LoginUserPresenter implements LoginUserContract.Presenter {

    private LoginUserContract.View view;
    private LoginUserContract.Model model;

    public LoginUserPresenter(LoginUserContract.View view) {
        this.view = view;
        model = new LoginUserModel();

    }

    @Override
    public void getUser(User user) {
        model.getUserService(new LoginUserContract.Model.OnLoginUserListener() {
            @Override
            public void onFinished(User user) {
                view.successLoginUser(user);
            }

            @Override
            public void onFailure(String error) {
                view.failureLoginUser(error);
            }
        }, user);
    }
}
