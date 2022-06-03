package cl.nelsonmc.indicadores.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import cl.nelsonmc.indicadores.model.DataIndicador;
import cl.nelsonmc.indicadores.model.SerieIndicador;
import cl.nelsonmc.indicadores.webServices.WebClient;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainRepository {
    private String TAG = "RemoteRepository";
    WebClient client;
    private Disposable disposable;
    public MainRepository(WebClient client) {
        this.client = client;
    }

    public void clearDisposable(){
        disposable.dispose();
    }

    public MutableLiveData<List<SerieIndicador>> getDolarData() {
        MutableLiveData<List<SerieIndicador>> data = new MutableLiveData<>();
        client.getDataDolarObs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataIndicador>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }
                    @Override
                    public void onNext(DataIndicador dataIndicador) {
                        data.setValue(dataIndicador.getSerie());
                        Log.d(TAG,dataIndicador.getSerie().get(0).getValor());
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
        return data;
    }

}
