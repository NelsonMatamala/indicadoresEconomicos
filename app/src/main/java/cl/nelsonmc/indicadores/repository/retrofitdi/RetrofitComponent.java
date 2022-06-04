package cl.nelsonmc.indicadores.di;

import javax.inject.Singleton;
import cl.nelsonmc.indicadores.MainActivityModelView;
import dagger.Component;

//puente entre los modulos y el codigo que solicita la inyeccion
@Singleton
@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {
    void inject(MainActivityModelView mainActivityModelView);
}
