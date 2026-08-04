package cl.nelsonmc.indicadores.repository;

import static cl.nelsonmc.indicadores.BaseApplication.sharedPreferences;
import static cl.nelsonmc.indicadores.common.Constants.BITCOIN;
import static cl.nelsonmc.indicadores.common.Constants.COBRE;
import static cl.nelsonmc.indicadores.common.Constants.DESEMPLEO;
import static cl.nelsonmc.indicadores.common.Constants.DOLAR;
import static cl.nelsonmc.indicadores.common.Constants.EURO;
import static cl.nelsonmc.indicadores.common.Constants.IMACEC;
import static cl.nelsonmc.indicadores.common.Constants.IPC;
import static cl.nelsonmc.indicadores.common.Constants.IVP;
import static cl.nelsonmc.indicadores.common.Constants.UF;
import static cl.nelsonmc.indicadores.common.Constants.UTM;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cl.nelsonmc.indicadores.model.DataIndicador;
import cl.nelsonmc.indicadores.model.SerieIndicador;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainRepository {
    private final String TAG = "MainRepository";
    public RemoteData remoteData;

    private final CompositeDisposable disposables = new CompositeDisposable();
    public MutableLiveData<List<SerieIndicador>> dolar = new MutableLiveData<>();
    public MutableLiveData<List<SerieIndicador>> euro = new MutableLiveData<>();
    public MutableLiveData<List<SerieIndicador>> uf = new MutableLiveData<>();
    public MutableLiveData<List<SerieIndicador>> ivp = new MutableLiveData<>();
    public MutableLiveData<List<SerieIndicador>> ipc = new MutableLiveData<>();
    public MutableLiveData<List<SerieIndicador>> utm = new MutableLiveData<>();
    public MutableLiveData<List<SerieIndicador>> imacec = new MutableLiveData<>();
    public MutableLiveData<List<SerieIndicador>> cobre = new MutableLiveData<>();
    public MutableLiveData<List<SerieIndicador>> desempleo = new MutableLiveData<>();
    public MutableLiveData<List<SerieIndicador>> bitcoin = new MutableLiveData<>();

    public MainRepository(RemoteData client) {
        this.remoteData = client;
    }

    public void loadData() {
        getDolarData();
        getEuroData();
        getUFData();
        getIVPData();
        getIPCData();
        getUTMData();
        getIMACECData();
        getCobreData();
        getDesempleoData();
        getBitcoinData();
    }

    public void clearDisposable() {
        disposables.clear();
    }

    private void fetchIndicador(String indicatorKey, Observable<DataIndicador> observable, MutableLiveData<List<SerieIndicador>> liveData) {
        if (getPreferencesData(indicatorKey) != null) {
            liveData.setValue(getPreferencesData(indicatorKey));
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataIndicador>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(DataIndicador dataIndicador) {
                        if (dataIndicador != null && dataIndicador.getSerie() != null) {
                            liveData.setValue(dataIndicador.getSerie());
                            sharedPreferences.setIndicadorValues(indicatorKey, dataIndicador.getSerie());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error fetching " + indicatorKey + ": " + (e != null ? e.getMessage() : "unknown error"));
                    }

                    @Override
                    public void onComplete() { }
                });
    }

    private void getDolarData() {
        fetchIndicador(DOLAR, remoteData.getDataDolarObs(), dolar);
    }

    private void getEuroData() {
        fetchIndicador(EURO, remoteData.getDataEuroObs(), euro);
    }

    private void getUFData() {
        fetchIndicador(UF, remoteData.getDataUFObs(), uf);
    }

    private void getIVPData() {
        fetchIndicador(IVP, remoteData.getDataIVPObs(), ivp);
    }

    private void getIPCData() {
        fetchIndicador(IPC, remoteData.getDataIPCObs(), ipc);
    }

    private void getUTMData() {
        fetchIndicador(UTM, remoteData.getDataUTMObs(), utm);
    }

    private void getIMACECData() {
        fetchIndicador(IMACEC, remoteData.getDataIMACECObs(), imacec);
    }

    private void getCobreData() {
        fetchIndicador(COBRE, remoteData.getDataLibraCobreObs(), cobre);
    }

    private void getDesempleoData() {
        fetchIndicador(DESEMPLEO, remoteData.getDataDesempleoObs(), desempleo);
    }

    private void getBitcoinData() {
        fetchIndicador(BITCOIN, remoteData.getDataBitcoinObs(), bitcoin);
    }

    public ArrayList<SerieIndicador> getPreferencesData(String nameIndicador) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<SerieIndicador>>() {}.getType();
        String jsonString = sharedPreferences.getDataByName(nameIndicador);
        if (!jsonString.isEmpty()) {
            return gson.fromJson(jsonString, type);
        } else return null;
    }

}
