package observer;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.caelum.vraptor.events.VRaptorInitialized;
import model.Usuario;
import java.util.Date;
import javax.persistence.Persistence;
import model.ContaBancaria;

@Dependent
public class InitialDataObserver {

    public void insert(@Observes VRaptorInitialized event) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ViserionPU");
        EntityManager manager = null;

        try {

            manager = factory.createEntityManager();
            manager.getTransaction().begin();

            //Usuario defaultUser = new Usuario();
            //defaultUser.setNome("Fulano de Tal");
            //defaultUser.setDataNascimentoByDate(new Date());

            //manager.persist(defaultUser);
            
            ContaBancaria conta = new ContaBancaria();
            conta.setAgencia("6666-6");
            conta.setNumero("66666-6");
            
            manager.persist(conta);
            
            manager.getTransaction().commit();

        } finally {
            if (manager != null && manager.isOpen()) {
                manager.close();
            }
        }
    }
}
