package com.example.jhonatasrm.jogodamemoria;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Collections;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton carta1, carta2, carta3, carta4, carta5, carta6,
            carta7, carta8, carta9, carta10, carta11, carta12;
    private int cartaId1, cartaId2, cartaId3, cartaId4, cartaId5, cartaId6,
            cartaId7, cartaId8, cartaId9, cartaId10, cartaId11, cartaId12;
    private ImageButton primeiraCartaVirada;
    private ImageButton segundaCartaVirada;
    private ImageButton[] cartas;
    private int imagens[];
    private int cartasViradas;
    private int pontos = 0;
    TextView pontosCartas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        configuracaoInicial();
        adicionaCartas();
        mostraCartas();
        escondeCartas();
    }

    //faz a verificação utilizando o evento onClick
    @Override
    public void onClick(View view) {
        ImageButton cartaTocada = (ImageButton) view;

        if (cartasViradas == 0) {
            primeiraCartaVirada = cartaTocada;
            viraCarta(cartaTocada);
            cartasViradas = 1;
            primeiraCartaVirada.setClickable(false);

        } else {
            segundaCartaVirada = cartaTocada;
            cartasViradas = 0;
            viraCarta(segundaCartaVirada);
            segundaCartaVirada.setClickable(false);

            if (verificaCartas(primeiraCartaVirada, segundaCartaVirada)) {
                pontos++;
                verificaPontos();
            } else {
                pontos--;
                verificaPontos();
                desviraCartas(primeiraCartaVirada, segundaCartaVirada);
                primeiraCartaVirada.setClickable(true);
                segundaCartaVirada.setClickable(true);
            }
        }

    }

    //inicia o menu na Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.acao, menu);
        return true;
    }

    //verifica se o botão de atualizar na Toolbar foi clicado
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.replay:
                jogarNovamente();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //cartas adicionadas a um array e atribuido a cada uma um evento onClickListener
    public void adicionaCartas() {
        cartas = new ImageButton[]{
                carta1, carta2, carta3, carta4, carta5, carta6, carta7, carta8,
                carta9, carta10, carta11, carta12
        };
        imagens = new int[]{
                cartaId1, cartaId2, cartaId3, cartaId4, cartaId5, cartaId6, cartaId7, cartaId8,
                cartaId9, cartaId10, cartaId11, cartaId12
        };
        for (int i = 0; i < cartas.length; i++) {
            cartas[i].setOnClickListener(this);
        }
    }

    //apresenta as cartas
    public void mostraCartas() {
        Collections.shuffle(Arrays.asList(imagens));
        Collections.shuffle(Arrays.asList(cartas));

        for (int i = 0; i < cartas.length; i++) {
            cartas[i].setBackgroundResource(imagens[i]);
        }
    }

    //esconde as cartas
    public void escondeCartas() {
        for (int i = 0; i < cartas.length; i++) {
            cartas[i].setImageResource(R.drawable.costas_carta);
        }
    }

    //verifica a carta que foi tocada
    private void viraCarta(ImageButton cartaTocada) {
        cartaTocada.setImageResource(0);
    }

    //faz uma verificação da pontuação atual, após cada jogada completa
    public void verificaPontos() {
        pontosCartas.setText(R.string.pontos);
        pontosCartas.append(" " + Integer.toString(pontos));
    }

    //verifica se as cartas são iguais
    private boolean verificaCartas(ImageButton carta1, ImageButton carta2) {
        if (carta1.getBackground().getConstantState().equals(carta2.getBackground().getConstantState())) {
            return true;
        } else {
            return false;
        }
    }

    //desvira as cartas selecionadas
    public void desviraCartas(ImageButton carta1, ImageButton carta2) {
        carta1.setImageResource(R.drawable.costas_carta);
        carta2.setImageResource(R.drawable.costas_carta);
    }

    //chamado para uma nova jogada
    public void jogarNovamente() {
        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    //inicia atribuindo id a cada carta
    public void configuracaoInicial() {
        carta1 = findViewById(R.id.carta1);
        carta2 = findViewById(R.id.carta2);
        carta3 = findViewById(R.id.carta3);
        carta4 = findViewById(R.id.carta4);
        carta5 = findViewById(R.id.carta5);
        carta6 = findViewById(R.id.carta6);
        carta7 = findViewById(R.id.carta7);
        carta8 = findViewById(R.id.carta8);
        carta9 = findViewById(R.id.carta9);
        carta10 = findViewById(R.id.carta10);
        carta11 = findViewById(R.id.carta11);
        carta12 = findViewById(R.id.carta12);

        cartaId1 = R.drawable.carta1;
        cartaId2 = R.drawable.carta2;
        cartaId3 = R.drawable.carta3;
        cartaId4 = R.drawable.carta4;
        cartaId5 = R.drawable.carta5;
        cartaId6 = R.drawable.carta6;
        cartaId7 = R.drawable.carta1;
        cartaId8 = R.drawable.carta2;
        cartaId9 = R.drawable.carta3;
        cartaId10 = R.drawable.carta4;
        cartaId11 = R.drawable.carta5;
        cartaId12 = R.drawable.carta6;

        pontosCartas = findViewById(R.id.pontosCartas);

        cartasViradas = 0;
    }
}

