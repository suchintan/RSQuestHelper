import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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

            System.out.println(q);
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
