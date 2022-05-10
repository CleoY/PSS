
/**
 * Write a description of class Controller here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;


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
    
    public void readInput() {
        Model model = new Model();
        Scanner scan = new Scanner(System.in);
        int input = Integer.parseInt(scan.nextLine());
        
        while (input != 0 ) {
            switch (input) {
            // Create task
            case 1:
                System.out.println("Please enter the name of task: ");
                String name = scan.nextLine();
                // check valid name here
                
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
                    // code here
                    int startDate = 0;
                    
                    
                    // Get end date
                    // code here
                    int endDate = 0;
                    
                    
                    // Get frequency
                    // code here
                    int frequency = 7;
                    model.createRecurringTask(name, taskType, startTime, duration, startDate, endDate, frequency);
                }
                else if (choice == 2) {
                    // Get date
                    // code here
                    int date = 0;
                    model.createTransientTask(name, taskType, startTime, duration, date);
                }
                else {
                    // Get date
                    // code here
                    int date = 0;
                    model.createAntiTask(name, taskType, startTime, duration, date);
                }    
                break;
            // View task
            case 2:
                System.out.println("Please enter the name of task: ");
                name = scan.nextLine(); 
                int pos = model.findTask(name);
                if (pos != -1) {
                    Task myTask = model.getTaskList().get(pos);
                    System.out.println("Name: " + myTask.getName());
                    System.out.println("Type: " + myTask.getType());
                    System.out.println("Start time: " + myTask.getStartTime());
                    System.out.println("Duration: " + myTask.getDuration());
                    // wip
                }
                else {
                    System.out.println("Task name does not exists. Please try again.");
                }
                break;
                    
            // Delete task
            case 3:
                System.out.println("Please enter the name of task: ");
                name = scan.nextLine(); 
                int pos2 = model.findTask(name);
                //Will only delete if the task exists
                if (pos2 != -1) {
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
            
            System.out.print("Press any key to continue or E/e to exit... ");
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
