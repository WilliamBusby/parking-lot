package com.nology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    private ParkingLot parkingLot;

    @BeforeEach
    void setUp() {this.parkingLot = new ParkingLot(10,5,5); }

    @AfterEach
    void tearDown() {}

    @Test
    public void checkTotalSpaces_FromConstructorCreation() {
        int numberOfSpaces = parkingLot.getTotalSpaces();
        assertEquals(20,numberOfSpaces,0);
    }

    @Test
    public void checkTotalSpacesRemaining_FromConstructorCreation() {
        int numberOfSpacesRemaining = parkingLot.getTotalSpacesRemaining();
        assertEquals(20,numberOfSpacesRemaining,0);
    }

    @Test
    public void checkNoVehicles_FromConstructorCreation() {
        int totalVehiclesParked = parkingLot.getTotalVehicles();
        assertEquals(0,totalVehiclesParked,0);
    }

    @Test
    public void addCarToParkingLot_CheckSuccessfullyAdded() {
        String response = parkingLot.newVehicleParking("car");
        assertEquals("Parked successfully", response);
        String responseCapital = parkingLot.newVehicleParking("CAR");
        assertEquals("Parked successfully", response);
    }

    @Test
    public void checkInvalidInput() {
        String response = parkingLot.newVehicleParking("tomato");
        assertEquals("That isn't a valid vehicle for the parking lot!", response);
    }

    @Test
    public void addCarToParkingLot_CheckSpacesRemaining() {
        parkingLot.newVehicleParking("car");
        int numberOfSpacesRemaining = parkingLot.getTotalSpacesRemaining();
        assertEquals(19,numberOfSpacesRemaining,0);
    }

    @Test
    public void addCarToParkingLot_CheckVehiclesParked() {
        parkingLot.newVehicleParking("car");
        int totalVehiclesParked = parkingLot.getTotalVehicles();
        assertEquals(1,totalVehiclesParked,0);
    }

    @Test
    public void addCarToParkingLot_CheckSpotsCarsTakeUp() {
        parkingLot.newVehicleParking("car");
        int carsParked = parkingLot.howManySpotsCarsTakeUp();
        assertEquals(1,carsParked,0);
    }

    @Test
    public void addCarsToFillCarPark_CheckTryingAddNewCar() {
        for(int i=0; i<10; i++) {
            parkingLot.newVehicleParking("car");
        }
        String response = parkingLot.newVehicleParking("car");
        assertEquals("Didn't park successfully", response);
    }

    @Test
    public void addVansToFillSpaces_CheckTakeOverCarSpaces() {
        for(int i = 0; i< 6; i++) {
            parkingLot.newVehicleParking("van");
        }
        int spacesTakenUp = parkingLot.howManySpotsVansTakeUp();
        assertEquals(8,spacesTakenUp,0);
    }

    @Test
    public void addVansToNotFillSpace_CheckIfLargeSpacesFull() {
        parkingLot.newVehicleParking("van");
        boolean response = parkingLot.areLargeFull();
        assertFalse(response);
    }

    @Test
    public void addVanstoFillSpaces_CheckIfLargeSpacesFull() {
        for(int i = 0; i< 6; i++) {
            parkingLot.newVehicleParking("van");
        }
        boolean response = parkingLot.areLargeFull();
        assertTrue(response);
    }

}
