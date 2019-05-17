package view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import controller.Controller;
import model.Student;

public class FindByGroup {
	RowsInPage rowsInPage;
	
	public FindByGroup(Display display, Controller controller) {
		Shell shell = new Shell(display, SWT.MAX | SWT.TITLE | SWT.CLOSE | SWT.SHELL_TRIM);
		shell.setBounds(150, 100, 815, 600);
		shell.open();
		
		Label labelSurname = new Label (shell, SWT.NONE);
		labelSurname.setText("Surname:");
		labelSurname.setBounds(10, 15, 70, 20);
		
		Text textSurname = new Text (shell, SWT.BORDER);
		textSurname.setBounds(80, 13, 150, 25);
		
		Label labelGroup = new Label (shell, SWT.NONE);
		labelGroup.setText("Group:");
		labelGroup.setBounds(250, 13, 50, 20);
		
		Text textGroup = new Text (shell, SWT.BORDER);
		textGroup.setBounds(300, 13, 70, 25);
		
		Button findButton = new Button (shell, SWT.PUSH);
		findButton.setText("Find");
		findButton.setBounds(380, 13, 120, 25);
		findButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent arg0) {

			}
		});
	}
}
