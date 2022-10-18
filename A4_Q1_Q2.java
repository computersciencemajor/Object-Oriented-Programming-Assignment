package assignment5;


/**
 * @department Gina Cody School of Engineering and Computer Science
 * @term Winter-2022 Term. 
 * @title Assignment4 Q1_Q2.
 * @author Jason Achkar Diab.
 * @StudentID: 40227239.
 * @course COMP248.
 * @scope SectionU - W2022. 
 * @date 23/3/2022.
 * @Purpose: This program helps the user manage their Classroom.
 */

/*
 * A Simple Program that allows the user to manage a Classroom.
 * The user can add Students by Entering their First name, Last name and assign their Student ID.
 * Remove Students by Simply Entering their assigned Student ID.
 * Assign the corresponding grades for every Student in the Class.
 * The Program checks the integrity of the user inputs (For example: If a Student is not in the Class, the program tells the user that the Student does not exist)
 * The user can check the Position of the Student in the Class by simply entering the Student's ID.
 * The user can Update the Particulars of a Student as well as their Scores by simply entering the updated information followed by the Student's ID. 
 * The user can also access the Report cards of every Student Individually
   as well as, the Report card for the whole class which also shows the Minimum
   Maximum and Average grades of the Class for every Assignment, Lab and Exam. 
*/
import java.util.Arrays;
import java.util.Scanner; //This import allows us to retrieve user input.

class Comp248secU {
	
	//Variable Declarations.
    static int classSize;
    static boolean found;
    static int  currentclasssize = 0;//This integer variable will be used to detect if a class is full when the user is adding students.
    int currentclassposition = 0;
    //These constants are the maximum scores of the Assignments, labs, MidTerm and Final. They will be used to calculate the weighted score.
    final int maxScoreassgt = 20;
    final int maxScoreLabs = 12;
    final int maxScoreMidterm = 30;
    final int maxScoreFinal = 40;
    static double wgtScore;
    static double[] Minimum = new double[7];//This array will store the minimum score received by students.
    static double[] Maximum = new double[7];//This array will store the maximum score received by students.
    static double[] Average = new double[7];//This array will store the average of the grades received by students.
    static double MinimumScore;
    static double MaximumScore;
    static double AverageScore;
    static char LetterGrade;
    static String semesterYear;
    static String lectureRoom;
    static String instructorFname;
    static String instructorLname;
    String[] studFname = { " " };
    String[] studLname = { " " };
    private int studID[] = { 1 };
    double[] assgt1 = { 0.0 };
    double[] assgt2 = { 0.0 };
    double[] assgt3 = { 0.0 };
    double[] assgt4 = { 0.0 };
    double[] labs = { 0.0 };
    double[] midTerm = { 0.0 };
    double[] finalExam = { 0.0 };
    
    //Comp248secU Custom Constructor.
    public Comp248secU(String fname, String lname, String room, String semYr, int size) {
    	
    	//Initializing variables with the custom constructor.
        instructorFname = fname;
        instructorLname = lname;
        lectureRoom = room;
        semesterYear = semYr;
        classSize = size;
        studID = Arrays.copyOf(studID, classSize);
        studFname = Arrays.copyOf(studFname, classSize);
        studLname = Arrays.copyOf(studLname, classSize);
        assgt1 = Arrays.copyOf(assgt1, classSize);
        assgt2 = Arrays.copyOf(assgt2, classSize);
        assgt3 = Arrays.copyOf(assgt3, classSize);
        assgt4 = Arrays.copyOf(assgt4, classSize);
        finalExam = Arrays.copyOf(finalExam, classSize);
        midTerm = Arrays.copyOf(midTerm, classSize);
        labs = Arrays.copyOf(labs, classSize);

    }
   
