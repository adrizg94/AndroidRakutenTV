package com.seas.androidrakutentv.list_films;

import com.seas.androidrakutentv.beans.Film;

import java.util.ArrayList;

public interface ListFilmsContract {

    interface View {
        void successListFilms(ArrayList<Film> listFilms);
        void failureListFilms(String error);
    }

    interface Presenter {
        void getListFilms();
    }

    interface Model {

        interface OnListFilmsListener {
            void onFinished(ArrayList<Film> listFilm);
            void onFailure(String error);
        }

        void getListFilmsService(OnListFilmsListener onListFilmsListener);
    }
}
