package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;


@Service
public class TodoService {
 
	private static List<Todo> todos=new ArrayList<Todo>();
	
	static int ToDOs=0;
	
	static {
	todos.add(new Todo(++ToDOs, "in28minutes", "LearnAws" , 
			           LocalDate.now().plusYears(1), false));
	
	todos.add(new Todo(++ToDOs, "in28minutes", "LearnDevops" , 
	           LocalDate.now().plusYears(2), false));
	
	todos.add(new Todo(++ToDOs, "in28minutes", "LearnFullStackDevelopment" , 
	           LocalDate.now().plusYears(3), false));
	}
	
	public static List<Todo> findByUserName(String UserName)
	{
		return todos;
	}
	
	public void AddToDo(String userName, String description, LocalDate targetDate, boolean done)
	{
		Todo todo= new Todo(++ToDOs,userName,description,targetDate,done);
		todos.add(todo);
	}
	
	public void DeleteById(int id)
	{
		Predicate<? super Todo> predicate
		   =todo->todo.getId() == id;
		   todos.removeIf(predicate);
	}
	
	public Todo FindById(int id)
	{
		Predicate<? super Todo> predicate
		   =todo->todo.getId() == id;
		Todo todo=todos.stream().filter(predicate).findFirst().get();
		return todo;	   
	}

	public void updateToDo(@Valid Todo todo) {
		DeleteById(todo.getId());
		todos.add(todo);		
	}
	
}
	
