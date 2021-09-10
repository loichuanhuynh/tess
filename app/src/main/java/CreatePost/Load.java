package CreatePost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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

import manhinhtrangchu.User;
import CreatePost.Postsearch;
import CreatePost.Postclass;
import manhinhtrangchu.Search;

public class Load extends AppCompatActivity {
    public TextView Name;
    int Sopost=0;
    public ImageButton imageButton;
    public TextView Se;
    public Button button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        Se=(TextView) findViewById(R.id.editText);
        Name=(TextView) findViewById(R.id.textView17);
        imageButton=(ImageButton) findViewById(R.id.imageButton4) ;
        button=(Button) findViewById(R.id.button13) ;
        Intent pre = getIntent();
        String ID= pre.getStringExtra("ID");
        String search=pre.getStringExtra("search");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Ref = database.getReference();
        Se.setText(search);
        Ref.child("Post").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Ref.child("Post").child(snapshot.getKey()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot1, @Nullable String previousChildName) {
                        Postclass A=snapshot1.getValue(Postclass.class);
                        if (A.Hashtag.contains(search)|A.Title.contains(search)) {
                            Sopost++;
                        }
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
        Ref.child("User").child(ID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User us=snapshot.getValue(User.class);
                Name.setText(us.Name);

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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Sopost==0){
                    Toast.makeText(Load.this, "Không tìm được post", Toast.LENGTH_LONG).show();
                    Intent Z = new Intent(Load.this, Search.class);
                    Z.putExtra("ID", ID);
                    startActivity(Z);
                }
                else{
                    Intent Z = new Intent(Load.this, Postsearch.class);
                    Z.putExtra("ID", ID);
                    Z.putExtra("search",search);
                    Z.putExtra("Sopost",Integer.valueOf(Sopost));
                    Z.putExtra("STT",0);
                    startActivity(Z);
                }
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Z = new Intent(Load.this, Search.class);
                Z.putExtra("ID", ID);
                startActivity(Z);
            }
        });
    }
}