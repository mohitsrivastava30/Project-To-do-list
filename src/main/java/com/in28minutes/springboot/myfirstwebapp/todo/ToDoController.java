package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class ToDoController {
  
	public ToDoController(TodoService todoservice) {
		super();
		this.todoservice = todoservice;
	}

	TodoService todoservice;
	
	@RequestMapping("list-todos")
	public String listToAllDos(ModelMap model)
	{
		List<Todo> todos=todoservice.findByUserName("in28minutes");	
		model.addAttribute("todos", todos);
		return "listToDos";
	}
	
	@RequestMapping(value="add-todo", method=RequestMethod.GET)
	public String showTODoPage(ModelMap model)
	{
		String userName=(String)model.get("name");
		Todo todo=new Todo(0,userName,"",LocalDate.now(),false);
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="add-todo", method=RequestMethod.POST)
	public String addNewPage(ModelMap model, @Valid Todo todo, BindingResult result)
	{
		if(result.hasErrors())
		{
			return "todo";
		}
		String userName=(String)model.get("name");
		todoservice.AddToDo(userName, todo.getDescription(), todo.getTargetDate(),false);
		return "redirect:list-todos";
	}
	
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id, ModelMap model)
	{
		todoservice.DeleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.GET)
	public String showUpdateToDoPage(@RequestParam int id, ModelMap model)
	{
		Todo todo=todoservice.FindById(id);	
		model.addAttribute("todo",todo);
		return "todo";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.POST)
	public String updateToDo(ModelMap model, @Valid Todo todo, BindingResult result)
	{
		if(result.hasErrors())
		{
			return "todo";
		}
		String userName=(String)model.get("name");
		todoservice.updateToDo(todo);
		return "redirect:list-todos";
	}
	
}
