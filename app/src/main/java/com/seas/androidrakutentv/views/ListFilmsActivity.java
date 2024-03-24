package com.seas.androidrakutentv.views;

import android.content.Intent;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.seas.androidrakutentv.R;
import com.seas.androidrakutentv.adapters.ListFilmsAdapter;
import com.seas.androidrakutentv.beans.Film;
import com.seas.androidrakutentv.list_films.ListFilmsContract;
import com.seas.androidrakutentv.list_films.ListFilmsPresenter;
import com.seas.androidrakutentv.utils.Statics;

import java.util.ArrayList;

public class ListFilmsActivity extends AppCompatActivity implements ListFilmsContract.View {

    private ListFilmsPresenter presenter;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private TextView txtUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_films);

        presenter = new ListFilmsPresenter(this);
        presenter.getListFilms();
        initComponents();

    }

    public void initComponents() {
        txtUserName = findViewById(R.id.txtUserName);
        txtUserName.setText(Statics.userName);
        recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
    }

    public void setViewFilmIntent(Intent intent) {
        startActivity(intent);
    }


    @Override
    public void successListFilms(ArrayList<Film> listFilms) {
        adapter = new ListFilmsAdapter(listFilms, getBaseContext(), this);
        recycler.setAdapter(adapter);
    }

    @Override
    public void failureListFilms(String error) {
        Toast.makeText(ListFilmsActivity.this, error, Toast.LENGTH_SHORT).show();
    }
}