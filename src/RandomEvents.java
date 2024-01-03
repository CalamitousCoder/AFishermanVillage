import java.lang.reflect.Array;
import java.util.ArrayList;

public class RandomEvents {
    // default constructor
   private double probability;
    private int peopleKilled;
    private int materialsDestroyed;
    private int foodDestroyed;

    private int hoursTaken;
    private String name;
    static ArrayList<RandomEvents> allRandomEvents = new ArrayList<RandomEvents>();

    // default constructor
    protected RandomEvents(){
        allRandomEvents.add(this);
        probability = 0;
        peopleKilled = 0;
        materialsDestroyed = 0;
        foodDestroyed = 0;

        hoursTaken = 2;
        String name = "Unknown incident";
    }

    // basic constructor (it uses this)
    public RandomEvents(double probability, int hoursTaken, String name){
        allRandomEvents.add(this);
        this.probability = probability;
        this.hoursTaken = hoursTaken;
        this.name = name;
    }
    // fully parametrized
    public RandomEvents(double probability, int peopleKilled, int materialsDestroyed, int hoursTaken, String name){
        allRandomEvents.add(this);
        this.probability = probability;
        this.peopleKilled = peopleKilled;
        this.materialsDestroyed = materialsDestroyed;
        this.hoursTaken = hoursTaken;
        this.name = name;

    }
// get method

    public void setName(String name) {
        this.name = name;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public void setHoursTaken(int hoursTaken) {
        this.hoursTaken = hoursTaken;
    }

    public void setMaterialsDestroyed(int materialsDestroyed) {
        this.materialsDestroyed = materialsDestroyed;
    }

    public void setPeopleKilled(int peopleKilled) {
        this.peopleKilled = peopleKilled;
    }

    public void setFoodDestroyed(int foodDestroyed) {
        this.foodDestroyed = foodDestroyed;
    }
    // get methods

    public String getName() {
        return name;
    }

    public double getProbability() {
        return probability;
    }

    public int getHoursTaken() {
        return hoursTaken;
    }

    public int getMaterialsDestroyed() {
        return materialsDestroyed;
    }

    public int getPeopleKilled() {
        return peopleKilled;
    }

    public int getFoodDestroyed() {
        return foodDestroyed;
    }

    // print method
    public void announceEvent(String event) throws InterruptedException{
        // let the player know what has happened and the results of the event
        System.out.println();
        MainFisherman.delayedPrintln(event,3400);
        System.out.println();

        MainFisherman.delayedPrintln(peopleKilled + " people died. ",3000);
        MainFisherman.delayedPrintln("You lost " + foodDestroyed + " pieces of food.",2300);
        MainFisherman.delayedPrintln("You lost " + foodDestroyed + " materials.",2300);

        System.out.println();

        MainFisherman.delayedPrintln("It took " + hoursTaken + " hours to resolve",3400);
        System.out.println();
    }
    // method that calls a method
    public void generateProbability() {
        int currentPopulation = Village.getPopulation();
        if (currentPopulation < 10) {
            setProbability(.20);
            // code block
        } else if (currentPopulation >= 10 && currentPopulation < 30) {
            setProbability(.5);
        } else {
            setProbability(.75);
        }
    }
    //- A method that uses this.
        public void generatePeopleKilled(){
            int currentPopulation = Village.getPopulation();
            int peopleKilled;

            if (currentPopulation < 10) {
                peopleKilled = (int) (currentPopulation * .25);
            } else if (currentPopulation >= 10 && currentPopulation < 30){

                // mix this up eventually
                peopleKilled = (int) (currentPopulation * .25);
            }
            else {
                peopleKilled = (int) (currentPopulation * .33);

                // use of this
                this.peopleKilled = peopleKilled;
         }
    }

    // additional useful methods
    public static void triggerPossibleEvent(ArrayList allRandomEvents) throws InterruptedException {
        // pick a random event from an array
        RandomEvents selectedEvent;
        int max = allRandomEvents.size();
        int min = 0;
        int genIndex = (int) ((Math.random() * (max - min)) + min);

        // roll dice on if it happens
        double genProbability;
        double randomNum = Math.random();

        selectedEvent = (RandomEvents) allRandomEvents.get(genIndex);
        genProbability = selectedEvent.getProbability();

        if(randomNum <= genProbability){
            selectedEvent.generatePeopleKilled();
            selectedEvent.generateFoodDestroyed();
            selectedEvent.generateHoursTaken();
            selectedEvent.generateMaterialDestroyed();

            selectedEvent.announceEvent(selectedEvent.name);

        } else{

            }
        }

    public static void announceButtonAnimalAttack(String context) throws InterruptedException {
        // Generates animal attacks that function with button code
            //static since it's outside of main
        RandomEvents buttonEvent = new RandomEvents();
        buttonEvent.generateFoodDestroyed();
        buttonEvent.generateHoursTaken();
        buttonEvent.setPeopleKilled(0);

        buttonEvent.announceEvent(context+ " were attacked while out ");
    }
    public void generateFoodDestroyed(){
        //decide how much food is destroyed by event based on player progress
            // meant to help make game feel fair
        String currentHunger = Village.getHungerLevels();
        int currentFood = Village.getFood();
        int foodLost;

        switch(currentHunger) {
            case "starving":
                foodLost = (int)(currentFood * .10);
                setFoodDestroyed(foodLost);
                break;
            case "hungry":
                foodLost = (int)(currentFood * .20);
                setFoodDestroyed(foodLost);
                break;
            case "satiated":
                foodLost = (int)(currentFood * .30);
                setFoodDestroyed(foodLost);
                break;
            case "full":
                foodLost = (int)(currentFood * .40);
                setFoodDestroyed(foodLost);
                break;

        }
    }
    public void generateMaterialDestroyed(){
        // determine how much material destroyed
        int currentMaterials = Village.getMaterials();
        int lostMaterials;

        if (currentMaterials < 10) {
            lostMaterials = (int) (currentMaterials * .10);
        } else if (currentMaterials >= 10 && currentMaterials< 30){

            lostMaterials = (int) (currentMaterials* .25);
        }
        else {
            currentMaterials = (int) (currentMaterials * .33);
        }
        setMaterialsDestroyed(currentMaterials);
    }
    public void generateHoursTaken(){
        // Roll dice on how much time a random event wasted from player
        setHoursTaken((int) ((Math.random() * (8 - 1)) + 1));
    }

}
