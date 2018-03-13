package cit.mini.tp.techpedia;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;

import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import android.net.Uri;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PostActivity extends AppCompatActivity  {

    ImageView postImageView;

    EditText postdesc,posttitle,filename,postname;
    //final static int PICK_PDF_CODE = 2342;

    // Folder path for Firebase Storage.
    String Storage_Path = "Blog/";

    // Root Database Name for Firebase Database.
    String Database_Path = "Blog";
    // Creating StorageReference and DatabaseReference object.
    StorageReference storageReference;
        DatabaseReference databaseReference;
    DatabaseReference databaseReference_file;
    //private StorageReference fileRef;


    // Creating URI.
    Uri FilePathUri;
    Uri fileUploadURI;
    ProgressDialog progressDialog ;

    Button  post;
    private static final  int PDF=1;
    private static final int GALLERY_REQUEST_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        // Assign FirebaseStorage instance to storageReference.
        storageReference = FirebaseStorage.getInstance().getReference();
        // Assign FirebaseDatabase instance with root database name.
        databaseReference = FirebaseDatabase.getInstance().getReference(Constants.Database_upload);
        databaseReference_file = FirebaseDatabase.getInstance().getReference(Constants.Databasefile_upload);
        // Assigning Id to ProgressDialog.
        progressDialog = new ProgressDialog(PostActivity.this);

        post = (Button) findViewById(R.id.btpost);
        posttitle = (EditText) findViewById(R.id.ettitle);
        postname=(EditText)findViewById(R.id.etpostername);
        postImageView = (ImageView) findViewById(R.id.iv_post);
        postdesc = (EditText) findViewById(R.id.etdesc);





        /*mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("users").child(mCurrentUser.getUid());*/

        postImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
            }
        });


        // posting to Firebase
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //method
                try {
                    uploadimage();
                    uploadFile();
                }catch(Exception e){

                }
                Intent intent=new Intent(PostActivity.this,BlogView.class);
                startActivity(intent);

            }

        });



    }@Override
    // image from gallery result
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK
                && data != null && data.getData() != null ){
                FilePathUri = data.getData();
            //  postImageView.setImageURI(FilePathUri);
            try {

                // Getting selected image into Bitmap.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);

                // Setting up bitmap selected image into ImageView.
                postImageView.setImageBitmap(bitmap);

                // After selecting image change choose button above text.
                // ChooseButton.setText("Image Selected");

            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
       else if (requestCode == PDF&& resultCode == RESULT_OK && data != null && data.getData() != null) {
            //if a file is selected
            if (data.getData() != null) {
                //uploading the file
                fileUploadURI = data.getData();
            }else{
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
    }


        public String GetFileExtension(Uri
        uri) {

            ContentResolver contentResolver = getContentResolver();

            MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

            // Returning the file Extension.
            return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;
/* String TempImageTitle = posttitle.getText().toString().trim();
                                String TempImageDesc = postdesc.getText().toString().trim();
*/
        }

        public void uploadimage() throws Exception{
            //checking if file is available
            if (FilePathUri != null) {
                //displaying progress dialog while image is uploading
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading");
                progressDialog.show();

                //getting the storage reference
                StorageReference sRef = storageReference.child(Constants.Storage_upload+ System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));

                //adding the file to reference
                sRef.putFile(FilePathUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                //dismissing the progress dialog
                                progressDialog.dismiss();

                                //displaying success toast
                                Toast.makeText(getApplicationContext(), "Photo Uploaded ", Toast.LENGTH_SHORT).show();

                                //creating the upload object to store uploaded image details
                                PostBaseclass postBaseclass = new PostBaseclass(posttitle.getText().toString().trim(),postname.getText().toString().trim(),postdesc.getText().toString().trim(), taskSnapshot.getDownloadUrl().toString());

                                //adding an upload to firebase database
                                String uploadId = databaseReference.push().getKey();
                                databaseReference.child(uploadId).setValue(postBaseclass);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                //displaying the upload progress
                                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                                progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                            }
                        });
            } else {
                  throw new Exception("File path null");
            }

        }

        public void uploadFile() {
            if (fileUploadURI != null) {
                //displaying progress dialog while image is uploading
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading");
                progressDialog.show();
       String type = MimeTypeMap.getFileExtensionFromUrl(fileUploadURI.toString());
                //getting the storage reference
                StorageReference sRef = storageReference.child(Constants.Storagefile_upload+ System.currentTimeMillis() + "." + type);

                //adding the file to reference
                sRef.putFile(fileUploadURI)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                //dismissing the progress dialog
                                progressDialog.dismiss();

                                //displaying success toast
                                Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_SHORT).show();

                                //creating the upload object to store uploaded image details
                                PostBaseclass postBaseclass = new PostBaseclass(filename.getText().toString().trim(), taskSnapshot.getDownloadUrl().toString());

                                //adding an upload to firebase database
                                String uploadId = databaseReference_file.push().getKey();
                                databaseReference_file.child(uploadId).setValue(postBaseclass);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                //displaying the upload progress
                                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                                progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                            }
                        });
            } else {

            }
    }





        public class Constants{
            public static final String Storage_upload="BlogImages/";
            public static final String Database_upload="Blog";
            public static final String Storagefile_upload="File/";
            public static final String Databasefile_upload="FileName";

        }


}