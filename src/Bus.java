
public class Bus extends PublicTransport {

	private Station station; // instance of composition (has-a)

	public Bus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bus(Station station) {
		this.station = station;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	@Override
	public void calculatePayment() {
		
		
		//calculate total fare based on the rules
		double baseFare = getBaseFare();
		
		double farePerStation = station.getFarePerStation();
		int nStations = station.getNStations();
		
		double totalFare;
		
		if (nStations <= 5) {
			
			totalFare = baseFare;
		}
		else {
			
			totalFare = baseFare + ((nStations - 5) * farePerStation);
		}
		
		setTotalFare(totalFare);
	}
    @Override
    public String toString() { // formatting
        return "Transport " + ": KORBUS\nFare: " + getTotalFare();
    }

}
