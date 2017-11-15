/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import model.RedeSocial;
import javax.enterprise.context.RequestScoped;
/**
 *
 * @author divensi
 */
@RequestScoped
public class RedeSocialDAO extends GenericDAO<Integer, RedeSocial> {

    public RedeSocialDAO() {
        super();
    }

}
