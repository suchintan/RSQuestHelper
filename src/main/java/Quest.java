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
    private Set<Quest> questSet;


    public Quest(String name, Difficulty difficulty, String length, int qp, Levels levels){
        setName(name);
        setDifficulty(difficulty);
        setLength(length);
        setQp(qp);
        setLevels(levels);
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
}
