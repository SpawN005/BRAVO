package entity;

public class NoteEvent {
    private int id;
    private int note;
    private Event id_event;

    public NoteEvent() {

    }

    public NoteEvent(int id, int note, Event id_event) {
        this.id = id;
        this.note = note;
        this.id_event = id_event;
    }

    public NoteEvent(int note, Event id_event) {
        this.note = note;
        this.id_event = id_event;
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

    public Event getId_event() {
        return id_event;
    }

    public void setId_event(Event id_event) {
        this.id_event = id_event;
    }

    @Override
    public String toString() {
        return "NoteEvent{" +
                "id=" + id +
                ", note=" + note +
                ", id_event=" + id_event +
                '}';
    }
}
