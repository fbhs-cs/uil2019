import java.util.*;
import java.io.*;

public class Date {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("date.dat"));
        int n = in.nextInt();
        while (n-- > 0) {
            String m = "";
            String month = in.next();
            switch (month.toLowerCase()) {
            case "january":
                m = "01";
                break;
            case "february":
                m = "02";
                break;
            case "march":
                m = "03";
                break;
            case "april":
                m = "04";
                break;
            case "may":
                m = "05";
                break;
            case "june":
                m = "06";
                break;
            case "july":
                m = "07";
                break;
            case "august":
                m = "08";
                break;
            case "september":
                m = "09";
                break;
            case "october":
                m = "10";
                break;
            case "november":
                m = "11";
                break;
            case "december":
                m = "12";
                break;
            default:
                System.out.println("Invalid");
                return;

            }
            String day = in.next();
            String d = day.replace(",", "");
            int dayNum = Integer.parseInt(d);

            if (dayNum < 1 || dayNum > 31) {
                System.out.println("Invalid");
                return;
            }

            String year = in.next();
            String y = year.substring(year.length() - 2);
            int yearNum = Integer.parseInt(y);

            System.out.printf("%s/%02d/%02d\n", m, dayNum, yearNum);

        }

    }
}