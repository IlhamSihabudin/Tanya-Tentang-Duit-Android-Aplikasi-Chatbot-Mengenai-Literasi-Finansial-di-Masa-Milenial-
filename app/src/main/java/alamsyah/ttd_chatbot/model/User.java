package alamsyah.ttd_chatbot.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ASUS on 14/12/2018.
 */

public class User {
    @SerializedName("nama")
    String namauser;
    @SerializedName("email")
    String email;

    public String getNamauser() {
        return namauser;
    }

    public void setNamauser(String namauser) {
        this.namauser = namauser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
