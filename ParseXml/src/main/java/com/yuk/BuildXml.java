package com.yuk;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class BuildXml {

	public  void xmlBuild(List<User> contactList,List<Salary> salaryList){
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder;
    try {
        dBuilder = dbFactory.newDocumentBuilder();
//        build new document.
        Document doc = dBuilder.newDocument();

//      add elements to Document
        Element rootElement = doc.createElement("persondata");

//      append root element to document
        doc.appendChild(rootElement);
        
        for(User user : contactList) {
	        for(Salary salary : salaryList) {
	    		if(user.getName().equals(salary.getName())) {
//	    			group user based on name...
	    			rootElement.appendChild(createUserElement(doc, salary.getName(), user.getAddress(), user.getPhoneNumber(), salary.getSalary(), salary.getPension()));
	    		}
	    	}
        }
        
        
//       for output to file, console
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
//      to print in output console.
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

//      source 
        DOMSource source = new DOMSource(doc);

        // write to console and file
        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(new File("persondata.xml"));

        // write data
        transformer.transform(source, console);
        transformer.transform(source, file);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
	
private static Node createUserElement(Document doc, String name, String address, String phonenumber, String salary,String pension) {
    Element person = doc.createElement("person");

//    set name value 
    person.setAttribute("name", name);
    person.appendChild(createUserElements(doc, person, "address", address));
    person.appendChild(createUserElements(doc, person, "phonenumber", phonenumber));
    person.appendChild(createUserElements(doc, person, "salary", salary));
    person.appendChild(createUserElements(doc, person, "pension", pension));

    return person;
}

//    method to create text node
private static Node createUserElements(Document doc, Element element, String name, String value) {
    Element node = doc.createElement(name);
    node.appendChild(doc.createTextNode(value));
    return node;
}
}
