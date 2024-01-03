import java.util.Random;
import java.util.Scanner;

abstract public class PlayerActions {
    private static int day;
    private static int hours;

    public static int getDay() {
        return day;
    }

    public static int getHours() {
        return hours;
    }

    public static void setDay(int newDays) {
        day = newDays;
    }

    public static void setHours(int newHours) {
        hours = newHours;
    }
public static void turnDay() throws InterruptedException {
        //Methods changes day and handles most time based events

       if(getHours() >= 12){
           // When the player has used 12 hours worth of actions increment day and reset hours to 0
           setHours(0);
           setDay(getDay()+1);
           System.out.println();
           // let player know the day changed
           MainFisherman.delayedPrintln("It's a new day",3000);

           // Go through our events and roll to trigger one
           RandomEvents.triggerPossibleEvent(RandomEvents.allRandomEvents);

           // Give boost to food based on status of farming
           Village.farmGeneration();

           // Every 2 days tell the players what their stats are looking like
           if((getDay()%4) == 0){
               System.out.println();
               int materials = Village.getMaterials();
               int food = Village.getFood();
               System.out.println();
               MainFisherman.delayedPrintln("So far, you have " + materials + " pieces of materials in total" ,3000);
               MainFisherman.delayedPrintln("You also have " + food + " pieces of food in total" ,3000);
           }
           // Trigger the final day on the 25th day
           if(getDay()>25){
               Village.triggerInvasion();
               Village.triggerWinOrLoss();
           }
           // Trigger an NPC cutscene on the appropriate days
           switch (getDay()) {
               case 2:
                   FriendlyStranger.triggerFirstStrangerCutScene1();
                   break;
               case 3:
                   FriendlyStranger.triggerFirstStrangerCutScene2();
                   break;
               case 7:
                   FriendlyStranger.triggerStranger2();
                   break;
               case 5:
                   FriendlyStranger.triggerStranger3();
                   break;
               case 10:
                   FriendlyStranger.triggerStranger2again();
                   break;
               default:
                   break;
           }

       }

}
    public static void growPopulation(){
        // increase village population 1 in 60 times
        boolean growPopulation = new Random().nextInt(60)==0;
        if(growPopulation){
            Village.setPopulation(Village.getPopulation()+5);
        }
    }
    public static void goFishing() throws InterruptedException {
        // Calc random number of fish for player to account Print statement for player
        System.out.println();
        int caughtFish = (int) (Math.random() * 8);
        Village.setFood(caughtFish);
        MainFisherman.delayedPrintln("You caught " + caughtFish + " fish.",3000);
        // roll for population increase
        growPopulation();
        //adjust hours
        setHours(getHours()+4);

        //adjust hunger stat
        Village.calculateHunger();
        turnDay();

    }
    public static void sendGroupHunting() throws InterruptedException {
        // Method for when you stop hunting alone
        //the same as goFishing() but with a 1.5 times boost and different text
        System.out.println();
        int caughtFood = (int) (1.5*(Math.random() * 8));
        Village.setFood(caughtFood);
        MainFisherman.delayedPrintln("The patrol caught " + caughtFood + " pieces of food.",3000);
        //hunger levels
        setHours(getHours()+4);
        Village.calculateHunger();
        turnDay();
    }
    public static void getMaterialsAlone() throws InterruptedException {
        // generate how much is gathered
        int gatheredMaterials = (int) ((Math.random()) * 10);
        System.out.println();
        if (FriendlyStranger.isHaveAxe()) {
            // player gets 2 times as much material if using axe
            gatheredMaterials *=2;
            Village.setMaterials(gatheredMaterials);
            MainFisherman.delayedPrintln("With your axe, you gathered " + gatheredMaterials + " materials", 3000);

        } else {
         Village.setMaterials(gatheredMaterials);
        MainFisherman.delayedPrintln("You gathered " + gatheredMaterials + " materials", 3000);
        }
        //adjust time
        setHours(getHours() + 6);
        turnDay();
    }

