package cit.mini.tp.techpedia;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

/**
 * Created by SARADHA on 17-02-2018.
 */

public class PostBaseclass {

        public String blogId;
        public String imageTitle;
        public String image_Desription;
        public String imageURL;
        public String imageName;
        public String filename;
        public String fileurl;
        public String isapproved;

       public  String comments;
       public String commentCounts;
       //public String commentCount;

        public PostBaseclass() {

        }

        public PostBaseclass(String title,String name, String desc,String url,String isapproved) {

            this.imageTitle = title;
            this.image_Desription=desc;
            this.imageURL= url;
            this.imageName=name;
            this.isapproved=isapproved;


        }

        public  PostBaseclass(String filename, String fileurl){
            this.fileurl=fileurl;
            this.filename=filename;
        }
    public  PostBaseclass(String comments){
        this.comments=comments;

    }


    public  String getImageName() {
        return imageName;
    }
    public String getImageTitle(){return  imageTitle;}

      public String getImage_Desription() {
        return image_Desription;
      }

        public String getImageURL() {
            return imageURL;
        }
    public String  getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename=filename;
    }


    public String getFileurl() {
        return fileurl;
    }
    public void setComments(String comments) {
        this.comments=comments;

    }
    public String getComments() {
        return comments;
    }

    public String getBlogId(){return blogId;}

    public void setBlogId(String blogId){
        this.blogId=blogId;
    }

    public void setCommentCounts(String commentCounts){
        this.commentCounts= commentCounts;
    }
    public String getCommentCounts(){return commentCounts;}
    public void setisapproved(String isapproved){
        this.isapproved=isapproved;
    }
    public String getisapproved(){return isapproved;}
}


