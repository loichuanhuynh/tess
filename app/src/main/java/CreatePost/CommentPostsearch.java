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

import manhinhdangnhap.manhinhdangnhap;
import manhinhtrangchu.manhnhtrangchu;
import manhinhtrangchu.User;

public class CommentPostsearch extends AppCompatActivity {
    public TextView Name;
    public Button button3;
    public EditText comment;
    ListView lv;
    public ImageButton imageButton;
    ArrayList<String> cm;
    ArrayAdapter adapter=null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentpostsearch);
        Name =(TextView) findViewById(R.id.textView17);
        button3=(Button) findViewById(R.id.button13);
        imageButton=(ImageButton) findViewById(R.id.imageButton4);
        lv =(ListView) findViewById(R.id.lv);
        comment=(EditText) findViewById(R.id.editText);
        Intent pre = getIntent();

        String ID= pre.getStringExtra("ID");
        String IDp=pre.getStringExtra("IDp");
        String search=pre.getStringExtra("search");
        int Sopost=pre.getIntExtra("Sopost",0);
        final int STT=pre.getIntExtra("STT",0);
        String key=pre.getStringExtra("key");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Ref = database.getReference();

        cm=new ArrayList<String>();
        adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,cm);
        lv.setAdapter(adapter);

        Ref.child("Post").child(ID).child(key).child("comment").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String temp=snapshot.getValue().toString();
                cm.add(temp);
                adapter.notifyDataSetChanged();
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
        Ref.child("User").child(IDp).addChildEventListener(new ChildEventListener() {
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
                String c=comment.getText().toString();
                String d=Name.getText().toString();
                Ref.child("Post").child(ID).child(key).child("comment").push().setValue(d+":"+c);
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Z = new Intent(CommentPostsearch.this, Postsearch.class);
                Z.putExtra("ID",IDp);
                Z.putExtra("STT",STT);
                Z.putExtra("search",search);
                Z.putExtra("Sopost",Sopost);
                startActivity(Z);

            }

        });

    }

}