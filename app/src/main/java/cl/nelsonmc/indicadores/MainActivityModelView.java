package cl.nelsonmc.indicadores;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import cl.nelsonmc.indicadores.common.Utils;
import cl.nelsonmc.indicadores.model.IndicadorList;
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
        repository.loadData();
    }

    public LiveData<List<IndicadorList>> getDolarListObserver() {
        return repository.dolar;
    }

    public LiveData<List<IndicadorList>> getEuroListObserver() {
        return repository.euro;
    }

    public LiveData<List<IndicadorList>> getUFListObserver() {
        return repository.uf;
    }

    public LiveData<List<IndicadorList>> getIVPListObserver() {
        return repository.ivp;
    }

    public LiveData<List<IndicadorList>> getIPCListObserver() {
        return repository.ipc;
    }

    public LiveData<List<IndicadorList>> getUTMListObserver() {
        return repository.utm;
    }

    public LiveData<List<IndicadorList>> getIMACECListObserver() {
        return repository.imacec;
    }

    public LiveData<List<IndicadorList>> getCobreListObserver() {
        return repository.cobre;
    }

    public LiveData<List<IndicadorList>> getDesempleoListObserver() {return repository.desempleo;}

    public LiveData<List<IndicadorList>> getBitcoinListObserver() {
        return repository.bitcoin;
    }

    public void updateValues() {
        repository.loadData();
    }

    public Boolean checkValueDecreased(String valueToday,String valueYesterday){
        float valorHoy = Float.parseFloat(valueToday);
        float valorAyer = Float.parseFloat(valueYesterday);
        return valorHoy < valorAyer;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository.clearDisposable();
    }

    public String decimalFormat(String valor) {
        return utils.decimalFormat(valor);
    }

    public String dateUtcToString(String fecha) {
        return utils.dateUtcToString(fecha);
    }
}
