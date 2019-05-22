package view;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.xml.sax.SAXException;

import controller.*;

public class View {
	
	private Controller controller;
	Display display = new Display();
	Shell shell = new Shell(display, SWT.MAX | SWT.TITLE | SWT.CLOSE | SWT.SHELL_TRIM);
	boolean firstIteration = true;
	RowsInPage rowsInPage;
	
	public View(Controller controller) {
		this.controller = controller;
	}
	
	public void createView() {
		shell.setBounds(150, 100, 1020, 620);
		shell.setText("Task 2 Option 3");
		
		Menu menu = new Menu (shell, SWT.BAR);
		shell.setMenuBar(menu);

		MenuItem fileItem = new MenuItem (menu, SWT.CASCADE);
		fileItem.setText("File");
		
		Menu subMenu = new Menu (shell, SWT.DROP_DOWN);
		fileItem.setMenu(subMenu);

		MenuItem fileSourceItem = new MenuItem (menu, SWT.CASCADE);
		fileSourceItem.setText("Source");
		
		Menu subMenuSource = new Menu (shell, SWT.DROP_DOWN);
		fileSourceItem.setMenu(subMenuSource);
		
		MenuItem subItemAdd = new MenuItem (subMenuSource, SWT.PUSH);
		subItemAdd.setText ("Add student");
		subItemAdd.addListener (SWT.Selection, new Listener () {
			  @Override
			    public void handleEvent (Event e) {
					if (rowsInPage == null) {
						rowsInPage = new RowsInPage();
						rowsInPage.createTable(shell, controller.getStudents());
					}
			    	new AddWindow(display, controller, rowsInPage, shell);
			    }
		});
		
		MenuItem subItemFindByGroup = new MenuItem (subMenuSource, SWT.PUSH);
		subItemFindByGroup.setText ("Find by Group");
		subItemFindByGroup.addListener (SWT.Selection, new Listener () {
		    @Override
		    public void handleEvent (Event e) {
		    	new FindByGroup(display, controller);
		    }
		});
		
		MenuItem subItemFindByAverageGrade = new MenuItem (subMenuSource, SWT.PUSH);
		subItemFindByAverageGrade.setText ("Find by Mind pass"); //Find by Average Grade
		subItemFindByAverageGrade.addListener (SWT.Selection, new Listener () {
		    @Override
		    public void handleEvent (Event e) {
				new FindByNumberOfPasses(display, controller);
		    }
		});

		MenuItem subItemFindByDiscipline = new MenuItem (subMenuSource, SWT.PUSH);
		subItemFindByDiscipline.setText ("Find by Number of passes");
		subItemFindByDiscipline.addListener (SWT.Selection, new Listener () {
		    @Override
		    public void handleEvent (Event e) {
				new FindByMindPasses(display, controller);
		    }
		});
		
		MenuItem subItemDeleteByGroup = new MenuItem (subMenuSource, SWT.PUSH);
		subItemDeleteByGroup.setText ("Delete by Group");
		subItemDeleteByGroup.addListener (SWT.Selection, new Listener () {
		    @Override
		    public void handleEvent (Event e) {
		    	new DeleteByGroup(display, controller, rowsInPage, shell);
		    }
		});
		
		MenuItem subItemDeleteByAverageGrade = new MenuItem (subMenuSource, SWT.PUSH);
		subItemDeleteByAverageGrade.setText ("Delete by Mind Pass");
		subItemDeleteByAverageGrade.addListener (SWT.Selection, new Listener () {
		    @Override
		    public void handleEvent (Event e) {
				new DeleteByMindPasses(display, controller, rowsInPage, shell);
		    }
		});

		MenuItem subItemDeleteByDiscipline = new MenuItem (subMenuSource, SWT.PUSH);
		subItemDeleteByDiscipline.setText ("Delete by Number of Passes");
		subItemDeleteByDiscipline.addListener (SWT.Selection, new Listener () {
		    @Override
		    public void handleEvent (Event e) {
				new DeleteByNumberOfPasses(display, controller, rowsInPage, shell);
		    }
		});
		
		MenuItem subItemOpen = new MenuItem (subMenu, SWT.PUSH);
		subItemOpen.setText ("Open");
		subItemOpen.addListener (SWT.Selection, new Listener () {
		    @Override
		    public void handleEvent (Event e) {
				FileDialog dialogOpen = new FileDialog(shell, SWT.OPEN);
				String fileNameOpen = dialogOpen.open();
				File fileOpen = new File(fileNameOpen);
				try {
					controller.loadStudents(fileOpen);
				} catch (SAXException | IOException | ParserConfigurationException e1) {
					e1.printStackTrace();
				}
				if (rowsInPage == null) {
					rowsInPage = new RowsInPage();
					rowsInPage.createTable(shell, controller.getStudents());
				} else {
					rowsInPage.refresh(shell);
					rowsInPage.createTable(shell, controller.getStudents());
				}
		    }
		});
		
		MenuItem subItemSave = new MenuItem (subMenu, SWT.PUSH);
		subItemSave.setText ("Save");
		subItemSave.addListener (SWT.Selection, new Listener () {
		    @Override
		    public void handleEvent (Event e) {
		    	if (controller.getStudents().size() == 0) {
					MessageBox messageError = new MessageBox(shell, SWT.ICON_ERROR);
					messageError.setText("ERROR!");
					messageError.setMessage("Nothing to save");
					messageError.open();
					return;
		    	}
				FileDialog dialogSave = new FileDialog(shell, SWT.SAVE);
				String fileNameSave = dialogSave.open();
				File fileSave = new File(fileNameSave);
				try {
					controller.saveStudents(fileSave);
				} catch (ParserConfigurationException | TransformerException e1) {
					e1.printStackTrace();
				}
		    }
		});

		Button addStudentButton = new Button(shell, SWT.PUSH);
		addStudentButton.setText("Add student");
		addStudentButton.setBounds(10, 5, 95, 40);

		addStudentButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (rowsInPage == null) {
					rowsInPage = new RowsInPage();
					rowsInPage.createTable(shell, controller.getStudents());
				}
				new AddWindow(display, controller, rowsInPage, shell);
			}
		});

		Button findByGroup = new Button(shell, SWT.PUSH | SWT.WRAP);
		findByGroup.setText("Find by Group");
		findByGroup.setBounds(105, 5, 110, 40);

		findByGroup.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				new FindByGroup(display, controller);
			}
		});

		Button findByMindPass = new Button(shell, SWT.PUSH | SWT.WRAP);
		findByMindPass.setText("Find by Mind Pass");
		findByMindPass.setBounds(215, 5, 130, 40);

		findByMindPass.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				new FindByMindPasses(display, controller);
			}
		});
		
		Button findByNumberOfPasses = new Button(shell, SWT.PUSH | SWT.WRAP);
		findByNumberOfPasses.setText("Find by Number of Passes");
		findByNumberOfPasses.setBounds(345, 5, 180, 40);

		findByNumberOfPasses.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				 new FindByNumberOfPasses(display, controller);
			}
		});
		
		Button deleteByGroup = new Button(shell, SWT.PUSH | SWT.WRAP);
		deleteByGroup.setText("Delete by Group");
		deleteByGroup.setBounds(525, 5, 120, 40);

		deleteByGroup.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				new DeleteByGroup(display, controller, rowsInPage, shell);
			}
		});

		Button deleteByMindPass = new Button(shell, SWT.PUSH | SWT.WRAP);
		deleteByMindPass.setText("Delete by Mind Pass");
		deleteByMindPass.setBounds(645, 5, 145, 40);

		deleteByMindPass.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				new DeleteByMindPasses(display, controller, rowsInPage, shell);
			}
		});
		
		Button deleteByNumberOfPasses = new Button(shell, SWT.PUSH | SWT.WRAP);
		deleteByNumberOfPasses.setText("Delete by Number of Passes");
		deleteByNumberOfPasses.setBounds(790, 5, 200, 40);

		deleteByNumberOfPasses.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				new DeleteByNumberOfPasses(display, controller, rowsInPage, shell);
			}
		});
				
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
		
	public Controller getController() {
		return controller;
	}
	
}
