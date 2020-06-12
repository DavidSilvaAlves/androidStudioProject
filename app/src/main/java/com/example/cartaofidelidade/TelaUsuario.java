package com.example.cartaofidelidade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class TelaUsuario extends AppCompatActivity {

    private Button btMeuCartaoFidelidade;
    private Button btEdtPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_usuario);

        btMeuCartaoFidelidade = (Button)findViewById(R.id.btMeuCartaoFidelidade);
        btEdtPerfil = (Button)findViewById(R.id.btEdtPerfil);
    }
}
