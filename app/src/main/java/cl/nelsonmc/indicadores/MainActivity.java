package cl.nelsonmc.indicadores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.inject.Inject;
import cl.nelsonmc.indicadores.di.BaseApplication;
import cl.nelsonmc.indicadores.modelos.DataIndicador;
import cl.nelsonmc.indicadores.modelos.SerieIndicador;
import cl.nelsonmc.indicadores.webServices.WebClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Inject
    WebClient client;
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
    private ImageView trendingDolar, trendingEuro,trendingUF,
            trendingIVP,trendingIPC,trendingUTM,trendingIMACEC
            ,trendingCobre,trendingDesempleo,trendingBitcoin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpDagger();
        setUpView();
        requestDolarData();
        requestEuro();
        requestUF();
        requestIVP();
        requestIPC();
        requestUTM();
        requestIMACEC();
        requestCobre();
        requestDesempleo();
        requestBitcoin();
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

    private void setUpDagger(){
        ((BaseApplication)getApplication()).getRetrofitComponent().inject(this);
    }

    private void requestDolarData(){
        Call<DataIndicador> call = client.getDataDolar();
        call.enqueue(new Callback<DataIndicador>() {
            @Override
            public void onResponse(Call<DataIndicador> call, Response<DataIndicador> response) {
                if (response!=null && response.isSuccessful() && response.body()!=null){

                    ArrayList<SerieIndicador> arrayDolar = response.body().getSerie();
                    fechaDolarText.setText(DateUtcToString( arrayDolar.get(0).getFecha() ));
                    valorDolarText.setText(arrayDolar.get(0).getValor() );
                    float valorHoy  = Float.parseFloat(arrayDolar.get(0).getValor());
                    float valorAyer = Float.parseFloat(arrayDolar.get(1).getValor());
                    if(valorHoy < valorAyer){
                        trendingDolar.setImageDrawable( ContextCompat.getDrawable(MainActivity.this, R.drawable.ictrending_down));
                    }
                }
            }

            @Override
            public void onFailure(Call<DataIndicador> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });
    }

    private void requestEuro(){
        Call<DataIndicador> call = client.getDataEuro();
        call.enqueue(new Callback<DataIndicador>() {
            @Override
            public void onResponse(Call<DataIndicador> call, Response<DataIndicador> response) {
                if (response!=null && response.isSuccessful() && response.body()!=null){

                    ArrayList<SerieIndicador> arrayEuro = response.body().getSerie();
                    fechaEuroTextView.setText(DateUtcToString( arrayEuro.get(0).getFecha() ));
                    valorEuroText.setText(arrayEuro.get(0).getValor() );
                    float valorHoy  = Float.parseFloat(arrayEuro.get(0).getValor());
                    float valorAyer = Float.parseFloat(arrayEuro.get(1).getValor());
                    if(valorHoy < valorAyer){
                        trendingEuro.setImageDrawable( ContextCompat.getDrawable(MainActivity.this, R.drawable.ictrending_down));
                    }
                }
            }

            @Override
            public void onFailure(Call<DataIndicador> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });
    }

    private void requestUF(){
        Call<DataIndicador> call = client.getDataUF();
        call.enqueue(new Callback<DataIndicador>() {
            @Override
            public void onResponse(Call<DataIndicador> call, Response<DataIndicador> response) {
                if (response!=null && response.isSuccessful() && response.body()!=null){

                    ArrayList<SerieIndicador> arrayUF = response.body().getSerie();
                    fechaUFTextView.setText(DateUtcToString( arrayUF.get(0).getFecha() ));
                    valorUFText.setText(decimalFormat (arrayUF.get(0).getValor()) );
                    float valorHoy  = Float.parseFloat(arrayUF.get(0).getValor());
                    float valorAyer = Float.parseFloat(arrayUF.get(1).getValor());
                    if(valorHoy < valorAyer){
                        trendingUF.setImageDrawable( ContextCompat.getDrawable(MainActivity.this, R.drawable.ictrending_down));
                    }
                }
            }

            @Override
            public void onFailure(Call<DataIndicador> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });
    }

    private void requestIVP(){
        Call<DataIndicador> call = client.getDataIVP();
        call.enqueue(new Callback<DataIndicador>() {
            @Override
            public void onResponse(Call<DataIndicador> call, Response<DataIndicador> response) {
                if (response!=null && response.isSuccessful() && response.body()!=null){

                    ArrayList<SerieIndicador> arrayIVP = response.body().getSerie();
                    fechaIVPTextView.setText(DateUtcToString( arrayIVP.get(0).getFecha() ));
                    valorIVPText.setText(decimalFormat (arrayIVP.get(0).getValor()) );
                    float valorHoy  = Float.parseFloat(arrayIVP.get(0).getValor());
                    float valorAyer = Float.parseFloat(arrayIVP.get(1).getValor());
                    if(valorHoy < valorAyer){
                        trendingIVP.setImageDrawable( ContextCompat.getDrawable(MainActivity.this, R.drawable.ictrending_down));
                    }
                }
            }

            @Override
            public void onFailure(Call<DataIndicador> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });
    }

    private void requestIPC() {
        Call<DataIndicador> call = client.getDataIPC();
        call.enqueue(new Callback<DataIndicador>() {
            @Override
            public void onResponse(Call<DataIndicador> call, Response<DataIndicador> response) {
                if (response!=null && response.isSuccessful() && response.body()!=null){

                    ArrayList<SerieIndicador> arrayIPC = response.body().getSerie();
                    fechaIPCTextView.setText(DateUtcToString( arrayIPC.get(0).getFecha() ));
                    valorIPCText.setText(arrayIPC.get(0).getValor() );
                    float valorHoy  = Float.parseFloat(arrayIPC.get(0).getValor());
                    float valorAyer = Float.parseFloat(arrayIPC.get(1).getValor());
                    if(valorHoy < valorAyer){
                        trendingIPC.setImageDrawable( ContextCompat.getDrawable(MainActivity.this, R.drawable.ictrending_down));
                    }
                }
            }

            @Override
            public void onFailure(Call<DataIndicador> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });
    }

    private void requestUTM() {
        Call<DataIndicador> call = client.getDataUTM();
        call.enqueue(new Callback<DataIndicador>() {
            @Override
            public void onResponse(Call<DataIndicador> call, Response<DataIndicador> response) {
                if (response!=null && response.isSuccessful() && response.body()!=null){

                    ArrayList<SerieIndicador> arrayUTM = response.body().getSerie();
                    fechaUTMTextView.setText(DateUtcToString( arrayUTM.get(0).getFecha() ));
                    valorUTMText.setText(decimalFormat(arrayUTM.get(0).getValor()));
                    float valorHoy  = Float.parseFloat(arrayUTM.get(0).getValor());
                    float valorAyer = Float.parseFloat(arrayUTM.get(1).getValor());
                    if(valorHoy < valorAyer){
                        trendingUTM.setImageDrawable( ContextCompat.getDrawable(MainActivity.this, R.drawable.ictrending_down));
                    }
                }
            }

            @Override
            public void onFailure(Call<DataIndicador> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });
    }

    private void requestIMACEC() {
        Call<DataIndicador> call = client.getDataIMACEC();
        call.enqueue(new Callback<DataIndicador>() {
            @Override
            public void onResponse(Call<DataIndicador> call, Response<DataIndicador> response) {
                if (response!=null && response.isSuccessful() && response.body()!=null){

                    ArrayList<SerieIndicador> arrayIMACEC = response.body().getSerie();
                    fechaIMACECTextView.setText(DateUtcToString( arrayIMACEC.get(0).getFecha() ));
                    valorIMACECText.setText(arrayIMACEC.get(0).getValor() );
                    float valorHoy  = Float.parseFloat(arrayIMACEC.get(0).getValor());
                    float valorAyer = Float.parseFloat(arrayIMACEC.get(1).getValor());
                    if(valorHoy < valorAyer){
                        trendingIMACEC.setImageDrawable( ContextCompat.getDrawable(MainActivity.this, R.drawable.ictrending_down));
                    }
                }
            }

            @Override
            public void onFailure(Call<DataIndicador> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });
    }

    private void requestCobre() {
        Call<DataIndicador> call = client.getDataLibraCobre();
        call.enqueue(new Callback<DataIndicador>() {
            @Override
            public void onResponse(Call<DataIndicador> call, Response<DataIndicador> response) {
                if (response!=null && response.isSuccessful() && response.body()!=null){

                    ArrayList<SerieIndicador> arrayCobre = response.body().getSerie();
                    fechaCobreTextView.setText(DateUtcToString( arrayCobre.get(0).getFecha() ));
                    valorCobreText.setText(arrayCobre.get(0).getValor() );
                    float valorHoy  = Float.parseFloat(arrayCobre.get(0).getValor());
                    float valorAyer = Float.parseFloat(arrayCobre.get(1).getValor());
                    if(valorHoy < valorAyer){
                        trendingCobre.setImageDrawable( ContextCompat.getDrawable(MainActivity.this, R.drawable.ictrending_down));
                    }
                }
            }

            @Override
            public void onFailure(Call<DataIndicador> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });
    }

    private void requestDesempleo() {
        Call<DataIndicador> call = client.getDataDesempleo();
        call.enqueue(new Callback<DataIndicador>() {
            @Override
            public void onResponse(Call<DataIndicador> call, Response<DataIndicador> response) {
                if (response!=null && response.isSuccessful() && response.body()!=null){

                    ArrayList<SerieIndicador> arrayDesempleo = response.body().getSerie();
                    fechaDesempleoText.setText(DateUtcToString( arrayDesempleo.get(0).getFecha() ));
                    valorDesempleoText.setText(arrayDesempleo.get(0).getValor() );
                    float valorHoy  = Float.parseFloat(arrayDesempleo.get(0).getValor());
                    float valorAyer = Float.parseFloat(arrayDesempleo.get(1).getValor());
                    if(valorHoy < valorAyer){
                        trendingDesempleo.setImageDrawable( ContextCompat.getDrawable(MainActivity.this, R.drawable.ictrending_down));
                    }
                }
            }

            @Override
            public void onFailure(Call<DataIndicador> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });
    }

    private void requestBitcoin() {
        Call<DataIndicador> call = client.getDataBitcoin();
        call.enqueue(new Callback<DataIndicador>() {
            @Override
            public void onResponse(Call<DataIndicador> call, Response<DataIndicador> response) {
                if (response!=null && response.isSuccessful() && response.body()!=null){

                    ArrayList<SerieIndicador> arrayBitcoin = response.body().getSerie();
                    fechaBitcoinText.setText(DateUtcToString( arrayBitcoin.get(0).getFecha() ));
                    valorBitcoinText.setText(decimalFormat(arrayBitcoin.get(0).getValor()) );
                    float valorHoy  = Float.parseFloat(arrayBitcoin.get(0).getValor());
                    float valorAyer = Float.parseFloat(arrayBitcoin.get(1).getValor());
                    if(valorHoy < valorAyer){
                        trendingBitcoin.setImageDrawable( ContextCompat.getDrawable(MainActivity.this, R.drawable.ictrending_down));
                    }
                }
            }

            @Override
            public void onFailure(Call<DataIndicador> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });
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