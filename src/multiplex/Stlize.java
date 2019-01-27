package multiplex;

public class Stlize extends Detergent {
    @Override
    public void scrup() {
        append(" Stlize ");
        super.scrup();
    }

    public void sterlize(){
        append(" sterlize ");
    }

    public static void main(String[] args){
        Stlize stlize = new Stlize();
        stlize.scrup();
        stlize.apply();
        stlize.dilute();
        stlize.foam();
        stlize.sterlize();
        System.out.println(stlize);
        Detergent.main(args);
    }
}
