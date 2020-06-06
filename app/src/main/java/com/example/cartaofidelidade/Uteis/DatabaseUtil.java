package com.example.cartaofidelidade.Uteis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseUtil extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "DataBaseFidelidade.db";
    private static final int VERSAO_BANCO = 1;

    public DatabaseUtil(Context context){
        super(context,NOME_BANCO,null,VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        StringBuilder stringBuilderCreateTable = new StringBuilder();

        stringBuilderCreateTable.append("CREATE TABLE DataBaseFidelidade (");
        stringBuilderCreateTable.append("id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        stringBuilderCreateTable.append("cpf TEXT NOT NULL, ");
        stringBuilderCreateTable.append("senha TEXT NOT NULL, ");
        stringBuilderCreateTable.append("nome TEXT NOT NULL, ");
        stringBuilderCreateTable.append("cep TEXT NOT NULL, ");
        stringBuilderCreateTable.append("email TEXT NOT NULL, ");
        stringBuilderCreateTable.append("complemento TEXT NOT NULL,");
        stringBuilderCreateTable.append("dtinclusao DATE     )");

        db.execSQL(stringBuilderCreateTable.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS DataBaseFidelidade");
        onCreate(db);
    }

    public SQLiteDatabase GetConexaoDataBase(){
        return this.getWritableDatabase();
    }
}
