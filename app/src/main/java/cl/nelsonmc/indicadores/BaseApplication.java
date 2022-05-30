package cl.nelsonmc.indicadores;

import static cl.nelsonmc.indicadores.UIModeOption.MODO_CLARO;
import static cl.nelsonmc.indicadores.UIModeOption.MODO_OSCURO;
import static cl.nelsonmc.indicadores.UIModeOption.PREDETERMINADO;

import android.app.Application;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatDelegate;

import com.jakewharton.threetenabp.AndroidThreeTen;

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
        String modeOptionString = PreferenceManager.getDefaultSharedPreferences(this).getString("option_mode", PREDETERMINADO.toString());
        UIModeOption modeOption = UIModeOption.valueOf(modeOptionString);
        if (modeOption == MODO_OSCURO) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else if (modeOption == MODO_CLARO) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
