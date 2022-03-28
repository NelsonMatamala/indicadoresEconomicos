package cl.nelsonmc.indicadores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;
import java.util.Objects;

import cl.nelsonmc.indicadores.modelos.SerieIndicador;

public class MainActivity extends AppCompatActivity {

    private TextView fechaDolarText,valorDolarText;
    private TextView fechaEuroTextView,valorEuroText;
    private TextView fechaUFTextView,valorUFText;
    private TextView fechaIVPTextView,valorIVPText;
    private TextView fechaIPCTextView,valorIPCText;
    private TextView fechaUTMTextView,valorUTMText;
    private TextView fechaIMACECTextView,valorIMACECText;
    private TextView fechaCobreTextView,valorCobreText;
    private TextView fechaDesempleoText,valorDesempleoText;
    private TextView fechaBitcoinText,valorBitcoinText;
    private ImageView trendingDolar, trendingEuro,trendingUF;
    private ImageView trendingIVP,trendingIPC,trendingUTM,trendingIMACEC;
    private ImageView trendingCobre,trendingDesempleo,trendingBitcoin;
    private MainActivityModelView viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();

        viewModel = new ViewModelProvider(this).get(MainActivityModelView.class);
        viewModel.getDolarListObserver().observe(this, new Observer<List<SerieIndicador>>() {
            @Override
            public void onChanged(List<SerieIndicador> serieIndicador) {
                if(serieIndicador != null) {
                    fechaDolarText.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                    valorDolarText.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                    float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                    float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                    if (valorHoy < valorAyer) {
                        trendingDolar.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                    }
                }
            }
        });
        viewModel.requestDolarData();

