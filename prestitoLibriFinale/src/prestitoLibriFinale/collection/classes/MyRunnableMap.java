package prestitoLibriFinale.collection.classes;

public class MyRunnableMap extends MetodiMap implements Runnable {
	private int i;
	private String titoloLibro;
	private String nomeUtente;
	private String cognomeUtente;
	
	public MyRunnableMap(int i, String titoloLibro){
		this.i = i;
		this.titoloLibro = titoloLibro;
	}
	

	public MyRunnableMap(int i, String nomeUtente, String cognomeUtente){
		this.i = i;
		this.nomeUtente = nomeUtente;
		this.cognomeUtente = cognomeUtente;
	}
	
	@Override
    public void run() {

		long tempo0 = System.currentTimeMillis();

		
		switch(i) {
		case 1:
			daRestituire(titoloLibro);
			break;
		case 2:
			libriUtente(nomeUtente, cognomeUtente);
			break;
		case 3:
			storicoUtente(nomeUtente, cognomeUtente);
			break;
		case 4:
			utenteInPrestito(titoloLibro);
			break;
		case 5:
			tempoPrestitoMassimo(titoloLibro);
			break;
		}

	
		long tempoImpiegato= System.currentTimeMillis()-tempo0;
		
		
		try {
			Thread.sleep(15);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Il metodo che utilizza le HashMap ha impegato: " + tempoImpiegato + " millesecondo/i");
		}
}
