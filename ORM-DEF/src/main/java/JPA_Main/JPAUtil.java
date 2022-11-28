package JPA_Main;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {//Arxiu de fa referència cap a persistence.xml i ens ajuda amb la connexió amb la BDD.
	
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";//El nom fa referència a l'arxiu persistence.xml (línia 7).
	private static EntityManagerFactory factory; //Connexió cap a la bdd

	public static EntityManagerFactory getEntityManagerFactory() {
		
		if (factory==null) { //Si factory és null li passa tota la configuració de la bdd.
			
			factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		
		return factory;				
	}
	
	public static void shutdown() {
		
		if (factory!=null) {
			
			factory.close();
		}		
	
	}
}

