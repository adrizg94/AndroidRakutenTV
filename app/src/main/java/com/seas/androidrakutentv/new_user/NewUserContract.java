package com.seas.androidrakutentv.new_user;

import com.seas.androidrakutentv.beans.User;

public interface NewUserContract {

    interface View {
        void successNewUser();
        void failureNewUser(String error);
    }

    interface Presenter {
        void addUser(User user);

    }

    interface Model {

        interface OnNewUserListener {
            void onFinished();
            void onFailure(String error);
        }

        void addUserService(OnNewUserListener onNewUserListener, User user);

    }
}
