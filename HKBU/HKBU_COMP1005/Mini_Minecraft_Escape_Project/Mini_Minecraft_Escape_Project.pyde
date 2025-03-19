def setup():
    global stageNum  #control the stages
    global attacker, helper, xAtta, xHelp, yAtta, yHelp, dxAtta, dyAtta, dxHelp, dyHelp, ScaleAtta, ScaleHelp
    global HP, Score, superpower, startTimeSte, startTimeCar, aliveAtta, aliveHelp
    global superpower, HPSte, HPCar, score
    global isUP, isDOWN, isLEFT, isRIGHT
    global speed, bgx, bgy, Size, velocity
    global x, y #Carron's original x and y positions
    size(1000,800)
    x=460 
    y=360 
    score = 0
    stageNum = 0
    superpower = 0
    HPSte = 30
    HPCar = 40
    speed = -2
    bgx = 0
    bgy = 0
    Size = 120
    velocity = 12
    
    isUP = False
    isDOWN = False
    isLEFT = False
    isRIGHT = False
    
    aliveAtta = []
    aliveHelp = []
    attacker = []
    helper = []
    attacker.append(loadImage("Bomb.png"))
    attacker.append(loadImage("Bomb.png"))
    attacker.append(loadImage("Zombie.png"))
    attacker.append(loadImage("Zombie.png"))
    attacker.append(loadImage("Skeleton.png"))
    attacker.append(loadImage("Skeleton.png"))
    attacker.append(loadImage("Bomb.png"))
    
    helper.append(loadImage("Sheep.png"))
    helper.append(loadImage("Villager.png"))
    helper.append(loadImage("Sheep.png"))
    helper.append(loadImage("Diamond.png"))
    
    
    #assign data to Steve page
    xAtta = []
    yAtta = []
    dxAtta = []
    dyAtta = []
    ScaleAtta = []
    
    xHelp = []
    yHelp = []
    dxHelp = []
    dyHelp = []
    ScaleHelp = []
    for i in range(7):
        xAtta.append(random(0,400))
        yAtta.append(random(0,300))
        xHelp.append(random(600,1000))
        yHelp.append(random(500,800))
        dxAtta.append(random(-7,7))
        dyAtta.append(random(-7,7))
        dxHelp.append(random(-25,25))
        dyHelp.append(random(-25,25))
        ScaleAtta.append(random(0.8,2))
        ScaleHelp.append(random(0.5,1))
        aliveAtta.append(True)

    for i in range(4):
        aliveHelp.append(True) 


def draw():
    if stageNum == 0:
        drawWelcome() #Welcome page
        
    elif stageNum == 1:
        drawInstruction() #Instruction page
        
    elif stageNum ==2:
        drawSteve()  #Game play Steve page
        
    elif stageNum ==3:
        drawCarron()  #Game play Carron page
        
    elif stageNum ==4:
        drawGameover() #Game over page
        
    elif stageNum ==5:
        drawGamewin() #Game Win page 
        
 
        
               
    
def drawWelcome():
    bk = loadImage("background.png")  #draw background
    image(bk,0,0,width,height)
    fill(255,0,0)
    textSize(50)    
    text("Welcome to \"MineCraft\"!", 200, 80)
    # text("Game Objective:",20,100)
    # text("Help Steve/Carron survive the night", 20, 150)
    # text("Get 500 scores so they can survive", 20, 200)
    # text("Get Help and Avoid attack", 20, 250)
    text("Press any key for Game Instructions...", 50, 750)





