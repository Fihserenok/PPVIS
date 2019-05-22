package view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import controller.Controller;
import model.Student;

public class DeleteByNumberOfPasses {
	public DeleteByNumberOfPasses(Display display, Controller controller, RowsInPage rowsInPage, Composite composite) {
		Shell shell = new Shell(display, SWT.MAX | SWT.TITLE | SWT.CLOSE | SWT.SHELL_TRIM);
		shell.setBounds(500, 250, 300, 280);
		shell.open();
		
		Label labelMain = new Label (shell, SWT.NONE);
		
		labelMain.setText("↓CHOOSE ONE OF THE WAYS!↓");
		labelMain.setBounds(45, 15, 250, 20);
		
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
		String[] items = new String[] { "Due to illness", "For other reasons", "Without good reason", "Total"};
		combo.setItems(items);
		
		Label labelMin = new Label (shell, SWT.NONE);
		labelMin.setText("Min limit:");
		labelMin.setBounds(10, 127, 80, 20);

		Text textMin = new Text (shell, SWT.BORDER);
		textMin.setBounds(100, 125, 30, 25);

		Label labelMax = new Label (shell, SWT.NONE);
		labelMax.setText("Max limit:");
		labelMax.setBounds(10, 157, 80, 20);

		Text textMax = new Text (shell, SWT.BORDER);
		textMax.setBounds(100, 155, 30, 25);
		
		Button deleteButton = new Button (shell, SWT.PUSH);
		deleteButton.setText("Delete row(s)");
		deleteButton.setBounds(90, 190, 120, 30);
		deleteButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent arg0) {
				String surnameFind = textSurname.getText();
				int min = Integer.parseInt(textMin.getText());
				int max = Integer.parseInt(textMax.getText());
				List<Student> find = controller.findByNumberOfPasses(combo.getSelectionIndex(), surnameFind, min, max);
				if (find.isEmpty()) {
					MessageBox messageError = new MessageBox(shell, SWT.ICON_ERROR);
					messageError.setText("Error!");
					messageError.setMessage("Row(s) not found!");
					messageError.open();
				} else {
					int i = controller.deleteRows(find);
					MessageBox messageError = new MessageBox(shell, SWT.OK);
					messageError.setText("Done!");
					messageError.setMessage(i + " row(s) was(were) delete!");
					messageError.open();
					rowsInPage.refresh(composite);
					rowsInPage.createTable(composite, controller.getStudents());
				}
				textSurname.setText("");
				textMax.setText("");
				textMin.setText("");
			}
		});
	}
}
