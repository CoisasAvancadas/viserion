/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author divensi
 */
@Entity
public class PapelSistema extends Papel implements Serializable {

    public PapelSistema clone() {
        PapelSistema x = new PapelSistema();
        x.setId(this.getId());
        x.setNome(this.getNome());
        return x;
    }
}
