package cl.nelsonmc.indicadores;

import static cl.nelsonmc.indicadores.common.Constants.MODE_UI;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.jakewharton.threetenabp.AndroidThreeTen;

import cl.nelsonmc.indicadores.common.Constants;
import cl.nelsonmc.indicadores.common.SharedPreferencesManager;
import cl.nelsonmc.indicadores.repository.retrofitdi.DaggerRetrofitComponent;
import cl.nelsonmc.indicadores.repository.retrofitdi.RetrofitComponent;
import cl.nelsonmc.indicadores.repository.retrofitdi.RetrofitModule;

public class BaseApplication extends Application {
    private RetrofitComponent retrofitComponent;
    public static SharedPreferencesManager sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        retrofitComponent = DaggerRetrofitComponent.builder().retrofitModule(new RetrofitModule()).build();
        sharedPreferences = SharedPreferencesManager.getInstance(this);
        uiModeVerification();
    }

    public RetrofitComponent getRetrofitComponent() {
        return retrofitComponent;
    }

    private void uiModeVerification() {
        String modeOptionString = sharedPreferences.getDataByName(MODE_UI);
        if (modeOptionString.equals(Constants.DARKMODE)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else if (modeOptionString.equals(Constants.LIGHTMODE)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
