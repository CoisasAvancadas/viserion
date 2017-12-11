package observer;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.caelum.vraptor.events.VRaptorInitialized;
import model.Usuario;
import java.util.Date;
import javax.persistence.Persistence;

@Dependent
public class InitialDataObserver {

    public void insert(@Observes VRaptorInitialized event) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ViserionPU");
        EntityManager manager = null;

        try {

            manager = factory.createEntityManager();
            manager.getTransaction().begin();

            //Usuario defaultUser = new Usuario();
            //defaultUser.setUsername("user");
            //defaultUser.setPassword("user");
            //defaultUser.setNome("User da Silva");
            
            
            //manager.persist(defaultUser);
            
            manager.getTransaction().commit();

        } finally {
            if (manager != null && manager.isOpen()) {
                manager.close();
            }
        }
    }
}
