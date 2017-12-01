/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import model.Papel;
import javax.enterprise.context.RequestScoped;
/**
 *
 * @author divensi
 */
@RequestScoped
public class PapelDAO extends GenericDAO<Integer, Papel> {

    public PapelDAO() {
        super();
    }

}
