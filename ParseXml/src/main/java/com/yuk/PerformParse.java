package com.yuk;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class PerformParse extends Thread{

	String file;
	List<User> contactList = new ArrayList<User>();
	List<Salary> salaryList = new ArrayList<Salary>();
	List<FullData> dataList = new ArrayList<FullData>();
	PerformParse(String file,List<User> contactList,List<Salary> salaryList,List<FullData> dataList){
		this.file=file;
		this.contactList = contactList;
		this.salaryList = salaryList;
		this.dataList = dataList;
	}
	public void run() {
	
		System.out.println("running");
		File xmlFile = new File(file);
		System.out.println(xmlFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder =  dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();	
			NodeList nodelist = doc.getElementsByTagName("person");   
			
//			store in objects relevent list 
			for(int i=0;i<nodelist.getLength();i++) {
				if(doc.getDocumentElement().getNodeName() == "geodata") {
					contactList.add(getAddress(nodelist.item(i)));
				}
				else if(doc.getDocumentElement().getNodeName() == "salarydata") {
					salaryList.add(getSalary(nodelist.item(i)));
				}
				else if(doc.getDocumentElement().getNodeName() == "persondata"){
					dataList.add(getPerson(nodelist.item(i)));
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
private static FullData getPerson(Node node) {		
	FullData data = new FullData();	
	if (node.getNodeType() == Node.ELEMENT_NODE) {
		
        Element element = (Element) node; 
        data.setName(element.getAttribute("name"));
        data.setAddress(getTagValue("address", element));
        data.setPhoneNumber(getTagValue("phonenumber", element)); 
        data.setSalary(getTagValue("salary", element));
        data.setPension(getTagValue("pension", element));
    }
   
	return data;
	}
private static User getAddress(Node node) {
//  method to store data from geodata.XML file		 
	User user = new User();	
	if (node.getNodeType() == Node.ELEMENT_NODE) {
		
        Element element = (Element) node; 
        System.out.println("name of the  "+element.getTagName());
        
        user.setName(element.getAttribute("name"));
        
        user.setAddress(getTagValue("address", element));
        user.setPhoneNumber(getTagValue("phonenumber", element)); 
    }
   
	return user;
		
	}
private static Salary getSalary(Node node) {
//   method to store data from salary XML file
	Salary salary = new Salary();	
	if (node.getNodeType() == Node.ELEMENT_NODE) {
		
        Element element = (Element) node; 
        salary.setName(element.getAttribute("name"));
        salary.setPension(getTagValue("pension", element));
        salary.setSalary(getTagValue("salary", element)); 
    }
	return salary;
	
}
 private static String getTagValue(String tag, Element element) {
	 	NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        System.out.println(node.getNodeValue()+"node value");
        return node.getNodeValue();
    }
}
