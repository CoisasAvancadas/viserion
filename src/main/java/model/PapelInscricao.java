/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author divensi
 */
@Entity
public class PapelInscricao extends Papel implements Serializable {

    @ManyToMany(mappedBy = "papelInscricoes")
    private List<InscricaoEvento> inscricaoEventos;

    public PapelInscricao clone() {
        PapelInscricao x = new PapelInscricao();
        x.setId(this.getId());
        x.setNome(this.getNome());
        x.setPermissoes(this.getPermissoes());
        return x;
    }
}
