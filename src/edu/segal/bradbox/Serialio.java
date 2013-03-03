package edu.segal.bradbox;

// Import serial utilities
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 
import java.util.Enumeration;


public class Serialio implements SerialPortEventListener {
	BradBox bradbox;
	JavaMonkey monkey;
    SerialPort serialPort;
    private BufferedReader input;
    private OutputStream output;
    // Timeout for waiting port to open
    private static final int SERIAL_TIMEOUT = 2000;
    // Serial baud rate. Do not change this.
    private static final int DATA_RATE = 9600;
    // THIS WILL NEED TO BE CONFIGURED --- maybe have it send a signal to each COM until it gets a signal back
    // COM10 for DynaVox (back USB port)
    // COM4 for LEELA
    private static final String SERIAL_PORT = "COM4";
    
    Serialio(BradBox bb) {
    	bradbox = bb;
    	monkey = bb.monkey;
    	initialize();
    }
    
    public void LoudspeakerOn() {
    	try {
			output.write('h');
		} catch (IOException e) {
			System.out.println("Error: could not write LoudspeakerOn");
			e.printStackTrace();
		}
    }
    
    public void LoudspeakerOff() {
    	try {
			output.write('l');
		} catch (IOException e) {
			System.out.println("Error: could not write LoudspeakerOn");
			e.printStackTrace();
		}
    }
    
    public void initialize () {
    	// Initialization for Arduino communication
        CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			if (currPortId.getName().equals(SERIAL_PORT)) {
				portId = currPortId;
				break;
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}
		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					SERIAL_TIMEOUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		} 
    }
    
    public void turnHigh() {
    	while(true)
			try {
				System.out.println("Printing high, bitches");
				output.write('h');
			} catch (IOException e) {
				System.out.println("Error: could not write high");
				e.printStackTrace();
			}
    }
    
    /**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine=input.readLine();
				
				if(inputLine.equals("short")){
					System.out.println("Answering Call");
					monkey.press("KEYCODE_CALL");
					//bradbox.gui.keypadPanel.initiateCall();
				}
				if(inputLine.equals("long")){
					System.out.println("Restarting Device");
					monkey.reboot();
				}
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}
}
