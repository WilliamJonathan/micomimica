package com.jtly.micomimica.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jtly.micomimica.R;

import org.apache.commons.lang3.ArrayUtils;

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
    private String[] profissao, esporte, filme, objeto, animal, todasCategorias;
    private String[] profissaoLista, esporteLista, filmeLista, objetoLista, animalLista;
    private int aux0, aux1, aux2, aux3, aux4;
    private int categoria;
    private Boolean verificaProfissao, verificaEsporte, verificaFilme, verificaObjeto, verificaAnimal;
    private int cont1, cont2;

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


        profissao = new String[]{
                "Advogado", "Bancario", "Desenvolvedor", "Atendente de Telemarketing",
                "Cantor", "Ator", "Jogardor de futebol", "Professor",
                "Motorista", "balconista", "Frentista"
        };

        esporte = new String[]{
                "Futebol", "Golf", "Natação", "Maratonismo",
                "Automobilismo", "Ciclismo", "Crossfit", "MMA",
                "Baseball"
        };

        filme = new String[]{
                "Lagoa azul", "Titanic", "Vingadores", "Esterminador do futuro",
                "A mulher de preto", "Anabelle", "Godzilla", "Velozes e furiosos",
                "Bastardos inglórios"
        };

        objeto = new String[]{
                "Faca", "Tesoura", "Balde de flor",
                "Garrafa de agua", "Caneta", "Celular",
                "Mochila", "Poutrona", "Agulha"
        };

        animal = new String[]{
                "Cavalo", "Leão", "Cachorro",
                "Coelho", "Canguru", "Coala",
                "Zebra", "Ornitorrinco", "Baleia Azul"
        };

        profissao();
        esporte();
        filme();
        objeto();
        animal();

        //lista abstrata
        todasCategorias = ArrayUtils.addAll(profissaoLista);
        todasCategorias = ArrayUtils.addAll(esporteLista, todasCategorias);
        todasCategorias = ArrayUtils.addAll(filmeLista, todasCategorias);
        todasCategorias = ArrayUtils.addAll(objetoLista, todasCategorias);
        todasCategorias = ArrayUtils.addAll(animalLista, todasCategorias);

        
        if (baralho){
            /*Collections.shuffle(profissao);
            Collections.shuffle(esporte);
            Collections.shuffle(filme);
            Collections.shuffle(objeto);
            Collections.shuffle(animal);*/
            baralho = false;
        }


    }

    private void profissao(){
        if (verificaProfissao){
            profissaoLista = profissao;
        }else {
            profissaoLista = new String[]{};
        }
    }

    private void esporte(){
        if (verificaEsporte){
            esporteLista = esporte;
        }else {
            esporteLista = new String[]{};
        }
    }

    private void filme(){
        if (verificaFilme){
            filmeLista = filme;
        }else {
            filmeLista = new String[]{};
        }
    }

    private void objeto(){
        if (verificaObjeto){
            objetoLista = objeto;
        }else {
            objetoLista = new String[]{};
        }
    }

    private void animal(){
        if (verificaAnimal){
            animalLista = animal;
        }else {
            animalLista = new String[]{};
        }
    }


    private void selecionaPalavraAleatoriamente(){
        String string = String.valueOf(todasCategorias.length);
        Toast.makeText(MainActivity.this, string, Toast.LENGTH_LONG).show();
    }


    public void gerarPalavra(View view){
        selecionaPalavraAleatoriamente();
    }
}

/*if (!baralho){
            //int teste =  Collections.shuffle(todasCategorias);
            categoria = new Random().nextInt(todasCategorias.size());

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
                if (categoria == 0 && aux0>= profissao.size()){
                    selecionaPalavraAleatoriamente();
                }else if (categoria == 1 && aux1>= esporte.size()){
                    selecionaPalavraAleatoriamente();
                }else if (categoria == 2 && aux2>= filme.size()){
                    selecionaPalavraAleatoriamente();
                }else if (categoria == 3 && aux3>= objeto.size()){
                    selecionaPalavraAleatoriamente();
                }else if (categoria == 4 && aux4>= animal.size()){

                }else if (aux0 >= profissao.size()
                        && aux1>= esporte.size()
                        && aux2>= filme.size()
                        && aux3>= objeto.size()
                        && aux4>= animal.size()){
                    Toast.makeText(MainActivity.this, "Fim das palavras", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "Logica errada! Pense mais um pouco", Toast.LENGTH_LONG).show();
                }
                //Toast.makeText(MainActivity.this, "Logica errada! Pense mais um pouco", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(MainActivity.this, "não deu", Toast.LENGTH_LONG).show();
        }*/


//String[][] numRandom = {profissao, esporte, filme, objeto, animal};

//categoria = new Random().nextInt(numRandom.length);
