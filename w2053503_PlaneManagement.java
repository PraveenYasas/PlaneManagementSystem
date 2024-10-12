import java.io.File;
import java.util.*;
public class w2053503_PlaneManagement {
    //Static scanner for user input
    static Scanner scanner = new Scanner(System.in);
    //This array presenting the seating plan of the plane
    public static char[][] seatingPlan;
    //This array for store sold tickets
    public static Ticket[] soldTickets;
    //This variable for track the number of sold tickets
    public static int countOfSoldTickets;
    //Variables to store user input for ticket details
    public static char rowLetter;
    public static int seatNumber;
    public  static String name,surname,email;

    //This Static block for initializing seatPlan and soldTickets arrays
    static {
        //Initializing the seating plan with 'O' including available seats
        seatingPlan = new char[4][];
        seatingPlan[0] = new char[]{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
        seatingPlan[1] = new char[]{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
        seatingPlan[2] = new char[]{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
        seatingPlan[3] = new char[]{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};

        //Initializing the array to store sold tickets with capacity of 52
        soldTickets = new Ticket[52];
        //Initializing the count of sold tickets
        countOfSoldTickets = 0;
    }

    public static void main(String[] args) {
        //Welcome message and opening the menu option
        System.out.println("Welcome to the plane management system.");
        System.out.println("*".repeat(50));
        System.out.println("*" + " ".repeat(18) + "MENU OPTIONS" + " ".repeat(18) + "*");
        System.out.println("*".repeat(50));

        //Main loop
        while (true) {
            //Displaying the menu options
            System.out.println("\t1) Buy a seat.");
            System.out.println("\t2) Cancel a seat.");
            System.out.println("\t3) Find a first available seat.");
            System.out.println("\t4) Show seating plan.");
            System.out.println("\t5) Print tickets information.");
            System.out.println("\t6) Search tickets");
            System.out.println("\t0) Quit");
            System.out.println("*".repeat(50));

            //Asking form the user select the option
            System.out.println("Please select the option: ");
            try {
                //Read user input
                int chooseNumber = scanner.nextInt();
                scanner.nextLine();

                //Work by user input command
                switch (chooseNumber) {
                    case 1:
                        buy_Seat();
                        break;
                    case 2:
                        cancel_Seat();
                        break;
                    case 3:
                        find_first_available();
                        break;
                    case 4:
                        show_seating_plan();
                        break;
                    case 5:
                        print_tickets_info();
                        break;
                    case 6:
                        search_ticket();
                        break;
                    case 0:
                        //The  user input the 0 after that exit the program
                        System.out.println("Exiting the program");
                        System.exit(0);
                        break;
                    default:
                        //Handling the invalid input
                        System.out.println("Invalid choice. Please enter correct number.");
                }
            }
            catch (InputMismatchException e) {
                //This for user input the without integer and handle it.
                System.out.println("Invalid input. Please enter correct input.");
                scanner.nextLine();
            }
        }
    }

    public static void buy_Seat() {
        //Asking form user to enter the row letter
        System.out.println("Enter the row letter: ");
        rowLetter = scanner.next().toUpperCase().charAt(0);

        //Validate the entered row letter
        if (rowLetter < 'A' || rowLetter > 'D') {
            System.out.println("Invalid input. Please enter correct letter.");
            return;
        }

        //Asking form user enter the seat number
        System.out.println("Enter the seat number: ");
        int seatNumber = scanner.nextInt();

        //Validate the entered seat number based on row letter
        if ((rowLetter == 'A' || rowLetter == 'D') && (seatNumber > 14 || seatNumber < 1)) {
            System.out.println("Invalid row number. Please enter correct number.");
            return;
        }
        if ((rowLetter == 'B' || rowLetter == 'C') && (seatNumber > 12 || seatNumber < 1)) {
            System.out.println("Invalid row number. Please enter correct number.");
            return;
        }

        //Convert row letter and seat number to indices
        int row = rowLetter - 'A';
        int colum = seatNumber - 1;

        //Checking this seat is already booked
        if (seatingPlan[row][colum] == 'X') {
            System.out.println("Sorry, This seat booked other one. please find another seat.");
            return;
        }

        scanner.nextLine();
        //Asking form the user enter name, surname, and email
        while (true) {
            System.out.println("Enter the name: ");
            name = scanner.nextLine().toUpperCase();
            //Validate the name
            if (name.isBlank() || (!name.matches("[A-Z]*$"))) {
                System.out.println("Enter the correct name!!..");
            }
            else {
                break;
            }
        }

        while (true) {
            System.out.println("Enter the surname: ");
            surname = scanner.nextLine().toUpperCase();
            //Validate the surname
            if (surname.isBlank() || (!surname.matches("[A-Z]*$"))) {
                System.out.println("Enter the correct surname!..");
            }
            else {
                break;
            }
        }

        while (true) {
            System.out.println("Enter the Email: ");
            email = scanner.nextLine();
            //Validate the surname
            if (email.isBlank() || (!email.matches("^[a-z0-9]+(?:\\.[a-z0-9]+)*@(?:[a-z]+\\.)+[a-z]{2,7}$"))) {
                System.out.println("Enter the correct email!..");
            }
            else {
                break;
            }
        }
        //Create the object with entered name, surname and email
        Person person = new Person(name, surname, email);

        //Calculate ticket price based on seat number
        double price = price(seatNumber);

        //Create object with row letter, seat number, price and email
        Ticket ticket = new Ticket(rowLetter, seatNumber, price, person);

        //Add the sold ticket to the array of sold ticket
        soldTickets[countOfSoldTickets++] = ticket;

        //Mark the seat as booked in the seating plan
        seatingPlan[row][colum] = 'X';

        //Display to user his purchase is successful
        System.out.println("Seat " + rowLetter + seatNumber + " has been successfully sold.");
        System.out.println("Seat Availability = 1");

        //create a file for the ticket
        ticket.CreateFile();
    }

    public static void cancel_Seat() {
        //Ask form the user enter the row letter
        System.out.println("Enter the row letter: ");
        rowLetter = scanner.next().toUpperCase().charAt(0);

        //Validate the entered row letter
        if (rowLetter < 'A' || rowLetter > 'D') {
            System.out.println("Invalid input please enter correct letter.");
            return;
        }

        //Prompt user to enter the seat number
        System.out.println("Enter the seat number");
        int seatNumber = scanner.nextInt();

        //Validate the entered seat number based on row letter
        if ((rowLetter == 'A' || rowLetter == 'D') && (seatNumber > 14 || seatNumber < 1)) {
            System.out.println("Invalid row number. Please enter correct number.");
            return;
        }
        if ((rowLetter == 'B' || rowLetter == 'C') && (seatNumber > 12 || seatNumber < 1)) {
            System.out.println("Invalid row number. Please enter correct number.");
            return;
        }

        //Convert row letter and seat number to array indices
        int row = rowLetter - 'A';
        int colum = seatNumber - 1;

        //Checking the that seat is booked or not booked
        if (seatingPlan[row][colum] != 'X') {
            System.out.println("You can't cancel that seat.");
            return;
        }

        //Create the file name for the ticket file
        String fileName = rowLetter + String.valueOf(seatNumber) + ".txt";
        File ticketFile = new File(fileName);

        //Checking the ticket file exists and delete it
        if (ticketFile.exists()) {
            if (ticketFile.delete()) {
                System.out.println("Ticket file for seat " + rowLetter + seatNumber + " was delete successfully.");
            }
            else {
                System.out.println("Failed to delete the ticket file for seat " + rowLetter + seatNumber + ".");
                return;
            }
        }
        else {
            System.out.println("No ticket found for seat " + rowLetter + seatNumber + ".");
        }

        //Iterate through sold tickets array to find the ticket to cancel
        for (int i = 0; i < Ticket.countOfSoldTickets; i++) {
            if ((soldTickets[i].getRow() == rowLetter) && (soldTickets[i].getSeat() == seatNumber)) {
                //Delete the ticket file and remove the ticket from the array
                soldTickets[i].deleteFile();
                soldTickets[i] = null;
                for (int j = i; j < Ticket.countOfSoldTickets - 1; j++) {
                    soldTickets[j] = soldTickets[j + 1];
                }
                soldTickets[--countOfSoldTickets] = null;
                break;
            }
        }

        //Mark the seat is now available in the seating plan
        seatingPlan[row][colum] = 'O';
        System.out.println("Seat " + rowLetter + seatNumber + " has been successfully canceled.");
        System.out.println("Seat Availability = 0");

    }

    public static void find_first_available() {
        boolean found = false;
        //Iterate through the seating plan array to find the first available seat
        for (int i = 0; i < seatingPlan.length; i++) {
            for (int j = 0; j < seatingPlan[i].length; j++) {
                if (seatingPlan[i][j] == 'O') {
                    //Print the location of first available seat
                    System.out.println("The first available seat is: " + (char)('A' + i) + (j + 1));
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }

        //If no available seat is found
        if (!found) {
            System.out.println("All seats are sold out!..");

            //Create a Person object with previously entered details
            Person person = new Person(name, surname, email);

            //Calculate price based on previously entered seat number
            double price = price(seatNumber);

            //Create a Ticket object with previously entered details
            Ticket ticket = new Ticket(rowLetter, seatNumber, price, person);

            //Add the created to the array of sold tickets
            soldTickets[countOfSoldTickets++] = ticket;

            //Display the ticket details for the created ticket
            ticket.ticketDetails();
        }
    }

    public static void show_seating_plan() {
        //Iterate through the seating plan array to display the seating plan
        for (int i = 0; i < seatingPlan.length; i++) {
            for (int j = 0; j < seatingPlan[i].length; j++) {
                //Print all the seats. Booked and Non booked
                System.out.print(seatingPlan[i][j] + " ");
            }
            //Move to the next line after printing each row of seats
            System.out.println();
        }
    }
    public static double price(int seatNumber) {
        //the prices based on the seat number
        if (seatNumber > 0 && seatNumber <= 5) {
            //Colum 1 to 5 range are pricing $200
            return 200.0;
        }
        else if (seatNumber >= 6 && seatNumber <= 9) {
            //Colum 6 to 9 range are pricing $150
            return 150.0;
        }
        else {
            //Colum 10 to 14 range are pricing $180(Other seats are $180)
            return 180.0;
        }
    }
    public static void print_tickets_info() {
        //This variable for store the total amount of ticket sales
        double totalAmount = 0.0;

        //Iterate through the array of sold tickets
        for (int i = 0; i < countOfSoldTickets; i++) {
            //Checking the current ticket is not null
            if (soldTickets[i] != null) {
                //Display the ticket details for the current ticket
                soldTickets[i].ticketDetails();

                //get this ticket price and calculate the total amount
                totalAmount += soldTickets[i].getPrice();
            }
        }
        //Print the total amount of ticket sales
        System.out.println("Total amount: " + totalAmount);
    }

    public static void search_ticket() {
        //Ask from the user to enter the row lwtter
        System.out.println("Enter the row letter: ");
        rowLetter = scanner.next().toUpperCase().charAt(0);

        //Validate entered row letter
        if (rowLetter < 'A' || rowLetter > 'D') {
            System.out.println("Invalid input please enter correct letter.");
            return;
        }

        //Ask from the user to enter the seat number
        System.out.println("Enter the seat number: ");
        seatNumber = scanner.nextInt();

        //Validate the entered seat number based on row letter
        if ((rowLetter == 'A' || rowLetter == 'D') && (seatNumber > 14 || seatNumber < 1)) {
            System.out.println("Invalid row number. Please enter correct number.");
            return;
        }
        if ((rowLetter == 'B' || rowLetter == 'C') && (seatNumber > 12 || seatNumber < 1)) {
            System.out.println("Invalid row number. Please enter correct number.");
            return;
        }

        //Search for the ticket based on row letters and seat number
        for (int i = 0; i < countOfSoldTickets; i++) {
            Ticket ticket = soldTickets[i];
            if (ticket != null && ticket.getRow() == rowLetter && ticket.getSeat() == seatNumber) {
                //Display the ticket information if found
                System.out.println("Ticket information: ");
                ticket.ticketDetails();
                System.out.println("Price: £" + price(seatNumber)); //Display the ticket price
                System.out.println("Person information: ");
                ticket.getPerson().personDetails();     //Display the person information
                break;      //Exit the loop after finding the ticket
            }
        }
    }
}
