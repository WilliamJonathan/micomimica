package com.jtly.micomimica.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.jtly.micomimica.R;

public class TipoJogoActivity extends AppCompatActivity {

    private CheckBox profissao, esporte, filme, objeto, animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_jogo);

        profissao = findViewById(R.id.checkBoxProfissao);
        esporte = findViewById(R.id.checkBoxEsportes);
        filme = findViewById(R.id.checkBoxFilmes);
        objeto = findViewById(R.id.checkBoxObjetos);
        animal = findViewById(R.id.checkBoxAnimais);

    }

    public void verificaCheck(){
        if (profissao.isChecked() || esporte.isChecked() || filme.isChecked() || objeto.isChecked() || animal.isChecked()){
            Intent intent = new Intent(TipoJogoActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(TipoJogoActivity.this, "Selecione uma ou mais\ncategorias para continuar!", Toast.LENGTH_LONG).show();
        }
    }

    public void iniciaJogo(View view){
        verificaCheck();
    }
}