def drawInstruction():
    Earth = loadImage("Earth.jpg")  #draw background
    image(Earth,0,0,width,height)
    #draw attackers
    imgBomb = loadImage("Bomb.png")
    imgZombie = loadImage("Zombie.png")
    imgSkeleton = loadImage("Skeleton.png")
    
    image(imgBomb,0,100,100,100)
    image(imgZombie,0,250,100,100)
    image(imgSkeleton,0,400,100,100)
    
    fill(255,255,255)
    rect(0,0,400,80)
    textSize(50)
    fill(0,0,0)
    text("Attackers",50,50)
    #instructions about attackers
    textSize(30)
    fill(0,125,255)
    text("Bomb: -30HP",100,170)
    text("Zombie: -10HP",100,320)
    text("Skeleton: -20HP",100,470)
    
    #draw helpers
    imgSheep = loadImage("Sheep.png")
    imgVillager = loadImage("Villager.png")
    imgDiamond = loadImage("Diamond.png")
    
    image(imgSheep,500,100,100,100)
    image(imgVillager,500,250,100,100)
    image(imgDiamond,500,400,100,100)
    
    fill(255,255,255)
    rect(500,0,400,80)
    textSize(50)
    fill(0)
    text("Helpers",550,50)
    #instructions about helpers
    textSize(30)
    fill(255,0,0)
    text("Sheep: +10Score",600,170)
    text("Villager: +50Score",600,320)
    text("Diamond: +1super-power",600,470)
    
    #draw Steve and Carron
    textSize(40)
    text("Click Steve or Carron image below to start.",0,550)
    
    imgSteve = loadImage("Steve.png")
    imgCarron = loadImage("Carron.png")
    image(imgSteve,0,600,100,100)
    image(imgCarron,500,600,100,100)
    
    textSize(30)
    fill(255)
    text("Steve: HP=30",100,630)
    text("Carron: HP=40",600,630)
    text("sp:smaller size & +10HP",100,660)
    text("sp:+50Score & move faster",600,660)
    fill(255,0,0)
    text("Mouse control(Easy)",150,690)
    text("key control(Hard)",650,690)
    fill(0,255,0)
    text("Remember to press 's' or 'S' to use super-power (i.e.,sp)",80,750)





