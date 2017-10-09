/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bindo
 */

import java.util.Random;


public class CitySim9005 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		if(args.length == 1) {
			try{
				Integer.valueOf(args[0]);
			}catch(Exception e){
				System.out.println("The argument should be a valid Java 32-bit integer.");
				System.exit(0);
			}
			
			int startPosition = 1;
			Random generator = new Random(Integer.valueOf(args[0]));
			int driver = 1;
			
			Location Hotel = new Location("Hotel","Bill St.","Fourth Ave.","Library","Diner");
			Location Diner = new Location("Diner","Phil St.","Fourth Ave.","Coffee","Outside City");
			Location Coffee = new Location("Coffee","Fifth Ave.","Phil St.","Library","Diner");
			Location Library = new Location ("Library","Fifth Ave.","Bill St.","Outside City","Hotel");
			
			for(int i = 0; i<5;i++){
				City city = new City();
				city.addToList(Hotel);
				city.addToList(Diner);
				city.addToList(Coffee);
				city.addToList(Library);
				city.connectCity();
				city.setStartingLocation(generator.nextInt(4));
				while(!city.getCurrentLocation().equals("Outside City")){
					simulate(driver, city, generator);
				}
				System.out.println("Driver " + driver + " has gone to " + city.getOutsideCityName() + "!");
				System.out.println("----------------------------");
				driver++;
			}
		}else{
			System.out.println("Please enter ONE argument that is of a valid Java 32-bit integer type.");
			System.exit(0);
		}
	}
    
    private static void simulate(int driver, City city, Random generator){
        int i = generator.nextInt(2);
        if(i == 0) System.out.println("Driver " + driver + " " + city.goLeft());
        else  System.out.println("Driver " + driver + " " + city.goRight());
    }
    
}
