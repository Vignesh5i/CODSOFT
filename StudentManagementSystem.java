import java.util.*;

class Student {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public int getRollNumber() { return rollNumber; }
    public String getName() { return name; }
    public String getGrade() { return grade; }

    @Override
    public String toString() {
        return "Roll No: " + rollNumber + " | Name: " + name + " | Grade: " + grade;
    }
}

public class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully!");
    }

    public boolean removeStudent(int rollNumber) {
        return students.removeIf(s -> s.getRollNumber() == rollNumber);
    }

    public Student searchStudent(int rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) return s;
        }
        return null;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("The list is empty. No student records found.");
        } else {
            System.out.println("\n--- All Student Records ---");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\n--- STUDENT MANAGEMENT SYSTEM ---");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Student Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Roll Number: ");
                        int roll = sc.nextInt();
                        System.out.print("Enter Grade: ");
                        String grade = sc.next();
                        
                        if (name.trim().isEmpty() || grade.trim().isEmpty()) {
                            System.out.println("Error: Name and Grade cannot be blank!");
                        } else {
                            sms.addStudent(new Student(name, roll, grade));
                        }
                        break;

                    case 2:
                        System.out.print("Enter Roll Number to remove: ");
                        int rollToRemove = sc.nextInt();
                        if (sms.removeStudent(rollToRemove)) {
                            System.out.println("Student removed successfully.");
                        } else {
                            System.out.println("Error: Student with Roll No " + rollToRemove + " not found.");
                        }
                        break;

                    case 3:
                        System.out.print("Enter Roll Number to search: ");
                        int rollToSearch = sc.nextInt();
                        Student found = sms.searchStudent(rollToSearch);
                        if (found != null) {
                            System.out.println("Search Result: " + found);
                        } else {
                            System.out.println("Error: Student not found!");
                        }
                        break;

                    case 4:
                        sms.displayAllStudents();
                        break;

                    case 5:
                        System.out.println("Closing the system. Goodbye!");
                        sc.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid option! Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
    }
}