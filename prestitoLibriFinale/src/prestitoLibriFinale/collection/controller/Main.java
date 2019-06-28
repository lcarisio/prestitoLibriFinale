package prestitoLibriFinale.collection.controller;

import java.util.Scanner;

import prestitoLibriFinale.collection.classes.MetodiMap;
import prestitoLibriFinale.collection.classes.MetodiSet;
import prestitoLibriFinale.collection.classes.MyRunnableMap;
import prestitoLibriFinale.collection.classes.MyRunnableSet;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MetodiMap metodoMap = new MetodiMap();
		MetodiSet metodoSet = new MetodiSet();
		
		String titoloLibro;
		String nome;
		String cognome;
		Scanner scanner1;
		scanner1 = new Scanner(System.in);
		Scanner scanner2;
		scanner2 = new Scanner(System.in);
		Scanner scanner3;
		scanner3 = new Scanner(System.in);
		Integer inputString;
		
		while(true) {
			System.out.println("\n Scegliere un'opzione");
			System.out.println("1. verificare se un dato libro è in prestito\r\n" + 
					"2. ritrovare i libri attualmente in prestito ad un utente\r\n" + 
					"3. ritrovare i libri che un utente ha preso in prestito nel tempo\r\n" + 
					"4. identificare il cliente che ha in prestito un libro\r\n" + 
					"5. calcolare il periodo più lungo in cui un libro è rimasto in prestito ad un cliente");
			
			
			inputString = scanner1.nextInt();
			
			
			
			
			switch(inputString) {
			case 1:
				 System.out.println("Inserire titolo o parte di esso: ");
				 titoloLibro = scanner2.nextLine();
				 
				 MyRunnableMap myRunnableMap1 = new MyRunnableMap(1,titoloLibro);
				 MyRunnableSet myRunnableSet1 = new MyRunnableSet(1,titoloLibro);
				 Thread t11 = new Thread(myRunnableMap1); 
				 Thread t21 = new Thread(myRunnableSet1); 
				 t11.start();
				 t21.start();
				 try {
					t11.join();
					t21.join();
				} catch (InterruptedException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
				 
				 
				 break; 
			case 2:
				 System.out.println("Inserire nome utente: ");
				 nome = scanner2.nextLine();
				 System.out.println("Inserire cognome utente: ");
				 cognome = scanner3.nextLine();
				 
				 MyRunnableMap myRunnableMap2 = new MyRunnableMap(2,nome,cognome);
				 MyRunnableSet myRunnableSet2 = new MyRunnableSet(2,nome,cognome);
				 Thread t12 = new Thread(myRunnableMap2); 
				 Thread t22 = new Thread(myRunnableSet2); 
				 t12.start();
				 t22.start();
				 try {
					t12.join();
					t22.join();
				} catch (InterruptedException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				 
				
				 break; 
			case 3:
				 System.out.println("Inserire nome utente: ");
				 nome = scanner2.nextLine();
				 System.out.println("Inserire cognome utente: ");
				 cognome = scanner3.nextLine();
				 
				 MyRunnableMap myRunnableMap3 = new MyRunnableMap(3,nome,cognome);
				 MyRunnableSet myRunnableSet3 = new MyRunnableSet(3,nome,cognome);
				 Thread t13 = new Thread(myRunnableMap3); 
				 Thread t23 = new Thread(myRunnableSet3); 
				 t13.start();
				 t23.start();
				 try {
					t13.join();
					t23.join();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				 
				 
				 break; 
			case 4:
				 System.out.println("Inserire titolo o parte di esso: ");
				 titoloLibro = scanner2.nextLine();
				 
				 MyRunnableMap myRunnableMap4 = new MyRunnableMap(4,titoloLibro);
				 MyRunnableSet myRunnableSet4 = new MyRunnableSet(4,titoloLibro);
				 Thread t14 = new Thread(myRunnableMap4); 
				 Thread t24 = new Thread(myRunnableSet4); 
				 t14.start();
				 t24.start();
				 try {
					t14.join();
					t24.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
				
				 break; 
			case 5:
				 System.out.println("Inserire titolo o parte di esso: ");
				 titoloLibro = scanner2.nextLine();
				 
				 MyRunnableMap myRunnableMap5 = new MyRunnableMap(5,titoloLibro);
				 MyRunnableSet myRunnableSet5 = new MyRunnableSet(5,titoloLibro);
				 Thread t15 = new Thread(myRunnableMap5); 
				 Thread t25 = new Thread(myRunnableSet5); 
				 t15.start();
				 t25.start();
				 try {
					t15.join();
					t25.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				
				 break;
			default:
				 System.out.println("Scelta non valida.");
				 break;
			} 
		}
	
		
		
		
		
		
		
	}
}
