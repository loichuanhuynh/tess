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

import java.util.ArrayList;
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
        String Hashtag=pre.getStringExtra("Hashtag");
        post.setText(Hashtag+"\n"+"Title: \n"+title+"\n"+"Ingredients:\n"+ing+"\n"+"Instruction:\n"+instruc);
        if (title==null | ing==null | instruc==null | Hashtag==null| !check(Hashtag)) {
            Toast.makeText(Newpost.this, "Lỗi, do bạn không làm đủ các bước, mời làm lại từ đầu", Toast.LENGTH_LONG).show();

            button7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent Z = new Intent(Newpost.this, CreatePost.class);
                    Z.putExtra("ID",ID);
                    startActivity(Z);
                }
            });
            button8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Newpost.this, "Lỗi, do bạn không làm đủ 3 bược, mời nhấn trở về để làm lại từ đầu", Toast.LENGTH_LONG).show();

                }
            });
        }
        else {
            button7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Newpost.this, "Trở về trang Istruction, bạn cần nhập lại Instruction", Toast.LENGTH_LONG).show();
                    Intent Z = new Intent(Newpost.this, CreatePost.class);
                    Z.putExtra("ing",ing);
                    Z.putExtra("ID",ID);
                    Z.putExtra("title",title);
                    Z.putExtra("Hashtag",Hashtag);
                    startActivity(Z);
                }
            });
            button8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference Ref = database.getReference().child("Post").child(ID);
                    //ArrayList<String> like=new ArrayList<String>();
                    Postclass A = new Postclass(title, ing, instruc,Hashtag);
                    Ref.push().setValue(A);
                    Toast.makeText(Newpost.this, "Đã đăng", Toast.LENGTH_LONG).show();
                    Intent Z = new Intent(Newpost.this, manhnhtrangchu.class);
                    Z.putExtra("ID", ID);
                    startActivity(Z);
                }
            });
        }

    }
    public boolean check(String a){
        return a.contains("#");
    }
}