
/**
 * Write a description of class AntiTask here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AntiTask extends Task
{
    private int date;
    
    // Not sure if necessary but leaving this here for now
    public AntiTask(String name) {
        this.name = name;
    }
    
    public AntiTask(String name, String type, float startTime, float duration, int date) {
        this.name = name;
        this.type = type;
        this.startTime = startTime;
        this.duration = duration;
        this.date = date;
    }
    
    public int getDate() {
        return date;
    }
    
    public void setDate(int date) {
        this.date = date;
    }
}
