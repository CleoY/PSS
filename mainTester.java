import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class mainTester
{
    public static void main(String[] args) throws ParseException{
        AntiTask aTask = new AntiTask("anti","antiType",22,0.5f,20220509);
        TransientTask tTask = new TransientTask("trans","transType",8,1f,20220508);
        RecurringTask rTask = new RecurringTask("rec1","RecType",5,3,20220427, 20220602,1);
        
        Date antiDate = aTask.getDateObject();
        System.out.println(antiDate);
        
        Date transDate = tTask.getDateObject();
        System.out.println(transDate);
        
        Date recStartDate = rTask.getStartDateObject();
        System.out.println(recStartDate);
        Date recEndDate = rTask.getEndDateObject();
        System.out.println(recEndDate);
        
        // boolean aBeforeT = antiDate.before(transDate);
        // System.out.println("AntiDate is before transDate: "+ aBeforeT);
        
        // boolean aAfterT = antiDate.after(transDate);
        // System.out.println("AntiDate is after transDate: "+ aAfterT);

        // boolean tBeforeA = transDate.before(antiDate);
        // System.out.println("TransDate is before antiDate: "+ tBeforeA);
        
        // boolean tAfterA = transDate.after(antiDate);
        // System.out.println("TransDate is after antiDate: "+ tAfterA);
        
        
        
        
        
        
        
        
        
    }
}