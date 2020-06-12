package com.example.cartaofidelidade;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.cartaofidelidade.Uteis.BuscaCep;
import com.example.cartaofidelidade.Uteis.Uteis;
import com.example.cartaofidelidade.repository.PessoaRepository;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private Button btnTelaCadastro;
    private EditText cpf;
    private EditText senha;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        btnLogin = (Button)findViewById(R.id.btnEntrar);
        btnTelaCadastro = (Button)findViewById(R.id.btnTelaCadastro);
        cpf = (EditText)findViewById(R.id.edtLogin);
        senha = (EditText)findViewById(R.id.edtSenha);
        btnLogin.setOnClickListener(this);
        btnTelaCadastro.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnEntrar){
            if(cpf.getText().toString().equals("") || senha.getText().toString().equals("")){
                Uteis.Alert(this,"Favor preencher todos os campos!");
            }else {
                if (new PessoaRepository(this).verificaLogin(cpf.getText().toString(), senha.getText().toString())) {
                    Log.e("msg", String.valueOf(cpf));
                    if(cpf.getText().toString().equals("999999")){
                        //tela do administrador
                        Log.e("msg", "Login Admin efetuado!");
                        abrirTelaAdmin();
                        return;
                    }else {
                        //tela do usuario
                        Log.e("msg", "Login Usuario efetuado!");
                        abrirTelaUsuario();
                    }
                } else {
                    Uteis.Alert(this,"Usuario ou senha incorreto!");
                    cpf.setText("");
                    senha.setText("");
                    Log.e("msg", "Login nao efetuado!");
                }
            }

        }
        if(v.getId() == R.id.btnTelaCadastro){
            abrirCadastro();
        }
    }
    private void abrirCadastro() {
        Intent intent = new Intent(this, CadastrarCliente.class);
        startActivity(intent);
        finish();
    }

    private void abrirTelaAdmin(){
        Intent intent = new Intent(this, TelaAdministrador.class);
        startActivity(intent);
        finish();
    }

    private void abrirTelaUsuario(){
        Intent intent = new Intent(this, TelaUsuario.class);
        startActivity(intent);
        finish();
    }
}
