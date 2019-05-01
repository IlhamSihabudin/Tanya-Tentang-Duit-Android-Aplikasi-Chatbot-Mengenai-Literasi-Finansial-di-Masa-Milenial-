package alamsyah.ttd_chatbot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import alamsyah.ttd_chatbot.api.ApiClient;
import alamsyah.ttd_chatbot.api.ApiInterface;
import alamsyah.ttd_chatbot.model.ResponModel;
import alamsyah.ttd_chatbot.model.User;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    PrefManager prefManager;
    ProgressDialog pd;
    EditText etUser, etPass;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prefManager = new PrefManager(this);

        if (!prefManager.getNameUser().equals("Empty")){
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }

        etUser = (EditText) findViewById(R.id.username);
        etPass = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        pd = new ProgressDialog(this);
        pd.setMessage("Loading..");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setCancelable(false);
                pd.show();
                String user = etUser.getText().toString();
                final String pass = etPass.getText().toString();
                ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
                Call<ResponModel> log = api.login(user,pass);
                log.enqueue(new Callback<ResponModel>() {
                    @Override
                    public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                        pd.hide();
                        ResponModel respon = response.body();
                        List<User> user = respon.getResult();
                        if (respon.getKode().equals("1")){
//                            Alerter.create(Login.this)
//                                    .setText("Success")
//                                    .setDuration(10000)
//                                    .enableSwipeToDismiss()
//                                    .show();
                            Toast.makeText(Login.this, "Nama : " + user.get(0).getNamauser(), Toast.LENGTH_SHORT).show();
                            prefManager.setNameUser(user.get(0).getNamauser());
                            new SweetAlertDialog(Login.this, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Success")
                                    .setContentText("Anda Berhasil Login")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {

//                                            Toast.makeText(Login.this, "Nama : " + user.get(0).getNamauser(), Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(Login.this, MainActivity.class);
                                            //i.putExtra("nama", user.get(0).getNamauser());
                                            startActivity(i);
                                        }
                                    })
                                    .show();


                        }else{
                            Toast.makeText(Login.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponModel> call, Throwable t) {
                        Toast.makeText(Login.this, "Not Valid", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        finishAffinity();
    }
}

