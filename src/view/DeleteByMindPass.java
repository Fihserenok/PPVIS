package view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import controller.*;
import model.Student;

public class DeleteByMindPass {
	public DeleteByMindPass(Display display, Controller controller, RowsInPage rowsInPage, Composite composite) {
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
		textSurname.setBounds(100, 45, 180, 23);
		
		Label labelTypeOfPass = new Label (shell, SWT.NONE);
		labelTypeOfPass.setText("Type of Pass:");
		labelTypeOfPass.setBounds(10, 87, 90, 20);
		
		Combo combo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		combo.setBounds(100, 85, 180, 23);
		String[] items = new String[] { "Due to illness", "For other reasons", "Without good reason" };
		combo.setItems(items);
		
		Button deleteButton = new Button (shell, SWT.PUSH);
		deleteButton.setText("Delete row(s)");
		deleteButton.setBounds(90, 120, 120, 30);
		deleteButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent arg0) {
				
			}
		});
	}
}
