// UserListActivity.java
package com.vanzaga.registrousuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    // Declaramos las lista de usuarios y el ListView
    private ListView listView;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        // Inicializamos el ListView
        listView = findViewById(R.id.listView);

        // Obtenemos la lista de usuarios de la actividad anterior
        userList = (List<User>) getIntent().getSerializableExtra("userList");

        if (userList == null) {
            userList = new ArrayList<>();
        }

        // Creamos una lista de nombres de usuario
        List<String> usernames = new ArrayList<>();

        // Construir un StringBuilder para almacenar los detalles de los usuarios
        StringBuilder userDetails = new StringBuilder();

        // Recorremos la lista de usuarios y agregamos los nombres de usuario a la lista
        // de nombres de usuario
        for (User user : userList) {
            usernames.add(user.getUsername());
            userDetails.append(user.getUsername())
                    .append(" - ")
                    .append(user.getName())
                    .append(" - ")
                    .append(user.getPassword())
                    .append("\n");
        }

        // Creamos un ArrayAdapter para mostrar la lista de nombres de usuario en el
        // ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, usernames);

        // Establecemos el adaptador en el ListView
        listView.setAdapter(adapter);

        // Establecemos un listener para el ListView, de manera que al hacer clic en un
        // elemento se abra la actividad UserDetailActivity
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, UserDetailActivity.class);

            // Pasamos el usuario correspondiente a la posición seleccionada a la actividad
            // UserDetailActivity
            intent.putExtra("user", userList.get(position));
            intent.putExtra("userList", new ArrayList<>(userList));
            intent.putExtra("userDetails", userDetails.toString());
            startActivity(intent);
        });
    }

    // Método para volver a la actividad MainActivity
    public void volverAlInicio(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        // Pasamos la lista de usuarios a la actividad MainActivity
        intent.putExtra("userList", new ArrayList<>(userList));
        startActivity(intent);
        finish();
    }
}