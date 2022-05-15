
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
    }
    
    public void setDuration(float duration) {
        this.duration = duration;
        //changing duration affects endTime
        this.endTime = (startTime + duration)%24;
    }
}
