/**Desafio 02
 * 
 * @authors Dennis Kaffer, Lucas Gutier
 * @version 1.0
 */

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.JOptionPane;

public class Desafio02 {
	
	static int idHelper = 0;
	//The current path of project..
	private static final String PATH = System.getProperty("user.dir")+"/images/";
	private static String input1;
	public static ArrayList<String> a = new ArrayList<>();
	private static BufferedWriter writer;
	private static Document doc; 
	
	//Thread who calls downloadImages() and do the download foreach link...
	static class ThreadIMG extends Thread {		
		private int id;

		public ThreadIMG() {
			this.id = idHelper;
			idHelper++;
		}
		
		@Override
		public void run() {	
			 try {
				 	downloadImages(a.get(this.id));
					sleep(10);
				 }catch (InterruptedException e) {
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	 
		}
		
	//Making the request in url of image link and make the download of them..
	private void downloadImages(String src) throws IOException {
		     //Exctract the name of the image from the src attribute..
		     int indexname = src.lastIndexOf("/");
		     
		     if (indexname == src.length()) {
		           src = src.substring(1, indexname);
		     }
		     
		     indexname = src.lastIndexOf("/");
		     String name = src.substring(indexname, src.length());
		     System.out.println(name);

		     //open a URL stream
		     URL url = new URL(src);
		     InputStream in = url.openStream();

		     OutputStream out = new BufferedOutputStream(new FileOutputStream(PATH + name));

		     for (int b; (b = in.read()) != -1;) {
		          out.write(b);
		      }
		      out.close();
		      in.close();
		   }
	}

	
    static synchronized void scanf() throws IOException, InterruptedException {
	    Thread th1 = new Thread() {
			@Override
	        public void run() {  		
				try {		
					try { 	
						input1 = JOptionPane.showInputDialog("Url do site (exemplo: google.com | facebook.com): ");
						
						if(input1 == null) System.exit(0);
						
						}catch (Exception e) {
					        System.err.println("Por favor, entre com um url! " + e.getMessage());
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
    
    
    static synchronized void requestPagina() throws IOException, InterruptedException {
	    Thread th2 = new Thread() {
			@Override
	        public void run() {  		
				try {		
						entry("http://www."+input1);
						
	                    Thread.sleep(500);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    };	    
	    th2.start();
	    th2.join();
	}
    
    //Do the parse of webpage with Jsoup API parser http://jsoup.org/apidocs/
    public static void entry(String webSiteUrl){
    	   try {
               doc = Jsoup.connect(webSiteUrl).get();                  
               writer = new BufferedWriter(new FileWriter("pagina.html"));
               writer.write(doc.toString());
            	      
           } catch (IOException ex) {
               System.err.println("Erro ao efetuar o download a imagem!");
               Logger.getLogger(Desafio02.class.getName()).log(Level.SEVERE, null, ex);
           }
	}
    
    //This method print and add to ArrayList a the images links...
    static synchronized void requestLinkImgs() throws InterruptedException {
	    Thread th3 = new Thread() {
			@Override
	        public void run() {  		
				try {		
					   Elements img = doc.getElementsByTag("img");         
						 for (Element el : img) {
						    String src = el.absUrl("src");
					
						    System.out.println("Imagem Encontrada!");
						    System.out.println("src atributo(link)--> "+src);
						    
						    a.add(src);
						 }		
	                    Thread.sleep(500);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    };	    
	    th3.start();
	    th3.join();
	}
    
    
 public static void main(String[] args) throws IOException, InterruptedException {
    	scanf();
    	requestPagina();
    	requestLinkImgs();
    	
    	ThreadIMG[] threads = new ThreadIMG[a.size()];
    	
    	for(int i=0; i < a.size(); i++) {	
			threads[i] = new ThreadIMG();
			threads[i].start();
			threads[i].join();
		 }
    } 
}