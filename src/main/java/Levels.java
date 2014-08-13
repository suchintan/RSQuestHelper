import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by suchintan on 2014-08-11.
 */
public class Levels implements Comparable<Levels>{
    private Map<Skill, Integer> levels;

    public Levels(Map<String, Integer> levels){
        this.levels = new TreeMap<Skill, Integer>();
        for(int c = 0; c < Skill.values().length; c++){
            Skill s = Skill.values()[c];
            this.levels.put(s, levels.containsKey(s.toString()) ? levels.get(s.toString()) : Integer.valueOf(0));
        }
    }

    public Levels(String[] levels){
        this.levels = new TreeMap<Skill, Integer>();

        if(levels.length != Skill.values().length){
            throw new RuntimeException("Incorrect levels length: " + levels.length + " " + " should be " + Skill.values().length);
        }

        int c = 0;
        for(Skill s : Skill.values()){
            this.levels.put(s, Integer.parseInt(levels[c]));
            c++;
        }
    }

    public int getSkillLevel(Skill s){
        return levels.get(s);
    }

    @Override
    public int compareTo(Levels other) {
        if(getMissingSkills(other).length > 0){
            return -1;
        }else{
            return 1;
        }
    }

    public int sum(){
        int sum = 0;
        for(Skill s : Skill.values()){
            sum += levels.get(s);
        }
        return sum;
    }

    @Override
    public String toString() {
        return levels.toString();
    }

    public Skill[] getMissingSkills(Levels other) {
        List<Skill> skills = new ArrayList<Skill>();
        for(Skill s : Skill.values()){
            if(levels.get(s) < other.getSkillLevel(s)){
                skills.add(s);
            }
        }
        return skills.toArray(new Skill[0]);
    }
}
