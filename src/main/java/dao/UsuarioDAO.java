/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import model.Usuario;
import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
/**
 *
 * @author divensi
 */
@RequestScoped
public class UsuarioDAO extends GenericDAO<Integer, Usuario> {

    public UsuarioDAO() {
        super();
    }

    public boolean containsUserWithLogin(String username) {
        Long count = entityManager
                .createQuery("select count(u) from Usuario u where u.username = :username", Long.class)
                .setParameter("username", username)
                .getSingleResult();
        return count > 0;
    }

    public Usuario find(String username, String password) {
        try {
            return entityManager
                    .createQuery("select u from Usuario u where u.username = :username and u.password = :password", Usuario.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
