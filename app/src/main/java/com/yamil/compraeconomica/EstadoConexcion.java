package com.yamil.compraeconomica;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by yarrib76 on 5/5/16.
 */
public class EstadoConexcion {

    private Context context;
    public EstadoConexcion(Context context) {
        this.context = context;
    }

    public boolean verificarConexionInternet(){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