    //This method allows the user to add a Student to the Classroom if The number of existing Students does not exceed the Class size.
    public void addStudent(String fname, String lname, int studID, int arrIdx) {
        System.out.print("Student with ID: " + studID);
        if (currentclasssize >= classSize) { 
            System.out.print(" CANNOT be added. Class is already full.\n");
        } else {
            System.out.print(" added successfully.\n");
            this.studID[currentclasssize] = studID;
            this.studFname[currentclasssize] = fname;
            this.studLname[currentclasssize] = lname;
            currentclasssize++;
        }

    }

    public int getStudentIdx(int studID) {
        int i = 0;
        Comp248secU.found = false;
        for (i = 0; i < this.studID.length; i++) {
            if (studID == this.studID[i]) { //it searches for the Student ID that corresponds to the ID passed on by the user.
                Comp248secU.found = true;
                currentclassposition = i;
                break;
            } else { // If the Student ID passed on by the user does not exist in the studID array then we set found to false.
                Comp248secU.found = false;
            }

        }
        return i;
    }
    
    //This method shows the Student information such as their Name and Grades received.
    public String[] getStudentInfo(int studID) {
        getStudentIdx(studID);
        if (Comp248secU.found == true) {
            System.out.print("Student's First Name = ");
            System.out.print(this.studFname[currentclassposition] + "\n");
            System.out.print("Student's Last Name = ");
            System.out.print(this.studLname[currentclassposition] + "\n");
            System.out.print("Student's ID = ");
            System.out.print(this.studID[currentclassposition] + "\n");
            System.out.print("Score in Assignment 1 = ");
            System.out.print(this.assgt1[currentclassposition] + "\n");
            System.out.print("Score in Assignment 2 = ");
            System.out.print(this.assgt2[currentclassposition] + "\n");
            System.out.print("Score in Assignment 3 = ");
            System.out.print(this.assgt3[currentclassposition] + "\n");
            System.out.print("Score in Assignment 4 = ");
            System.out.print(this.assgt4[currentclassposition] + "\n");
            System.out.print("Cummulative Score in Labs =  ");
            System.out.print(this.labs[currentclassposition] + "\n");
            System.out.print("Mid-Term Test Score = ");
            System.out.print(this.midTerm[currentclassposition] + "\n");
            System.out.print("Final Examination Score = ");
            System.out.println(this.finalExam[currentclassposition]);
        } else {
        	System.out.println("Student with ID: "+studID+" does NOT exist");
            System.out.println("Unable to retrieve information for Student with ID: " + studID);
        }

        return studFname;

    }
   
    //This Method allows the user to remove a Student from the Classroom.
    public int delStudent(int studID) {
        getStudentIdx(studID);
        if (Comp248secU.found == true) {
            for (int j = currentclassposition; j <classSize; j++) {
            	String first;
            	String last;
            	int id;
            	double a1;
            	double a2;
            	double a3;
            	double a4;
            	double lab;
            	double mid;
            	double finals;
            	//In order to delete a student we first have to shift all student information to the end of each array storing the information.
            	if (j != (classSize-1)) {
            		first=this.studFname[j];
            	   	last=this.studLname[j];
            	   	id=this.studID[j];
            	   	a1=this.assgt1[j];
            	   	a2=this.assgt2[j];
            	   	a3=this.assgt3[j];
            	   	a4=this.assgt4[j];
            	   	lab=this.labs[j];
            	   	mid=this.midTerm[j];
            	    finals=this.finalExam[j];
            		this.studFname[j] = this.studFname[j + 1];
            		this.studFname[j+1]=first;
            		this.studLname[j] = this.studLname[j + 1];
            		this.studLname[j+1]=last;
                    this.studID[j] =this.studID[j+1];
                    this.studID[j+1]=id;
                    this.assgt1[j]=this.assgt1[j+1];
                    this.assgt1[j+1]=a1;
                    this.assgt2[j]=this.assgt2[j+1];
                    this.assgt2[j+1]=a2;
                    this.assgt3[j]=this.assgt3[j+1];
                    this.assgt3[j+1]=a3;
                    this.assgt4[j]=this.assgt4[j+1];
                    this.assgt4[j+1]=a4;
                    this.midTerm[j]=this.midTerm[j+1];
                    this.midTerm[j+1]=mid;
                    this.labs[j]=this.labs[j+1];
                    this.labs[j+1]=lab;
                    this.finalExam[j]=this.finalExam[j+1];
                    this.finalExam[j+1]=finals;
                    
              /*When we finish shifting the student info to the end of each array we delete the student.
                To delete the student we set the primitive values to 0 or 0.0 and the non-primitive values to null 
                then we decrease currentclasssize by 1 because now the class has one less student.*/ 
                } else {
                	this.studFname[classSize-1]=null;
                	this.studLname[classSize-1]=null;
                	this.studID[classSize-1]=0;
                	this.assgt1[classSize-1]=0.0;
                    this.assgt2[classSize-1]=0.0;
                    this.assgt3[classSize-1]=0.0;
                    this.assgt4[classSize-1]=0.0;
                    this.labs[classSize-1]=0.0;
                    this.finalExam[classSize-1]=0.0;
                    this.midTerm[classSize-1]=0.0;
                	currentclasssize--;
                }

            }
            System.out.println("Successfully removed Student with ID: " + studID);

        }else {
        	System.out.println("Student with ID: "+studID+" does NOT exist");
            System.out.println("Unable to retrieve information for Student with ID: " + studID);
        }

        return studID;
    }
   
