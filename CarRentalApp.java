package rentalApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class RentalHistory {
    private String startDate;
    private String endDate;
    private double totalCost;
    private String status;

    public RentalHistory(String startDate, String endDate, double totalCost, String status) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Start Date: " + startDate +
               ", End Date: " + endDate +
               ", Total Cost: $" + totalCost +
               ", Status: " + status;
    }
}

class CarDetails {
    private String carModel;
    private int bookingCount;
    private List<String> bookingDates;
    private List<RentalHistory> rentalHistory;

    public CarDetails(String carModel) {
        this.carModel = carModel;
        this.bookingCount = 0;
        this.bookingDates = new ArrayList<>();
        this.rentalHistory = new ArrayList<>();
    }

    public String getCarModel() {
        return carModel;
    }

    public void addRental(String startDate, String endDate, double totalCost, String status) {
        bookingCount++;
        rentalHistory.add(new RentalHistory(startDate, endDate, totalCost, status));
        bookingDates.add(startDate); // You can modify this to add the whole range if needed
    }

    public void printCarDetails() {
        System.out.println("Car Model: " + carModel);
        System.out.println("Booking Count: " + bookingCount);
        System.out.println("Booking Dates: " + bookingDates);
        System.out.println("Rental History:");
        for (RentalHistory rental : rentalHistory) {
            System.out.println(rental);
        }
    }
}

public class CarRentalApp {
    private static Map<String, CarDetails> carDetailsMap = new HashMap<>();

    static {
        // Initializing with some cars and their rental history
        CarDetails toyotaCamry = new CarDetails("Toyota Camry");
        toyotaCamry.addRental("2023-01-10", "2023-01-15", 200.00, "Completed");
        carDetailsMap.put("Toyota Camry", toyotaCamry);

        CarDetails hondaAccord = new CarDetails("Honda Accord");
        hondaAccord.addRental("2023-02-05", "2023-02-10", 250.00, "Completed");
        carDetailsMap.put("Honda Accord", hondaAccord);

        CarDetails fordFocus = new CarDetails("Ford Focus");
        fordFocus.addRental("2023-03-01", "2023-03-05", 150.00, "Canceled");
        carDetailsMap.put("Ford Focus", fordFocus);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Display available cars
        System.out.println("Available cars:");
        for (String carModel : carDetailsMap.keySet()) {
            System.out.println("- " + carModel);
        }

        System.out.println("\nEnter the car model to view rental history or 'exit' to quit:");
        String inputCarModel = scanner.nextLine();
        
        if (inputCarModel.equalsIgnoreCase("exit")) {
            System.out.println("Exiting...");
            scanner.close();
            return;
        }

        CarDetails carDetails = carDetailsMap.get(inputCarModel);
        
        if (carDetails != null) {
            carDetails.printCarDetails();
        } else {
            System.out.println("No rental history found for car model: " + inputCarModel);
        }

        // Optionally allow adding more rentals
        System.out.println("\nWould you like to add a rental record for this car? (yes/no)");
        String response = scanner.nextLine();
        
        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Enter start date (yyyy-mm-dd):");
            String startDate = scanner.nextLine();
            System.out.println("Enter end date (yyyy-mm-dd):");
            String endDate = scanner.nextLine();
            System.out.println("Enter total cost:");
            double totalCost = scanner.nextDouble();
            scanner.nextLine();  // Consume newline
            System.out.println("Enter booking status (Completed/Cancelled):");
            String status = scanner.nextLine();
            
            carDetails.addRental(startDate, endDate, totalCost, status);
            System.out.println("Rental added successfully.");
            carDetails.printCarDetails();
        }
        
        scanner.close();
    }
}
