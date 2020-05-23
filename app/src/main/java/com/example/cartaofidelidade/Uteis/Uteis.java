package com.example.cartaofidelidade.Uteis;

import android.app.AlertDialog;
import android.content.Context;

import com.example.cartaofidelidade.R;

public class Uteis {

    public static void Alert(Context context, String mensagem){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle("SISTEMA");

        alertDialog.setMessage(mensagem);

        alertDialog.setPositiveButton("OK",null);

        alertDialog.show();
    }
}
