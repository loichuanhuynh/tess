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

import manhinhdangnhap.manhinhdangnhap;
import manhinhtrangchu.manhnhtrangchu;

public class Ingredients extends AppCompatActivity {
    public EditText ingredients;
    public Button button3;
    public Button button4;
    public String ing;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ingredients);
        ingredients=(EditText) findViewById(R.id.ingredients);
        button3=(Button) findViewById(R.id.button3);
        button4=(Button) findViewById(R.id.button4);
        Intent pre=getIntent();
        String title=pre.getStringExtra("title");
        String ID=pre.getStringExtra("ID");
        String Hashtag=pre.getStringExtra("Hashtag");
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Ingredients.this,"Trở về trang Title post",Toast.LENGTH_LONG).show();
                Intent Z = new Intent(Ingredients.this, CreatePost.class);
                Z.putExtra("ID",ID);
                startActivity(Z);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ing=ingredients.getText().toString();
                Toast.makeText(Ingredients.this,"Đã xong Ingredients",Toast.LENGTH_LONG).show();
                Intent Z = new Intent(Ingredients.this, Instruction.class);
                Z.putExtra("title",title);
                Z.putExtra("ing",ing);
                Z.putExtra("ID",ID);
                Z.putExtra("Hashtag",Hashtag);
                startActivity(Z);
            }
        });

    }

}