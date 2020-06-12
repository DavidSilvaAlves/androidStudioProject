package com.example.cartaofidelidade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.cartaofidelidade.model.PessoaActivity;

public class TelaAdministrador extends AppCompatActivity {

    private Button btNovaVenda;
    private Button btProdutos;
    private Button btNovoProduto;
    private Button btNovoCliente;
    private Button btConsultar;
    private Button btExcluirCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_administrador);

        btNovaVenda = (Button)findViewById(R.id.btNovaVenda);
        btProdutos = (Button)findViewById(R.id.btProdutos);
        btNovoProduto = (Button)findViewById(R.id.btNovoProduto);
        btNovoCliente = (Button)findViewById(R.id.btNovoCliente);
        btConsultar = (Button)findViewById(R.id.btConsultar);
        btExcluirCliente = (Button)findViewById(R.id.btExcluirCliente);

    }
}