    //This method allows the user to Update the First and Last name of a Student.
    public int updateStudentPart(String fname, String lname, int studID) {
        getStudentIdx(studID);
        if (Comp248secU.found == true) {
            this.studFname[currentclassposition] = fname;
            this.studLname[currentclassposition] = lname;

            System.out.println("Successfully updated identification particulars for Student with ID: " + studID);
        } else {
        	System.out.println("Student with ID: "+studID+" does NOT exist");
            System.out.println("Unable to retrieve information for Student with ID: " + studID);
        }
        return studID;
    }
   
    //This method allows the user to Update the Scores of the Assignments of a Student.
    public int updateAssgtScore(double a1, double a2, double a3, double a4, int studID) {
        getStudentIdx(studID);
        if (Comp248secU.found == true) {
            this.assgt1[currentclassposition] = a1;
            this.assgt2[currentclassposition] = a2;
            this.assgt3[currentclassposition] = a3;
            this.assgt4[currentclassposition] = a4;
            System.out.println("Successfully updated Assignments' scores for Student with ID: "+studID);
        } else {
        	System.out.println("Student with ID: "+studID+" does NOT exist");
            System.out.println("Unable to retrieve information for Student with ID: " + studID);
        }
        return (int) a1;
    }
    
    //This method allows the user to Update the Scores of the Labs, Midterms and Finals of a Student.
    public int updateOtherScore(double lab, double test, double exam, int studID) {
        getStudentIdx(studID); //Checks if the student is in the Class and if they are it gets their position.
        if (Comp248secU.found == true) { //If the Student is in the Class the scores are updated respectively.
            this.labs[currentclassposition] = lab;
            this.midTerm[currentclassposition] = test;
            this.finalExam[currentclassposition] = exam;
            System.out.println("Successfully updated Cummulative Labs, Mid-Term Test, and Final Examination scores for Student with ID: "+studID);
        } else { //If the student is not in the class we print for the user that the student does not exist.
        	System.out.println("Student with ID: "+studID+" does NOT exist");
            System.out.println("Unable to retrieve information for Student with ID: " + studID);
        }
        return (int) ((lab + test + exam) / 3);
    }
    
    //This method computes the weighted Score for each Student in the Classroom.
    public double computeWeightScore(int studID) {
        getStudentIdx(studID);
        if (Comp248secU.found == true) {
            wgtScore = (((2.0 / 100) * (assgt1[currentclassposition] / maxScoreassgt)
                     + (3.0 / 100) * (assgt2[currentclassposition] / maxScoreassgt)
                     + (5.0 / 100) * (assgt3[currentclassposition] / maxScoreassgt)
                     + (8.0 / 100) * (assgt4[currentclassposition] / maxScoreassgt)
                     + (12.0 / 100) * (labs[currentclassposition] / maxScoreLabs)
                     + (30.0 / 100) * (midTerm[currentclassposition] / maxScoreMidterm)
                     + (40.0 / 100) * (finalExam[currentclassposition] / maxScoreFinal))) * 100;
        }
        return wgtScore;
    }
    
