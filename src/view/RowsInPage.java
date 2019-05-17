package view;

import java.util.List;
import java.util.Map;

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
		allPages.setText("All pages: " + numberOfCurrentPage);
		
        howRows = new Label(shell, SWT.NONE);
        howRows.setText("How rows:");
        howRows.setBounds(490, 460, 70, 30);
		
        countRows = new Text(shell, SWT.BORDER);
        countRows.setText("10");
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
            	allPages.setText("All pages: " + 1);
            }
            allRows.setText("All rows: " + listOfRows.size());
    		currentPage.setText("Current page: " + 1);
            numberOfCurrentPage = 1;
        }
        createButtons(shell);
	}
	
	public void createButtons(Composite shell) {
        nextPage = new Button(shell, SWT.PUSH);
        nextPage.setText("Next page");
        nextPage.setBounds(250, 495, 100, 30);
        nextPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	if (listOfRows.size() - numberOfCurrentPage * count <= 0) {
            		return;
            	} else {
            		if (!countRows.getText().isEmpty()) {
            			table.removeAll();
            			count = Integer.parseInt(countRows.getText());
            			if (count <= listOfRows.size() - numberOfCurrentPage * count) {
            				setRows(listOfRows, numberOfCurrentPage * count, numberOfCurrentPage * count + count);
            				rowsInPage.setText("Rows in page: " + count);
            				allPages.setText("All pages: " + (int)Math.ceil((double)listOfRows.size() / (double)count));
            			} else {
            				setRows(listOfRows, numberOfCurrentPage * count, listOfRows.size());
            				rowsInPage.setText("Rows in page: " + (listOfRows.size() - numberOfCurrentPage * count));
            				allPages.setText("All pages: " + (int)Math.ceil((double)listOfRows.size() / (double)count));
            			}
            			allRows.setText("All rows: " + listOfRows.size());
            			numberOfCurrentPage++;
            			currentPage.setText("Current page: " + numberOfCurrentPage);
            		} 
            	}
            }
        });

        prevPage = new Button(shell, SWT.PUSH);
        prevPage.setText("Prev page");
        prevPage.setBounds(130, 495, 100, 30);
        prevPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	if (numberOfCurrentPage == 1) {
            		return;
            	} else {
            		if (!countRows.getText().isEmpty()) {
            			table.removeAll();
            			count = Integer.parseInt(countRows.getText());
            			setRows(listOfRows, (numberOfCurrentPage - 2) * count, (numberOfCurrentPage - 1) * count);
            			rowsInPage.setText("Rows in page: " + count);
            			allPages.setText("All pages: " + (int)Math.ceil((double)listOfRows.size() / (double)count));
            			numberOfCurrentPage--;
            			allRows.setText("All rows: " + listOfRows.size());
            			currentPage.setText("Current page: " + numberOfCurrentPage);
            		} 
            	}
            }
        });

        lastPage = new Button(shell, SWT.PUSH);
        lastPage.setText("Last page");
        lastPage.setBounds(370, 495, 100, 30);
        lastPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	if (numberOfCurrentPage == (int)Math.ceil((double)listOfRows.size() / (double)count)) {
            		return;
            	} else {
            		if (!countRows.getText().isEmpty()) {
            			table.removeAll();
            			count = Integer.parseInt(countRows.getText());
            			numberOfCurrentPage = (int)Math.ceil((double)listOfRows.size() / (double)count);
           				setRows(listOfRows, (numberOfCurrentPage - 1) * count, listOfRows.size());
            			rowsInPage.setText("Rows in page: " + (listOfRows.size() - (numberOfCurrentPage - 1) * count));
            			allPages.setText("All pages: " + (int)Math.ceil((double)listOfRows.size() / (double)count));
            			allRows.setText("All rows: " + listOfRows.size());
            			currentPage.setText("Current page: " + numberOfCurrentPage);
            		} 
            	}
            }
        });

        firstPage = new Button(shell, SWT.PUSH);
        firstPage.setText("First page");
        firstPage.setBounds(10, 495, 100, 30);
        firstPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	if (numberOfCurrentPage == 1) {
            		return;
            	} else {
            		if (!countRows.getText().isEmpty()) {
            			table.removeAll();
            			count = Integer.parseInt(countRows.getText());
            			setRows(listOfRows, 0, count);
            			rowsInPage.setText("Rows in page: " + count);
            			allPages.setText("All pages: " + (int)Math.ceil((double)listOfRows.size() / (double)count));
            			numberOfCurrentPage = 1;
            			allRows.setText("All rows: " + listOfRows.size());
            			currentPage.setText("Current page: " + numberOfCurrentPage);
            		} 
            	}
            }
        });
        
        countRows.addKeyListener(new KeyAdapter(){	
        	@Override        		
        	public void keyPressed(KeyEvent e){
        		if(e.keyCode == SWT.CR){
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
        					allPages.setText("All pages: " + 1);
        				}
        				allRows.setText("All rows: " + listOfRows.size());
        				currentPage.setText("Current page: " + 1);
        				numberOfCurrentPage = 1;
        			}
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
            //tableItem.setText(2, student.getIll() +  student.getOther() + student.getWithout() + student.getTotal());
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