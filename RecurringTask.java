import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Write a description of class RecurringTask here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RecurringTask extends Task
{
    private int startDate;
    private int endDate;
    private int frequency;
    
    // Not sure if necessary but leaving this here for now
    public RecurringTask(String name) {
        this.name = name;
    }
    
    public RecurringTask(String name, String type, float startTime, float duration, int startDate, int endDate, int frequency) {
        this.name = name;
        this.type = type;
        this.startTime = startTime;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.frequency = frequency;
    }
    
    public int getStartDate() {
        return startDate;
    }
    
    public int getEndDate() {
        return endDate;
    }
    
    public int getFrequency() {
        return frequency;
    }
    
    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }
    
    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }
    
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
