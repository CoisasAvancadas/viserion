package model;

import java.io.File;
import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

    @ManyToMany
    private List<Atividade> atividades;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int ra;
    
    @NotNull
    @Length(min = 3, max = 100)
    private String nome;
    
    @Temporal(TemporalType.DATE)
    @Past
    private Date nascimento;

    @Column(unique = true)
    private String email;
    
    @Column(unique = true)
    @NotNull
    @Length(min = 3, max = 20)
    @Pattern(regexp = "[a-z0-9_]+", message = "{invalid_user}")
    private String username;
    
    @NotNull
    @Length(min = 4, max = 100)
    private String password;
    
    private String cpf;
    
    private String rg;
    
    @Column(columnDefinition="mediumblob")
    private File foto;
    
    @ManyToMany
    private Collection<RedeSocial> redesSociais = new ArrayList<>();
    
    @OneToOne()
    private Endereco endereco;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Curriculo curriculo;
        
    @ManyToOne
    private PapelSistema papel;
    
    @OneToMany
    private Collection<InscricaoEvento> inscricaoEventos;
    
    @OneToMany
    private Collection<InscricaoAtividade> inscricaoAtividades;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PapelSistema getPapel() {
        return papel;
    }

    public void setPapel(PapelSistema papel) {
        this.papel = papel;
    }

    public Collection<InscricaoEvento> getInscricaoEventos() {
        return inscricaoEventos;
    }

    public void setInscricaoEventos(Collection<InscricaoEvento> inscricaoEventos) {
        this.inscricaoEventos = inscricaoEventos;
    }

    public void addInscricaoEventos(InscricaoEvento inscricaoEvento) {
        this.inscricaoEventos.add(inscricaoEvento);
    }
    
    public Collection<InscricaoAtividade> getInscricaoAtividades() {
        return inscricaoAtividades;
    }

    public void setInscricaoAtividades(Collection<InscricaoAtividade> inscricaoAtividades) {
        this.inscricaoAtividades = inscricaoAtividades;
    }
    
    public void addInscricaoAtividades(InscricaoAtividade inscricaoAtividade) {
        this.inscricaoAtividades.add(inscricaoAtividade);
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

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public File getFoto() {
        return foto;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public Collection<RedeSocial> getRedesSociais() {
        return redesSociais;
    }

    public void setRedesSociais(Collection<RedeSocial> redesSociais) {
        this.redesSociais = redesSociais;
    }
    
    public void addRedesSociais(RedeSocial redeSocial) {
        this.redesSociais.add(redeSocial);
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public Usuario() {
    }

    @Override
    public Usuario clone() {
        Usuario p = new Usuario();
        p.setId(this.id);
        p.setRa(this.ra);
        p.setNome(this.nome);
        p.setNascimento(this.nascimento);
        p.setEmail(this.email);
        p.setUsername(this.username);
        p.setPassword(this.password);
        p.setCpf(this.cpf);
        p.setRg(this.rg);
        p.setFoto(this.foto);
        p.setRedesSociais(this.redesSociais);
        p.setEndereco(this.endereco);
        p.setCurriculo(this.curriculo);
        p.setAtividades(this.atividades);
        p.setInscricaoAtividades(this.inscricaoAtividades);
        p.setInscricaoEventos(this.inscricaoEventos);
        return p;
    }
    
    
}
