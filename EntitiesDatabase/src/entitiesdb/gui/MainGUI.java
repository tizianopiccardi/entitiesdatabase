package entitiesdb.gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import entitiesdb.dao.JEDao;

public class MainGUI extends JFrame{

	private static final long serialVersionUID = -5514787605482372081L;
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
            JEDao.open();
        } catch (Exception e) {}

       
        new MainGUI();
    }
}
