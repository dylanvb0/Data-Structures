package project5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AirplaneSimulation {
	final static String FILE_NAME = "current.data"; 
	final static int MAX_NUM_RUNWAYS = 2; // will create simulations with runways from 1 to this number
	public static int clock = 0;
	public static int takeoff_duration;
	public static int landing_duration;
	public static float departure_rate;
	public static float arrival_rate;
	public static int reserve_fuel_time;
	public static int simulation_time;
	public static String description;
	public static int crashes;
	public static ArrayList<Integer> departure_wait_times;
	public static ArrayList<Integer> arrival_wait_times;
	static double airProbability;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		importScenario();
		beginSimulation();
	}
	
	static void beginSimulation(){
		for(int i = 1; i <= MAX_NUM_RUNWAYS; i++){
			Runway.createRunways(i);
			departure_wait_times = new ArrayList<Integer>();
			arrival_wait_times = new ArrayList<Integer>();
			clock = 0;
			crashes = 0;
			while(clock <= simulation_time){
				tickClock();
			}
			printOutput();
		}
	}
	
	public static void tickClock(){
		clock++;
		// add planes to queue if needed
		Random rand = new Random();
		if(rand.nextDouble() < departure_rate){
			Runway.enqueue(false);
		}
		if(rand.nextDouble() < arrival_rate){
			Runway.enqueue(true);
		}
		// remove planes from queue if possible
		for(Runway r: Runway.runways){
			if(!r.isEmpty())
				r.handleCompletedProcess();
			r.handleCrashes();
			if(r.isEmpty()){
				if(!r.airQueue.isEmpty() && !r.groundQueue.isEmpty()){
					// use given probability if there are planes in air and on ground
					r.dequeue(rand.nextDouble() < airProbability);
				}else{
					if(!r.airQueue.isEmpty()){
						r.dequeue(true);
					}else if(!r.groundQueue.isEmpty()){
						r.dequeue(false);
					}
				}
			}
		}
		
	}
	
	private static void printOutput() {
		System.out.println(Runway.runways.size() + " Runway(s): ");
		System.out.println(description);
		System.out.println(departure_wait_times.size() + " planes departed.");
		System.out.println(average(departure_wait_times) + " average wait time for departing planes.");
		System.out.println(arrival_wait_times.size() + " planes arrived.");
		System.out.println(average(arrival_wait_times) + " average wait time for arriving planes.");
		System.out.println(crashes + " planes crashed.");
		System.out.println();
	}
	
	private static double average(ArrayList<Integer> nums) {
		int sum = 0;
		for(int num : nums){
			sum += num;
		}
		return sum / (double)nums.size();
	}

	static void importScenario(){
		try{
			File f = new File(FILE_NAME);
			Scanner s = new Scanner(f);
			takeoff_duration = s.nextInt();
			landing_duration = s.nextInt();
			departure_rate = s.nextFloat();
			arrival_rate = s.nextFloat();
			reserve_fuel_time = s.nextInt();
			simulation_time = s.nextInt();
			s.nextLine();
			description = s.nextLine();
			s.close();
			Scanner input = new Scanner(System.in);
			System.out.print("Probability of choosing plane in air when planes are in air and on ground? (As decimal between 0 and 1) >> ");
			airProbability = input.nextDouble();
			if(airProbability > 1 || airProbability < 0){
				System.out.println("Invalid input");
				System.exit(1);
			}
			input.close();
		}catch(FileNotFoundException e){
			System.out.println("File not found");
			System.exit(1);
		}
		
	}

}
