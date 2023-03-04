package entity;

public class Oeuvre {
    private int id;
    private String title;
    private String description;
    private User owner;
    private String category;

    private String url;

    public Oeuvre() {
    }

    public Oeuvre(String title, String description, User owner, String category, String url) {
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.category = category;
        this.url=url;
    }

    public Oeuvre(int id, String title, String description, User owner, String category, String url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.category = category;
        this.url =url;
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

    public User getOwner() {
        return owner;
    }

    public String getCategory() {
        return category;
    }

    public String getUrl() {
        return url;
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

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setUrl(String url) {
        this.url = url;
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
