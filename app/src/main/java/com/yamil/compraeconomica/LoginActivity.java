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

import com.yamil.compraeconomica.Data.Contract;
import com.yamil.compraeconomica.Data.DbProvider;

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
                final Consultas consultas = new Consultas(LoginActivity.this);
                boolean respuesta = consultas.exsisteUsuario(edit_email.getText().toString());
                if (respuesta){
                    Intent startAplication = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(startAplication);
                } else {
                    final AlertDialog alertaPreguntaCrearUsuario = new AlertDialog.Builder(LoginActivity.this).create();
                    LayoutInflater inflater = LoginActivity.this.getLayoutInflater();
                    v = inflater.inflate(R.layout.dialogregusuario, (ViewGroup) findViewById(R.id.dialog_mod_pedidos));
                    Button btn_respuesta_si = (Button) v.findViewById(R.id.btn_respuesta_si);
                    Button btn_respuesta_no = (Button) v.findViewById(R.id.btn_respuesta_no);
                    alertaPreguntaCrearUsuario.setView(v);
                    alertaPreguntaCrearUsuario.show();
                    btn_respuesta_no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertaPreguntaCrearUsuario.dismiss();
                        }
                    });
                    btn_respuesta_si.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final AlertDialog alertaRegUsuario = new AlertDialog.Builder(LoginActivity.this).create();
                            LayoutInflater inflater = LoginActivity.this.getLayoutInflater();
                            v = inflater.inflate(R.layout.dialogo_reg_usuario_form, (ViewGroup) findViewById(R.id.dialogo_reg_usuario));
                            final EditText edit_nombre = (EditText)v.findViewById(R.id.edit_nombre);
                            final EditText edit_apellido = (EditText)v.findViewById(R.id.edit_apellido);
                            final EditText edit_email = (EditText)v.findViewById(R.id.edit_email);
                            final EditText edit_clave = (EditText)v.findViewById(R.id.edit_clave);
                            Button btn_guardar_usuario = (Button) v.findViewById(R.id.btn_guardar_usuario);
                            Button btn_cancelar = (Button) v.findViewById(R.id.btn_cancelar_usuario);
                            alertaRegUsuario.setView(v);
                            alertaRegUsuario.show();
                            btn_cancelar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertaRegUsuario.dismiss();
                                    alertaPreguntaCrearUsuario.dismiss();
                                }
                            });
                            btn_guardar_usuario.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    DbProvider dbProvider = new DbProvider(LoginActivity.this);
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put(Contract.Usuarios.COL_NOMBRE, edit_nombre.getText().toString());
                                    contentValues.put(Contract.Usuarios.COL_APELLIDO,edit_apellido.getText().toString());
                                    contentValues.put(Contract.Usuarios.COL_EMAIL,edit_email.getText().toString());
                                    contentValues.put(Contract.Usuarios.COL_CLAVE,edit_clave.getText().toString());
                                    dbProvider.insert(Contract.Usuarios.TABLA_USUARIOS, contentValues);
                                    Toast.makeText(LoginActivity.this, "El usuario se creo correctamente", Toast.LENGTH_SHORT).show();
                                    alertaRegUsuario.dismiss();
                                    alertaPreguntaCrearUsuario.dismiss();
                                }
                            });
                        }
                    });
                }
            }
        });

    }
}
