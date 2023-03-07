package entity;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event {
    private int id;
    private String title;
    private String description;
    private int nb_placeMax;
    private LocalDateTime date_beg;
    private LocalDateTime date_end;

    private String type_event;

    private String url;



    public Event() {

    }

    public Event(int id, String title, String description, int nb_placeMax, LocalDateTime date_beg, LocalDateTime date_end,  String type_event,  String url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.nb_placeMax = nb_placeMax;
        this.date_beg = date_beg;
        this.date_end = date_end;

        this.type_event = type_event;

        this.url = url;

    }

    public Event(String title, String description, int nb_placeMax, LocalDateTime date_beg, LocalDateTime date_end, String type_event,  String url) {
        this.title = title;
        this.description = description;
        this.nb_placeMax = nb_placeMax;
        this.date_beg = date_beg;
        this.date_end = date_end;

        this.type_event = type_event;

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

    public LocalDateTime getDate_beg() {
        return date_beg;
    }

    public void setDate_beg(LocalDateTime date_beg) {
        this.date_beg = date_beg;
    }

    public LocalDateTime getDate_end() {
        return date_end;
    }

    public void setDate_end(LocalDateTime date_end) {
        this.date_end = date_end;
    }



    public String getType_event() {
        return type_event;
    }

    public void setType_event(String type_event) {
        this.type_event = type_event;
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

                ", type_event='" + type_event + '\'' +
                 ", url='" + url + '\'' +

                '}';
    }
}
