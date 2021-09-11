package manhinhtrangchu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tess.R;
import com.example.tess.databinding.ActivityChangeimageBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Changeimage extends AppCompatActivity {
    @NonNull ActivityChangeimageBinding binding;
    Uri imageUri;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    private ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangeimageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        im=(ImageView) findViewById(R.id.firebaseimage);
        Intent pre=getIntent();
        String ID=pre.getStringExtra("ID");
        binding.selectImagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                selectImage();


            }
        });

        binding.uploadimagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                uploadImage();

            }
        });
        binding.downloadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DowloadImage();
            }
        });
        binding.imageButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Z=new Intent(Changeimage.this,Info.class);
                Z.putExtra("ID",ID);
                startActivity(Z);
            }
        });
    }

    private void DowloadImage() {
        FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
        String fileName = user.getEmail();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference photoReference= storageReference.child("Image_User/"+fileName);//fileName là tên gmail nha
        final long ONE_MEGABYTE = 1024 * 1024;
        photoReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                im.setImageBitmap(bmp);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getApplicationContext(), "No Such file or Path found!!", Toast.LENGTH_LONG).show();
            }
        });


    }

    private void uploadImage() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading File....");
        progressDialog.show();
        FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
        String fileName = user.getEmail();
        storageReference = FirebaseStorage.getInstance().getReference("Image_User/"+fileName);
        storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                binding.firebaseimage.setImageURI(null);
                Toast.makeText(Changeimage.this,"Successfully Uploaded",Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            public void onFailure(@NonNull Exception e) {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                Toast.makeText(Changeimage.this,"Failed to Upload", Toast.LENGTH_SHORT).show();


            }
        });

    }

    private void selectImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null && data.getData() != null){

            imageUri = data.getData();
            binding.firebaseimage.setImageURI(imageUri);
        }
    }
}