    public static void sendGroupForMaterials() throws InterruptedException {
        // generate how much is gathered
        int pop = Village.getPopulation();
        pop = (int) ((pop*.25) + 1);
        int gatheredMaterials = (int) (Math.random() * 10);
        System.out.println();
            // player gets boost based on population
            gatheredMaterials = 1+ gatheredMaterials * pop;
            Village.setMaterials(gatheredMaterials);
            MainFisherman.delayedPrintln("A party goes out and gathers " + gatheredMaterials + " materials", 3000);
        setHours(getHours() + 4);
        turnDay();

    }
    public static void cantAffordRebuild() throws InterruptedException {
        // Displays text explaining that a player can't afford an upgrade
        System.out.println();
        int cost = Village.getFarmCost();
        int material = Village.getMaterials();
        MainFisherman.delayedPrintln("Sorry, you can't afford that.",3000);
        System.out.println();
        // Tells them how much more they need to collect
        MainFisherman.delayedPrintln("The next upgrade requires " + cost + " materials",3000);
        MainFisherman.delayedPrintln("You only have " + material,3000);
    }
    public static void cantAffordArmy() throws InterruptedException {
        // Displays text explaining that a player can't afford an upgrade
        System.out.println();
        int foodCost = Village.getMilitaryCostFood();
        int materialCost = Village.getMilitaryCostMaterials();

        int food = Village.getFood();
        int material = Village.getMaterials();
        MainFisherman.delayedPrintln("Sorry, you can't afford that.",3000);
        System.out.println();
        // Tells them how much more they need to collect
        MainFisherman.delayedPrintln("The next upgrade requires " + materialCost + " materials and " + foodCost + " pieces of food.",3000);
        MainFisherman.delayedPrintln("You only have " + material + " materials and " + food + "pieces of food",3000);
    }

    public static void rebuildFarm(Scanner input) throws InterruptedException {
        System.out.println();
     boolean isDone = false;
     String farmingStat = Village.getFarmStatus();
     int material = Village.getMaterials();
     int cost = Village.getFarmCost();
     int finalCost = (material - cost);
         System.out.println();
         MainFisherman.delayedPrintln("You walk over to speak to the farmer about possible repairs",3500);
         switch(farmingStat) {
             // matches you to upgrade based on farming status
                // either lets you purchase or fail to purchase upgrade
                    // Changes farm status accordingly
             case "broken":
                 if(material < cost){
                     cantAffordRebuild();
                     break;
                 } else {
                     Village.setFarmStatus("humble");
                     Village.setFarmCost(50);
                     Village.setMaterials(finalCost);
                     MainFisherman.delayedPrintln("Upgrade Complete!",3000);
                     MainFisherman.delayedPrintln("You should now now notice the villages farm generating food on their own",3000);
                break;
                 }
             case "humble":
                 if(material < cost){
                     cantAffordRebuild();
                     break;
                 } else{
                     Village.setFarmStatus("solid");
                     Village.setFarmCost(30);
                     Village.setMaterials(finalCost);
                     break;
                 }
             case "solid":
                 if(material < cost){
                     cantAffordRebuild();
                     break;

                 } else{
                     Village.setFarmStatus("booming");
                     break;
                 }
            }

            }

     public static void rebuildMilitary() throws InterruptedException {
        System.out.println();
        // collect relevant info about stats
        int militiaStrength = Village.getMilitaryStrength();
        int foodCost = Village.getMilitaryCostFood();
        int materialCost = Village.getMilitaryCostMaterials();
        int foodAmount = Village.getFood();
        int materialAmount = Village.getMaterials();

        //check if player has enough supplies for upgrade
        if(foodCost <= foodAmount && materialCost <= materialAmount){
            militiaStrength += 3;

            // increase the cost and strength of military
            Village.setMilitaryCostFood(foodCost*2);
            Village.setMilitaryCostMaterials(materialCost*2);
            Village.setMilitaryStrength(militiaStrength);

            // subtract what player spent from their supplies
            int finalFood = foodAmount - foodCost;
            int finalMaterial = materialAmount - materialCost;

            Village.setFood(finalFood);
            Village.setMaterials(finalMaterial);

            // Display text
            MainFisherman.delayedPrintln("Upgrade Complete!",3000);
            MainFisherman.delayedPrintln("Your army grows stronger",3000);
        }
        else
            //text for if player can't afford upgrade
         cantAffordArmy();
        }
    }

