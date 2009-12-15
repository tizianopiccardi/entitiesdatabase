package entitiesdb.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import entitiesdb.types.Record;

public class Delete extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea text = null;
    private JTextField entityname = null;
    private JTextField attributename = null;
    private JTextField attributevalue = null;
    private JTextField enamedelete = null;

    public Delete() {
        this.setLayout(new BorderLayout());

        JPanel creation = new JPanel(new BorderLayout(5, 5));
        creation.setBorder(new TitledBorder("Delete an Attribute"));

        entityname = new JTextField(20);
        attributename = new JTextField(20);
        attributevalue = new JTextField(20);
        enamedelete = new JTextField(20);

        JButton run = new JButton("Delete");

        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.LINE_AXIS));
        north.setBorder(new TitledBorder("Delete an Entity:"));
        JButton deleteEntity = new JButton("Delete");
        north.add(Box.createHorizontalStrut(5));
        north.add(enamedelete);
        north.add(Box.createHorizontalStrut(10));
        north.add(deleteEntity);
        
        
        JPanel zero = new JPanel();
        zero.setLayout(new BoxLayout(zero, BoxLayout.Y_AXIS));
        JPanel uno = new JPanel();
        uno.setLayout(new BoxLayout(uno, BoxLayout.Y_AXIS));
        JPanel due = new JPanel();
        due.setLayout(new BoxLayout(due, BoxLayout.Y_AXIS));

        JTextField pau1 = new JTextField("Entity Name: ");
        pau1.setEditable(false);
        pau1.setHighlighter(null);
        pau1.setBorder(null);
        pau1.setOpaque(false);
        JTextField pau2 = new JTextField("Attribute Name: ");
        pau2.setEditable(false);
        pau2.setHighlighter(null);
        pau2.setBorder(null);
        pau2.setOpaque(false);
        JTextField pau3 = new JTextField("Attribute Value: ");
        pau3.setEditable(false);
        pau3.setHighlighter(null);
        pau3.setBorder(null);
        pau3.setOpaque(false);

        zero.add(pau1);
        zero.add(pau2);
        zero.add(pau3);

        uno.add(entityname);
        uno.add(attributename);
        uno.add(attributevalue);
        
        due.add(Box.createVerticalStrut(18));
        due.add(run);

        creation.add(zero,BorderLayout.WEST);
        creation.add(uno, BorderLayout.CENTER);
        creation.add(due, BorderLayout.EAST);

        JPanel res = new JPanel(new BorderLayout());
        res.setBorder(new TitledBorder("Result"));

        text = new JTextArea();
        text.setFont(pau1.getFont());
        text.setLineWrap(true);
        text.setEditable(false);
        JScrollPane scroll2 = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        res.add(scroll2, BorderLayout.CENTER);
        JPanel sopra = new JPanel(new BorderLayout());
        sopra.add(north, BorderLayout.NORTH);
        sopra.add(creation, BorderLayout.SOUTH);
        this.add(sopra, BorderLayout.NORTH);
        this.add(res, BorderLayout.CENTER);

        deleteEntity.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	String ename = enamedelete.getText();
            	
            	//JEDao.deleteAttribute(new Record(ename, null, null));
            	String testo = "";
            	if (MainGUI.dao.deleteEntity(ename))
            		testo = "You have deleted an entity called "+ename;
            	else
            		testo = "The entity doesn't exist";
                text.setText(testo);
            }
        });
        // Blocking for now...
        run.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	String ename = entityname.getText();
            	String aname = attributename.getText();
            	String avalue= attributevalue.getText();
            	
            	String testo = "";
            	if (MainGUI.dao.deleteRecord(new Record(ename, aname, avalue)))
            		testo = "You have deleted an attribute called "+aname+" with value "+avalue+" for the entity "+ename;
            	else
            		testo = "The attribute doesn't exist";
                text.setText(testo);
            }
        });
    }

}