    //This method assigns a Letter Grade for each Student depending on the weighted score calculated.
    public static char computeGrade(double wgtScore) {
        if (wgtScore >= 88) {
            LetterGrade = 'A';
        } else if (wgtScore >= 80) {
            LetterGrade = 'B';
        } else if (wgtScore >= 67) {
            LetterGrade = 'C';
        } else if (wgtScore >= 60) {
            LetterGrade = 'D';
        } else {
            LetterGrade = 'F';
        }
        return LetterGrade;
    }
    
    //This method finds the Minimum grade received by a Student in a Class for each of the Assignments, Midterm, Finals and Labs.
    public static double findMin(double[] dataArr) {
    	MinimumScore=dataArr[0];
        for (int i = 1; i < currentclasssize ; i++) {
        	
            if (dataArr[i] <MinimumScore) {
                MinimumScore = dataArr[i];
            }
        }
        return MinimumScore;

    }
    
    //This method finds the Maximum grade received by a Student in a Class for each of the Assignments, Midterm, Finals and Labs.
    public static double findMax(double[] dataArr) {
    	MaximumScore=dataArr[0];
        for (int i = 1; i < currentclasssize; i++) {
        	
            if (dataArr[i] > MaximumScore) {
                MaximumScore = dataArr[i];
            } 
        }
        return MaximumScore;
    }
    
    // This method finds the average grade of the class for the Midterm, Finals, Labs or for a specific assignment.
    public static double findAvg(double[] dataArr) {
        int i = 0;
        AverageScore = 0;
        for (i = 0; i < currentclasssize; i++) {
            AverageScore += dataArr[i];
        }
        AverageScore /= i;
        return AverageScore;
    }
   
