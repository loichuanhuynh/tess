package manhinhtrangchu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tess.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import CreatePost.CreatePost;
import CreatePost.Ingredients;
import CreatePost.Postclass;
import CreatePost.Post;
import manhinhdangnhap.manhinhdangnhap;

public class manhnhtrangchu extends AppCompatActivity {
    public Button button;
    public Button button2;
    public Button button3;
    public Button button4;
    public TextView Name;
    public TextView Date;
    public TextView Phone;
    public TextView like;
    public TextView post;
    public TextView rate;
    public TextView like1;
    public TextView post1;
    public TextView rate1;
    public int l=0;
    public int p=0;
    public float r=0;
    public int dem=1;
    public User user;
    public ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhnhtrangchu);
        imageButton=(ImageButton) findViewById(R.id.imageButton4);
        button=(Button) findViewById(R.id.button9);
        button2=(Button) findViewById(R.id.button10) ;
        button3=(Button) findViewById(R.id.button11) ;
        button4=(Button) findViewById(R.id.button15) ;
        Name=(TextView) findViewById(R.id.textView10);
        Date=(TextView) findViewById(R.id.textView11);
        Phone=(TextView) findViewById(R.id.textView12);
        post=(TextView) findViewById(R.id.textView21);
        like=(TextView) findViewById(R.id.textView22);
        rate=(TextView) findViewById(R.id.textView23);
        post1=(TextView) findViewById(R.id.textView13);
        like1=(TextView) findViewById(R.id.textView14);
        rate1=(TextView) findViewById(R.id.textView15);

        Intent pre=getIntent();
        String ID=pre.getStringExtra("ID");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Ref = database.getReference();
        Ref.child("User").child(ID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User temp=snapshot.getValue(User.class);
                Name.append(temp.Name);
                Date.append(temp.Date);
                Phone.append(temp.Phone);
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
        Ref.child("Post").child(ID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Postclass a=snapshot.getValue(Postclass.class);
                p+=1;
                post.setText(String.valueOf(p));
                Ref.child("Post").child(ID).child(snapshot.getKey()).child("like").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        String key=snapshot.getValue(String.class);
                        l+=1;
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
                Ref.child("Post").child(ID).child(snapshot.getKey()).child("rate").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        String ra=snapshot.getValue(String.class);
                        r+=Float.valueOf(ra);
                        rate.setText(String.valueOf(r/dem));
                        dem+=1;
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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Z = new Intent(manhnhtrangchu.this, CreatePost.class);
                Z.putExtra("ID",ID);
                startActivity(Z);
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Z = new Intent(manhnhtrangchu.this, manhinhdangnhap.class);
                startActivity(Z);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Z = new Intent(manhnhtrangchu.this, Info.class);
                Z.putExtra("ID",ID);
                startActivity(Z);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Z = new Intent(manhnhtrangchu.this, Search.class);
                Z.putExtra("ID",ID);
                startActivity(Z);
            }
        });
        button2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (p==0){
                    Toast.makeText(manhnhtrangchu.this, "Bạn hiện không có bài post nào", Toast.LENGTH_LONG).show();

                }
                else {
                    Intent Z=new Intent(manhnhtrangchu.this,Post.class);
                    Z.putExtra("ID", ID);
                    Z.putExtra("STT", 0);
                    Z.putExtra("Sopost", p);
                    Z.putExtra("IDp",ID);
                    startActivity(Z);
                }
            }
        }));
    }
}