import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

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
                System.out.println("Please enter the start time: ");
                float startTime = Float.parseFloat(scan.nextLine());
                while (startTime < 0 || startTime > 23.75) {
                    System.out.println("Invalid input. Please input a start time between 0 and 23.75: ");
                    startTime = Float.parseFloat(scan.nextLine());
                }
                // Round to nearest .25
                startTime = (float) Math.ceil(startTime * 4)/4f;
                
                // Get duration
                System.out.println("Please enter the duration: ");
                float duration = Float.parseFloat(scan.nextLine());
                while (duration < 0.25 || duration > 23.75) {
                    System.out.println("Invalid input. Please input a start time between 0.25 and 23.75: ");
                    duration = Float.parseFloat(scan.nextLine());
                } 
                // Round to nearest .25
                duration = (float) Math.ceil(duration * 4)/4f;
                
                if (choice == 1) {
                    // Get start date
                    System.out.println("Please enter a start date in the format MMDDYYYY: ");
                    int startDate = Integer.parseInt(scan.nextLine());
                    // Need more check
                    while (startDate < 0) {
                        System.out.println("Invalid input. Please try again: ");
                        startDate = Integer.parseInt(scan.nextLine());
                    }
                    
                    
                    // Get end date
                    System.out.println("Please enter a start date in the format MMDDYYYY: ");
                    int endDate = Integer.parseInt(scan.nextLine());
                    while (endDate < startDate) {
                        System.out.println("Invalid input: End date must be after start date. Please try again: ");
                        endDate = Integer.parseInt(scan.nextLine());
                    }
                    
                    // Get frequency
                    System.out.println("Please enter a frequency (1-7): ");
                    int frequency = Integer.parseInt(scan.nextLine());
                    while (frequency < 1 || frequency > 7) {
                        System.out.println("Invalid input: Frequency must be an integer between 1 and 7. Please try again: ");
                        frequency = Integer.parseInt(scan.nextLine());
                    }
                    
                    if (model.createRecurringTask(name, taskType, startTime, duration, startDate, endDate, frequency)) {
                        System.out.println("Sucessfully created recurring task.");
                    }
                    else {
                        System.out.println("Cannot create recurring task. Please check inputs and try again.");
                    }
                }
                else if (choice == 2) {
                    // Get date
                    System.out.println("Please enter a date in the format MMDDYYYY: ");
                    int date = Integer.parseInt(scan.nextLine());
                    while (date < 0) {
                        System.out.println("Invalid input. Please try again: ");
                        date = Integer.parseInt(scan.nextLine());
                    }
                    
                    if (model.createTransientTask(name, taskType, startTime, duration, date)) {
                        System.out.println("Sucessfully created transient task.");
                    }
                    else {
                        System.out.println("Cannot create transient task. Please check inputs and try again.");
                    }
                }
                else {
                    // Get date
                    System.out.println("Please enter a date in the format MMDDYYYY: ");
                    int date = Integer.parseInt(scan.nextLine());
                    while (date < 0) {
                        System.out.println("Invalid input. Please try again: ");
                        date = Integer.parseInt(scan.nextLine());
                    }
                    
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
                System.out.println("(Edit)Please enter the name of task: ");
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
                                rTask.name = editName;
                                break;
                            //Edit Type
                            case 2:
                            System.out.println("Please choose the new task type: ");
                            System.out.println("1 - Visit");
                            System.out.println("2 - Shopping");
                            System.out.println("3 - Appointment");
                            
                            int editType = Integer.parseInt(scan.nextLine());
                            while (editType < 1 || editType > 6) {
                                System.out.println("Invalid option. Please choose a valid option: ");
                                editType = Integer.parseInt(scan.nextLine());
                                }
                                switch (editType) {
                                    case 1:
                                        rTask.type = "Visit";
                                        break;
                                    case 2:
                                        rTask.type = "Shopping";
                                        break;  
                                    case 3: 
                                        rTask.type = "Appointment";
                                        break;
                                    default:
                                        break;
                                    }
                                
                            break;
                            //Edit Start Time  
                            case 3: 
                                System.out.println("Please enter the start time: ");
                                float editStartTime = Float.parseFloat(scan.nextLine());
                                while (editStartTime < 0 || editStartTime > 23.75) {
                                    System.out.println("Invalid input. Please input a start time between 0 and 23.75: ");
                                    editStartTime = Float.parseFloat(scan.nextLine());
                                }
                                // Round to nearest .25
                                editStartTime = (float) Math.ceil(editStartTime * 4)/4f;
                                rTask.startTime = editStartTime;
                                break;
                            //Edit Duration
                            case 4: 
                                System.out.println("Please enter the duration: ");
                                float editDuration = Float.parseFloat(scan.nextLine());
                                while (editDuration < 0.25 || editDuration > 23.75) {
                                    System.out.println("Invalid input. Please input a start time between 0.25 and 23.75: ");
                                    duration = Float.parseFloat(scan.nextLine());
                                } 
                                // Round to nearest .25
                                editDuration = (float) Math.ceil(editDuration * 4)/4f;
                                rTask.duration = editDuration;
                                break;
                            //Edit Start Date
                            case 5: 
                                System.out.println("Please enter a start date in the format MMDDYYYY: ");
                                int editStartDate = Integer.parseInt(scan.nextLine());
                                while (editStartDate < 0) {
                                    System.out.println("Invalid input. Please try again: ");
                                    editStartDate = Integer.parseInt(scan.nextLine());
                                }
                                rTask.setStartDate(editStartDate);
                                break;
                            //Edit End Date
                            case 6: 
                                System.out.println("Please enter a end date in the format MMDDYYYY: ");
                                int endDate = Integer.parseInt(scan.nextLine());
                                while (endDate < rTask.getStartDate()) {
                                    System.out.println("Invalid input: End date must be after start date. Please try again: ");
                                    endDate = Integer.parseInt(scan.nextLine());
                                }
                                rTask.setEndDate(endDate);
                                break;
                            //Edit Frequency
                            case 7: 
                                System.out.println("Please enter a frequency (1-7): ");
                                int newFrequency = Integer.parseInt(scan.nextLine());
                                while (newFrequency < 1 || newFrequency > 7) {
                                    System.out.println("Invalid input: Frequency must be an integer between 1 and 7. Please try again: ");
                                    newFrequency = Integer.parseInt(scan.nextLine());
                                }
                                rTask.setFrequency(newFrequency);
                                break;
                            default:
                                taskType = "";
                                break;
                        }
                        System.out.println("\n*** UPDATED TASK INFO ***");
                        System.out.println("Name: " + myTask.getName());
                        System.out.println("Type: " + myTask.getType());
                        System.out.println("Start time: " + myTask.getStartTime());
                        System.out.println("Duration: " + myTask.getDuration());
                        System.out.println("Start Date: " + rTask.getStartDate());
                        System.out.println("End Date: " + rTask.getEndDate());
                        System.out.println("Frequency: " + rTask.getFrequency());
                    }
                    //Edit Transient Task
                    else if (myTask instanceof TransientTask) {
                        TransientTask tTask = (TransientTask)myTask;
                        System.out.println("Date: " + tTask.getDate());
                        System.out.println("What would you like to edit? ");
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
                                tTask.name = editName;
                                break;
                            //Edit Type
                            case 2:
                            System.out.println("Please choose the new task type: ");
                            System.out.println("1 - Visit");
                            System.out.println("2 - Shopping");
                            System.out.println("3 - Appointment");
                            
                            int editType = Integer.parseInt(scan.nextLine());
                            while (editType < 1 || editType > 6) {
                                System.out.println("Invalid option. Please choose a valid option: ");
                                editType = Integer.parseInt(scan.nextLine());
                                }
                                switch (editType) {
                                    case 1:
                                        tTask.type = "Visit";
                                        break;
                                    case 2:
                                        tTask.type = "Shopping";
                                        break;  
                                    case 3: 
                                        tTask.type = "Appointment";
                                        break;
                                    default:
                                        break;
                                    }
                                
                            break;
                            //Edit Start Time  
                            case 3: 
                                System.out.println("Please enter the start time: ");
                                float editStartTime = Float.parseFloat(scan.nextLine());
                                while (editStartTime < 0 || editStartTime > 23.75) {
                                    System.out.println("Invalid input. Please input a start time between 0 and 23.75: ");
                                    editStartTime = Float.parseFloat(scan.nextLine());
                                }
                                // Round to nearest .25
                                editStartTime = (float) Math.ceil(editStartTime * 4)/4f;
                                tTask.startTime = editStartTime;
                            break;
                            //Edit Duration
                            case 4: 
                                System.out.println("Please enter the duration: ");
                                float editDuration = Float.parseFloat(scan.nextLine());
                                while (editDuration < 0.25 || editDuration > 23.75) {
                                    System.out.println("Invalid input. Please input a start time between 0.25 and 23.75: ");
                                    duration = Float.parseFloat(scan.nextLine());
                                } 
                                // Round to nearest .25
                                editDuration = (float) Math.ceil(editDuration * 4)/4f;
                                tTask.duration = editDuration;
                                break;
                            //Edit Start Date
                            case 5: 
                                System.out.println("Please enter a start date in the format MMDDYYYY: ");
                                int editStartDate = Integer.parseInt(scan.nextLine());
                                // Need more check
                                while (editStartDate < 0) {
                                    System.out.println("Invalid input. Please try again: ");
                                    editStartDate = Integer.parseInt(scan.nextLine());
                                }
                                tTask.setDate(editStartDate);
                            default:
                                taskType = "";
                                break;
                        }
                        System.out.println("\n*** UPDATED TASK INFO ***");
                        System.out.println("Name: " + myTask.getName());
                        System.out.println("Type: " + myTask.getType());
                        System.out.println("Start time: " + myTask.getStartTime());
                        System.out.println("Duration: " + myTask.getDuration());
                        System.out.println("Date: " + tTask.getDate());
                    }
                    //Edit Anti-Task
                    else {
                        AntiTask aTask = (AntiTask)myTask;
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
                                aTask.name = editName;
                                break;
                            //Edit Start Time  (same check as delete) also need to check if new start time is valid
                            case 2: 
                                System.out.println("Please enter the start time: ");
                                float editStartTime = Float.parseFloat(scan.nextLine());
                                while (editStartTime < 0 || editStartTime > 23.75) {
                                    System.out.println("Invalid input. Please input a start time between 0 and 23.75: ");
                                    editStartTime = Float.parseFloat(scan.nextLine());
                                }
                                // Round to nearest .25
                                editStartTime = (float) Math.ceil(editStartTime * 4)/4f;
                                aTask.startTime = editStartTime;
                                break;
                            //Edit Duration
                            case 3: 
                                System.out.println("Please enter the duration: ");
                                float editDuration = Float.parseFloat(scan.nextLine());
                                while (editDuration < 0.25 || editDuration > 23.75) {
                                    System.out.println("Invalid input. Please input a start time between 0.25 and 23.75: ");
                                    duration = Float.parseFloat(scan.nextLine());
                                } 
                                // Round to nearest .25
                                editDuration = (float) Math.ceil(editDuration * 4)/4f;
                                aTask.duration = editDuration;
                                break;
                            //Edit Start Date
                            case 4: 
                                System.out.println("Please enter a start date in the format MMDDYYYY: ");
                                int editStartDate = Integer.parseInt(scan.nextLine());
                                // Need more check
                                while (editStartDate < 0) {
                                    System.out.println("Invalid input. Please try again: ");
                                    editStartDate = Integer.parseInt(scan.nextLine());
                                }
                                //Checking for overlap
                                aTask.setDate(editStartDate);
                            default:
                                taskType = "";
                                break;
                            }
                        System.out.println("\n*** UPDATED TASK INFO ***");
                        System.out.println("Name: " + myTask.getName());
                        System.out.println("Type: " + myTask.getType());
                        System.out.println("Start time: " + myTask.getStartTime());
                        System.out.println("Duration: " + myTask.getDuration());
                        System.out.println("Date: " + aTask.getDate());
                    }   
                }
                //Non existed task name
                else {
                    System.out.println("Task name does not exist. Please try again.");
                }
                break;
            // Write schedule
            case 5:
            
                break;
            // Read schedule
            case 6:
                
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
}
