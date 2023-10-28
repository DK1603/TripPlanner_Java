
public class PublicTransport {
	
	public String model;
	public double baseFare;
	public double totalFare;
	
	public PublicTransport() { // default constructor		
		
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getBaseFare() {
		return baseFare;
	}

	public void setBaseFare(double baseFare) {
		this.baseFare = baseFare;
	}

	public double getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(double totalFare) {
		this.totalFare = totalFare;
	}
	
	
	public void calculatePayment() { // empty method
		
	}

	@Override
	public String toString() {
		return "PublicTransport [model=" + model + ", baseFare=" + baseFare + ", totalFare=" + totalFare + "]";
	}
	
	

}
