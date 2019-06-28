package prestitoLibriFinale.collection.classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

import prestitoLibriFinale.collection.classes.Prestito;
import prestitoLibriFinale.collection.classes.Libro;
import prestitoLibriFinale.collection.interfaces.InterfacciaGestioneCatalogo;



public class MetodiMap implements InterfacciaGestioneCatalogo {

	
	protected HashMap<String,Libro> catalogoLibri;
	protected Collection<Prestito> elencoPrestiti;
	protected HashMap<String,Prestito> prestitiNonResi;
	
	public MetodiMap() {
		
		HashMap<String,Libro> catalogoLibriTemporaneo = new HashMap<String,Libro>();
		Collection<Prestito> elencoPrestitiTemporaneo = new ArrayList<Prestito>();
		HashMap<String,Prestito> prestitiNonResiTemporaneo = new HashMap<String,Prestito>();
		
		
		Path percorsoCatalogo = Paths.get("Catalogo.txt");
		Collection<String> lineeLibri;			
		try {
			lineeLibri = Files.lines(percorsoCatalogo).collect(Collectors.toList());
				
			for(String l : lineeLibri) {
				String[] parole= l.split(";");				
				int numeroAutori = Integer.parseInt(parole[2]);
				ArrayList<String> autori = new ArrayList<>(); 
				for(int i = 0; i< numeroAutori; i++) {
					autori.add(parole[3+i]);
				}
				Libro libro = new Libro(parole[0], parole[1].toLowerCase(), numeroAutori, autori);
				catalogoLibriTemporaneo.put(parole[0],libro);
					
			}
		} 
		catch (IOException e2) {
			e2.printStackTrace();
		}
		
		
		
		
		
		Path percorsoPrestiti = Paths.get("Prestiti.txt");
		Collection<String> lineePrestiti;
			
			try {
				lineePrestiti = Files.lines(percorsoPrestiti).collect(Collectors.toList());
				
				for(String l : lineePrestiti) {
					String[] parole= l.split(";");
					Date inizioPrestito;
					
					inizioPrestito = new SimpleDateFormat("dd/MM/yyyy").parse(parole[3]);
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					Date finePrestito = new Date();
					
					if(parole[4].contentEquals("0")) {
						finePrestito = formatter.parse("01/01/1800");
					}
					else {
						finePrestito = formatter.parse(parole[4]);
					}
					
					Prestito prestito = new Prestito(parole[0], parole[1], parole[2], inizioPrestito, finePrestito);
					elencoPrestitiTemporaneo.add(prestito);
				}
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			this.catalogoLibri = catalogoLibriTemporaneo;
			this.elencoPrestiti = elencoPrestitiTemporaneo;
			
			for(Prestito prestito : this.elencoPrestiti) {
					if(inPrestito(prestito.getFinePrestito())) {
					prestitiNonResiTemporaneo.put(prestito.getIdLibro(),prestito);
					}
			}
			
			
			
			
			this.prestitiNonResi = prestitiNonResiTemporaneo;
	}

	
	
	public boolean inPrestito(Date d) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dataRiferimento = new Date();
		try {
			dataRiferimento = formatter.parse("01/01/1800");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d.equals(dataRiferimento);
	}
	
	
	
	
	public void daRestituire(String titoloLibro) {
		boolean flag=false;	
		for(String key : catalogoLibri.keySet()) {
			Libro libro = catalogoLibri.get(key);
			if (libro.getTitoloLibro().contains(titoloLibro)) {
				flag=true;
				if (prestitiNonResi.containsKey(libro.getIdLibro())) {
					System.out.println("Il libro " + libro.getTitoloLibro() + " è da restituire.");
				}
				else{
					System.out.println("Il libro " + libro.getTitoloLibro() + " è in biblioteca.");
				}
			}
		}
		if(!flag) {
			System.out.println("Il libro non esiste");
		}
	}
		
	
	
	
	public void libriUtente(String nomeUtente, String cognomeUtente) {
		boolean flag= false;
		for(String key : prestitiNonResi.keySet()) {
			Prestito prestito = prestitiNonResi.get(key);
			if(prestito.getNomeUtente().equals(nomeUtente) && prestito.getCognomeUtente().equals(cognomeUtente)) {
				String key2 = prestito.getIdLibro();
				Libro libro = catalogoLibri.get(key2);
				System.out.println("L'utente " + nomeUtente + " " + cognomeUtente + " ha in prestito il libro " + libro.getTitoloLibro());
				flag=true;
			}
		}
		if(!flag) {				
			System.out.println("L'utente " + nomeUtente + " " + cognomeUtente + " non ha in prestito alcun libro.");
		}
	}
	
	
	
	
	
	public void storicoUtente(String nomeUtente, String cognomeUtente) {
		boolean flag = false;
		System.out.println("L'utente " + nomeUtente + " " + cognomeUtente + " ha in prestito i libri:");
		for(Prestito prestito : elencoPrestiti) {
			if(prestito.getNomeUtente().equals(nomeUtente) && prestito.getCognomeUtente().equals(cognomeUtente)) {
				String idLibro = prestito.getIdLibro();
				Libro libro = catalogoLibri.get(idLibro);
				System.out.println(libro.getTitoloLibro());
				flag = true;
			}
		}
		if(!flag) {
			System.out.println("Nessuno");
		}
	}
	
	
	
	
	public void utenteInPrestito(String titoloLibro) {
		boolean flag;
		for(String key1 : catalogoLibri.keySet()) {
			flag = false;
			Libro libro = catalogoLibri.get(key1);
			if (libro.getTitoloLibro().contains(titoloLibro)) {
				if(prestitiNonResi.containsKey(key1)) {
					Prestito prestito = prestitiNonResi.get(key1);
					System.out.println("L'utente " + prestito.getNomeUtente() + " " + prestito.getCognomeUtente() + " è in possesso del libro " + libro.getTitoloLibro());
					flag = true;
				}
				if(!flag) {
					System.out.println("Il libro "+ libro.getTitoloLibro() + " NON è in prestito");
				}
			}
		}
	}
	
	
	
	
	
	public void tempoPrestitoMassimo(String titoloLibro) {
		
		Date dataAttuale = new Date(); 
		for(String key: catalogoLibri.keySet()) {
			Libro libro=catalogoLibri.get(key);
			if(libro.getTitoloLibro().contains(titoloLibro)) {
				long massimo = 0;
				for(Prestito prestito : elencoPrestiti) {
					if(prestito.getIdLibro().contentEquals(key)) {
						if(inPrestito(prestito.getFinePrestito())) {
							long differenzaDate = Math.abs(dataAttuale.getTime()-prestito.getInizioPrestito().getTime());
							if(differenzaDate > massimo) {
								massimo = differenzaDate;
							}
						}
						else {
							long differenzaDate = Math.abs(prestito.getFinePrestito().getTime()-prestito.getInizioPrestito().getTime());
							if(differenzaDate > massimo) {
								massimo = differenzaDate;
							}
						}
					}	
				}
				
				if(massimo == 0) {
					System.out.println("Il libro "+ libro.getTitoloLibro() + " non è mai stato preso in prestito");
				}
				else {
					System.out.println("Il libro "+ libro.getTitoloLibro() + " è stato preso in prestito per un periodo massimo di " + massimo/(1000*60*60*24) + " giorni");
				}
			}
		}
	}
	

	
	
}
