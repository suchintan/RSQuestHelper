import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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


        Document doc = Jsoup.connect("http://www.runehq.com/oldschoolquest").get();
        Elements elements = doc.select("tbody").select("tr");

        Map<String, Quest> quests = new HashMap<String, Quest>();

        for(Element e : elements){
            //grab url of quest page
            String url = e.children().get(0).child(0).attr("href");

            System.out.println("http://www.runehq.com" + url);
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

            //print out quest page data
            for(Element el : e.children()){
                System.out.println(el.text());
            }

            //print specific quest data
            for(int c = 0; c < headers.size(); c++){
                System.out.println(headers.get(c) + guides.get(c));
            }
//            System.out.println(details);

            break;
//            for(Element el : e.children()){
//                System.out.println(el.text());
//            }
        }
//        Elements e = elements.get(0).children();
//        System.out.println(e.get(0).child(0).attr("href"));
//        for(Element el : e){
//            System.out.println(el.text());
//        }
    }
}
