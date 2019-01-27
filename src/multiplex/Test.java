package multiplex;

import javafx.application.Application;
import javafx.stage.Stage;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Music music = new Music();
        Wind wind = new Wind();
        AS as = new AS();
        music.tune(wind);
        music.a(as);
        music.a(wind);
    }
    //final 有助于提高性能

    public enum Note {
        MIDDLE_C, C_SHARP, B_FlAT
    }

    class Insturment {
        public void play(Note b) {
            System.out.println("Insturment play :" + b);
        }

        public void aujst(){
            System.out.println("Insturment.aujst");
        }
    }

    class Wind extends Insturment {
        public void play(Note b) {
            System.out.println("Wind.play():" + b);
        }

        @Override
        public void aujst() {
            System.out.println("Wind.aujst");
        }
    }

    class AS extends Insturment{
        @Override
        public void aujst() {
            System.out.println("AS.aujst");
        }
    }

    class Music {
        public void tune(Insturment insturment) {
            insturment.play(Note.B_FlAT);
        }

        public void a(Insturment insturment){
            insturment.aujst();
        }
    }
}
