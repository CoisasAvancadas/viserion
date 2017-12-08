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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author divensi
 */
@Entity
@Table(name = "tb_tipo_atividade")
public class TipoAtividade implements Serializable {

    @OneToMany(mappedBy = "tipoAtividade")
    private List<Atividade> atividades;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    private String nome;

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

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }
    
    public void addAtividades(Atividade atividade) {
        this.atividades.add(atividade);
    }
    
    public TipoAtividade clone() {
        TipoAtividade x = new TipoAtividade();
        x.setId(this.getId());
        x.setNome(this.getNome());
        return x;
    }
}
