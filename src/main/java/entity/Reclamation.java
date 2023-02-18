package entity;
import java.sql.Date;
public class Reclamation {
    private int id	;
    private String title ;
    private String description	;
    private Date date_creation	;
    private String etat	;
    private User ownerID	;
    private Date date_treatment ;
    private int note;

    public Reclamation() {}

    public Reclamation(int id, String title, String description, Date date_creation, String etat, User ownerID,
                       Date date_treatment,int note) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.date_creation = date_creation;
        this.etat = etat;
        this.ownerID = ownerID;
        this.date_treatment = date_treatment;
        this.note = note;
    }
    public Reclamation(String title, String description, Date date_creation, String etat, User ownerID,
                       Date date_treatment, int note) {
        super();
        this.title = title;
        this.description = description;
        this.date_creation = date_creation;
        this.etat = etat;
        this.ownerID = ownerID;
        this.date_treatment = date_treatment;
        this.note = note;
    }
    public Reclamation(String title, String description, Date date_creation, String etat, Date date_treatment,
                       int note) {
        super();
        this.title = title;
        this.description = description;
        this.date_creation = date_creation;
        this.etat = etat;
        this.date_treatment = date_treatment;
        this.note = note;
    }
    public Reclamation(String title, String description, String etat, User ownerID, Date date_treatment,int note) {
        super();
        this.title = title;
        this.description = description;
        this.etat = etat;
        this.ownerID = ownerID;
        this.date_treatment = date_treatment;
        this.note = note;
    }

    public Reclamation(String title, String description, Date date_creation, String etat, User ownerID, int note) {
        this.title = title;
        this.description = description;
        this.date_creation = date_creation;
        this.etat = etat;
        this.ownerID = ownerID;
        this.note = note;
    }

    public Reclamation(int id, String title, String description, Date date_creation, String etat, Date date_treatment, int note) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date_creation = date_creation;
        this.etat = etat;
        this.date_treatment = date_treatment;
        this.note = note;
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

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public User getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(User ownerID) {
        this.ownerID = ownerID;
    }

    public Date getDate_treatment() {
        return date_treatment;
    }

    public void setDate_treatment(Date date_treatment) {
        this.date_treatment = date_treatment;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date_creation=" + date_creation +
                ", etat='" + etat + '\'' +
                ", ownerID=" + ownerID +
                ", date_treatment=" + date_treatment +
                ", note=" + note +
                '}';
    }
}
