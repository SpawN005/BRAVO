package entity;

import java.sql.Timestamp;
import java.util.Date;

public class Dons {

    private int id;
   private String title;
   private String description;
    private Timestamp date_creation;
    private Timestamp date_expiration;
    private int amount;
    private String owner;


    public Dons() {
    }

    public Dons(int id, String title, String description, Timestamp date_creation, Timestamp date_expiration, int amount, String owner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date_creation = date_creation;
        this.date_expiration = date_expiration;
        this.amount = amount;
        this.owner = owner;
    }

    public Dons(String title, String description, Timestamp date_creation, Timestamp date_expiration, int amount, String owner) {
        this.title = title;
        this.description = description;
        this.date_creation = date_creation;
        this.date_expiration = date_expiration;
        this.amount = amount;
        this.owner = owner;
    }

    public Dons(String title, String description, int amount) {
        this.title = title;
        this.description = description;
        this.amount = amount;
    }

    public Dons(String title, String description, Date date, Date dateExpiration, int amount) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Timestamp date_creation) {
        this.date_creation = date_creation;
    }

    public Timestamp getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(Timestamp date_expiration) {
        this.date_expiration = date_expiration;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Dons{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date_creation=" + date_creation +
                ", date_expiration=" + date_expiration +
                ", amount=" + amount +
                ", owner='" + owner + '\'' +
                '}';
    }
}
