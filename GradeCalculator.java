/*
 * Class: CMSC203 
 * Instructor: Professor Monshi
 * Description: (Learning Java)
 * Due: 9/15/2026
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
  independently. I have not copied the code from a student or   * any source. I have not given my code to any student.
 * Print your Name here: Jonathan
*/
package cmsc203;

import java.util.Scanner;

import java.io.FileNotFoundException;

import java.io.PrintWriter;
import java.io.File;

public class GradeCalculator {
	
	public static void main(String[] args) {
		
		String firstName, lastName, course, category, userInput, letterGrade;
		int categories, numScores, weight;
		double finalAverage = 0, catAverage = 0, score;
		Scanner userReader = new Scanner(System.in);
		
		File config = new File("gradeconfig.txt");
		File input = new File("grades_input.txt");
		
		System.out.println("----------------------");
		System.out.println("JAVA GRADE CALCULATOR");
		System.out.println("----------------------");
		
		System.out.println("Loading configuration from gradeconfig.txt");
		
		try (PrintWriter writer = new PrintWriter("grades_report.txt");
				Scanner inputReader = new Scanner(input) 
			){
	
			Scanner configReader = null;
			
			try {
				configReader= new Scanner(config);
			}
			catch(FileNotFoundException e){
				System.out.println("Config not found. Using default config.");
				configReader = new Scanner("CMSC203\n" + "3\n" + "Homework 30\n" + "Projects 30\n" + "Exams 40");
				
			}
			
			firstName = inputReader.nextLine();
			lastName = inputReader.nextLine();
			writer.println(firstName + " " + lastName);

			course = configReader.nextLine();
			writer.println("Course: "+ course);
			
			writer.println();
			writer.println("Category Results:");
			
			categories = configReader.nextInt();
			configReader.nextLine();
			
			for(int i = 0; i < categories; i++) {
				catAverage = 0;
				
				
				category = inputReader.nextLine();
				numScores = inputReader.nextInt();
				
				String configCategory = configReader.next();
				weight = configReader.nextInt();
				
				
				
				for(int j = 0; j < numScores; j++) {
					score = inputReader.nextDouble();
					catAverage += score;
					}
				
				inputReader.nextLine();
				
				catAverage = catAverage/numScores;
				finalAverage += catAverage * (weight/100.0);
				
				writer.println(category + " " + weight + "%: average = " + catAverage);
				writer.println();
				
				}
			writer.println("Final Numeric Average: " + finalAverage);
			
			System.out.println("Apply +/- grading? (Y/N)");
			userInput = userReader.nextLine();
			
			while(!(userInput.equalsIgnoreCase("Y") || userInput.equalsIgnoreCase("N"))){
				System.out.println("Apply +/- grading? (Y/N)");
				userInput = userReader.nextLine();
			}
			
			if(finalAverage >= 90) {
				
				letterGrade = "A";
			}else if(finalAverage >=80) {
				
				letterGrade = "B";
				
			}else if( finalAverage >=70) {	
				
				letterGrade = "C";
				
			}else if(finalAverage >=60) {
				
				letterGrade = "D";
				
			}else {
				
				letterGrade = "F";
				
				}
			if(userInput.equalsIgnoreCase("Y")) {
				System.out.println("Base letter grade: " + letterGrade);
				writer.println("Base Letter Grade: " + letterGrade);
			
				double decimal = ( finalAverage- (int)(finalAverage) );
				
				if(decimal<=0.33) {
					System.out.println(letterGrade+"-");
					letterGrade+="-";
				}else if(decimal >=0.66) {
					System.out.println(letterGrade+"+");
					letterGrade+="+";
				}
				
				System.out.println("Final letter grade: " + letterGrade);
				writer.println("Final letter grade: " + letterGrade);
			}	
			
			if(userInput.equalsIgnoreCase("N"))	{
				
				System.out.println("Base letter grade: " + letterGrade);
				writer.println("Base Letter Grade: " + letterGrade);
			}
				
				
			
			System.out.println("Summary written to grades_report.txt");
			System.out.println("Program Complete. Goodbye!");
			
			if(configReader != null) {
				configReader.close();
				}
		}		
		catch(FileNotFoundException e) {
			System.out.println("Required file not found.");
			e.printStackTrace();
		}
		
		userReader.close();
		
	
	}
}



