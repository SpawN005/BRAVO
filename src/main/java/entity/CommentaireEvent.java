package entity;

public class CommentaireEvent {
    private int id;
    private String content;
    private int id_event ;
    private int id_user;

    public CommentaireEvent() {

    }

    public CommentaireEvent(int id, String content, int id_event, int id_user) {
        this.id = id;
        this.content = content;
        this.id_event = id_event;
        this.id_user = id_user;
    }

    public CommentaireEvent(String content, int id_event, int id_user) {
        this.content = content;
        this.id_event = id_event;
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getId_event() {
        return id_event;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "CommentaireEvent{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", id_event=" + id_event +
                ", id_user=" + id_user +
                '}';
    }
}
