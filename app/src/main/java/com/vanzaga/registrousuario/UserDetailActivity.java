package com.vanzaga.registrousuario;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserDetailActivity extends AppCompatActivity {

    private TextView textViewUsername;
    private TextView textViewName;
    private TextView textViewPassword;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        textViewUsername = findViewById(R.id.textViewUsername);
        textViewName = findViewById(R.id.textViewName);
        textViewPassword = findViewById(R.id.textViewPassword);

        user = (User) getIntent().getSerializableExtra("user");

        textViewUsername.setText(user.getUsername());
        textViewName.setText(user.getName());
        textViewPassword.setText(user.getPassword());
    }

    public void volverAListaUsuarios(View view) {
        Intent intent = new Intent(this, UserListActivity.class);
        startActivity(intent);
        finish();
    }
}