package entity;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String email;
    private String role;
    private String password;
    private String image;


    // Constructors
    public User() {
    }
    
    public User (int id, String firstName, String lastName, int phoneNumber, String password, String image ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.image = image;
    }
    
    public User(int id, String firstName, String lastName, int phoneNumber, String email, String role, String password) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.role = role;
    this.password = password;
}






    public User(String firstName, String lastName, int phoneNumber, String email, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;

    }

    public User(int aInt, String string, String string0, String string1, int aInt0, String string2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User(String firstName, String lastName, int phoneNumber, String email, String role, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getimage() {
    return image;}
    
    public void setimage(String image) {
        this.image = image;
    }

}
