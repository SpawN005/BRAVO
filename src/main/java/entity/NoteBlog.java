package entity;

public class NoteBlog {
    private int id;
    private double note;
    private Blog id_blog;
    private int user;

    public NoteBlog() {
    }

    public NoteBlog(int id, double note, Blog id_blog) {
        this.id = id;
        this.note = note;
        this.id_blog = id_blog;
    }

    public NoteBlog(double note, Blog id_blog,int user ) {
        this.note = note;
        this.id_blog = id_blog;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNote() {

        return note;
    }

    public void setNote(double note) {

        this.note = note;
    }

    public Blog getId_blog() {

        return id_blog;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void setId_blog(Blog id_blog) {
        this.id_blog = id_blog;
    }

    @Override
    public String toString() {
        return "NoteBlog{" +
                "id=" + id +
                ", note=" + note +
                ", id_blog=" + id_blog +
                '}';
    }
}


