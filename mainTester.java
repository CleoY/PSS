import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class mainTester
{
    private static ArrayList<Task> listOfTask = new ArrayList<Task>();
    public static void main(String[] args) throws ParseException{
        
        
        AntiTask aTask = new AntiTask("anti","antiType",9,4f,20220509);
        AntiTask aTask2 = new AntiTask("anti2","antiType",5,3f,20220509);
        
        
        TransientTask tTask = new TransientTask("trans","transType",8,4f,20220509); //existing task
        TransientTask tTask2 = new TransientTask("trans2","transType",4,3f,20220509); //new task
        
        
        
        RecurringTask rTask = new RecurringTask("rec1","RecType",5,3,20220427, 20220602,1);
    
        
        float tStartTime = tTask.getStartTime();
        System.out.println("Start time1: "+tStartTime);
        float tEndTime = tTask.getEndTime();
        System.out.println("End time1: "+tEndTime);   
        
        float tStartTime2 = tTask2.getStartTime();
        System.out.println("Start time2: "+tStartTime2);
        float tEndTime2 = tTask2.getEndTime();
        System.out.println("End time2: "+tEndTime2);  
        
        listOfTask.add(tTask); //existing task
        boolean ovie = checkOverlap(tTask2); //new task
        System.out.println("Check overlap: "+ovie);
        
        
        // Date antiDate = aTask.getStartDateObject();
        // System.out.println("Anti start: "+ antiDate);
        // Date antiEndDate = aTask.getEndDateObject();
        // System.out.println("Anti end: "+antiEndDate);
        
        // Date transDate = tTask.getStartDateObject();
        // System.out.println("Trans start: "+ transDate);
        // Date transEndDate = tTask.getEndDateObject();
        // System.out.println("Trans end: "+transEndDate);
        
        
        // float aStartTime = aTask.getStartTime();
        // System.out.println("Start time1: "+aStartTime);
        // float aEndTime = aTask.getEndTime();
        // System.out.println("End time1: "+aEndTime);
        
        // float aStartTime2 = aTask2.getStartTime();
        // System.out.println("Start time2: "+aStartTime2);
        // float aEndTime2 = aTask2.getEndTime();
        // System.out.println("End time2: "+aEndTime2);     
        
        
        // float startTime = rTask.getStartTime();
        // System.out.println("Start time1: "+startTime);
        
        // float endTime = rTask.getEndTime();
        // System.out.println("End time1: "+endTime);
        
        // rTask.setStartTime(22);
        // startTime = rTask.getStartTime();
        // System.out.println("Start time2: "+startTime);
        // endTime = rTask.getEndTime();
        // System.out.println("End time2: "+endTime);
        
        // rTask.setDuration(23);
        // startTime = rTask.getStartTime();
        // System.out.println("Start time3: "+startTime);
        // endTime = rTask.getEndTime();
        // System.out.println("End time3: "+endTime);
        
        
        // Date antiDate = aTask.getDateObject();
        // System.out.println(antiDate);
        
        // Date antiEndDate = aTask.getEndDateObject();
        // System.out.println(antiEndDate);
        
        // aTask.setDate(20021231);
        // antiDate = aTask.getDateObject();
        // System.out.println(antiDate);
        // antiEndDate = aTask.getEndDateObject();
        // System.out.println(antiEndDate);
        
        // Date transDate = tTask.getDateObject();
        // System.out.println(transDate);
        
        // Date transEndDate = tTask.getEndDateObject();
        // System.out.println(transEndDate);
        
        // tTask.setDate(20020101);
        // transDate = tTask.getDateObject();
        // System.out.println(transDate);
        // transEndDate = tTask.getEndDateObject();
        // System.out.println(transEndDate);
        
        // Date recStartDate = rTask.getStartDateObject();
        // System.out.println(recStartDate);
        // Date recEndDate = rTask.getEndDateObject();
        // System.out.println(recEndDate);
        
        
        
        // boolean equalCheck = antiDate.equals(transDate);
        // System.out.println("Equality check: "+equalCheck);
        
        // boolean aBeforeT = antiDate.before(transDate);
        // System.out.println("AntiDate is before transDate: "+ aBeforeT);
        
        // boolean aAfterT = antiDate.after(transDate);
        // System.out.println("AntiDate is after transDate: "+ aAfterT);

        // boolean tBeforeA = transDate.before(antiDate);
        // System.out.println("TransDate is before antiDate: "+ tBeforeA);
        
        // boolean tAfterA = transDate.after(antiDate);
        // System.out.println("TransDate is after antiDate: "+ tAfterA);
        
        // Date recStartDate = rTask.getStartDateObject();
        // System.out.println(recStartDate);
        // Date recEndDate = rTask.getEndDateObject();
        // System.out.println(recEndDate);
        
        // rTask.setStartDate(20000114);
        // recStartDate = rTask.getStartDateObject();
        // System.out.println(recStartDate);
        
        // rTask.setEndDate(20000115);
        // recEndDate = rTask.getEndDateObject();
        // System.out.println(recEndDate);
        
        
        
        
        
        
        
    }
    private static boolean checkOverlap(Task newTask){
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
}