package com.seas.androidrakutentv.list_films;

import com.seas.androidrakutentv.beans.Film;

import java.util.ArrayList;

public class ListFilmsPresenter implements ListFilmsContract.Presenter {

    private ListFilmsContract.View view;
    private ListFilmsContract.Model model;

    public ListFilmsPresenter(ListFilmsContract.View view) {
        this.view = view;
        model = new ListFilmsModel();
    }

    @Override
    public void getListFilms() {
        model.getListFilmsService(new ListFilmsContract.Model.OnListFilmsListener() {
            @Override
            public void onFinished(ArrayList<Film> listFilm) {
                view.successListFilms(listFilm);
            }

            @Override
            public void onFailure(String error) {
                view.failureListFilms(error);
            }
        });
    }
}
