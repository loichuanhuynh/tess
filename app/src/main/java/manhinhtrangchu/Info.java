package manhinhtrangchu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tess.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;

import java.util.Map;

import CreatePost.CreatePost;
import CreatePost.Ingredients;
import CreatePost.Postclass;
import CreatePost.Post;

public class Info extends AppCompatActivity {
    public Button button10;
    public TextView text;
    public ImageButton imageButton;
    public EditText Name;
    public EditText Date;
    public EditText Phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeinfo);
        button10=(Button) findViewById(R.id.button10);
        Name=(EditText) findViewById(R.id.editText);
        Date=(EditText) findViewById(R.id.editTextDate);
        Phone=(EditText) findViewById(R.id.editText2);
        imageButton=(ImageButton) findViewById(R.id.imageButton4);
        text=(TextView) findViewById(R.id.textView17);
        text.setText("Change Info");
        Intent pre=getIntent();
        String ID=pre.getStringExtra("ID");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Ref = database.getReference().child("User").child(ID);

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=Name.getText().toString();
                String d=Date.getText().toString();
                String p=Phone.getText().toString();
                Intent Z = new Intent(Info.this, manhnhtrangchu.class);
                Z.putExtra("ID",ID);
                Ref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Ref.child(snapshot.getKey()).child("Name").setValue(n);
                        Ref.child(snapshot.getKey()).child("Date").setValue(d);
                        Ref.child(snapshot.getKey()).child("Phone").setValue(p);
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
                startActivity(Z);
            }
        });
        imageButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Z=new Intent(Info.this,manhnhtrangchu.class);
                Z.putExtra("ID",ID);
                startActivity(Z);
            }
        }));
    }
}