    //getClassMin gets the minimum grades received by a student for assignments, labs and Exams.
    public double[] getClassMin() {
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                findMin(assgt1);
                Minimum[i] = MinimumScore;
            } else if (i == 1) {
                findMin(assgt2);
                Minimum[i] = MinimumScore;
            } else if (i == 2) {
                findMin(assgt3);
                Minimum[i] = MinimumScore;
            } else if (i == 3) {
                findMin(assgt4);
                Minimum[i] = MinimumScore;
            } else if (i == 4) {
                findMin(labs);
                Minimum[i] = MinimumScore;
            } else if (i == 5) {
                findMin(midTerm);
                Minimum[i] = MinimumScore;
            } else if (i == 6) {
                findMin(finalExam);
                Minimum[i] = MinimumScore;
            }
        }
        return Minimum;
    }
    
    //getClassMax method uses the findMax method to find the Highest grades received by a student for assignments, labs and Exams.
    public double[] getClassMax() {
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                findMax(assgt1);
                Maximum[i] = MaximumScore;
            } else if (i == 1) {
                findMax(assgt2);
                Maximum[i] = MaximumScore;
            } else if (i == 2) {
                findMax(assgt3);
                Maximum[i] = MaximumScore;
            } else if (i == 3) {
                findMax(assgt4);
                Maximum[i] = MaximumScore;
            } else if (i == 4) {
                findMax(labs);
                Maximum[i] = MaximumScore;
            } else if (i == 5) {
                findMax(midTerm);
                Maximum[i] = MaximumScore;
            } else if (i == 6) {
                findMax(finalExam);
                Maximum[i] = MaximumScore;
            }
        }
        return Maximum;
    }
    
    //getClassAvg method uses the findAvg method to get the class average for Assignments, labs and Exams.
    public double[] getClassAvg() {
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                findAvg(assgt1);
                Average[i] = AverageScore;
            } else if (i == 1) {
                findAvg(assgt2);
                Average[i] = AverageScore;
            } else if (i == 2) {
                findAvg(assgt3);
                Average[i] = AverageScore;
            } else if (i == 3) {
                findAvg(assgt4);
                Average[i] = AverageScore;
            } else if (i == 4) {
                findAvg(labs);
                Average[i] = AverageScore;
            } else if (i == 5) {
                findAvg(midTerm);
                Average[i] = AverageScore;
            } else if (i == 6) {
                findAvg(finalExam);
                Average[i] = AverageScore;
            }
        }
        return Average;														
    }
    
    //This method displays the Report card of the Class to the user.
    public void classReportCard() {
        System.out.println("Displaying Class Report Card...");
        System.out.println("-------------------------------");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println("First Name \t Last Name \tStud. ID\t A1\t A2\t A3\t A4\tLabs\tTest\tExam\t Wgt.  *");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < currentclasssize ; i++) {
            System.out.printf("%-16s%-16s%-16d%-8.2f%-8.2f%-8.2f%-8.2f%-8.2f%-8.2f%-8.2f%-5.2f  %s",
                    studFname[i], studLname[i],
                    studID[i], assgt1[i],
                    assgt2[i], assgt3[i], assgt4[i], labs[i],
                    midTerm[i], finalExam[i], computeWeightScore(studID[i]),
                    computeGrade(Comp248secU.wgtScore));
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------");

        System.out.print("\t\tMinimum Score in Class: ");
        for (int i = 0; i < 7; i++) {
        	System.out.printf("\t%.2f", getClassMin()[i]);
        }
        System.out.println();
        System.out.print("\t\tAverage Score in Class: ");
        for (int i = 0; i < 7; i++) {
            System.out.printf("\t%.2f", getClassAvg()[i]);
        }
        System.out.println();

        System.out.print("\t\tMaximum Score in Class: ");
        for (int i = 0; i < 7; i++) {
        	System.out.printf("\t%.2f", getClassMax()[i]);
        }
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------------------");

    }

}

public class A4_Q1_Q2 {

