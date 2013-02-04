/*
  Timed Button
  
  Informs the user how many seconds a button attached to pin 2 was depressed
  
  The circuit:
  * LED attached from pin 13 to ground (i.e. the LED on board the Arduino Uno)
  * pushbutton attached to pin 2 from +5V
  * 10K resistor attached to pin 2 from ground

*/

#define holdTime 3000
#define minPressTime 20

#define ledPin  13                  // LED connected to digital pin 13
#define buttonPin 2                 // button on pin 2

int value = LOW;                    // previous value of the LED
int buttonState;                    // variable to store button state
int lastButtonState;                // variable to store last button state
long startTime;                     // start time for stop watch
long elapsedTime;                   // elapsed time for stop watch
int fractional;                     // variable used to store fractional part of time
String output;

void setup() {
   Serial.begin(9600);              // setup serial with 9600 baud rate
  
   pinMode(ledPin, OUTPUT);         // sets the digital pin as output
   pinMode(buttonPin, INPUT);       // sets the digital pin as input
}

void loop() {
   // check for button press
   buttonState = digitalRead(buttonPin);                   // read the button state and store
  
   // check for a low to high transistion (button press)
   if (buttonState == HIGH && lastButtonState == LOW) {    
        startTime = millis();                                // store the start time
        digitalWrite(ledPin, HIGH);                          // turn the LED on
        lastButtonState = HIGH;
        
   } else if (buttonState == HIGH && lastButtonState == HIGH) {   
        elapsedTime = millis() - startTime;              // store elapsed time
        if(elapsedTime == holdTime) {
          Serial.println("long");
          delay(2);
        }
        
   } else if (buttonState == LOW && lastButtonState == HIGH) {
       elapsedTime = millis() - startTime;
       digitalWrite(ledPin, LOW);                         // turn the LED off
       lastButtonState = buttonState;                     // store buttonState in lastButtonState, to compare next time
       if((elapsedTime < holdTime) && (elapsedTime > minPressTime)){
          Serial.println("short");                             // print status
       }
   }
}
