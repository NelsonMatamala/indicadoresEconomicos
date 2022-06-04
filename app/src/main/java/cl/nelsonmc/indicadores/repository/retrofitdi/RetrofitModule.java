package cl.nelsonmc.indicadores.di;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import cl.nelsonmc.indicadores.repository.RepositoryData;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    private static final String BASE_URL = "https://mindicador.cl/";

    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(){
        return new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    RxJava2CallAdapterFactory providesRxJava2CallAdapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient client,GsonConverterFactory gsonConverterFactory,RxJava2CallAdapterFactory rxJava2CallAdapterFactory){
        return  new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    RepositoryData provideWebClient(Retrofit retrofit){
        return retrofit.create(RepositoryData.class);
    }
}
