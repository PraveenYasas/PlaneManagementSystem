public class Person {
    //Private fields to store person information
    private String name;
    private String surname;
    private String email;

    //Constructor to initialize person with name, surname and email
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    //Getter method for name
    public String getName() {
        return name;
    }

    //Setter method for name
    public void setName(String name) {
        this.name = name;
    }

    //Getter method for surname
    public String getSurname() {
        return surname;
    }

    //Setter method for surname
    public void setSurname(String surname) {
        this.surname = surname;
    }

    //getter method for email
    public String getEmail() {
        return email;
    }

    //Setter method for email
    public void setEmail(String email) {
        this.email = email;
    }

    //Method to print person details
    public void personDetails() {
        System.out.println("Name:    " + getName());
        System.out.println("Surname: " + getSurname());
        System.out.println("Email:   " + getEmail());
    }
}
