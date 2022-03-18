package com.nology;

import java.io.*;
import java.util.*;

class Solution {

    // - The parking lot can hold motorcycles, cars and vans
    //- The parking lot has motorcycle spots, car spots and large spots
    //- A motorcycle can park in any spot
    //- A car can park in a single compact spot, or a regular spot
    //- A van can park, but it will take up 3 regular spots
    //- These are just a few assumptions. Feel free to ask your interviewer about more assumptions as needed

    //- Tell us how many spots are remaining
    //- Tell us how many total spots are in the parking lot
    //- Tell us when the parking lot is full
    //- Tell us when the parking lot is empty
    //- Tell us when certain spots are full e.g. when all motorcycle spots are taken
    //- Tell us how many spots vans are taking up
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Please put code below");
        for (String string : strings) {
            System.out.println(string);
        }

        ParkingLot test = new ParkingLot(10,5,5);
        System.out.println(test.getTotalSpacesRemaining());
        System.out.println(test.getTotalSpaces());
        System.out.println(test.newVehicleParking("van"));
        System.out.println(test.getTotalSpacesRemaining());
    }


}
