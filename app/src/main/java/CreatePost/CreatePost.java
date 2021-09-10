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

public class CreatePost extends AppCompatActivity {
    public  EditText Title_post;
    public EditText Hashtag;
    public  Button button;
    public Button button2;
    public String titlepost;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_post);
        Title_post = (EditText) findViewById(R.id.Title_post);
        Hashtag = (EditText) findViewById(R.id.hashtag);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        Intent pre=getIntent();
        String ID=pre.getStringExtra("ID");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreatePost.this,"Thoát tạo post, trở về trang chủ",Toast.LENGTH_LONG).show();
                Intent Z = new Intent(CreatePost.this, manhnhtrangchu.class);
                Z.putExtra("ID",ID);
                startActivity(Z);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titlepost=Title_post.getText().toString();
                String hash=Hashtag.getText().toString();
                Toast.makeText(CreatePost.this,"Đã xong title",Toast.LENGTH_LONG).show();
                Intent Z = new Intent(CreatePost.this, Ingredients.class);
                Z.putExtra("title",titlepost);
                Z.putExtra("Hashtag",hash);
                Z.putExtra("ID",ID);

                startActivity(Z);
            }
        });


    }

}