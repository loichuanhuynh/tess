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

public class Instruction extends AppCompatActivity {
    public EditText ins;
    public Button button5;
    public Button button6;
    public String instruc;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        ins =(EditText) findViewById(R.id.instruction);
        button5=(Button) findViewById(R.id.button5);
        button6=(Button) findViewById(R.id.button6);
        Intent pre=getIntent();
        String title=pre.getStringExtra("title");
        String ing=pre.getStringExtra("ing");
        String ID=pre.getStringExtra("ID");
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Instruction.this,"Trở về trang Ingredients, nhập lại từ bước Ingredients",Toast.LENGTH_LONG).show();
                Intent Z = new Intent(Instruction.this, Ingredients.class);
                Z.putExtra("ID",ID);
                Z.putExtra("title",title);
                startActivity(Z);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instruc=ins.getText().toString();
                Toast.makeText(Instruction.this,"Đã xong Instruction",Toast.LENGTH_LONG).show();

                Intent Z = new Intent(Instruction.this, Newpost.class);
                Z.putExtra("title",title);
                Z.putExtra("ing",ing);
                Z.putExtra("instruc",instruc);
                Z.putExtra("ID",ID);
                startActivity(Z);
            }
        });


    }

}