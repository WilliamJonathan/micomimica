package com.jtly.micomimica.activity;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import android.app.Activity;
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
    private Boolean pararTimer = false;
    private int cont, eq, pontos1, pontos2, tempo;
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
        //tempo = 60;

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

        //verifica de as palavras já foram embaralhadas
        if (!baralho){
            Collections.shuffle(Arrays.asList(todasCategorias));
            baralho = true;
        }

    }

    /*private CountDownTimer timer(){
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
        //return tempo;
        return null;
    }*/


    private void iniciarTimer(){
        pararTimer = false;
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable).start();
    }

    class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i=60; i >=0; i--){
                tempo = i;
                if (pararTimer)
                    return;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView timer = dialogPalavra.findViewById(R.id.txtTimer);
                        timer.setText(String.valueOf(tempo));
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    //Metodo que insere as palavras no textView
    private void selecionaPalavraAleatoriamente(){
        if (baralho){
            if (cont<todasCategorias.length){
                LayoutInflater layoutInflater = getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.dialog_principal, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(view);
                builder.setCancelable(false);
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
                        pararTimer = true;
                        dialogPalavra.cancel();
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
                        pararTimer = true;
                        dialogPalavra.cancel();
                    }
                });
                dialogPalavra.show();
                TextView palavra = dialogPalavra.findViewById(R.id.txtPalavra);
                palavra.setText(todasCategorias[cont]);
                iniciarTimer();
                cont = cont+1;
            }else {
                Toast.makeText(MainActivity.this, "Fim das palavras!", Toast.LENGTH_LONG).show();
                pararTimer = true;
            }

        }else {
            Toast.makeText(MainActivity.this, "Embaralhe de novo!", Toast.LENGTH_LONG).show();
        }
        /*String string = String.valueOf(todasCategorias.length);
        Toast.makeText(MainActivity.this, string, Toast.LENGTH_LONG).show();*/
    }

    //alertDialog do fim do jogo
    private void fimDoJogo(){
        pararTimer = true;
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
        pararTimer = true;
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
                "Capoeira","Lacrosse","hóquei no gelo","Esgrima","Vôlei","Críquete","Arquearia","Futebol","Beisebol","Golfe",
                "Sumô","Basquete","Futebol Americano","Automobilismo","Tênis","Tênis de mesa","Motocross","Surf","Natação","Crossfit",
                "Musculação","Ginástica artística","Alpinismo","Boxe","Handebol","Curling","Bobsled","Esqui estilo livre","Patinação artística",
                "Patinação de velocidade","Skeleton","Snowboard","Escalada","Mountain bike","Rapel","Paraquedismo","Down Hill","Surf","Rafting",
                "Arborismo","Taekwondo","Kung-fu","Jiu-jitsu","Karatê","Judô","Muay Thai","Futsal","Ciclismo","Maratonismo"
        };
        if (verificaEsporte){
            esporteLista = esporte;
        }else {
            esporteLista = new String[]{};
        }
    }

    private void filme(){
        filme = new String[]{
                "Um sonho de Liberdade","O Poderoso Chefão","O Poderoso Chefão II","Batman - O Cavaleiro das Trevas","12 Homens e uma Sentença",
                "A Lista de Schindler","Pulp Fiction: Tempo de Violência","O Senhor dos Anéis: O Retorno do Rei","Três homens em Conflito","Clube da Luta",
                "O Senhor dos Anéis: A Sociedade do Anel","Forrest Gump: O Contador de Histórias","Star Wars: Episódio V - O Império Contra-Ataca","Inception",
                "O Senhor dos Anéis: As Duas Torres","Um Estranho no Ninho","Os Bons Companheiros","Matrix","Os Sete Samurais",
                "Guerra nas Estrelas - Uma Nova Esperança","Cidade de Deus","Seven: Os Sete Crimes Capitais","O Silêncio dos Inocentes",
                "A Felicidade Não se Compra","A Vida é Bela","Os Suspeitos","O Profissional","O Resgate do Soldado Ryan","A Viagem de Chihiro",
                "A Outra História Americana","Era uma Vez no Oeste","Interestelar","À Espera de um Milagre","Psicose","Casablanca","Luzes da Cidade",
                "Intocáveis","Os Tempos Modernos","O Pianista","Indiana Jones e Os Caçadores da Arca Perdida","Os Infiltrados","Janela Indiscreta",
                "O Exterminador do Futuro 2: O Julgamento Final","De Volta para o Futuro","Whiplash: Em Busca da Perfeição","Gladiador","O Grande Truque",
                "O Rei Leão","Amnésia","Apocalypse Now","Alien, o Oitavo Passageiro","O Grande Ditador","Crepúsculo dos Deuses","Cinema Paradiso",
                "Dr. Fantástico","A Vida dos Outros","O Túmulo dos Vagalumes","Glória Feita de Sangue","Django Livre","O Iluminado","Wall-E","Beleza Americana",
                "Blade Runner 2049","Batman: O Cavaleiro das Trevas Ressurge","Princesa Mononoke","Oldboy","Aliens, O Resgate","Testemunha de Acusação",
                "Era Uma Vez na América","O barco: Inferno no Mar","Dangal","O Cidadão Kane","Um Corpo que Cai","Intriga Internacional",
                "Star Wars: Episódio VI - O Retorno de Jedi","Coração Valente","Cães de Aluguel","M, O Vampiro de Dusseldorf","Réquiem Para um Sonho",
                "Como Estrelas na Terra","O Fabuloso Destino de Amélie Poulain","Kimi no na wa.","Laranja Mecânica","Lawrence da Arábia","Amadeus",
                "Pacto de Sangue","Brilho Eterno de uma Mente Sem Lembranças","Taxi Driver","O Sol é Para Todos","Nascido para Matar",
                "2001: Uma Odisseia no Espaço","Cantando na Chuva","Toy Story","3 Idiotas","Golpe de Mestre","Toy Story 3","Bastardos Inglórios",
                "Ladrões de Bicicletas","O Garoto","dunkirk","Snatch: Porcos e Diamantes","Monty Python em Busca do Cálice Sagrado","Gênio Indomável",
                "A Caça","Por Uns Dólares a Mais ","Los Angeles: Cidade Proibida","Scarface","Se Meu Apartamento Falasse","Metrópolis ","A Separação",
                "Rashomon","Indiana Jones e a Última Cruzada","A Malvada","Yojimbo - O Guarda-Costas","Meu Pai e Meu Filho","Up: Altas Aventuras",
                "Batman Begins","Quanto Mais Quente Melhor","O Tesouro da Sierra Madre","Os Imperdoáveis","A Queda! As Últimas Horas de Hitler",
                "Duro de Matar","Touro Indomável","Fogo Contra Fogo","Filhos do Paraíso","O 3º Homem","Fugindo do Inferno","Viver","Chinatown",
                "O Labirinto do Fauno","Meu Amigo Totoro","Incêndios","Ran","Julgamento em Nuremberg","Em Busca do Ouro","O Segredo dos seus Olhos",
                "Divertida Mente","O Castelo Animado","Sindicato de Ladrões","A Ponte do Rio Kwai","O Sétimo Selo","O Quarto de Jack",
                "Jogos, Trapaças e Dois Canos Fumegantes","A Mulher Faz o Homem","Uma Mente Brilhante","Cassino","Blade Runner, o Caçador de Androides",
                "O Homem Elefante","V de Vingança","O Lobo de Wall Street"
        };
        if (verificaFilme){
            filmeLista = filme;
        }else {
            filmeLista = new String[]{};
        }
    }

    private void objeto(){
        objeto = new String[]{
                "Despertador","Cama","Mesa de cabeceira","Cobertor","Beliche","Lustre","cama de casal","Penteadeira","Edredom","Cabide",
                "Cabeceira","Abajur","Colchão","Quadro","Travesseiro","Fronha","Lençol","Estante","Cama de solteiro","Papel de parede","Guarda roupas",
                "Vassoura","Balde","Cafeteira","Xícara","Armário","Lava louças","Garfo","Fruteira","Copo","Ferro de passar roupa","Tábua de passar roupa",
                "Bucha","Faca","Microondas","Esfregão","Caneca","Forno","Frigideira","Prato","Ralo","Panela","Geladeira","Pia","Colher","Banco","Fogão",
                "Rodo","Toalha de mesa","Torneira","Aspirador de pó","Vaso","Máquina de lavar roupas","Poltrona","Cinzeiro","Persianas","Estante","Cadeira",
                "Relógio","Bengaleiro","Computador","Sofá","Cortinas","Almofada","Mesa","Escrivaninha","Maçaneta","Capacho","Lareira","Lenha","Lâmpada",
                "Radiador","Rádio","Tapete","Aparador","Interruptor","Telefone","Televisão","Banheira","Perfume","Pente","Condicionador","Cotonete","Fio dental",
                "Desodorante","Fraldas","Escova de cabelo","Espelho","Creme hidratante","Absorvente","Lâmina de barbear","Shampoo","Creme de barbear","Chuveiro",
                "Sabonete","Esponja","Papel higiênico","Escova de dente","Pasta de dente","Toalha","Secador de cabelo","Sapato","Calculadora",
                "Maquina de escrever","Balde de agua","Vaso de flor","Sapatos","Calça jeans","Meia calça","Toalha de rosto","Pano de prato","Fones de ouvido",
                "Capacete","Celular","Violão","Guitarra","Skate","Piano","Teclado"
        };
        if (verificaObjeto){
            objetoLista = objeto;
        }else {
            objetoLista = new String[]{};
        }
    }

    private void animal(){
        animal = new String[]{
                "Jacaré","tamanduá","Antílope","Castor","Urso","Camelo","Crocodilo","Veado","Águia","Elefante","Peixe","Raposa","Gazela","Girafa",
                "Gorila","Hipopótamo","Hiena","Chacal","Canguru","Coala","Leopardo","Leão","Arara","Suricate","Macaco","Orangotango","Avestruz","Lontra",
                "Jaguar","Coruja","Panda","Pantera","Pavão","Pinguim","Urso polar","Rinoceronte","Cobra","Tigre","Tartaruga marinha","Tartaruga terrestre",
                "Morsa","Zebra","Gato","Hamster","Coelho","Cachoro","Baleia Azul","Tubarão Branco","Tubarão Tigre","Tubarão Martelo","Baleia Jubarte",
                "Baleia Cachalote","ornitorrinco","Caguru","Cavalo","Boi","Vaca","Avestruz","Pica-pau","Morcego","Beija-flor","Peixe voador","Falcão",
                "Águia","Papagaio","Bem-te-vi","João-de-barro","Rolinha","Coleiro","Pardal","Cegonha","Sapo Boi","Sapo","Coiote","Puma","Jaguar","Leopardo",
                "Guepardo","Orangontango","Macaco-aranha","Lula Gigante","Camarão","Lagosta","Carangueijo","Golfinho","Abelha","Besouro","Borboleta",
                "Vaga-lume","Cigarra","Cupim","Mosca","Gafanhoto","Formiga","Grilo","Piolho","Pulga","Pernilongo","Lagarta","Borrachudo","Galinha","Galo",
                "Pato","Ganço","Marreco","Peixe-espada","Peixe-palhaço"
        };
        if (verificaAnimal){
            animalLista = animal;
        }else {
            animalLista = new String[]{};
        }
    }
}


