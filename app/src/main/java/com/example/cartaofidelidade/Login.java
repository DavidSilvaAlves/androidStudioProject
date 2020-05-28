package com.example.cartaofidelidade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private Button btnTelaCadastro;
    private EditText cpf;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button)findViewById(R.id.btnEntrar);
        btnTelaCadastro = (Button)findViewById(R.id.btnTelaCadastro);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnEntrar){
            if(cpf.getText().toString().equals(123456) && senha.getText().toString().equals(1111)){
                //Entrar na tela de Admin para fazer cadastro das compras para cpf especifico
            }else {
                //entra na tela do usuario que apenas le os bonus do cartao fidelidade
            }
        }
        if(v.getId() == R.id.btnTelaCadastro){
            //entra na tela de cadastro do usuario
        }
    }
}
