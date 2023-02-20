package entity;

public class Commentaire {
    private int id;
    private String content;
    private Blog id_blog;
    private User id_user;

    public Commentaire() {
    }

    public Commentaire(int id, String content, Blog id_blog, User id_user) {
        this.id = id;
        this.content = content;
        this.id_blog = id_blog;
        this.id_user = id_user;
    }

    public Commentaire(String content, Blog id_blog, User id_user) {
        this.content = content;
        this.id_blog = id_blog;
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Blog getId_blog() {
        return id_blog;
    }

    public void setId_blog(Blog id_blog) {
        this.id_blog = id_blog;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", id_blog=" + id_blog +
                ", id_user=" + id_user +
                '}';
    }
}

