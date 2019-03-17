#include <Pixy2.h>
Pixy2 pixy;

void setup() {
  Serial.begin(9600);
  while(!Serial) {}
  Serial.print("Starting...\n");

  pixy.init();
  pixy.setLamp(1,1);
  Serial.println(pixy.changeProg("line_tracking"));
}

void loop() {
  int8_t pixyData;
  char buf[128];
  
  pixyData = pixy.line.getMainFeatures();
  if (pixy.line.numVectors)
    pixy.line.vectors.m_angle->print();
    // pixy.line.vectors[0]->print();
    
}
