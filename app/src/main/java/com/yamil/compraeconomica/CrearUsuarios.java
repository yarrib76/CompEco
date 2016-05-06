package com.yamil.compraeconomica;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by yamil on 4/29/16.
 */
public class CrearUsuarios extends AsyncTask<Void, Void, Integer> {
    int resultado;
    String email;
    ProgressDialog pDialog;
    String password;
    Context context;
    String codval = "4015";
    private SharedPreferences configuracion;

    public CrearUsuarios(Context context, String email, String password) {
        this.email = email;
        this.password = password;
        this.context = context;
        configuracion = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        pDialog = new ProgressDialog(context);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setMessage("Aguarde un momento,por favor...");
        pDialog.setCancelable(true);
        pDialog.setMax(100);
    }

    @Override
    protected Integer doInBackground(Void... params) {
        String uriBuilder = "http://190.221.134.35:8003/api/crearloginmovil?email=" + email + "&&" + "password=" + password +
                "&&" + "codval=" + codval;
        String result = "";
        URL url = null;
        try {
            url = new URL(uriBuilder);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BufferedReader reader = null;

        if (url != null){
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(url.openStream());
                reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    result += line;
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (!result.isEmpty()) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                resultado = jsonObject.getInt("valor");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

    @Override
    protected void onPostExecute(Integer resultado) {
        pDialog.cancel();
        switch (resultado){
            case 0:
                Toast.makeText(context, "No hay conexion de Internet", Toast.LENGTH_LONG).show();
            case 1:
                Toast.makeText(context, "Debe Ingresar un Mail", Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(context, "Debe Ingresar una Contraseña", Toast.LENGTH_LONG).show();
                break;
            case 3:
                Toast.makeText(context, "Debe Ingresar un Mail y una Consraseña", Toast.LENGTH_LONG).show();
                break;
            case 4:
                Toast.makeText(context, "El Usuario ya Existe", Toast.LENGTH_LONG).show();
                break;
            default:
                SharedPreferences.Editor edit = configuracion.edit();
                edit.putInt("session_id", resultado);
                edit.apply();
                Toast.makeText(context, "El Usuario se Creo Correctamente", Toast.LENGTH_LONG).show();
                Intent login = new Intent(context,LoginActivity.class);
                context.startActivity(login);
                break;
        }
    }

    @Override
    protected void onPreExecute() {
        pDialog.setProgress(0);
        pDialog.show();
    }
}
