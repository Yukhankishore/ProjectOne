package com.yuk;

public class User {
    private String address;
    private String phonenumber;
    private String name;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phonenumber;
    }

    public void setPhoneNumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    
    
    public String printUser() {
        return "User : phonenumber=" + phonenumber + ", address=" + address +"name ="+name;
    }
}
class Salary {
	 private String pension;
	 private String salary;
	 private String name;
	 public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
    public String getPension() {
        return pension;
    }

    public void setPension(String pension) {
        this.pension = pension;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String printSalary(){
		return "name="+":"+name+"pension="+pension+"salary="+salary;
    	
    }
}

class FullData {
	 private String address;
     private String phonenumber;
     private String name;
     private String pension;
	 private String salary;
	 
	 public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
    public String getPension() {
        return pension;
    }

    public void setPension(String pension) {
        this.pension = pension;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phonenumber;
    }

    public void setPhoneNumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}