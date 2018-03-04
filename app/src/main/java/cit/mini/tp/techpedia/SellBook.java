package cit.mini.tp.techpedia;

/**
 * Created by Saradha on 01-01-2018.
 */

public class SellBook {
    private String id;
    private String name;
    private String owner;
    private String author;


    public SellBook(String id, String name, String owner, String author) {
        this.id=id;
        this.name=name;
        this.owner=owner;
        this.author=author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

//    private String getId() {
//        return id;
//    }
//    public void setId(String id){
//        this.id=id;
//    }
//
//    public String getBookName() {
//
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getBookOwner() {
//        return owner;
//    }
//
//    public void setOwner(String owner) {
//        this.owner = owner;
//    }
//
//    public String getBookAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }

}



