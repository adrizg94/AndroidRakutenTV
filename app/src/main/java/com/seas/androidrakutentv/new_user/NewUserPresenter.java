package com.seas.androidrakutentv.new_user;

import com.seas.androidrakutentv.beans.User;

public class NewUserPresenter implements NewUserContract.Presenter {

    private NewUserContract.View view;
    private NewUserContract.Model model;

    public NewUserPresenter(NewUserContract.View view) {
        this.view = view;
        this.model = new NewUserModel();
    }

    @Override
    public void addUser(User user) {
        model.addUserService(new NewUserContract.Model.OnNewUserListener() {
            @Override
            public void onFinished() {
                view.successNewUser();
            }

            @Override
            public void onFailure(String error) {
                view.failureNewUser(error);
            }
        }, user);

    }
}
