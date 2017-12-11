/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.Collection;
import java.util.List;
import model.Atividade;
import javax.enterprise.context.RequestScoped;
/**
 *
 * @author divensi
 */
@RequestScoped
public class AtividadeDAO extends GenericDAO<Integer, Atividade> {

    public AtividadeDAO() {
        super();
    }
    
    public List<Atividade> findAllByEvento(int EventoId) {
        return entityManager.createQuery((" SELECT a FROM Atividade a WHERE a.evento.id = " + EventoId + " ")).getResultList();
    }

}
