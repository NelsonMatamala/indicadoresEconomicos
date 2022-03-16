package cl.nelsonmc.indicadores;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import cl.nelsonmc.indicadores.modelos.SerieIndicador;

public class IndicadorActivity extends AppCompatActivity {

    private ArrayList<SerieIndicador> serieIndicadorArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicador);

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("dolarData", null);
        Type type = new TypeToken<ArrayList<SerieIndicador>>() {}.getType();
        serieIndicadorArrayList = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (serieIndicadorArrayList == null) {
            serieIndicadorArrayList = new ArrayList<>();
        }

        Log.d("IndicadorActivity", serieIndicadorArrayList.get(0).getValor());
        Log.d("IndicadorActivity", serieIndicadorArrayList.get(1).getValor());
        Log.d("IndicadorActivity", serieIndicadorArrayList.get(2).getValor());

    }
}