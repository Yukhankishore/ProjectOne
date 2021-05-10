package com.yuk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Base extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		String selectValue = req.getParameter("selectValue");
		String inputValue = req.getParameter("inputValue");
		
		if(selectValue=="" || inputValue=="") {
			res.sendRedirect("index.html");
		}
		else {
		
		String[] files = {"C:\\Users\\yukha\\eclipse-workspace\\ParseXml\\geodata.xml","C:\\Users\\yukha\\eclipse-workspace\\ParseXml\\salarydata.xml"};
		
//		ArrayList to store data fetched from XML files
		List<User> contactList = new ArrayList<User>();
		List<Salary> salaryList = new ArrayList<Salary>();
		List<FullData> dataList = new ArrayList<FullData>();
		
//		Parsing XML file and add to the relevent list
		
//		for(int i=0;i<files.length;i++) {
////			PerformParse PerformParseObj = i+"";
//			PerformParse PerformParseObj =  new PerformParse(files[i], contactList, salaryList, dataList);
//			PerformParseObj.start();
//		}
		
		PerformParse parserOne =  new PerformParse(files[0], contactList, salaryList, dataList);
		PerformParse parserTwo =  new PerformParse(files[1], contactList, salaryList, dataList);
		
		parserOne.run();
		parserTwo.run();
			
			try {
				
				parserOne.join();
				parserTwo.join();
				System.out.println("one alive"+parserOne.isAlive());
				System.out.println("two alive"+parserTwo.isAlive());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
		
		System.out.println("overflowOne");
		
//		Build XML by grouping Contact list and SalaryList
		BuildXml xmlBuilder = new BuildXml();
		xmlBuilder.xmlBuild(contactList,salaryList);
		
//		Parser-Persondata XML and store it in dataList
		PerformParse parserThree =  new PerformParse("persondata.xml", contactList, salaryList, dataList);
		parserThree.run();
		
		try {
			parserThree.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("overflowTwo");
//		Adding to database-datalist values;
		AddToDB db = new AddToDB();
		db.addToDB(dataList);
		
//		fetch from db
		PersonDao deo = new PersonDao();
		deo.connection();
		Person personOne;
		if(selectValue.equals("salary")  || selectValue.equals("pension")) {
			personOne = deo.getPerson(selectValue,"$"+inputValue);
		}else {
			personOne = deo.getPerson(selectValue,inputValue);
		}
			
		if(personOne.name==null ) {
			res.sendRedirect("index.html");
		}
		else {
			HttpSession session =  req.getSession();
			session.setAttribute("name", personOne.name);
			session.setAttribute("phonenumber", personOne.phonenumber);
			session.setAttribute("salary", personOne.salary);
			session.setAttribute("pension", personOne.pension);
			session.setAttribute("address", personOne.address);
			res.sendRedirect("OutputPage.jsp");
		}
		}
		
	}
}
