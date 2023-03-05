package entity;

public class Blog {
    private int id;
    private String title;
    private String description;
    private String content;
    private String url;
    private User author;

    public Blog() {
    }

    public Blog(int id, String title, String description, String content,String url, User author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.url=url;
        this.author = author;
    }

    public Blog(String title, String description, String content,String url, User author) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.url=url;
        this.author = author;
    }

    public Blog(String title, String description, String content, String url) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.url = url;
    }

    public Blog(int id, String title, String description, String content, User author) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", title=" + title + ", description=" + description + ", content=" + content + ", author=" + author + ", url=" + url + '}';
    }




}
