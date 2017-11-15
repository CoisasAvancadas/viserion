/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import javax.persistence.Entity;
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
import org.joda.time.LocalTime;

/**
 *
 * @author divensi
 */
@Entity
@Table(name = "tb_atividade")
public class Atividade implements Serializable {

    @ManyToOne
    private Evento evento;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    private String nome;
    
    @NotNull
    @Temporal(TemporalType.TIME)
    @Future
    private Calendar horaInicio; 
        
    @NotNull
    @Temporal(TemporalType.TIME)
    @Future
    private Calendar horaFim; 
    
    @ManyToMany(mappedBy = "atividades")
    private Collection<Usuario> palestrantes;
    
    @ManyToOne
    private Sala sala;

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

    public Calendar getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Calendar horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Calendar getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Calendar horaFim) {
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
        return x;
    }
}
