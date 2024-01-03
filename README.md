# Arcade-Manager-AMP
My current most complex personal project. This program allows the user to play a simple textbased rpg. I’m stil expanding and refining the gameplay


## How It's Made:

**Concepts used:** Java Swing, Random Class, Inheritance, objects

### Classes

 #### Village
 This abstract class stores all of the basic stats associated with the village (population, hungerlevel, etc).

 Notable Methods:
 
 triggerInvasion():
   This method triggers a cutscene for the final invasion. It then calculates your chance of victory based on the player's stats. It will then trigger a the corresponding failure or victory cutscene.

  farmGeneration():
  This method calculates how much food the player's farm generates based on it's level.

 #### PlayerActions
 This abstract class contains data related to the player and their actions (fishing, gathering materials, etc). It also handles the passing of time. Everytime the player does an action, they use a certain amount of time and after they've used 12
 hours a day passes. 

 Notable Methods:
   turnDay():
   This method changes the day when the player uses 12 hours of time. It triggers various cutscenes for NPC on certain days using a switch statement. The method also calls triggerPossibleEvent(), so
   there's a chance a random event will occur that day.

 goFishing():
   This method sets fishCaught to a random number between 0 and 8. It then prints out how much fish the player caught. It adds this amount to the food stat and increments time used by 4. 

 #### FriendlyStranger
 This class handles all of the mandatory NPCS and their cutscenes. There are 3 NPC and 5 cutscenes. Each interaction unlocks a new mechanic for the player.

-  The first NPC unlocks an axe
-  The 2nd a hunting commitee
-  The 3rd a farming
-  The 4th a milita
  Notable Methods:
   triggerFirstStrangerCutScene1():
   When called this method prints out a small cutscene.
   canRebuild()
   This method checks if you've interacted with the 3rd Stranger. If you have, you are able to begin upgrading the village's farms.

 #### RandomEvents
 This class manages 'specialEvents' that can occur at random to disrupt the player. Each event has a probability, name, and an amount of people, food, and material destroyed.

    Notable Methods:
      announceButtonAnimalAttack()
        This is a static method that announces an animal attack and the amount of destruction caused. It has a chance to occur when the player gather's materials.

       triggerPossibleEvent()
         This static method takes in an Array of RandomEvents. It then generates a random index and gets the event located there. Then it rolls the dice on if the selecte event will occur and also generates
         the damage the event will do. It then prints the relevant information to the player.
 
  #### Natural Events
    This is a child class of RandomEvents focused on NaturalEvents.
      

### MainFisherman

This class handles all of the GUI set up using Java Swing. It sets up 4 buttons and loads an image of a fisherman. Upon launch introduction text will play before launching the image and the buttons.
