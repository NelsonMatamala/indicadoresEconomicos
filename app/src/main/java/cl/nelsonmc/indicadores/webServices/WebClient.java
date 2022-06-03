package cl.nelsonmc.indicadores.webServices;

import cl.nelsonmc.indicadores.model.DataIndicador;
import cl.nelsonmc.indicadores.model.SerieIndicador;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WebClient {
    @GET("api/dolar")
    Observable<DataIndicador> getDataDolarObs();

    @GET("api/dolar")
    Call<DataIndicador> getDataDolar();

    @GET("api/euro")
    Call<DataIndicador> getDataEuro();

    @GET("api/uf")
    Call<DataIndicador> getDataUF();

    @GET("api/ivp")
    Call<DataIndicador> getDataIVP();

    @GET("api/ipc")
    Call<DataIndicador> getDataIPC();

    @GET("api/utm")
    Call<DataIndicador> getDataUTM();

    @GET("api/imacec")
    Call<DataIndicador> getDataIMACEC();

    @GET("api/libra_cobre")
    Call<DataIndicador> getDataLibraCobre();

    @GET("api/tasa_desempleo")
    Call<DataIndicador> getDataDesempleo();

    @GET("api/bitcoin")
    Call<DataIndicador> getDataBitcoin();

}
