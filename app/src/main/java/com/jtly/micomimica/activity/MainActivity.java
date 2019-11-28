package com.jtly.micomimica.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jtly.micomimica.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private TextView palavrarandom;
    private Button gerar;

    private int palavra0;
    private int palavra1;
    private int palavra2;
    private int palavra3;
    private int palavra4;
    private Random random = new Random();
    private int categoria;

    private Set<Integer> set0 = new HashSet<>();
    private Set<Integer> set1 = new HashSet<>();
    private Set<Integer> set2 = new HashSet<>();
    private Set<Integer> set3 = new HashSet<>();
    private Set<Integer> set4 = new HashSet<>();

    private Integer[] profissaoResultado;
    private Integer[] esporteResultado;
    private Integer[] filmeResultado;
    private Integer[] objetoResultado;
    private Integer[] animalResultado;

    private String[] profissao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        palavrarandom = findViewById(R.id.txtPalavra);

        profissao = new String[]{
                "Advogado", "Bancario", "Desenvolvedor", "Atendente de Telemarketing",
                "Cantor", "Ator", "Jogardor de futebol", "Professor",
                "Motorista", "balconista", "Frentista"
        };
        for (int i=0;i<=profissao.length; i++){
            palavra0 = random.nextInt(profissao.length);
            set0.add(palavra0);
        }
       profissaoResultado = set0.toArray(new Integer[set0.size()]);

    }

    private void selecionaPalavraAleatoriamente(){


        String[] esporte = {
                "Futebol", "Golf", "Natação", "Maratonismo",
                "Automobilismo", "Ciclismo", "Crossfit", "MMA",
                "Baseball"
        };
        for (int i=0;i<=esporte.length; i++){
            palavra1 = random.nextInt(esporte.length);
            set1.add(palavra1);
        }
        profissaoResultado = set0.toArray(new Integer[set0.size()]);

        String[] filme = {
                "Lagoa azul", "Titanic", "Vingadores", "Esterminador do futuro",
                "A mulher de preto", "Anabelle", "Godzilla", "Velozes e furiosos",
                "Bastardos inglórios"
        };
        palavra2 = random.nextInt(filme.length);

        String[] objeto = {
                "Faca", "Tesoura", "Balde de flor", "Garrafa de agua",
                "Caneta", "Celular", "Mochila", "Poutrona",
                "Agulha"
        };
        palavra3 = random.nextInt(objeto.length);

        String[] animal = {
                "Cavalo", "Leão", "Cachorro", "Coelho",
                "Canguru", "Coala", "Zebra", "Ornitorrinco",
                "Baleia Azul"
        };
        palavra4 = random.nextInt(animal.length);

        //String[][] numRandom = {profissao, esporte, filme, objeto, animal};

        //categoria = new Random().nextInt(numRandom.length);

    }


    public void gerarPalavra(View view){
        selecionaPalavraAleatoriamente();
    }
}
