package com.yamil.compraeconomica;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText edit_email;
    private EditText edit_password;
    private Button btn_login;
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
                Consultas consultas = new Consultas(LoginActivity.this);
                boolean respuesta = consultas.exsisteUsuario(edit_email.getText().toString());
                if (respuesta){
                    Intent startAplication = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(startAplication);
                } else {
                    final AlertDialog alertaModificar = new AlertDialog.Builder(LoginActivity.this).create();
                    LayoutInflater inflater = LoginActivity.this.getLayoutInflater();
                    v = inflater.inflate(R.layout.dialogregusuario, (ViewGroup) findViewById(R.id.dialog_mod_pedidos));
                    Button btn_respuesta_si = (Button) v.findViewById(R.id.btn_respuesta_si);
                    alertaModificar.setView(v);
                    alertaModificar.show();
                    btn_respuesta_si.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final AlertDialog alertaRegUsuario = new AlertDialog.Builder(LoginActivity.this).create();
                            LayoutInflater inflater = LoginActivity.this.getLayoutInflater();
                            v = inflater.inflate(R.layout.dialogo_reg_usuario_form, (ViewGroup) findViewById(R.id.dialogo_reg_usuario));
                            Button btn_guardar_usuario = (Button) v.findViewById(R.id.btn_guardar_usuario);
                            alertaRegUsuario.setView(v);
                            alertaRegUsuario.show();
                            btn_guardar_usuario.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ContentValues
                                }
                            });
                        }
                    });
                }
            }
        });

    }
}
