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
//            if(cpf.getText().toString().equals(123456) && senha.getText().toString().equals(1111)){
//                //Entrar na tela de Admin para fazer cadastro das compras para cpf especifico
//            }else {
//                //entra na tela do usuario que apenas le os bonus do cartao fidelidade
//            }
            if(cpf.getText().toString().equals("") || senha.getText().toString().equals("")){
                Uteis.Alert(this,"Favor preencher todos os campos!");
            }else {
                if (new PessoaRepository(this).verificaLogin(cpf.getText().toString(), senha.getText().toString()) == true) {

                    if(cpf.getText().toString().equals("000000")){
                        //tela do administrador
                        Log.e("msg", "Login Admin efetuado!");
                        return;
                    }
                    //tela do usuario

                } else {
                    Uteis.Alert(this,"Usuario ou senha incorreto!");
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
}
