import java.util.Random;

abstract public class Village {
    private static int food;
    private static int population;
    private static String hungerLevels;
    private static int materials;
    private static String farmStatus = "broken";
    private static int farmCost;
    private static int militaryCostMaterials;
    private static int militaryCostFood;
    private static int militaryStrength;
    private static boolean invasionHappening;
    private static boolean victory;
    //default stats for Village
    public Village(){
        food = 0;
        population = 25;
        hungerLevels = "starving";
        farmStatus = "broken";
        farmCost = 15;
        militaryStrength = 1;
        militaryCostMaterials = 20;
        militaryCostFood = 12;
        invasionHappening = false;
        victory = false;
    }
// get methods
    public static int getFood() {
        return food;
    }

    public static int getPopulation() {
        return population;
    }

    public static int getMaterials() {
        return materials;
    }
    public static String getHungerLevels() {
        return hungerLevels;
    }

    public static int getFarmCost() {
        return farmCost;
    }

    public static int getMilitaryCostMaterials() {
        return militaryCostMaterials;
    }

    public static int getMilitaryCostFood() {
        return militaryCostFood;
    }

    public static int getMilitaryStrength() {
        return militaryStrength;
    }

    // set methods
    public static void setPopulation(int population) {
        Village.population = population;
    }

    public static void setMaterials(int materials) {
        Village.materials = materials;
    }

    public static void setFood(int food) {
        Village.food = food;
    }

    public static void setHungerLevels(String hungerLevels) {
        Village.hungerLevels = hungerLevels;
    }

    public static void setFarmStatus(String farmStatus) {
        String farmingStat = Village.getFarmStatus();
    }

    public static void setFarmCost(int farmCost) {
        Village.farmCost = farmCost;
    }

    public static void setMilitaryCostMaterials(int militaryCostMaterials) {
        Village.militaryCostMaterials = militaryCostMaterials;
    }

    public static void setMilitaryCostFood(int militaryCostFood) {
        Village.militaryCostFood = militaryCostFood;
    }

    public static void setMilitaryStrength(int militaryStrength) {
        Village.militaryStrength = militaryStrength;
    }
// Other methods
    public static void triggerInvasion() throws InterruptedException{
        // Trigger ending cutscene
        MainFisherman.delayedPrintln("The patrols come back in panic",3000);
        MainFisherman.delayedPrintln("The bandits have returned",3000);
        System.out.println();
        MainFisherman.delayedPrintln("The warriors of the village grab their weapons",3000);
        MainFisherman.delayedPrintln("All of your work, so far will decide what happens next",3000);
        for(int i = 0; i< 3; i++){
            // roll for random events
            MainFisherman.delayedPrintln("In the midst of the battle, you get unlucky",3000);
        RandomEvents.triggerPossibleEvent(RandomEvents.allRandomEvents);
        }
    // calc if win based on stats
        int chanceOfVictory = 8;
        int chanceOfFailure = 90;
        int populationBoost = (int)(getPopulation() *.35);
        if(invasionHappening) {
            chanceOfVictory *= chanceOfVictory * militaryStrength;
            switch(hungerLevels) {
                case "starving":
                    chanceOfVictory *= .55;
                    break;
                case "hungry":
                    chanceOfVictory *= .75;
                    break;
                case "satiated":
                    break;
                case "full":
                    chanceOfVictory *= 1.20;
                    break;
            }
            chanceOfVictory += populationBoost;
            chanceOfFailure = chanceOfFailure - chanceOfVictory;
            boolean doesLose = new Random().nextInt(chanceOfFailure)==0;
            // set victory stat
            victory = !doesLose;
        }
    }
    public static void triggerWinOrLoss() throws InterruptedException{
        // trigger win or loss cut scene
        System.out.println();
        if(victory){
            MainFisherman.delayedPrintln("The battle is hard fought but against all odds the village pulls through",3000);
            MainFisherman.delayedPrintln("You led this tiny fishing village into a bustling hub that demands respect",3000);
            MainFisherman.delayedPrintln("You Win!",5000);
        } else{
            MainFisherman.delayedPrintln("The battle is a disaster the bandits make quick work of your rag-tag warriors",3000);
            MainFisherman.delayedPrintln("The bandits decide to make an example out of you for your defiance.",3000);
            MainFisherman.delayedPrintln("You die quickly if not painlessly",3000);
            MainFisherman.delayedPrintln("You Lose",5000);

        }
        System.exit(0);
    }
    public static String calculateHunger() {
        // set levels of hunger based on ratio of population to food
        double currentFood = getFood();
        double currentPopulation = getPopulation();
        String hunger;

        double foodPopRatio = currentFood / currentPopulation;
        if (foodPopRatio < .26) {
            hunger = "starving";
        } else if (foodPopRatio < .45) {

            hunger = "hungry";
        } else if (foodPopRatio < .64) {
            hunger = "satiated";
        } else {
            hunger = "full";
        }
        setHungerLevels(hunger);
        return hungerLevels;
    }

    public static String getFarmStatus() {
        return farmStatus;

    }
    public static void farmGeneration(){
        // based on status farms generate a food boost
        int foodAmount = food;
        switch(farmStatus) {
            case "broken":
                break;
            case "humble":
                setFood(foodAmount + 10);
                break;
            case "solid":
                setFood(foodAmount + 24);
               break;
            case "booming":
                setFood(foodAmount + 40);
                break;
        }

    }
}
