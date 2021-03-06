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
		labelSurname.setBounds(230, 15, 70, 20);
		
		Text textSurname = new Text (shell, SWT.BORDER);
		textSurname.setBounds(300, 13, 150, 25);
		
		Label labelGroup = new Label (shell, SWT.NONE);
		labelGroup.setText("Group:");
		labelGroup.setBounds(490, 13, 50, 20);
		
		Text textGroup = new Text (shell, SWT.BORDER);
		textGroup.setBounds(540, 13, 70, 25);
		
		Label labelInfo = new Label (shell, SWT.NONE);
		labelInfo.setText("CHOOSE ONE OF THE WAYS!=>");
		labelInfo.setBounds(8, 15, 210, 30);
		
		Button findButton = new Button (shell, SWT.PUSH);
		findButton.setText("Find");
		findButton.setBounds(630, 13, 120, 25);
		findButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent arg0) {
				String surnameFind = textSurname.getText();
				String groupFind = textGroup.getText();
				List<Student> find = controller.findByGroup(groupFind, surnameFind);
				if (find.isEmpty()) {
					MessageBox messageError = new MessageBox(shell, SWT.ICON_ERROR);
					messageError.setText("Error!");
					messageError.setMessage("Row(s) not found!");
					messageError.open();
				} else {
					if (rowsInPage == null) {
						rowsInPage = new RowsInPage();
						rowsInPage.createTable(shell, find);
					} else {
						rowsInPage.refresh(shell);
						rowsInPage.createTable(shell, find);
					}
				}
				textSurname.setText("");
				textGroup.setText("");
			}
		});
	}
}
