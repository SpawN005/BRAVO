//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package entity;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private int age;

    public User() {
    }

    public User(int id, String nom, String prenom, int age) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public User(String nom, String prenom, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "Personne{id=" + this.id + ", nom=" + this.nom + ", prenom=" + this.prenom + ", age=" + this.age + "}";
    }
}
