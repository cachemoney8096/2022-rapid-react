import math 
import matplotlib.pyplot as plt
import numpy as np

### cm's used throughout (cm/s, cm/s^2) + grams
### accel = force/mass
angle = int(input("Type in angle of Launch here: "))
### TBD ###
LaunchAngle = angle*math.pi/180
###     ###
AngleFromVert = 90-LaunchAngle

distance = 534
TargetHeight  = 264
TargetStart = distance-61
TargetEnd = TargetStart+122
SecondHeight = 104
SecondStart = distance+77
SecondEnd = distance-77
Time = 1


BDragCoef = 0.47
BAreaExposed = 904.77
AirDensity = 0.0012929
Velocity = 0
#DragForce = 0.5*BDragCoef*BAreaExposed*AirDensity*(Velocity**2)


### INIT ###
lToTest = []
inp = input("Type in value here: (or enter to run sim) ")
while inp!= "":
  lToTest.append(int(inp))
  inp = input("Type in value here: (or enter to run sim) ")


HubRange = np.array([TargetStart,TargetEnd])
HubHeight = np.array([TargetHeight,TargetHeight])
SecRange = np.array([SecondStart,SecondEnd])
SecHeight = np.array([SecondHeight,SecondHeight])
plt.plot(0,0,'o')
plt.plot(HubRange,HubHeight,'r')
plt.plot(SecRange,SecHeight,'g')
plt.plot(0,400)


for v in lToTest:
  DragForce = 0.5*BDragCoef*BAreaExposed*AirDensity*(v**2)
  DragAccel = DragForce/270
  
  startingV = v
  time = np.array([.3*x for x in list(range(0,40))])
  VTArray = [v]
  for i in range(1,len(time)):
    curDragForce = 0.5*BDragCoef*BAreaExposed*AirDensity*(VTArray[i-1]**2)
    curDragAccel = curDragForce/270
    VTArray.append(VTArray[i-1]-curDragAccel)

  VTArray = np.array(VTArray)


  yVels = np.array(-9.8*(time**2)+(VTArray*math.sin(LaunchAngle))*time)
  yPos = [0]
  for vel in range(len(yVels)-1):
    yPos.append(yPos[-1]+(yVels[vel]/2+yVels[vel+1]/2))
  xPos = [0]
  xVels = np.array((VTArray*math.cos(LaunchAngle))*time)
  for vel in range(len(xVels)-1):
    xPos.append(xPos[-1]+(xVels[vel]/2+xVels[vel+1]/2))
  yPos = [x for x in yPos if x >=-100]
  plt.plot(xPos[0:len(yPos)],yPos)
  plt.plot([0,700],[0,0])

plt.show