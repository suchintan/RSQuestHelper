import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * Created by susingh on 8/11/14.
 */
public class QuestFinder {
    public static void main(String[] args) throws Exception{
        List<String> questData = getQuestDataFromFile("quest_data.csv");

        List<Quest> quests = new ArrayList<Quest>();
        for(String questInfo : questData){
            String[] info = questInfo.split(",");

            Quest q = new Quest();

            q.setName(info[0]);
            q.setDifficulty(Difficulty.valueOf(info[1]));
            q.setLength(info[2]);
            q.setQp(Integer.parseInt(info[3]));
            q.setItems("".split(""));
            q.setQuests(info[5]);
            q.setFinished(false);

            String[] levelsArray = new String[info.length-6];
            for(int c = 6; c < info.length; c++){
                levelsArray[c-6] = info[c];
            }
            q.setLevels(new Levels(levelsArray));

            quests.add(q);
        }

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


        for(int c = 0; c < levels.size(); c++){
            map.put(names[c].trim(), Integer.parseInt(levels.get(c).split(",")[1]));
        }

        Levels player = new Levels(map);

        for(Quest q : quests){
            q.setCanBeCompleted(player);
        }

        Quest[] questsArray = quests.toArray(new Quest[0]);
        List<Quest> requirementsMet = new ArrayList<Quest>();
        List<Quest> requirementsNotMet = new ArrayList<Quest>();

        Arrays.sort(questsArray);
        for(Quest q : questsArray){
            if(q.isCanBeCompleted()){
                requirementsMet.add(q);
            }else{
                requirementsNotMet.add(q);
            }
        }

        System.out.println("Requirement met:");

        for(Quest q : requirementsMet){
            System.out.println(q.toString());

        }

        System.out.println("\n\n\nNeed stats to finish");

        for(Quest q : requirementsNotMet){
            System.out.println(q.toString());
        }

        Map<Skill, Integer> skills = new HashMap<Skill, Integer>();
        for(Quest q : requirementsNotMet){
            Skill[] sk = q.getMissingSkills();
            Levels l = q.getLevels();
            for(Skill s : sk){
                if(!skills.containsKey(s)){
                    skills.put(s, 0);
                }

                if(skills.get(s) < l.getSkillLevel(s)){
                    skills.put(s, l.getSkillLevel(s));
                }
            }
        }

        for(Skill s : skills.keySet()){
            System.out.println(s + " " + skills.get(s) + " " + player.getSkillLevel(s));
        }

    }

    private static List<String> getQuestDataFromFile(String filename) throws Exception{
        List<String> data = new ArrayList<String>();

        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        String in;
        while( (in = br.readLine()) != null){
            data.add(in);
        }
        br.close();
        return data;
    }
}
