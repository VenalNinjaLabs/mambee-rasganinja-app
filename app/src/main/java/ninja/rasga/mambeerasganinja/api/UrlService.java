package ninja.rasga.mambeerasganinja.api;

import java.util.List;

import ninja.rasga.mambeerasganinja.ModeloRasgada;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by magdiel on 15/01/18.
 */

public interface UrlService {

    @POST("rasgadas/")
    Call<Void> salvar(@Body ModeloRasgada modeloRasgada);

    @GET("rasgadas/")
    Call<RespostaListagem> listarRasgadas();

    @GET("rasgadas/")
    Call<RespostaListagem> listarRasgadas(@Query("p") int page);

    @GET("rasgadas/{id}")
    Call<ModeloRasgada> pegarRasgada(@Path("id") String id);

    @PUT("rasgadas/{id}/up")
    Call<Void> votoPositivo(@Path("id") String id);

    @PUT("rasgadas/{id}/down")
    Call<Void> votoNegativo(@Path("id") String id);
}
