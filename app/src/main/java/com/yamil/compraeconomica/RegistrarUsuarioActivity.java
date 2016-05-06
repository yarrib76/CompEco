package com.yamil.compraeconomica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarUsuarioActivity extends AppCompatActivity {
    private EditText edit_nombre;
    private EditText edit_email;
    private EditText edit_apellido;
    private EditText edit_clave;
    private Button btn_guardar_usuario;
    private Button btn_cancelar_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        edit_nombre = (EditText) findViewById(R.id.edit_nombre);
        edit_apellido = (EditText) findViewById(R.id.edit_apellido);
        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_clave = (EditText) findViewById(R.id.edit_clave);
        btn_guardar_usuario = (Button) findViewById(R.id.btn_guardar_usuario);
        btn_cancelar_usuario = (Button) findViewById(R.id.btn_cancelar_usuario);

        btn_guardar_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EstadoConexcion estadoConexcion = new EstadoConexcion(RegistrarUsuarioActivity.this);
                if (estadoConexcion.verificarConexionInternet()){
                    CrearUsuarios crearUsuarios = new CrearUsuarios(RegistrarUsuarioActivity.this,edit_email.getText().toString(),
                            edit_clave.getText().toString(),edit_nombre.getText().toString(),edit_apellido.getText().toString());
                    crearUsuarios.execute();
                }else {
                    Toast.makeText(RegistrarUsuarioActivity.this, "No hay conexion a internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
