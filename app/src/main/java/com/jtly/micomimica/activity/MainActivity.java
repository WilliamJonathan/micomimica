package com.jtly.micomimica.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jtly.micomimica.R;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView palavrarandom;
    private Button gerar;
    private int palavra;
    private Boolean baralho;
    private List<String> profissao, esporte, filme, objeto, animal;
    private int aux0, aux1, aux2, aux3, aux4;
    private int categoria;
    private Boolean verificaProfissao, verificaEsporte, verificaFilme, verificaObjeto, verificaAnimal;

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

        Bundle bundle = getIntent().getExtras();
        verificaProfissao = bundle.getBoolean("profissao");
        verificaEsporte = bundle.getBoolean("esporte");
        verificaFilme = bundle.getBoolean("fime");
        verificaObjeto = bundle.getBoolean("objeto");
        verificaAnimal = bundle.getBoolean("animal");

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


        if (baralho){
            Collections.shuffle(profissao);
            Collections.shuffle(esporte);
            Collections.shuffle(filme);
            Collections.shuffle(objeto);
            Collections.shuffle(animal);
            baralho = false;
        }

    }

    private void profissao(){
        palavrarandom.setText(profissao.get(aux0));
        aux0 = aux0 + 1;
        /*if (aux0 >= profissao.size()){
            verificaProfissao = true;
        }*/
    }

    private void esporte(){
        palavrarandom.setText(esporte.get(aux1));
        aux1 = aux1 + 1;
        /*if (aux1 >= esporte.size()){
            verificaEsporte = true;
        }*/
    }

    private void filme(){
        palavrarandom.setText(filme.get(aux2));
        aux2 = aux2 + 1;
        /*if (aux2 >= filme.size()){
            verificaFilme = true;
        }*/
    }

    private void objeto(){
        palavrarandom.setText(objeto.get(aux3));
        aux3 = aux3 + 1;
        /*if (aux3 >= objeto.size()){
            verificaObjeto = true;
        }*/
    }

    private void animal(){
        palavrarandom.setText(animal.get(aux4));
        aux4 = aux4 + 1;
        /*if (aux4 >= animal.size()){
            verificaAnimal = true;
        }*/
    }


    private void selecionaPalavraAleatoriamente(){
        if (!baralho){
            //Collections.shuffle();
            categoria = new Random().nextInt(4);
            if (categoria == 0 && aux0 < profissao.size() && verificaProfissao){
                profissao();
            }else if (categoria == 1 && aux1 < esporte.size() && verificaEsporte){
                esporte();
            }else if (categoria == 2 && aux2 < filme.size() && verificaFilme){
                filme();
            }else if (categoria == 3 && aux3 < objeto.size() && verificaObjeto){
                objeto();
            }else if (categoria == 4 && aux4 < animal.size() && verificaAnimal){
                animal();
            }else {
                Toast.makeText(MainActivity.this, "Logica errada! Pense mais um pouco", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(MainActivity.this, "não deu", Toast.LENGTH_LONG).show();
        }
    }


    public void gerarPalavra(View view){
        selecionaPalavraAleatoriamente();
    }
}


       /*if (!baralho){
           categoria = new Random().nextInt(4);
           if (categoria == 0 && aux0 < profissao.size()){
               profissao();
           }else if (categoria == 1 && aux1 < esporte.size()){
               esporte();
           }else if (categoria == 2 && aux2 < filme.size()){
               filme();
           }else if (categoria == 3 && aux3 < objeto.size()){
               objeto();
           }else if (categoria == 4 && aux4 < animal.size()){
               animal();
           }else {

               Toast.makeText(MainActivity.this, "Logica errada! Pense mais um pouco", Toast.LENGTH_LONG).show();
           }
       }else{
           Toast.makeText(MainActivity.this, "não deu", Toast.LENGTH_LONG).show();
       }*/

        /*String[] lista = ArrayUtils.addAll(profissao, esporte);
        lista = ArrayUtils.addAll(lista, filme);*/
//String[][] numRandom = {profissao, esporte, filme, objeto, animal};

//categoria = new Random().nextInt(numRandom.length);
