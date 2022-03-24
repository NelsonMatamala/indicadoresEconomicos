package cl.nelsonmc.indicadores.di;

import android.app.Application;
import com.jakewharton.threetenabp.AndroidThreeTen;

//contenedor de dependencia en el nivel de la aplicación
public class BaseApplication extends Application {
    private RetrofitComponent retrofitComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        retrofitComponent  = DaggerRetrofitComponent.builder().retrofitModule(new RetrofitModule()).build();
    }

    public RetrofitComponent getRetrofitComponent(){
        return retrofitComponent;
    }
}
