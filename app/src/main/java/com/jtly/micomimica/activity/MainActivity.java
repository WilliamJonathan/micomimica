package com.jtly.micomimica.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jtly.micomimica.R;

import org.apache.commons.lang3.ArrayUtils;

import java.io.Console;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private TextView palavrarandom;
    private Button gerar;
    private int palavra;
    private Boolean baralho;

    private List<String> profissao;
    private List<String> esporte;
    private List<String> filme;
    private List<String> objeto;
    private List<String> animal;

    private int aux0;
    private int aux1;
    private int aux2;
    private int aux3;
    private int aux4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        palavrarandom = findViewById(R.id.txtPalavra);
        baralho = true;
        aux0 = 0;
        aux1 = 0;
        aux2 = 0;
        aux3 = 0;
        aux4 = 0;

        profissao = Arrays.asList("Advogado", "Bancario", "Desenvolvedor", "Atendente de Telemarketing",
                "Cantor", "Ator", "Jogardor de futebol", "Professor",
                "Motorista", "balconista", "Frentista");

        esporte = Arrays.asList(
                "Futebol", "Golf", "Natação", "Maratonismo",
                "Automobilismo", "Ciclismo", "Crossfit", "MMA",
                "Baseball"
        );


        filme = Arrays.asList(
                "Lagoa azul", "Titanic", "Vingadores", "Esterminador do futuro",
                "A mulher de preto", "Anabelle", "Godzilla", "Velozes e furiosos",
                "Bastardos inglórios"
        );

        objeto = Arrays.asList(
                "Faca", "Tesoura", "Balde de flor", "Garrafa de agua",
                "Caneta", "Celular", "Mochila", "Poutrona",
                "Agulha"
        );

        animal = Arrays.asList(
                "Cavalo", "Leão", "Cachorro", "Coelho",
                "Canguru", "Coala", "Zebra", "Ornitorrinco",
                "Baleia Azul"
        );

    }

    private void selecionaPalavraAleatoriamente(){

       if (baralho){
           Collections.shuffle(profissao);
           Collections.shuffle(esporte);
           Collections.shuffle(filme);
           Collections.shuffle(objeto);
           Collections.shuffle(animal);
           baralho = false;
       }else if (!baralho){
           int categoria = new Random().nextInt(4);

           switch (categoria){
               case 0:
                   palavrarandom.setText(profissao.get(aux0));
                   aux0 = aux0 + 1;
                   break;
               case 1:
                   palavrarandom.setText(esporte.get(aux1));
                   aux1 = aux1 + 1;
                   break;
               case 2:
                   palavrarandom.setText(objeto.get(aux2));
                   aux2 = aux2 + 1;
                   break;
               case 3:
                   palavrarandom.setText(filme.get(aux3));
                   aux3 = aux3 + 1;
                   break;
               case 4:
                   palavrarandom.setText(animal.get(aux4));
                   aux4 = aux4 + 1;
                   break;

               default:
                   Toast.makeText(MainActivity.this, "fim de jogo", Toast.LENGTH_LONG).show();
                   break;
           }
       }else{
           Toast.makeText(MainActivity.this, "não deu", Toast.LENGTH_LONG).show();
       }

        /*String[] lista = ArrayUtils.addAll(profissao, esporte);
        lista = ArrayUtils.addAll(lista, filme);*/
        //String[][] numRandom = {profissao, esporte, filme, objeto, animal};

        //categoria = new Random().nextInt(numRandom.length);

    }


    public void gerarPalavra(View view){
        selecionaPalavraAleatoriamente();
    }
}
