package cl.nelsonmc.indicadores;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
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
    private String tipoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicador);
        TextView tittle = findViewById(R.id.textTitulo);
        BottomNavigationView bottomNav = findViewById(R.id.navigator_bottom);
        bottomNav.setOnNavigationItemSelectedListener( bottomListener );

        Bundle extras   = getIntent().getExtras();
        tipoData = extras != null ? extras.getString("tipoData") : "";
        tittle.setText(tipoData.toUpperCase());
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(tipoData, null);
        Type type = new TypeToken<ArrayList<SerieIndicador>>() {}.getType();
        serieIndicadorArrayList = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (serieIndicadorArrayList == null) {
            serieIndicadorArrayList = new ArrayList<>();
        }

        Bundle datos = new Bundle();
        datos.putString("tipoData",tipoData);
        datos.putSerializable("indicador",serieIndicadorArrayList.get(0));
        Fragment calcularFragment = new CalcularFragment();
        calcularFragment.setArguments(datos);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, calcularFragment ).commit();

        LineChart lineChart = findViewById(R.id.reportingChart);
        lineChart.setTouchEnabled(true);
        lineChart.setPinchZoom(true);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getDescription().setText("");
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getXAxis().setDrawAxisLine(false);
        lineChart.getXAxis().setEnabled(false);

        LineDataSet lineDataSet = new LineDataSet(dataValues1(),"Data set 1");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        lineDataSet.setLineWidth(2f);
        lineDataSet.setColor(Color.BLUE);
        //lineDataSet.setDrawValues(false);  //quita los numeros
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setCubicIntensity(0.2f);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillDrawable(getResources().getDrawable(R.drawable.gradient_graph));
        lineDataSet.setFillAlpha(80);
        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);
        //to remove the cricle from the graph
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawHighlightIndicators(false);
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
            dataVals.add(new Entry(i,valor));
        }
        return dataVals;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment selectedFragment;
            Bundle datos;

            switch (menuItem.getItemId()){

                case R.id.calcular:
                    datos = new Bundle();
                    datos.putString("tipoData",tipoData);
                    datos.putSerializable("indicador",serieIndicadorArrayList.get(0));
                    selectedFragment = new CalcularFragment();
                    selectedFragment.setArguments(datos);
                    break;

                case R.id.lista:
                    datos = new Bundle();
                    datos.putSerializable("arrayList",serieIndicadorArrayList);
                    datos.putString("tipoData",tipoData);
                    selectedFragment = new ListaFragment();
                    selectedFragment.setArguments(datos);
                    break;

                default:
                    selectedFragment = new CalcularFragment();
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };

}

