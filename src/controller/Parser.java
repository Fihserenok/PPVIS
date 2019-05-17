package controller;

import java.io.File;
import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class Parser {
    
	private final String ELEMENT_UNIVERSITY = "university";
	private final String ELEMENT_STUDENT = "student";
	private final String ELEMENT_NAME = "name";
	private final String ELEMENT_SURNAME = "surname";
	private final String ELEMENT_SECOND_NAME = "secondname";
	private final String ELEMENT_GROUP = "group";


	public void write(File file, List<Student> students) throws ParserConfigurationException, TransformerException {		
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
		
		Element universityElement = document.createElement(ELEMENT_UNIVERSITY);
		document.appendChild(universityElement);
		
		for (Student student : students) {

			Element studentElement = document.createElement(ELEMENT_STUDENT);
			universityElement.appendChild(studentElement);
			
			Element nameElement = document.createElement(ELEMENT_NAME);
			nameElement.setTextContent(student.getName());
			studentElement.appendChild(nameElement);
			Element surnameElement = document.createElement(ELEMENT_SURNAME);
			surnameElement.setTextContent(student.getSurname());
			studentElement.appendChild(surnameElement);
			Element secondNameElement = document.createElement(ELEMENT_SECOND_NAME);
			secondNameElement.setTextContent(student.getSecondName());
			studentElement.appendChild(secondNameElement);
			Element groupElement = document.createElement(ELEMENT_GROUP);
			groupElement.setTextContent(student.getGroup());
			studentElement.appendChild(groupElement);
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		
		DOMSource source = new DOMSource(document);

		StreamResult result =  new StreamResult(file);
		
		transformer.transform(source, result);
	}

    public ArrayList<Student> read(File file) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        StudentHandler handler = new StudentHandler();
        parser.parse(file, handler); 
        
        return handler.returnStudents();
    }

    private static class StudentHandler extends DefaultHandler {
        private String name;
        private String surname;
        private String secondName;
        private String group;
        private String lastElementName;
        private ArrayList<Student> students = new ArrayList<>();

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);

            information = information.replace("\n", "").trim();

            if (!information.isEmpty()) {
                if (lastElementName.equals("name"))
                    name = information;
                if (lastElementName.equals("surname"))
                    surname = information;
                if (lastElementName.equals("secondname"))
                    secondName = information;
                if (lastElementName.equals("group")) {
                	group = information;
                students.add(new Student(name, surname, secondName, group));
                } 
            }
        }
        
		public ArrayList<Student> returnStudents() {
			return students;
		}
    }	
}
