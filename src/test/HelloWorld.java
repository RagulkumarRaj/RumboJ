package test;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class HelloWorld{
	private String message;
	
	public HelloWorld(){
		System.out.println("inside Helloworld");
	}
	   public void setMessage(String message){
		   System.out.println("inside setMessage");
	      this.message  = message;
	   }
	   public String getMessage(){
	      return message;
	   }
	 
	   @PostConstruct
	   public void printMessae(){
		   System.out.println("After cons");
	   }
	   public void cleanup(){
		   System.out.println("cleaned");
	   }
}
