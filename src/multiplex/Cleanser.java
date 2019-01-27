package multiplex;

public class Cleanser {
    private String s = "Cleanser";

    public void append(String a) {
        s += a;
    }

    public void dilute() {
        append(" dilute ");
    }

    public void apply() {
        append(" apply ");
    }

    public void scrup() {
        append(" scrup ");
    }

    public String toString() {
        return s;
    }

    public static void main(String[] args) {
        Cleanser cleanser = new Cleanser();
        cleanser.dilute();
        cleanser.apply();
        cleanser.scrup();
        System.out.println(cleanser);
    }
}
