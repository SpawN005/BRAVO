package entity;


public class NoteOeuvre {
    private int id;
    private Oeuvre oeuvre;
    private int user;
    private double note;

    public NoteOeuvre(Oeuvre oeuvre, int user, double note) {
        this.oeuvre = oeuvre;
        this.user = user;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public int getUser() {
        return user;
    }

    public double getNote() {
        return note;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void setNote(double note) {
        this.note = note;
    }

}
