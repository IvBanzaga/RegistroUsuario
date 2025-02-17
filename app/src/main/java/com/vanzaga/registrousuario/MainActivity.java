package com.vanzaga.registrousuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Declaración de variables
    private EditText txt_user, txt_password;
    // Lista de usuarios
    private ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialización de vistas
        txt_user = findViewById(R.id.txt_user);
        txt_password = findViewById(R.id.txt_password);

        // Intent para obtener la lista de usuarios de la actividad anterior
        Intent intent = getIntent();
        // userList es inicializada con la lista de usuarios obtenida del intent
        userList = (ArrayList<User>) intent.getSerializableExtra("userList");

        // Si la lista de usuarios es nula, se inicializa con una lista vacía y se agregan tres usuarios
        if (userList == null) {
            userList = new ArrayList<>();
            userList.add(new User("admin", "Admin", "123456@a"));
            userList.add(new User("user1", "User primero", "123456"));
            userList.add(new User("user2", "User segundo", "123456"));
        }
    }

    // Método para iniciar sesión
    public void iniciarSesion(View view) {
        // Se obtienen los valores de los campos de texto
        String username = txt_user.getText().toString().trim();
        String password = txt_password.getText().toString().trim();

        // Si los campos de texto están vacíos, se muestra un mensaje y se retorna
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese usuario y contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        // Se crea un StringBuilder para almacenar los detalles de los usuarios
        StringBuilder userDetails = new StringBuilder();

        // Recorremos la lista de usuarios y agregamos los detalles de los usuarios al StringBuilder
        for (User user : userList) {
            userDetails.append(user.getUsername())
                    .append(" - ")
                    .append(user.getName())
                    .append(" - ")
                    .append(user.getPassword())
                    .append("\n");

            // Si el usuario y la contraseña ingresados coinciden con los de un usuario de la lista
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                Intent intent = new Intent(this, UserListActivity.class);
                intent.putExtra("userList", userList);
                intent.putExtra("userDetails", userDetails.toString());
                startActivity(intent);
                finish();
                return;
            }
        }

        Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
    }

    // Método para registrar un nuevo usuario
    public void registrar(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("userList", new ArrayList<>(userList));
        startActivity(intent);
    }
}