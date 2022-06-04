package cl.nelsonmc.indicadores.repository;

import cl.nelsonmc.indicadores.model.DataIndicador;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RemoteData {
    @GET("api/dolar")
    Observable<DataIndicador> getDataDolarObs();

    @GET("api/euro")
    Observable<DataIndicador> getDataEuroObs();

    @GET("api/uf")
    Observable<DataIndicador> getDataUFObs();

    @GET("api/ivp")
    Observable<DataIndicador> getDataIVPObs();

    @GET("api/ipc")
    Observable<DataIndicador> getDataIPCObs();

    @GET("api/utm")
    Observable<DataIndicador> getDataUTMObs();

    @GET("api/imacec")
    Observable<DataIndicador> getDataIMACECObs();

    @GET("api/libra_cobre")
    Observable<DataIndicador> getDataLibraCobreObs();

    @GET("api/tasa_desempleo")
    Observable<DataIndicador> getDataDesempleoObs();

    @GET("api/bitcoin")
    Observable<DataIndicador> getDataBitcoinObs();

}
