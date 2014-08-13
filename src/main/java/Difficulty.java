/**
 * Created by suchintan on 2014-08-11.
 */
public enum Difficulty {
    Novice, Intermediate, Experienced, Master;

    public static int toInt(Difficulty d){
        switch (d){
            case Novice:
                return 0;
            case Intermediate:
                return 1;
            case Experienced:
                return 2;
            case Master:
                return 3;
            default:
                return 4;

        }
    }
}
