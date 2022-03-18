package cl.nelsonmc.indicadores;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Entity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.util.ArrayList;


import cl.nelsonmc.indicadores.modelos.SerieIndicador;
import cl.nelsonmc.indicadores.ui.CalcularFragment;
import cl.nelsonmc.indicadores.ui.ListaFragment;

public class IndicadorActivity extends AppCompatActivity {

    private ArrayList<SerieIndicador> serieIndicadorArrayList;
    private LineChart lineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicador);

        BottomNavigationView bottomNav = findViewById(R.id.navigator_bottom);
        bottomNav.setOnNavigationItemSelectedListener( bottomListener );
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalcularFragment() ).commit();

        Bundle extras   = getIntent().getExtras();
        String tipoData = extras.getString("tipoData");

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(tipoData, null);
        Type type = new TypeToken<ArrayList<SerieIndicador>>() {}.getType();
        serieIndicadorArrayList = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (serieIndicadorArrayList == null) {
            serieIndicadorArrayList = new ArrayList<>();
        }
        for (int i = 0;i < serieIndicadorArrayList.size();i++){
            float valor = Float.parseFloat(serieIndicadorArrayList.get(i).getValor());
            Log.d("serieIndicadorArrayList",valor+"");
        }
        Log.d("serieIndicadorArrayList","****************");

        lineChart = findViewById(R.id.reportingChart);
        lineChart.setTouchEnabled(true);
        lineChart.setPinchZoom(true);

        LineDataSet lineDataSet = new LineDataSet(dataValues1(),"Data set 1");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        lineDataSet.setLineWidth(3f);
        lineDataSet.setColor(Color.GRAY);
        lineDataSet.setHighLightColor(Color.RED);
        //lineDataSet.setDrawValues(false);  //quita los numeros


        //to make the smooth line as the graph is adrapt change so smooth curve
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        //to enable the cubic density : if 1 then it will be sharp curve
        lineDataSet.setCubicIntensity(0.2f);

        //to fill the below of smooth line in graph
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillColor(Color.BLACK);
        //set the transparency
        lineDataSet.setFillAlpha(80);

        //set the gradiant then the above draw fill color will be replace
        //Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.gradiant);
        //lineDataSet.setFillDrawable(drawable);

        //set legend disable or enable to hide {the left down corner name of graph}
        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);

        //to remove the cricle from the graph
        lineDataSet.setDrawCircles(false);


        dataSets.add(lineDataSet);
        LineData data = new LineData(dataSets);

        lineChart.setData(data);
        lineChart.invalidate();

    }

    private ArrayList<Entry> dataValues1(){


        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        int arraySize = serieIndicadorArrayList.size();
        for (int i = 0;i < arraySize;i++){

            float valor = Float.parseFloat(serieIndicadorArrayList.get(arraySize - i -1).getValor());
            Log.d("serieIndicadorArrayList",valor+"");
            dataVals.add(new Entry(i,valor));
        }

        return dataVals;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment selectedFragment = null;

            switch (menuItem.getItemId()){

                case R.id.calcular:
                    selectedFragment = new CalcularFragment();
                    break;

                case R.id.lista:
                    Bundle datos = new Bundle();
                    datos.putSerializable("ArrayList",serieIndicadorArrayList);
                    selectedFragment = new ListaFragment();
                    selectedFragment.setArguments(datos);
                    break;

            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };

}

