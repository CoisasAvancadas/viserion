/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author divensi
 */
@Entity
@Table(name = "tb_intituicao")
public class Instituicao implements Serializable {

    @OneToMany(mappedBy = "instituicao", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Collection<Sala> salas;

    @OneToMany(mappedBy = "instituicao", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Collection<Evento> eventos;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    private String nome;
    
    @OneToOne()
    private Endereco endereco;
    
    @NotNull
    private String cnpj;

    public Collection<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(Collection<Evento> eventos) {
        this.eventos = eventos;
    }
    
    public void AddEventos(Evento evento) {
        this.eventos.add(evento);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Collection<Sala> getSalas() {
        return salas;
    }

    public void setSalas(Collection<Sala> salas) {
        this.salas = salas;
    }
    
    public void addSalas(Sala sala) {
        this.salas.add(sala);
    }
    
    public Instituicao clone() {
        Instituicao x = new Instituicao();
        x.setId(this.id);
        x.setNome(this.nome);
        x.setEndereco(this.endereco);
        x.setCnpj(this.cnpj);
        return x;
    }
}