def drawSteve():
    global xAtta, yAtta, dxAtta, dyAtta, ScaleAtta
    global xHelp, yHelp, dxHelp, dyHelp, ScaleHelp
    global HPSte, superpower, score, stageNum
    global bgx, bgy
    TimeSte = (millis()-startTimeSte)/1000
    Score = TimeSte + score
    
    imgforest = loadImage("forest.jpg")
    image(imgforest,bgx,bgy,width,height)
    image(imgforest,bgx+width,bgy,width,height)
    bgx = bgx + speed
    if bgx <= -width:
        bgx = 0
    
    
    Steve = loadImage("Steve.png")
    image(Steve,mouseX-40,mouseY-40,Size,Size) #display Steve
    
    textSize(30)
    fill(255,0,0)
    text("HP: " + str(HPSte),0,30) #disaplay HP
    text("Score: " + str(Score),0,70) #display Score
    text("superpower: " + str(superpower),0,110) #display superpower
    text("Time: " + str((millis()-startTimeSte)/1000) + "s",0,150) #display Time
    
    #Attackers
    for i in range(6):
        if aliveAtta[i] == True:
            image(attacker[i],xAtta[i],yAtta[i],60*ScaleAtta[i],60*ScaleAtta[i])
            xAtta[i] = xAtta[i] + dxAtta[i]
            yAtta[i] = yAtta[i] + dyAtta[i]
            
            #Attackers bounce
            if xAtta[i] + 60*ScaleAtta[i] >= 1000:
                xAtta[i] = 1000 - 60*ScaleAtta[i]
                dxAtta[i] = -dxAtta[i]
                
            elif xAtta[i] <=0:
                xAtta[i] = 0
                dxAtta[i] = -dxAtta[i]
                
            if yAtta[i] + 60*ScaleAtta[i] >=800:
                yAtta[i] = 800 - 60*ScaleAtta[i]
                dyAtta[i] = -dyAtta[i]
                
            elif yAtta[i] <= 0:
                yAtta[i] = 0
                dyAtta[i] = -dyAtta[i]
    
    # Helpers
    for i in range(3):
        if aliveHelp[i] == True:
            image(helper[i],xHelp[i],yHelp[i],60*ScaleHelp[i],60*ScaleHelp[i])
            xHelp[i] = xHelp[i] + dxHelp[i]
            yHelp[i] = yHelp[i] + dyHelp[i]
            #Helpers bounce 
            if xHelp[i] + 60*ScaleHelp[i] >= 1000:
                xHelp[i] = 1000 - 60*ScaleHelp[i]
                dxHelp[i] = -dxHelp[i]
                
            elif xHelp[i] <=0:
                xHelp[i] = 0
                dxHelp[i] = -dxHelp[i]
                
            if yHelp[i] + 60*ScaleHelp[i] >=800:
                yHelp[i] = 800 - 60*ScaleHelp[i]
                dyHelp[i] = -dyHelp[i]
                
            elif yHelp[i] <= 0:
                yHelp[i] = 0
                dyHelp[i] = -dyHelp[i]
    
    if TimeSte >=20:
        if aliveHelp[3] == True :
            image(helper[3],xHelp[3],yHelp[3],60*ScaleHelp[3],60*ScaleHelp[3])
            xHelp[3] = xHelp[3] + dxHelp[3]
            yHelp[3] = yHelp[3] + dyHelp[3]
            if xHelp[3] + 60*ScaleHelp[3] >= 1000:
                    xHelp[3] = 1000 - 60*ScaleHelp[3]
                    dxHelp[3] = -dxHelp[3]
            elif xHelp[3] <=0:
                xHelp[3] = 0
                dxHelp[3] = -dxHelp[3]
                    
            if yHelp[3] + 60*ScaleHelp[3] >=800:
                yHelp[3] = 800 - 60*ScaleHelp[3]
                dyHelp[3] = -dyHelp[3]
                    
            elif yHelp[3] <= 0:
                yHelp[3] = 0
                dyHelp[3] = -dyHelp[3]
    # HP system
    if aliveAtta[0] == True and xAtta[0] + 60*ScaleAtta[0] >= mouseX and mouseX + Size >= xAtta[0] and yAtta[0] + 60*ScaleAtta[0] >= mouseY and mouseY +Size >= yAtta[0]:
        aliveAtta[0] = False
        HPSte = HPSte - 30
    if aliveAtta[1] == True and xAtta[1] + 60*ScaleAtta[1] >= mouseX and mouseX + Size >= xAtta[1] and yAtta[1] + 60*ScaleAtta[1] >= mouseY and mouseY +Size >= yAtta[1]:
        aliveAtta[1] = False
        HPSte = HPSte - 30
    if aliveAtta[2] == True and xAtta[2] + 60*ScaleAtta[2] >= mouseX and mouseX + Size >= xAtta[2] and yAtta[2] + 60*ScaleAtta[2] >= mouseY and mouseY +Size >= yAtta[2]:
        aliveAtta[2] = False
        HPSte = HPSte - 10
    if aliveAtta[3] == True and xAtta[3] + 60*ScaleAtta[3] >= mouseX and mouseX + Size >= xAtta[3] and yAtta[3] + 60*ScaleAtta[3] >= mouseY and mouseY +Size >= yAtta[3]:
        aliveAtta[3] = False
        HPSte = HPSte - 10
    if aliveAtta[4] == True and xAtta[4] + 60*ScaleAtta[4] >= mouseX and mouseX + Size >= xAtta[4] and yAtta[4] + 60*ScaleAtta[4] >= mouseY and mouseY +Size >= yAtta[4]:
        aliveAtta[4] = False
        HPSte = HPSte - 20
    if aliveAtta[5] == True and xAtta[5] + 60*ScaleAtta[5] >= mouseX and mouseX + Size >= xAtta[5] and yAtta[5] + 60*ScaleAtta[5] >= mouseY and mouseY +Size >= yAtta[5]:
        aliveAtta[5] = False
        HPSte = HPSte - 20
    
    # Score system
    if aliveHelp[0] == True and xHelp[0] + 60*ScaleHelp[0] >= mouseX and mouseX + Size >= xHelp[0] and yHelp[0] + 60*ScaleHelp[0] >= mouseY and mouseY +Size >= yHelp[0]:
         aliveHelp[0] = False
         score = score + 10
    if aliveHelp[1] == True and xHelp[1] + 60*ScaleHelp[1] >= mouseX and mouseX + Size >= xHelp[1] and yHelp[1] + 60*ScaleHelp[1] >= mouseY and mouseY +Size >= yHelp[1]:
         aliveHelp[1] = False
         score = score + 30
    if aliveHelp[2] == True and xHelp[2] + 60*ScaleHelp[2] >= mouseX and mouseX + Size >= xHelp[2] and yHelp[2] + 60*ScaleHelp[2] >= mouseY and mouseY +Size >= yHelp[2]:
         aliveHelp[2] = False
         score = score + 10
    if TimeSte >= 20 and aliveHelp[3] == True and xHelp[3] + 60*ScaleHelp[3] >= mouseX and mouseX + Size >= xHelp[3] and yHelp[3] + 60*ScaleHelp[3] >= mouseY and mouseY +Size >= yHelp[3]:
         aliveHelp[3] = False
         superpower = superpower + 1

   #Game Win 
    if stageNum == 2:
        if Score >= 200 :
            Score = 200
            stageNum = 5
    #Game Over
    if stageNum == 2:
        if HPSte <= 0 :
            HPSte = 0
            stageNum = 4
        
