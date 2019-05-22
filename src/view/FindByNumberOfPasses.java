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
		shell.setText("     ↓CHOOSE ONE OF THE WAYS!↓");
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
		String[] items = new String[] { "Due to illness", "For other reasons", "Without good reason", "Total"};
		combo.setItems(items);
		
		Label labelMin = new Label (shell, SWT.NONE);
		labelMin.setText("Min limit:");
		labelMin.setBounds(480, 15, 70, 20);

		Text textMin = new Text (shell, SWT.BORDER);
		textMin.setBounds(550, 12, 30, 25);

		Label labelMax = new Label (shell, SWT.NONE);
		labelMax.setText("Max limit:");
		labelMax.setBounds(610, 15, 70, 20);

		Text textMax = new Text (shell, SWT.BORDER);
		textMax.setBounds(680, 12, 30, 25);
		
		Button findButton = new Button (shell, SWT.PUSH);
		findButton.setText("Find");
		findButton.setBounds(730, 10, 50, 30);
		findButton.addSelectionListener(new SelectionAdapter() {

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
					if (rowsInPage == null) {
						rowsInPage = new RowsInPage();
						rowsInPage.createTable(shell, find);
					} else {
						rowsInPage.refresh(shell);
						rowsInPage.createTable(shell, find);
					}
				}
				textSurname.setText("");
				textMax.setText("");
				textMin.setText("");
			}
		});
	}
}
