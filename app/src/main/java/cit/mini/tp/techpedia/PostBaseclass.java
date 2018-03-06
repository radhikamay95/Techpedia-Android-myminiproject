package cit.mini.tp.techpedia;

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
       public  String comments;
       public String commentCounts;
       //public String commentCount;

        public PostBaseclass() {

        }

        public PostBaseclass(String title,String name, String desc,String url) {

            this.imageTitle = title;
            this.image_Desription=desc;
            this.imageURL= url;
            this.imageName=name;


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
    public String getFilename() {
        return filename;
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

}


