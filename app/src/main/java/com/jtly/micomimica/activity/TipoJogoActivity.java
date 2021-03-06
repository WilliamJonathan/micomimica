package com.jtly.micomimica.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.jtly.micomimica.R;

public class TipoJogoActivity extends AppCompatActivity {

    private CheckBox profissao, esporte, filme, objeto, animal;
    private Boolean p, e, f, o, a;
    private EditText equipe1, equipe2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_jogo);

        p = false;
        e = false;
        f = false;
        o = false;
        a = false;

        profissao = findViewById(R.id.checkBoxProfissao);
        esporte = findViewById(R.id.checkBoxEsportes);
        filme = findViewById(R.id.checkBoxFilmes);
        objeto = findViewById(R.id.checkBoxObjetos);
        animal = findViewById(R.id.checkBoxAnimais);
        equipe1 = findViewById(R.id.edtEquipe1);
        equipe2 = findViewById(R.id.edtEquipe2);

    }

    private Boolean profissao(){
        if (profissao.isChecked()){
            return true;
        }else{
           return false;
        }
    }

    private Boolean esporte(){
        if (esporte.isChecked()){
            return true;
        }else{
            return false;
        }
    }

    private Boolean filme(){
        if (filme.isChecked()){
            return true;
        }else{
            return false;
        }
    }

    private Boolean objeto(){
        if (objeto.isChecked()){
            return true;
        }else{
            return false;
        }
    }

    private Boolean animal(){
        if (animal.isChecked()){
            return true;
        }else{
            return false;
        }
    }

    private void verificaCheck(){
        String eq1 = equipe1.getText().toString();
        String eq2 = equipe2.getText().toString();
        if (profissao.isChecked() || esporte.isChecked() || filme.isChecked() || objeto.isChecked() || animal.isChecked()){
            if (!eq1.isEmpty() && !eq2.isEmpty()){
                Intent intent = new Intent(TipoJogoActivity.this, MainActivity.class);
                intent.putExtra("profissao", profissao());
                intent.putExtra("esporte", esporte());
                intent.putExtra("filme", filme());
                intent.putExtra("objeto", objeto());
                intent.putExtra("animal", animal());
                intent.putExtra("eq1", eq1);
                intent.putExtra("eq2", eq2);
                startActivity(intent);
            }else {
                Toast.makeText(TipoJogoActivity.this, "Preencha o nome das equipes!!", Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(TipoJogoActivity.this, "Selecione uma ou mais\ncategorias para continuar!", Toast.LENGTH_LONG).show();
        }
    }

    public void iniciaJogo(View view){
        verificaCheck();
        //verificaProfissao(profissao.isChecked());
    }
}
