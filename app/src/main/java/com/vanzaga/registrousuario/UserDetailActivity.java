// UserDetailActivity.java
package com.vanzaga.registrousuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UserDetailActivity extends AppCompatActivity {

    // Declaración de variables
    private TextView textViewUsername;
    private TextView textViewName;
    private TextView textViewPassword;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        // Se inicializan las vistas (TextView) en el método onCreate.
        textViewUsername = findViewById(R.id.textViewUsername);
        textViewName = findViewById(R.id.textViewName);
        textViewPassword = findViewById(R.id.textViewPassword);

        // Se obtiene el usuario de la actividad anterior y se muestran los datos en los TextView.
        user = (User) getIntent().getSerializableExtra("user");

        // Se verifica si el usuario no es nulo
        if (user != null) {
            // Se muestran los datos del usuario en los TextView
            textViewUsername.setText(user.getUsername());
            textViewName.setText(user.getName());
            textViewPassword.setText(user.getPassword());
        }
    }

    // Método para volver a la lista de usuarios
    public void volverAListaUsuarios(View view) {

        // Se crea un intent para abrir la actividad UserListActivity
        Intent intent = new Intent(this, UserListActivity.class);

        // Se pasa la lista de usuarios a la actividad UserListActivity
        intent.putExtra("user", user);

        // Se inicia la actividad UserListActivity
        startActivity(intent);
    }
}
