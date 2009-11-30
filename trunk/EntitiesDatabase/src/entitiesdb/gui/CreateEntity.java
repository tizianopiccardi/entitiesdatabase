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

public class CreateEntity extends JPanel {

	private static final long serialVersionUID = -551478760542372081L;
	private JTextArea text = null;
    private JTextField entity = null;

    public CreateEntity() {
        this.setLayout(new BorderLayout());

        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.LINE_AXIS));
        north.setBorder(new TitledBorder("Create a new Entity:"));
        JButton run = new JButton("OK");
        entity = new JTextField(40);
        north.add(Box.createHorizontalStrut(5));
        north.add(entity);
        north.add(Box.createHorizontalStrut(10));
        north.add(run);
        
        JPanel results = new JPanel(new BorderLayout());
        results.setBorder(new TitledBorder("Result:"));
        text = new JTextArea();
        text.setFont(entity.getFont());
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

        entity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {doStuff();}
        });
    }
    private void doStuff() {        
        text.setText("");
        String sq = entity.getText();
        text.append("You want to create an entity with this name: "+sq);   
    }
}