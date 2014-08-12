import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by susingh on 8/11/14.
 */
public class QuestDeserializer implements JsonDeserializer<Quest> {

    @Override
    public Quest deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject j = jsonElement.getAsJsonObject();
        Quest q = new Quest();

        q.setName(j.getAsJsonPrimitive("name").getAsString());
        q.setDifficulty(Difficulty.valueOf(j.getAsJsonPrimitive("difficulty").getAsString()));
        q.setLength(j.getAsJsonPrimitive("length").getAsString());
        q.setQp(j.getAsJsonPrimitive("qp").getAsInt());

        JsonObject levels = j.getAsJsonObject("levels").getAsJsonObject("levels");

        Map<String, Integer> lvs = new HashMap<String, Integer>();
        for(Skill s : Skill.values()){
            lvs.put(s.toString(), levels.getAsJsonPrimitive(s.toString()).getAsInt());
        }
        q.setLevels(new Levels(lvs));

        String[] items = j.getAsJsonArray("items").toString().split(",");
        q.setItems(items);

        q.setQuests(j.getAsJsonPrimitive("quests").getAsString());
        q.setFinished(j.getAsJsonPrimitive("finished").getAsBoolean());
        return q;
    }
}
