package CreatePost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tess.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import manhinhdangnhap.manhinhdangnhap;
import manhinhtrangchu.manhnhtrangchu;

public class Newpost extends AppCompatActivity {
    public TextView post;
    public Button button7;
    public Button button8;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpost);
        post =(TextView) findViewById(R.id.textView8);
        button7=(Button) findViewById(R.id.button7);
        button8=(Button) findViewById(R.id.button8);
        Intent pre=getIntent();
        String title=pre.getStringExtra("title");
        String ing=pre.getStringExtra("ing");
        String instruc=pre.getStringExtra("instruc");
        String ID=pre.getStringExtra("ID");


        post.setText("Title: \n"+title+"\n"+"Ingredients:\n"+ing+"\n"+"Instruction:\n"+instruc);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Z = new Intent(Newpost.this, Instruction.class);
                startActivity(Z);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference Ref = database.getReference().child("Post").child(ID);
                Postclass A=new Postclass(title,ing,instruc);
                Ref.push().setValue(A);
                Toast.makeText(Newpost.this,"Đã đăng",Toast.LENGTH_LONG).show();
                Intent Z = new Intent(Newpost.this, manhnhtrangchu.class);
                Z.putExtra("ID",ID);
                startActivity(Z);
            }
        });


    }

}