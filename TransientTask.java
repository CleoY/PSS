import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Write a description of class TransientTask here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TransientTask extends Task
{
    private int date;
    private Date dateObject;
    private SimpleDateFormat dateParser = new SimpleDateFormat("yyyyMMdd");
    
    // Not sure if necessary but leaving this here for now
    public TransientTask(String name) {
        this.name = name;
    }
    
    public TransientTask(String name, String type, float startTime, float duration, int date) throws ParseException{
        this.name = name;
        this.type = type;
        this.startTime = startTime;
        this.duration = duration;
        this.date = date;
        this.dateObject = dateParser.parse(""+date);
    }
    
    public int getDate() {
        return date;
    }
    
    public void setDate(int date) throws ParseException{
        this.date = date;
        this.dateObject = dateParser.parse(""+date);
    }
}
