package cl.nelsonmc.indicadores;

import android.app.Application;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatDelegate;

import com.jakewharton.threetenabp.AndroidThreeTen;

import cl.nelsonmc.indicadores.common.Constants;
import cl.nelsonmc.indicadores.di.DaggerRetrofitComponent;
import cl.nelsonmc.indicadores.di.RetrofitComponent;
import cl.nelsonmc.indicadores.di.RetrofitModule;


public class BaseApplication extends Application {
    private RetrofitComponent retrofitComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        retrofitComponent = DaggerRetrofitComponent.builder().retrofitModule(new RetrofitModule()).build();
        uiModeVerification();
    }

    public RetrofitComponent getRetrofitComponent() {
        return retrofitComponent;
    }

    private void uiModeVerification() {
        String modeOptionString = PreferenceManager.getDefaultSharedPreferences(this).getString("option_mode", Constants.PREDETERMINADO);
        if (modeOptionString.equals(Constants.DARKMODE)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else if (modeOptionString.equals(Constants.LIGHTMODE)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
