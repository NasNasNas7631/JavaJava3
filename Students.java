package JavaJava3;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Students {
    private int numOfStudents;
    private ArrayList<String> studentID = new ArrayList();
    private ArrayList<String> studentSpecialty = new ArrayList();
    private ArrayList<String> studentName = new ArrayList();
    private ArrayList<String> studentGroup = new ArrayList();

    public void inputStudentDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of students:");
        numOfStudents = scanner.nextInt();

        for (int i = 0; i < numOfStudents; i++) {
            System.out.println("Enter student ID:");

            studentID.add(scanner.next());


            System.out.println("Enter student specialty:");
            String specialty = scanner.next();
            studentSpecialty.add(specialty);


            System.out.println("Enter student name:");
            String name = scanner.next();
            studentName.add(name);

            System.out.println("Enter student group:");
            String group = scanner.next();
            studentGroup.add(group);

            DBhandler.saveString(specialty, name, group);
        }
    }
    public void printOutList(){
        System.out.println("Student list:");
        System.out.format("%-15s %-20s %-20s %-15s\n", "ID", "Specialty", "Name", "Group");
        for (String name : studentName) {
            int index = this.getStudentName().indexOf(name);
            System.out.format("%-15s %-20s %-20s %-15s\n", this.getStudentID().get(index), this.getStudentSpecialty().get(index), this.getStudentName().get(index), this.getStudentGroup().get(index));
        }
    }
    public int getNumOfStudents() {
        return numOfStudents;
    }

    public void setNumOfStudents(int numOfStudents) {
        this.numOfStudents = numOfStudents;
    }

    public ArrayList<String> getStudentID() {
        return studentID;
    }

    public void setStudentID(ArrayList<String> studentID) {
        this.studentID = studentID;
    }

    public ArrayList<String> getStudentSpecialty() {
        return studentSpecialty;
    }

    public void setStudentSpecialty(ArrayList<String> studentSpecialty) {
        this.studentSpecialty = studentSpecialty;
    }

    public ArrayList<String> getStudentName() {
        return studentName;
    }

    public void setStudentName(ArrayList<String> studentName) {
        this.studentName = studentName;
    }

    public ArrayList<String> getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(ArrayList<String> studentGroup) {
        this.studentGroup = studentGroup;
    }
}

