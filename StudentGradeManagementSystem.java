import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Student {
    private String name;
    private int rollNumber;
    private int[] marks;

    public Student(String name, int rollNumber, int[] marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }

    public double calculatePercentage() {
        int totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }
        return (double) totalMarks / marks.length;
    }

    public String calculateGrade() {
        double percentage = calculatePercentage();

        if (percentage >= 90) {
            return "A";
        } else if (percentage >= 80) {
            return "B";
        } else if (percentage >= 70) {
            return "C";
        } else if (percentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public String toString() {
        return "Student{name='" + name + "', rollNumber=" + rollNumber + ", marks=" + Arrays.toString(marks) + '}';
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public int[] getMarks() {
        return marks;
    }

    public void updateMarks(int[] newMarks) {
        this.marks = newMarks;
    }
}

public class StudentGradeManagementSystem {
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudentMarks();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    viewStudentDetails();
                    break;
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Student Grade Management System");
        System.out.println("1. Add Student");
        System.out.println("2. Update Student Marks");
        System.out.println("3. Delete Student");
        System.out.println("4. View Student Details");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter roll number: ");
        int rollNumber = scanner.nextInt();

        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        int[] marks = new int[numSubjects];

        System.out.println("Enter marks for each subject:");
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
        }

        studentList.add(new Student(name, rollNumber, marks));
        System.out.println("Student added successfully!");
    }

    private static void updateStudentMarks() {
        System.out.print("Enter roll number of the student to update marks: ");
        int rollNumber = scanner.nextInt();

        boolean found = false;
        for (Student student : studentList) {
            if (student.getRollNumber() == rollNumber) {
                System.out.println("Current marks: " + Arrays.toString(student.getMarks()));

                System.out.print("Enter new marks for each subject:");
                int[] newMarks = new int[student.getMarks().length];
                for (int i = 0; i < newMarks.length; i++) {
                    System.out.print("Subject " + (i + 1) + ": ");
                    newMarks[i] = scanner.nextInt();
                }

                student.updateMarks(newMarks);
                System.out.println("Marks updated successfully!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter roll number of the student to delete: ");
        int rollNumber = scanner.nextInt();

        boolean removed = studentList.removeIf(student -> student.getRollNumber() == rollNumber);

        if (removed) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    private static void viewStudentDetails() {
        System.out.print("Enter roll number of the student to view details: ");
        int rollNumber = scanner.nextInt();

        boolean found = false;
        for (Student student : studentList) {
            if (student.getRollNumber() == rollNumber) {
                System.out.println("Student Details:\n" + student);
                System.out.println("Percentage: " + student.calculatePercentage());
                System.out.println("Grade: " + student.calculateGrade());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }
}