    public static void main(String[] args) {
    	
    	//Declaring our Variables.
        Scanner scanner = new Scanner(System.in);
        int Studentid = 0;
        int MaxClassSize = 0;
        int code;
        boolean condition = true;
        String studentfirstname;
        String studentlastname;
        System.out.println("Welcome to the Simple Classroom Management System:");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();
        System.out.println("Enter instructor's particulars(FirstName, LastName, LectureRoom, Semester, MaxClassSize) as a single-line entry:");
        String firstname = scanner.next();
        String lastname = scanner.next();
        String lectureroom = scanner.next();
        String semester = scanner.next();
        
        //This if statement check if the user is entering an Integer. It returns True if they are and False if they aren't.
        if (scanner.hasNextInt() == true) { 
            MaxClassSize = scanner.nextInt();
        } else {
            System.out.println("Error: Your input/entry for 'MaxClassSize' is NOT a valid integer. Kindly retry again!");
            System.exit(0);
        }
        
        Comp248secU comp248secu = new Comp248secU(firstname, lastname, lectureroom, semester, MaxClassSize);
        
        System.out.println();
        System.out.println("Code => Description");
        System.out.println("-------------------");
        System.out.println("103 => Enrol new Student");
        System.out.println("106 => Find Student Position in Class List");
        System.out.println("109 => Retrieve Student's Information");
        System.out.println("112 => Unenrol Student");
        System.out.println("115 => Update Student's Particulars");
        System.out.println("118 => Update Assignment Scores");
        System.out.println("121 => Update Other Scores");
        System.out.println("124 => Display Student's Report Card");
        System.out.println("127 => Display Class Report Card");
        System.out.println("0 ===> Exit");
        System.out.println();
        int s=0;
        while (condition) {
        	if(s==0) {
        	System.out.print("Please enter a Code, from the aforementioned, that corresponds to your task: ");
            code = scanner.nextInt();
            s++;
        	}else {
            System.out.print("Kindly continue by entering a Code, from the menu above, that corresponds to your task: ");
            code = scanner.nextInt();
            
        	}
            
            if (code > -128) {
               if (code <= 127) {
               } else {
                    System.out.println("Error: Your input/entry is not a valid integer between -128 to 127. Kindly retry again!");
                    System.exit(0);
               	}
                if (code == 103) {
                	System.out.println();
                    System.out.println("Enrolling New Student...");
                    System.out.println("------------------------");
                    System.out.println("Enter student's particulars (FirstName, LastName, StudentID) as a single-line entry:");
                    studentfirstname = scanner.next();
                    studentlastname = scanner.next();
                    if (scanner.hasNextInt() == true) {
                        Studentid = scanner.nextInt();
                    } else {
                        System.out.println("Error: Your input/entry for 'StudentID' is NOT a valid integer. Kindly retry again!");
                        scanner.next(); //Clear scanner buffer.
                        continue;
                    }
                    comp248secu.addStudent(studentfirstname, studentlastname, Studentid, 0);
                    continue;
                }
                if (code == 106) {
                	System.out.println();
                    System.out.println("Finding Student's Position in Class List...");
                    System.out.println("-------------------------------------------");
                    System.out.println("Enter StudentID: ");
                    if (scanner.hasNextInt() == true) {
                        Studentid = scanner.nextInt();
                    } else {
                        System.out.println("Error: Your input/entry for 'StudentID' is NOT a valid integer. Kindly retry again!");
                        scanner.next(); //Clearing scanner buffer so we don't have any compilation errors.
                        continue;
                    }
                    comp248secu.getStudentIdx(Studentid);
                    if(Comp248secU.found==true) {
                    //Since in other methods we do not want to use this print Statement when calling upon the getStudentidx method we implement the print statement here.
                    System.out.println("The position of student with ID: " + Studentid + ", in the class list, is: "+ (comp248secu.currentclassposition));
                    continue;
                    }else {
                    	System.out.println("Student with ID: "+Studentid+" does not exist.");
                    	continue;
                    }
                }
                if (code == 109) {
                	System.out.println();
                    System.out.println("Retrieving Student's Information...");
                    System.out.println("-----------------------------------");
                    System.out.println("Enter StudentID:");
                    if(scanner.hasNextInt()==true) {
                    Studentid = scanner.nextInt();
                    }else {
                    	System.out.println("Error: Your input/entry for 'StudentID' is NOT a valid integer. Kindly retry again!");
                    	scanner.next(); //Clearing scanner buffer.
                    	continue;
                    }
                    comp248secu.getStudentInfo(Studentid);
                    continue;

                }
                if (code == 112) {
                	System.out.println();
                    System.out.println("Unenrolling Student...");
                    System.out.println("----------------------");
                    System.out.println("Enter StudentID:");
                    //If the user does not enter an Integer for StudentID we print Error and ask the user to try again.
                    if (scanner.hasNextInt() == true) {
                        Studentid = scanner.nextInt();
                        comp248secu.delStudent(Studentid);
                    } else {
                        System.out.println("Error: Your input/entry for 'StudentID' is NOT a valid integer. Kindly retry again!");
                        scanner.next();//Clearing scanner buffer.
                        continue;
                    }
                    continue;
                }
                if (code == 115) {
                	System.out.println();
                    System.out.println("Updating Student's Particulars...");
                    System.out.println("---------------------------------");
                    System.out.println("Enter update to student's particulars(FirstName, LastName, StudentID) as a single-line entry:");
                    studentfirstname = scanner.next();
                    studentlastname = scanner.next();
                    if (scanner.hasNextInt()) {
                        Studentid = scanner.nextInt();
                    } else {
                        System.out.println("Error: Your input/entry for 'StudentID' is NOT a valid integer. Kindly retry again!");
                        scanner.next();//Clearing scanner buffer.
                        continue;
                    }
                    comp248secu.updateStudentPart(studentfirstname, studentlastname, Studentid);
                    continue;
                }
                if (code == 118) {
                	System.out.println();
                    System.out.println("Updating Assignment Scores...");
                    System.out.println("-----------------------------");
                    System.out.println("Enter update to student's Assignment scores (Assignment1, Assignment2, Assignment3, Assignment4, StudentID) as a single-line entry:");
                    double assignment1 = scanner.nextDouble();
                    double assignment2 = scanner.nextDouble();
                    double assignment3 = scanner.nextDouble();
                    double assignment4 = scanner.nextDouble();
                    if (scanner.hasNextInt() == true) {
                    	//Takes in user input for the Assignment scores and implements these values into the updateAssgtScore method.
                        Studentid = scanner.nextInt();
                        comp248secu.updateAssgtScore(assignment1, assignment2, assignment3, assignment4, Studentid);
                    } else {
                        System.out.println("Error: Your input/entry for 'StudentID' is NOT a valid integer. Kindly retry again!");
                        scanner.next(); //Clearing scanner buffer.
                        continue;
                    }
                    continue;
                }
                if (code == 121) {
                	System.out.println();
                    System.out.println("Updating Other Scores...");
                    System.out.println("-----------------------------");
                    System.out.println("Enter update to student's Other scores(Cummulitivelabs, MidTerm, FinalExam, StudentID) as a single-line entry:");
                    double lab = scanner.nextDouble();
                    double test = scanner.nextDouble();
                    double exam = scanner.nextDouble();
                    if (scanner.hasNextInt() == true) {
                        Studentid = scanner.nextInt();
                        comp248secu.updateOtherScore(lab, test, exam, Studentid);
                    } else {
                        System.out.println("Error: Your input/entry for 'StudentID' is NOT a valid integer. Kindly retry again!");
                        scanner.next(); //Clearing scanner buffer.
                        continue;
                    }
                    continue;

                }
                if (code == 124) {
                	System.out.println();
                    System.out.println("Displaying Student's Report Card...");
                    System.out.println("-----------------------------------");
                    System.out.println("Enter StudentID:");
                    if (scanner.hasNextInt() == true) {
                        Studentid = scanner.nextInt();
                        comp248secu.getStudentInfo(Studentid);
                        comp248secu.computeWeightScore(Studentid);
                       
                        //We are printing the results of the wgtScore and LetterGrade methods here because we want to use these methods in the classReportcard method but without the print statements.
                        if(Comp248secU.found==true) {
                        	System.out.println("--------------------------");
                        	System.out.printf("Student's Cummulative Weighted Score = %.1f\n" , Comp248secU.wgtScore);
                        	Comp248secU.computeGrade(Comp248secU.wgtScore);
                            System.out.println("Student's Final Letter Grade = " + Comp248secU.LetterGrade);
                            
                        }
                    }else {
                        System.out.println("Error: Your input/entry for 'StudentID' is NOT a valid integer. Kindly retry again!");
                        scanner.next(); //Clearing scanner buffer.
                        continue;
                    }
                    continue;
                }
                
                //If the user enters 0 this ends the Program and displays a complementery closing message.
                if (code == 0) {
                	System.out.println();
                    System.out.println("Thank you for patronizing our Simple Classroom Management System.");
                    System.exit(0);
                }
                if (code == 127) {
                	System.out.println();
                    comp248secu.classReportCard();
                    continue;
                }
                
                //If the user does not enter any of those numbers below the program will keep prompting the user to enter a valid number.
                if (code != 103 || code != 106 || code != 109 || code != 112 || code != 115 || code != 118 || code != 121 || code != 124 || code != 127 || code != 0) {
                	System.out.println();
                    System.out.println("Thank you for patronizing our Simple Classroom Management System.");
                    System.exit(0);
                }

            } else {
                System.out.println("Error: Your input/entry is not a valid integer between -128 to 127. Kindly retry again!");
                System.exit(0);

            }
        }
        scanner.close();
    }
}
