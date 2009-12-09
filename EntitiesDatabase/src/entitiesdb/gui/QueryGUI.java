package entitiesdb.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import entitiesdb.dao.DaoException;
import entitiesdb.dao.JEDao;
import entitiesdb.types.*;

public class QueryGUI extends JPanel {

	private static final long serialVersionUID = -5514787605482372081L;
	private JTextArea text = null;
    private JTextField query = null;

    public QueryGUI() {
        this.setLayout(new BorderLayout());

        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.LINE_AXIS));
        north.setBorder(new TitledBorder("Insert Your Query:"));
        JButton run = new JButton("OK");
        JButton clear = new JButton("Clear");
        JButton dump = new JButton("DUMP!");
        query = new JTextField(40);
        north.add(Box.createHorizontalStrut(5));
        north.add(query);
        north.add(Box.createHorizontalStrut(10));
        north.add(run);
        north.add(Box.createHorizontalStrut(5));
        north.add(clear);
        north.add(Box.createHorizontalStrut(5));
        north.add(dump);
        
        JPanel results = new JPanel(new BorderLayout());
        results.setBorder(new TitledBorder("Results"));
        text = new JTextArea();
        text.setFont(query.getFont());
        text.setLineWrap(true);
        text.setEditable(false);
        JScrollPane scroll = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        results.add(scroll);

        this.add(north, BorderLayout.NORTH);
        this.add(results, BorderLayout.CENTER);

        run.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {doStuff();}
        });

        query.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {doStuff();}
        });
        
        clear.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {clearStuff();}	
        });
        
        dump.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {dumpDb();}	
        });
        
        }

    private void doStuff() {        
        text.setText("");
        String sq = query.getText();
        text.append("Here it is your query: "+sq);   
    }

    private void clearStuff(){
    	text.setText("");
    	query.setText("");
    }
    
    private void dumpDb() {        
        text.setText("");
		Collection<? extends Record> records;
		try {
			records = JEDao.getInstance().getRecords();
			for (Record r : records) {
				text.append(r + "\n"); 
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

          
    }
}