def drawCarron():
    global xAtta, yAtta, dxAtta, dyAtta, ScaleAtta
    global xHelp, yHelp, dxHelp, dyHelp, ScaleHelp
    global HPCar, superpower, score, x, y, stageNum
    global bgx, bgy
    TimeCar = (millis()-startTimeCar)/1000
    Score = TimeCar + score
    
    imgdesert = loadImage("desert.jpg")
    image(imgdesert,bgx,bgy,width,height)
    image(imgdesert,bgx,bgy+height,width,height)
    bgy = bgy + speed
    if bgy >= -width:
        bgy = 0
        
    Steve = loadImage("Carron.png")
    image(Steve,x,y,60,60) #display Steve
    if (isUP):  # UP
        print("UP")
        y = y - velocity
        
    if (isDOWN): # DOWN
        print("DOWN")
        y = y + velocity
  
    if (isLEFT): # LEFT
        print("LEFT")
        x = x - velocity
  
    if (isRIGHT): # RIGHT
        print("RIGHT")
        x = x + velocity
    
    textSize(30)
    fill(255,0,0)
    text("HP: " + str(HPCar),0,30) #disaplay HP
    text("Score: " + str(Score),0,70) #display Score
    text("superpower: " + str(superpower),0,110) #display superpower
    text("Time: " + str((millis()-startTimeCar)/1000) + "s",0,150) #display Time
    
    for i in range(7):
        if aliveAtta[i] == True:
            image(attacker[i],xAtta[i],yAtta[i],60*ScaleAtta[i],60*ScaleAtta[i])
            xAtta[i] = xAtta[i] + dxAtta[i]
            yAtta[i] = yAtta[i] + dyAtta[i]
            
            #Attackers bounce
            if xAtta[i] + 60*ScaleAtta[i] >= 1000:
                xAtta[i] = 1000 - 60*ScaleAtta[i]
                dxAtta[i] = -dxAtta[i]
                
            elif xAtta[i] <=0:
                xAtta[i] = 0
                dxAtta[i] = -dxAtta[i]
                
            if yAtta[i] + 60*ScaleAtta[i] >=800:
                yAtta[i] = 800 - 60*ScaleAtta[i]
                dyAtta[i] = -dyAtta[i]
                
            elif yAtta[i] <= 0:
                yAtta[i] = 0
                dyAtta[i] = -dyAtta[i]
            
    for i in range(3):
        if aliveHelp[i] == True:
            image(helper[i],xHelp[i],yHelp[i],60*ScaleHelp[i],60*ScaleHelp[i])
            xHelp[i] = xHelp[i] + dxHelp[i]
            yHelp[i] = yHelp[i] + dyHelp[i]
            #Helpers bounce 
            if xHelp[i] + 60*ScaleHelp[i] >= 1000:
                xHelp[i] = 1000 - 60*ScaleHelp[i]
                dxHelp[i] = -dxHelp[i]
                
            elif xHelp[i] <=0:
                xHelp[i] = 0
                dxHelp[i] = -dxHelp[i]
                
            if yHelp[i] + 60*ScaleHelp[i] >=800:
                yHelp[i] = 800 - 60*ScaleHelp[i]
                dyHelp[i] = -dyHelp[i]
                
            elif yHelp[i] <= 0:
                yHelp[i] = 0
                dyHelp[i] = -dyHelp[i]
    
    if aliveHelp[3] == True :
        if TimeCar >=30 :
            image(helper[3],xHelp[3],yHelp[3],60*ScaleHelp[3],60*ScaleHelp[3])
            xHelp[3] = xHelp[3] + dxHelp[3]
            yHelp[3] = yHelp[3] + dyHelp[3]
            if xHelp[3] + 60*ScaleHelp[3] >= 1000:
                    xHelp[3] = 1000 - 60*ScaleHelp[3]
                    dxHelp[3] = -dxHelp[3]
            elif xHelp[3] <=0:
                xHelp[3] = 0
                dxHelp[3] = -dxHelp[3]
                    
            if yHelp[3] + 60*ScaleHelp[3] >=800:
                yHelp[3] = 800 - 60*ScaleHelp[3]
                dyHelp[3] = -dyHelp[3]
                    
            elif yHelp[3] <= 0:
                yHelp[3] = 0
                dyHelp[3] = -dyHelp[3]

    # HP system
    if aliveAtta[0] == True and xAtta[0] + 60*ScaleAtta[0] >= x and x + 60 >= xAtta[0] and yAtta[0] + 60*ScaleAtta[0] >= y and y +60 >= yAtta[0]:
        aliveAtta[0] = False
        HPCar = HPCar - 30
    if aliveAtta[1] == True and xAtta[1] + 60*ScaleAtta[1] >= x and x + 60 >= xAtta[1] and yAtta[1] + 60*ScaleAtta[1] >= y and y +60 >= yAtta[1]:
        aliveAtta[1] = False
        HPCar = HPCar - 30
    if aliveAtta[2] == True and xAtta[2] + 60*ScaleAtta[2] >= x and x + 60 >= xAtta[2] and yAtta[2] + 60*ScaleAtta[2] >= y and y +60 >= yAtta[2]:
        aliveAtta[2] = False
        HPCar = HPCar - 10
    if aliveAtta[3] == True and xAtta[3] + 60*ScaleAtta[3] >= x and x + 60 >= xAtta[3] and yAtta[3] + 60*ScaleAtta[3] >= y and y +60 >= yAtta[3]:
        aliveAtta[3] = False
        HPCar = HPCar - 10
    if aliveAtta[4] == True and xAtta[4] + 60*ScaleAtta[4] >= x and x + 60 >= xAtta[4] and yAtta[4] + 60*ScaleAtta[4] >= y and y +60 >= yAtta[4]:
        aliveAtta[4] = False
        HPCar = HPCar - 20
    if aliveAtta[5] == True and xAtta[5] + 60*ScaleAtta[5] >= x and x + 60 >= xAtta[5] and yAtta[5] + 60*ScaleAtta[5] >= y and y +60 >= yAtta[5]:
        aliveAtta[5] = False
        HPCar = HPCar - 20
    if aliveAtta[6] == True and xAtta[6] + 60*ScaleAtta[6] >= x and x + 60 >= xAtta[6] and yAtta[6] + 60*ScaleAtta[6] >= y and y +60 >= yAtta[6]:
        aliveAtta[6] = False
        HPCar = HPCar - 30
    # Score system
    if aliveHelp[0] == True and xHelp[0] + 60*ScaleHelp[0] >= x and x + 60 >= xHelp[0] and yHelp[0] + 60*ScaleHelp[0] >= y and y +60 >= yHelp[0]:
         aliveHelp[0] = False
         score = score + 10
    if aliveHelp[1] == True and xHelp[1] + 60*ScaleHelp[1] >= x and x + 60 >= xHelp[1] and yHelp[1] + 60*ScaleHelp[1] >= y and y +60 >= yHelp[1]:
         aliveHelp[1] = False
         score = score + 30
    if aliveHelp[2] == True and xHelp[2] + 60*ScaleHelp[2] >= x and x + 60 >= xHelp[2] and yHelp[2] + 60*ScaleHelp[2] >= y and y +60 >= yHelp[2]:
         aliveHelp[2] = False
         score = score + 10
    if TimeCar >= 30 and aliveHelp[3] == True and xHelp[3] + 60*ScaleHelp[3] >= x and x + 60 >= xHelp[3] and yHelp[3] + 60*ScaleHelp[3] >= y and y +60 >= yHelp[3]:
         aliveHelp[3] = False
         superpower = superpower + 1

   #Game Win 
    if stageNum == 3:
        if Score >= 100 :
            Score = 200
            stageNum = 5
    #Game Over
    if stageNum == 3:
        if HPCar <= 0 :
            HPCar = 0
            stageNum = 4
        



