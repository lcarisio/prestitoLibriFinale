package prestitoLibriFinale.collection.interfaces;

import java.util.Date;

public interface InterfacciaGestioneCatalogo{
	
	public boolean inPrestito(Date d);
	public void daRestituire(String titoloLibro);
	public void libriUtente(String nomeUtente, String cognomeUtente);
	public void storicoUtente(String nomeUtente, String cognomeUtente);
	public void utenteInPrestito(String titoloLibro);
	public void tempoPrestitoMassimo(String titoloLibro);

}