public class TrainCar {
    private String carNumber;
    private String contents;
    private String origin;
    private String destination;
    private int weight;
    private int miles;
    
    public TrainCar(String carNumber, String contents, String origin, 
                    String destination, int weight, int miles) {
        this.carNumber = carNumber;
        this.contents = contents;
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
        this.miles = miles;
    }
    
    public String getCarNumber() { return carNumber; }
    public String getContents() { return contents; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public int getWeight() { return weight; }
    public int getMiles() { return miles; }
    public void setMiles(int miles) { this.miles = miles; }
    
    @Override
    public String toString() {
        return carNumber + " containing " + contents;
    }
}