package alamsyah.ttd_chatbot;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ASUS on 24/09/2018.
 */

public class PrefManager {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    private static final int PREF_MODE = 0;
    private static final String PREF_NAME = "Session_Manager";
    private static final String IS_FIRST_TIME = "isFirstTime";

    private static final String NAME_USER = "nameUser";

   public PrefManager(Context context){
       this.context = context;
       preferences = context.getSharedPreferences(PREF_NAME, PREF_MODE);
       editor = preferences.edit();
   }

    public void setNameUser(String nameUser) {
        editor.putString(NAME_USER, nameUser);
        editor.commit();
    }

    public String getNameUser() {
        return preferences.getString(NAME_USER, "Empty");
    }
}
