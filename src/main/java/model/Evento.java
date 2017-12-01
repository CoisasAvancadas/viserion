/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

/**
 *
 * @author divensi
 */
@Entity
@Table(name = "tb_evento")
public class Evento implements Serializable {

    @OneToMany(mappedBy = "evento")
    private List<InscricaoEvento> inscricaoEventos;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nome;
    
    private String descricao;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @Future
    private Calendar dataInicio; 
        
    @NotNull
    @Temporal(TemporalType.DATE)
    @Future
    private Calendar dataFim;
    
    @OneToMany
    private Collection<Usuario> responsavel;
    
    @OneToMany(mappedBy = "evento")
    private Collection<Atividade> atividades;
    
    private boolean ativo;
    
    @ManyToOne
    private Instituicao instituicao;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
    
    
    
    @Lob
    private byte[] logo;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Calendar getDataFim() {
        return dataFim;
    }

    public void setDataFim(Calendar dataFim) {
        this.dataFim = dataFim;
    }

    public Collection<Usuario> getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Collection<Usuario> responsavel) {
        this.responsavel = responsavel;
    }

    public Collection<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(Collection<Atividade> atividades) {
        this.atividades = atividades;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }
    
    public Evento clone() {
        Evento x = new Evento();
        x.setId(this.id);
        x.setNome(this.nome);
        x.setDescricao(this.descricao);
        x.setDataInicio(this.dataInicio);
        x.setDataFim(this.dataFim);
        x.setResponsavel(this.responsavel);
        x.setAtividades(this.atividades);
        x.setLogo(this.logo);
        x.setAtivo(this.ativo);
        return x;
    }
}
