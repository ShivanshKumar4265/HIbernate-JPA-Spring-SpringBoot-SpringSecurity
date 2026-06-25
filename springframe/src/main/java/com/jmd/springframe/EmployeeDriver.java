package com.jmd.springframe;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmployeeDriver {
	public static void main(String[] args) {
		BeanFactory factory = new ClassPathXmlApplicationContext("config.xml");
		Employee employee = (Employee) factory.getBean("employee");
		System.out.println(employee);
	}
}
