package com.appserver.action;

class Model{
	String name;
}

public class TestAction {
	
	Model model2;
	
	public static void main(String[] args) {

		TestAction action = new TestAction();

		Model model = new Model();
		model.name="dog";
		action.setName(model);
		System.out.println(model.name);
		
		//=================== title2 ===============
		action.model2=new Model();
		action.model2.name="dog";
		action.setModelName();
		System.out.println(action.model2.name);
		
	}

	private void setName(Model model) {
		model.name="cat";
	}
	
	private void setModelName(){
		this.model2.name="cat";
	}
	
}
