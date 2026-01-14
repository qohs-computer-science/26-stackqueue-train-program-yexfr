/*
 * Name: Nick Griffith
 * Date: 1/6/26
 * Class Period: 6
 * Description: Program making a train station using stacks and queues
 */

public class MyProgram {
	public static void main(String[] args) {
		int limitTrackA = 100000, limitTrackB = 100000, limitTrackC = 100000;
	
		TrainStation station = new TrainStation(limitTrackA, limitTrackB, limitTrackC);
    	station.readInput("data.txt");
        station.processCars();
	}
}