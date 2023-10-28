import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class Payable implements ActionListener {
	
	//buttons
	private JFrame frmTripPlanner;
	private JLabel plannerLogo;
	private JButton trainButton;
	private JButton busButton;
	private JButton taxiButton;
	private JTextArea tripPlanner;
	private JButton yesButton;
	private JButton noButton;
	private JButton sevenBtn;
	private JButton eightBtn;
	private JButton nineBtn;
	private JButton fourBtn;
	private JButton fiveBtn;
	private JButton sixBtn;
	private JButton oneBtn;
	private JButton twoBtn;
	private JButton threeBtn;
	private JButton twoNullBtn;
	private JButton oneNullBtn;
	private JButton cancelBtn;
	private JButton clearBtn;
	private JButton enterBtn;
	private JLabel plannerLabel;

	boolean isTaxi = false; // flag for a taxi
	boolean isBus = false; //flag for a bus
	
	private double baseFare = 0; //variable for buses and trains to store the incoming base fare
	private double farePerSt = 0; // for buses and trains (fare per station)
	private int numOfSt = 0; //number of stations (also user's input)
	

	public int state = 0; //state to control the process

	String userInput = ""; //user input - empty string

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payable window = new Payable();
					window.frmTripPlanner.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public Payable() {
		initialize();

	}

	
	//creating the instances of each class and composition
	PublicTransport transport = new PublicTransport();  
	Station stationObj = new Station(farePerSt, numOfSt);
	Bus busObj = new Bus();
	Train trainObj = new Train();
	Taxi taxiObj = new Taxi();

	// list to store the description of each transport used and its corresponding
	private ArrayList<String> transactions = new ArrayList<>();

	@Override
	public void actionPerformed(ActionEvent e) { // Action event for each button

		Object input = e.getSource();

		if (input == trainButton || input == busButton || input == taxiButton) { // defining state for controlling the process

			if (input == trainButton && state == 0) {
				tripPlanner.setText("You have chosen TRAIN." + "\n" + "Enter the base fare: ");

				Station station = new Station(); // instance of station class
				station.setFarePerStation(farePerSt); //pass farePerSt to the station class
				station.setNStations(numOfSt); // pass numOfSt to the station class as a parameter

				transport = new Train(station); // instance of Train class
				state = 1;

			} else if (input == busButton && state == 0) { // for bus case
				tripPlanner.setText("You have chosen BUS." + "\n" + "Enter the base fare: ");
				Station station = new Station(); // instance of station class
				station.setFarePerStation(farePerSt); 
				station.setNStations(numOfSt);

				transport = new Bus(station); 
				state = 1;
				isBus = true;

			} else if (input == taxiButton && state == 0) { // for taxi case
				tripPlanner.setText("You have chosen TAXI." + "\n" + "Enter the base fare: ");

				transport = new Taxi();

				state = 1;
				isTaxi = true;
			}

		}
		
		// buttons as listeners
		else if (input == sevenBtn) {

			tripPlanner.append("7");
			userInput += '7';

		} else if (input == eightBtn) {

			tripPlanner.append("8");
			userInput += '8';

		} else if (input == nineBtn) {

			tripPlanner.append("9");
			userInput += '9';

		} else if (input == fourBtn) {

			tripPlanner.append("4");
			userInput += '4';

		} else if (input == fiveBtn) {

			tripPlanner.append("5");
			userInput += '5';

		} else if (input == sixBtn) {

			tripPlanner.append("6");
			userInput += '6';

		} else if (input == oneBtn) {

			tripPlanner.append("1");
			userInput += '1';

		} else if (input == twoBtn) {

			tripPlanner.append("2");
			userInput += '2';

		} else if (input == threeBtn) {

			tripPlanner.append("3");
			userInput += '3';

		} else if (input == oneNullBtn) {

			tripPlanner.append("0");
			userInput += '0';

		} else if (input == twoNullBtn) {

			tripPlanner.append("00");
			userInput += "00";
		}

		else if (input == enterBtn && state == 1) {
			if (!isTaxi) { //for buses and trains
				
				tripPlanner.setText("Enter fare per station (for extra stations): ");

				baseFare = Double.parseDouble(userInput); // Convert a string to a double data type
				userInput = "";

				stationObj.setNStations(numOfSt); //passing this class variables to classes as parameters and using their methods (inheritance)
				busObj.setBaseFare(baseFare);
				trainObj.setBaseFare(baseFare);

				state = 2;

			} else { // for taxi because it does not have station as a composition
				tripPlanner.setText("Enter fare per km: ");
				baseFare = Double.parseDouble(userInput);
				userInput = "";

				taxiObj.setBaseFare(baseFare);

				state = 2;

			}
			// store the user input into classes
		} else if (input == enterBtn && state == 2) {
			if (!isTaxi) {
				tripPlanner.setText("Enter number of stations: ");
				farePerSt = Double.parseDouble(userInput);
				userInput = "";

				// bus1.setStation(station1);

				stationObj.setFarePerStation(farePerSt);

				state = 3;
			} else if (isTaxi) {
				tripPlanner.setText("Enter distance (in km): "); // for Taxi
				farePerSt = Double.parseDouble(userInput);
				userInput = "";

				taxiObj.setFarePerKm(farePerSt); 

				state = 3;

			}

		} else if (input == enterBtn && state == 3) { // last stage of assignments: variables -> parameters of classes
			if (!isTaxi) {
				tripPlanner.setText("Would you like to add more transport (left menu)?");
				numOfSt = Integer.parseInt(userInput);
				userInput = "";

				stationObj.setNStations(numOfSt);
				busObj.setStation(stationObj);
				trainObj.setStation(stationObj);

				state = 4;
			} else if (isTaxi) {
				tripPlanner.setText("Would you like to add more transport (left menu)?");
				numOfSt = Integer.parseInt(userInput);
				userInput = "";

				taxiObj.setDistance(numOfSt);

				state = 4;

			}

		}

		else if (input == yesButton) { // storing the coming payments in a list "transactions"
			if (state == 4) { // Assuming state 4 means the transaction is to be processed
				if (!isTaxi && isBus) { // For Bus
					
					busObj.calculatePayment();
					transactions.add(busObj.toString()); // call the toString method from the proper class
					
					isTaxi = false; // also reset all flags after storing
					isBus = false;
					
					System.out.println("Bus Transaction Added"); // Debug
					
				} else if (isTaxi) { // For Taxi
					
					taxiObj.calculatePayment();
					transactions.add(taxiObj.toString());
					
					isTaxi = false;
					isBus = false;
					
					System.out.println("Taxi Transaction Added"); // Debug
				} else if (isBus == false && isTaxi == false) { // For Train
					
					trainObj.calculatePayment();
					transactions.add(trainObj.toString());
					
					isTaxi = false;
					isBus = false;
					
					System.out.println("Train Transaction Added"); // Debug
				}

				tripPlanner.setText("Choose the transport (from the left menu)");
				state = 0; // state == 0 -> new loop
			}
		}

		else if (input == noButton && state != 0) { // noBtn can be clicked only once in a row
		    StringBuilder summary = new StringBuilder(); // instance of a string builder class
		    
		    if (state == 4) { // Assuming state 4 means the transaction is to be processed
		        if (!isTaxi && isBus) { // For Bus
		            busObj.calculatePayment();
		            transactions.add(busObj.toString());
		            System.out.println("Bus Transaction Added"); // Debug
		        } else if (isTaxi) { // For Taxi
		            taxiObj.calculatePayment();
		            transactions.add(taxiObj.toString());
		            System.out.println("Taxi Transaction Added"); // Debug
		        } else if (!isBus && !isTaxi) { // For Train
		            trainObj.calculatePayment();
		            transactions.add(trainObj.toString());
		            System.out.println("Train Transaction Added"); // Debug
		        }
		        
		        isTaxi = false;  // Resetting flags outside of the condition blocks
		        isBus = false; // It will be executed regardless of which block is entered

		        tripPlanner.setText("Choose the transport (from the left menu)");
		        state = 0;
		    }

		    int counter = 1; // for formatting
		    double totalFare = 0; // For storing total fare
		    
		    for (String transaction : transactions) {
		        summary.append(counter).append(". ").append(transaction).append("\n"); // formatting and displaying each transaction which has been made
		        counter++;
		        
		        // Assuming that the fare is the last part of the transaction string after a space
		        String[] parts = transaction.split(" ");
		        totalFare += Double.parseDouble(parts[parts.length - 1]); // Parsing the last part as a double
		    }

		    summary.append("==================\n");
		    summary.append("Total Trip Fare: ").append(totalFare).append("\n"); // Added the total fare to the summary
		    
		    tripPlanner.setText(summary.toString());

		    System.out.println("Number of Transactions: " + transactions.size()); // Debug

		    transactions.clear(); // clear all transactions for a new loop
		    state = 0;
		}


		//
		else if (input == cancelBtn) { //reset everything if input == cancelBtn
			tripPlanner.setText("Cancelled!" + "\n" + "New plan:" + "\n" + "Choose the transport (from the left menu)"); 
			userInput = "";
			state = 0;
		}

		else if (input == clearBtn) { 
		    // Getting the current text in the tripPlanner
		    String currentText = tripPlanner.getText();
		    
		    // Finding the index of ": "
		    int index = currentText.lastIndexOf(": ");
		    
		    // If ": " is found in the string
		    if (index != -1) {
		        // Keeping the text before ": " and appending ": " to maintain the format
		        String newText = currentText.substring(0, index + 2); // +2 to include ": "
		        tripPlanner.setText(newText);
		        userInput ="";
		    }
		}


	}
	
	//Interface
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTripPlanner = new JFrame();
		frmTripPlanner.setTitle("Trip Planner");
		frmTripPlanner.setBounds(100, 100, 814, 600);
		frmTripPlanner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTripPlanner.getContentPane().setLayout(null); 

		plannerLogo = new JLabel("");
		plannerLogo.setBounds(201, 6, 413, 86);
		plannerLogo.setIcon(new ImageIcon(getClass().getResource("/assets/planner_logo.png")));
		frmTripPlanner.getContentPane().add(plannerLogo);

		trainButton = new JButton("TRAIN");
		trainButton.addActionListener(this); // add the button to action listeners

		trainButton.setBounds(86, 128, 117, 29);
		trainButton.setFont(new Font("PingFang SC", Font.BOLD, 14));
		trainButton.setIcon(new ImageIcon(getClass().getResource("/assets/train1.png")));
		frmTripPlanner.getContentPane().add(trainButton);

		busButton = new JButton("BUS");
		busButton.addActionListener(this);

		busButton.setBounds(86, 169, 117, 29);
		busButton.setFont(new Font("PingFang SC", Font.BOLD, 14));
		busButton.setIcon(new ImageIcon(getClass().getResource("/assets/bus.png")));
		frmTripPlanner.getContentPane().add(busButton);

		taxiButton = new JButton("TAXI");
		taxiButton.setBounds(86, 210, 117, 29);
		taxiButton.setFont(new Font("PingFang SC", Font.BOLD, 14));
		taxiButton.setIcon(new ImageIcon(getClass().getResource("/assets/taxi.png")));
		taxiButton.addActionListener(this);
		frmTripPlanner.getContentPane().add(taxiButton);

		tripPlanner = new JTextArea("Choose the transport (from the left menu).");
		tripPlanner.setFont(new Font("STIX Two Math", Font.PLAIN, 15));
		tripPlanner.setBounds(226, 114, 360, 232);
		frmTripPlanner.getContentPane().add(tripPlanner);

		yesButton = new JButton("Yes");
		yesButton.setBounds(609, 141, 125, 37);
		yesButton.setIcon(new ImageIcon(getClass().getResource("/assets/yes.png")));
		frmTripPlanner.getContentPane().add(yesButton);
		yesButton.addActionListener(this);

		noButton = new JButton("No");
		noButton.setBounds(609, 192, 125, 37);
		noButton.setIcon(new ImageIcon(getClass().getResource("/assets/no.png")));
		frmTripPlanner.getContentPane().add(noButton);
		noButton.addActionListener(this);

		sevenBtn = new JButton("");
		sevenBtn.setIcon(new ImageIcon(getClass().getResource("/assets/seven.png")));
		sevenBtn.setActionCommand("7");
		sevenBtn.addActionListener(this);
		sevenBtn.setBounds(236, 350, 82, 45);
		frmTripPlanner.getContentPane().add(sevenBtn);

		eightBtn = new JButton("");
		eightBtn.setIcon(new ImageIcon(getClass().getResource("/assets/eight.png")));
		eightBtn.setBounds(315, 350, 82, 45);
		frmTripPlanner.getContentPane().add(eightBtn);
		eightBtn.setActionCommand("8");
		eightBtn.addActionListener(this);

		nineBtn = new JButton("");
		nineBtn.setIcon(new ImageIcon(getClass().getResource("/assets/nine.png")));
		nineBtn.setBounds(394, 350, 82, 45);
		frmTripPlanner.getContentPane().add(nineBtn);
		nineBtn.setActionCommand("9");
		nineBtn.addActionListener(this);

		fourBtn = new JButton("");
		fourBtn.setIcon(new ImageIcon(getClass().getResource("/assets/four.png")));
		fourBtn.setBounds(236, 391, 82, 45);
		frmTripPlanner.getContentPane().add(fourBtn);
		fourBtn.setActionCommand("4");
		fourBtn.addActionListener(this);

		fiveBtn = new JButton("");
		fiveBtn.setIcon(new ImageIcon(getClass().getResource("/assets/five.png")));
		fiveBtn.setBounds(315, 391, 82, 45);
		frmTripPlanner.getContentPane().add(fiveBtn);
		fiveBtn.setActionCommand("5");
		fiveBtn.addActionListener(this);

		sixBtn = new JButton("");
		sixBtn.setIcon(new ImageIcon(getClass().getResource("/assets/six.png")));
		sixBtn.setBounds(394, 391, 82, 45);
		frmTripPlanner.getContentPane().add(sixBtn);
		sixBtn.setActionCommand("6");
		sixBtn.addActionListener(this);

		oneBtn = new JButton("");
		oneBtn.setIcon(new ImageIcon(getClass().getResource("/assets/one.png")));
		oneBtn.setBounds(236, 433, 82, 45);
		frmTripPlanner.getContentPane().add(oneBtn);
		oneBtn.setActionCommand("1");
		oneBtn.addActionListener(this);

		twoBtn = new JButton("");
		twoBtn.setIcon(new ImageIcon(getClass().getResource("/assets/two.png")));
		twoBtn.setBounds(315, 433, 82, 45);
		frmTripPlanner.getContentPane().add(twoBtn);
		twoBtn.setActionCommand("2");
		twoBtn.addActionListener(this);

		threeBtn = new JButton("");
		threeBtn.setIcon(new ImageIcon(getClass().getResource("/assets/three.png")));
		threeBtn.setBounds(394, 433, 82, 45);
		frmTripPlanner.getContentPane().add(threeBtn);
		threeBtn.setActionCommand("3");
		threeBtn.addActionListener(this);

		twoNullBtn = new JButton("00");
		twoNullBtn.setVerticalAlignment(SwingConstants.BOTTOM);
		twoNullBtn.setFont(new Font("Academy Engraved LET", Font.PLAIN, 24));
		twoNullBtn.setBounds(315, 474, 82, 45);
		frmTripPlanner.getContentPane().add(twoNullBtn);
		twoNullBtn.setActionCommand("00");
		twoNullBtn.addActionListener(this);

		oneNullBtn = new JButton("");
		oneNullBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		oneNullBtn.setIcon(new ImageIcon(getClass().getResource("/assets/zero.png")));
		oneNullBtn.setBounds(236, 474, 82, 45);
		frmTripPlanner.getContentPane().add(oneNullBtn);
		oneNullBtn.setActionCommand("0");
		oneNullBtn.addActionListener(this);

		cancelBtn = new JButton(" CANCEL");
		cancelBtn.setHorizontalAlignment(SwingConstants.LEADING);
		cancelBtn.setIcon(new ImageIcon(getClass().getResource("/assets/cancel.png")));
		cancelBtn.setBounds(473, 350, 108, 45);
		frmTripPlanner.getContentPane().add(cancelBtn);
		cancelBtn.addActionListener(this);

		clearBtn = new JButton("  CLEAR");
		clearBtn.setHorizontalAlignment(SwingConstants.LEADING);
		clearBtn.setIcon(new ImageIcon(getClass().getResource("/assets/clear.png")));
		clearBtn.setBounds(473, 391, 108, 45);
		frmTripPlanner.getContentPane().add(clearBtn);
		clearBtn.addActionListener(this);

		enterBtn = new JButton("  ENTER");
		enterBtn.setHorizontalAlignment(SwingConstants.LEADING);
		enterBtn.setIcon(new ImageIcon(getClass().getResource("/assets/enter.png")));
		enterBtn.setBounds(473, 433, 108, 45);
		frmTripPlanner.getContentPane().add(enterBtn);
		enterBtn.addActionListener(this);

		plannerLabel = new JLabel("Trip Planner");
		plannerLabel.setBounds(364, 93, 82, 16);
		frmTripPlanner.getContentPane().add(plannerLabel);

	}
}
