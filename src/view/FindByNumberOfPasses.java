package view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import controller.Controller;
import model.*;

public class FindByNumberOfPasses {
	
	RowsInPage rowsInPage;
	
	public FindByNumberOfPasses(Display display, Controller controller) {
		Shell shell = new Shell(display, SWT.MAX | SWT.TITLE | SWT.CLOSE | SWT.SHELL_TRIM);
		shell.setBounds(150, 100, 815, 600);
		shell.open();
		
		Label labelSurname = new Label (shell, SWT.NONE);
		labelSurname.setText("Surname:");
		labelSurname.setBounds(10, 15, 70, 20);
		
		Text textSurname = new Text (shell, SWT.BORDER);
		textSurname.setBounds(80, 13, 100, 25);
		
		Label labelTypeOfPass = new Label (shell, SWT.NONE);
		labelTypeOfPass.setText("Type of Pass:");
		labelTypeOfPass.setBounds(190, 13, 85, 20);
		
		Combo combo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		combo.setBounds(280, 10, 180, 25);
		String[] items = new String[] { "Due to illness", "For other reasons", "Without good reason" };
		combo.setItems(items);
		
		Label labelLowerGrade = new Label (shell, SWT.NONE);
		labelLowerGrade.setText("Lower limit:");
		labelLowerGrade.setBounds(470, 15, 80, 20);

		Text textLowerGrade = new Text (shell, SWT.BORDER);
		textLowerGrade.setBounds(550, 12, 30, 25);

		Label labelUpperGrade = new Label (shell, SWT.NONE);
		labelUpperGrade.setText("Upper limit:");
		labelUpperGrade.setBounds(600, 15, 80, 20);

		Text textUpperGrade = new Text (shell, SWT.BORDER);
		textUpperGrade.setBounds(680, 12, 30, 25);
		
		Button findButton = new Button (shell, SWT.PUSH);
		findButton.setText("Find");
		findButton.setBounds(730, 10, 50, 30);
		findButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent arg0) {
			}
		});
	}
}
