package manhinhdangky;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tess.R;

import manhinhdangnhap.manhinhdangnhap;
import manhinhemail.manhinhemail;
import manhinhfacebook.manhinhfacebook;

public class manhinhdangky extends AppCompatActivity {
    ImageView IconGoogle;
    Button Google,Facebook,Email;
    ImageButton Back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhdangky);

        Back =(ImageButton)findViewById(R.id.Back);
        Google=(Button)findViewById(R.id.Google);
        Facebook=(Button)findViewById(R.id.Facebook);
        Email=(Button)findViewById(R.id.Email);
        Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent G = new Intent(manhinhdangky.this, manhinhemail.class);
                startActivity(G);
            }
        });
        Facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent F =new Intent(manhinhdangky.this, manhinhfacebook.class);
                startActivity(F);
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Z = new Intent(manhinhdangky.this, manhinhdangnhap.class);
                startActivity(Z);
            }
        });
    }

}