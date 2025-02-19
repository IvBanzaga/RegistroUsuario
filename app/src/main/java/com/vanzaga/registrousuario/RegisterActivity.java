package com.vanzaga.registrousuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    // Declaración de variables
    private EditText editTextUsername, editTextName, editTextPassword;

    // Declaración de la lista de usuarios
    private ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicialización de vistas
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);

        // Intent para obtener la lista de usuarios de la actividad anterior
        Intent intent = getIntent();

        // userList es inicializada con la lista de usuarios obtenida del intent
        userList = (ArrayList<User>) intent.getSerializableExtra("userList");

        // Si la lista de usuarios es nula, se inicializa con una lista vacía
        if (userList == null) {
            userList = new ArrayList<>();
        }
    }

    // Método para registrar un usuario
    public void registrarUsuario(View view) {

        // Se obtienen los valores de los campos de texto
        String username = editTextUsername.getText().toString();
        String name = editTextName.getText().toString();
        String password = editTextPassword.getText().toString();

        // Si los campos de texto están vacíos, se muestra un mensaje y se retorna
        if (username.isEmpty() || name.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar si la contraseña cumple con los requisitos
        if (!isValidPassword(password)) {
            Toast.makeText(this,
                    "La contraseña debe tener al menos 8 caracteres, incluyendo una mayúscula, letras, números y algún carácter especial ($, %, &, @)",
                    Toast.LENGTH_LONG).show();
            return;
        }

        // Recorremos la lista de usuarios y verificamos si el nombre de usuario ya existe
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                Toast.makeText(this, "El nombre de usuario ya existe", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // Se crea un nuevo usuario y se agrega a la lista de usuarios
        User newUser = new User(username, name, password);

        // Se agrega el nuevo usuario a la lista de usuarios
        userList.add(newUser);

        // Se muestra un mensaje de registro exitoso
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

        // Se crea un intent para abrir la actividad MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("userList", userList);
        startActivity(intent);
        finish();
    }

    // Método para verificar si la contraseña cumple con los requisitos
    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[$%&@].*");
    }
}