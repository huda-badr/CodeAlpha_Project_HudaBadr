package codeAlpha;
import java.util.ArrayList;
import java.util.Scanner;
//Task 1:
public class StudentGradeTracker {

    private ArrayList<Integer> grades;

    public StudentGradeTracker() {
        grades = new ArrayList<>();
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }

    public double computeAverage() {
        if (grades.isEmpty()) {
            return 0.0;  
        }
        int sum = 0;
        for (int grade : grades) {
            sum += grade;  
        }
        return sum / (double) grades.size();  
    }

    public int findHighest() {
        if (grades.isEmpty()) {
            return 0;  
        }
        int highest = grades.get(0);
        for (int grade : grades) {
            if (grade > highest) {
                highest = grade;  
            }
        }
        return highest;
    }

    public int findLowest() {
        if (grades.isEmpty()) {
            return 0; 
        }
        int lowest = grades.get(0);
        for (int grade : grades) {
            if (grade < lowest) {
                lowest = grade; 
            }
        }
        return lowest;
    }

    public static void main(String[] args) {
    	// TODO Auto-generated method stub
    	
        Scanner scanner = new Scanner(System.in);
        StudentGradeTracker tracker = new StudentGradeTracker();

        System.out.println("Enter grades (enter -1 to finish):");
        while (true) {
            int grade = scanner.nextInt();
            if (grade == -1) {
                break;  
            }
            tracker.addGrade(grade); 
        }

        System.out.println("Average grade: " + tracker.computeAverage());
        System.out.println("Highest grade: " + tracker.findHighest());
        System.out.println("Lowest grade: " + tracker.findLowest());

    }
}
