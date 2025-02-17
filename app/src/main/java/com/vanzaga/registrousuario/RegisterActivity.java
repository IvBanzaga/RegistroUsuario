package com.vanzaga.registrousuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    // Declarar las vistas
    private EditText editTextUsername, editTextName, editTextPassword;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializar las vistas
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);

        // Obtener la lista de usuarios de la actividad anterior
        userList = (List<User>) getIntent().getSerializableExtra("userList");
    }

    // Método para registrar un nuevo usuario
    public void registrarUsuario(View view) {

        // Obtener los datos ingresados por el usuario
        String username = editTextUsername.getText().toString();
        String name = editTextName.getText().toString();
        String password = editTextPassword.getText().toString();

        // Verificar si los campos están vacíos
        if (username.isEmpty() || name.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar si la contraseña cumple con los requisitos
        if (!isValidPassword(password)) {
            Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres, incluyendo una mayúscula, letras, números y algún carácter especial ($, %, &, @)", Toast.LENGTH_LONG).show();
            return;
        }

        // Verificar si el nombre de usuario ya existe
        for (User user : userList) {

            // Si el nombre de usuario ya existe, mostrar un mensaje de error
            if (user.getUsername().equals(username)) {
                Toast.makeText(this, "El nombre de usuario ya existe", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // Agregar el nuevo usuario a la lista de usuarios y mostrar un mensaje de registro exitoso
        userList.add(new User(username, name, password));
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("userList", new ArrayList<>(userList));
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