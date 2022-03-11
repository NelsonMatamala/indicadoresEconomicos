package cl.nelsonmc.indicadores.di;

import javax.inject.Singleton;
import cl.nelsonmc.indicadores.webServices.WebClient;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    private static final String BASE_URL = "https://mindicador.cl/";

    //provides (proporcionar clases que no son de propiedad de tu proyecto (p. ej., una instancia de Retrofit).

    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory){
        return  new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    WebClient provideWebClient(Retrofit retrofit){
        return retrofit.create(WebClient.class);
    }
}
