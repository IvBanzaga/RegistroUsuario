package com.vanzaga.registrousuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText txt_user, txt_password;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Inicializar las vistas
        txt_user = findViewById(R.id.txt_user);
        txt_password = findViewById(R.id.txt_password);

        // Inicializar la lista de usuarios con algunos usuarios predefinidos
        userList = new ArrayList<>();
        userList.add(new User("admin", "Admin", "123456@a"));
        userList.add(new User("user1", "User primero", "123456"));
        userList.add(new User("user2", "User segundo", "123456"));
    }

    // Método para iniciar sesión
    public void iniciarSesion(View view) {

        // Obtener el nombre de usuario y la contraseña ingresados por el usuario
        String username = txt_user.getText().toString();
        String password = txt_password.getText().toString();

        // Verificar si el usuario y la contraseña coinciden con algún usuario en la lista de usuarios
        // Mediante un for each se recorre la lista de usuarios
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                Intent intent = new Intent(this, UserListActivity.class);
                intent.putExtra("userList", new ArrayList<>(userList));
                startActivity(intent);
                return;
            }
        }
        // Si no se encuentra un usuario con el nombre de usuario y la contraseña ingresados, se muestra un mensaje de error
        Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
    }

    // Método para registrar un nuevo usuario, lo que abrirá la actividad RegisterActivity
    public void registrar(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("userList", new ArrayList<>(userList));
        startActivity(intent);
    }
}