
public class Station extends PublicTransport{ // has-a composition to other child classes
	
    private double farePerStation;
    private int nStations;

    // Constructor, getters and setters
    
    public Station(double farePerStation, int nStations) {
        this.farePerStation = farePerStation;
        this.nStations = nStations;
    }

    public Station() { //empty constructor
		super();
		// TODO Auto-generated constructor stub
	}
    
    //getters and setters

	public double getFarePerStation() {
        return farePerStation;
    }

    public void setFarePerStation(double farePerStation) {
        this.farePerStation = farePerStation;
    }

    public int getNStations() {
        return nStations;
    }

    public void setNStations(int nStations) {
        this.nStations = nStations;
    }
    
}
