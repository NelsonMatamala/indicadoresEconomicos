package cl.nelsonmc.indicadores.common;

import static cl.nelsonmc.indicadores.common.Constants.MODE_UI;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;

import cl.nelsonmc.indicadores.model.SerieIndicador;

public class SharedPreferencesManager {
    private static SharedPreferencesManager instance;
    private static final String APP_DATA = "APP_DATA";
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    public static SharedPreferencesManager getInstance(Context context) {
        if(instance == null)
            instance = new SharedPreferencesManager(context.getApplicationContext());
        return instance;
    }

    private SharedPreferencesManager(Context context) {
        preferences = context.getSharedPreferences(APP_DATA, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }


    public String getDataByName(String name) {
        return preferences.getString(name , "");
    }

    public void setUIMode(String newValue) {
        editor.putString(MODE_UI,newValue);
        editor.apply();
    }

    public void setIndicadorValues(String name, ArrayList<SerieIndicador> newValue) {
        Gson gson = new Gson();
        String jsonData = gson.toJson(newValue);
        editor.putString(name , jsonData);
        editor.apply();
    }
}
