package com.yamil.compraeconomica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText edit_email;
    private EditText edit_password;
    private Button btn_login;
    private CrearUsuarios usuario;
    int respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_password = (EditText) findViewById(R.id.edit_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerificarUsuario verificarUsuario = new  VerificarUsuario(LoginActivity.this,edit_email.getText().toString(),
                        edit_password.getText().toString());
                verificarUsuario.execute();
            }
        });

    }
}