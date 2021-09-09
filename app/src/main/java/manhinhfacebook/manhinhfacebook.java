package manhinhfacebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.example.tess.R;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import manhinhdangky.manhinhdangky;
import manhinhdangnhap.manhinhdangnhap;
import manhinhtrangchu.manhnhtrangchu;

public class manhinhfacebook extends AppCompatActivity {

    LoginButton loginButton;
    CallbackManager callbackManager;
    FirebaseDatabase database;
    DatabaseReference taikhoan;
    DatabaseReference matkhau;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhfacebook);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        database = FirebaseDatabase.getInstance();
        taikhoan = database.getReference("Tài khoản");
        matkhau =database.getReference("Mật khẩu");
        // If using in a fragment
   //     loginButton.setFragment(this);
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent S=new Intent(manhinhfacebook.this, manhnhtrangchu.class);
                startActivity(S);
                Toast.makeText(manhinhfacebook.this,"Đăng nhập thành công !",Toast.LENGTH_SHORT).show();
              //  handleFacebookAccessToken(loginResult.getAccessToken());
            }
            @Override
            public void onCancel() {
                Intent C=new Intent(manhinhfacebook.this, manhinhdangky.class);
                startActivity(C);
            }
            @Override
            public void onError(FacebookException exception) {
                Intent E=new Intent(manhinhfacebook.this, manhinhdangky.class);
                startActivity(E);
            }
        });
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

}
