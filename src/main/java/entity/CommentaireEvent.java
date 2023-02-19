package entity;

public class CommentaireEvent {
    private int id;
    private String content;
    private Event id_event ;
    private User id_user;

    public CommentaireEvent() {

    }

    public CommentaireEvent(int id, String content, Event id_event, User id_user) {
        this.id = id;
        this.content = content;
        this.id_event = id_event;
        this.id_user = id_user;
    }

    public CommentaireEvent(String content, Event id_event, User id_user) {
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

    public Event getId_event() {
        return id_event;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId_event(Event id_event) {
        this.id_event = id_event;
    }

    public void setId_user(User id_user) {
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
