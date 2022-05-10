
/**
 * Write a description of class Model here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class Model
{
    private ArrayList<Task> listOfTask = new ArrayList<Task>();
    
    public Model() {}
    
    // Create an object of RecurringTask
    public void createRecurringTask(String name, String type, float startTime, float duration, int startDate, int endDate, int frequency) { 
        RecurringTask myTask = new RecurringTask(name, type, startTime, duration, startDate, endDate, frequency);
        addTask(myTask);
        
        
        // This is just for testing
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Start time: " + startTime);
        System.out.println("Duration: " + duration);
        System.out.println("Start date: " + startDate);
        System.out.println("End date: " + endDate);
        System.out.println("Frequency: " + frequency);
    }
    
    public void createTransientTask(String name, String type, float startTime, float duration, int date) {
        TransientTask myTask = new TransientTask(name, type, startTime, duration, date);
        addTask(myTask);
    }
    
    public void createAntiTask(String name, String type, float startTime, float duration, int date) {
        AntiTask myTask = new AntiTask(name, type, startTime, duration, date);
        addTask(myTask);
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
}
