package com.nology;

import java.io.*;
import java.util.*;

public class ParkingLot {

    private final int motorcycleSpaces; // Total in creation
    private final int carSpaces; // Total in creation
    private final int largeSpaces; // Total in creation
    private int motorcycleSpacesLeft; // Amount remaining after current vehicles parked
    private int carSpacesLeft; // Amount remaining after current vehicles parked
    private int largeSpacesLeft; // Amount remaining after current vehicles parked
    private int motorcyclesParked = 0; // Total motorcycles parked
    private int carsParked = 0; // Total cars parked
    private int vansParked = 0; // Total vans parked

    // Motorcycle takes up any
    // Car takes up 1 car spot or 1 large
    // Van takes up 1 large, 3 car

    public ParkingLot(int motorcycleSpaces, int carSpaces, int largeSpaces) { // Constructor
        this.motorcycleSpaces = motorcycleSpaces;
        this.carSpaces = carSpaces;
        this.largeSpaces = largeSpaces;
        this.motorcycleSpacesLeft = motorcycleSpaces; // On construction assume no vehicles in parking lot
        this.carSpacesLeft = carSpaces; // On construction assume no vehicles in parking lot
        this.largeSpacesLeft = largeSpaces; // On construction assume no vehicles in parking lot
    }

    public int getTotalSpaces() {
        return motorcycleSpaces + carSpaces + largeSpaces; // Total spaces from creation
    }

    public int getTotalSpacesRemaining() {
        return motorcycleSpacesLeft + carSpacesLeft + largeSpacesLeft; // Spaces remaining when vehicles populate
    }

    public boolean isParkingFull() {
        return getTotalSpacesRemaining() == 0; // Check if total spaces remaining is 0
    }

    public boolean isParkingEmpty() {
        return getTotalSpaces() == getTotalSpacesRemaining(); // Check if total spaces remaining is total spaces from creation
    }

    public boolean areMotorcyclesFull() {
        return motorcycleSpacesLeft == 0; // Check if motorcycle spots full
    }

    public boolean areCarsFull() {
        return carSpacesLeft == 0; // Check if car spots full
    }

    public boolean areLargeFull() {
        return largeSpacesLeft == 0; // Check if large spots full
    }

    public String newVehicleParking(String vehicle) {
        if(getTotalSpacesRemaining()==0) { return "There are no spaces available to park."; } // If no spaces remaining
        boolean successfullyParked; // Checking if they've parked successfully
        switch(vehicle.toLowerCase(Locale.ROOT)) { // Lowercase input
            case "motorcycle": // Motorcycles first
                successfullyParked = motorcycleParking(); // Use method to check, however motorcycle is always true
                break;
            case "car": // Cars next. Can't go into motorcycle space so can't always add to cars parked
                successfullyParked = carParking(); // Use method to check
                break;
            case "van": // Vans last. Can't go into motorcycle spot or singular car spot
                successfullyParked = vanParking(); // Use method to check
                break;
            default:
                return "That isn't a valid vehicle for the parking lot!"; // Check for invalid input
        }
        return (successfullyParked) ? "Parked successfully" : "Didn't park successfully"; // Show that parking successful
    }

    public int howManySpotsVansTakeUp() {
        if(vansParked < largeSpaces) {
            return vansParked; // If not all the large spaces are taken up, just how many vans are parked
        } else {
            return largeSpaces + (vansParked-largeSpaces)*3; // Otherwise, total large spaces + how many regular spaces taken up
        }
    }

    public int howManySpotsMotorcyclesTakeUp() {
        return motorcyclesParked; // Motorcycles always take up 1 spot so can just return number, getter
    }

    public int howManySpotsCarsTakeUp() {
        return carsParked; // Cars always take up 1 spot so can just return number, getter
    }

    private boolean motorcycleParking() {
        motorcyclesParked++; // If there are any spaces available (already checked) motorcycle can park
        if(motorcycleSpacesLeft != 0) { // Fill up motorcycle spaces first
            motorcycleSpacesLeft--;
        } else if(carSpacesLeft != 0) { // Then fill up car spaces
            carSpacesLeft--;
        } else { // Finally, fill up large spaces
            largeSpacesLeft--;
        }
        return true;
    }

    private boolean carParking() {
        if(carSpacesLeft != 0) {  // Fill up car spaces first
            carsParked++;
            carSpacesLeft--;
        } else if(largeSpacesLeft != 0) { // Fill up large spaces after
            largeSpacesLeft--;
            carsParked++;
        } else {
            return false; // If there aren't any of these spaces then can't park
        }
        return true;
    }

    private boolean vanParking(){
        if(largeSpacesLeft != 0) { // Check large spaces first (as only 1 taken up)
            largeSpacesLeft--;
            vansParked++;
        } else if(carSpacesLeft >= 3) { // Check car spaces next (needs 3 spaces)
            carSpacesLeft -= 3;
            vansParked++;
        } else {
            return false; // If there aren't any of these spaces then can't park
        }
        return true;
    }
}
