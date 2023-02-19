package entity;

import java.time.LocalDate;
import java.util.List;

public class Event {
    private int id;
    private String title;
    private String description;
    private int nb_placeMax;
    private LocalDate date_beg;
    private LocalDate date_end;
    private int note;
    private String type_event;
    private CommentaireEvent id_commentaire;
    private String url;



    public Event() {

    }

    public Event(int id, String title, String description, int nb_placeMax, LocalDate date_beg, LocalDate date_end, int note, String type_event, CommentaireEvent id_commentaire, String url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.nb_placeMax = nb_placeMax;
        this.date_beg = date_beg;
        this.date_end = date_end;
        this.note = note;
        this.type_event = type_event;
        this.id_commentaire = id_commentaire;
        this.url = url;

    }

    public Event(String title, String description, int nb_placeMax, LocalDate date_beg, LocalDate date_end, int note, String type_event, CommentaireEvent id_commentaire, String url) {
        this.title = title;
        this.description = description;
        this.nb_placeMax = nb_placeMax;
        this.date_beg = date_beg;
        this.date_end = date_end;
        this.note = note;
        this.type_event = type_event;
        this.id_commentaire = id_commentaire;
        this.url = url;

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

    public int getNb_placeMax() {
        return nb_placeMax;
    }

    public void setNb_placeMax(int nb_placeMax) {
        this.nb_placeMax = nb_placeMax;
    }

    public LocalDate getDate_beg() {
        return date_beg;
    }

    public void setDate_beg(LocalDate date_beg) {
        this.date_beg = date_beg;
    }

    public LocalDate getDate_end() {
        return date_end;
    }

    public void setDate_end(LocalDate date_end) {
        this.date_end = date_end;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getType_event() {
        return type_event;
    }

    public void setType_event(String type_event) {
        this.type_event = type_event;
    }

    public CommentaireEvent getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(CommentaireEvent id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", nb_placeMax=" + nb_placeMax +
                ", date_beg=" + date_beg +
                ", date_end=" + date_end +
                ", note=" + note +
                ", type_event='" + type_event + '\'' +
                ", id_commentaire=" + id_commentaire +
                ", url='" + url + '\'' +

                '}';
    }
}
