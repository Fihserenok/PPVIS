package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

import model.*;

public class Controller {
	
	private Model list;
	private Parser parser;
	
	public Controller(Model list) {
		this.list = list;
		parser = new Parser();
	}
	
	public void loadStudents(File fileOpen) throws SAXException, IOException, ParserConfigurationException {
		list.setStudents(parser.read(fileOpen));
	}
	
	public void saveStudents(File fileSave) throws ParserConfigurationException, TransformerException {
		parser.write(fileSave, list.getStudents());
	}
	
	public void addStudent(String nameToAdd, String surnameToAdd, String secondNameToAdd, String groupToAdd, int illToAdd, int otherToAdd, int withoutToAdd, int totalToAdd) {
		List<Student> addedStudent = list.getStudents();
		addedStudent.add(new Student(nameToAdd, surnameToAdd, secondNameToAdd, groupToAdd, illToAdd, otherToAdd, withoutToAdd, totalToAdd));
		list.setStudents(addedStudent);
	}
	
	public List<Student> findByGroup(String groupFind, String surnameFind) {
		ArrayList<Student> findStudent = new ArrayList<>();
		for (Student student : list.getStudents()) {
			if (groupFind.equals(student.getGroup())) {
				findStudent.add(student);	
			} else if(surnameFind.equals(student.getSurname())) {
				findStudent.add(student);
			  }
		}
		return findStudent;
	}
	
	public int deleteRows(List<Student> removeStudent) {
		List<Student> deleteRows = list.getStudents();
		int size = deleteRows.size();
		for (Student student : removeStudent) {
			deleteRows.remove(student);
		}
		list.setStudents(deleteRows);
		return size - deleteRows.size();
	}
	
	public List<Student> findByMindPasses(int passType, String surnameFind) {
		ArrayList<Student> findPasses = new ArrayList<>();
		for (Student student : list.getStudents()) {
			if (surnameFind.equals(student.getSurname())) {
				findPasses.add(student);
			} else if (passType == 0 && (student.getIll() > 0)) {
				findPasses.add(student);
			} else if (passType == 1 && (student.getOther() > 0)) {
				findPasses.add(student);
			} else if (passType == 2 && (student.getWithout() > 0)) {
				findPasses.add(student);
			} else if (passType == 3 && (student.getTotal() > 0)) {
				findPasses.add(student);
			}
		}
		return findPasses;
	}
	
	public List<Student> findByNumberOfPasses(int passType, String surnameFind, int min, int max) {
		ArrayList<Student> findQuantityPasses = new ArrayList<>();
		for (Student student : list.getStudents()) {
			if (surnameFind.equals(student.getSurname())) {
				findQuantityPasses.add(student);
			} else if (passType == 0 && (student.getIll() >= min) && (student.getIll() <= max)) {
				findQuantityPasses.add(student);
			} else if (passType == 1 && (student.getOther() >= min) && (student.getOther() <= max)) {
				findQuantityPasses.add(student);
			} else if (passType == 2 && (student.getWithout() >= min) && (student.getWithout() <= max)) {
				findQuantityPasses.add(student);
			} else if (passType == 3 && (student.getTotal() >= min) && (student.getTotal() <= max)) {
				findQuantityPasses.add(student);
			}
		}
		return findQuantityPasses;
	}
	
	public List<Student> getStudents(){
		return list.getStudents();
	}
}
