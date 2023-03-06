package entity;


import java.sql.Timestamp;

public class CommentaireOeuvre {
        private int id;
        private Oeuvre oeuvre;
        private User user;
        private String comment;
        private Timestamp timestamp;

        public CommentaireOeuvre(int id, Oeuvre oeuvre, User user, String comment, Timestamp timestamp) {
            this.id = id;
            this.oeuvre = oeuvre;
            this.user = user;
            this.comment = comment;
            this.timestamp = timestamp;
        }
    public CommentaireOeuvre( Oeuvre oeuvre, User userId, String comment, Timestamp timestamp) {

        this.oeuvre = oeuvre;
        this.user = userId;
        this.comment = comment;
        this.timestamp = timestamp;
    }

    public CommentaireOeuvre() {

    }

    // Getters and setters for all fields
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Oeuvre getOeuvre() {
            return oeuvre;
        }

        public void setOeuvre(Oeuvre oeuvre) {
            this.oeuvre = oeuvre;
        }

        public User getUserId() {
            return user;
        }

        public void setUserId(int userId) {
            this.user = user;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public Timestamp getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
        }

    @Override
    public String toString() {
        return "CommentaireOeuvre{" +
                "oeuvre=" + oeuvre +
                ", userId=" + user +
                ", comment='" + comment + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}


