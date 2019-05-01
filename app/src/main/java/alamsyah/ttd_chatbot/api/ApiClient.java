package alamsyah.ttd_chatbot.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ASUS on 14/12/2018.
 */

public class ApiClient {
//    private static final String base_url = "http://192.168.137.1:81/Android/qr/";
    private static final String base_url = "http://tanyatentangduit.xyz/api/";

    private static Retrofit retrofit;

    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
