package com.example.cartaofidelidade;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cartaofidelidade.Uteis.BuscaCep;
import com.example.cartaofidelidade.Uteis.Uteis;
import com.example.cartaofidelidade.model.PessoaActivity;
import com.example.cartaofidelidade.repository.PessoaRepository;

import java.util.Date;
import java.util.List;

public class CadastrarCliente extends AppCompatActivity implements View.OnClickListener {

    public Button btnVoltar, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10;
    private Button btnCadCliente;
    private Button adcEstrela;
    private Button excEstrela;
    private EditText edtNome;
    private EditText edtCpf, edtcep, edtsenha, edtemail, edtcomplemento;
    private TextView viewCep;
    public final String estrela = "X";
    private int numeroBt = 0;
    String cpfAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        this.CriarComponentes();
        this.CriarEventos();

        edtcep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (edtcep.getText().length() == 8) {
                    BuscaCep buscaCep = new BuscaCep();
                    List<String> lista = buscaCep.buscarCep(edtcep.getText().toString());
                    if (lista != null) {
                        String logradouro = lista.get(0);
                        String bairro = lista.get(1);
                        String cidade = lista.get(2);
                        String uf = lista.get(3);
                        Log.e("Tag2", "logradouro " + logradouro + "bairro " + bairro + "cidade " + cidade + uf);
                        viewCep.setText("Logradouro: " + logradouro
                                + "\nBairro: " + bairro + "\nCidade: " + cidade + "\nUF: " + uf);

                    } else {
                        viewCep.setText("CEP não encontrado. Favor digite novamente!");
                    }
                } else if (edtcep.getText().length() != 8) {
                    viewCep.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    protected void CriarComponentes() {
       /*bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        bt4 = (Button) findViewById(R.id.bt4);
        bt5 = (Button) findViewById(R.id.bt5);
        bt6 = (Button) findViewById(R.id.bt6);
        bt7 = (Button) findViewById(R.id.bt7);
        bt8 = (Button) findViewById(R.id.bt8);
        bt9 = (Button) findViewById(R.id.bt9);
        bt10 = (Button) findViewById(R.id.bt10);*/
        edtNome = (EditText) findViewById(R.id.edtnome);
        edtCpf = (EditText) findViewById(R.id.edtcpf);
        btnCadCliente = (Button) findViewById(R.id.btnCadCliente);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        edtcep = (EditText) findViewById(R.id.edtcep);
        edtcomplemento = (EditText) findViewById(R.id.edtcomplemento);
        edtsenha = (EditText) findViewById(R.id.edtsenha);
        edtemail = (EditText) findViewById(R.id.edtemail);
        viewCep = (TextView) findViewById(R.id.viewCep);
        btnVoltar.setOnClickListener(this);
        btnCadCliente.setOnClickListener(this);
        //adcEstrela = (Button) findViewById(R.id.adcEstrela);
        //excEstrela = (Button) findViewById(R.id.excEstrela);
        //adcEstrela.setOnClickListener(this);
        //excEstrela.setOnClickListener(this);
        //Estrela(true);
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
                Intent intentMainActivity = new Intent(getApplicationContext(), Login.class);
                startActivity(intentMainActivity);
                finish();
            }

        });
    }

    protected void Salvar_onClick() {
        if (edtCpf.getText().toString().trim().equals("") || edtsenha.getText().toString().trim().equals("") || edtNome.getText().toString().trim().equals("") || edtemail.getText().toString().trim().equals("") || edtcep.getText().toString().trim().equals("") || edtcomplemento.getText().toString().trim().equals("")) {
            Uteis.Alert(this, "Campos obrigatórios vazios!!");
        } else {
            PessoaActivity pessoaActivity = new PessoaActivity();

            pessoaActivity.setCpf(edtCpf.getText().toString().trim());
            pessoaActivity.setSenha(edtsenha.getText().toString().trim());
            pessoaActivity.setNome(edtNome.getText().toString().trim());
            pessoaActivity.setEmail(edtemail.getText().toString().trim());
            pessoaActivity.setCep(edtcep.getText().toString().trim());
            pessoaActivity.setComplemento(edtcomplemento.getText().toString().trim());
            pessoaActivity.setDtinclusao(new Date());
            //pessoaActivity.setNumeroBt(numeroBt);
            cpfAtual = edtCpf.getText().toString();

            new PessoaRepository(this).Salvar(pessoaActivity);
            Uteis.Alert(this, this.getString(R.string.registro_salvo));
            //numeroBt = 0;
            limpaNmCpf();
            //limpaBotoes("");
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
        edtsenha.setText("");
        edtcep.setText("");
        edtcomplemento.setText("");
        edtemail.setText("");
    }

    public void showMessage(String Caption, String Title) {
        androidx.appcompat.app.AlertDialog.Builder dialogo = new androidx.appcompat.app.AlertDialog.Builder(CadastrarCliente.this);
        dialogo.setTitle(Title);
        dialogo.setMessage(Caption);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }

    /*private boolean Estrela(Boolean acao) {
        adcEstrela.setEnabled(acao);
        excEstrela.setEnabled(acao);
        return false;
    }*/

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

