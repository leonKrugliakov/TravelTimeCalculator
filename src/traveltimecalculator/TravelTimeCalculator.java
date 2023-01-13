//***************************************************************************************************** 
// 
// 	 	File: 	 	 	TravelTimeCalculator
// 
// 	 	Student:  	 	Leon Krugliakov
// 
// 	 	Assignment: 	  	Assignement #1 Travel Time Calculator
// 
// 	 	Course Name: 	 	JAVA Programming 
// 
// 	 	Course Number:  	COSC 2050 - 01 
// 
// 
// 	 	Due: 	 	 	January 29, 2020  
//
//
// 	 	This program asks the user to enter total distance traveled and
//              then it calculates the total amount of minutes and hours it will
// 	 	take to travel. The program also validates the user's input.
// 	 	Other files required:  
// 	 	 
//***************************************************************************************************** 



//package traveltimecalculator;

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;
public class TravelTimeCalculator 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        boolean contin = true;
        String name,
                distanceInput,
                speedInput,
                continueInput;
        BigDecimal distance,
                speed;
        
        System.out.println("Welcome to the Travel Time Calculator!");
        System.out.println();
        
        System.out.print("Please enter your name:");
        name = sc.nextLine();
        
        while(contin)
        {
            System.out.print("\nEnter miles:");
            distanceInput = sc.nextLine();
            distance = new BigDecimal(distanceInput);
            distance = validateMiles(distance, sc);
            
            System.out.print("\nEnter miles per hour:");
            speedInput = sc.nextLine();
            speed = new BigDecimal(speedInput);
            speed = validateSpeed(speed, sc);
            
            System.out.print("\nEstimated travel time for " + name + "\n");
            System.out.println(calculateTime(speed, distance));
            
            if(askToContinue(sc))
            {
                contin = true;
            }
            else
            {
                contin = false;
            }
            
            sc.nextLine();
        }
    }

//*****************************************************************************************************

    public static String calculateTime(BigDecimal speed, 
            BigDecimal distance)
    {
        final BigDecimal SIXTY = new BigDecimal("60");
        final BigDecimal ONE = new BigDecimal("1");
        BigDecimal minutes = new BigDecimal("0");
        BigDecimal hours = new BigDecimal("0");
        BigDecimal totalMinutes = new BigDecimal("0");
        String returnString = "";
        
        hours = distance.divide(speed, RoundingMode.FLOOR);
        totalMinutes = distance.divide(speed, 2, RoundingMode.HALF_UP).multiply(SIXTY);
        minutes = totalMinutes.remainder(SIXTY);
        
        if(hours.compareTo(ONE) > 0)
        {
            if(minutes.compareTo(ONE) > 0){
                returnString = "The time it would take you to travel this will be " + hours.setScale(0, RoundingMode.HALF_UP) + " hours and " + minutes.setScale(0, RoundingMode.HALF_UP) + " minutes.";
            }
            else if(minutes.compareTo(ONE) == 0){
                returnString = "The time it would take you to travel this will be " + hours.setScale(0, RoundingMode.HALF_UP) + " hours and " + minutes.setScale(0, RoundingMode.HALF_UP) + " minute.";
            }
        }
        else if(hours.compareTo(ONE) > 0)
        {
            if(minutes.compareTo(ONE) > 0){
                returnString = "The time it would take you to travel this will be " + hours.setScale(0, RoundingMode.HALF_UP) + " hour and " + minutes.setScale(0, RoundingMode.HALF_UP) + " minutes.";
            }
            else if(minutes.compareTo(ONE) == 0){
                returnString = "The time it would take you to travel this will be " + hours.setScale(0, RoundingMode.HALF_UP) + " hour and " + minutes.setScale(0, RoundingMode.HALF_UP) + " minute.";
            }
        }
        else
        {
            returnString = "The time it would take you to travel this will be " + hours.setScale(0, RoundingMode.HALF_UP) + " hours and " + minutes.setScale(0, RoundingMode.HALF_UP) + " minutes.";
        }
        
        return returnString;
    }

//*****************************************************************************************************

    public static BigDecimal validateMiles(BigDecimal milesInput, 
            Scanner sc)
    {
        final BigDecimal ZERO = new BigDecimal("0");
        boolean valid = false;
        String distance;
        
        while(!valid)
        {
            if(milesInput.compareTo(ZERO) <= 0)
            {
                valid = false;
                System.out.println("The input you have entered is invalid, please enter a number larger than 0. \n");
                distance = sc.nextLine();
                milesInput = new BigDecimal(distance);
                
            }
            else
            {
                valid = true;
            }
        }
        
        return milesInput;
    }

//*****************************************************************************************************

    public static BigDecimal validateSpeed(BigDecimal speedInput, Scanner sc)
    {
        final BigDecimal ZERO = new BigDecimal("0");
        final BigDecimal ONE_HUNDRED = new BigDecimal("100");
        boolean valid = false;
        String input;
        
        while(!valid)
        {
            
            if(speedInput.compareTo(ZERO) <= 0)
            {
                valid = false;
                System.out.println("The input you have entered is invalid, please enter a number larger than 0: ");
                input = sc.nextLine();
                speedInput = new BigDecimal(input);
                
                
            }
            else if(speedInput.compareTo(ONE_HUNDRED) >= 0)
            {
                valid = false;
                System.out.println("The input you have entered is invalid, please enter a number smaller than 100: ");
                input = sc.nextLine();
                speedInput = new BigDecimal(input);
            }
            else
            {
                valid = true;
            }
        }
        
        return speedInput;
    }

//*****************************************************************************************************

    public static boolean askToContinue(Scanner sc){
        String continueInput;
        boolean valid = false;
        System.out.print("\nWould you like to continue? ('y' for yes and 'n' for no):");
        continueInput = sc.next();
        
        while(!valid)
        {
            
            if(continueInput.equalsIgnoreCase("y"))
            {
                valid = true;
                return true;

            }
            else if(continueInput.equalsIgnoreCase("n"))
            {
                valid = true;
                return false;
            }
            else
            {
                System.out.println("You have entered an invalid input. Please try again.");
                continueInput = sc.next();
            }
            
        }
        return true;
    }
    
}
