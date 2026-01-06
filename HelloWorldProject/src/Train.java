import java.util.*;

public class Train {
    private String engineNumber;
    private String destination;
    private List<TrainCar> cars;
    private int totalWeight;
    private int weightLimit;
    
    public Train(String destination, int weightLimit) {
        this.engineNumber = "ENG00000";
        this.destination = destination;
        this.cars = new ArrayList<>();
        this.totalWeight = 0;
        this.weightLimit = weightLimit;
    }
    
    public Train(String engineNumber, String destination) {
        this.engineNumber = engineNumber;
        this.destination = destination;
        this.cars = new ArrayList<>();
        this.totalWeight = 0;
        this.weightLimit = Integer.MAX_VALUE;
    }
    
    public boolean canAddCar(TrainCar car) {
        return totalWeight + car.getWeight() <= weightLimit;
    }
    
    public void addCar(TrainCar car) {
        cars.add(car);
        totalWeight += car.getWeight();
    }
    
    public String getDestination() { return destination; }
    public List<TrainCar> getCars() { return cars; }
    public int getTotalWeight() { return totalWeight; }
    public boolean isEmpty() { return cars.isEmpty(); }
    
    public void depart() {
        System.out.println(engineNumber + " leaving for " + destination + 
                         " with the following cars:");
        for (TrainCar car : cars) {
            System.out.println(car);
        }
    }
}