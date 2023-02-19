package entity;

public class Blog {
    private int id;
    private String title;
    private String description;
    private String content;
    private int author;


    public Blog() {
    }

    public Blog(int id, String title, String description, String content, int author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.author = author;
    }

    public Blog(String title, String description, String content, int author) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.author = author;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", title=" + title + ", description=" + description + ", content=" + content + ", author=" + author + '}';
    }



}
