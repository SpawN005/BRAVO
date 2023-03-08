package entity;

public class TypeReclamation {
    private int id	;
    private String typeReclamation	;
    public TypeReclamation() {}

    public TypeReclamation(int id, String typeReclamation) {

        this.id = id;
        this.typeReclamation = typeReclamation;
    }

    public TypeReclamation(String typeReclamation) {

        this.typeReclamation = typeReclamation;
    }

    public TypeReclamation(int id) {
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeReclamation() {
        return typeReclamation;
    }

    public void setTypeReclamation(String typeReclamation) {
        this.typeReclamation = typeReclamation;
    }

    @Override
    public String toString() {
        return "TypeReclamation{" +
                "id=" + id +
                ", typeReclamation='" + typeReclamation + '\'' +
                '}';
    }
}
