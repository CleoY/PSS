import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class mainTester
{
    private static ArrayList<Task> listOfTask = new ArrayList<Task>();
    public static void main(String[] args) throws ParseException{
        
        AntiTask aTask = new AntiTask("anti","antiType",3,2f,20220603); //existing task
        //AntiTask aTask2 = new AntiTask("anti2","antiType",5,3f,20220509);
        
        TransientTask tTask = new TransientTask("trans","transType",3,3f,20220603); //new task
        //TransientTask tTask2 = new TransientTask("trans2","transType",4,3f,20220509); //new task
        
        
        RecurringTask rTask2 = new RecurringTask("rec2","RecType",6,2f,20220515, 20220605,7);
        RecurringTask rTask = new RecurringTask("rec1","RecType",3,3f,20220501, 20220603,7); //new task
        
               
        
        System.out.println("\n");
        
        Date recStartDate2 = rTask2.getStartDateObject();
        System.out.println("Weekly rec start1: "+recStartDate2);
        Date recEndDate2 = rTask2.getEndDateObject();
        System.out.println("Weekly rec end1: "+recEndDate2);
        
        float startTime2 = rTask2.getStartTime();
        System.out.println("Weekly start time1: "+startTime2);
        float endTime2 = rTask2.getEndTime();
        System.out.println("Weekly end time1: "+endTime2);
        
        
        Date recStartDate = rTask.getStartDateObject();
        System.out.println("Weekly rec start2: "+recStartDate);
        Date recEndDate = rTask.getEndDateObject();
        System.out.println("Weekly rec end2: "+recEndDate);
        
        float startTime = rTask.getStartTime();
        System.out.println("Weekly start time2: "+startTime);
        float endTime = rTask.getEndTime();
        System.out.println("Weekly end time2: "+endTime);
        
        
        listOfTask.add(rTask2); //existing task
        boolean ovie = checkOverlap(rTask); //new task
        System.out.println("Check overlap: "+ovie);    

        
        // Date recStartDate2 = rTask2.getStartDateObject();
        // System.out.println("Weekly rec start1: "+recStartDate2);
        // Date recEndDate2 = rTask2.getEndDateObject();
        // System.out.println("Weekly rec end1: "+recEndDate2);
        
        // float startTime2 = rTask2.getStartTime();
        // System.out.println("Weekly start time1: "+startTime2);
        // float endTime2 = rTask2.getEndTime();
        // System.out.println("Weekly end time1: "+endTime2);
        
        
        // Date recStartDate2 = rTask2.getStartDateObject();
        // System.out.println("Daily rec start: "+recStartDate2);
        // Date recEndDate2 = rTask2.getEndDateObject();
        // System.out.println("Daily rec end: "+recEndDate2);
        
        // float startTime2 = rTask2.getStartTime();
        // System.out.println("Daily start time1: "+startTime2);
        // float endTime2 = rTask2.getEndTime();
        // System.out.println("Daily end time1: "+endTime2);
        
        
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
        
        
        // float tStartTime = tTask.getStartTime();
        // System.out.println("Start time1: "+tStartTime);
        // float tEndTime = tTask.getEndTime();
        // System.out.println("End time1: "+tEndTime);   
        
        // float tStartTime2 = tTask2.getStartTime();
        // System.out.println("Start time2: "+tStartTime2);
        // float tEndTime2 = tTask2.getEndTime();
        // System.out.println("End time2: "+tEndTime2);  
        
        
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
    public static boolean checkOverlap(Task newTask){
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
        float newTaskEndTime;
        float existingTaskEndTime;
        
        float newTaskDuration;
        float existingTaskDuration;
        
        int newTaskFrequency=0; //only if newTask is recurring
        int existingTaskFrequency=0; //only if existingTask is recurring
        
        //for WEEKLY RecurringTask checks
        Calendar newCalendar = Calendar.getInstance(); 
        Calendar exCalendar = Calendar.getInstance(); 
        Date newTempDate;
        Date exTempDate;
        
        int timeOverlapCounter=0;
        int dateOverlapCounter=0;
        
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
        newTaskEndDate = newTask.getEndDateObject();
        newTaskStartTime = newTask.getStartTime();
        newTaskEndTime = newTask.getEndTime();
        newTaskDuration = newTask.getDuration();
        
        for(int j=0; j<listOfTask.size(); j++){
            existingTask = listOfTask.get(j);
            dateOverlapCounter = 0;
            timeOverlapCounter = 0;
            
            newTaskFrequency = 0;
            existingTaskFrequency = 0;
            
            //get existing task's type, date(s), startTime, and duration
            existingTaskType = existingTask.getType();
            existingTaskStartDate = existingTask.getStartDateObject();
            existingTaskEndDate = existingTask.getEndDateObject();
            existingTaskStartTime = existingTask.getStartTime();
            existingTaskEndTime = existingTask.getEndTime();
            existingTaskDuration = existingTask.getDuration();
            
            //any combo of Transient and Anti-Tasks
            if(!(newTask instanceof RecurringTask) && !(existingTask instanceof RecurringTask)){
                System.out.println("Neither task is recurring");
                //check overlap of dates first
                if (newTaskStartDate.equals(existingTaskStartDate)){
                    //cases 1 and 3
                    if((newTaskStartTime <= existingTaskStartTime) 
                            && (newTaskEndTime > existingTaskStartTime)){
                        System.out.println("Case 1 or 3");
                        timeOverlapCounter++;
                    } else if((newTaskStartTime >= existingTaskStartTime) 
                            && (newTaskStartTime < existingTaskEndTime)){
                        System.out.println("Case 2 or 4");
                        timeOverlapCounter++;
                    }
                    
                    //need to check Task types before returning true
                    //cannot return false until all Tasks in listOfTask have been checked
                    if(timeOverlapCounter > 0){
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
            } else {
                System.out.println("At least one task is recurring");
                if(newTask instanceof RecurringTask){
                    newTaskFrequency = ((RecurringTask)newTask).getFrequency();
                }
                if(existingTask instanceof RecurringTask){
                    existingTaskFrequency = ((RecurringTask)existingTask).getFrequency();
                }
                
                //one or both are DAILY recurring tasks; covers ALL combos with daily
                //0+1, 1+0, or 1+1
                //1+7 or 7+1 ONLY
                if((newTaskFrequency + existingTaskFrequency <= 2) 
                        || (newTaskFrequency + existingTaskFrequency == 8)){
                    //8 = possibly 1 weekly
                    System.out.println("One or both are daily recurring");
                    //check dates
                    //checking dates for 4 standard cases accounts for 1 or 2 daily RecurringTasks
                    //dateOverlapCounter avoids repeating the same code for checking time
                    if((newTaskStartDate.compareTo(existingTaskStartDate)<=0) && (newTaskEndDate.compareTo(existingTaskStartDate) > 0)){
                        dateOverlapCounter++;
                    } else if((newTaskStartDate.compareTo(existingTaskStartDate)>=0) && (newTaskStartDate.compareTo(existingTaskEndDate) < 0)){
                        dateOverlapCounter++;
                    }
                } 
                //1-2 weekly + anti/trans; NO daily
                //0+7, 7+0, OR 7+7
                else{
                    System.out.println("1-2 weekly");
                    
                    //Initialize tempDate objects
                    newCalendar.setTime(newTaskStartDate);
                    newTempDate = newCalendar.getTime();
                    exCalendar.setTime(existingTaskStartDate);
                    exTempDate = exCalendar.getTime();
                    
                    //iterate through every instance of POSSIBLE NEW WEEKLY RecurringTask
                    while (newTempDate.compareTo(newTaskEndDate)<=0){
                        System.out.println("newTempDate: "+newTempDate);
                        //iterate through every instace of POSSIBLE existing weekly RecurringTask
                        while(exTempDate.compareTo(existingTaskEndDate)<=0){
                            System.out.println("ExTempDate: "+exTempDate);
                            if(newTempDate.compareTo(exTempDate)==0){
                                dateOverlapCounter++;
                                break;
                            }
                            
                            //increment existingTask tempDate by 1 week
                            exCalendar.add(Calendar.DATE, 7);
                            exTempDate = exCalendar.getTime();
                        }
                        
                        if(dateOverlapCounter > 0){
                            break;
                        }
                        
                        //increment newTempDate by 1 week
                        newCalendar.add(Calendar.DATE, 7);
                        newTempDate = newCalendar.getTime();
                        
                        //reset exTempDate
                        exCalendar.setTime(existingTaskStartDate);
                        exTempDate = exCalendar.getTime();
                    }
                }
                
                //check times
                if(dateOverlapCounter > 0){
                    System.out.println("The dates overlap");
                    if((newTaskStartTime <= existingTaskStartTime) && (newTaskEndTime > existingTaskStartTime)){
                        timeOverlapCounter++;
                    } else if((newTaskStartTime >= existingTaskStartTime) && (newTaskStartTime < existingTaskEndTime)){
                        timeOverlapCounter++;
                    }
                }
                
                //check types
                if(timeOverlapCounter > 0){
                    System.out.println("The times overlap; need to check types");
                    if(!(newTask instanceof AntiTask) && !(existingTask instanceof AntiTask)){
                        System.out.println("Neither is an AntiTask");
                        return true;
                    }
                }
            }
        }
        return false;
    }
}