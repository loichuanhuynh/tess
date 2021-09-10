package manhinhemail;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tess.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import manhinhdangky.manhinhdangky;
import manhinhdangnhap.manhinhdangnhap;
import manhinhtrangchu.manhnhtrangchu;
import manhinhtrangchu.User;

public class manhinhemail extends AppCompatActivity {
    private EditText taikhoan;
    private EditText matkhau;
    Button register;
    ImageButton Back;
    ImageView image_1;
    private FirebaseAuth Mauth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhemail);
        Mauth = FirebaseAuth.getInstance();
        taikhoan=(EditText)findViewById(R.id.taikhoanemail);
        matkhau=(EditText)findViewById(R.id.matkhauemail);
        register=(Button)findViewById(R.id.dangkyemail);
        Back= (ImageButton) findViewById(R.id.Backemail);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Z = new Intent(manhinhemail.this, manhinhdangky.class);
                startActivity(Z);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();

            }
        });
    }
    private void Register()
    {
        String email, password;
        email = taikhoan.getText().toString();
        password = matkhau.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(manhinhemail.this, "Vui lòng nhập Email !", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(manhinhemail.this, "Vui lòng nhập mật khẩu !", Toast.LENGTH_SHORT).show();
            return;
        }
        Mauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(manhinhemail.this, "Đăng ký thành công !", Toast.LENGTH_SHORT).show();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference Ref = database.getReference().child("User").child(email.substring(0,email.length()-10));
                    User us=new User();
                    us.Name=email.substring(0,email.length()-10);
                    us.Date="01/01/2000";
                    us.Phone="0123456789";
                    Ref.push().setValue(us);
                    Intent Z = new Intent(manhinhemail.this, manhnhtrangchu.class);
                    Z.putExtra("ID",email.substring(0,email.length()-10));
                    startActivity(Z);
                } else {
                    Toast.makeText(manhinhemail.this, "Tài khoản trùng hoặc không hợp lệ, vui lòng nhập lại tài khoản !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}