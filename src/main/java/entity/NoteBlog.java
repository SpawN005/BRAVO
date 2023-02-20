package entity;

public class NoteBlog {
    private int id;
    private int note;
    private Blog id_blog;

    public NoteBlog() {
    }

    public NoteBlog(int id, int note, Blog id_blog) {
        this.id = id;
        this.note = note;
        this.id_blog = id_blog;
    }

    public NoteBlog(int note, Blog id_blog) {
        this.note = note;
        this.id_blog = id_blog;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Blog getId_blog() {
        return id_blog;
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


