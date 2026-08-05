package cl.nelsonmc.indicadores.indicadores;

import static cl.nelsonmc.indicadores.BaseApplication.sharedPreferences;
import static cl.nelsonmc.indicadores.common.Constants.DATAYPE;
import static cl.nelsonmc.indicadores.common.Constants.INDICATOR;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import cl.nelsonmc.indicadores.R;
import cl.nelsonmc.indicadores.common.Utils;
import cl.nelsonmc.indicadores.indicadores.calculadora.CalcularFragment;
import cl.nelsonmc.indicadores.indicadores.lista.ListaFragment;
import cl.nelsonmc.indicadores.model.IndicadorList;

public class IndicadorActivity extends AppCompatActivity {

    private ArrayList<IndicadorList> indicadorList;
    private final ArrayList<String> dateList = new ArrayList<>();
    private String dataType;
    private final Utils utils = new Utils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicador);
        TextView tittle = findViewById(R.id.textTitulo);
        TextView indicatorValue = findViewById(R.id.txtValue);
        BottomNavigationView bottomNav = findViewById(R.id.navigator_bottom);
        bottomNav.setOnNavigationItemSelectedListener( bottomListener );

        Bundle extras   = getIntent().getExtras();
        dataType = extras != null ? extras.getString(DATAYPE) : "";
        tittle.setText(dataType.toUpperCase());

        Gson gson = new Gson();
        String json = sharedPreferences.getDataByName(dataType);
        Type type = new TypeToken<ArrayList<IndicadorList>>() {}.getType();
        indicadorList = gson.fromJson(json, type);

        if (indicadorList == null) {
            indicadorList = new ArrayList<>();
        }

        if (!indicadorList.isEmpty() && indicatorValue != null) {
            indicatorValue.setText(utils.decimalFormat(indicadorList.get(0).getValor()));
        }

        Bundle datos = new Bundle();
        datos.putString(DATAYPE, dataType);
        datos.putSerializable(INDICATOR, indicadorList.get(0));
        Fragment calcularFragment = new CalcularFragment();
        calcularFragment.setArguments(datos);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, calcularFragment ).commit();

        createGraph();
    }

    private void createGraph(){
        LineChart lineChart = findViewById(R.id.reportingChart);
        lineChart.setTouchEnabled(true);
        lineChart.setPinchZoom(true);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getDescription().setText("");
        lineChart.setExtraBottomOffset(15f);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelRotationAngle(45f);
        xAxis.setTextColor(ContextCompat.getColor(this, R.color.colorFontValor));
        lineChart.getAxisLeft().setTextColor(ContextCompat.getColor(this, R.color.colorFontValor));
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int index = (int) value;
                if (index >= 0 && index < dateList.size()) {
                    return dateList.get(index);
                }
                return "";
            }
        });

        LineDataSet lineDataSet = new LineDataSet(valoresIndicador(),"Valores");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        lineDataSet.setLineWidth(2f);
        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setDrawValues(false);  //quita los numeros amontonados arriba
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setCubicIntensity(0.2f);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillDrawable(getResources().getDrawable(R.drawable.gradient_graph));
        lineDataSet.setFillAlpha(80);
        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);
        lineDataSet.setDrawCircles(true); // muestra puntos para poder tocarlos o verlos claramente
        lineDataSet.setCircleRadius(3f);
        lineDataSet.setCircleColor(Color.BLUE);
        lineDataSet.setDrawHighlightIndicators(true);
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.setMarker(new CustomMarkerView(this, dateList));
        lineChart.invalidate();
    }

    private ArrayList<Entry> valoresIndicador(){
        ArrayList<Entry> dataVals = new ArrayList<>();
        dateList.clear();
        int arraySize = indicadorList.size();
        Utils utils = new Utils();
        for (int i = 0;i < arraySize;i++){
            IndicadorList indicador = indicadorList.get(arraySize - i - 1);
            float valor = Float.parseFloat(indicador.getValor());
            dataVals.add(new Entry(i,valor));
            try {
                dateList.add(utils.dateUtcToShortString(indicador.getFecha()));
            } catch (Exception e) {
                dateList.add("");
            }
        }
        return dataVals;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment selectedFragment;
            Bundle datos;
            int id = menuItem.getItemId();

            if (id == R.id.calcular) {
                datos = new Bundle();
                datos.putString(DATAYPE, dataType);
                datos.putSerializable(INDICATOR, indicadorList.get(0));
                selectedFragment = new CalcularFragment();
                selectedFragment.setArguments(datos);

            } else if (id == R.id.lista) {
                datos = new Bundle();
                datos.putSerializable("arrayList", indicadorList);
                datos.putString(DATAYPE, dataType);
                selectedFragment = new ListaFragment();
                selectedFragment.setArguments(datos);

            } else {
                selectedFragment = new CalcularFragment();
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };

}

