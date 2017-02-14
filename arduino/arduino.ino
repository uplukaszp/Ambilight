#include <Adafruit_NeoPixel.h>
#include <EEPROM.h>

#define AMMOUNT 36
#define PIN 12
#define UPDATE 0
#define SAVE 1
#define START_ADDRESS 1


Adafruit_NeoPixel leds = Adafruit_NeoPixel(AMMOUNT, PIN, NEO_GRB + NEO_KHZ800);
byte data[3 * AMMOUNT+1];


void setup() {
  leds.begin();
  readFromEEPROM();
  updateColors();
  Serial.begin(250000);
  
  pinMode(13,OUTPUT);
  clearBuffer();
}

void loop() {

  if(Serial.available())
  {

    Serial.readBytes(data, (3 * AMMOUNT)+1);
    if(data[3*AMMOUNT]==SAVE)
    {
      saveInEEPROM();    
    }
    if(data[3*AMMOUNT]==UPDATE)
    {
      updateColors();
    }
   
  }
  

}
void updateColors()
{
   for (int i = 0; i < (3 * AMMOUNT); i += 3)
    {
      leds.setPixelColor(i / 3, data[i], data[i + 1], data[i + 2]);
      
    }
  
    leds.show();
}
void saveInEEPROM()
{
    for (int i = 0; i < (3 * AMMOUNT); i ++)
    {
        EEPROM.update(START_ADDRESS+i,data[i]);
    }
}
void readFromEEPROM()
{
  for (int i = 0; i < (3 * AMMOUNT); i ++)
    {
       data[i]=EEPROM.read(START_ADDRESS+i);
    }
}
void clearBuffer()
{
   for (int i = 0; i < 3 * AMMOUNT; i++)
  {
    data[i] = 0;
  }
}
void clearSerial()
{
  while(Serial.available()>0)
  {
    Serial.read();
  }
}


