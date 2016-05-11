package com.yamil.compraeconomica;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
    private SharedPreferences configuracion;
    private FragmentTabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = (FragmentTabHost) findViewById(R.id.mi_tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Tab 1"),
                Tab1.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Tab 2"),
                Tab2.class, null);
        /*tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Lengüeta 3"),
                Tab3.class, null);*/
        // tabHost.getTabWidget().getChildTabViewAt(1).setBackgroundResource(R.drawable.comida);

        configuracion = this.getSharedPreferences("config", Context.MODE_PRIVATE);
        Toast.makeText(MainActivity.this, "Session id: " + configuracion.getInt("session_id", 1), Toast.LENGTH_SHORT).show();
    }
}
