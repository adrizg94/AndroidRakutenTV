package com.seas.androidrakutentv.views;

import android.graphics.Color;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.seas.androidrakutentv.R;
import com.seas.androidrakutentv.utils.Statics;
import com.squareup.picasso.Picasso;

public class ViewFilmActivity extends AppCompatActivity {

    private ImageView imgFilmView;
    private TextView txtTituloView;
    private TextView txtEstreno;
    private TextView txtDuracion;
    private TextView txtSinopsis;
    private Button btnAlquilar;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_film);

        initComponents();
        setComponents();

        btnAlquilar.setOnClickListener(v -> onBackPressed());
    }

    public void initComponents() {
        imgFilmView = findViewById(R.id.imgFilmView);
        txtTituloView = findViewById(R.id.txtTituloView);
        txtEstreno = findViewById(R.id.txtEstreno);
        txtDuracion = findViewById(R.id.txtDuracion);
        txtSinopsis = findViewById(R.id.txtSinopsis);
        btnAlquilar = findViewById(R.id.btnAlquilar);
        webView = findViewById(R.id.webView);
        webView.setBackgroundColor(Color.TRANSPARENT);

    }

    public void setComponents() {
        Picasso.get().load(Statics.urlImages + Statics.film.getUrl() + ".jpg").into(imgFilmView);
        txtTituloView.setText(Statics.film.getTitulo());
        txtEstreno.setText("Estreno: " + Statics.film.getEstreno());
        txtDuracion.setText("Duración: " + String.valueOf(Statics.film.getDuracion()) + " minutos");
        txtSinopsis.setText(Statics.film.getSinopsis());
        btnAlquilar.setText("Alquilar: " + Statics.film.getPrecio() + "€");
        initWeb();
    }

    public void initWeb() {
//        String video = Statics.film.getTrailer();
//        https://www.youtube.com/embed/vDX_r9MH5Z0?si=j2-o5_6njNRjnb7g
//        https://www.youtube.com/embed/vDX_r9MH5Z0
//        " + Statics.film.getUrl() + "

        String video = "<iframe width=\"100%\" height=\"100%\" src=\"" + Statics.film.getTrailer() + "\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView.loadData(video, "text/html", "utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
    }

}