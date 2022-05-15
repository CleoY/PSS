import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

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
    private Date endDateObject; //may be the same as startDate; accounts for tasks that wrap past midnight
    
    private SimpleDateFormat dateParser = new SimpleDateFormat("yyyyMMdd");
    private Calendar calendar = Calendar.getInstance();
    
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
        this.endTime = (startTime+duration)%(24); //task wraps past midnight
        
        //initialize endDateObject
        if(startTime + duration > 24){ //task wraps past midnight
            calendar.setTime(dateObject);
            calendar.add(Calendar.DATE, 1); //increment by 1 day
            endDateObject = calendar.getTime();
        } else{ //task does NOT wrap past midnight
            endDateObject = dateObject;
        }
    }
    
    public int getDate() {
        return date;
    }
    
    public Date getDateObject(){
        return dateObject;
    }
    
    public Date getEndDateObject(){
        return endDateObject;
    }
    
    public void setDate(int date) throws ParseException{
        this.date = date;
        this.dateObject = dateParser.parse(""+date);
        
        //set endDateObject
        if(startTime + duration > 24){ //task wraps past midnight
            calendar.setTime(dateObject);
            calendar.add(Calendar.DATE, 1); //increment by 1 day
            endDateObject = calendar.getTime();
        } else{ //task does NOT wrap past midnight
            endDateObject = dateObject;
        }
    }
}