def keyPressed():
    global stageNum
    global superpower, HPSte
    global x, y, HPCar, score
    global isUP, isDOWN, isLEFT, isRIGHT, Size, velocity
    if stageNum == 0 :  #at Welcome Page
        stageNum = 1  #move to Instructions if any key is pressed 
    
    if stageNum == 2 : 
        if key == 's' or key == 'S' :
            if superpower == 1:
                superpower = 0
                HPSte = HPSte + 10
                Size = 80

    if stageNum == 3 :
        if key == 's' or key == 'S' :
            if superpower == 1:
                score = score + 50
                velocity = 20
                superpower = 0
        
        if (keyCode == UP):    
            isUP=True
        if (keyCode == DOWN):
            isDOWN=True
        if (keyCode == LEFT):
            isLEFT=True
        if (keyCode == RIGHT):
            isRIGHT=True
        
        if x >= 940:
            x = 940
        elif x<= 0:
            x = 0
        elif y >= 740:
            y = 740
        elif y <= 0:
            y = 0
def drawGameover():
    imgGameover = loadImage("Gameover.jpg")
    image(imgGameover,0,0,width,height)
    textSize(80)
    text("Game Over!", 300,200)
    textSize(50)
    text("He cannot survive the long night...",100,250)
    text("You can choose to replay or exit.",100,320)
    #display buttons
    Restart = loadImage("Restart.png")
    Exit = loadImage("Exit.png")
    image(Restart,200,400,200,200)
    image(Exit,600,400,200,200)
    
