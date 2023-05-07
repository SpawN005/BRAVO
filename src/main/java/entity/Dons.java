package entity;

import java.time.LocalDate;

public class Dons {

    private int id;
    private String title;
    private String description;
    private LocalDate date_creation;
    private LocalDate date_expiration;
    private int amount;
    private CategorieDon cat;
    private User owner;


    public Dons() {
    }

    public Dons(int id, String title, String description, LocalDate date_creation, LocalDate date_expiration, int amount, User owner,CategorieDon cat) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date_creation = date_creation;
        this.date_expiration = date_expiration;
        this.amount = amount;
        this.owner = owner;
        this.cat = cat;
    }

    public Dons(String title, String description, LocalDate date_creation, LocalDate date_expiration, int amount, User owner,CategorieDon cat) {
        this.title = title;
        this.description = description;
        this.date_creation = date_creation;
        this.date_expiration = date_expiration;
        this.amount = amount;
        this.owner = owner;
        this.cat=cat;
    }

    public Dons(String title, String description, int amount) {
        this.title = title;
        this.description = description;
        this.amount = amount;
    }

    public Dons(String title, String description, LocalDate dateCreation, LocalDate dateExpiration, int amount) {
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

    public LocalDate getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(LocalDate date_creation) {
        this.date_creation = date_creation;
    }


    public LocalDate getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(LocalDate date_expiration) {
        this.date_expiration = date_expiration;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getOwner() {
        return owner;
    }

    public CategorieDon getCat() {
        return cat;
    }

    public void setCat(CategorieDon cat) {
        this.cat = cat;
    }

    public void setOwner(User owner) {
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
