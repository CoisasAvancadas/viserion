package dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.eclipse.persistence.sessions.Session;

public abstract class GenericDAO<PK, T> {

    @Inject
    protected EntityManager entityManager;

    public T getById(PK pk) {
        return (T) entityManager.find(getTypeClass(), pk);
    }

    public long count() {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(c) FROM " + getTypeClass().getSimpleName() + " c", Long.class);
        return query.getSingleResult();
    }

    public boolean contains(T entity) {
        return entityManager.contains(entity);
    }

    public void save(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    public void update(T entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    public void delete(T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    public List<T> findAll() {
        return entityManager.createQuery(("SELECT e FROM " + getTypeClass().getSimpleName() + " e")).getResultList();
    }

    private Class<?> getTypeClass() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        return clazz;
    }

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }
    
    public void refresh(T obj) {
        getSession().refreshObject(obj);
    }
      
}
