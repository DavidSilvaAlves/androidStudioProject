package com.example.cartaofidelidade.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.cartaofidelidade.Uteis.DatabaseUtil;
import com.example.cartaofidelidade.model.PessoaActivity;

import java.util.ArrayList;
import java.util.List;

public class PessoaRepository {

    DatabaseUtil databaseUtil;

    public PessoaRepository(Context context) {
        databaseUtil = new DatabaseUtil(context);
    }

    public void Salvar(PessoaActivity pessoaActivity) {
        ContentValues cv = new ContentValues();

        cv.put("cpf", pessoaActivity.getCpf());
        cv.put("nome", pessoaActivity.getNome());
        cv.put("numerobotoes", pessoaActivity.getNumeroBt());

        databaseUtil.GetConexaoDataBase().insert("clientes", null, cv);
    }

    public void Atualizar(PessoaActivity pessoaActivity) {
        ContentValues cv = new ContentValues();

        cv.put("cpf", pessoaActivity.getCpf());
        cv.put("nome", pessoaActivity.getNome());
        cv.put("numerobotoes", pessoaActivity.getNumeroBt());

        databaseUtil.GetConexaoDataBase().update("clientes", cv, "id = ?", new String[]{Integer.toString(pessoaActivity.getId())});
    }

    public Integer Excluir(int id) {
        return databaseUtil.GetConexaoDataBase().delete("clientes", "id = ?", new String[]{Integer.toString(id)});
    }

    public PessoaActivity PesquisaPessoa(int id) {
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery("SELECT * FROM clientes WHERE id= " + id, null);

        cursor.moveToFirst();
        PessoaActivity pessoaActivity = new PessoaActivity();

        pessoaActivity.setId(cursor.getInt(cursor.getColumnIndex("id")));
        pessoaActivity.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
        pessoaActivity.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        pessoaActivity.setNumeroBt(cursor.getInt(cursor.getColumnIndex("numerobotoes")));
        return pessoaActivity;
    }

    public List<PessoaActivity> SelecionarTodos() {

        List<PessoaActivity> pessoas = new ArrayList<PessoaActivity>();

        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append("SELECT id, ");
        stringBuilderQuery.append("cpf, ");
        stringBuilderQuery.append("nome, ");
        stringBuilderQuery.append("numerobotoes ");
        stringBuilderQuery.append(" FROM clientes ");
        stringBuilderQuery.append(" ORDER BY nome ");

        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderQuery.toString(), null);
        cursor.moveToFirst();
        PessoaActivity pessoaActivity;

        while (!cursor.isAfterLast()) {
            pessoaActivity = new PessoaActivity();

            pessoaActivity.setId(cursor.getInt(cursor.getColumnIndex("id")));
            pessoaActivity.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
            pessoaActivity.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            pessoaActivity.setNumeroBt(cursor.getInt(cursor.getColumnIndex("numerobotoes")));

            pessoas.add(pessoaActivity);
            cursor.moveToNext();
        }
        return pessoas;
    }
}
