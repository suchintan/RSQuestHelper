import java.util.Map;
import java.util.TreeMap;

/**
 * Created by suchintan on 2014-08-11.
 */
public class Levels implements Comparable{
    private Map<Skill, Integer> levels;

    public Levels(Map<String, Integer> levels){
        this.levels = new TreeMap<Skill, Integer>();
        for(int c = 0; c < Skill.values().length; c++){
            Skill s = Skill.values()[c];
            this.levels.put(s, levels.containsKey(s.toString()) ? levels.get(s.toString()) : Integer.valueOf(0));
        }
    }

    public int getSkillLevel(Skill s){
        return levels.get(s);
    }

    @Override
    public int compareTo(Object o) {
        Levels other = (Levels) o;

        for(Skill s : Skill.values()){
            if(!(levels.get(s) >= other.getSkillLevel(s))){
                return -1;
            }
        }
        return 1;
    }

    @Override
    public String toString() {
        return levels.toString();
    }
}
