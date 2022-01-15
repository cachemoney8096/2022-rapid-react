import math 
import matplotlib.pyplot as plt
import numpy as np

### m's used throughout (m/s, m/s^2) + kilograms
### accel = force/mass
angle = int(input("Type in angle of Launch here: ")) 
### TBD ###
LaunchAngle = angle*math.pi/180 # radian
###     ###

SpeedToRPMConversion = 1/75

distance = 5.34 # meter
TargetHeight  = 2.64 # meter
TargetStart = distance-0.61 # meter
TargetEnd = TargetStart+1.22 # meter
SecondHeight = 1.04 # meter
SecondStart = distance+0.77 # meter
SecondEnd = distance-0.77 # meter
HeightOfRelease = 0 # meter
Time = 1 # seconds
BallWeight = 0.27 #kg


BDragCoef = 0.47 # unitless, air's coefficient
BAreaExposed = 0.04523893 # meter^2
AirDensity = 1.225 # kg/m^3
Velocity = 0 # meter/second
DragAccel = (0.5*BDragCoef*BAreaExposed*AirDensity*(Velocity**2))/0.27




### INIT ###
lToTest = []
inp = input("Type in value here: (or enter to run sim) ")
while inp:
  lToTest.append(float(inp))
  inp = input("Type in value here: (or enter to run sim) ")

HubRange = np.array([TargetStart,TargetEnd])
HubHeight = np.array([TargetHeight,TargetHeight])
SecRange = np.array([SecondStart,SecondEnd])
SecHeight = np.array([SecondHeight,SecondHeight])
plt.plot(0,0,'o')
plt.plot([0,10],[0,0])
plt.plot(HubRange,HubHeight,'r')
plt.plot(SecRange,SecHeight,'g')
plt.plot(0,10)


for v in lToTest:
  
  startingV = v
  time = np.array([.1*x for x in list(range(0,30))])


  yVels = np.array(-9.8*(time**2)+(v*(1-(0.5*BDragCoef*BAreaExposed*AirDensity*(Velocity)/0.27))*math.sin(LaunchAngle))*time)
  yPos = [0]
  for vel in range(len(yVels)-1):
    yPos.append(yPos[-1]+(yVels[vel]/2+yVels[vel+1]/20))
  xPos = [0]
  xVels = np.array((v*(1-(0.5*BDragCoef*BAreaExposed*AirDensity*(Velocity)/0.27))*math.cos(LaunchAngle))*time)
  for vel in range(len(xVels)-1):
    xPos.append(xPos[-1]+(xVels[vel]/2+xVels[vel+1]/20))
  yPos = [x for x in yPos if x >=-5]
  plt.plot(xPos[0:len(yPos)],yPos)

plt.show
