package project5;

public class Plane {
	int queue_start;
	int queue_end;
	boolean isInAir;
	
	public Plane(boolean air){
		isInAir = air;
	}
	
	// Returns boolean whether plane ran out of fuel and crashed
	public boolean isCrashed(){
		return waitTime() > AirplaneSimulation.reserve_fuel_time;
	}
	
	// Returns the number of minutes it was waiting in the queue
	public int waitTime(){
		return AirplaneSimulation.clock - queue_start;
	}
	
}
