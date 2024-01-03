abstract public class FriendlyStranger {
    private static boolean firstStranger = false;
    private static boolean secondStranger = false;
    private static boolean secondAgainStranger = false;
    private static boolean thirdStranger = false;
    private static boolean fourthStranger =false;
    private static boolean haveAxe = false;

    public static boolean isFirstStranger() {
        return firstStranger;
    }

    public static boolean isSecondStranger() {
        return secondStranger;
    }

    public static boolean isSecondAgainStranger() {
        return secondAgainStranger;
    }

    public static boolean isThirdStranger() {
        return thirdStranger;
    }

    public static boolean isFourthStranger() {
        return fourthStranger;
    }

    public static boolean isHaveAxe() {
        return haveAxe;
    }

    public void setFirstStranger(boolean firstStranger) {
        this.firstStranger = firstStranger;
    }

    public void setSecondStranger(boolean secondStranger) {
        this.secondStranger = secondStranger;
    }

    public void setSecondAgainStranger(boolean secondAgainStranger) {
        this.secondAgainStranger = secondAgainStranger;
    }

    public void setThirdStranger(boolean thirdStranger) {
        this.thirdStranger = thirdStranger;
    }

    public void setFourthStranger(boolean fourthStranger) {
        FriendlyStranger.fourthStranger = fourthStranger;
    }

    public static void setHaveAxe(boolean haveAxe) {
        FriendlyStranger.haveAxe = haveAxe;
    }

    public static String getFoodMethod(boolean isSecondStranger, boolean isThirdStranger) {
        String method;

     if (isSecondStranger) {
            method = "hunting";

        } else {
            method = "fishing";
        }
        return method;

    }
    public static String getMaterialMethod(boolean isSecondStranger, boolean isThirdStranger) {
        // decide if player gets material alone or not
        String method;
        if (!isSecondStranger) {
            method = "alone";
        } else {
            method = "Group";
        }
        return method;

    }
    public static String canRebuild (boolean isThirdStranger) {
        // determine if player has unlocked rebuilding farms
        String method;
        if (isThirdStranger) {
            method = "yes";
        } else {
            method = "no";
        }
        return method;

    }
    public static String canBuildMilitia (boolean isSecondAgain) {
        // determine if player has unlocked rebuilding army
        String method;
        if (isSecondAgain) {
            method = "yes";
        } else {
            method = "no";
        }
        return method;

    }
    public static void triggerFirstStrangerCutScene1() throws InterruptedException{
        // trigger cutscene
        System.out.println();
        MainFisherman.delayedPrintln("A man walks over to the dock while you are fishing. He seems deep in thought",4500);
        System.out.println();
        MainFisherman.delayedPrintln("He looks thin",3000);
        MainFisherman.delayedPrintln("You Pass him a couple fish without a word",3000);
        System.out.println();
        MainFisherman.delayedPrintln("He accepts them and thanks you",3000);

        System.out.println();
    }
    public static void triggerFirstStrangerCutScene2() throws InterruptedException{
        // trigger cutscene unlock axe
        System.out.println();
        MainFisherman.delayedPrintln("The same man from yesterday returns",3000);
        System.out.println();
        MainFisherman.delayedPrintln("'Thank you so much for your help. I respect what you are trying to do'",3000);
        MainFisherman.delayedPrintln("'We're all just trying to survive. I think seeing your determination gives people hope.'",3000);
        System.out.println();
        MainFisherman.delayedPrintln("'Please accept this a token of my appreciation'",3500);
       System.out.println();
        MainFisherman.delayedPrintln("You received an axe",3000);
        MainFisherman.delayedPrintln("Gathering materials will now be easier",3000);
        setHaveAxe(true);
    }

    public static void triggerStranger2() throws InterruptedException{
        // trigger cutscene unlock hunting
        System.out.println();
        MainFisherman.delayedPrintln("A man walks up to you with plans to start a hunting committee",3000);
        System.out.println();
        MainFisherman.delayedPrintln("Gathering food will now be more efficient",3000);
        secondStranger = true;
    }
    public static void triggerStranger3() throws InterruptedException{
        // trigger cutscene unlock rebuilding farm
        System.out.println();
        MainFisherman.delayedPrintln("A farmer walks up to you with plans to start rebuilding the farms",3000);
        MainFisherman.delayedPrintln("She will need materials from you to get started",3000);
        System.out.println();
        MainFisherman.delayedPrintln("You may now use the rebuild button to use supplies to restore the farms",3000);
        thirdStranger = true;
    }
    public static void triggerStranger2again() throws InterruptedException {
        // trigger cutscene unlock rebuilding army
        System.out.println();
        MainFisherman.delayedPrintln("The man leading the hunters returns to you with plans to form a small army for protection",3000);
        MainFisherman.delayedPrintln("It's only a matter of time before the bandits return. You must prepare.", 3000);
        System.out.println();
        MainFisherman.delayedPrintln("You may now use the militia button to improve the army",3000);
        secondAgainStranger = true;
    }
}