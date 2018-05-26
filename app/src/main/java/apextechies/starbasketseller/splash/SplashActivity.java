package apextechies.starbasketseller.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.firebase.digitsmigrationhelpers.AuthMigrator;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import apextechies.starbasketseller.activity.MainActivity;
import apextechies.starbasketseller.common.AppConstants;
import apextechies.starbasketseller.common.ClsGeneral;
import apextechies.starbasketseller.model.LoginModel;
import apextechies.starbasketseller.retrofit.DownlodableCallback;
import apextechies.starbasketseller.retrofit.RetrofitDataProvider;
import io.fabric.sdk.android.Fabric;
import java.util.Collections;

import apextechies.starbasketseller.R;

/**
 * Created by Shankar on 3/30/2018.
 */

public class SplashActivity extends AppCompatActivity {
    public static final int RC_SIGN_IN = 111;
    private RetrofitDataProvider retrofitDataProvider ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splas);
        Fabric.with(this, new Crashlytics());
        retrofitDataProvider = new RetrofitDataProvider(this);
       // FirebaseApp.initializeApp(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        Thread timer= new Thread()
        {
            public void run()
            {
                try
                {
                    //Display for 3 seconds
                    sleep(3000);
                }
                catch (InterruptedException e)
                {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                finally
                {

                    checkSession();
                }
            }
        };
        timer.start();
    }


    private void checkSession() {
        AuthMigrator.getInstance().migrate(true).addOnSuccessListener(this,
                new OnSuccessListener() {

                    @Override
                    public void onSuccess(Object o) {
                        FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
                        if (u != null) {
                            callApi(u.getPhoneNumber());

                        } else {
                            startActivityForResult(
                                    AuthUI.getInstance()
                                            .createSignInIntentBuilder()
                                            .setProviders(
                                                    //  Arrays.asList(
                                                    Collections.singletonList(
                                                            new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build()
                                                    )
                                            )
                                            .setTheme(R.style.LoginTheme)
                                            .setLogo(R.mipmap.ic_launcher)
                                            .build(),
                                    RC_SIGN_IN);
                        }
                    }
                }).addOnFailureListener(this,
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                    }
                });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == ResultCodes.OK) {
                FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
                callApi(response.getPhoneNumber());

            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    Log.e("Login", "Login canceled by User");
                }
                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                }
                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    Log.e("Login", "Unknown Error");
                }
            }
            Log.e("Login", "Unknown sign in response");

        }
    }

    private void callApi(String phoneNumber) {
        retrofitDataProvider.login(phoneNumber, new DownlodableCallback<LoginModel>() {
            @Override
            public void onSuccess(LoginModel result) {

                if (result.getStatus().equals("true")){
                    ClsGeneral.setPreferences(SplashActivity.this, AppConstants.INSTANCE.getUSERID(), result.getData().get(0).getId());
                    if (!TextUtils.isEmpty(result.getData().get(0).getEmail())){
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }else {
                        startActivity(new Intent(SplashActivity.this, SignUpActivity.class)
                        .putExtra("mobile",result.getData().get(0).getPhone()));
                        finish();
                    }
                }
                else {
                    Toast.makeText(SplashActivity.this, ""+result.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String error) {

            }

            @Override
            public void onUnauthorized(int errorNumber) {

            }
        });
    }


}
