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

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    ImageButton carta1, carta2, carta3, carta4, carta5, carta6;
    ImageButton carta7, carta8, carta9, carta10, carta11, carta12;
    int cartasViradas;
    private int pontos = 0;
    private ImageButton primeiraCartaVirada;
    private ImageButton segundaCartaVirada;

    private  int cartaId1 = R.drawable.carta1;
    private  int cartaId2 = R.drawable.carta2;
    private  int cartaId3 = R.drawable.carta3;
    private  int cartaId4 = R.drawable.carta4;
    private  int cartaId5 = R.drawable.carta5;
    private  int cartaId6 = R.drawable.carta6;
    private  int cartaId7 = R.drawable.carta1;
    private  int cartaId8 = R.drawable.carta2;
    private  int cartaId9 = R.drawable.carta3;
    private  int cartaId10 = R.drawable.carta4;
    private  int cartaId11 = R.drawable.carta5;
    private  int cartaId12 = R.drawable.carta6;
    private ImageButton[] cartas;
    private  int imagens[];
    TextView pontosCartas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        pontosCartas = findViewById(R.id.pontosCartas);

        cartasViradas = 0;
        adicionarCartasOuvintes();
        mostrarCartas();
        esconderCartas();
    }

    @Override
    public void onClick(View v) {
        ImageButton cartaTocada = (ImageButton) v;

        if(cartasViradas == 0) {
            primeiraCartaVirada = cartaTocada;
            viraCarta(cartaTocada);
            cartasViradas = 1;
            primeiraCartaVirada.setClickable(false);

        }else {
            segundaCartaVirada = cartaTocada;
            cartasViradas = 0;
            viraCarta(segundaCartaVirada);
            segundaCartaVirada.setClickable(false);

            if(verificaCartas(primeiraCartaVirada, segundaCartaVirada)) {
                pontos++;
                verificarPlacar();
            }
            else {
                pontos--;
                verificarPlacar();
                desvirarCartas(primeiraCartaVirada, segundaCartaVirada);
                primeiraCartaVirada.setClickable(true);
                segundaCartaVirada.setClickable(true);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.acao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.replay:
                botaoReplay();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void adicionarCartasOuvintes() {
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

    public void mostrarCartas() {
        Collections.shuffle(Arrays.asList(imagens));
        Collections.shuffle(Arrays.asList(cartas));
        for(int i = 0 ; i < cartas.length; i++) {
            cartas[i].setBackgroundResource(imagens[i]);
        }
    }

    public void esconderCartas() {
        for(int i = 0 ; i < cartas.length; i++){
            cartas[i].setImageResource(R.drawable.costas_carta);
        }
    }

    private void viraCarta(ImageButton cartaTocada){
        cartaTocada.setImageResource(0);
    }

    public void  verificarPlacar() {
        pontosCartas.setText(R.string.pontos);
        pontosCartas.append(" "+Integer.toString(pontos));
    }

    private boolean verificaCartas(ImageButton carta1, ImageButton carta2) {
        if(carta1.getBackground().getConstantState().equals(carta2.getBackground().getConstantState()))
            return true;
        else
            return false;
    }


    public void desvirarCartas(ImageButton carta1, ImageButton carta2) {
        carta1.setImageResource(R.drawable.costas_carta);
        carta2.setImageResource(R.drawable.costas_carta);
    }

    public void botaoReplay(){
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        this.finish();
    }

}

