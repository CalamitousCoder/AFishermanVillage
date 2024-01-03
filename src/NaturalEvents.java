public class NaturalEvents extends RandomEvents{
    // default constructor
    String involvedAnimal;
    String involvedWeatherHazard;
    String otherNaturalHazards;

    public NaturalEvents(){
        String involvedAnimal = "unknown";
        String involvedWeatherHazard = "unknown";
        String otherNaturalHazards = "unknown";
    }

    // super overloaded constructor
    public NaturalEvents(String involvedAnimal, String involvedWeatherHazard, String otherNaturalHazards, double probability, int hoursTaken, String name){
        super(probability, hoursTaken, name);
        this.involvedAnimal = involvedAnimal;
        this.otherNaturalHazards = otherNaturalHazards;
        this.involvedWeatherHazard = involvedWeatherHazard;
    }

    // overloaded constructor B
    public NaturalEvents(double probability, int hoursTaken, String name, String involvedAnimal) {
        super(probability, hoursTaken, name);
        this.involvedAnimal = involvedAnimal;
    }

    public String getInvolvedAnimal() {
        return involvedAnimal;
    }

    public String getInvolvedWeatherHazard() {
        return involvedWeatherHazard;
    }

    public String getOtherNaturalHazards() {
        return otherNaturalHazards;
    }
    // set

    public void setInvolvedAnimal(String involvedAnimal) {
        this.involvedAnimal = involvedAnimal;
    }

    public void setInvolvedWeatherHazard(String involvedWeatherHazard) {
        this.involvedWeatherHazard = involvedWeatherHazard;
    }

    public void setOtherNaturalHazards(String otherNaturalHazards) {
        this.otherNaturalHazards = otherNaturalHazards;
    }
    // method using super
    public void calculateAllDamage(){
        // Generates all values for event
        super.generateFoodDestroyed();
        super.generateMaterialDestroyed();
        super.generateHoursTaken();
        super.generatePeopleKilled();
    }

    // additional useful methods
    public void announceAnimalAttack(String context) throws InterruptedException {
        //announce animal attack and generate values
        super.generateFoodDestroyed();
        super.generateHoursTaken();
        super.generatePeopleKilled();

        super.announceEvent(context+ " were attacked by a " + involvedAnimal);
    }

    public void announceClimateFoodDisaster(String fateOfCrops) throws InterruptedException {
        //announce disaster to player and generate values
        super.generateFoodDestroyed();
        super.generateHoursTaken();
        int foodLost = super.getFoodDestroyed();

        MainFisherman.delayedPrintln("There was a " + involvedWeatherHazard,3000);
        MainFisherman.delayedPrintln("No one died, but the crops have " +fateOfCrops,3000);

        System.out.println();
        MainFisherman.delayedPrintln("You've lost " + foodLost + " pieces of food.", 3400);
    }
}
