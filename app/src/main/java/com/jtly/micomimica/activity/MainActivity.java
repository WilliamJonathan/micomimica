package com.jtly.micomimica.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jtly.micomimica.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView palavrarandom;
    private Button gerar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        palavrarandom = findViewById(R.id.txtPalavra);

    }

    public void gerarPalavra(View view){

        String[] palavras = {
                "Sapato","Calça", "Bombeiro", "Brasil",
                "Parana", "Sexo", "Mauricio Boloia", "Aramis Boloia",
                "mauricio me traz um balde"
        };

        int numero = new Random().nextInt(palavras.length);
        palavrarandom.setText(palavras[numero]);
    }
}
