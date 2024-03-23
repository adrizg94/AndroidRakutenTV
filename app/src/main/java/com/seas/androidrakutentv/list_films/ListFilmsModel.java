package com.seas.androidrakutentv.list_films;

import com.seas.androidrakutentv.tasks.ListFilmsTask;
import com.seas.androidrakutentv.utils.Statics;

public class ListFilmsModel implements ListFilmsContract.Model {

    private ListFilmsTask task;

    @Override
    public void getListFilmsService(OnListFilmsListener onListFilmsListener) {
        String url = Statics.url + "peliculas";
        task = new ListFilmsTask(onListFilmsListener);
        task.execute(url);
    }
}
