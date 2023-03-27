

import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class HotelA {
    public static String[] hotel = new String[12];
    public static String[] payment = new String [12];
    public static Scanner input = new Scanner(System.in);
    public static int roomNum = 0;
    public static String firstName;
    public static String surname;
    public static String creditCard;

    public static void main(String[] args) throws IOException {

        initialise(hotel);
        boolean loop = true;
        while (loop) {
            System.out.println(" \nMain Menu\n ");
            System.out.println("A: Add Customer to a Room");
            System.out.println("V: View All Rooms");
            System.out.println("E: Display Empty Rooms");
            System.out.println("D: Delete Customer from Room");
            System.out.println("F: Find Room from Customer Name");
            System.out.println("S: Store Program Data into File");
            System.out.println("L: Load program Data From File");
            System.out.println("O: View Guests Ordered Alphabetically by Name");
            System.out.println("P: Payment");
            String selection = input.next();
            selection = selection.toUpperCase();
            switch (selection) {
                case "A" -> {
                    System.out.println("List of Empty Rooms:");
                    displayEmptyRooms();
                    addCustomerToRoom();
                }
                case "D" -> deleteCustomerFromRooms();
                case "E" -> displayEmptyRooms();
                case "F" -> {
                    System.out.println("Enter the Customer Name:");
                    String customerName = input.next();
                    System.out.println("This Customer is in Room " + (findRoomsByCustomerName(customerName)));
                }
                case "V" -> viewAllRooms();
                case "S" -> storeData();
                case "L" -> loadData();
                case "O" -> sortCustomers();
                case "P" -> payment();
                default -> System.out.println("Invalid Input. Select something from the given options.");
            }

        }
    }

    private static void initialise(String[] hotel) {
        for (int x = 0; x < 12; x++) hotel[x] = "null";
    }

    private static void displayEmptyRooms() {
        for (int x = 0; x < 12; x++) {
            if (hotel[x].equals("null")) System.out.println("Room " + x + " is Empty");
        }
    }

    private static int findRoomsByCustomerName(String customerName) {
        return Arrays.asList(hotel).indexOf(customerName);
    }

    private static void addCustomerToRoom() {
        System.out.println("Select a Room Number:");
        roomNum = input.nextInt();

        if (hotel[roomNum].equals("null")) {
            System.out.println("Customer Name:");
            String customerName;
            customerName = input.next();
            hotel[roomNum] = customerName;
            System.out.println("How many guests will be sharing the room with you? ");
            String guest = input.next();
            System.out.println(customerName + " and " + guest + " guests has been successfully added to Room: " + roomNum);
        } else {
            System.out.println("Sorry, the Room is Occupied.");
        }
    }

    private static void deleteCustomerFromRooms() {
        System.out.print("Enter the room number you wish to delete: ");
        roomNum = input.nextInt();
        hotel[roomNum] = "null";
        System.out.println("The Customer from the Selected Room Number Was Deleted Successfully");

    }

    private static void storeData() throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter("./hotel.txt"))) {
            int x;
            for (x = 0; x < hotel.length; x++) {
                out.println("Customer Name: " + hotel[x] + " Room Number: " + x);
            }
            System.out.println("Data Stored to file Successfully");
        }
    }

    private static void loadData() throws IOException {
        String datei = ("./hotel.txt");
        FileReader fr = new FileReader(datei);
        BufferedReader bf = new BufferedReader(fr);
        try {
            int i = 0;
            String line;
            while ((line = bf.readLine()) != null) {
                hotel[i] = line;
                i++;
            }
            bf.close();
            System.out.println("Data loaded from file successfully");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private static void viewAllRooms() {
        for (int x = 1; x < 12; x++) {
            System.out.println("Room " + x + " is occupied by: " + hotel[x]);
        }
    }

    private static void sortCustomers() {
        String[] customer = new String[hotel.length];
        for (int i = 0; i < hotel.length; i++) {
            customer[i] = hotel[i];
        }

        Arrays.sort(customer);
        for (int a = 0; a < customer.length; a++) {
            System.out.println(customer[a]);
        }
        }
    private static void payment() {
        System.out.println("Enter your Room Number: ");
        roomNum = input.nextInt();
        System.out.println("Enter Your First Name: ");
        firstName = input.next();
        System.out.println("Enter your Surname: ");
        surname = input.next();
        System.out.println("Enter your Credit Card Number: ");
        creditCard = input.next();
        payment[roomNum] = firstName;
        payment[roomNum] = surname;
        payment[roomNum] = creditCard;
        System.out.println("Your payment for room " + roomNum + " has been successful.");



    }
    }


