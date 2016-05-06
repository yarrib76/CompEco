package com.yamil.compraeconomica;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.yamil.compraeconomica.Data.Contract;
import com.yamil.compraeconomica.Data.DbProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by yamil on 4/19/16.
 */
public class VerificarUsuario extends AsyncTask <Void, Void, Integer> {
    int resultado;
    String email;
    ProgressDialog pDialog;
    String password;
    Context context;
    String codval = "4015";
    private SharedPreferences configuracion;

    public VerificarUsuario(Context context, String email, String password) {
        this.context = context;
        this.email = email;
        this.password = password;
        pDialog = new ProgressDialog(context);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setMessage("Aguarde un momento,por favor...");
        pDialog.setCancelable(true);
        pDialog.setMax(100);
    }

    @Override
    protected Integer doInBackground(Void... params) {
        String uriBuilder = "http://190.221.134.35:8003/api/loginmovil?email=" + email + "&&" + "password=" + password +
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
                final AlertDialog alerta = new AlertDialog.Builder(context).create();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                View view = inflater.inflate(R.layout.dialogregusuario, null);
                Button btn_respuesta_si = (Button) view.findViewById(R.id.btn_respuesta_si);
                Button btn_respuesta_no = (Button) view.findViewById(R.id.btn_respuesta_no);
                btn_respuesta_si.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent registrarUsuario = new Intent(context,RegistrarUsuarioActivity.class);
                        context.startActivity(registrarUsuario);
                        alerta.dismiss();
                    }
                });
                btn_respuesta_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alerta.dismiss();
                    }
                });
                alerta.setView(view);
                alerta.show();
                break;
            case 1:
                Intent ingresar = new Intent(context,MainActivity.class);
                context.startActivity(ingresar);
                break;
            case 2:
                Toast.makeText(context, "Password Incorrecta", Toast.LENGTH_LONG).show();
                break;
        }
    }
    @Override
    protected void onPreExecute() {
        pDialog.setProgress(0);
        pDialog.show();
    }

    public boolean exsisteUsuario(String email) {

        return false;
    }

    public boolean validarPassword(String password) {

        return false;
    }
}
