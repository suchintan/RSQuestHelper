import java.util.Map;

/**
 * Created by suchintan on 2014-08-11.
 */
public class Levels implements Comparable{
    private Map<Skill, Integer> levels;

    public Levels(String csv){
        String[] lvs = csv.split(",");
        if(lvs.length != Skill.values().length){
            throw new RuntimeException("Length difference" + lvs.length + " " + Skill.values().length);
        }
        for(int c = 0; c < lvs.length; c++){
            levels.put(Skill.values()[c], Integer.parseInt(lvs[c]));
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
}
