/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import model.Permissao;
import javax.enterprise.context.RequestScoped;
/**
 *
 * @author divensi
 */
@RequestScoped
public class PermissaoDAO extends GenericDAO<Integer, Permissao> {

    public PermissaoDAO() {
        super();
    }

}
