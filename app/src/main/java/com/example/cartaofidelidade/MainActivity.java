package com.example.cartaofidelidade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCadastrar;
    private Button btnPesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnPesquisar = (Button) findViewById(R.id.btnPesquisaClienteTela);
        btnCadastrar.setOnClickListener(this);
        btnPesquisar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCadastrar) {
            abrirCadastro();
        } else if (v.getId() == R.id.btnPesquisaClienteTela) {
            abrirPesquisar();
        }
    }

    private void abrirCadastro() {
        Intent it = new Intent(this, CadastrarCliente.class);
        startActivity(it);
    }

    private void abrirPesquisar() {
        Intent intent = new Intent(this, ConsultarAcitivty.class);
        startActivity(intent);
        finish();
    }
}
