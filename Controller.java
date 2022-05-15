import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.nio.file.Path;

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

                    break;
                // Write schedule
                case 5:
                    //boolean FileExists = false;
                    System.out.println("Please choose an option on what you want written to a file:");

                    System.out.println("\n1 - Write entire schedule to file");
                    System.out.println("2 - Write a given task to file");

                    System.out.println("\nEnter your choice: ");

                    int userChoice = Integer.parseInt(scan.nextLine());

                    while (userChoice != 1 && userChoice != 2)
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
                        System.out.println("test");
                    }

                    /**try
                    {

                    }
                    catch
                    {

                    }*/


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
                    } catch (ParseException e) {
                        e.printStackTrace();
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

    private static void writeToFile(Scanner scan, Model model) throws IOException   // pass scanner as parameter for writeToFile method to access
    {
        System.out.println("Please enter a name for the file you want to write the schedule to:");
        String inputFileName = scan.nextLine();  // take in user input for file name
        Path path = Paths.get("C:\\Users\\BT\\Documents\\CS 3560\\PSS-bt\\outputFiles\\" + inputFileName + ".json");    // checks directory if a json file with that name exists there

        while(Files.exists(path))   // reprompt user for a file name if file with given name already exists
        {
            System.out.println("There already exists a json file with the name " + inputFileName + ". Please enter another filename:");
            inputFileName = scan.nextLine();  // set variable to new user-inputted file name
            path = Paths.get("C:\\Users\\BT\\Documents\\CS 3560\\PSS-bt\\outputFiles\\" + inputFileName + ".json"); // set path to new file name for next check
        }

        File file = new File("C:\\Users\\BT\\Documents\\CS 3560\\PSS-bt\\outputFiles\\" + inputFileName + ".json"); // initialize file object with given name
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
                //if (task.getType().matches("Class|Study|Sleep|Exercise|Work|Meal")) // checks if task if recurring task
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
                    //if (task.getType().equals("Cancellation"))  // if task is an anti-task
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

}   // end Controller class
