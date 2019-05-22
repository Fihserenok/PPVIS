package controller;

import java.io.File;
import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import model.*;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class Parser {
    
	private final static String ELEMENT_UNIVERSITY = "university";
	private final static String ELEMENT_STUDENT = "student";
	private final static String ELEMENT_NAME = "name";
	private final static String ELEMENT_SURNAME = "surname";
	private final static String ELEMENT_SECOND_NAME = "secondName";
	private final static String ELEMENT_GROUP = "group";
	private final static String ELEMENT_ILL = "ill";
	private final static String ELEMENT_OTHER = "other";
	private final static String ELEMENT_WITHOUT = "without";


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
			
			Element illElement = document.createElement(ELEMENT_ILL);
			illElement.setTextContent(String.valueOf(student.getIll()));
			studentElement.appendChild(illElement);
			
			Element otherElement = document.createElement(ELEMENT_OTHER);
			otherElement.setTextContent(String.valueOf(student.getOther()));
			studentElement.appendChild(otherElement);
			
			Element withoutElement = document.createElement(ELEMENT_WITHOUT);
			withoutElement.setTextContent(String.valueOf(student.getWithout()));
			studentElement.appendChild(withoutElement);					
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
        private int ill;
        private int other;
        private int without;
        private int total;
        private ArrayList<Student> students;
        
        
      
        @Override
        public void startDocument() throws SAXException {
            students = new ArrayList<>();
        }

        
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        	lastElementName = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String data = new String(ch, start, length);

            data = data.replace("\n", "").trim();

            if (!data.isEmpty()) {
                if (lastElementName.equals(ELEMENT_NAME))
                    name = data;
                if (lastElementName.equals(ELEMENT_SURNAME))
                    surname = data;
                if (lastElementName.equals(ELEMENT_SECOND_NAME))
                    secondName = data; 
                if (lastElementName.equals(ELEMENT_GROUP))
                	group = data;
                if (lastElementName.equals(ELEMENT_ILL))
                	ill = Integer.valueOf(data);
                if (lastElementName.equals(ELEMENT_OTHER))
                    other = Integer.valueOf(data);
                if (lastElementName.equals(ELEMENT_WITHOUT))
                	without = Integer.valueOf(data);
                if (lastElementName.equals(ELEMENT_WITHOUT))
                	total = Integer.valueOf(data);
            }
        }
        
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException{
            if (qName.equals("student")) {
            	students.add(new Student(name, surname, secondName, group, ill, other, without, total));
            }
        }

        
		public ArrayList<Student> returnStudents() {
			return students;
		}
    }	
}
