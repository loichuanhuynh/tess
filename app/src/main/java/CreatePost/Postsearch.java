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
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

import manhinhdangnhap.manhinhdangnhap;
import manhinhtrangchu.manhnhtrangchu;
import manhinhtrangchu.User;
import manhinhtrangchu.Search;

public class Postsearch extends AppCompatActivity {
    public TextView post;
    public TextView Name;
    public Button button3;
    public TextView like;
    public ImageButton imageButton;
    public ImageButton imageButton2;
    public ImageButton imageButton3;
    public TextView text;
    public TextView text2;
    public ImageButton imageButton5;
    public ImageButton imageButton7;
    public int dem=0;
    public int l=0;
    public String key="";
    public String key1="";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postsearch);
        Name =(TextView) findViewById(R.id.textView4);
        like =(TextView) findViewById(R.id.textView9);
        post =(TextView) findViewById(R.id.textView7);
        text =(TextView) findViewById(R.id.textView16);
        text2 =(TextView) findViewById(R.id.textView19);
        button3=(Button) findViewById(R.id.button3);
        imageButton=(ImageButton) findViewById(R.id.imageButton);
        imageButton2=(ImageButton) findViewById(R.id.imageButton2);
        imageButton3=(ImageButton) findViewById(R.id.imageButton3);
        imageButton5=(ImageButton) findViewById(R.id.imageButton5);
        imageButton7=(ImageButton) findViewById(R.id.imageButton7);
        Intent pre = getIntent();
        String ID= pre.getStringExtra("ID");
        String search=pre.getStringExtra("search");
        final int STT=pre.getIntExtra("STT",0);
        final int Sopost=pre.getIntExtra("Sopost",0);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Ref = database.getReference();
        dem=0;

        Ref.child("Post").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Ref.child("Post").child(snapshot.getKey()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot1, @Nullable String previousChildName) {
                        Postclass A=snapshot1.getValue(Postclass.class);
                        if (dem==STT & (A.Hashtag.contains(search)|A.Title.contains(search))) {
                            key = snapshot1.getKey();
                            key1=snapshot.getKey();
                            post.setText(A.Hashtag + "\n");
                            post.append("Title:\n");
                            post.append(A.Title + "\n");
                            post.append("Ingredients:\n");
                            post.append(A.Ingredients + "\n");
                            post.append("Instruction:\n");
                            post.append(A.Instruction + "\n" + key1 + "\n" + key);
                            D();
                            Ref.child("Post").child(key1).child(key).child("like").addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                    String te = snapshot.getValue(String.class);
                                    l += 1;
                                    like.setText(String.valueOf(l));
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

        Ref.child("User").child(key1).addChildEventListener(new ChildEventListener() {
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
                Intent Z = new Intent(Postsearch.this, Search.class);
                Z.putExtra("ID",ID);
                startActivity(Z);
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ref.child("Post").child(key1).child(key).child("like").push().setValue(ID);

            }

        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Z = new Intent(Postsearch.this, CommentPostsearch.class);
                Z.putExtra("STT",STT);
                Z.putExtra("search",search);
                Z.putExtra("ID",key1);
                Z.putExtra("IDp",ID);
                Z.putExtra("key",key);
                startActivity(Z);
            }

        });
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (STT==Sopost-1){
                    Toast.makeText(Postsearch.this, "Phía sau không còn post nào", Toast.LENGTH_LONG).show();

                }
                else {
                    Intent Z = new Intent(Postsearch.this, Postsearch.class);
                    Z.putExtra("ID", ID);
                    Z.putExtra("STT", STT + 1);
                    Z.putExtra("search",search);
                    Z.putExtra("Sopost",Sopost);
                    startActivity(Z);
                }
            }

        });
        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (STT==0){
                    Toast.makeText(Postsearch.this, "Trước đó không còn post nào", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent Z = new Intent(Postsearch.this, Postsearch.class);
                    Z.putExtra("ID", ID);
                    Z.putExtra("STT", STT - 1);
                    Z.putExtra("search",search);
                    Z.putExtra("Sopost",Sopost);
                    startActivity(Z);
                }
            }

        });
    }
    public void D(){
        dem+=1;
    }
}