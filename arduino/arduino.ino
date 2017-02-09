#include <Adafruit_NeoPixel.h>
#define AMMOUNT 36
#define PIN 12

Adafruit_NeoPixel leds = Adafruit_NeoPixel(AMMOUNT, PIN, NEO_GRB + NEO_KHZ800);
int r = 255;
int g = 255;
int b = 255;
byte tab[3 * AMMOUNT];
void setup() {
  leds.begin();
  setall(r, g, b);
  Serial.begin(250000);
  pinMode(13,OUTPUT);
  clearBuffer();
}

void loop() {

  if(Serial.available())
  {
         

    Serial.readBytes(tab, 3 * AMMOUNT);
    for (int i = 0; i < (3 * AMMOUNT); i += 3)
    {
      leds.setPixelColor(i / 3, tab[i], tab[i + 1], tab[i + 2]);
      
    }
  
    leds.show();
  }
  

}

void setall(int r, int g, int b)
{
  for (int i = 0; i < AMMOUNT; i++)
  {
    leds.setPixelColor(i, r, g, b);
  }
   leds.show();
}
void clearBuffer()
{
   for (int i = 0; i < 3 * AMMOUNT; i++)
  {
    tab[i] = 0;
  }
}
void clearSerial()
{
  while(Serial.available()>0)
  {
    Serial.read();
  }
}


