package manhinhtrangchu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tess.R;

import CreatePost.CreatePost;
import CreatePost.Ingredients;
import CreatePost.Post;

public class manhnhtrangchu extends AppCompatActivity {
    Button button;
    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhnhtrangchu);
        button=(Button) findViewById(R.id.button9);
        button2=(Button) findViewById(R.id.button10) ;
        Intent pre=getIntent();
        String ID=pre.getStringExtra("ID");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Z = new Intent(manhnhtrangchu.this, CreatePost.class);
                Z.putExtra("ID",ID);
                startActivity(Z);
            }
        });
        button2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Z=new Intent(manhnhtrangchu.this,Post.class);
                Z.putExtra("ID",ID);
                startActivity(Z);
            }
        }));
    }
}