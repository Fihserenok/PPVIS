package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import controller.Controller;

public class AddWindow {
	
	//String stringOfExams;
	//String stringOfGrades;
	boolean firstIteration = true;
	
	public AddWindow(Display display, Controller controller, RowsInPage rowsInPage, Composite composite) {
		Shell shell = new Shell(display, SWT.MAX | SWT.TITLE | SWT.CLOSE | SWT.SHELL_TRIM);
		shell.setBounds(400, 100, 330, 380);
		shell.open();
		
		Label labelMain = new Label (shell, SWT.NONE);
		labelMain.setText("ADD RECORD");
		labelMain.setBounds(120, 15, 150, 20);
		
		Label labelName = new Label (shell, SWT.NONE);
		labelName.setText("Name:");
		labelName.setBounds(10, 47, 70, 20);
		
		Text textName = new Text (shell, SWT.BORDER);
		textName.setBounds(110, 45, 180, 24);
		
		Label labelSurname = new Label (shell, SWT.NONE);
		labelSurname.setText("Surname:");
		labelSurname.setBounds(10, 77, 70, 20);
		
		Text textSurname = new Text (shell, SWT.BORDER);
		textSurname.setBounds(110, 75, 180, 24);
		
		Label labelSecondName = new Label (shell, SWT.NONE);
		labelSecondName.setText("Second name:");
		labelSecondName.setBounds(10, 107, 92, 20);
		
		Text textSecondName = new Text (shell, SWT.BORDER);
		textSecondName.setBounds(110, 105, 180, 24);
		
		Label labelGroup = new Label (shell, SWT.NONE);
		labelGroup.setText("Group:");
		labelGroup.setBounds(10, 137, 70, 20);
		
		Text textGroup = new Text (shell, SWT.BORDER);
		textGroup.setBounds(110, 135, 100, 24);
		
		Label labelExams = new Label (shell, SWT.NONE);
		labelExams.setText("Due to illness:");
		labelExams.setBounds(10, 167, 130, 20);
		
		Text textExams = new Text (shell, SWT.BORDER);
		textExams.setBounds(170, 165, 40, 24);
		
		Label labelGrades = new Label (shell, SWT.NONE);
		labelGrades.setText("For other reasons:");
		labelGrades.setBounds(10, 197, 130, 20);
		
		Text textGrades = new Text (shell, SWT.BORDER);
		textGrades.setBounds(170, 195, 40, 24);
		
		Label labelWithout = new Label (shell, SWT.NONE);
		labelWithout.setText("Without good reason:");
		labelWithout.setBounds(10, 227, 150, 20);
		
		Text textWithout = new Text (shell, SWT.BORDER);
		textWithout.setBounds(170, 225, 40, 24);

		Label labelTotal = new Label (shell, SWT.NONE);
		labelTotal.setText("Total:");
		labelTotal.setBounds(10, 257, 130, 20);
		
		Text textTotal = new Text (shell, SWT.BORDER);
		textTotal.setBounds(170, 255, 40, 24);
		
		Button addButton = new Button (shell, SWT.PUSH);
		addButton.setText("Add information");
		addButton.setBounds(100, 295, 120, 30);
		addButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent arg0) {
				String nameToAdd = textName.getText();
				String surnameToAdd = textSurname.getText();
				String secondNameToAdd = textSecondName.getText();
				String groupToAdd = textGroup.getText();
				controller.addStudent(nameToAdd, surnameToAdd, secondNameToAdd, groupToAdd);
				
				rowsInPage.refresh(composite);
				rowsInPage.createTable(composite, controller.getStudents());
				
				textName.setText("");
				textSurname.setText("");
				textSecondName.setText("");
				textGroup.setText("");
				firstIteration = true;
			}
		});
	}
}
