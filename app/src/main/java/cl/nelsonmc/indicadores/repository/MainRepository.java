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
        getDolarData();
        getEuroData();
        getUFData();
        getIVPData();
        getIPCData();
        getUTMData();
        getIMACECData();
        getCobreData();
        getDesempleoData();
    }

    public void clearDisposable() {
        disposables.clear();
    }

    public void getDolarData() {
        if (getPreferencesData(DOLAR) != null) {
            dolar.setValue(getPreferencesData(DOLAR));
        }
        remoteData.getDataDolarObs().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataIndicador>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(DataIndicador dataIndicador) {
                        dolar.setValue(dataIndicador.getSerie());
                        sharedPreferences.setIndicadorValues(DOLAR,dataIndicador.getSerie());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.getMessage());
                    }

                    @Override
                    public void onComplete() { }
                });
    }

    public void getEuroData() {
        if (getPreferencesData(EURO) != null) {
            euro.setValue(getPreferencesData(EURO));
        }
        remoteData.getDataEuroObs().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataIndicador>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(DataIndicador dataIndicador) {
                        euro.setValue(dataIndicador.getSerie());
                        sharedPreferences.setIndicadorValues(EURO,dataIndicador.getSerie());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"getDataEuroObs"+ e.getMessage());
                    }

                    @Override
                    public void onComplete() { }
                });
    }

    public void getUFData(){
        if (getPreferencesData(UF) != null) {
            uf.setValue(getPreferencesData(UF));
        }
        remoteData.getDataUFObs().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataIndicador>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(DataIndicador dataIndicador) {
                        uf.setValue(dataIndicador.getSerie());
                        sharedPreferences.setIndicadorValues(UF,dataIndicador.getSerie());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"getDataUFObs"+ e.getMessage());
                    }

                    @Override
                    public void onComplete() { }
                });
    }

    public void getIVPData(){
        if (getPreferencesData(IVP) != null) {
            ivp.setValue(getPreferencesData(IVP));
        }
        remoteData.getDataIVPObs().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataIndicador>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(DataIndicador dataIndicador) {
                        ivp.setValue(dataIndicador.getSerie());
                        sharedPreferences.setIndicadorValues(IVP,dataIndicador.getSerie());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"getDataIVPObs"+ e.getMessage());
                    }

                    @Override
                    public void onComplete() { }
                });
    }

    public void getIPCData(){
        if (getPreferencesData(IPC) != null) {
            ipc.setValue(getPreferencesData(IPC));
        }
        remoteData.getDataIPCObs().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataIndicador>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(DataIndicador dataIndicador) {
                        ipc.setValue(dataIndicador.getSerie());
                        sharedPreferences.setIndicadorValues(IPC,dataIndicador.getSerie());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"getDataIPCObs"+ e.getMessage());
                    }

                    @Override
                    public void onComplete() { }
                });
    }

    public void getUTMData(){
        if (getPreferencesData(UTM) != null) {
            utm.setValue(getPreferencesData(UTM));
        }
        remoteData.getDataUTMObs().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataIndicador>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(DataIndicador dataIndicador) {
                        utm.setValue(dataIndicador.getSerie());
                        Log.d(TAG,"UTM "+dataIndicador.getSerie().get(0).getValor());
                        sharedPreferences.setIndicadorValues(UTM,dataIndicador.getSerie());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() { }
                });
    }

    public void getIMACECData(){
        if (getPreferencesData(IMACEC) != null) {
            imacec.setValue(getPreferencesData(IMACEC));
        }
        remoteData.getDataIMACECObs().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataIndicador>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(DataIndicador dataIndicador) {
                        imacec.setValue(dataIndicador.getSerie());
                        sharedPreferences.setIndicadorValues(IMACEC,dataIndicador.getSerie());
                    }

                    @Override
                    public void onError(Throwable e) { }

                    @Override
                    public void onComplete() { }
                });
    }

    public void getCobreData(){
        if (getPreferencesData(COBRE) != null) {
            cobre.setValue(getPreferencesData(COBRE));
        }
        remoteData.getDataLibraCobreObs().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataIndicador>() {
                    @Override
                    public void onSubscribe(Disposable d) { disposables.add(d); }

                    @Override
                    public void onNext(DataIndicador dataIndicador) {
                        cobre.setValue(dataIndicador.getSerie());
                        sharedPreferences.setIndicadorValues(COBRE,dataIndicador.getSerie());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() { }
                });
    }

    public void getDesempleoData(){
        if (getPreferencesData(DESEMPLEO) != null) {
            desempleo.setValue(getPreferencesData(DESEMPLEO));
        }
        remoteData.getDataDesempleoObs().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataIndicador>() {
                    @Override
                    public void onSubscribe(Disposable d) { disposables.add(d); }

                    @Override
                    public void onNext(DataIndicador dataIndicador) {
                        desempleo.setValue(dataIndicador.getSerie());
                        sharedPreferences.setIndicadorValues(DESEMPLEO,dataIndicador.getSerie());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() { }
                });
    }

    public void getBitcoinData(){
        if (getPreferencesData(BITCOIN) != null) {
            bitcoin.setValue(getPreferencesData(BITCOIN));
        }
        remoteData.getDataBitcoinObs().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataIndicador>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(DataIndicador dataIndicador) {
                        bitcoin.setValue(dataIndicador.getSerie());
                        sharedPreferences.setIndicadorValues(BITCOIN,dataIndicador.getSerie());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
