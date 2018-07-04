package utsman.kucingapes.retrofitsimple;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetData {
    @GET("/photos")
    Call<List<Model>> getPhoto();
}
