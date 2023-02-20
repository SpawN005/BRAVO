package entity;


import java.sql.Timestamp;

public class CommentaireOeuvre {
        private int id;
        private Oeuvre oeuvre;
        private int userId;
        private String comment;
        private Timestamp timestamp;

        public CommentaireOeuvre(int id, Oeuvre oeuvre, int userId, String comment, Timestamp timestamp) {
            this.id = id;
            this.oeuvre = oeuvre;
            this.userId = userId;
            this.comment = comment;
            this.timestamp = timestamp;
        }
    public CommentaireOeuvre( Oeuvre oeuvre, int userId, String comment, Timestamp timestamp) {

        this.oeuvre = oeuvre;
        this.userId = userId;
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
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
                ", userId=" + userId +
                ", comment='" + comment + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}


