package spring.test;

import com.liu.model.Studens;
import com.liu.spring.context.ApplicationContext;
import com.liu.spring.context.ClasspathXmlApplicationContext;

public class TestString {
		public static void main(String[] args) {
//			String e = "/test/json ";
//			String e2 = "/test/json";
//			System.out.println(e.trim().equals(e2));
//			
			ApplicationContext ap = new ClasspathXmlApplicationContext();
		   Studens bean =  (Studens) ap.getBean("studens");
		   
		   System.out.println(bean);
			
		}
}
