package main;

import controller.Controller;
import model.Model;
import view.View;

public class Main {
	public static void main(String[] args) {
		Model model = new Model();
		Controller controller = new Controller(model);
		View view = new View(controller);
		view.createView();
	}
}
