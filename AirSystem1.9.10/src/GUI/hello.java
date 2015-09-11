package GUI;

import DBBasic.DBLoginHandler;
import javax.swing.*;            
import java.awt.*;  
import java.awt.event.*;  
import CS.Client;
public class hello implements ActionListener {  
	
	Client me=new Client();
	JButton button = new JButton("登录"); 
	JTextField ID= new JTextField("ID");
	JTextField Password= new JTextField("Password");
	DBLoginHandler handler=new DBLoginHandler();
    
    
  
    //Specify the look and feel to use.  Valid values:  
   //null (use the default), "Metal", "System", "Motif", "GTK+"  
    final static String LOOKANDFEEL = "System";  
  
    public Component createComponents() {  
         
        button.setMnemonic(KeyEvent.VK_I);  
        button.addActionListener(this);  
        
  
        /* 
        * An easy way to put space between a top-level container 
         * and its contents is to put the contents in a JPanel 
         * that has an "empty" border. 
       */  
       JPanel pane = new JPanel(new GridLayout(0, 1));  
       
       pane.add(ID);  
       pane.add(Password);  
       pane.add(button);  
       pane.setBorder(BorderFactory.createEmptyBorder(  
                                       30, //top  
                                       30, //left  
                                        10, //bottom  
                                        30) //right  
                                       );  
  
        return pane;  
    }  
    //监听者监听到风吹草动后做什么
    public void actionPerformed(ActionEvent e) {  
    	//getTextID()
    	//getTextPASSWORD()
    	//尝试login handlerlogin.login
       String id=ID.getText();
       String password=Password.getText();
       System.out.println(id +"  "+password);
       
       String line=id+"-"+password;
       me.sendMessage(line);
       String result=me.receiveMessage();
       if(me.receiveMessage()=="0"){
    	   System.out.println("账户不存在");
    	   
       }
       else{
    	    if(me.receiveMessage()=="1"){
    	    	   new ClientWindow();
    			   System.out.println("1");
    			   
    	       }
    		   else{
    			   if(me.receiveMessage()=="2"){
    		    	   //new OperatorWindow();
    				   System.out.println("2");
    				   
    		       }
    			   else
    				   new AdminWindow();
    				   System.out.println("3");
    			   
    		   }
    	   }
      }
    	   
    	   
       
       
       
    
  
    private static void initLookAndFeel() {  
        String lookAndFeel = null;  
  
        if (LOOKANDFEEL != null) {  
            if (LOOKANDFEEL.equals("Metal")) {  
               lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();  
            } else if (LOOKANDFEEL.equals("System")) {  
                lookAndFeel = UIManager.getSystemLookAndFeelClassName();  
            } else if (LOOKANDFEEL.equals("Motif")) {  
                lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";  
           } else if (LOOKANDFEEL.equals("GTK+")) { //new in 1.4.2  
               lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";  
            } else {  
               System.err.println("Unexpected value of LOOKANDFEEL specified: "  
                                   + LOOKANDFEEL);  
                lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();  
           }  
  
           try {  
                UIManager.setLookAndFeel(lookAndFeel);  
           } catch (ClassNotFoundException e) {  
                System.err.println("Couldn't find class for specified look and feel:"  
                                   + lookAndFeel);  
                System.err.println("Did you include the L&F library in the class path?");  
               System.err.println("Using the default look and feel.");  
            } catch (UnsupportedLookAndFeelException e) {  
                System.err.println("Can't use the specified look and feel ("  
                                   + lookAndFeel  
                                  + ") on this platform.");  
                System.err.println("Using the default look and feel.");  
            } catch (Exception e) {  
               System.err.println("Couldn't get specified look and feel ("  
                                   + lookAndFeel  
                                + "), for some reason.");  
                System.err.println("Using the default look and feel.");  
                e.printStackTrace();  
            }  
       }  
    }  
  
   /** 
    * Create the GUI and show it.  For thread safety, 
     * this method should be invoked from the 
    * event-dispatching thread. 
     */  
    private static void createAndShowGUI() {  
       //Set the look and feel.---设置外观，可以忽略  
        initLookAndFeel();  
 
        //Make sure we have nice window decorations.  
        //设置为false的话，即为不改变外观  
        JFrame.setDefaultLookAndFeelDecorated(true);  
  
        //Create and set up the window.  
       JFrame frame = new JFrame("SwingApplication");  
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
  
       hello app = new hello();  
       Component contents = app.createComponents();  
       frame.getContentPane().add(contents, BorderLayout.CENTER);  
  
        //Display the window.  
       frame.pack();  
       frame.setVisible(true);  
   }  
  
    public static void main(String[] args) {  
       //Schedule a job for the event-dispatching thread:  
       //creating and showing this application's GUI.  
        javax.swing.SwingUtilities.invokeLater(new Runnable() {              public void run() {  
               createAndShowGUI();  
           }  
       });  
    }  
}  
