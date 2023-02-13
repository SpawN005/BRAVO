package entity;

public class Oeuvre {
    private int id;
    private String title;
    private String description;
    private String owner;
    private String category;

    public Oeuvre() {
    }

    public Oeuvre(String title, String description, String owner, String category) {
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.category = category;
    }

    public Oeuvre(int id, String title, String description, String owner, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getOwner() {
        return owner;
    }

    public String getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Oeuvre{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", owner='" + owner + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
