package entity;

public class DonsUser {
    private int id;
    private int id_user;
    private int id_don;
    private int amout;
    public DonsUser(){
    }

    public DonsUser(int id) {
        this.id = id;
    }

    public DonsUser(int id, int id_user, int id_don, int amout) {
        this.id = id;
        this.id_user = id_user;
        this.id_don = id_don;
        this.amout = amout;
    }

    public DonsUser(int id_don, int amout) {
        this.id_don = id_don;
        this.amout = amout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_don() {
        return id_don;
    }

    public void setId_don(int id_don) {
        this.id_don = id_don;
    }

    public int getAmout() {
        return amout;
    }

    public void setAmout(int amout) {
        this.amout = amout;
    }

    @Override
    public String toString() {
        return "DonsUser{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", id_don=" + id_don +
                ", amout=" + amout +
                '}';
    }
}

