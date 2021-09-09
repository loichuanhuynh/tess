package manhinhcho;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tess.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import manhinhdangnhap.manhinhdangnhap;


public class manhinhcho extends AppCompatActivity {
    Button b;
    ImageView image_2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhcho);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");
        myRef.setValue("hello","Quan");
        b=(Button)findViewById(R.id.button12);
        image_2=(ImageView)findViewById(R.id.imageView12);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent U = new Intent(manhinhcho.this, manhinhdangnhap.class);
                startActivity(U);
            }
        });
    }
}