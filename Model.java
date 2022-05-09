
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
    
    public void deleteTask(String name) {
        
    }
}
