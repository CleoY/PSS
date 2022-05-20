import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

/**
 * Write a description of class Model here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Model
{
    private ArrayList<Task> listOfTask = new ArrayList<Task>();
    
    public Model() {}
    
    // Create an object of RecurringTask
    // Create an object of RecurringTask
    public boolean createRecurringTask(String name, String type, float startTime, float duration, int startDate, int endDate, int frequency) throws ParseException{ 
        RecurringTask myTask = new RecurringTask(name, type, startTime, duration, startDate, endDate, frequency);
        // check overlap
        // if not overlap, add to list, return true
        if (!checkOverlap(myTask)) {
            addTask(myTask);
            return true;
        }
        return false;
    }
    
    public boolean createTransientTask(String name, String type, float startTime, float duration, int date) throws ParseException{
        TransientTask myTask = new TransientTask(name, type, startTime, duration, date);
        // check overlap
        // if not overlap, add to list, return true
        if (!checkOverlap(myTask)) {
            addTask(myTask);
            return true;
        }
        return false;
    }
    
    public boolean createAntiTask(String name, String type, float startTime, float duration, int date) throws ParseException{
        AntiTask myTask = new AntiTask(name, type, startTime, duration, date);
        if (findAssociatedRecurringTask(myTask)) {
            addTask(myTask);
            return true;
        }
        return false;
    }
    
    public ArrayList<Task> getTaskList() {
        return listOfTask;
    }
    
    public void addTask(Task newTask) {
        listOfTask.add(newTask);
    }
    
    // Returning index instead of task but might change if we gonna have 3 ArrayList instead of 1
    public int findTask(String name) {
        for (int i = 0; i < listOfTask.size(); ++i) {
            if (listOfTask.get(i).name.equals(name)) {
                return i;
            }
        }
        return -1;
    }
    
    public Boolean deleteTask(String name) {
        //count for anti-task overlaps
        int count = 0;

        //get index of task
        int pos2 = findTask(name);
        Task myTask = listOfTask.get(pos2);

        //Reccuring Task Delete
        if (myTask instanceof RecurringTask) {
            RecurringTask rTask = (RecurringTask)myTask;
            //Find associated anti-tasks
            //Loop through the array (get antitasks and compare time and dates)
            for(int i = 0; i<listOfTask.size(); i++){
                myTask = listOfTask.get(i);
                if(myTask instanceof AntiTask){
                    AntiTask findAnti = (AntiTask)myTask;

                    //If the Start time and Date of Recurring and Anti task match 
                    //Delete recurring and anti-task
                    if(rTask.getStartTime() == findAnti.getStartTime() 
                    && rTask.getStartDate() == findAnti.getDate()){
                        listOfTask.remove(findAnti);
                        listOfTask.remove(rTask);
                        return true;
                    }
                    //There are no anti-tasks associated (Delete recurring)
                } else {
                    listOfTask.remove(rTask);
                    return true;
                }
                }  
            }
        //Transient Task Delete
        else if (myTask instanceof TransientTask) {
            TransientTask tTask = (TransientTask)myTask;
            listOfTask.remove(tTask);
            return true;
        }
        //Anti Task delete
        else {
            AntiTask aTask = (AntiTask)myTask;
            //check if there is more than 1 task with matching starttime and date
            //return error
            for(int i = 0; i<listOfTask.size(); i++){
                myTask = listOfTask.get(i);
                if(myTask instanceof TransientTask){
                    TransientTask findTran = (TransientTask)myTask;
                    if(aTask.getStartTime() == findTran.getStartTime() &&
                    aTask.getDate() == findTran.getDate()){
                        count++;
                    }
                }
                if(myTask instanceof RecurringTask){
                    RecurringTask findRec = (RecurringTask)myTask;
                    if(aTask.getStartTime() == findRec.getStartTime() &&
                    aTask.getDate() == findRec.getStartDate()){
                        count++;
                    }
                } 
            }
            //Overlap Error
            if(count > 1){
               return false;

            //No overlap
            } else {
                 //else
                listOfTask.remove(aTask);
                return true;
            }
        }
        return true;
    }

    public ArrayList<Task> getTasksInRange(int startDate, int endDate) throws ParseException {
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyyMMdd");
        Date start = dateParser.parse(""+startDate);
        Date end = dateParser.parse(""+endDate);
        ArrayList<Task> tasks = new ArrayList<Task>();

        for (int i = 0; i < listOfTask.size(); i++){
            if (listOfTask.get(i).startDateObject.equals(start) || listOfTask.get(i).startDateObject.after(start)) {
                if (listOfTask.get(i) instanceof RecurringTask) {
                    if (listOfTask.get(i).startDateObject.equals(end) || listOfTask.get(i).startDateObject.before(end)){
                        tasks.add(listOfTask.get(i));
                    }
                } else {
                    if (listOfTask.get(i).endDateObject.equals(end) || listOfTask.get(i).endDateObject.before(end)) {
                        tasks.add(listOfTask.get(i));
                    }
                }
            }
        }
        return tasks;
    }
    
    private boolean checkOverlap(Task newTask){
        //names
        String newName = newTask.getName();
        String existingName;
        
        //temp variable for existing Tasks in listOfTask
        Task existingTask;
        
        //types
        String newTaskType;
        String existingTaskType;
        
        //dates
        Date newTaskStartDate;
        Date existingTaskStartDate;
        
        //Date newTaskEndDate; //could be same day as startDate
        //Date existingTaskEndDate;
        
        //times
        float newTaskStartTime;
        float existingTaskStartTime;
        float newTaskEndTime;
        float existingTaskEndTime;
        
        float newTaskDuration;
        float existingTaskDuration;
        
        int overlapCounter=0;
        
        //check for name overlap
        for(int i=0; i<listOfTask.size(); i++){
            existingName = listOfTask.get(i).getName();
            if(newName.equals(existingName) == true){
                System.out.println("The task name overlaps.");
                return true; //the names overlap
            }
        }
        
        // get type, startDate, endDate, startTime, endTime, and duration of newTask
        newTaskType = newTask.getType();
        newTaskStartDate = newTask.getStartDateObject();
        //newTaskEndDate = newTask.getEndDateObject();
        newTaskStartTime = newTask.getStartTime();
        newTaskEndTime = newTask.getEndTime();
        newTaskDuration = newTask.getDuration();
        
        for(int j=0; j<listOfTask.size(); j++){
            existingTask = listOfTask.get(j);
            overlapCounter = 0;
            //get existing task's type, date(s), startTime, and duration
            existingTaskType = existingTask.getType();
            existingTaskStartDate = existingTask.getStartDateObject();
            //existingTaskEndDate = existingTask.getEndDateObject();
            existingTaskStartTime = existingTask.getStartTime();
            existingTaskEndTime = existingTask.getEndTime();
            existingTaskDuration = existingTask.getDuration();
            
            //any combo of Transient and Anti-Tasks
            if(!(newTask instanceof RecurringTask) && !(existingTask instanceof RecurringTask)){
                System.out.println("Neither task is recurring");
                //check overlap of dates first
                if (newTaskStartDate.equals(existingTaskStartDate)){
                    //cases 1 and 3
                    if((newTaskStartTime <= existingTaskStartTime) && (newTaskEndTime > existingTaskStartTime)){
                        System.out.println("Case 1 or 3");
                        overlapCounter++;
                    } else if((newTaskStartTime >= existingTaskStartTime) && (newTaskStartTime < existingTaskEndTime)){
                        System.out.println("Case 2 or 4");
                        overlapCounter++;
                    }
                    
                    //need to check Task types before returning true
                    //cannot return false until all Tasks in listOfTask have been checked
                    if(overlapCounter > 0){
                        System.out.println("newTask is Trans?: "+ (newTask instanceof TransientTask));
                        System.out.println("existingTask is Anti?: "+ (existingTask instanceof AntiTask));
                        if(!((newTask instanceof TransientTask) && (existingTask instanceof AntiTask))){
                            System.out.println("Not Trans & Anti combo");
                            return true;
                        }
                    }
                }else{
                    System.out.println("The tasks are not on the same day.");
                }
            }
        }
        
        
        return false;
    }
    private Task findTaskFromNameBinarySearch(String name){
        return null;
    }
    
    private boolean findAssociatedRecurringTask(AntiTask aTask) throws ParseException{
        for (int i = 0; i < listOfTask.size(); ++i) {
            // Find a recurring task with same start time
            if (listOfTask.get(i).getStartTime() == aTask.getStartTime() && listOfTask.get(i) instanceof RecurringTask) {
                // Recurring Task case
                if (listOfTask.get(i) instanceof RecurringTask) {
                    RecurringTask rTask = (RecurringTask)listOfTask.get(i);
                    int startDate = rTask.getStartDate();
                    int endDate = rTask.getEndDate();
                    int date = aTask.getDate();
                
                    if (date == startDate || date == endDate) {
                        return true;
                    }
                    else if (date < startDate || date > endDate) {
                        return false;
                    }
                    else {
                        // If anti-task is in the range of recurring task
                        int frequency = rTask.getFrequency();
                        String startDateStr = Integer.toString(startDate);
                        String endDateStr = Integer.toString(endDate);
                        String dateStr = Integer.toString(date);
                        String instanceDate = "";
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                        Calendar calendarDate = new GregorianCalendar();
                        calendarDate.setTime(dateFormat.parse(startDateStr));
                        while (!instanceDate.equals(endDateStr) && !instanceDate.equals(dateStr)) {
                            calendarDate.add(Calendar.DATE, frequency);
                            // Convert to String
                            instanceDate = dateFormat.format(calendarDate.getTime());
                        }
                    
                        if (instanceDate.equals(dateStr)) {
                            return true;
                        }
                    }
                }
                // Overlapped transient task and anti-task
                if (listOfTask.get(i) instanceof TransientTask && listOfTask.get(i).getStartTime() == aTask.getStartTime()) {
                    return false;
                }
            }
        }
        return false;
    }
}
