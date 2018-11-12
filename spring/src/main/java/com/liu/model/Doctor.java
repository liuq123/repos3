package com.liu.model;

public class Doctor extends Teacher{
	
      private String name;
      private String address;
      
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

	@Override
	public String toString() {
		return "Doctor [name=" + name + ", address=" + address + ", toString()=" + super.toString() + "]";
	}

	
      
	
}
