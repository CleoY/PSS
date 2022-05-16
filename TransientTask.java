import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

/**
 * Write a description of class TransientTask here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TransientTask extends Task
{
    private int date;
    
    private SimpleDateFormat dateParser = new SimpleDateFormat("yyyyMMdd");
    private Calendar calendar = Calendar.getInstance();
    
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
        this.startDateObject = dateParser.parse(""+date);
        this.endTime = (startTime+duration)%(24); //task wraps past midnight
        
        //initialize endDateObject
        if(startTime + duration > 24){ //task wraps past midnight
            calendar.setTime(startDateObject);
            calendar.add(Calendar.DATE, 1); //increment by 1 day
            endDateObject = calendar.getTime();
        } else{ //task does NOT wrap past midnight
            endDateObject = startDateObject;
        }
    }
    
    public int getDate() {
        return date;
    }
    
    public void setDate(int date) throws ParseException{
        this.date = date;
        this.startDateObject = dateParser.parse(""+date);
        
        //set endDateObject
        if(startTime + duration > 24){ //task wraps past midnight
            calendar.setTime(startDateObject);
            calendar.add(Calendar.DATE, 1); //increment by 1 day
            endDateObject = calendar.getTime();
        } else{ //task does NOT wrap past midnight
            endDateObject = startDateObject;
        }
    }
}
