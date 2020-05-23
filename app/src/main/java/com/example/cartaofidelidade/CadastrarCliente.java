package com.example.cartaofidelidade;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cartaofidelidade.Uteis.Uteis;
import com.example.cartaofidelidade.model.PessoaActivity;
import com.example.cartaofidelidade.repository.PessoaRepository;

public class CadastrarCliente extends AppCompatActivity implements View.OnClickListener {

    public Button btnVoltar, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10;
    private Button btnCadCliente;
    private Button adcEstrela;
    private Button excEstrela;
    private EditText edtNome;
    private EditText edtCpf;
    public final String estrela = "X";
    private int numeroBt = 0;
    String cpfAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        this.CriarComponentes();
        this.CriarEventos();
    }

    protected void CriarComponentes() {
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        bt4 = (Button) findViewById(R.id.bt4);
        bt5 = (Button) findViewById(R.id.bt5);
        bt6 = (Button) findViewById(R.id.bt6);
        bt7 = (Button) findViewById(R.id.bt7);
        bt8 = (Button) findViewById(R.id.bt8);
        bt9 = (Button) findViewById(R.id.bt9);
        bt10 = (Button) findViewById(R.id.bt10);
        edtNome = (EditText) findViewById(R.id.edtnome);
        edtCpf = (EditText) findViewById(R.id.edtcpf);
        adcEstrela = (Button) findViewById(R.id.adcEstrela);
        btnCadCliente = (Button) findViewById(R.id.btnCadCliente);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        excEstrela = (Button) findViewById(R.id.excEstrela);
        btnVoltar.setOnClickListener(this);
        btnCadCliente.setOnClickListener(this);
        adcEstrela.setOnClickListener(this);
        excEstrela.setOnClickListener(this);
        Estrela(true);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.adcEstrela) {
            adicionarBt();
        }
        if (v.getId() == R.id.excEstrela) {
            removerBt();
        }
    }

    protected void CriarEventos() {
        btnCadCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Salvar_onClick();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    protected void Salvar_onClick() {
        if (edtCpf.getText().toString().trim().equals("")) {
            Uteis.Alert(this, this.getString(R.string.cpf_obrigatorio));
            edtCpf.requestFocus();
        } else if (edtNome.getText().toString().trim().equals("")) {
            Uteis.Alert(this, this.getString(R.string.nome_obrigatorio));
            edtNome.requestFocus();
        } else {
            PessoaActivity pessoaActivity = new PessoaActivity();

            pessoaActivity.setCpf(edtCpf.getText().toString().trim());
            pessoaActivity.setNome(edtNome.getText().toString().trim());
            pessoaActivity.setNumeroBt(numeroBt);
            cpfAtual = edtCpf.getText().toString();

            new PessoaRepository(this).Salvar(pessoaActivity);
            Uteis.Alert(this, this.getString(R.string.registro_salvo));
            numeroBt = 0;
            limpaNmCpf();
            limpaBotoes("");
        }
    }

    private void adicionarBt() {
        if (bt1.getText().toString() == "") {
            bt1.setText(estrela);
            numeroBt++;
        } else if (bt2.getText().toString() == "") {
            bt2.setText(estrela);
            numeroBt++;
        } else if (bt3.getText().toString() == "") {
            bt3.setText(estrela);
            numeroBt++;
        } else if (bt4.getText().toString() == "") {
            bt4.setText(estrela);
            numeroBt++;
        } else if (bt5.getText().toString() == "") {
            bt5.setText(estrela);
            numeroBt++;
        } else if (bt6.getText().toString() == "") {
            bt6.setText(estrela);
            numeroBt++;
        } else if (bt7.getText().toString() == "") {
            bt7.setText(estrela);
            numeroBt++;
        } else if (bt8.getText().toString() == "") {
            bt8.setText(estrela);
            numeroBt++;
        } else if (bt9.getText().toString() == "") {
            bt9.setText(estrela);
            numeroBt++;
        } else if (bt10.getText().toString() == "") {
            bt10.setText(estrela);
            numeroBt++;
        }

        if (bt10.getText().toString().equals("X")) {
            if (edtCpf.getText().toString().equals(cpfAtual)) {
                showMessage("Cliente CPF: " + edtCpf.getText().toString() + " Tem direito ao consumo gratis!", "Aviso");
                //limpaBotoes("");
                numeroBt = 0;
            } else {
                showMessage("CPF " + edtCpf.getText().toString() + " Não cadastrado!", "Aviso");
                limpaNmCpf();
                limpaBotoes("");
                edtNome.requestFocus();
            }
        }
    }


    private void removerBt() {
        if (bt10.getText().toString() == "X") {
            bt10.setText("");
            numeroBt--;
        } else if (bt9.getText().toString() == "X") {
            bt9.setText("");
            numeroBt--;
        } else if (bt8.getText().toString() == "X") {
            bt8.setText("");
            numeroBt--;
        } else if (bt7.getText().toString() == "X") {
            bt7.setText("");
            numeroBt--;
        } else if (bt6.getText().toString() == "X") {
            bt6.setText("");
            numeroBt--;
        } else if (bt5.getText().toString() == "X") {
            bt5.setText("");
            numeroBt--;
        } else if (bt4.getText().toString() == "X") {
            bt4.setText("");
            numeroBt--;
        } else if (bt3.getText().toString() == "X") {
            bt3.setText("");
            numeroBt--;
        } else if (bt2.getText().toString() == "X") {
            bt2.setText("");
            numeroBt--;
        } else if (bt1.getText().toString() == "X") {
            bt1.setText("");
            numeroBt--;
        }
    }

    private void limpaNmCpf() {
        edtNome.setText("");
        edtCpf.setText("");
    }

    public void showMessage(String Caption, String Title) {
        androidx.appcompat.app.AlertDialog.Builder dialogo = new androidx.appcompat.app.AlertDialog.Builder(CadastrarCliente.this);
        dialogo.setTitle(Title);
        dialogo.setMessage(Caption);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }

    private boolean Estrela(Boolean acao) {
        adcEstrela.setEnabled(acao);
        excEstrela.setEnabled(acao);
        return false;
    }

    private void limpaBotoes(String acao) {
        bt1.setText(acao);
        bt2.setText(acao);
        bt3.setText(acao);
        bt4.setText(acao);
        bt5.setText(acao);
        bt6.setText(acao);
        bt7.setText(acao);
        bt8.setText(acao);
        bt9.setText(acao);
        bt10.setText(acao);
    }

}

