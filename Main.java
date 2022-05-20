import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main
{
    public static void main(String[] args) throws ParseException{
        Controller ctrl = new Controller();
        ctrl.displayMenu();
        ctrl.readInput();
    }
}
