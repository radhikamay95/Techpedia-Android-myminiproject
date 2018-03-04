package cit.mini.tp.techpedia;

/**
 * Created by SARADHA on 04-03-2018.
 */

class Comment {
    private String img_id;
    private String comments_typied;


    public Comment(/*String img_id,*/ String comments_typied) {
       /* this.img_id = img_id;*/
        this.comments_typied = comments_typied;

    }

    public String getImg_id() {
        return img_id;
    }

    public void setImg_id(String id) {
        this.img_id = img_id;
    }

    public String getComments_typied() {
        return comments_typied;
    }

    public void setComments_typied(String name) {
        this.comments_typied = comments_typied;
    }
}
