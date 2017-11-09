package project5;

import java.util.*;

public class Runway {
	Queue<Plane> airQueue;
	Queue<Plane> groundQueue;
	Plane inProgress; // Plane that is currently departing or landing
	static ArrayList<Runway> runways;
	
	public Runway(){
		airQueue = new LinkedList<Plane>();
		groundQueue = new LinkedList<Plane>();
		// add to list of runways when created
		runways.add(this);
	}
	
	// create specified number of runways
	static void createRunways(int num){
		runways = new ArrayList<Runway>();
		for(int i = 0; i < num; i++){
			new Runway();
		}
	}
	
	// get airQueue if passed true, else the groundQueue
	Queue<Plane> getQueue(boolean isAir){
		if(isAir){
			return airQueue;
		}else{
			return groundQueue;
		}
	}
	
	// Create plane and add it to the shortest queue
	static void enqueue(boolean isAir){
		Plane p = new Plane(isAir);
		// find shortest queue
		int min = Integer.MAX_VALUE;
		int index = -1;
		for(int i = 0; i < runways.size(); i++){
			int	size = runways.get(i).getQueue(isAir).size();
			if(size < min){
				min = size;
				index = i;
			}
		}
		// add to shortest queue
		p.queue_start = AirplaneSimulation.clock;
		runways.get(index).getQueue(isAir).offer(p);
	}
	
	// remove plane from queue and move them to inProgress
	void dequeue(boolean isAir){
		inProgress = getQueue(isAir).poll();
		inProgress.queue_end = AirplaneSimulation.clock;
		if(isAir){
			AirplaneSimulation.arrival_wait_times.add(inProgress.waitTime());
		}else{
			AirplaneSimulation.departure_wait_times.add(inProgress.waitTime());
		}
	}
	
	// get number of planes in front of queue that crashed and remove from queue
	void handleCrashes(){
		while(!airQueue.isEmpty() && airQueue.peek().isCrashed()){
			airQueue.poll();
			AirplaneSimulation.crashes++;
		}
	}
	
	// remove inProgress plane if it is finished departing or landing
	void handleCompletedProcess(){
		int processDuration = inProgress.isInAir ? AirplaneSimulation.landing_duration : AirplaneSimulation.takeoff_duration;
		if(AirplaneSimulation.clock - inProgress.queue_end >= processDuration){
			inProgress = null;
		}
	}
	
	// return true if there is no plane inProgress
	boolean isEmpty(){
		return inProgress == null;
	}
}
