import math 
import matplotlib.pyplot as plt
import numpy as np


### m's used throughout (m/s, m/s^2) + kilograms
### accel = force/mass
angle = int(input("Type in angle of Launch here: ")) 
### TBD ###
LaunchAngle = angle*math.pi/180 # radian
###     ###

RPM = 0

BRadius = 0.12
BCircumference = BRadius*2*math.pi
distance = 5.34 # meter
TargetHeight  = 2.64 # meter
TargetStart = distance-0.61 # meter
TargetEnd = TargetStart+1.22 # meter
SecondHeight = 1.04 # meter
SecondStart = distance+0.77 # meter
SecondEnd = distance-0.77 # meter
HeightOfRelease = 0 # meter
Time = 1 # seconds
BallMass = 0.27 #kg
BallWeight = BallMass*9.8
print(BallWeight)


BDragCoef = 0.57 # unitless, air's coefficient
BAreaExposed = BRadius**2 * math.pi # meter^2
AirDensity = 1.225 # kg/m^3
Velocity = 0 # meter/second
v = Velocity
smoothness = 0.1
DragAccel = (0.5*BDragCoef*BAreaExposed*AirDensity*(v**2))/0.27




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
plt.plot([SecRange[0],SecRange[0]],[SecHeight[0],0],'g')
plt.plot([SecRange[1],SecRange[1]],[SecHeight[1],0],'g')
plt.plot([HubRange[0],HubRange[0]],[SecHeight[0],HubHeight[0]],'r')
plt.plot([HubRange[1],HubRange[1]],[SecHeight[1],HubHeight[1]],'r')
plt.plot(0,10)



for v in lToTest:
  Times = [smoothness*x for x in range(0,80)]
  Xpos = 0
  Ypos = HeightOfRelease
  cacheX = [0]
  cacheY = [HeightOfRelease]
  Xvel = v*math.cos(LaunchAngle)
  Yvel = v*math.sin(LaunchAngle)

  for time in Times:
    
    Yvel = v*math.sin(LaunchAngle) - 9.8*time


    if Yvel > 0:
      Yvel = Yvel - smoothness*(0.5*BDragCoef*BAreaExposed*AirDensity*(Yvel*Yvel))/0.27
    if Yvel < 0:
      Yvel = Yvel + smoothness*(0.5*BDragCoef*BAreaExposed*AirDensity*(Yvel*Yvel))/0.27

    Ypos += Yvel*smoothness

    cacheY.append(Ypos)


    Xvel = v*math.cos(LaunchAngle) 

    if Xvel > 0:
      Xvel = Xvel - smoothness*(0.5*BDragCoef*BAreaExposed*AirDensity*(Xvel*Xvel))/0.27
    if Xvel < 0:
      Xvel = Xvel + smoothness*(0.5*BDragCoef*BAreaExposed*AirDensity*(Xvel*Xvel))/0.27

    Xpos += Xvel*smoothness

    cacheX.append(Xpos)
  cacheY = [x for x in cacheY if x > -10]
  plt.plot(cacheX[:len(cacheY)],cacheY)

  
    

  
  
