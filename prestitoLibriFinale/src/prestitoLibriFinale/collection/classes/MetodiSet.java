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
import java.util.HashSet;
import java.util.stream.Collectors;

import prestitoLibriFinale.collection.interfaces.InterfacciaGestioneCatalogo;

public class MetodiSet  implements InterfacciaGestioneCatalogo{
	
	private HashSet<Libro> catalogoLibri;
	private Collection<Prestito> elencoPrestiti;
	private HashSet<String> idPrestitiNonResi;
	
	public MetodiSet() {
		Path percorsoCatalogo = Paths.get("Catalogo.txt");
		Collection<String> lineeLibri;
		HashSet<Libro> catalogoLibri1= new HashSet<Libro>();
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
			catalogoLibri1.add(libro);
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.catalogoLibri= catalogoLibri1;
		Collection<Prestito> elencoPrestiti1 = new ArrayList<Prestito>();//////////
		HashSet<String> idPrestitiNonResi1= new HashSet<String>();//////////
		Path percorsoPrestiti = Paths.get("Prestiti.txt");
		Collection<String> lineePrestiti;
			try {
				lineePrestiti = Files.lines(percorsoPrestiti).collect(Collectors.toList());
				for(String l : lineePrestiti) {
					String[] parole= l.split(";");
					Date inizioPrestito;
					inizioPrestito = new SimpleDateFormat("dd/MM/yyyy").parse(parole[3]);
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					/*
					formatter.setLenient(false);
					usato per trovare un errore nee dati in input su Prestiti
					*/
					Date finePrestito = new Date();
					if(parole[4].contentEquals("0")) {
						finePrestito = formatter.parse("01/01/1800");
					}
					else {
						finePrestito = formatter.parse(parole[4]);
					}
					Prestito prestito = new Prestito(parole[0], parole[1], parole[2], inizioPrestito, finePrestito);
					elencoPrestiti1.add(prestito);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.elencoPrestiti=elencoPrestiti1;
			for(Prestito prestito : elencoPrestiti) {
				if(inPrestito(prestito.getFinePrestito())) {
					idPrestitiNonResi1.add(prestito.getIdLibro());
				}
			}	
			this.idPrestitiNonResi=idPrestitiNonResi1;
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
		for(Libro libro : catalogoLibri) {
			if (libro.getTitoloLibro().contains(titoloLibro)) {
				flag=true;
				if (idPrestitiNonResi.contains(libro.getIdLibro())) {
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
		for(Prestito prestito : elencoPrestiti) {
			if(prestito.getNomeUtente().equals(nomeUtente) && prestito.getCognomeUtente().equals(cognomeUtente)&& idPrestitiNonResi.contains(prestito.getIdLibro())) {
				flag=true;
				for(Libro libro : catalogoLibri) {
					if(libro.getIdLibro().equals(prestito.getIdLibro())) {
						System.out.println("L'utente " + nomeUtente + " " + cognomeUtente + " ha in prestito il libro " + libro.getTitoloLibro());
						break;
					}
				}
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
				for(Libro libro : catalogoLibri) {
					if(libro.getIdLibro().equals(prestito.getIdLibro())) {
						System.out.println(libro.getTitoloLibro());
						flag = true;
						break;
					}
				}
			}
		}
		if(!flag) {
			System.out.println("Nessuno");
		}
	}
	
	
	public void utenteInPrestito(String titoloLibro) {
		boolean flag = false;
		for(Libro libro : catalogoLibri) {
			if (libro.getTitoloLibro().contains(titoloLibro)) {
				flag=true;
				if(idPrestitiNonResi.contains(libro.getIdLibro())) {
					for(Prestito prestito : elencoPrestiti) {
						if(prestito.getIdLibro().equals(libro.getIdLibro())&&inPrestito(prestito.getFinePrestito())) {
							System.out.println("L'utente " + prestito.getNomeUtente() + " " + prestito.getCognomeUtente() + " è in possesso del libro " + libro.getTitoloLibro());
							break;
						}
					}
				}
				else {
					System.out.println("Il libro "+ libro.getTitoloLibro() + " NON è in prestito");
				}
			}
		}
		if(!flag) {
			System.out.println("Il libro "+ titoloLibro + " non esiste.");

		}
	}
	
	public void tempoPrestitoMassimo(String titoloLibro) {
		Date dataAttuale = new Date(); 
		for(Libro libro: catalogoLibri) {
			if(libro.getTitoloLibro().contains(titoloLibro)) {
				long massimo = 0;	
				for(Prestito prestito : elencoPrestiti) {
					if(prestito.getIdLibro().contentEquals(libro.getIdLibro())) {
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

