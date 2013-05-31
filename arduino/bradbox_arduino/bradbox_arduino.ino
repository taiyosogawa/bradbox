#define buttonPin 2                 // button on pin 2
#define loudspeakerPin 6            // high for loudspeaker on, low for loudspeaker off
#define ledPin 13
#define debounceDelay 50            // 50 millisecond delay on the button debounce
#define longHoldDelay 3000           // hold the button for 3 seconds for a long hold to be activated

int battery = A1;    // select the input pin for the battery
int redPin = 11;     // select the pin for the red LED
int yellowPin = 10;  // select the pin for the yellow LED
int greenPin = 9;    // select the pin for the green LED
int isPowerOn = 8;   // select the pin for checking power

int batteryValue = 0;  // variable to store the value coming from the battery
int powerState = 0;    // variable to store the value coming from the power

int buttonState = 0;  // 0 = rest, 1 = debounce_hold, 2 = short_hold, 3 = short_debounce_time, 4 = long_hold
long startTime;       // The time when button transitions from state 0 to state 1
long debounceStartTime; // the time when button transitions from state 2 to state 3

char loudspeakerChar; // 'h' for high (on), 'l' for low (off)

void setup() {
   Serial.begin(9600);              // setup serial with 9600 baud rate
   
   pinMode(ledPin, OUTPUT);
   pinMode(redPin, OUTPUT); 
   pinMode(yellowPin, OUTPUT);
   pinMode(greenPin, OUTPUT);
   pinMode(isPowerOn, INPUT);
   
   pinMode(loudspeakerPin, OUTPUT);
   digitalWrite(loudspeakerPin, LOW);
   
   pinMode(buttonPin, INPUT);
   digitalWrite(buttonPin, HIGH);   
}

void loop() {
  
  //---------------------------------------------
  // Handle the battery reading
  //---------------------------------------------
   // read the value from the battery:
  batteryValue = analogRead(battery);
  
  // see if the power to the whole circuit is on
  powerState = digitalRead(isPowerOn);
  
  // turn on the right color LED
  // turn on none if the circuit does not have power
  if (powerState == HIGH) {
    if (batteryValue > 928) {
      analogWrite(greenPin, 10);
      analogWrite(yellowPin, 0);
      analogWrite(redPin, 0);       
    } else if (batteryValue > 898) {
      analogWrite(greenPin, 0);
      analogWrite(yellowPin, 10);
      analogWrite(redPin, 0);
    } else {
      analogWrite(greenPin, 0);
      analogWrite(yellowPin, 0);
      analogWrite(redPin, 20);
    }
  } else {
      analogWrite(greenPin, 0);
      analogWrite(yellowPin, 0);
      analogWrite(redPin, 0);
  }
  
  
  
   //---------------------------------------------
   // check for loudspeaker
   //---------------------------------------------
   loudspeakerChar = Serial.read();
   if(loudspeakerChar == 'h') {
     digitalWrite(loudspeakerPin, HIGH);  
     digitalWrite(ledPin, HIGH);  
   } else if(loudspeakerChar == 'l') {
     digitalWrite(loudspeakerPin, LOW);
     digitalWrite(ledPin, LOW);
   }


  //---------------------------------------------
  // Read the button
  //---------------------------------------------
  
  // IMPORTANT: LOW means button is DEPRESSED!!!!
  int buttonValue = digitalRead(buttonPin);
  if (buttonState == 0) {
    // The button is in a rest state
    if (buttonValue == LOW) {
      startTime = millis();
      buttonState = 1;
    }
    
  } else if (buttonState == 1) {
    // The button is in a debounce state
    if (buttonValue == HIGH) {
      buttonState = 0;
    } else if (millis() - startTime > debounceDelay) {
      buttonState = 2;
    }    
    
  } else if (buttonState == 2) {
    // The button has been active for a short amount of time
    if (buttonValue == HIGH) {
      buttonState = 3;
    } else if (millis() - startTime > longHoldDelay) {
      buttonState = 4;
      Serial.println("long");
    }
  } else if (buttonState == 3) {
    if (millis() - startTime > debounceDelay) {
      Serial.println("short");
      buttonState = 0;
    } else if (buttonValue == LOW) {
      buttonState = 2;
    }
    
  } else if (buttonState == 4) {
    // The button has been active for a long amount of time
    if (buttonValue == HIGH) {
      buttonState = 1;
    }
  }
  
}
