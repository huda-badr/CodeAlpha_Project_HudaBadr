package codeAlpha;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

//Task 4: 
class Room {
    private int roomNumber;
    private String category;
    private double pricePerNight;
    private boolean isAvailable;

    public Room(int roomNumber, String category, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + ", Category: " + category + ", Price per Night: $" + pricePerNight + ", Available: " + isAvailable;
    }
}

class Reservation {
    private Room room;
    private String guestName;
    private int nights;
    private double totalCost;

    public Reservation(Room room, String guestName, int nights) {
        this.room = room;
        this.guestName = guestName;
        this.nights = nights;
        this.totalCost = room.getPricePerNight() * nights;
    }

    public Room getRoom() {
        return room;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getNights() {
        return nights;
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return "Reservation for " + guestName + "\nRoom: " + room.getRoomNumber() + "\nNights: " + nights + "\nTotal Cost: $" + totalCost;
    }
}

class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> searchAvailableRooms(String category) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable() && room.getCategory().equalsIgnoreCase(category)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Reservation makeReservation(int roomNumber, String guestName, int nights) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                room.setAvailable(false);
                Reservation reservation = new Reservation(room, guestName, nights);
                reservations.add(reservation);
                return reservation;
            }
        }
        return null;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}

class PaymentService {
    public static boolean processPayment(String guestName, double amount) {
        System.out.println("Processing payment for " + guestName + " of amount $" + amount);
        return true;
    }
}



class HotelReservationSystem {
    private Hotel hotel;
    private Scanner scanner;

    public HotelReservationSystem() {
        hotel = new Hotel();
        scanner = new Scanner(System.in);
        initializeHotel();
    }

    private void initializeHotel() {
        hotel.addRoom(new Room(101, "Single", 50));
        hotel.addRoom(new Room(102, "Double", 75));
        hotel.addRoom(new Room(103, "Suite", 150));
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    searchAvailableRooms();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewBookingDetails();
                    break;
                case 4:
                    System.out.println("Exiting the application.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void searchAvailableRooms() {
        System.out.print("Enter room category (Single, Double, Suite): ");
        String category = scanner.nextLine();
        List<Room> availableRooms = hotel.searchAvailableRooms(category);
        if (availableRooms.isEmpty()) {
            System.out.println("No available rooms in this category.");
        } else {
            System.out.println("Available rooms:");
            for (Room room : availableRooms) {
                System.out.println(room);
            }
        }
    }

    private void makeReservation() {
        System.out.print("Enter room number to reserve: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();

        System.out.print("Enter number of nights: ");
        int nights = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        Reservation reservation = hotel.makeReservation(roomNumber, guestName, nights);
        if (reservation != null) {
            boolean paymentSuccess = PaymentService.processPayment(guestName, reservation.getTotalCost());
            if (paymentSuccess) {
                System.out.println("Reservation successful:\n" + reservation);
            } else {
                System.out.println("Payment failed. Reservation not completed.");
            }
        } else {
            System.out.println("Reservation failed. Room not available or invalid room number.");
        }
    }

    private void viewBookingDetails() {
        System.out.println("Booking details:");
        List<Reservation> reservations = hotel.getReservations();
        if (reservations.isEmpty()) {
            System.out.println("No bookings available.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}


public class HotelReservation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HotelReservationSystem system = new HotelReservationSystem();
        system.displayMenu();
	}

}
