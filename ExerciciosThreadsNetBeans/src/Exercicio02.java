/**Exercicio 02
 * 
 * @authors Dennis Kaffer, Lucas Gutier
 * @version 1.0
 */

import java.util.*;

class MyThread2 extends Thread {	
	static String number;
	static int idHelper = 0;
	private int id;
	private int q;
	
	public MyThread2(String name, int q) {
		super(name);
		this.id = idHelper;
		this.q = q;
		idHelper++;
	}
	
	@Override
	public void run() {
		
		System.out.print("Thread->"+(this.id+1)+"-->");
		int [] x = new int[this.q+1];
		 
			for(int i = 0; i <= this.q; i++ ) {	
				if(i == 0) {
					x[i] = this.id;
				}else {
					x[i] = x[i-1] + this.q;
				}				
			}
			 
			for(int i = 0; i <= this.q; i++ ) {
		        System.out.print(x[i]+", ");
		    }
		
		System.out.print("...");
		System.out.println("\n");
	
		 try {
				sleep(10);
			 }catch (InterruptedException e) {
			}	 
	}
}


public class Exercicio02 {

	private static Scanner sc;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.out.println("-Digite a quantidade de Threads que voce deseja criar-");
		sc = new Scanner(System.in);
		System.out.println("Quantidade:");
		int s = sc.nextInt();
		
		if(s > 0){
		MyThread2 threads[] = new MyThread2[s+1];
		
		for(int i=1; i <= s; i++) {	
			threads[i] = new MyThread2("Thread", s);
			threads[i].start();
			threads[i].join();
		 }
		}else {
			System.out.println("Insira um numero maior que zero da proxima vez..");
			System.exit(0);
		}
	}

}
