/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.enterprise.context.RequestScoped;
import model.ContaBancaria;

@RequestScoped
public class ContaBancariaDAO  extends GenericDAO<Integer, ContaBancaria> {

    public ContaBancariaDAO() {
        super();
    }
}