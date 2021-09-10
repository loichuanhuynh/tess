package manhinhtrangchu;

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

import CreatePost.Postclass;
import manhinhtrangchu.User;
import CreatePost.Postsearch;
import CreatePost.Load;

public class Search extends AppCompatActivity {
    public TextView Name;
    public Button button3;
    public EditText search;
    public ImageButton imageButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Name =(TextView) findViewById(R.id.textView17);
        button3=(Button) findViewById(R.id.button13);
        imageButton=(ImageButton) findViewById(R.id.imageButton4);
        search=(EditText) findViewById(R.id.editText);
        Intent pre = getIntent();
        String ID= pre.getStringExtra("ID");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Ref = database.getReference();



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

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Hashtag = search.getText().toString();
                Intent Z = new Intent(Search.this, Load.class);
                Z.putExtra("ID", ID);
                Z.putExtra("search", Hashtag);
                startActivity(Z);

            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Z = new Intent(Search.this, manhnhtrangchu.class);
                Z.putExtra("ID",ID);
                Z.putExtra("STT",0);
                startActivity(Z);
            }

        });

    }
}