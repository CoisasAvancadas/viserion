/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author divensi
 */
@Entity
@Table(name = "tb_inscricao_evento")
public class InscricaoEvento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    private Evento evento;
    
    @ManyToOne
    private Usuario usuario;
        
    @ManyToMany
    private Collection<PapelInscricao> papelInscricoes;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Collection<PapelInscricao> getPapelInscricoes() {
        return papelInscricoes;
    }

    public void setPapelInscricoes(Collection<PapelInscricao> papelInscricoes) {
        this.papelInscricoes = papelInscricoes;
    }

    
    public InscricaoEvento clone() {
        InscricaoEvento x = new InscricaoEvento();
        x.setId(this.getId());
        x.setEvento(this.getEvento());
        x.setUsuario(this.getUsuario());
        x.setPapelInscricoes(this.getPapelInscricoes());
        return x;
    }
}
