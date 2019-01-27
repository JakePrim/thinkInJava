package multiplex;

public class Detergent extends Cleanser {

    private Cleanser cleanser = new Cleanser();



    @Override
    public void scrup() {
        append(" Detergent ");
        super.scrup();
    }

    //add methods
    public void foam() {
        append(" foam ");
    }

    public static void main(String[] args) {
        Detergent detergent = new Detergent();
        detergent.dilute();
        detergent.apply();
        detergent.scrup();
        detergent.foam();
        System.out.println(detergent);
        System.out.println("Test Base Class:");
        Cleanser.main(args);
    }
}
