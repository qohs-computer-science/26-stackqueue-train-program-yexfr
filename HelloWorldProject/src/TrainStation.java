import java.util.*;
import java.io.*;

public class TrainStation {
    private Queue<Object> mainLine; // Track 0
    private Queue<TrainCar> inspectionTrack; // Track 1
    private Train trackA; // Trenton
    private Train trackB; // Charlotte
    private Train trackC; // Baltimore
    private Stack<TrainCar> trackD; // Other destinations
    
    private int limitTrackA;
    private int limitTrackB;
    private int limitTrackC;
    
    public TrainStation(int limitA, int limitB, int limitC) {
        mainLine = new LinkedList<>();
        inspectionTrack = new LinkedList<>();
        trackD = new Stack<>();
        this.limitTrackA = limitA;
        this.limitTrackB = limitB;
        this.limitTrackC = limitC;
        
        trackA = new Train("Trenton", limitA);
        trackB = new Train("Charlotte", limitB);
        trackC = new Train("Baltimore", limitC);
    }
    
    public void readInput(String filename) throws IOException {
        File textFile = new File(filename);
        Scanner fileReader = new Scanner(textFile);
        
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            if (line.equals("END")) break;
            
            if (line.startsWith("CAR")) {
                String[] parts = new String[6];
                i[0] = line.substring(3);
                for(int i = 1; i < 6; i++))
                    parts[i] = fileReader.nextLine();
                String carNumber = parts[0];
                String contents = parts[1];
                String origin = parts[2];
                String destination = parts[3];
                int weight = Integer.parseInt(parts[4]);
                int miles = Integer.parseInt(parts[5]);
                
                TrainCar car = new TrainCar(carNumber, contents, origin, 
                                           destination, weight, miles);
                mainLine.add(car);
            } else if (line.startsWith("ENG")) {
                String engineNumber = line.substring(3);
                String destination = fileReader.nextLine();
                mainLine.add(new String[]{engineNumber, destination});
            }
        }
        fileReader.close();
    }
    
    public void processCars() {
        // Process all items from main line
        while (!mainLine.isEmpty()) {
            Object item = mainLine.poll();
            
            if (item instanceof TrainCar) {
                TrainCar car = (TrainCar) item;
                
                // Check if car needs inspection
                if (car.getMiles() > 700) {
                    inspectionTrack.add(car);
                } else {
                    routeCar(car);
                }
            } else if (item instanceof String[]) {
                String[] engineInfo = (String[]) item;
                String engineNumber = engineInfo[0];
                String destination = engineInfo[1];
                departTrain(destination, engineNumber);
            }
        }
        
        // Process inspection track
        processInspectionTrack();
        
        // Final departures
        finalDepartures();
        
        // Print status
        printStatus();
    }
    
    private void routeCar(TrainCar car) {
        String dest = car.getDestination();
        
        if (dest.equals("Trenton")) {
            if (trackA.canAddCar(car)) {
                trackA.addCar(car);
            } else {
                trackA.depart();
                trackA = new Train("Trenton", limitTrackA);
                trackA.addCar(car);
            }
        } else if (dest.equals("Charlotte")) {
            if (trackB.canAddCar(car)) {
                trackB.addCar(car);
            } else {
                trackB.depart();
                trackB = new Train("Charlotte", limitTrackB);
                trackB.addCar(car);
            }
        } else if (dest.equals("Baltimore")) {
            if (trackC.canAddCar(car)) {
                trackC.addCar(car);
            } else {
                trackC.depart();
                trackC = new Train("Baltimore", limitTrackC);
                trackC.addCar(car);
            }
        } else {
            trackD.push(car);
        }
    }
    
    private void departTrain(String destination, String engineNumber) {
        if (destination.equals("Trenton") && !trackA.isEmpty()) {
            Train departing = new Train(engineNumber, destination);
            for (TrainCar car : trackA.getCars()) {
                departing.addCar(car);
            }
            departing.depart();
            trackA = new Train("Trenton", limitTrackA);
        } else if (destination.equals("Charlotte") && !trackB.isEmpty()) {
            Train departing = new Train(engineNumber, destination);
            for (TrainCar car : trackB.getCars()) {
                departing.addCar(car);
            }
            departing.depart();
            trackB = new Train("Charlotte", limitTrackB);
        } else if (destination.equals("Baltimore") && !trackC.isEmpty()) {
            Train departing = new Train(engineNumber, destination);
            for (TrainCar car : trackC.getCars()) {
                departing.addCar(car);
            }
            departing.depart();
            trackC = new Train("Baltimore", limitTrackC);
        }
    }
    
    private void processInspectionTrack() {
        while (!inspectionTrack.isEmpty()) {
            TrainCar car = inspectionTrack.poll();
            car.setMiles(100);
            routeCar(car);
        }
    }
    
    private void finalDepartures() {
        if (!trackA.isEmpty()) {
            trackA.depart();
        }
        if (!trackB.isEmpty()) {
            trackB.depart();
        }
        if (!trackC.isEmpty()) {
            trackC.depart();
        }
    }
    
    private void printStatus() {
        System.out.println("\nStation Status:");
        if (trackD.empty()) {
            System.out.println("Track D (Other Destinations): Empty");
        } else {
            System.out.println("Track D (Other Destinations):");
            for (TrainCar car : trackD) {
                System.out.println("  " + car);
            }
        }
    }
}