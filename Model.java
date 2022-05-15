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
        // if not overlap, create task, add to list, return true
        addTask(myTask);
        return true;
    }
    
    public boolean createTransientTask(String name, String type, float startTime, float duration, int date) throws ParseException{
        TransientTask myTask = new TransientTask(name, type, startTime, duration, date);
        // check overlap
        // if not overlap, create task, add to list, return true
        addTask(myTask);
        return true;
    }
    
    public boolean createAntiTask(String name, String type, float startTime, float duration, int date) throws ParseException{
        AntiTask myTask = new AntiTask(name, type, startTime, duration, date);
        // check overlap
        // if not overlap, create task, add to list, return true
        addTask(myTask);
        return true;
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
        
        Date newTaskEndDate; //could be same day as startDate
        Date existingTaskEndDate;
        
        //times
        float newTaskStartTime;
        float existingTaskStartTime;
        float newTaskDuration;
        float existingTaskDuration;
        
        //check for name overlap
        for(int i=0; i<listOfTask.size(); i++){
            existingName = listOfTask.get(i).getName();
            if(newName.equals(existingName) == true){
                System.out.println("The task name overlaps.");
                return true; //the names overlap
            }
        }
        
        // get startDate/date, startTime, duration, and type of newTask
        newTaskType = newTask.getType();
        if(newTask instanceof TransientTask){
            newTaskStartDate = ((TransientTask)newTask).getDateObject();
            newTaskStartTime = ((TransientTask)newTask).getStartTime();
            newTaskDuration = ((TransientTask)newTask).getDuration();
            
            // TransientTask newTransientTask = (TransientTask)newTask;
            // newTaskStartDate = newTransientTask.getDate();
            // newTaskStartTime = newTransientTask.getStartTime();
            // newTaskDuration = newTransientTask.getDuration();
        } else if (newTask instanceof RecurringTask){
            newTaskStartDate = ((RecurringTask)newTask).getStartDateObject();
            newTaskEndDate = ((RecurringTask)newTask).getEndDateObject();
            newTaskStartTime = ((RecurringTask)newTask).getStartTime();
            newTaskDuration = ((RecurringTask)newTask).getDuration();
        } else{
            newTaskStartDate = ((AntiTask)newTask).getDateObject();
            newTaskStartTime = ((AntiTask)newTask).getStartTime();
            newTaskDuration = ((AntiTask)newTask).getDuration();
        }
        
        for(int j=0; j<listOfTask.size(); j++){
            //get existing task's type, date(s), startTime, and duration
            existingTask = listOfTask.get(j);
            existingTaskType = existingTask.getType();
            
            //any combo of Transient and Anti-Tasks
            if(!(newTask instanceof RecurringTask) && !(existingTask instanceof RecurringTask)){
                //check overlap of dates first
                
            }
        }
        
        
        return false;
    }
    private Task findTaskFromNameBinarySearch(String name){
        return null;
    }
}