        viewModel.getEuroListObserver().observe(this, new Observer<List<SerieIndicador>>() {
            @Override
            public void onChanged(List<SerieIndicador> serieIndicador) {
                if(serieIndicador != null) {
                fechaEuroTextView.setText(viewModel.dateUtcToString( serieIndicador.get(0).getFecha() ));
                valorEuroText.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()) );
                float valorHoy  = Float.parseFloat(serieIndicador.get(0).getValor());
                float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                if(valorHoy < valorAyer){
                    trendingEuro.setImageDrawable( ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                }
                }
            }
        });
        viewModel.requestEuroData();

        viewModel.getUFListObserver().observe(this, new Observer<List<SerieIndicador>>() {
            @Override
            public void onChanged(List<SerieIndicador> serieIndicador) {
                if(serieIndicador != null) {
                    fechaUFTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                    valorUFText.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                    float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                    float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                    if (valorHoy < valorAyer) {
                        trendingUF.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                    }
                }
            }
        });
        viewModel.requestUFData();

        viewModel.getIVPListObserver().observe(this, new Observer<List<SerieIndicador>>() {
           @Override
           public void onChanged(List<SerieIndicador> serieIndicador) {
               if(serieIndicador != null) {
                   fechaIVPTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                   valorIVPText.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                   float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                   float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                   if (valorHoy < valorAyer) {
                       trendingIVP.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                   }
               }
           }
       });
        viewModel.requestIVPData();

        viewModel.getIPCListObserver().observe(this, new Observer<List<SerieIndicador>>() {
            @Override
            public void onChanged(List<SerieIndicador> serieIndicador) {
                if(serieIndicador != null) {
                    fechaIPCTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                    valorIPCText.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                    float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                    float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                    if (valorHoy < valorAyer) {
                        trendingIPC.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                    }
                }
            }
        });
        viewModel.requestIPCData();

        viewModel.getUTMListObserver().observe(this, new Observer<List<SerieIndicador>>() {
            @Override
            public void onChanged(List<SerieIndicador> serieIndicador) {
                if(serieIndicador != null) {
                    fechaUTMTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                    valorUTMText.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                    float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                    float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                    if (valorHoy < valorAyer) {
                        trendingUTM.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                    }
                }
            }
        });
        viewModel.requestUTMData();

        viewModel.getIMACECListObserver().observe(this, new Observer<List<SerieIndicador>>() {
            @Override
            public void onChanged(List<SerieIndicador> serieIndicador) {
                if(serieIndicador != null) {
                    fechaIMACECTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                    valorIMACECText.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                    float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                    float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                    if (valorHoy < valorAyer) {
                        trendingIMACEC.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                    }
                }
            }
        });
        viewModel.requestIMACECData();

        viewModel.getCobreListObserver().observe(this, new Observer<List<SerieIndicador>>() {
            @Override
            public void onChanged(List<SerieIndicador> serieIndicador) {
                if(serieIndicador != null) {
                    fechaCobreTextView.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                    valorCobreText.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                    float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                    float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                    if (valorHoy < valorAyer) {
                        trendingCobre.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                    }
                }
            }
        });
        viewModel.requestCobreData();

        viewModel.getDesempleoListObserver().observe(this, new Observer<List<SerieIndicador>>() {
            @Override
            public void onChanged(List<SerieIndicador> serieIndicador) {
                if(serieIndicador != null) {
                    fechaDesempleoText.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                    valorDesempleoText.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                    float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                    float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                    if (valorHoy < valorAyer) {
                        trendingDesempleo.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                    }
                }
            }
        });
        viewModel.requestDesempleoData();

        viewModel.getBitcoinListObserver().observe(this, new Observer<List<SerieIndicador>>() {
            @Override
            public void onChanged(List<SerieIndicador> serieIndicador) {
                if(serieIndicador != null) {
                    fechaBitcoinText.setText(viewModel.dateUtcToString(serieIndicador.get(0).getFecha()));
                    valorBitcoinText.setText(viewModel.decimalFormat(serieIndicador.get(0).getValor()));
                    float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                    float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                    if (valorHoy < valorAyer) {
                        trendingBitcoin.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_trending_down));
                    }
                }
            }
        });
        viewModel.requestBitcoinData();
    }
    
    private void setUpView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout coll_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        coll_toolbar.setTitle("Indicadores");
        coll_toolbar.setCollapsedTitleTextAppearance(R.style.coll_toolbar_title);
        coll_toolbar.setExpandedTitleTextAppearance(R.style.exp_toolbar_title);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        fechaDolarText      = findViewById(R.id.fechaDolarTextView);
        valorDolarText      = findViewById(R.id.valorDolarTextView);
        fechaEuroTextView   = findViewById(R.id.fechaEuroTextView);
        valorEuroText       = findViewById(R.id.valorEuroTextView);
        fechaUFTextView     = findViewById(R.id.fechaUFTextView);
        valorUFText         = findViewById(R.id.valorUFTextView);
        fechaIVPTextView    = findViewById(R.id.fechaIVPTextView);
        valorIVPText        = findViewById(R.id.valorIVPTextView);
        fechaIPCTextView    = findViewById(R.id.fechaIPCTextView);
        valorIPCText        = findViewById(R.id.valorIPCTextView);
        fechaUTMTextView    = findViewById(R.id.fechaUTMTextView);
        valorUTMText        = findViewById(R.id.valorUTMTextView);
        fechaIMACECTextView = findViewById(R.id.fechaIMACECTextView);
        valorIMACECText     = findViewById(R.id.valorIMACECTextView);
        fechaCobreTextView  = findViewById(R.id.fechaCobreTextView);
        valorCobreText      = findViewById(R.id.valorCobreTextView);
        fechaDesempleoText  = findViewById(R.id.fechaDesempleoTextView);
        valorDesempleoText  = findViewById(R.id.valorDesempleoTextView);
        fechaBitcoinText    = findViewById(R.id.fechaBitcoinTextView);
        valorBitcoinText    = findViewById(R.id.valorBitcoinTextView);
        trendingDolar       = findViewById(R.id.trendingDolar);
        trendingEuro        = findViewById(R.id.trendingEuro);
        trendingUF          = findViewById(R.id.trendingUF);
        trendingIVP         = findViewById(R.id.trendingIVP);
        trendingIPC         = findViewById(R.id.trendingIPC);
        trendingUTM         = findViewById(R.id.trendingUTM);
        trendingIMACEC      = findViewById(R.id.trendingIMACEC);
        trendingCobre       = findViewById(R.id.trendingCobre);
        trendingDesempleo   = findViewById(R.id.trendingDesempleo);
        trendingBitcoin     = findViewById(R.id.trendingBitcoin);
    }

    public void goToIndicador(View view){
        Intent intent = new Intent(MainActivity.this,IndicadorActivity.class);
        intent.putExtra("tipoData",(String) view.getTag());
        startActivity(intent);
    }

   

}