import java.util.*;
import java.io.*;
import static java.lang.System.*;

class Opus{
    public static void main(String[] args) throws IOException{
        Scanner s = new Scanner(new File("opus.dat"));
        s.next();
        String Key = s.nextLine();
        ArrayList <Student> comp;
        while(s.hasNext()){
            comp = new ArrayList <Student>();
            comp.add(new Student(s.next(),s.next(),s.next(),s.next(),s.nextLine()));
            
        }
        out.println(comp.get(0).score(Key));
    }
    public static class Student {
        String first, last, school,div,answers;
        public Student(String f,String l,String s, String d,String a){
            this.first = f;
            this.last = l;
            this.school = s;
            this.div = d;
            this.answers = a;ArrayList <Student> comp
        }
        public int score(String key){
            char [] k = key.toCharArray();
            char [] y = this.answers.toCharArray();
            int score = 0;
            for(int i = 0; i < 40; i++){
                if(y[i] == 'S'){
                    continue;
                }
                else if(k[i] == y[i]){
                    score += 6;
                }
                else{
                    score -= 2;
                }
            }
            return score;
        }
}
}
