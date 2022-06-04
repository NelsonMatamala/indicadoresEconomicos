package cl.nelsonmc.indicadores;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;


import cl.nelsonmc.indicadores.model.SerieIndicador;
import cl.nelsonmc.indicadores.repository.MainRepository;
import cl.nelsonmc.indicadores.repository.RemoteData;

public class MainActivityModelView extends AndroidViewModel {
    @Inject
    RemoteData client;
    private final MainRepository repository;

    public MainActivityModelView(@NonNull Application application) {
        super(application);
        ((BaseApplication) getApplication()).getRetrofitComponent().inject(this);
        repository = new MainRepository(client);
    }

    public LiveData<List<SerieIndicador>> getDolarListObserver() {
        return repository.dolar;
    }

    public MutableLiveData<List<SerieIndicador>> getEuroListObserver() {
        return repository.euro;
    }

    public MutableLiveData<List<SerieIndicador>> getUFListObserver() {
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

    public void clearDisposable(){
        repository.clearDisposable();
    }

    public String decimalFormat(String valor) {
        float numero = Float.parseFloat(valor);
        Locale chileLocale = new Locale("es", "CL");
        NumberFormat nf = NumberFormat.getNumberInstance(chileLocale);
        return nf.format(numero);
    }

    public String dateUtcToString(String fecha) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Instant instantFecha = Instant.parse(fecha);
        return ZonedDateTime.ofInstant(instantFecha, ZoneId.of("America/Santiago")).format(dtf);
    }
}
