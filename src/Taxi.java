
public class Taxi extends PublicTransport { //child class of Public transport parent

	private double farePerKm;
	private double distance;

	public Taxi() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//getter and setters
	public double getFarePerKm() {
		return farePerKm;
	}

	public void setFarePerKm(double farePerKm) {
		this.farePerKm = farePerKm;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public void calculatePayment() {
		
		double baseFare = getBaseFare();
		
		double distance = getDistance();
		double farePerKm = getFarePerKm();
		
		double totalFare = baseFare + (distance * farePerKm);
		
		setTotalFare(totalFare);
	}
	
    @Override
    public String toString() {
        return "Transport " + ": KAKAO TAXI\nFare: " + getTotalFare();
    }

}
