package com.example.cartaofidelidade.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.cartaofidelidade.Uteis.DatabaseUtil;
import com.example.cartaofidelidade.model.CartaoActivity;
import com.example.cartaofidelidade.model.PessoaActivity;

import java.util.ArrayList;
import java.util.List;

public class PessoaRepository {

    DatabaseUtil databaseUtil;

    public PessoaRepository(Context context) {
        databaseUtil = new DatabaseUtil(context);
    }

    public void Salvar(PessoaActivity pessoaActivity) {
        CartaoActivity ca = new CartaoActivity();
        ContentValues cvca = new ContentValues();
        ContentValues cv = new ContentValues();

        cv.put("cpf", pessoaActivity.getCpf());
        cv.put("senha", pessoaActivity.getSenha());
        cv.put("nome", pessoaActivity.getNome());
        cv.put("cep", pessoaActivity.getCep());
        cv.put("email", pessoaActivity.getEmail());
        cv.put("complemento", pessoaActivity.getComplemento());
        cv.put("dtinclusao", String.valueOf(pessoaActivity.getDtInclusao()));


        ca.setPessoaCpf(pessoaActivity);
        cvca.put("PessoaCpf",ca.getPessoaCpf().getCpf());

        //cv.put("numerobotoes", pessoaActivity.getNumeroBt());

        databaseUtil.GetConexaoDataBase().insert("DataBaseFidelidade", null, cv);
        databaseUtil.GetConexaoDataBase().insert("CartaoFidelidadeBd",null,cvca);
    }

    public void Atualizar(PessoaActivity pessoaActivity) {
        ContentValues cv = new ContentValues();

        cv.put("cpf", pessoaActivity.getCpf());
        cv.put("senha", pessoaActivity.getSenha());
        cv.put("nome", pessoaActivity.getNome());
        cv.put("cep", pessoaActivity.getCep());
        cv.put("email", pessoaActivity.getEmail());
        cv.put("complemento", pessoaActivity.getComplemento());
        //cv.put("numerobotoes", pessoaActivity.getNumeroBt());

        databaseUtil.GetConexaoDataBase().update("DataBaseFidelidade", cv, "id = ?", new String[]{Integer.toString(pessoaActivity.getId())});
    }

    public Integer Excluir(int id) {
        return databaseUtil.GetConexaoDataBase().delete("DataBaseFidelidade", "id = ?", new String[]{Integer.toString(id)});
    }

    public PessoaActivity PesquisaPessoa(int id) {
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery("SELECT * FROM DataBaseFidelidade WHERE id= " + id, null);

        cursor.moveToFirst();
        PessoaActivity pessoaActivity = new PessoaActivity();

        pessoaActivity.setId(cursor.getInt(cursor.getColumnIndex("id")));
        pessoaActivity.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
        pessoaActivity.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
        pessoaActivity.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        pessoaActivity.setCep(cursor.getString(cursor.getColumnIndex("cep")));
        pessoaActivity.setEmail(cursor.getString(cursor.getColumnIndex("email")));
        pessoaActivity.setComplemento(cursor.getString(cursor.getColumnIndex("complemento")));
//        pessoaActivity.setNumeroBt(cursor.getInt(cursor.getColumnIndex("numerobotoes")));
        return pessoaActivity;
    }

    public boolean verificaLogin(String cpf, String senha) {
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery("SELECT cpf,senha FROM DataBaseFidelidade WHERE cpf= " + cpf + " AND senha= " + senha, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public List<PessoaActivity> SelecionarTodos() {

        List<PessoaActivity> pessoas = new ArrayList<PessoaActivity>();

        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append("SELECT id, ");
        stringBuilderQuery.append("cpf, ");
        stringBuilderQuery.append("senha, ");
        stringBuilderQuery.append("nome, ");
        stringBuilderQuery.append("cep, ");
        stringBuilderQuery.append("email, ");
        stringBuilderQuery.append("complemento");
        //stringBuilderQuery.append("numerobotoes ");
        stringBuilderQuery.append(" FROM DataBaseFidelidade");
        stringBuilderQuery.append(" ORDER BY nome");

        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderQuery.toString(), null);
        cursor.moveToFirst();
        PessoaActivity pessoaActivity;

        while (!cursor.isAfterLast()) {
            pessoaActivity = new PessoaActivity();

            pessoaActivity.setId(cursor.getInt(cursor.getColumnIndex("id")));
            pessoaActivity.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
            pessoaActivity.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
            pessoaActivity.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            pessoaActivity.setCep(cursor.getString(cursor.getColumnIndex("cep")));
            pessoaActivity.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            pessoaActivity.setComplemento(cursor.getString(cursor.getColumnIndex("complemento")));
            //pessoaActivity.setNumeroBt(cursor.getInt(cursor.getColumnIndex("numerobotoes")));

            pessoas.add(pessoaActivity);
            cursor.moveToNext();
        }
        return pessoas;
    }
}
