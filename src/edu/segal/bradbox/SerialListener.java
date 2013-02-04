package edu.segal.bradbox;

// Import serial utilities
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 
import java.util.Enumeration;


public class SerialListener implements SerialPortEventListener {
	BradBox bradbox;
	JavaMonkey monkey;
    SerialPort serialPort;
    private BufferedReader input;
    private OutputStream output;
    // Timeout for waiting port to open
    private static final int SERIAL_TIMEOUT = 2000;
    // Serial baud rate. Do not change this.
    private static final int DATA_RATE = 9600;
    // THIS WILL NEED TO BE CONFIGURED
    private static final String SERIAL_PORT = "COM4";
    
    SerialListener(BradBox bb) {
    	bradbox = bb;
    	monkey = bb.monkey;
    	initialize();
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
					monkey.shell("am broadcast -a edu.segal.androidbradbox.smsbroadcast -e number '+16305369748' -e message 'Am i bothering you?'");
					//monkey.shell("am start -a android.intent.action.SENDTO -d sms:5132546751 --es sms_body \"Hey Brian, is this working??\" --ez exit_on_sent true");
					//monkey.press("am broadcast sendMessage");
					//bradbox.gui.keypadPanel.initiateCall();
				}
				
				if(inputLine.equals("long")){
					System.out.println("Restarting Device");
					monkey.shell("reboot");
				}
				
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}
}
