/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "tb_atividade")
public class Atividade implements Serializable {

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Evento evento;
    
    private boolean autoinscreve;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    private String nome;
    
    private String horaInicio; 
        
    private String horaFim; 
    
    @Temporal(TemporalType.DATE)
    @Future
    private Date data;
    
    @ManyToMany(mappedBy = "atividades")
    private Collection<Usuario> palestrantes;
    
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Sala sala;
    
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private TipoAtividade tipoAtividade;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isAutoinscreve() {
        return autoinscreve;
    }

    public void setAutoinscreve(boolean autoinscreve) {
        this.autoinscreve = autoinscreve;
    }

    public TipoAtividade getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(TipoAtividade tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
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

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public Collection<Usuario> getPalestrantes() {
        return palestrantes;
    }

    public void setPalestrantes(Collection<Usuario> palestrantes) {
        this.palestrantes = palestrantes;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
    
    public Atividade clone() {
        Atividade x = new Atividade();
        x.setId(this.id);
        x.setNome(this.nome);
        x.setHoraInicio(this.horaInicio);
        x.setHoraFim(this.horaFim);
        x.setPalestrantes(this.palestrantes);
        x.setSala(this.sala);
        x.setEvento(this.evento);
        x.setAutoinscreve(this.isAutoinscreve());
        return x;
    }
}
