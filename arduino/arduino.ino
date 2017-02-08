#include <Adafruit_NeoPixel.h>
#define AMMOUNT 6
#define PIN 13

Adafruit_NeoPixel leds = Adafruit_NeoPixel(AMMOUNT, PIN, NEO_GRB + NEO_KHZ800);
int r = 1;
int g = 1;
int b = 1;
char tab[3 * AMMOUNT];
void setup() {
  leds.begin();
  setall(r, g, b);
  Serial.begin(250000);

 clearBuffer();
  while (true)
  {
    if (Serial.available() > 0)
    {
      clearSerial();
      Serial.write('y');
      break;
    }
  }
}

void loop() {

  if(Serial.available() > 0)
  {
    Serial.readBytes(tab, 3 * AMMOUNT);
    for (int i = 0; i < (3 * AMMOUNT); i += 3)
    {
      leds.setPixelColor(i / 3, tab[i], tab[i + 1], tab[i + 2]);
      leds.show();
    }
   /* for (int i = 0; i < 3 * AMMOUNT; i++)
    {
      Serial.print(tab[i]);
    }
    */
    /*Serial.print('\n');*/
    clearSerial();
   // clearBuffer();
  }
  

}

void setall(int r, int g, int b)
{
  for (int i = 0; i < AMMOUNT; i++)
  {
    leds.setPixelColor(i, r, g, b);
    leds.show();
  }
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


