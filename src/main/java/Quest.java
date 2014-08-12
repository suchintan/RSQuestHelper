import java.util.Set;

/**
 * Created by suchintan on 2014-08-11.
 */
public class Quest {
    private String name;
    private Difficulty difficulty;
    private String length;
    private int qp;
    private Levels levels;
    private String[] items;
    private String quests;
    private boolean finished;


    public Quest(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public int getQp() {
        return qp;
    }

    public void setQp(int qp) {
        this.qp = qp;
    }

    public Levels getLevels() {
        return levels;
    }

    public void setLevels(Levels levels) {
        this.levels = levels;
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public String getQuests() {
        return quests;
    }

    public void setQuests(String quests) {
        this.quests = quests;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        String s = getName() + "\n";
        s += getDifficulty() + "\n";
        s += getLength() + "\n";
        s += getQuests() + "\n";
        s += getLevels().toString() + "\n";
        for(String item : getItems()){
            s += item + " ";
        }
        s += "\n";
        s += getQp() + "\n";
        s += isFinished() ? "Complete" : "Incomplete";
        return s;
    }
}
