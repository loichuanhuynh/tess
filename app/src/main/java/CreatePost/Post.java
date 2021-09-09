package CreatePost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tess.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import manhinhdangnhap.manhinhdangnhap;
import manhinhtrangchu.manhnhtrangchu;

public class Post extends AppCompatActivity {
    public TextView post;
    public TextView Name;
    public Button button3;
    public TextView like;
    public ImageButton imageButton;
    public ImageButton imageButton2;
    public ImageButton imageButton3;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Name =(TextView) findViewById(R.id.textView4);
        like =(TextView) findViewById(R.id.textView9);
        post =(TextView) findViewById(R.id.textView7);
        button3=(Button) findViewById(R.id.button3);
        imageButton=(ImageButton) findViewById(R.id.imageButton);
        imageButton2=(ImageButton) findViewById(R.id.imageButton2);
        imageButton3=(ImageButton) findViewById(R.id.imageButton3);
        Intent pre = getIntent();
        String ID= pre.getStringExtra("ID");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Ref = database.getReference().child("Post").child(ID);
        final ArrayList<Postclass> A=new ArrayList<Postclass>();

        Ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
               A.add(snapshot.getValue(Postclass.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        final ArrayList<String> L=new ArrayList<String>();
        if (1==1) {
            Ref.child("Like").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    L.add(snapshot.getValue().toString());
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        like.setText(L.size());
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Z = new Intent(Post.this, manhnhtrangchu.class);
                Z.putExtra("ID",ID);
                startActivity(Z);
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });
    }

}