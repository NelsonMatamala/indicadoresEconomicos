package cl.nelsonmc.indicadores;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import cl.nelsonmc.indicadores.common.Utils;
import cl.nelsonmc.indicadores.model.SerieIndicador;
import cl.nelsonmc.indicadores.repository.MainRepository;
import cl.nelsonmc.indicadores.repository.RemoteData;

public class MainActivityModelView extends AndroidViewModel {
    @Inject
    RemoteData client;
    private final MainRepository repository;
    private final Utils utils;

    public MainActivityModelView(@NonNull Application application) {
        super(application);
        ((BaseApplication) getApplication()).getRetrofitComponent().inject(this);
        repository = new MainRepository(client);
        utils = new Utils();
    }

    public LiveData<List<SerieIndicador>> getDolarListObserver() {
        return repository.dolar;
    }

    public LiveData<List<SerieIndicador>> getEuroListObserver() {
        return repository.euro;
    }

    public LiveData<List<SerieIndicador>> getUFListObserver() {
        return repository.uf;
    }

    public LiveData<List<SerieIndicador>> getIVPListObserver() {
        return repository.ivp;
    }

    public LiveData<List<SerieIndicador>> getIPCListObserver() {
        return repository.ipc;
    }

    public LiveData<List<SerieIndicador>> getUTMListObserver() {
        return repository.utm;
    }

    public LiveData<List<SerieIndicador>> getIMACECListObserver() {
        return repository.imacec;
    }

    public LiveData<List<SerieIndicador>> getCobreListObserver() {
        return repository.cobre;
    }

    public LiveData<List<SerieIndicador>> getDesempleoListObserver() {
        return repository.desempleo;
    }

    public LiveData<List<SerieIndicador>> getBitcoinListObserver() {
        return repository.bitcoin;
    }

    public void actualizarValores() {
        repository.getDolarData();
        repository.getEuroData();
        repository.getUFData();
        repository.getIVPData();
        repository.getIPCData();
        repository.getUTMData();
        repository.getIMACECData();
        repository.getCobreData();
        repository.getDesempleoData();
        repository.getBitcoinData();
    }

    public Boolean checkValueDecreased(String valueToday,String valueYesterday){
        float valorHoy = Float.parseFloat(valueToday);
        float valorAyer = Float.parseFloat(valueYesterday);
        return valorHoy < valorAyer;
    }

    public void clearDisposable() {
        repository.clearDisposable();
    }

    public String decimalFormat(String valor) {
        return utils.decimalFormat(valor);
    }

    public String dateUtcToString(String fecha) {
        return utils.dateUtcToString(fecha);
    }
}
