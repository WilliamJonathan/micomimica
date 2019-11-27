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
    private int palavra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        palavrarandom = findViewById(R.id.txtPalavra);

    }

    public void gerarPalavraRandomica(){
        String[] profissao = {
                "Advogado","Bancario", "Desenvolvedor", "Atendente de Telemarketing",
                "Cantor", "Ator", "Jogardor de futebol", "Professor",
                "Motorista", "balconista", "Frentista"
        };

        String[] esporte = {
                "Futebol", "Golf", "Natação", "Maratonismo",
                "Automobilismo", "Ciclismo", "Crossfit", "MMA",
                "Baseball"
        };

        String[] filme = {
                "Lagoa azul", "Titanic", "Vingadores", "Esterminador do futuro",
                "A mulher de preto", "Anabelle", "Godzilla", "Velozes e furiosos",
                "Bastardos inglórios"
        };

        String[] objeto = {
                "Faca", "Tesoura", "Balde de flor", "Garrafa de agua",
                "Caneta", "Celular", "Mochila", "Poutrona",
                "Agulha"
        };

        String[] animal = {
                "Cavalo", "Leão", "Cachorro", "Coelho",
                "Canguru", "Coala", "Zebra", "Ornitorrinco",
                "Baleia Azul"
        };

        String[][] numRandom = {profissao, esporte, filme, objeto, animal};

        int categoria = new Random().nextInt(numRandom.length);

        switch (categoria){
            case 0:
                palavra = new Random().nextInt(profissao.length);
                break;
            case 1:
                palavra = new Random().nextInt(esporte.length);
                break;
            case 2:
                palavra = new Random().nextInt(filme.length);
                break;
            case 3:
                palavra = new Random().nextInt(objeto.length);
                break;
            case 4:
                palavra = new Random().nextInt(animal.length);
                break;
        }

        palavrarandom.setText(numRandom[categoria][palavra]);
    }

    public void gerarPalavra(View view){
        gerarPalavraRandomica();
    }
}
