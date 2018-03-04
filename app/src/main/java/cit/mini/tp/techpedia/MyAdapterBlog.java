package cit.mini.tp.techpedia;

/**
 * Created by SARADHA on 21-02-2018.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import android.os.Environment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MyAdapterBlog  extends RecyclerView.Adapter<MyAdapterBlog.ViewHolder>{
    private Context context;
    private List<PostBaseclass> posts;
    StorageReference storageReference;
    ProgressDialog pd;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.conten_blog_activity, parent, false);
        storageReference = FirebaseStorage.getInstance().getReference(PostActivity.Constants.Storagefile_upload);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    public MyAdapterBlog(Context context, List<PostBaseclass> posts) {
        this.posts = posts;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PostBaseclass postBaseclass = posts.get(position);

        holder.imagetitle.setText(postBaseclass.getImageTitle());
        holder.imagename.setText(postBaseclass.getImageName());
        holder.description.setText(postBaseclass.getImage_Desription());
     /*   holder.filedowname.setText(postBaseclass.getFilename());*/




        Glide.with(context).load(postBaseclass.getImageURL()).into(holder.imageView);
    }



    public long getItemId(int i) {
        return i;
    }
    @Override
    public int getItemCount() {
        return posts.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView imagename,imagetitle,description,filedowname,comment;
       /* FloatingActionButton filedownload;*/
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            imagename = (TextView) itemView.findViewById(R.id.tvimgname);

            imagetitle=(TextView)itemView.findViewById(R.id.tvimgtitle);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            description=(TextView)itemView.findViewById(R.id.tvdescription);
            comment=(TextView)itemView.findViewById(R.id.tvcomment);
            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println(this);
                    Intent intent = new Intent(context, CommentActivity.class);
                    context.startActivity(intent);

                }
            });


        }

    }



    }

