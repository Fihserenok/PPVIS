package view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import controller.Controller;
import model.Student;

public class DeleteByGroup {
	public DeleteByGroup(Display display, Controller controller, RowsInPage rowsInPage, Composite composite) {
		Shell shell = new Shell(display, SWT.MAX | SWT.TITLE | SWT.CLOSE | SWT.SHELL_TRIM);
		shell.setBounds(500, 250, 300, 210);
		shell.open();
		
		Label labelMain = new Label (shell, SWT.NONE);
		labelMain.setText("DELETE ROW(S)");
		labelMain.setBounds(90, 15, 250, 20);
		
		Label labelSurname = new Label (shell, SWT.NONE);
		labelSurname.setText("Surname:");
		labelSurname.setBounds(10, 47, 70, 20);
		
		Text textSurname = new Text (shell, SWT.BORDER);
		textSurname.setBounds(85, 45, 180, 23);
		
		Label labelGroup = new Label (shell, SWT.NONE);
		labelGroup.setText("Group:");
		labelGroup.setBounds(10, 87, 70, 20);
		
		Text textGroup = new Text (shell, SWT.BORDER);
		textGroup.setBounds(85, 85, 180, 23);
		
		Button deleteButton = new Button (shell, SWT.PUSH);
		deleteButton.setText("Delete row(s)");
		deleteButton.setBounds(90, 120, 120, 30);
		deleteButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent arg0) {
				
			}
		});
	}
}
