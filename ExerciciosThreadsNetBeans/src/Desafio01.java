/**Desafio 01
 * 
 * @authors Dennis Kaffer, Lucas Gutier
 * @version 1.0
 */

import java.io.FileReader;
import java.io.IOException;
import java.util.*;


import javax.swing.*;

public class Desafio01 extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private static boolean execute = true;
	
	static ArrayList<String> linhas;
	
	static synchronized void scanf() throws IOException, InterruptedException {
	    
	    Thread th1 = new Thread() {  
	    	
			private String input1;
			private Scanner reader;
	
			@Override
	        public void run() {  		
				try {		
					try { 	
						input1 = JOptionPane.showInputDialog("Nome Arquivo('arquivo.txt'): ");												
						reader = new Scanner(new FileReader(input1));
											
						linhas = new ArrayList<>();
							
						while (reader.hasNext()) {
							String currentLine = reader.nextLine();
							linhas.add(currentLine);
						}
						 
						}catch (Exception e) {
					        System.err.println("Por favor, entre com um dado! " + e.getMessage());
					        execute = true;
					        System.exit(0);
					    }
						
	                    Thread.sleep(500);
	
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    };
	    
	    th1.start();
	    th1.join();
	}
	
	
	static synchronized void put() throws IOException, InterruptedException {  
	    Thread th = new Thread() {
	      
			@Override
	        public void run() {          
				try {		
					
					for(String x :linhas){
						System.out.println(x);
					}
					
	                    Thread.sleep(10000); 
					
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    };
	    
	    th.start();  
	}
	

public static void main(String[] args){
    	    
    	try {
    		while(execute){
    			scanf();
    			put();
    		}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}