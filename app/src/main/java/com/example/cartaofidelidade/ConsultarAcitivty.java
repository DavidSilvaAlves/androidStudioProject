package com.example.cartaofidelidade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cartaofidelidade.Uteis.LinhaConsultarAdapter;
import com.example.cartaofidelidade.model.PessoaActivity;
import com.example.cartaofidelidade.repository.PessoaRepository;

import java.util.List;

public class ConsultarAcitivty extends AppCompatActivity {

    ListView listViewPessoas;
    Button buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        listViewPessoas = (ListView) this.findViewById(R.id.listViewPessoas);
        buttonVoltar = (Button) this.findViewById(R.id.buttonVoltar);

        this.CarregarPessoasCadastradas();
        this.CriarEvento();
    }

    protected void CriarEvento() {
        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentMainActivity);
                finish();
            }
        });
    }

    protected void CarregarPessoasCadastradas() {

        PessoaRepository pessoaRepository = new PessoaRepository(this);
        List<PessoaActivity> pessoas = pessoaRepository.SelecionarTodos();

        listViewPessoas.setAdapter(new LinhaConsultarAdapter(this, pessoas));
    }
}
