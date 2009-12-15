package entitiesdb.gui;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import entitiesdb.dao.EntitiesDAO;

public class MainGUI extends JFrame{

	private static final long serialVersionUID = -5514787605482372081L;
	
	public static EntitiesDAO dao = null;
	
    public MainGUI() {
        super("Dynamic Querier");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Query System", new QueryGUI());
        tabs.addTab("Create new Entity", new CreateEntity());
        tabs.addTab("Add some Attribute", new AddAttribute());
        tabs.addTab("Delete Something", new Delete());

        this.getContentPane().add(tabs);

        this.pack();
        this.setSize(600, 400);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            dao = new EntitiesDAO(new File("db/"));
        } catch (Exception e) {}

       
        new MainGUI();
    }
}
