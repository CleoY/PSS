import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

/**
 * Abstract class Task - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Task
{
    // instance variables - replace the example below with your own
    String name;
    String type;
    float startTime;
    float duration;
    float endTime;
    
    Date startDateObject;
    Date endDateObject; 
    //^may be the same as startDate; accounts for Transient and Anti tasks that wrap past midnight
    
    public Task() {}

    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
    
    public float getStartTime() {
        return startTime;
    }
    
    public float getDuration() {
        return duration;
    }
    
    public float getEndTime(){
        return endTime;
    }
    
    public Date getStartDateObject(){
        return startDateObject;
    }
    
    public Date getEndDateObject(){
        return endDateObject;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setStartTime(float startTime) {
        this.startTime = startTime;
        //changing startTime also affects endTime
        this.endTime = (startTime + duration)%24;
        //startDateObject.setTime(convertTime(startTime));
    }
    
    public void setDuration(float duration) {
        this.duration = duration;
        //changing duration affects endTime
        this.endTime = (startTime + duration)%24;
    }
    
    // public float convertTime(float time){
        // int timeInt = (int) time;
        // float timeDec = time - timeInt;
        // float hourInMS = timeInt * 3600000;
        // float oneMinInMS = 60000;
        // if(timeDec == 0){
            // return hourInMS;
        // } else if(timeDec == 0.25){
            // return hourInMS + 15*oneMinInMS;
        // } else if(timeDec == 0.5){
            // return hourInMS + 30*oneMinInMS;
        // } else if(timeDec == 0.75){
            // return hourInMS + 45*oneMinInMS;
        // }
    // }
}
