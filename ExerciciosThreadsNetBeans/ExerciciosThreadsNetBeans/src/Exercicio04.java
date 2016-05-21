/**Exercicio 04
 * 
 * @authors Dennis Kaffer, Lucas Gutier
 * @version 1.0
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.*;


public class Exercicio04 extends JFrame{
	
private static final long serialVersionUID = 1L;
	
	public static JTextArea textArea1;
	
	public Exercicio04() {
		setTitle("Dashboard");
        setSize(new Dimension(500, 550));
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        textArea1 = new JTextArea(40, 40);
        getContentPane().add(textArea1, BorderLayout.CENTER); 
        pack();
        setVisible(true);
    }
	
	private static boolean execute = true;
	
	public static HashMap<String, String> sensores = new HashMap<>();
	
	static synchronized void scanf() throws IOException, InterruptedException {
	    
	    Thread th2 = new Thread() {   	
			private String input1;
			private String input2;

			@Override
	        public void run() {  		
				try {
					while(execute) {		
						try { 	
							input1 = JOptionPane.showInputDialog("Nome sensor: ");			
							input2 = JOptionPane.showInputDialog("Dado: ");
							
							if(input1 != null || input1.equals("") && input2 != null || input2.equals("")){
								sensores.put(input1, input2);
							}
						 
						}catch (Exception e) {
					        System.err.println("Por favor, entre com um dado! " + e.getMessage());	        
					        continue; 
					    }
							
	                    Thread.sleep(500);
	                
					}
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    };
	    th2.start();
	}
	

	static synchronized void refreshDashboard(final Exercicio04 e) throws IOException, InterruptedException {  
	    Thread th = new Thread() {
	      
			@Override
	        public void run() {          
				try {		
					while(execute) {
						if (e.isVisible()) {					
							SwingUtilities.invokeLater(new Runnable() {   	
								public void run() {
									textArea1.setText("");
									textArea1.append("--DASHBOARD--\n");  
									for(Entry<String, String> s : sensores.entrySet()) {  
										  textArea1.append("Sensor nome->"+s.getKey()+"  || Dado--> "+s.getValue()+"\n");  
									}
							    }
							 });
							}
	                    Thread.sleep(1000); 
					}
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    };
	  
	    th.start();
	   
	}
	
    public static void main(String[] args) {
    	    
    	/*
    	String[] cmd = new String[] {"C:\\Windows\\System32\\cmd.exe", "/c", "cls"}; 
    	Runtime.getRuntime().exec(cmd);
    	*/
    	Exercicio04 f = new Exercicio04();
    	try {
    		scanf();
    		refreshDashboard(f);
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}