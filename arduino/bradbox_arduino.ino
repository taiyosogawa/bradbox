#define holdTime 3000
#define minPressTime 20

#define buttonPin 2                 // button on pin 2
#define loudspeakerPin 6            // high for loudspeaker on, low for loudspeaker off
#define ledPin 13

int battery = A1;    // select the input pin for the battery
int redPin = 11;     // select the pin for the red LED
int yellowPin = 10;  // select the pin for the yellow LED
int greenPin = 9;    // select the pin for the green LED
int isPowerOn = 8;   // select the pin for checking power

int batteryValue = 0;  // variable to store the value coming from the battery
int powerState = 0;    // variable to store the value coming from the power

int buttonState;                    // variable to store button state
int lastButtonState;                // variable to store last button state
long startTime;                     // start time for stop watch
long elapsedTime;                   // elapsed time for stop watch
int fractional;                     // variable used to store fractional part of time
String output;
char loudspeakerChar;              // 'h' for high (on), 'l' for low (off)

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
  
   // check for loudspeaker
   loudspeakerChar = Serial.read();
   if(loudspeakerChar == 'h') {
     digitalWrite(loudspeakerPin, HIGH);  
     digitalWrite(ledPin, HIGH);  
   } else if(loudspeakerChar == 'l') {
     digitalWrite(loudspeakerPin, LOW);
     digitalWrite(ledPin, LOW);
   }
   // check for button press
   buttonState = digitalRead(buttonPin);                   // read the button state and store
   
   // check for a low to high transistion (button press)
   if (buttonState == HIGH && lastButtonState == LOW) {    
        startTime = millis();                                // store the start time
        lastButtonState = HIGH;
        
   } else if (buttonState == HIGH && lastButtonState == HIGH) {   
        elapsedTime = millis() - startTime;              // store elapsed time
        if(elapsedTime == holdTime) {
          Serial.println("long");
          delay(2);
        }
        
   } else if (buttonState == LOW && lastButtonState == HIGH) {
       elapsedTime = millis() - startTime;
       lastButtonState = buttonState;                     // store buttonState in lastButtonState, to compare next time
       if((elapsedTime < holdTime) && (elapsedTime > minPressTime)){
          Serial.println("short");                             // print status
       }
   }
}
