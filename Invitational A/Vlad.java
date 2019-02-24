import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class Vlad {

    public static class Student implements Comparable<Student> {
        String first;
        String middle;
        String last;

        public Student(String first, String middle, String last) {
            this.first = first;
            this.middle = middle;
            this.last = last;
        }

        public int compareTo(Vlad.Student other) {
            if(this.last.equals(other.last)) {
                if(this.first.equals(other.first)) {
                    return this.middle.compareTo(other.middle);
                } else {
                    return this.first.compareTo(other.first);
                }
            } else {
                return this.last.compareTo(other.last);
            }
        }

        public String toString() {
            if(this.middle.equals("")) {
                return this.first + " " + this.last;
            }
            return this.first + " " + this.middle + " " + this.last;
        }

    }


    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("vlad.dat"));
        ArrayList<Student> students = new ArrayList<Student>();
        while(in.hasNextLine()) {
            // create a new student file
            String s = in.nextLine();
            Scanner line = new Scanner(s);
            line.next(); // throw away ID number
            String last = line.next();
            last = last.substring(0,last.length()-1);
            String first = line.next();
            String middle = "";
            if(line.hasNext()) {
                middle = line.next();
                middle = middle.substring(0,1);
            }
            Student temp = new Student(first, middle, last);
            



            
            // add student file to an arrayList
            students.add(temp);


        }

        // sort the arrayList by lastname, initial, then first name
        Collections.sort(students);

        // print all elements of the arrayList
        for(Student s : students) {
            out.println(s);
        }
    }
}