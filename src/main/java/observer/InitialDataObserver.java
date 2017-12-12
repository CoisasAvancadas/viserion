package observer;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.caelum.vraptor.events.VRaptorInitialized;
import model.Usuario;
import java.util.Date;
import javax.persistence.Persistence;
import javax.persistence.Query;

@Dependent
public class InitialDataObserver {

    public void insert(@Observes VRaptorInitialized event) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ViserionPU");
        EntityManager manager = null;

        try {

            manager = factory.createEntityManager();
            manager.getTransaction().begin();

            Query query = manager.createQuery("SELECT COUNT(u) FROM Usuario u");
            long userCount = (Long)query.getSingleResult();
            
            if (userCount < 1) {
                Usuario defaultUser = new Usuario();
                defaultUser.setUsername("admin");
                defaultUser.setPassword("admin");
                defaultUser.setEmail("djcleitonrasta@hotmail.com.br");
                defaultUser.setNome("Edivaldo Pericleiton Rasta");
                manager.persist(defaultUser);
                manager.getTransaction().commit();
            }

        } finally {
            if (manager != null && manager.isOpen()) {
                manager.close();
            }
        }
    }
}
