package manhinhdangnhap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.SystemClock;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tess.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import manhinhdangky.manhinhdangky;
import manhinhtrangchu.manhnhtrangchu;

public class manhinhdangnhap extends AppCompatActivity {
    private EditText taikhoan;
    private EditText matkhau;
    Button login, signup;
    ImageView image_1;
    private FirebaseAuth Mauth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhdangnhap);
        Mauth = FirebaseAuth.getInstance();
        image_1 = (ImageView) findViewById(R.id.imageView2);
        taikhoan = (EditText) findViewById(R.id.taikhoan);
        matkhau = (EditText) findViewById(R.id.matkhau);
        login = (Button) findViewById(R.id.button);
        signup = (Button) findViewById(R.id.button1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taikhoan.getText().length() == 0 || matkhau.getText().length() == 0) {
                    Toast.makeText(manhinhdangnhap.this, "Đăng nhập không hợp lệ !", Toast.LENGTH_SHORT).show();
                } else {
                    Login();

                }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent U = new Intent(manhinhdangnhap.this, manhinhdangky.class);
                startActivity(U);
            }
        });


    }
    private void Login() {
        String email, password;
        email = taikhoan.getText().toString();
        password = matkhau.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(manhinhdangnhap.this, "Vui lòng nhập Email !", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(manhinhdangnhap.this, "Vui lòng nhập mật khẩu !", Toast.LENGTH_SHORT).show();
            return;
        }
        Mauth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(manhinhdangnhap.this, "Đăng nhập thành công !", Toast.LENGTH_SHORT).show();
                    Intent Z = new Intent(manhinhdangnhap.this, manhnhtrangchu.class);
                    Z.putExtra("ID",email.substring(0,email.length()-10));
                    startActivity(Z);
                } else {
                    Toast.makeText(manhinhdangnhap.this, "Tài khoản hoặc mật khẩu sai !", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}
