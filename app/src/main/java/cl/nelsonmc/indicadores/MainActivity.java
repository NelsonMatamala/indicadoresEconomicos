package cl.nelsonmc.indicadores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import cl.nelsonmc.indicadores.di.BaseApplication;
import cl.nelsonmc.indicadores.modelos.DataIndicador;
import cl.nelsonmc.indicadores.modelos.SerieIndicador;
import cl.nelsonmc.indicadores.webServices.WebClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                    fechaDolarText.setText(DateUtcToString(serieIndicador.get(0).getFecha()));
                    valorDolarText.setText(serieIndicador.get(0).getValor());
                    float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                    float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                    if (valorHoy < valorAyer) {
                        trendingDolar.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ictrending_down));
                    }
                }
            }
        });
        viewModel.requestDolarData();

        viewModel.getEuroListObserver().observe(this, new Observer<List<SerieIndicador>>() {
            @Override
            public void onChanged(List<SerieIndicador> serieIndicador) {
                if(serieIndicador != null) {
                fechaEuroTextView.setText(DateUtcToString( serieIndicador.get(0).getFecha() ));
                valorEuroText.setText(serieIndicador.get(0).getValor() );
                float valorHoy  = Float.parseFloat(serieIndicador.get(0).getValor());
                float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                if(valorHoy < valorAyer){
                    trendingEuro.setImageDrawable( ContextCompat.getDrawable(MainActivity.this, R.drawable.ictrending_down));
                }
                }
            }
        });
        viewModel.requestEuroData();

        viewModel.getUFListObserver().observe(this, new Observer<List<SerieIndicador>>() {
            @Override
            public void onChanged(List<SerieIndicador> serieIndicador) {
                if(serieIndicador != null) {
                    fechaUFTextView.setText(DateUtcToString(serieIndicador.get(0).getFecha()));
                    valorUFText.setText(decimalFormat(serieIndicador.get(0).getValor()));
                    float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                    float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                    if (valorHoy < valorAyer) {
                        trendingUF.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ictrending_down));
                    }
                }
            }
        });
       viewModel.requestUFData();

       viewModel.getIVPListObserver().observe(this, new Observer<List<SerieIndicador>>() {
           @Override
           public void onChanged(List<SerieIndicador> serieIndicador) {
               if(serieIndicador != null) {
                   fechaIVPTextView.setText(DateUtcToString(serieIndicador.get(0).getFecha()));
                   valorIVPText.setText(decimalFormat(serieIndicador.get(0).getValor()));
                   float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                   float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                   if (valorHoy < valorAyer) {
                       trendingIVP.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ictrending_down));
                   }
               }
           }
       });
        viewModel.requestIVPData();

        viewModel.getIPCListObserver().observe(this, new Observer<List<SerieIndicador>>() {
            @Override
            public void onChanged(List<SerieIndicador> serieIndicador) {
                if(serieIndicador != null) {
                    fechaIPCTextView.setText(DateUtcToString(serieIndicador.get(0).getFecha()));
                    valorIPCText.setText(serieIndicador.get(0).getValor());
                    float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                    float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                    if (valorHoy < valorAyer) {
                        trendingIPC.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ictrending_down));
                    }
                }
            }
        });
        viewModel.requestIPCData();

        viewModel.getUTMListObserver().observe(this, new Observer<List<SerieIndicador>>() {
            @Override
            public void onChanged(List<SerieIndicador> serieIndicador) {
                if(serieIndicador != null) {
                    fechaUTMTextView.setText(DateUtcToString(serieIndicador.get(0).getFecha()));
                    valorUTMText.setText(decimalFormat(serieIndicador.get(0).getValor()));
                    float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                    float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                    if (valorHoy < valorAyer) {
                        trendingUTM.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ictrending_down));
                    }
                }
            }
        });
        viewModel.requestUTMData();

        viewModel.getIMACECListObserver().observe(this, new Observer<List<SerieIndicador>>() {
            @Override
            public void onChanged(List<SerieIndicador> serieIndicador) {
                if(serieIndicador != null) {
                    fechaIMACECTextView.setText(DateUtcToString(serieIndicador.get(0).getFecha()));
                    valorIMACECText.setText(serieIndicador.get(0).getValor());
                    float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                    float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                    if (valorHoy < valorAyer) {
                        trendingIMACEC.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ictrending_down));
                    }
                }
            }
        });
        viewModel.requestIMACECData();

        viewModel.getCobreListObserver().observe(this, new Observer<List<SerieIndicador>>() {
            @Override
            public void onChanged(List<SerieIndicador> serieIndicador) {
                if(serieIndicador != null) {
                    fechaCobreTextView.setText(DateUtcToString(serieIndicador.get(0).getFecha()));
                    valorCobreText.setText(serieIndicador.get(0).getValor());
                    float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                    float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                    if (valorHoy < valorAyer) {
                        trendingCobre.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ictrending_down));
                    }
                }
            }
        });
        viewModel.requestCobreData();

        viewModel.getDesempleoListObserver().observe(this, new Observer<List<SerieIndicador>>() {
            @Override
            public void onChanged(List<SerieIndicador> serieIndicador) {
                if(serieIndicador != null) {
                    fechaDesempleoText.setText(DateUtcToString(serieIndicador.get(0).getFecha()));
                    valorDesempleoText.setText(serieIndicador.get(0).getValor());
                    float valorHoy = Float.parseFloat(serieIndicador.get(0).getValor());
                    float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                    if (valorHoy < valorAyer) {
                        trendingDesempleo.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ictrending_down));
                    }
                }
            }
        });
        viewModel.requestDesempleoData();

        viewModel.getBitcoinListObserver().observe(this, new Observer<List<SerieIndicador>>() {
            @Override
            public void onChanged(List<SerieIndicador> serieIndicador) {
                fechaBitcoinText.setText(DateUtcToString( serieIndicador.get(0).getFecha() ));
                valorBitcoinText.setText(decimalFormat(serieIndicador.get(0).getValor()) );
                float valorHoy  = Float.parseFloat(serieIndicador.get(0).getValor());
                float valorAyer = Float.parseFloat(serieIndicador.get(1).getValor());
                if(valorHoy < valorAyer){
                    trendingBitcoin.setImageDrawable( ContextCompat.getDrawable(MainActivity.this, R.drawable.ictrending_down));
                }
            }
        });
        viewModel.requestBitcoinData();
    }
    
    private void setUpView(){
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
        fechaBitcoinText    = findViewById(R.id.valorBitcoinTextView);
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
        startActivity(intent);
    }

    private String decimalFormat(String valor){
        float numero = Float.parseFloat(valor);
        DecimalFormat formato = new DecimalFormat("#,###.00");
        String valorFormateado = formato.format(numero);
        return  valorFormateado;
    }

    private String DateUtcToString(String fecha) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Instant instantFecha  = Instant.parse(fecha);
        return ZonedDateTime.ofInstant(instantFecha, ZoneId.of("America/Santiago")).format(dtf);
    }


}