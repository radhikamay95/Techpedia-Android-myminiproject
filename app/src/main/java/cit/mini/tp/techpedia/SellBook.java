package cit.mini.tp.techpedia;

/**
 * Created by Saradha on 01-01-2018.
 */

public class SellBook {
    private String id;
    private String name;
    private String owner;
    private String author;
    public String price;
    public String edpub;
    private String contact;
    private String access;
    private String remark;


    public SellBook(String id, String name, String owner, String author,String edpub,String price,
                    String access,String contact,String remark) {
        this.id=id;
        this.name=name;
        this.owner=owner;
        this.author=author;
        this.edpub=edpub;
        this.price=price;
        this.access=access;
        this.contact=contact;
        this.remark=remark;
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


  public void setPrice(String price){
        this.price=price;
    }

   public String getEdpub() {
      return edpub;
   }

   public void setEdpub(String edpub) {
       this.edpub = edpub;
  }

  public String getContact() {
      return contact;
  }

   public void setContact(String contact) {
        this.contact = contact;
   }
    public String getAccess() {
       return access;
    }
   public void setAccess(String access) {
        this.access = access;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;

    }


    public String getPrice() {
        return price;
    }


}



