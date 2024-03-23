package com.seas.androidrakutentv.login_user;

import com.seas.androidrakutentv.beans.User;

public interface LoginUserContract {

    interface View {
        void successLoginUser(User user);
        void failureLoginUser(String error);
    }

    interface Presenter {
        void getUser(User user);
    }

    interface Model {
        interface OnLoginUserListener {
            void onFinished(User user);
            void onFailure(String error);
        }
        void getUserService(OnLoginUserListener onLoginUserListener, User user);
    }
}
