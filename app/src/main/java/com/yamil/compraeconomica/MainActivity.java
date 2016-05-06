package com.yamil.compraeconomica;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences configuracion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configuracion = this.getSharedPreferences("config", Context.MODE_PRIVATE);
        Toast.makeText(MainActivity.this, "Session id: " + configuracion.getInt("session_id", 1), Toast.LENGTH_SHORT).show();
    }
}
