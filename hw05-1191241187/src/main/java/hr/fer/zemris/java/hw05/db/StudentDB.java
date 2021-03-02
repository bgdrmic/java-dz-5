package hr.fer.zemris.java.hw05.db;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * StudentDB is a class which offers simple filtering
 * operations with the database through console. 
 * @author Božidar Grgur Drmić
 *
 */
public class StudentDB {

	/**
	 * Path to the database file.
	 */
	private static final String PATH = "src/main/resources/database.txt";
	
	/**
	 * Database of students.
	 */
	private static StudentDatabase students;
	
	public static void main(String[] args) {
		try {
			readDatabase();
		} catch (Exception e) {
			System.out.println("Wrong database format.");
			return;
		}
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.format("> ");
			String line = scanner.nextLine();
			Scanner lineScanner = new Scanner(line);
			
			String input = lineScanner.next();
			if(input.equals("exit")) {
				System.out.println("Goodbye!");
				break;
			}
			
			if(!input.equals("query")) {
				System.out.println("Unknown command");
				continue;
			}
			
			input = "";
			while(lineScanner.hasNext()) {
				input += " " + lineScanner.next();
			}
			lineScanner.close();

			QueryParser query;
			
			try {
				query = new QueryParser(input);				
			} catch(RuntimeException e) {
				System.out.println("Wrong query.");
				continue;
			}
			
			if(query.isDirectQuery()) {
				var student = students.forJMBAG(query.getQueriedJMBAG());
				var list  = new ArrayList<StudentRecord>();
				if(student != null) {
					System.out.println("Using index for record retrieval.");
					list.add(student);
				}
				print(list);
			} else {
				print(students.filter(new QueryFilter(query.getQuery())));
			}
		}
		
		scanner.close();
	}
	
	/**
	 * A method which reads the database.
	 * @throws IOException if something went wrong with reading
	 * @throws IllegalArgumentException if something went wrong with reading
	 */
	private static void readDatabase() throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(PATH), StandardCharsets.UTF_8);
		students = new StudentDatabase(lines);
	}

	/**
	 * A method which prints the list of student in appropriate format.
	 * @param students - list of StudentRecords.
	 */
	private static void print(List<StudentRecord> students) {
		if(students.size() == 0) {
			System.out.println("Records selected: 0");
			return;
		}
		int jmbagLength = 0, firstNameLength = 0, lastNameLength = 0;
		for(var student : students) {
			if(jmbagLength < student.getJmbag().length()) jmbagLength = student.getJmbag().length();
			if(firstNameLength < student.getFirstName().length()) firstNameLength = student.getFirstName().length();
			if(lastNameLength < student.getLastName().length()) lastNameLength = student.getLastName().length();
		}
		String header = "+";
		for(int i = 0; i < 2 + jmbagLength; i++) header += "=";
		header += "+";
		for(int i = 0; i < 2 + lastNameLength; i++) header += "=";
		header += "+";
		for(int i = 0; i < 2 + firstNameLength; i++) header += "=";
		header += "+===+" + "\n";

		String result = header;
		for(var student : students) {
			result += "| " + student.getJmbag();
			for(int i = 0; i < jmbagLength-student.getJmbag().length(); i++) result += " ";
			result += " | " + student.getLastName();
			for(int i = 0; i < lastNameLength-student.getLastName().length(); i++) result += " ";
			result += " | " + student.getFirstName();
			for(int i = 0; i < firstNameLength-student.getFirstName().length(); i++) result += " ";
			result += " | " + student.getGrade() + " |" + "\n";
		}
		System.out.println(result + header + "Records selected: " + students.size());
	}
}
