package com.seas.androidrakutentv.views;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.seas.androidrakutentv.R;
import com.seas.androidrakutentv.beans.User;
import com.seas.androidrakutentv.new_user.NewUserContract;
import com.seas.androidrakutentv.new_user.NewUserPresenter;

public class NewUserActivity extends AppCompatActivity implements NewUserContract.View {

    private EditText edtNewNombre;
    private EditText edtNewApellido;
    private EditText edtNewEmail;
    private EditText edtNewPass;
    private Button btnNewUser;
    private User user;
    private NewUserContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        initComponents();

        btnNewUser.setOnClickListener(v -> {
            presenter = new NewUserPresenter(this);
            user = new User();
            user.setNombre(edtNewNombre.getText().toString());
            user.setApellido(edtNewApellido.getText().toString());
            user.setEmail(edtNewEmail.getText().toString());
            user.setPassword(edtNewPass.getText().toString());
            presenter.addUser(user);
        });
    }

    public void initComponents() {
        edtNewNombre = findViewById(R.id.edtNewNombre);
        edtNewApellido = findViewById(R.id.edtNewApellido);
        edtNewEmail = findViewById(R.id.edtNewEmail);
        edtNewPass = findViewById(R.id.edtNewPass);
        btnNewUser = findViewById(R.id.btnNewUser);
    }

    @Override
    public void successNewUser() {
        Toast.makeText(NewUserActivity.this, "Usuario añadido", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void failureNewUser(String error) {
        Toast.makeText(NewUserActivity.this, error, Toast.LENGTH_SHORT).show();
    }
}