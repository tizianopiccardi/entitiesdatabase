package entitiesdb.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class AddAttribute extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea text = null;
    private JTextField entityname = null;
    private JTextField attributename = null;
    private JTextField attributevalue = null;
    //private JCheckBox  valueType = null;

    public AddAttribute() {
        this.setLayout(new BorderLayout());

        JPanel creation = new JPanel(new BorderLayout(5, 5));
        creation.setBorder(new TitledBorder("Add new Attribute"));

        entityname = new JTextField(20);
        attributename = new JTextField(20);
        attributevalue = new JTextField(10);
        //valueType = new JCheckBox("isEntity");
        JButton run = new JButton("Add");

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
        due.add(Box.createVerticalStrut(1));
        //due.add(valueType);

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

        this.add(creation, BorderLayout.NORTH);
        this.add(res, BorderLayout.CENTER);


        // Blocking for now...
		run.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String ename = entityname.getText();
				String aname = attributename.getText();
				String avalue = attributevalue.getText();

				String testo = "";

				if (MainGUI.dao.put(ename, aname, avalue))
					testo = "You have created an attribute called " + aname
							+ " with value " + avalue + " for the entity "
							+ ename;
				else
					testo = "Error... Are you sure that this attribute doesn't exist?";

				text.setText(testo);
			}
		});
	}

}