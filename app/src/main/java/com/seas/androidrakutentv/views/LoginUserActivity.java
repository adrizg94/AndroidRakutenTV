package com.seas.androidrakutentv.views;

import android.content.Intent;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.seas.androidrakutentv.R;
import com.seas.androidrakutentv.beans.User;
import com.seas.androidrakutentv.login_user.LoginUserContract;
import com.seas.androidrakutentv.login_user.LoginUserPresenter;
import com.seas.androidrakutentv.utils.Statics;

//MainActivity
public class LoginUserActivity extends AppCompatActivity implements LoginUserContract.View {

    private EditText edtUser;
    private EditText edtPass;
    private Button btnLogin;
    private TextView txtNewUser;
    private LoginUserPresenter presenter;
    private User user;

    public LoginUserActivity() {
        user = new User();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        initComponents();
        presenter = new LoginUserPresenter(this);

        btnLogin.setOnClickListener(v -> {
            initUser();
            presenter.getUser(user);
        });

        txtNewUser.setOnClickListener(v -> {
            Intent viewNewUser = new Intent(getBaseContext(), NewUserActivity.class);
            startActivity(viewNewUser);
        });

    }

    public void initComponents() {
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
        txtNewUser = findViewById(R.id.textNewUser);
        txtNewUser.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void initUser() {
        user.setEmail(edtUser.getText().toString());
        user.setPassword(edtPass.getText().toString());
    }


    @Override
    public void successLoginUser(User user) {
        Statics.userName = user.getNombre();
        Intent vistaPeliculas = new Intent(getBaseContext(), ListFilmsActivity.class);
        startActivity(vistaPeliculas);
    }

    @Override
    public void failureLoginUser(String error) {
        Toast.makeText(LoginUserActivity.this, error, Toast.LENGTH_SHORT).show();
    }
}