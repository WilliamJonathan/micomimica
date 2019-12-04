package com.jtly.micomimica.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

    private TextView palavrarandom, equipe1, equipe2;
    private Button gerar;
    private int palavra;
    private Boolean baralho;
    private String[] profissao, esporte, filme, objeto, animal, todasCategorias;
    private String[] profissaoLista, esporteLista, filmeLista, objetoLista, animalLista;
    private String eq1, eq2;
    private Boolean verificaProfissao, verificaEsporte, verificaFilme, verificaObjeto, verificaAnimal;
    private int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        palavrarandom = findViewById(R.id.txtPalavra);
        equipe1 = findViewById(R.id.txtEquipe1);
        equipe2 = findViewById(R.id.txtEquipe2);

        baralho = false;
        cont = 0;

        //Verifica quais categorias foram selecionadas no CheckBox
        Bundle bundle = getIntent().getExtras();
        verificaProfissao = bundle.getBoolean("profissao");
        verificaEsporte = bundle.getBoolean("esporte");
        verificaFilme = bundle.getBoolean("fime");
        verificaObjeto = bundle.getBoolean("objeto");
        verificaAnimal = bundle.getBoolean("animal");

        eq1 = bundle.getString("eq1");
        equipe1.setText(eq1 + ": ");
        eq2 = bundle.getString("eq2");
        equipe2.setText(eq2 + ": ");

        alertDialogBoasVindas();



        /**
         * Grupo de Arrays com todas as palavras divididas em categorias
         * */
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

        /**
         * Metodos que verificam quais categorias foram selecionadas
         * */
        profissao();
        esporte();
        filme();
        objeto();
        animal();

        //lista com todas as palavras de acordo com a categoria
        todasCategorias = ArrayUtils.addAll(profissaoLista);
        todasCategorias = ArrayUtils.addAll(esporteLista, todasCategorias);
        todasCategorias = ArrayUtils.addAll(filmeLista, todasCategorias);
        todasCategorias = ArrayUtils.addAll(objetoLista, todasCategorias);
        todasCategorias = ArrayUtils.addAll(animalLista, todasCategorias);

        //verifica de as palavras já forqam embaralhadas
        if (!baralho){
            Collections.shuffle(Arrays.asList(todasCategorias));
            baralho = true;
        }


    }

    /**
     * Metodos que inserem palavras na lista principal de acordo com a categoria selecionada
     * */

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

    //Metodo que insere as palavras no textView
    private void selecionaPalavraAleatoriamente(){
        if (baralho){
            if (cont<todasCategorias.length){
                palavrarandom.setText(todasCategorias[cont]);
                cont = cont+1;
            }else {
                Toast.makeText(MainActivity.this, "Fim das palavras!", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(MainActivity.this, "Embaralhe de novo!", Toast.LENGTH_LONG).show();
        }
        /*String string = String.valueOf(todasCategorias.length);
        Toast.makeText(MainActivity.this, string, Toast.LENGTH_LONG).show();*/
    }

    private void alertDialogEncerrarJogo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja realmente sair da partida?");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void alertDialogBoasVindas(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Vamos Começar!");
        builder.setMessage("Equipe "+eq1+"\n"+"e\n"+"Equipe "+eq2);
        builder.setCancelable(false);
        builder.setPositiveButton("Começar jogo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        alertDialogEncerrarJogo();
        //super.onBackPressed();
    }

    public void gerarPalavra(View view){
        selecionaPalavraAleatoriamente();
    }
}


