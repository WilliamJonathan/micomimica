package com.jtly.micomimica.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
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

    private TextView timer, palavra, equipe1, equipe2;
    private Button gerar;
    private Boolean baralho;
    private String[] profissao, esporte, filme, objeto, animal, todasCategorias;
    private String[] profissaoLista, esporteLista, filmeLista, objetoLista, animalLista;
    private String eq1, eq2;
    private Boolean verificaProfissao, verificaEsporte, verificaFilme, verificaObjeto, verificaAnimal;
    private int cont, eq, pontos1, pontos2;
    private long tempo;
    private AlertDialog dialogPalavra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //palavra = findViewById(R.id.txtPalavra);
        //timer = findViewById(R.id.txtTimer);
        equipe1 = findViewById(R.id.txtEquipe1);
        equipe2 = findViewById(R.id.txtEquipe2);
        gerar = findViewById(R.id.btnGerar);
        equipe1.setVisibility(View.GONE);
        equipe2.setVisibility(View.GONE);

        baralho = false;
        cont = 0;
        pontos1 = 0;
        pontos2 = 0;
        tempo = 60;

        //Verifica quais categorias foram selecionadas no CheckBox
        Bundle bundle = getIntent().getExtras();
        verificaProfissao = bundle.getBoolean("profissao");
        verificaEsporte = bundle.getBoolean("esporte");
        verificaFilme = bundle.getBoolean("filme");
        verificaObjeto = bundle.getBoolean("objeto");
        verificaAnimal = bundle.getBoolean("animal");

        eq1 = bundle.getString("eq1");
        equipe1.setText(eq1 + ": "+pontos1);
        eq2 = bundle.getString("eq2");
        equipe2.setText(eq2 + ": "+pontos2);

        alertDialogBoasVindas();

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

    private long timer(){
        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long l) {
                tempo = l / 1000;
                TextView timer = dialogPalavra.findViewById(R.id.txtTimer);
                timer.setText(String.valueOf(tempo));
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "Fim do tempo", Toast.LENGTH_SHORT).show();
            }
        }.start();
        return tempo;
    }

    //Metodo que insere as palavras no textView
    private void selecionaPalavraAleatoriamente(){
        if (baralho){
            if (cont<todasCategorias.length){
                LayoutInflater layoutInflater = getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.dialog_principal, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(view);
                dialogPalavra = builder.create();
                //btn positivo
                view.findViewById(R.id.btnAcertou).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (eq == 0){
                            gerar.setText("Proximo a jogar\n"+eq2);
                            pontos1 = pontos1 + 1;
                            if (pontos1 >= 20){
                                fimDoJogo();
                            }else {
                                equipe1.setText(eq1 + ": "+pontos1);
                                eq = 1;
                            }
                        }else {
                            gerar.setText("Proximo a jogar\n"+eq1);
                            pontos2 = pontos2 + 1;
                            if (pontos2 >= 20){
                                fimDoJogo();
                            }else {
                                equipe2.setText(eq2 + ": "+pontos2);
                                eq = 0;
                            }
                        }
                        //dimis
                        dialogPalavra.dismiss();
                    }
                });
                //btn negativo
                view.findViewById(R.id.btnErrou).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (eq == 0){
                            gerar.setText("Proximo a jogar\n"+eq2);
                            eq = 1;
                        }else {
                            gerar.setText("Proximo a jogar\n"+eq1);
                            eq = 0;
                        }
                        dialogPalavra.dismiss();
                    }
                });
                dialogPalavra.show();
                TextView palavra = dialogPalavra.findViewById(R.id.txtPalavra);
                palavra.setText(todasCategorias[cont]);
                timer();
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

    //alertDialog do fim do jogo
    private void fimDoJogo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Vitoria!");
        if (eq == 0){
            builder.setMessage("Equipe "+eq1+" venceu!");
        }else if (eq == 1){
            builder.setMessage("Equipe "+eq2+" venceu!");
        }else {
            builder.setMessage("Desculpe houve um erro no placar");
        }
        builder.setCancelable(false);
        builder.setPositiveButton("Nova partida", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        TextView textView = dialog.findViewById(android.R.id.message);
        textView.setTextSize(40);
    }

    //AlerDialog abre quando o jogador deseja encerrar a partida
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

    //AlertDialog abre e da boas vindas a nova partida
    private void alertDialogBoasVindas(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Vamos Começar!");
        builder.setMessage("Equipe "+eq1+"\n"+"e\n"+"Equipe "+eq2);
        builder.setCancelable(false);
        builder.setPositiveButton("Começar jogo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                equipe1.setVisibility(View.VISIBLE);
                equipe2.setVisibility(View.VISIBLE);
                gerar.setText("Primeiro a jogar\n"+eq1);
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        eq = 0;
    }


    @Override
    public void onBackPressed() {
        alertDialogEncerrarJogo();
        //super.onBackPressed();
    }

    //clique do botão que gera a palavra
    public void gerarPalavra(View view){
        selecionaPalavraAleatoriamente();
    }

    /**
     * Metodos que inserem palavras na lista principal de acordo com a categoria selecionada
     * */
    private void profissao(){
        profissao = new String[]{
                "Etiquetador","Estampador","Jornalista","Blogueiro","Decorador","Barman","Chaveiro","Jardineiro","Alpinista Industrial",
                "Bioinformacionista","Diretor Industrial","Barista","Bombeiro Civil","Pizzaiolo","Colchoeiro","Caldeireiro","Biotecnólogo",
                "Comissário de Avarias","Conferente","Chefe de Cozinha","Pintor","Bilheteiro","Cenotécnico","Piloto de Avião","Documentador",
                "Colorista de Papel","Dentista","Fotógrafo","Acupunturista","Fonoaudiólogo","Camareiro","Bioquímico","Executivo de Vendas",
                "Comprador","Dedetizador","Adestrador","Arte-finalista","Maquinista de Trem","Maquiador","Chefe de Confeitaria","Cabista",
                "Escrevente","Paisagista","Digitador","Escriturário","Acrilista","Estatístico Trainee","Encadernador","Ecólogo","Florista",
                "Caracterizador","Pedreiro","Colorista","Alinhador","Forneiro","Entregador","Farmacêutico","Desenhista","Comissário de Bordo",
                "Economista","Programador","Estilista","Doméstica","Coletor de Lixo","Carpinteiro","Médico","Nutricionista","Padeiro","Bordador",
                "Diretor Pedagógico","Marceneiro","Degustador","Business Partner","Cartazista","Back Office de Vendas","Duteiro","Biologista",
                "Frentista","Estatístico","Colorista Têxtil","Fracionador","Cobrador de Ônibus","Administrador","Manobrista","Depiladora","Calceteiro",
                "Caseiro","Carteiro","Cinegrafista","Biomédico","Ferramenteiro","Biólogo","Borracheiro","Comunicador social","Diretor Hospitalar",
                "Confeiteiro","Adesivador","Amarrador","Comprador Técnico","Arrematador","Cenógrafo","Alfaiate","Fresador","DJ","Bloquista","Artesão",
                "Articulador Social","Balanceiro","Ascensorista","Churrasqueiro","Comprador de Medicamentos","Motorista","Cliente Oculto",
                "Bombeiro Industrial","Executivo de Contas","Chapeiro","Colocador de Papel de Parede","Brigadista","Bibliotecário","Extrusor",
                "Berçarista","Marmorista","Ergonomista","Back Office","Chefe de Costura","Piloto de Helicóptero","Piloto","Panfleteiro","Blaster",
                "Cilindrista","Calculista","Booker","Chefe de Compras","Concierge","Coach","Bamburista","Divulgador","Caixa de Banco","Esteticista",
                "Marinheiro","Azulejista","Arquivista","Estoquista","Classificador de Grãos","Estofador","Empacotador","Jornaleiro","Palestrante",
                "Chefe de Bar","Ferreiro","Almoxarife","Diagramador","Babá","Coletor de Amostras","Cabeleireiro","Encanador"
        };
        if (verificaProfissao){
            profissaoLista = profissao;
        }else {
            profissaoLista = new String[]{};
        }
    }

    private void esporte(){
        esporte = new String[]{
                "Futebol", "Golf", "Natação", "Maratonismo",
                "Automobilismo", "Ciclismo", "Crossfit", "MMA",
                "Baseball"
        };
        if (verificaEsporte){
            esporteLista = esporte;
        }else {
            esporteLista = new String[]{};
        }
    }

    private void filme(){
        filme = new String[]{
                "Lagoa azul", "Titanic", "Vingadores", "Esterminador do futuro",
                "A mulher de preto", "Anabelle", "Godzilla", "Velozes e furiosos",
                "Bastardos inglórios"
        };
        if (verificaFilme){
            filmeLista = filme;
        }else {
            filmeLista = new String[]{};
        }
    }

    private void objeto(){
        objeto = new String[]{
                "Faca", "Tesoura", "Balde de flor",
                "Garrafa de agua", "Caneta", "Celular",
                "Mochila", "Poutrona", "Agulha"
        };
        if (verificaObjeto){
            objetoLista = objeto;
        }else {
            objetoLista = new String[]{};
        }
    }

    private void animal(){
        animal = new String[]{
                "Cavalo", "Leão", "Cachorro",
                "Coelho", "Canguru", "Coala",
                "Zebra", "Ornitorrinco", "Baleia Azul"
        };
        if (verificaAnimal){
            animalLista = animal;
        }else {
            animalLista = new String[]{};
        }
    }
}


