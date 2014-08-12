import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suchintan on 2014-08-11.
 */
public class QuestHelper {

    public static void main(String[] args) throws Exception{
        List<String> headers = new ArrayList<String>();
        List<String> guides = new ArrayList<String>();

        List<Quest> quests = new ArrayList<Quest>();


        Document doc = Jsoup.connect("http://www.runehq.com/oldschoolquest").get();
        Elements elements = doc.select("tbody").select("tr");

        for(Element e : elements){
            headers.clear();
            guides.clear();
            //grab url of quest page
            String url = e.children().get(0).child(0).attr("href");

            Document d = Jsoup.connect("http://www.runehq.com" + url).get();


            Elements details = d.select("ol").parents().parents().get(0).children();

            for(Element specs : details){
                String clazz = specs.attr("class");
                if(clazz.equals("undersubheader")){
                    headers.add(specs.text());
                }else{
                    guides.add(specs.text());
                }

            }

            Quest quest = new Quest();
            quest.setName(e.children().get(0).text());
            quest.setDifficulty(Difficulty.valueOf(guides.get(0)));
            quest.setLength(guides.get(1));
            quest.setQuests(guides.get(2));

            String[] lvs = guides.get(3).split(" ");
            Map<String, Integer> skills = new HashMap<String, Integer>();
            for(int c = 0; c < lvs.length/2; c++){

                skills.put(lvs[2 * c + 1], Integer.parseInt(lvs[2 * c]));
            }
            Levels levels = new Levels(skills);

            quest.setLevels(levels);

            String items = guides.get(4) + ", " + guides.get(5) + ", " + guides.get(6);
            quest.setItems(items.replaceAll(", ", ",").split(","));

            quest.setQp(Integer.parseInt(guides.get(8)));

            quests.add(quest);
//            System.out.println(quest.toString());


            //print specific quest data
//            System.out.println(e.children().get(0).text()); //quest name
//            for(int c = 0; c < headers.size(); c++){
//                System.out.println(c + ": " + headers.get(c) + guides.get(c));
//            }


        }

//        System.out.println(new Gson().toJson(quests));

//        Gson gson = new GsonBuilder().registerTypeAdapter(Quest.class, new QuestDeserializer()).create();

//        System.out.println(new Gson().fromJson(t, new TypeToken<List<Quest>>(){}.getType()));
        writeQuestsToFile(new Gson().toJson(quests));

    }

    private static void writeQuestsToFile(String json) throws Exception{
        String filename = "quest_data.json";

        FileWriter fw = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(json + "\n");
        bw.close();
    }
}
