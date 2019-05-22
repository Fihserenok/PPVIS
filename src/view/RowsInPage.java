package view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import model.*;

public class RowsInPage{
	private Table table;
	private Label rowsInPage;
	private Label allRows;
	private Label currentPage;
	private Label allPages;
	private int numberOfCurrentPage = 1;
    private int count = 0;
    private Text countRows;
    private Label howRows;
    private List<Student> listOfRows;
    private Button lastPage;
    private Button nextPage;
    private Button prevPage;
    private Button firstPage;
	
	public void createTable(Composite shell, List<Student> rowsForSet){
		rowsInPage = new Label(shell, SWT.NONE);
		rowsInPage.setBounds(10, 460, 140, 30);
		rowsInPage.setText("Rows in page: " + 0);
		
		allRows = new Label(shell, SWT.NONE);
		allRows.setBounds(150, 460, 80, 30);
		allRows.setText("All rows: " + 0);
		
		currentPage = new Label(shell, SWT.NONE);
		currentPage.setBounds(250, 460, 120, 30);
		currentPage.setText("Current page: " + 1);
				
		allPages = new Label(shell, SWT.NONE);
		allPages.setBounds(385, 460, 100, 30);
		allPages.setText("All pages: " + 1);
		
        howRows = new Label(shell, SWT.NONE);
        howRows.setText("How rows:");
        howRows.setBounds(490, 460, 70, 30);
		
        countRows = new Text(shell, SWT.BORDER);
        countRows.setText("6");
        countRows.setBounds(565, 458, 30, 25);
        
        listOfRows = rowsForSet;
        
		Table tableNew = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		tableNew.setLinesVisible(true);
		tableNew.setHeaderVisible(true);
		tableNew.setBounds(10, 50, 780, 400);

		this.table = tableNew;
		
		TableColumn FIOColumn = new TableColumn(tableNew, SWT.DEFAULT);
		FIOColumn.setText("FIO");
		FIOColumn.setWidth(200);
		
		TableColumn groupColumn = new TableColumn(tableNew, SWT.DEFAULT);
		groupColumn.setText("Group");
		groupColumn.setWidth(100);
		
		TableColumn illColumn = new TableColumn(tableNew, SWT.DEFAULT);
        illColumn.setText("Due to illness");
        illColumn.setWidth(120);
        
        TableColumn otherColumn = new TableColumn(tableNew, SWT.DEFAULT);
        otherColumn.setText("For other reasons");
        otherColumn.setWidth(130);
        
        TableColumn withoutColumn = new TableColumn(tableNew, SWT.DEFAULT);
        withoutColumn.setText("Without good reason");
        withoutColumn.setWidth(160);
        
        TableColumn totalColumn = new TableColumn(tableNew, SWT.DEFAULT);
        totalColumn.setText("Total");
        totalColumn.setWidth(65);
        
        treatment(shell);
        numberOfCurrentPage = 1;
        createButtons(shell);
	}
	
	public void treatment(Composite shell) {		
	if (!countRows.getText().isEmpty()) {
		table.removeAll();
		count = Integer.parseInt(countRows.getText());
		if (count <= listOfRows.size()) {
			setRows(listOfRows, 0, count);
			rowsInPage.setText("Rows in page: " + count);
			allPages.setText("All pages: " + (int)Math.ceil((double)listOfRows.size() / (double)count));
		} else {
			setRows(listOfRows, 0, listOfRows.size());
			rowsInPage.setText("Rows in page: " + listOfRows.size());
		}
		allRows.setText("All rows: " + listOfRows.size());
		currentPage.setText("Current page: " + 1);
		}
	}
	
	public void createButtons(Composite shell) {
        nextPage = new Button(shell, SWT.PUSH);
        nextPage.setText(">");
        nextPage.setBounds(250, 495, 100, 30);
        nextPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	if (listOfRows.size() - numberOfCurrentPage * count <= 0) {
            		return;
            	} else {
            		treatment(shell);
            		table.removeAll();
            		if (count <= listOfRows.size() - numberOfCurrentPage * count) {
            			setRows(listOfRows, numberOfCurrentPage * count, numberOfCurrentPage * count + count);
            		} else {
            			setRows(listOfRows, numberOfCurrentPage * count, listOfRows.size());
            			rowsInPage.setText("Rows in page: " + (listOfRows.size() - numberOfCurrentPage * count));
            		}
            		numberOfCurrentPage++; 
            		currentPage.setText("Current page: " + numberOfCurrentPage);
            	}
            }
        });

        prevPage = new Button(shell, SWT.PUSH);
        prevPage.setText("<");
        prevPage.setBounds(130, 495, 100, 30);
        prevPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	if (numberOfCurrentPage == 1) {
            		return;
            	} else {   
            		treatment(shell);
            		table.removeAll();
            		setRows(listOfRows, (numberOfCurrentPage - 2) * count, (numberOfCurrentPage - 1) * count);
            		numberOfCurrentPage--;
            		currentPage.setText("Current page: " + numberOfCurrentPage);
            	}
            }
        });

        lastPage = new Button(shell, SWT.PUSH);
        lastPage.setText(">>");
        lastPage.setBounds(370, 495, 100, 30);
        lastPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	if (numberOfCurrentPage == listOfRows.size()) {
            		return;
            	} else {
            		numberOfCurrentPage = (int)Math.ceil((double)listOfRows.size() / (double)count);
        			treatment(shell);
        			table.removeAll();        			
        			setRows(listOfRows, (numberOfCurrentPage - 1) * count, listOfRows.size());
        			rowsInPage.setText("Rows in page: " + (listOfRows.size() - (numberOfCurrentPage - 1) * count));
        			currentPage.setText("Current page: " + numberOfCurrentPage);
            	}
            }
        });

        firstPage = new Button(shell, SWT.PUSH);
        firstPage.setText("<<");
        firstPage.setBounds(10, 495, 100, 30);
        firstPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	if (numberOfCurrentPage == 1) {
            		return;
            	} else {
            		numberOfCurrentPage = 1;
            		treatment(shell);
            	}
            }
        });
        
        countRows.addKeyListener(new KeyAdapter(){	
        	@Override        		
        	public void keyPressed(KeyEvent e){
        		if(e.keyCode == SWT.CR){
        			numberOfCurrentPage = 1;
        			treatment(shell);	
        		}
        	}
        });
	}
	
	public void setRows(List<Student> studentToSet, int start, int end) {
		List<Student> subStudentToSet = studentToSet.subList(start, end);
        for (Student student : subStudentToSet) {
            TableItem tableItem = new TableItem(table, SWT.DEFAULT);
            tableItem.setText(0, student.getSurname() + " " + student.getName() + " " + student.getSecondName());
            tableItem.setText(1, student.getGroup());
            tableItem.setText(2, String.valueOf(student.getIll()));
            tableItem.setText(3, String.valueOf(student.getOther()));
            tableItem.setText(4, String.valueOf(student.getWithout()));
            tableItem.setText(5, String.valueOf(student.getTotal()));
        }
	}
	
	public void refresh(Composite shell){
		table.dispose();
		lastPage.dispose();
		nextPage.dispose();
		firstPage.dispose();
		prevPage.dispose();
		countRows.dispose();
		howRows.dispose();
		allPages.dispose();
		currentPage.dispose();
		allRows.dispose();
		rowsInPage.dispose();
        shell.layout(true);
	}
}