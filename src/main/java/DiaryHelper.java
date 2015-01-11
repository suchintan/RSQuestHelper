import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suchintan on 2015-01-11.
 */
public class DiaryHelper {
    private static Levels getDiaryReqs() {
        Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("Attack",70);
        map.put("Defence",70);
        map.put("Strength",76);
        map.put("Hitpoints",70);
        map.put("Ranged",70);
        map.put("Prayer",85);
        map.put("Magic",96);
        map.put("Cooking",95);
        map.put("Woodcutting",75);
        map.put("Fletching",95);
        map.put("Fishing",96);
        map.put("Firemaking",85);
        map.put("Crafting",85);
        map.put("Smithing",91);
        map.put("Mining",85);
        map.put("Herblore",90);
        map.put("Agility",90);
        map.put("Thieving",91);
        map.put("Slayer",93);
        map.put("Farming",91);
        map.put("Runecrafting",91);
        map.put("Hunter",80);
        map.put("Construction",78);

        return new Levels(map);
    }

    public static void main(String[] args) throws Exception{
        URL url = new URL("http://services.runescape.com/m=hiscore_oldschool/index_lite.ws?player=NPE");
        URLConnection con = url.openConnection();

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

        List<String> levels = new ArrayList<String>();
        String in;
        while((in = br.readLine()) != null){
            levels.add(in);
        }
        levels.remove(0);
        levels.remove(levels.size()-1);

        Map<String, Integer> map = new HashMap<String, Integer>();

        String[] names = "\"Attack\", \"Defence\", \"Strength\",\"Hitpoints\", \"Ranged\", \"Prayer\",\"Magic\", \"Cooking\", \"Woodcutting\",\"Fletching\", \"Fishing\", \"Firemaking\",\"Crafting\", \"Smithing\", \"Mining\",\"Herblore\", \"Agility\", \"Thieving\",\"Slayer\", \"Farming\", \"Runecrafting\",\"Hunter\", \"Construction\""
                .replaceAll("\"", "").trim().split(",");

        for(int c = 0; c < names.length; c++){
            map.put(names[c].trim(), Integer.parseInt(levels.get(c).split(",")[1]));
        }



        Levels player = new Levels(map);

        Levels diaryReqs = getDiaryReqs();

        boolean canBeCompleted = player.compareTo(diaryReqs) == 1;

        Skill[] missingSkills = new Skill[0];
        if(!canBeCompleted){
            missingSkills = player.getMissingSkills(diaryReqs);
        }

        Map<Skill, Integer> skills = new HashMap<Skill, Integer>();
        Levels l = diaryReqs;
        for(Skill s : missingSkills){
            if(!skills.containsKey(s)){
                skills.put(s, 0);
            }

            if(skills.get(s) < l.getSkillLevel(s)){
                skills.put(s, l.getSkillLevel(s));
            }
        }


        for(Skill s : skills.keySet()){
            System.out.println(s + " " + skills.get(s) + " " + player.getSkillLevel(s));
        }
    }
}
