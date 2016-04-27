package com.yamil.compraeconomica;

import android.content.Context;
import android.database.Cursor;
import com.yamil.compraeconomica.Data.Contract;
import com.yamil.compraeconomica.Data.DbProvider;


/**
 * Created by yamil on 4/19/16.
 */
public class Consultas {
    private DbProvider provider;
    private Cursor cursor;
    private Context context;

    public Consultas(Context context) {
        this.context = context;
        provider = new DbProvider(context);
    }

    public boolean exsisteUsuario (String email){
        String[] columns = {Contract.Usuarios.COL_EMAIL};
        // Aigno a un string el where en este ejemplo seria: where titulo like "HOLA&"
        String selection = Contract.Usuarios.COL_EMAIL+ " LIKE ?";
        // String[] selectionArgs = {"HOLA%"};
        String[] selectionArgs = {"" + email};
        cursor = provider.query(Contract.Usuarios.TABLA_USUARIOS,columns, selection, selectionArgs);
        if (cursor != null && cursor.getCount() > 0){
            return true;
        }
        return false;
    }

    public boolean validarPassword(String password){
        String[] columns = {Contract.Usuarios.COL_APELLIDO};
        String selection = Contract.Usuarios.COL_CLAVE + " LIKE ?";
        String[] selectionArgs = {password};
        cursor = provider.query(Contract.Usuarios.TABLA_USUARIOS,columns, selection, selectionArgs);
        if (cursor != null && cursor.getCount() > 0){
            return true;
        }
        return false;
    }
}