def drawGamewin():
    imgGamewinr = loadImage("Gamewin.jpg")
    image(imgGamewinr,0,0,width,height)
    textSize(80)
    text("Congratulations!", 200,100)
    text("YOU WIN!", 300,200)
    textSize(50)
    text("He successfully waits till sunrise !",100,250)
    text("You can choose to replay or exit.",100,320)
    #display buttons
    Restart = loadImage("Restart.png")
    Exit = loadImage("Exit.png")
    image(Restart,200,400,200,200)
    image(Exit,600,400,200,200)
    
def mousePressed():
    global stageNum, startTimeSte, startTimeCar
    
    if stageNum == 1:  #at Game Instruction page
        if mouseX >=0 and mouseX <=100 and \
           mouseY >=600 and mouseY <=700:
               stageNum = 2
               startTimeSte = millis()
               
    if stageNum == 1:  #at Game Instruction page
        if mouseX >=500 and mouseX <=600 and \
           mouseY >=600 and mouseY <=700:
               stageNum = 3
               startTimeCar = millis()
               
    if stageNum == 4:
        if mouseX >=200 and mouseX <=400 and \
           mouseY >=400 and mouseY <=600:
               setup()
        elif mouseX >=600 and mouseX <=800 and \
           mouseY >=400 and mouseY <=600:
               exit()
               
    if stageNum == 5:
        if mouseX >=200 and mouseX <=400 and \
           mouseY >=400 and mouseY <=600:
               setup()
        elif mouseX >=600 and mouseX <=800 and \
           mouseY >=400 and mouseY <=600:
               exit()
               

def keyReleased():
    global isUP, isDOWN, isLEFT, isRIGHT
    if (keyCode == UP):
        isUP=False
    if (keyCode == DOWN):
        isDOWN=False
    if (keyCode == LEFT):
        isLEFT=False
    if (keyCode == RIGHT):
        isRIGHT=False
