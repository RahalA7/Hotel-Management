
import java.io.Serializable;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;


public class Hotel implements Serializable {
    public static Scanner input = new Scanner(System.in);
    private final Room[] rooms = new Room[12];

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Hotel hotel = new Hotel();
        hotel.initialise();
        boolean loop = true;
        while (loop) {
            System.out.println(" \nMain Menu\n ");
            System.out.println("A: Add Customer to a Room & Payment");
            System.out.println("V: View All Rooms");
            System.out.println("E: Display Empty Rooms");
            System.out.println("D: Delete Customer from Room");
            System.out.println("F: Find Room from Customer Name");
            System.out.println("S: Store Program Data into File");
            System.out.println("L: Load program Data From File");
            System.out.println("O: View Guests Ordered Alphabetically by Name");
            String selection = input.next();
            selection = selection.toUpperCase();
            switch (selection) {
                case "A" -> {
                    System.out.println("-------------------------------------------------");
                    System.out.println("Add Customer");
                    System.out.println("-------------------------------------------------");
                    //System.out.println("List of Empty Rooms: ");
                   // hotel.displayEmptyRooms();
                    Person person = new Person();
                    System.out.println("Enter Room Number: ");
                    int roomNumber = input.nextInt();
                    System.out.println("How Many Guests will be sharing the room with you?: ");
                    int numberOfGuests = input.nextInt();
                    System.out.println("Enter Surname: ");
                    String surname = input.next();
                    System.out.println("Enter First Name: ");
                    String firstName = input.next();
                    System.out.println("iEnter Credit Card Number:");
                    int creditCardNumber = input.nextInt();
                    person.setSurName(surname);
                    person.setFirstName(firstName);
                    person.setCreditCardNumber(creditCardNumber);
                    hotel.addCustomerToRoom(roomNumber, person, numberOfGuests);
                }
                case "D" -> {
                    System.out.println("-------------------------------------------------");
                    System.out.println("Remove Customer from Room");
                    System.out.println("-------------------------------------------------");
                    System.out.println("EnterRoom Number you wish to delete: ");
                    int removeCustomerRoomNumber = input.nextInt();
                    hotel.deleteCustomerFromRooms(removeCustomerRoomNumber);
                    //hotel.displayEmptyRooms();
                }
                case "E" -> {
                    System.out.println("-------------------------------------------------");
                    System.out.println("Display Empty Rooms");
                    System.out.println("-------------------------------------------------");
                   // hotel.displayEmptyRooms();
                }
                case "F" -> {
                    System.out.println("-------------------------------------------------");
                    System.out.println("Find Room from Customer Name");
                    System.out.println("-------------------------------------------------");
                    System.out.println("Enter Customer Name Surname: ");
                    String surname = input.next();
                    int message = hotel.findRoomsByCustomerName(surname);
                    if (message > 12) {
                        System.out.println("Sorry, This customer does not exist.");
                    } else {
                        System.out.println("This Customer is in room: " + message);
                    }
                }
                case "V" -> {
                    System.out.println("-------------------------------------------------");
                    System.out.println(" View All Customers");
                    System.out.println("-------------------------------------------------");
                    System.out.println("Current Customers: ");
                    for (String customer : hotel.viewAllCustomers()) {
                        if (customer != null) {
                            System.out.println(customer);
                        }
                    }
                }
                case "O" -> {
                    System.out.println("-------------------------------------------------");
                    System.out.println("Sort Guests in Alphabetical Order by Name");
                    System.out.println("-------------------------------------------------");
                    hotel.sortCustomers();
                }
                case "S" -> {
                    System.out.println("-------------------------------------------------");
                    System.out.println("Store Data to File");
                    System.out.println("-------------------------------------------------");
                    storeData(hotel);
                }
                case "L" -> hotel = loadData();
                default -> System.out.println("Invalid Input. Select something from the given options.");
            }
        }
    }

    public static Hotel loadData() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream("./hotel.txt");
        ObjectInputStream oi = new ObjectInputStream(fi);
        Hotel hotel = (Hotel) oi.readObject();
        oi.close();
        fi.close();
        return hotel;
    }

    public static void storeData(Hotel hotel) {
        try {
            FileOutputStream fos = new FileOutputStream("./hotel.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(hotel);
            oos.close();
            fos.close();
            System.out.println("Data Stored to File Successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //public void displayEmptyRooms() {
       // System.out.println("Empty Rooms: ");
       // for (Room room : rooms)
           // if (Objects.isNull(room.getPerson())) {
           //     System.out.println("Room" + room.getRoomNumber() + " is empty");
          //  }


    public int findRoomsByCustomerName(String customerName) {
        for (Room room : rooms) {
            if (!Objects.isNull(room.getPerson())) {
                Person person = room.getPerson();
                if (person.getSurName().equals(customerName)) {
                    return room.getRoomNumber();
                }
            }
        }
        return 12;
    }

    public void addCustomerToRoom(int roomNumber, Person person, int numberOfGuests) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                if (Objects.isNull(room.getPerson())) {
                    room.setPerson(person);
                    room.setNumberOfGuests(numberOfGuests);
                    System.out.println("Your payment has been successful, customer added to Room");
                } else {
                    System.out.println("Room is occupied");
                }
            }
        }
    }

    public void deleteCustomerFromRooms(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.setPerson(null);
            }
        }
    }

    public String[] viewAllCustomers() {
        String[] customerNames = new String[12];
        int customerNameCounter = 0;
        for (Room room : rooms) {
            if (!Objects.isNull(room.getPerson())) {
                Person person = room.getPerson();
                customerNames[customerNameCounter] = person.getSurName();
                customerNameCounter += 1;
            }
        }
        return customerNames;
    }

    public void sortCustomers() {

        boolean isSwapped = false;
        int customercount = 0;
        for (String o : viewAllCustomers()) {
            if (o != null) {
                customercount += 1;
            }
        }
        String[] customers = new String[customercount];
        int count = 0;
        for (String o : viewAllCustomers()) {
            if (o != null) {
                customers[count] = o;
                count += 1;
            }
        }
        for (String x : customers) {
            System.out.println(x);
        }
        do {
            isSwapped = false;
            for (int i = 0; i < customers.length - 1; i++) {
                if (customers[i].compareTo(customers[i + 1]) > 0) {
                    String temp = customers[i + 1];
                    customers[i + 1] = customers[i];
                    customers[i] = temp;
                    isSwapped = true;
                }
            }
        } while ((isSwapped));
        System.out.println("customer names in alphabetical order:");
        for (String i : customers) {
            if (!i.equals("e")) {
                System.out.println(i);
            }
        }
    }

    public void initialise() {
        for (int x = 0; x < 6; x++) {
            Room room = new Room();
            room.setRoomNumber(x);
            rooms[x] = room;
        }

    }


}
