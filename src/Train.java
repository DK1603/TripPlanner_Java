
public class Train extends PublicTransport {

	private Station station; // instance of composition
	
	
	public Train() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Constructor, getters and setters
	public Train(Station station) { //empty constructor
		this.station = station;
	}

	//getters and setters
	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	@Override
	public void calculatePayment() {
		
		double baseFare = getBaseFare();
		
		double farePerStation = station.getFarePerStation();
		int nStations = station.getNStations();
		
		double totalFare;
		
		if (nStations <= 5) {
			
			totalFare = baseFare;
		}
		else { // if number of station is greater than 
			
			totalFare = baseFare + ((nStations - 5) * farePerStation);
		}
		
		setTotalFare(totalFare);
	}
    @Override
    public String toString() {
        return "Transport " + ": KORAIL\nFare: " + getTotalFare();
    }

}
