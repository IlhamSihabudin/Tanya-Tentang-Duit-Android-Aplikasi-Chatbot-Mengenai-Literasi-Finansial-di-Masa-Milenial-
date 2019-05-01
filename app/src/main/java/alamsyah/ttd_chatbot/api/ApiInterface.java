package alamsyah.ttd_chatbot.api;

import alamsyah.ttd_chatbot.model.ResponModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ASUS on 14/12/2018.
 */

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login_ttd.php")
    Call<ResponModel> login(@Field("email") String email,
                            @Field("password") String password);
}
