package entity;

public class Reservation {
    private int id;
    private User id_participant;
    private Event id_event;
    private boolean isConfirmed;
    private int nb_place;

    public Reservation() {

    }

    public Reservation(int id, User id_participant, Event id_event, boolean isConfirmed, int nb_place) {
        this.id = id;
        this.id_participant = id_participant;
        this.id_event = id_event;
        this.isConfirmed = isConfirmed;
        this.nb_place = nb_place;
    }

    public Reservation(User id_participant, Event id_event, boolean isConfirmed, int nb_place) {
        this.id_participant = id_participant;
        this.id_event = id_event;
        this.isConfirmed = isConfirmed;
        this.nb_place = nb_place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getId_participant() {
        return id_participant;
    }

    public void setId_participant(User id_participant) {
        this.id_participant = id_participant;
    }

    public Event getId_event() {
        return id_event;
    }

    public void setId_event(Event id_event) {
        this.id_event = id_event;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", id_participant=" + id_participant +
                ", id_event=" + id_event +
                ", isConfirmed=" + isConfirmed +
                ", nb_place=" + nb_place +
                '}';
    }
}
