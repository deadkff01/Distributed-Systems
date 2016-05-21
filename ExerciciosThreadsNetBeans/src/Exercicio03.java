/**Exercicio 03
 * 
 * @authors Dennis Kaffer, Lucas Gutier
 * @version 1.0
 */

import java.awt.*;


import javax.swing.*;


public class Exercicio03 extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static JTextArea textArea1;
	
	public Exercicio03() {
		setTitle("Exercicio03");
        setSize(new Dimension(500, 550));
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        textArea1 = new JTextArea(40, 40);
        getContentPane().add(textArea1, BorderLayout.CENTER); 
        pack();
        setVisible(true);
    }


    private static class MyThread3 
    extends Thread implements Runnable {

    private Exercicio03 e;
	public String name = null;
	static String message = null;
    
	MyThread3(Exercicio03 e, String name) {
		this.e = e;
		this.name = name;
	}

	@Override
	public void run() {
			if (e.isVisible()) {					
				SwingUtilities.invokeLater(new Runnable() {   
					public void run() {
				    	textArea1.append(name+"->diz:  " +message+'\n');     
				    }
				 });
				}
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					
				}
		}
	
	
	 public void put(String a){
		message = a;
	 }
	
    }


public static void main(String[] args) throws InterruptedException {
	Exercicio03 f = new Exercicio03();

	boolean loop = false;
	boolean loop2 = false;
	boolean start = false;
	int input = 0;
	
	while(!loop){
		
		try { 
			 input = Integer.parseInt(JOptionPane.showInputDialog("Numero de escritores: "));
			 if(input > 0) {
				 loop = true; 
			 }
	    } 
	    catch (Exception e) {
	        System.err.println("Por favor, entre com um numero! " + e.getMessage());
	        if(e.getMessage() == null){
	        	System.exit(0);
	        }
	        continue; 
	    }
	}
	
	int inputInt = input;
	
	if(inputInt > 0){
		MyThread3[] t = new MyThread3[inputInt+1];
		String input2 = null;
		
		while(!loop2) {
			
			try { 
				 input2 = JOptionPane.showInputDialog("Mensagem: ");
				 if(input2 == null) {
					 loop2 = true;
					 System.exit(0);
				 }
		    } 
		    catch (Exception e) {
		        System.err.println("Error! " + e.getMessage());
		        continue; 
		    }
			
			if(input2.length() > 0 && !input2.equals(null)) {
				start = true;
				if(start) {			
					int idEscritor = (int) (Math.random() * inputInt+1);		
					t[idEscritor] = new MyThread3(f, "Escritor->"+idEscritor);
					t[idEscritor].put(input2);
					t[idEscritor].start();
					start = false;
				}	
		    }
		 }
		}else {
			System.out.println("Insira um numero maior que zero da proxima vez..");
			System.exit(0);
		}
	
    }
}