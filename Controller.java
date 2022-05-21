import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Write a description of class Controller here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Controller
{
    public Controller() {}

    public void displayMenu()
    {
        System.out.println("*** WELCOME TO PSS SYSTEM ***");
        System.out.println("\nPlease select an option:\n");
        System.out.println("1 - Create a task");
        System.out.println("2 - View a task");
        System.out.println("3 - Delete a task");
        System.out.println("4 - Edit a task");
        System.out.println("5 - Write the schedule to a file");
        System.out.println("6 - Read the schedule from a file");
        System.out.println("7 - View schedule");
        System.out.println("0 - Exit");
        System.out.print("\nEnter your choice: ");
    }

    public void readInput() throws ParseException{
        Model model = new Model();
        Scanner scan = new Scanner(System.in);
        int input = Integer.parseInt(scan.nextLine());

        while (input != 0 ) {
            switch (input) {
                // Create task
                case 1:
                    System.out.println("Please enter the name of task: ");
                    String name = scan.nextLine();
                    int pos = model.findTask(name);
                    while (pos != -1) {
                        System.out.println("Invalid name. Please enter a different name: ");
                        name = scan.nextLine();
                    }

                    System.out.println("Please choose the type of task you want to create:\n");
                    System.out.println("1 - Recurring Task");
                    System.out.println("2 - Transient Task");
                    System.out.println("3 - Anti-task");
                    int choice = Integer.parseInt(scan.nextLine());
                    while (choice != 1 && choice != 2 && choice != 3) {
                        System.out.println("Invalid option. Please choose a valid option: ");
                        choice = Integer.parseInt(scan.nextLine());
                    }
                    String taskType = "";
                    if (choice == 1) {
                        // Get task type
                        System.out.println("Please choose a task type: ");
                        System.out.println("1 - Class");
                        System.out.println("2 - Study");
                        System.out.println("3 - Sleep");
                        System.out.println("4 - Exercise");
                        System.out.println("5 - Work");
                        System.out.println("6 - Meal");

                        int typeChoice = Integer.parseInt(scan.nextLine());
                        while (typeChoice < 1 || typeChoice > 6) {
                            System.out.println("Invalid option. Please choose a valid option: ");
                            typeChoice = Integer.parseInt(scan.nextLine());
                        }
                        switch (typeChoice) {
                            case 1:
                                taskType = "Class";
                                break;
                            case 2:
                                taskType = "Study";
                                break;
                            case 3:
                                taskType = "Sleep";
                                break;
                            case 4:
                                taskType = "Exercise";
                                break;
                            case 5:
                                taskType = "Work";
                                break;
                            case 6:
                                taskType = "Meal";
                                break;
                            default:
                                taskType = "";
                                break;
                        }
                    }
                    else if (choice == 2) {
                        // Get task type
                        System.out.println("Please choose a task type: ");
                        System.out.println("1 - Visit");
                        System.out.println("2 - Shopping");
                        System.out.println("3 - Appointment");

                        int typeChoice = Integer.parseInt(scan.nextLine());
                        while (typeChoice < 1 || typeChoice > 6) {
                            System.out.println("Invalid option. Please choose a valid option: ");
                            typeChoice = Integer.parseInt(scan.nextLine());
                        }
                        switch (typeChoice) {
                            case 1:
                                taskType = "Visit";
                                break;
                            case 2:
                                taskType = "Shopping";
                                break;
                            case 3:
                                taskType = "Appointment";
                                break;
                            default:
                                taskType = "";
                                break;
                        }
                    }
                    else {
                        taskType = "Cancellation";
                    }

                    // Get start time
                    float startTime = getStartTime();

                    // Get duration
                    float duration = getDuration();

                    if (choice == 1) {
                        // Get start date
                        int startDate = getStartDate();

                        // Get end date
                        int endDate = getEndDate(startDate);

                        // Get frequency
                        int frequency = getFrequency();

                        if (model.createRecurringTask(name, taskType, startTime, duration, startDate, endDate, frequency)) {
                            System.out.println("Sucessfully created recurring task.");
                        }
                        else {
                            System.out.println("Cannot create recurring task. Please check inputs and try again.");
                        }
                    }
                    else if (choice == 2) {
                        // Get date
                        int date = getDate();

                        if (model.createTransientTask(name, taskType, startTime, duration, date)) {
                            System.out.println("Sucessfully created transient task.");
                        }
                        else {
                            System.out.println("Cannot create transient task. Please check inputs and try again.");
                        }
                    }
                    else {
                        // Get date
                        int date = getDate();

                        if (model.createAntiTask(name, taskType, startTime, duration, date)) {
                            System.out.println("Sucessfully created anti-task.");
                        }
                        else {
                            System.out.println("Cannot create anti-task. Please check inputs and try again.");
                        }
                    }
                    break;
                // View task
                case 2:
                    System.out.println("Please enter the name of task: ");
                    name = scan.nextLine();
                    pos = model.findTask(name);
                    if (pos != -1) {
                        Task myTask = model.getTaskList().get(pos);
                        System.out.println("\n*** TASK INFO ***");
                        System.out.println("Name: " + myTask.getName());
                        System.out.println("Type: " + myTask.getType());
                        System.out.println("Start time: " + myTask.getStartTime());
                        System.out.println("Duration: " + myTask.getDuration());

                        if (myTask instanceof RecurringTask) {
                            RecurringTask rTask = (RecurringTask)myTask;
                            System.out.println("Start Date: " + rTask.getStartDate());
                            System.out.println("End Date: " + rTask.getEndDate());
                            System.out.println("Frequency: " + rTask.getFrequency());
                        }
                        else if (myTask instanceof TransientTask) {
                            TransientTask tTask = (TransientTask)myTask;
                            System.out.println("Date: " + tTask.getDate());
                        }
                        else {
                            AntiTask aTask = (AntiTask)myTask;
                            System.out.println("Date: " + aTask.getDate());
                        }
                    }
                    else {
                        System.out.println("Task name does not exist. Please try again.");
                    }
                    break;

                // Delete task
                case 3:
                    System.out.println("Please enter the name of task: ");
                    name = scan.nextLine();
                    pos = model.findTask(name);
                    //Will only delete if the task exists
                    if (pos != -1) {
                        Boolean success = model.deleteTask(name);
                        if(success == true)
                        {
                            System.out.println("The task was deleted sucessfully.");
                        } else {
                            System.out.println("Deleting this anti-task would leave conflicts between two or more tasks.");
                        }
                    }
                    else {
                        System.out.println("Task name does not exists. Please try again.");
                    }

                    break;
                // Edit task
                case 4:
                    //Input Task Name
                    System.out.println("Please enter the name of task you wish to edit: ");
                    name = scan.nextLine(); 
                    //Existed task name
                    pos = model.findTask(name);
                    if (pos != -1) {
                        Task myTask = model.getTaskList().get(pos);
                        System.out.println("\n*** TASK INFO ***");
                        System.out.println("Name: " + myTask.getName());
                        System.out.println("Type: " + myTask.getType());
                        System.out.println("Start time: " + myTask.getStartTime());
                        System.out.println("Duration: " + myTask.getDuration());

                        if (myTask instanceof RecurringTask) {
                            RecurringTask rTask = (RecurringTask)myTask;

                             //Save task info
                             String tempName = myTask.getName();
                             String tempType = rTask.getType();
                             float tempStartTime = rTask.getStartTime();
                             float tempDuration = rTask.getDuration();
                             int tempStartDate = rTask.getStartDate();
                             int tempEndDate = rTask.getEndDate();
                             int tempFrequency = rTask.getFrequency();

                            System.out.println("Start Date: " + rTask.getStartDate());
                            System.out.println("End Date: " + rTask.getEndDate());
                            System.out.println("Frequency: " + rTask.getFrequency());

                            System.out.println("\n What would you like to edit? ");
                            System.out.println("1 - Name");
                            System.out.println("2 - Type");
                            System.out.println("3 - Start Time");
                            System.out.println("4 - Duration");
                            System.out.println("5 - Start Date");
                            System.out.println("6 - End Date");
                            System.out.println("7 - Frequency");

                            int typeChoice = Integer.parseInt(scan.nextLine());
                            while (typeChoice < 1 || typeChoice > 7) {
                                System.out.println("Invalid option. Please choose a valid option: ");
                                typeChoice = Integer.parseInt(scan.nextLine());
                            }
                            switch (typeChoice) {
                                //Edit Name
                                case 1:
                                System.out.println("What shall the task be renamed as: ");
                                String editName = scan.nextLine(); 
                                    rTask.setName(editName);
                                    System.out.println("\n*** UPDATED TASK INFO ***");
                                    System.out.println("Name: " + rTask.getName());
                                    System.out.println("Type: " + rTask.getType());
                                    System.out.println("Start time: " + rTask.getStartTime());
                                    System.out.println("Duration: " + rTask.getDuration());
                                    System.out.println("Start Date: " + rTask.getStartDate());
                                    System.out.println("End Date: " + rTask.getEndDate());
                                    System.out.println("Frequency: " + rTask.getFrequency());;
                                    break;
                                //Edit Type
                                case 2:
                                System.out.println("Please choose a task type: ");
                                System.out.println("1 - Class");
                                System.out.println("2 - Study");
                                System.out.println("3 - Sleep");
                                System.out.println("4 - Exercise");
                                System.out.println("5 - Work");
                                System.out.println("6 - Meal");

                                typeChoice = Integer.parseInt(scan.nextLine());
                                while (typeChoice < 1 || typeChoice > 6) {
                                    System.out.println("Invalid option. Please choose a valid option: ");
                                    typeChoice = Integer.parseInt(scan.nextLine());
                                }
                                switch (typeChoice) {
                                    case 1:
                                        rTask.setType("Class");
                                        System.out.println("\n*** UPDATED TASK INFO ***");
                                        System.out.println("Name: " + rTask.getName());
                                        System.out.println("Type: " + rTask.getType());
                                        System.out.println("Start time: " + rTask.getStartTime());
                                        System.out.println("Duration: " + rTask.getDuration());
                                        System.out.println("Start Date: " + rTask.getStartDate());
                                        System.out.println("End Date: " + rTask.getEndDate());
                                        System.out.println("Frequency: " + rTask.getFrequency());;
                                        break;
                                    case 2:
                                        rTask.setType("Study");
                                        System.out.println("\n*** UPDATED TASK INFO ***");
                                        System.out.println("Name: " + rTask.getName());
                                        System.out.println("Type: " + rTask.getType());
                                        System.out.println("Start time: " + rTask.getStartTime());
                                        System.out.println("Duration: " + rTask.getDuration());
                                        System.out.println("Start Date: " + rTask.getStartDate());
                                        System.out.println("End Date: " + rTask.getEndDate());
                                        System.out.println("Frequency: " + rTask.getFrequency());;
                                        break;  
                                    case 3: 
                                        rTask.setType("Sleep");
                                        System.out.println("\n*** UPDATED TASK INFO ***");
                                        System.out.println("Name: " + rTask.getName());
                                        System.out.println("Type: " + rTask.getType());
                                        System.out.println("Start time: " + rTask.getStartTime());
                                        System.out.println("Duration: " + rTask.getDuration());
                                        System.out.println("Start Date: " + rTask.getStartDate());
                                        System.out.println("End Date: " + rTask.getEndDate());
                                        System.out.println("Frequency: " + rTask.getFrequency());;
                                        break;
                                    case 4:
                                        rTask.setType("Exercise");
                                        System.out.println("\n*** UPDATED TASK INFO ***");
                                        System.out.println("Name: " + rTask.getName());
                                        System.out.println("Type: " + rTask.getType());
                                        System.out.println("Start time: " + rTask.getStartTime());
                                        System.out.println("Duration: " + rTask.getDuration());
                                        System.out.println("Start Date: " + rTask.getStartDate());
                                        System.out.println("End Date: " + rTask.getEndDate());
                                        System.out.println("Frequency: " + rTask.getFrequency());;
                                        break;
                                    case 5:
                                        rTask.setType("Work");
                                        System.out.println("\n*** UPDATED TASK INFO ***");
                                        System.out.println("Name: " + rTask.getName());
                                        System.out.println("Type: " + rTask.getType());
                                        System.out.println("Start time: " + rTask.getStartTime());
                                        System.out.println("Duration: " + rTask.getDuration());
                                        System.out.println("Start Date: " + rTask.getStartDate());
                                        System.out.println("End Date: " + rTask.getEndDate());
                                        System.out.println("Frequency: " + rTask.getFrequency());;
                                        break;      
                                    case 6:
                                        rTask.setType("Meal");
                                        System.out.println("\n*** UPDATED TASK INFO ***");
                                        System.out.println("Name: " + rTask.getName());
                                        System.out.println("Type: " + rTask.getType());
                                        System.out.println("Start time: " + rTask.getStartTime());
                                        System.out.println("Duration: " + rTask.getDuration());
                                        System.out.println("Start Date: " + rTask.getStartDate());
                                        System.out.println("End Date: " + rTask.getEndDate());
                                        System.out.println("Frequency: " + rTask.getFrequency());;
                                        break;
                                    default:
                                        break;
                                }

                                break;

                                //Edit Start Time  
                                case 3: 

                                //Delete old task
                                model.getTaskList().remove(rTask);

                                //Ask for new start time
                                float newStartTime = getStartTime();

                                //Make new task (with new start time)
                                RecurringTask recTask1 = new RecurringTask(tempName, tempType, 
                                newStartTime, tempDuration, tempStartDate, tempEndDate, tempFrequency);

                                //No overlap
                                if(!model.checkOverlap(recTask1)){
                                    model.addTask(recTask1);
                                    System.out.println("The start time has been changed successfully.");
                                    System.out.println("\n*** UPDATED TASK INFO ***");
                                    System.out.println("Name: " + recTask1.getName());
                                    System.out.println("Type: " + recTask1.getType());
                                    System.out.println("Start time: " + recTask1.getStartTime());
                                    System.out.println("Duration: " + recTask1.getDuration());
                                    System.out.println("Start Date: " + recTask1.getStartDate());
                                    System.out.println("End Date: " + recTask1.getEndDate());
                                    System.out.println("Frequency: " + recTask1.getFrequency());

                                //Overlap
                                } else {
                                    //Delete new task
                                    model.getTaskList().remove(recTask1);
                                    //Remake old task
                                    model.createRecurringTask(tempName, tempType, tempStartTime, tempDuration, tempStartDate, tempEndDate, tempFrequency);
                                    System.out.println("This start time would overlap with other tasks.");

                                }
                                    break;
                                //Edit Duration
                                case 4: 
                                    //Delete old task
                                    model.getTaskList().remove(rTask);

                                    //Ask for new duration
                                    float newDuration = getDuration();

                                    //Make new task (with new duration)
                                    RecurringTask recTask2 = new RecurringTask(tempName, tempType, 
                                    tempStartTime, newDuration, tempStartDate, tempEndDate, tempFrequency);

                                    //No overlap
                                    if(!model.checkOverlap(recTask2)){
                                        model.addTask(recTask2);
                                        System.out.println("The duration has been changed successfully.");
                                        System.out.println("\n*** UPDATED TASK INFO ***");
                                        System.out.println("Name: " + recTask2.getName());
                                        System.out.println("Type: " + recTask2.getType());
                                        System.out.println("Start time: " + recTask2.getStartTime());
                                        System.out.println("Duration: " + recTask2.getDuration());
                                        System.out.println("Start Date: " + recTask2.getStartDate());
                                        System.out.println("End Date: " + recTask2.getEndDate());
                                        System.out.println("Frequency: " + recTask2.getFrequency());

                                    //Overlap
                                    } else {
                                        //Delete new task
                                        model.getTaskList().remove(recTask2);
                                        //Remake old task
                                        model.createRecurringTask(tempName, tempType, 
                                        tempStartTime, tempDuration, tempStartDate, tempEndDate, tempFrequency);
                                        System.out.println("This duration would overlap with other tasks.");

                                    }
                                    break;

                                //Edit Start Date
                                case 5: 
                                    //Delete old task
                                    model.getTaskList().remove(rTask);

                                    //Ask for new duration
                                    int newStartDate = getStartDate();

                                    //Make new task (with new start date)
                                    RecurringTask recTask3 = new RecurringTask(tempName, tempType, 
                                    tempStartTime, tempDuration, newStartDate, tempEndDate, tempFrequency);

                                    //No overlap
                                    if(!model.checkOverlap(recTask3)){
                                        model.addTask(recTask3);
                                        System.out.println("The start date has been changed successfully.");
                                        System.out.println("\n*** UPDATED TASK INFO ***");
                                        System.out.println("Name: " + recTask3.getName());
                                        System.out.println("Type: " + recTask3.getType());
                                        System.out.println("Start time: " + recTask3.getStartTime());
                                        System.out.println("Duration: " + recTask3.getDuration());
                                        System.out.println("Start Date: " + recTask3.getStartDate());
                                        System.out.println("End Date: " + recTask3.getEndDate());
                                        System.out.println("Frequency: " + recTask3.getFrequency());

                                    //Overlap
                                    } else {
                                        //Delete new task
                                        model.getTaskList().remove(recTask3);
                                        //Remake old task
                                        model.createRecurringTask(tempName, tempType, 
                                        tempStartTime, tempDuration, tempStartDate, tempEndDate, tempFrequency);
                                        System.out.println("This start date would overlap with other tasks.");

                                    }
                                    break;

                                //Edit End Date
                                case 6:
                                    //Delete old task
                                    model.getTaskList().remove(rTask);

                                    //Ask for new end date
                                    int newEndDate = getEndDate(tempStartDate);

                                    //Make new task (with new end date)
                                    RecurringTask recTask4 = new RecurringTask(tempName, tempType, 
                                    tempStartTime, tempDuration, tempStartDate, newEndDate, tempFrequency);

                                    //No overlap
                                    if(!model.checkOverlap(recTask4)){
                                        model.addTask(recTask4);
                                        System.out.println("The end date has been changed successfully.");
                                        System.out.println("\n*** UPDATED TASK INFO ***");
                                        System.out.println("Name: " + recTask4.getName());
                                        System.out.println("Type: " + recTask4.getType());
                                        System.out.println("Start time: " + recTask4.getStartTime());
                                        System.out.println("Duration: " + recTask4.getDuration());
                                        System.out.println("Start Date: " + recTask4.getStartDate());
                                        System.out.println("End Date: " + recTask4.getEndDate());
                                        System.out.println("Frequency: " + recTask4.getFrequency());

                                    //Overlap
                                    } else {
                                        //Delete new task
                                        model.getTaskList().remove(recTask4);
                                        //Remake old task
                                        model.createRecurringTask(tempName, tempType, 
                                        tempStartTime, tempDuration, tempStartDate, tempEndDate, tempFrequency);
                                        System.out.println("This end date would overlap with other tasks.");

                                    }
                                    break;

                                //Edit Frequency
                                case 7:    
                                     //Delete old task
                                     model.getTaskList().remove(rTask);

                                     //Ask for new frequency
                                     int newFrequency = getFrequency();

                                     //Make new task (with new frequency)
                                     RecurringTask recTask5 = new RecurringTask(tempName, tempType, 
                                     tempStartTime, tempDuration, tempStartDate, tempEndDate, newFrequency);

                                    //No overlap
                                     if(!model.checkOverlap(recTask5)){
                                         model.addTask(recTask5);
                                         System.out.println("The frequency has been changed successfully.");
                                         System.out.println("\n*** UPDATED TASK INFO ***");
                                         System.out.println("Name: " + recTask5.getName());
                                         System.out.println("Type: " + recTask5.getType());
                                         System.out.println("Start time: " + recTask5.getStartTime());
                                         System.out.println("Duration: " + recTask5.getDuration());
                                         System.out.println("Start Date: " + recTask5.getStartDate());
                                         System.out.println("End Date: " + recTask5.getEndDate());
                                         System.out.println("Frequency: " + recTask5.getFrequency());;

                                    //Overlap
                                     } else {
                                         //Delete new task
                                         model.getTaskList().remove(recTask5);
                                         //Remake old task
                                         model.createRecurringTask(tempName, tempType, tempStartTime,
                                          tempDuration, tempStartDate, tempEndDate, tempFrequency);
                                         System.out.println("This frequency would overlap with other tasks.");

                                     }
                                    break;
                                default:
                                    taskType = "";
                                    break;
                            }

                        }
                        //Edit Transient Task
                        else if (myTask instanceof TransientTask) {
                            TransientTask tTask = (TransientTask)myTask;

                            //Save task info
                            String tempName = myTask.getName();
                            String tempType = tTask.getType();
                            float tempStartTime = tTask.getStartTime();
                            float tempDuration = tTask.getDuration();
                            int tempStartDate = tTask.getDate();

                            System.out.println("Date: " + tTask.getDate());
                            System.out.println("\nWhat would you like to edit? ");
                            System.out.println("1 - Name");
                            System.out.println("2 - Type");
                            System.out.println("3 - Start Time");
                            System.out.println("4 - Duration");
                            System.out.println("5 - Start Date");
                            int typeChoice = Integer.parseInt(scan.nextLine());
                            while (typeChoice < 1 || typeChoice > 5) {
                                System.out.println("Invalid option. Please choose a valid option: ");
                                typeChoice = Integer.parseInt(scan.nextLine());
                            }
                            switch (typeChoice) {

                                //Edit Name
                                case 1:
                                System.out.println("What shall the task be renamed as: ");
                                String editName = scan.nextLine(); 
                                    tTask.setName(editName);
                                    System.out.println("\n*** UPDATED TASK INFO ***");
                                    System.out.println("Name: " + tTask.getName());
                                    System.out.println("Type: " + tTask.getType());
                                    System.out.println("Start time: " + tTask.getStartTime());
                                    System.out.println("Duration: " + tTask.getDuration());
                                    System.out.println("Date: " + tTask.getDate());
                                    break;

                                //Edit Type
                                case 2:
                                System.out.println("Please choose the new task type: ");
                                System.out.println("1 - Visit");
                                System.out.println("2 - Shopping");
                                System.out.println("3 - Appointment");

                                int editType = Integer.parseInt(scan.nextLine());
                                while (editType < 1 || editType > 3) {
                                    System.out.println("Invalid option. Please choose a valid option: ");
                                    editType = Integer.parseInt(scan.nextLine());
                                    }
                                    switch (editType) {
                                        case 1:
                                            tTask.setType("Visit");
                                            System.out.println("\n*** UPDATED TASK INFO ***");
                                            System.out.println("Name: " + tTask.getName());
                                            System.out.println("Type: " + tTask.getType());
                                            System.out.println("Start time: " + tTask.getStartTime());
                                            System.out.println("Duration: " + tTask.getDuration());
                                            System.out.println("Date: " + tTask.getDate());
                                            break;
                                        case 2:
                                            tTask.setType("Shopping");
                                            System.out.println("\n*** UPDATED TASK INFO ***");
                                            System.out.println("Name: " + tTask.getName());
                                            System.out.println("Type: " + tTask.getType());
                                            System.out.println("Start time: " + tTask.getStartTime());
                                            System.out.println("Duration: " + tTask.getDuration());
                                            System.out.println("Date: " + tTask.getDate());
                                            break;  
                                        case 3: 
                                            tTask.setType("Appointment");
                                            System.out.println("\n*** UPDATED TASK INFO ***");
                                            System.out.println("Name: " + tTask.getName());
                                            System.out.println("Type: " + tTask.getType());
                                            System.out.println("Start time: " + tTask.getStartTime());
                                            System.out.println("Duration: " + tTask.getDuration());
                                            System.out.println("Date: " + tTask.getDate());
                                            break;
                                        default:
                                            break;
                                        }

                                break;

                                //Edit Start Time  
                                case 3: 
                                     //Delete old task
                                     model.getTaskList().remove(tTask);

                                     //Ask for new start time
                                     float newStartTime = getStartTime();

                                     //Make new task (with new start time)
                                     TransientTask traTask1 = new TransientTask(tempName, tempType, 
                                     newStartTime, tempDuration, tempStartDate);

                                     //No overlap
                                     if(!model.checkOverlap(traTask1)){
                                         model.addTask(traTask1);
                                         System.out.println("The start time has been changed successfully.");
                                         System.out.println("\n*** UPDATED TASK INFO ***");
                                         System.out.println("Name: " + traTask1.getName());
                                         System.out.println("Type: " + traTask1.getType());
                                         System.out.println("Start time: " + traTask1.getStartTime());
                                         System.out.println("Duration: " + traTask1.getDuration());
                                         System.out.println("Date: " + traTask1.getDate());

                                    //Overlap
                                     } else {
                                         //Delete new task
                                         model.getTaskList().remove(traTask1);
                                         //Remake old task
                                         model.createTransientTask(tempName, tempType,
                                          tempStartTime, tempDuration, tempStartDate);
                                         System.out.println("This start time would overlap with other tasks.");
                                     }
                                break;

                                //Edit Duration
                                case 4: 
                                    //Delete old task
                                    model.getTaskList().remove(tTask);

                                    //Ask for new duration
                                    float newDuration = getDuration();

                                    //Make new task (with new duration)
                                    TransientTask traTask2 = new TransientTask(tempName, tempType, 
                                    tempStartTime, newDuration, tempStartDate);

                                    //No overlap
                                    if(!model.checkOverlap(traTask2)){
                                        model.addTask(traTask2);
                                        System.out.println("The duration has been changed successfully.");
                                        System.out.println("\n*** UPDATED TASK INFO ***");
                                        System.out.println("Name: " + traTask2.getName());
                                        System.out.println("Type: " + traTask2.getType());
                                        System.out.println("Start time: " + traTask2.getStartTime());
                                        System.out.println("Duration: " + traTask2.getDuration());
                                        System.out.println("Date: " + traTask2.getDate());

                                    //Overlap
                                    } else {
                                        //Delete new task
                                        model.getTaskList().remove(traTask2);
                                        //Remake old task
                                        model.createTransientTask(tempName, tempType, 
                                        tempStartTime, tempDuration, tempStartDate);
                                        System.out.println("This duration would overlap with other tasks.");
                                    }
                                    break;

                                //Edit Start Date
                                case 5: 
                                    //Delete old task
                                    model.getTaskList().remove(tTask);

                                    //Ask for new start date
                                    int newStartDate = getStartDate();

                                    //Make new task (with new start date)
                                    TransientTask traTask3 = new TransientTask(tempName, tempType,
                                     tempStartTime, tempDuration, newStartDate);

                                    //No overlap
                                    if(!model.checkOverlap(traTask3)){
                                        model.addTask(traTask3);
                                        System.out.println("The start date has been changed successfully.");
                                        System.out.println("\n*** UPDATED TASK INFO ***");
                                        System.out.println("Name: " + traTask3.getName());
                                        System.out.println("Type: " + traTask3.getType());
                                        System.out.println("Start time: " + traTask3.getStartTime());
                                        System.out.println("Duration: " + traTask3.getDuration());
                                        System.out.println("Date: " + traTask3.getDate());

                                    //Overlap
                                    } else {
                                        //Delete new task
                                        model.getTaskList().remove(traTask3);
                                        //Remake old task
                                        model.createTransientTask(tempName, tempType, 
                                        tempStartTime, tempDuration, tempStartDate);
                                        System.out.println("This start date would overlap with other tasks.");
                                    }
                                default:
                                    taskType = "";
                                    break;
                            }
                        }
                        //Edit Anti-Task
                        else {
                            AntiTask aTask = (AntiTask)myTask;
                            String tempName = myTask.getName();
                            String tempType = aTask.getType();
                            float tempStartTime = aTask.getStartTime();
                            float tempDuration = aTask.getDuration();
                            int tempStartDate = aTask.getDate();
                            System.out.println("Date: " + aTask.getDate());
                            System.out.println("What would you like to edit? ");
                            System.out.println("1 - Name");
                            System.out.println("2 - Start Time");
                            System.out.println("3 - Duration");
                            System.out.println("4 - Date");
                            int typeChoice = Integer.parseInt(scan.nextLine());
                            while (typeChoice < 1 || typeChoice > 4) {
                                System.out.println("Invalid option. Please choose a valid option: ");
                                typeChoice = Integer.parseInt(scan.nextLine());
                            }
                            switch (typeChoice) {
                                //Edit Name
                                case 1:
                                    System.out.println("What shall the task be renamed as: ");
                                    String editName = scan.nextLine(); 
                                    aTask.setName(editName);
                                    System.out.println("\n*** UPDATED TASK INFO ***");
                                    System.out.println("Name: " + aTask.getName());
                                    System.out.println("Type: " + aTask.getType());
                                    System.out.println("Start time: " + aTask.getStartTime());
                                    System.out.println("Duration: " + aTask.getDuration());
                                    System.out.println("Date: " + aTask.getDate());
                                    break;

                                //Edit Start Time  (same check as delete) also need to check if new start time is valid
                                case 2: 
                                    //Delete old task
                                    model.getTaskList().remove(aTask);

                                    //Ask for new start time
                                    float newStartTime = getStartTime();

                                    //Make new task (with new start time)
                                    AntiTask antTask1 = new AntiTask(tempName, tempType,
                                     newStartTime, tempDuration, tempStartDate);

                                    //No overlap
                                    if(!model.checkOverlap(antTask1)){
                                        model.addTask(antTask1);
                                        System.out.println("The start time has been changed successfully.");
                                        System.out.println("\n*** UPDATED TASK INFO ***");
                                        System.out.println("Name: " + antTask1.getName());
                                        System.out.println("Type: " + antTask1.getType());
                                        System.out.println("Start time: " + antTask1.getStartTime());
                                        System.out.println("Duration: " + antTask1.getDuration());
                                        System.out.println("Date: " + antTask1.getDate());

                                    //Overlap
                                    } else {
                                        //Delete new task
                                        model.getTaskList().remove(antTask1);
                                        //Remake old task
                                        model.createAntiTask(tempName, tempType,
                                         tempStartTime, tempDuration, tempStartDate);
                                        System.out.println("This start time would overlap with other tasks.");
                                    }
                                    break;

                                //Edit Duration
                                case 3: 
                                    //Delete old task
                                    model.getTaskList().remove(aTask);

                                    //Ask for new duration
                                    float newDuration = getDuration();

                                    //Make new task (with new duration)
                                    AntiTask antTask2 = new AntiTask(tempName, tempType,
                                     tempStartTime, newDuration, tempStartDate);

                                    //No overlap
                                    if(!model.checkOverlap(antTask2)){
                                        model.addTask(antTask2);
                                        System.out.println("The duration has been changed successfully.");
                                        System.out.println("\n*** UPDATED TASK INFO ***");
                                        System.out.println("Name: " + antTask2.getName());
                                        System.out.println("Type: " + antTask2.getType());
                                        System.out.println("Start time: " + antTask2.getStartTime());
                                        System.out.println("Duration: " + antTask2.getDuration());
                                        System.out.println("Date: " + antTask2.getDate());

                                    //Overlap
                                    } else {
                                        //Delete new task
                                        model.getTaskList().remove(antTask2);
                                        //Remake old task
                                        model.createAntiTask(tempName, tempType, 
                                        tempStartTime, tempDuration, tempStartDate);
                                        System.out.println("This duration would overlap with other tasks.");
                                    }
                                    break;

                                //Edit Start Date
                                case 4: 
                                    //Delete old task
                                    model.getTaskList().remove(aTask);

                                    //Ask for new start date
                                    int newDate = getStartDate();

                                    //Make new task (with new start date)
                                    AntiTask antTask3 = new AntiTask(tempName, tempType, 
                                    tempStartTime, tempDuration, newDate);

                                    //No overlap
                                    if(!model.checkOverlap(antTask3)){
                                        model.addTask(antTask3);
                                        System.out.println("The date has been changed successfully.");
                                        System.out.println("\n*** UPDATED TASK INFO ***");
                                        System.out.println("Name: " + antTask3.getName());
                                        System.out.println("Type: " + antTask3.getType());
                                        System.out.println("Start time: " + antTask3.getStartTime());
                                        System.out.println("Duration: " + antTask3.getDuration());
                                        System.out.println("Date: " + antTask3.getDate());

                                    //Overlap
                                    } else {
                                        //Delete new task
                                        model.getTaskList().remove(antTask3);
                                        //Remake old task
                                        model.createAntiTask(tempName, tempType, 
                                        tempStartTime, tempDuration, tempStartDate);
                                        System.out.println("This date would overlap with other tasks.");
                                    }
                                default:
                                    taskType = "";
                                    break;
                                }
                        }   
                    }
                    //Non existed task name
                    else {
                        System.out.println("Task name does not exist. Please try again.");
                    }
                    break;
                // Write schedule
                case 5:
                    //boolean FileExists = false;
                    System.out.println("Please choose an option on what you want written to a file:");

                    System.out.println("\n1 - Write entire schedule to file");
                    System.out.println("2 - Write a day's schedule to file");
                    System.out.println("3 - Write a week's schedule to file");
                    System.out.println("4 - Write a month's schedule to file");

                    System.out.println("\nEnter your choice: ");

                    int userChoice = Integer.parseInt(scan.nextLine());

                    while (userChoice != 1 && userChoice != 2 && userChoice !=3 && userChoice !=4)
                    {
                        System.out.println("Invalid option. Please choose a valid option: ");
                        userChoice = Integer.parseInt(scan.nextLine());
                    }   // end while

                    if (userChoice == 1)
                    {
                        try
                        {
                            writeToFile(scan, model);   // pass in scanner and model
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else if (userChoice == 2)
                    {
                        System.out.println("Please enter a date in the format YYYYMMDD and tasks from that date will be written to file: ");
                        int date = Integer.parseInt(scan.nextLine());

                        while (date < 0)    // user inputs invalid date
                        {
                            System.out.println("Invalid input. Please try again: ");
                            date = Integer.parseInt(scan.nextLine());
                        }

                        Model filteredModel = new Model();  // initialize new model

                        for (Task task: model.getTaskList())
                        {
                            if (task instanceof RecurringTask)
                            {
                                RecurringTask rTask = (RecurringTask) task;

                                if (rTask.getStartDate() == date)   // if recurring task's date equals user's selected date
                                {
                                    filteredModel.createRecurringTask(rTask.getName(), rTask.getType(), rTask.getStartTime(), rTask.getDuration(), rTask.getStartDate(), rTask.getEndDate(), rTask.getFrequency()); // add recurring task to filtered model
                                }
                            }
                            else if (task instanceof AntiTask)
                            {
                                AntiTask aTask = (AntiTask) task;

                                if (aTask.getDate() == date)   // if antitask's date equals user's selected date
                                {
                                    filteredModel.createAntiTask(aTask.getName(), aTask.getType(), aTask.getStartTime(), aTask.getDuration(), aTask.getDate()); // add antitask to filtered model
                                }
                            }
                            else if (task instanceof TransientTask)
                            {
                                TransientTask tTask = (TransientTask) task;

                                if (tTask.getDate() == date)   // if transient task's date equals user's selected date
                                {
                                    filteredModel.createTransientTask(tTask.getName(), tTask.getType(), tTask.getStartTime(), tTask.getDuration(), tTask.getDate()); // add transient task to filtered model
                                }
                            }
                        }

                        if (filteredModel.getTaskList().size() != 0)    // if filteredModel size is not 0 (tasks with given date were found)
                        {
                            try
                            {
                                writeToFile(scan, filteredModel);   // write tasks contained in filtered model to file
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        else    // task with given date were not found
                        {
                            System.out.println("There exists no tasks with the given date to write to file.");
                        }

                    }   // end user choice 2
                    else if (userChoice == 3)
                    {
                        System.out.println("Please enter a date in the format YYYYMMDD that will be the first day of the week: ");
                        int userDate = Integer.parseInt(scan.nextLine());

                        while (userDate < 0)    // user inputs invalid date
                        {
                            System.out.println("Invalid input. Please try again: ");
                            userDate = Integer.parseInt(scan.nextLine());
                        }

                        String userStrDate = Integer.toString(userDate);    // converts user-inputted date to string
                        int year = Integer.parseInt(userStrDate.substring(0, 4));   // extract year from date using substring method
                        int month = Integer.parseInt(userStrDate.substring(4, 6));
                        int day = Integer.parseInt(userStrDate.substring(6));

                        Model filteredModel = new Model();  // initialize new model

                        for (Task task: model.getTaskList())
                        {
                            if (task instanceof RecurringTask)
                            {
                                RecurringTask rTask = (RecurringTask) task;

                                String rTaskStrYear = Integer.toString(rTask.getStartDate()).substring(0, 4);
                                int rTaskYear = Integer.parseInt(rTaskStrYear);
                                String rTaskStrMonth = Integer.toString(rTask.getStartDate()).substring(4, 6);
                                int rTaskMonth = Integer.parseInt(rTaskStrMonth);
                                String rTaskStrDay = Integer.toString(rTask.getStartDate()).substring(6);
                                int rTaskDay = Integer.parseInt(rTaskStrDay);

                                if (rTaskYear == year && rTaskMonth == month && rTaskDay >= day && rTaskDay <= (day + 6))   // if recurring task's month and year equal user selected month and year and within week range
                                {
                                    filteredModel.createRecurringTask(rTask.getName(), rTask.getType(), rTask.getStartTime(), rTask.getDuration(), rTask.getStartDate(), rTask.getEndDate(), rTask.getFrequency()); // add recurring task to filtered model
                                }
                            }
                            else if (task instanceof AntiTask)
                            {
                                AntiTask aTask = (AntiTask) task;

                                String aTaskStrYear = Integer.toString(aTask.getDate()).substring(0, 4);
                                int aTaskYear = Integer.parseInt(aTaskStrYear);
                                String aTaskStrMonth = Integer.toString(aTask.getDate()).substring(4, 6);
                                int aTaskMonth = Integer.parseInt(aTaskStrMonth);
                                String aTaskStrDay = Integer.toString(aTask.getDate()).substring(6);
                                int aTaskDay = Integer.parseInt(aTaskStrDay);


                                if (aTaskYear == year && aTaskMonth == month && aTaskDay >= day && aTaskDay <= (day + 6))
                                {
                                    filteredModel.createAntiTask(aTask.getName(), aTask.getType(), aTask.getStartTime(), aTask.getDuration(), aTask.getDate()); // add antitask to filtered model
                                }
                            }
                            else if (task instanceof TransientTask)
                            {
                                TransientTask tTask = (TransientTask) task;

                                String tTaskStrYear = Integer.toString(tTask.getDate()).substring(0, 4);
                                int tTaskYear = Integer.parseInt(tTaskStrYear);
                                String tTaskStrMonth = Integer.toString(tTask.getDate()).substring(4, 6);
                                int tTaskMonth = Integer.parseInt(tTaskStrMonth);
                                String tTaskStrDay = Integer.toString(tTask.getDate()).substring(6);
                                int tTaskDay = Integer.parseInt(tTaskStrDay);

                                if (tTaskYear == year && tTaskMonth == month && tTaskDay >= day && tTaskDay <= (day + 6))   // if transient task's date equals user's selected date
                                {
                                    filteredModel.createTransientTask(tTask.getName(), tTask.getType(), tTask.getStartTime(), tTask.getDuration(), tTask.getDate()); // add transient task to filtered model
                                }
                            }
                        }   // end for loop

                        if (filteredModel.getTaskList().size() != 0)    // if filteredModel size is not 0
                        {
                            try
                            {
                                writeToFile(scan, filteredModel);   // write tasks contained in filtered model to file
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        else    // task with given date were not found
                        {
                            System.out.println("There exists no tasks with the given date to write to file.");
                        }
                    }   // end user choice 3
                    else if (userChoice == 4)
                    {
                        System.out.println("Please enter a year for the month you want written to file:");
                        int userYear = Integer.parseInt(scan.nextLine());

                        while (userYear < 0)    // user inputs invalid date
                        {
                            System.out.println("Invalid input. Please try again: ");
                            userYear = Integer.parseInt(scan.nextLine());
                        }

                        System.out.println("Please enter a month:");
                        int userMonth = Integer.parseInt(scan.nextLine());

                        while (userMonth < 1 || userMonth > 12)    // user inputs invalid date outside of range of 1-12
                        {
                            System.out.println("Invalid input. Please input a month within range of 1-12: ");
                            userMonth = Integer.parseInt(scan.nextLine());
                        }

                        Model filteredModel = new Model();  // initialize new model

                        for (Task task: model.getTaskList())
                        {
                            if (task instanceof RecurringTask)
                            {
                                RecurringTask rTask = (RecurringTask) task;

                                String rTaskStrYear = Integer.toString(rTask.getStartDate()).substring(0, 4);
                                int rTaskYear = Integer.parseInt(rTaskStrYear);
                                String rTaskStrMonth = Integer.toString(rTask.getStartDate()).substring(4, 6);
                                int rTaskMonth = Integer.parseInt(rTaskStrMonth);

                                if (rTaskYear == userYear && rTaskMonth == userMonth)   // if recurring task's month and year equal user selected month and year
                                {
                                    filteredModel.createRecurringTask(rTask.getName(), rTask.getType(), rTask.getStartTime(), rTask.getDuration(), rTask.getStartDate(), rTask.getEndDate(), rTask.getFrequency()); // add recurring task to filtered model
                                }
                            }
                            else if (task instanceof AntiTask)
                            {
                                AntiTask aTask = (AntiTask) task;

                                String aTaskStrYear = Integer.toString(aTask.getDate()).substring(0, 4);
                                int aTaskYear = Integer.parseInt(aTaskStrYear);
                                String aTaskStrMonth = Integer.toString(aTask.getDate()).substring(4, 6);
                                int aTaskMonth = Integer.parseInt(aTaskStrMonth);

                                if (aTaskYear == userYear && aTaskMonth == userMonth)
                                {
                                    filteredModel.createAntiTask(aTask.getName(), aTask.getType(), aTask.getStartTime(), aTask.getDuration(), aTask.getDate()); // add antitask to filtered model
                                }
                            }
                            else if (task instanceof TransientTask)
                            {
                                TransientTask tTask = (TransientTask) task;

                                String tTaskStrYear = Integer.toString(tTask.getDate()).substring(0, 4);
                                int tTaskYear = Integer.parseInt(tTaskStrYear);
                                String tTaskStrMonth = Integer.toString(tTask.getDate()).substring(4, 6);
                                int tTaskMonth = Integer.parseInt(tTaskStrMonth);

                                if (tTaskYear == userYear && tTaskMonth == userMonth)   // if transient task's date equals user's selected date
                                {
                                    filteredModel.createTransientTask(tTask.getName(), tTask.getType(), tTask.getStartTime(), tTask.getDuration(), tTask.getDate()); // add transient task to filtered model
                                }
                            }
                        }   // end for loop

                        if (filteredModel.getTaskList().size() != 0)    // if filteredModel size is not 0
                        {
                            try
                            {
                                writeToFile(scan, filteredModel);   // write tasks contained in filtered model to file
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        else    // task with given date were not found
                        {
                            System.out.println("There exists no tasks with the given date to write to file.");
                        }
                    }   // end user choice 4
                    break;
                // Read schedule
                case 6:
                    System.out.println("Please enter the name of the JSON file (including .json): ");
                    String fileName = scan.nextLine();
                    JSONParser jsonParser = new JSONParser();
                    try (FileReader reader = new FileReader(fileName)) {
                        Object obj = jsonParser.parse(reader);

                        JSONArray taskList = (JSONArray) obj;

                        System.out.println("Tasks read in from file...");
                        Model tempModel = new Model();
                        boolean readOkay = true;
                        boolean finalCheck = true;
                        //taskList.forEach(tsk -> parseTaskObject( (JSONObject) tsk, tempModel));
                        for (Object tsk : taskList) {
                            if (!parseTaskObject((JSONObject) tsk, tempModel)) {
                                readOkay = false;
                            }
                        }
                        Model copyModel = model;
                        if (!readOkay) {
                            System.out.println("The json file had an error! Please recheck your file input. Rejecting all changes.");
                        } else {
                            for (Task task: tempModel.getTaskList()){
                                try {
                                    if (task instanceof RecurringTask) {
                                        RecurringTask rTask = (RecurringTask) task;
                                        copyModel.createRecurringTask(task.getName(), task.getType(), task.getStartTime(), task.getDuration(), rTask.getStartDate(), rTask.getEndDate(), rTask.getFrequency());
                                    } else if (task instanceof TransientTask) {
                                        TransientTask tTask = (TransientTask) task;
                                        copyModel.createTransientTask(task.getName(), task.getType(), task.getStartTime(), task.getDuration(), tTask.getDate());
                                    } else {
                                        AntiTask aTask = (AntiTask) task;
                                        copyModel.createAntiTask(task.getName(), task.getType(), task.getStartTime(), task.getDuration(), aTask.getDate());
                                    }
                                } catch (Exception e) {
                                    System.out.println("The json file had an error! Please recheck your file input. Rejecting all changes.");
                                    finalCheck = false;
                                }
                            }
                            if (finalCheck) {
                                model = copyModel;
                            }
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("File name does not exists. Please try again.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (org.json.simple.parser.ParseException e) {
                        throw new RuntimeException(e);
                    }

                    break;
                // View schedule
                case 7:

                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }

            System.out.print("\nPress any key to continue or E/e to exit... ");
            String option = scan.nextLine();
            if (!option.equals("E") && !option.equals("e")) {
                displayMenu();
                input = Integer.parseInt(scan.nextLine());
            }
            else {
                input = 0;
            }
        }

        if (input == 0) {
            System.out.println("*** Thank you and have a great day! ***");
        }
    }

    private static boolean parseTaskObject(JSONObject task, Model tempModel) {
        try {
            String taskName = (String) task.get("Name");
            String taskType = (String) task.get("Type");
            if (taskType.matches("Class|Study|Sleep|Exercise|Work|Meal")) {
                int taskSD = (int) (long) task.get("StartDate");
                float taskST = (float) (double) task.get("StartTime");
                float taskD = (float) (double) task.get("Duration");
                int taskED = (int) (long) task.get("EndDate");
                int taskF = (int) (long) task.get("Frequency");
                tempModel.createRecurringTask(taskName, taskType, taskST, taskD, taskSD, taskED, taskF);
                // throw exception if overlap detected within these tasks itself, reject read
            } else {
                int taskDate = (int) (long) task.get("Date");
                float taskST = (float) (double) task.get("StartTime");
                float taskD = (float) (double) task.get("Duration");
                if (taskType.equals("Cancellation")) {
                    tempModel.createAntiTask(taskName, taskType, taskST, taskD, taskDate);
                } else {
                    tempModel.createTransientTask(taskName, taskType, taskST, taskD, taskDate);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    private float getStartTime() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the start time: ");
        float startTime = Float.parseFloat(scan.nextLine());
        while (startTime < 0 || startTime > 23.75) {
            System.out.println("Invalid input. Please input a start time between 0 and 23.75: ");
            startTime = Float.parseFloat(scan.nextLine());
        }
        // Round to nearest .25
        startTime = (float) Math.ceil(startTime * 4)/4f;
        return startTime;
    }

    private float getDuration() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the duration: ");
        float duration = Float.parseFloat(scan.nextLine());
        while (duration < 0.25 || duration > 23.75) {
            System.out.println("Invalid input. Please input a start time between 0.25 and 23.75: ");
            duration = Float.parseFloat(scan.nextLine());
        }
        // Round to nearest .25
        duration = (float) Math.ceil(duration * 4)/4f;
        return duration;
    }

    private int getStartDate() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a start date in the format YYYYMMDD: ");
        int startDate = Integer.parseInt(scan.nextLine());
        while (startDate < 0) {
            System.out.println("Invalid input. Please try again: ");
            startDate = Integer.parseInt(scan.nextLine());
        }
        return startDate;
    }

    private int getEndDate(float startDate) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter an end date in the format YYYYMMDD: ");
        int endDate = Integer.parseInt(scan.nextLine());
        while (endDate < startDate) {
            System.out.println("Invalid input: End date must be after start date. Please try again: ");
            endDate = Integer.parseInt(scan.nextLine());
        }
        return endDate;
    }

    private int getFrequency() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a frequency (1-7): ");
        int frequency = Integer.parseInt(scan.nextLine());
        while (frequency < 1 || frequency > 7) {
            System.out.println("Invalid input: Frequency must be an integer between 1 and 7. Please try again: ");
            frequency = Integer.parseInt(scan.nextLine());
        }
        return frequency;
    }

    private int getDate() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a date in the format YYYYMMDD: ");
        int date = Integer.parseInt(scan.nextLine());
        while (date < 0) {
            System.out.println("Invalid input. Please try again: ");
            date = Integer.parseInt(scan.nextLine());
        }
        return date;
    }

    private static void writeToFile(Scanner scan, Model model) throws IOException   // pass scanner as parameter for writeToFile method to access
    {
        System.out.println("Please enter a name for the file you want to write the schedule to:");
        String inputFileName = scan.nextLine();  // take in user input for file name
        Path path = Paths.get("../" + inputFileName + ".json");    // goes back 1 directory and checks if a json file with that name exists there

        while(Files.exists(path))   // reprompt user for a file name if file with given name already exists
        {
            System.out.println("There already exists a json file with the name " + inputFileName + ". Please enter another filename:");
            inputFileName = scan.nextLine();  // set variable to new user-inputted file name
            path = Paths.get("../" + inputFileName + ".json"); // set path to new file name for next check
        }

        File file = new File("../" + inputFileName + ".json"); // initialize file object with given name
        PrintWriter output = new PrintWriter(new FileWriter(String.valueOf(path)));

        try
        {
            file.createNewFile(); // creates new file with given name at directory location
            System.out.println("File created at " + file.getCanonicalPath());   // print directory location of where file was created
        }   // end try
        catch (IOException e)   // if file creation fails
        {
            e.printStackTrace();
        }   // end catch

        for (Task task: model.getTaskList())
        {
            JSONObject json = new JSONObject();
            try
            {
                if (task instanceof RecurringTask)
                {
                    RecurringTask rTask = (RecurringTask) task; // cast task to RecurringTask type
                    json.put("Name", rTask.getName());
                    json.put("Type", rTask.getType());
                    json.put("StartTime", rTask.getStartTime());
                    json.put("Duration", rTask.getDuration());
                    json.put("StartDate", rTask.getStartDate());
                    json.put("EndDate", rTask.getEndDate());
                    json.put("Frequency", rTask.getFrequency());
                }
                else
                {
                    if (task instanceof AntiTask)
                    {
                        AntiTask aTask = (AntiTask) task; // cast task to AntiTask type
                        json.put("Name", aTask.getName());
                        json.put("Type", aTask.getType());
                        json.put("StartTime", aTask.getStartTime());
                        json.put("Duration", aTask.getDuration());
                        json.put("Date", aTask.getDate());
                    }
                    else if (task instanceof TransientTask)    // task is a transient task
                    {
                        TransientTask tTask = (TransientTask) task; // cast task to TransientTask type
                        json.put("Name", tTask.getName());
                        json.put("Type", tTask.getType());
                        json.put("StartTime", tTask.getStartTime());
                        json.put("Duration", tTask.getDuration());
                        json.put("Date", tTask.getDate());
                    }
                }   // end else
            }   // end try
            catch (Exception e) // if error occurs when converting to json
            {
                e.printStackTrace();
            }   // end catch

            try
            {
                output.write(json.toString());  // output task object converted to json into file
            }   // end try
            catch (Exception e)
            {
                e.printStackTrace();
            }   // end catch

        }   // end for loop

        output.close(); // save and close file

    }   // end writeToFile method

}
