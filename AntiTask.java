import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Write a description of class AntiTask here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class AntiTask extends Task
{
    private int date;
    private Date dateObject;
    private SimpleDateFormat dateParser = new SimpleDateFormat("yyyyMMdd");
    
    // Not sure if necessary but leaving this here for now
    public AntiTask(String name) {
        this.name = name;
    }
    
    public AntiTask(String name, String type, float startTime, float duration, int date) throws ParseException{
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
    
    public Date getDateObject(){
        return dateObject;
    }
    
    public void setDate(int date) throws ParseException{
        this.date = date;
        this.dateObject = dateParser.parse(""+date);
    }
}
