/**Exercicio 01
 * 
 * @authors Dennis Kaffer, Lucas Gutier
 * @version 1.0
 */

import java.util.*;

class MyThread extends Thread {	
	static String number;
	static int idHelper = 1;
	private int id;
	private boolean pares = true;
	private boolean impares = true;
	
	public MyThread(String name) {
		super(name);
		System.out.println("Criada a Thread: "+ this.getName());
		this.id = idHelper++;
	}
	
	@Override
	public void run() {
		System.out.println("Executando: "+ this.getName());
		
		try {
			int i = 0;
			while(this.pares || this.impares) {
				 if(this.id == 1) {
					 if (pares(i))
						 if(Integer.parseInt(number) > 1)
						  System.out.println(this.getName()+"-id-->"+this.id+"numero-->"+number);
					 else
						 this.pares = false;
				 }
				 if(this.id == 2) {
					 if (impares(i))
					   System.out.println(this.getName()+"-id-->"+this.id+"numero-->"+number);
					 else
						 this.impares = false;
				 } 
				sleep((int) (Math.random() * 10));	
			i++;
			} 
		   }catch (InterruptedException e) {
	   }
	}
	
	private boolean pares(int index) {
		ArrayList<Integer> x = new ArrayList<Integer>();
		for(int i = 0; i <= 1000; i++) {
			if((i % 2) == 0) {	
				x.add(i);
			}
		}
		if(index >= x.size()) {
			return false;
		}else {
			number = x.get(index).toString();
			return true;
		}
	}
	
	private boolean impares(int index) {
		ArrayList<Integer> x = new ArrayList<Integer>();
		for(int i = 0; i <= 1000; i++) {
			if((i % 2 == 1) && i > 0) {
				x.add(i);	
			}
		}
		if(index >= x.size()) {
			return false;
		}else {
			number = x.get(index).toString();
			return true;
		}
	}
}


public class Exercicio01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyThread t1 = new MyThread("Thread_1");
		t1.start();
		
		MyThread t2 = new MyThread("Thread_2");
		t2.start();

	}

}
