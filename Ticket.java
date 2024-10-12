import jdk.dynalink.beans.StaticClass;

import java.io.*;
public class Ticket {
    //Static variable to keep track of the count of sold tickets
    public static int countOfSoldTickets;

    //Instance variables to store ticket information
    private char row;
    private int seat;
    private double price;
    private Person person;

    //Constructor to initialize ticket with row, seat, price, and person details
    public Ticket(char row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    //Getter methord for row
    public char getRow() {
        return row;
    }
    //Setter methord for row
    public void setRow(char row) {
        this.row = row;
    }

    //getter method for seat
    public int getSeat() {
        return seat;
    }

    //Setter method for seat
    public void setSeat(int seat) {
        this.seat = seat;
    }

    //getter method for price
    public double getPrice() {
        return price;
    }

    //setter method for price
    public void setPrice(double price) {
        this.price = price;
    }

    //Getter method for person
    public Person getPerson() {
        return person;
    }

    //setter method for person
    public void setPerson(Person person) {
        this.person = person;
    }

    //Method to print ticket details
    public void ticketDetails() {
        System.out.println("Ticket Details: ");
        System.out.println("Row:          " + row);
        System.out.println("Seat:         " + seat);
        System.out.println("Price:       $" + price);
        System.out.println("Person details: ");
        person.personDetails();
    }

    //Method to create a file and save ticket information
    public void CreateFile() {
        String filename = getRow() + "" + getSeat()+ ".txt";
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write("Row:     " + getRow() + "\n");
            myWriter.write("Seat:    " + getSeat() + "\n");
            myWriter.write("Price:   " + getPrice() + "\n");
            myWriter.write("Person information: \n");
            myWriter.write("Name:    " + getPerson().getName() + "\n");
            myWriter.write("Surname: " + getPerson().getSurname() + "\n");
            myWriter.write("Email:   " + getPerson().getEmail() + "\n");
            myWriter.close();
            System.out.println("Ticket information saved in " + filename);
        }
        catch (IOException e) {
            System.out.println("An error occurred while saving ticket information to " + filename);
        }
    }

    //Method to delete the file associated with the ticket
    public void deleteFile() {
        String filename = getRow() +""+ getSeat() + ".txt";
        File file =  new File(filename);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Deleted the file: " + filename);
            } else {
                System.out.println("Failed to delete the file: " + filename);
            }
        }
        else {
            System.out.println("File dose  not exist: " + filename);
        }
    }
}
