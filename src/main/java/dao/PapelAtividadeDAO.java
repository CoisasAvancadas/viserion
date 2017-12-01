/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import model.PapelAtividade;
import javax.enterprise.context.RequestScoped;
/**
 *
 * @author divensi
 */
@RequestScoped
public class PapelAtividadeDAO extends GenericDAO<Integer, PapelAtividade> {

    public PapelAtividadeDAO() {
        super();
    }

}
