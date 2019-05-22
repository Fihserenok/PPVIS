package view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import controller.Controller;
import model.Student;

public class FindByMindPasses {
	
	RowsInPage rowsInPage;
	
	public FindByMindPasses(Display display, Controller controller) {
		Shell shell = new Shell(display, SWT.MAX | SWT.TITLE | SWT.CLOSE | SWT.SHELL_TRIM);
		shell.setBounds(150, 100, 815, 600);
		shell.open();
		
		Label labelInfo = new Label (shell, SWT.NONE);
		labelInfo.setText("CHOOSE ONE OF THE WAYS!=>");
		labelInfo.setBounds(8, 15, 210, 30);
		
		Label labelSurname = new Label (shell, SWT.NONE);
		labelSurname.setText("Surname:");
		labelSurname.setBounds(230, 15, 70, 20);
		
		Text textSurname = new Text (shell, SWT.BORDER);
		textSurname.setBounds(300, 13, 110, 25);
		
		Label labelTypeOfPass = new Label (shell, SWT.NONE);
		labelTypeOfPass.setText("Type of Pass:");
		labelTypeOfPass.setBounds(425, 15, 95, 20);
		
		Combo combo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		combo.setBounds(520, 13, 160, 25);
		String[] items = new String[] { "Due to illness", "For other reasons", "Without good reason", "Total" };
		combo.setItems(items);
		
		Button findButton = new Button (shell, SWT.PUSH);
		findButton.setText("Find");
		findButton.setBounds(690, 13, 90, 25);
		findButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent arg0) {
				String surnameFind = textSurname.getText();
				List<Student> find = controller.findByMindPasses(combo.getSelectionIndex(), surnameFind);
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
			}
		});
	}
}
