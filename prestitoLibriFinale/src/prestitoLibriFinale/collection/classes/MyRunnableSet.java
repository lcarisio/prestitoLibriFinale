package prestitoLibriFinale.collection.classes;

public class MyRunnableSet extends MetodiSet implements Runnable {
	private Integer i;
	private String titoloLibro;
	private String nomeUtente;
	private String cognomeUtente;
	
	
	public MyRunnableSet(int i, String titoloLibro){
		this.i = i;
		this.titoloLibro = titoloLibro;
	}
	
	public MyRunnableSet(int i, String nomeUtente, String cognomeUtente){
		this.i = i;
		this.nomeUtente = nomeUtente;
		this.cognomeUtente = cognomeUtente;
	}
	
	
	
	@Override
    public void run() {

		long tempo0= System.currentTimeMillis();

		
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
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("");
		System.out.println("Il metodo che utilizza le HashSet ha impegato: " + tempoImpiegato + " millesecondo/i");
		
		}
}
