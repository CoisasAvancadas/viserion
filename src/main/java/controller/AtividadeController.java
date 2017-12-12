package controller;

import br.com.caelum.vraptor.AroundCall;
import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import dao.AtividadeDAO;
import dao.EventoDAO;
import interceptor.Public;
import interceptor.UserInfo;
import model.Atividade;
import validation.LoginAvailable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import model.Evento;

@Controller
@Path("/")
public class AtividadeController {

    private final Result result;
    private final Validator validator;
    private final UserInfo userInfo;
    private final AtividadeDAO atividadeDAO;
    private final EventoDAO eventoDAO;

    protected AtividadeController() {
        this(null, null, null, null, null);
    }

    @Inject
    public AtividadeController(AtividadeDAO atividadeDAO, EventoDAO eventoDAO, UserInfo userInfo, Result result, Validator validator) {
        this.eventoDAO = eventoDAO;
        this.atividadeDAO = atividadeDAO;
        this.result = result;
        this.validator = validator;
        this.userInfo = userInfo;
    }
    
    @Post(value = {"/evento/{EventoId}/atividade/add"})
    @Public
    public void add(@Valid @LoginAvailable Atividade atividade) {
        atividadeDAO.save(atividade);

        result.include("notice", "Atividade " + atividade.getNome()+ " adicionado com sucesso");
    }

    @Get(value = {"/evento/{EventoId}/atividade/novo", "/evento/{EventoId}/atividade/editar/{AtividadeId}"})
    public Atividade form(int EventoId, int AtividadeId) {
        result.include("EventoId", EventoId);
        return (AtividadeId > 0) ? atividadeDAO.getById(AtividadeId) : null;
    }
    
    @AroundCall
    @Get(value = {"/evento/{EventoId}/atividade"})
    public void list(int EventoId) {
        Evento evento = eventoDAO.getById(EventoId);
        result.include("evento", evento);
    }
    
    @Get(value = {"/evento/{EventoId}/atividade/{id}"})
    public Atividade view(int id) {
        return atividadeDAO.getById(id);
    }

    @Post(value = {"/evento/{EventoId}/atividade"})
    public Atividade form(Atividade atividade) {
        return atividade;
    }

    @IncludeParameters
    @Post(value = {"/evento/{EventoId}/atividade/save"})
    public void save(int EventoId, @NotNull @Valid Atividade atividade) {
        //if(person.getNome() == null || person.getNome().trim().equals(""))
        //validator.add(new SimpleMessage("nome", "O nome deve ser preenchido"));
        validator.onErrorForwardTo(this).form(atividade);
        
        atividade.setEvento(eventoDAO.getById(EventoId));

        if (atividade.getId() > 0) {
            atividadeDAO.update(atividade);
        } else {
            atividadeDAO.save(atividade);
        }
        
        result.include("notice", "Atividade " + atividade.getNome()+ " salvo com sucesso");

        // Redireciona para a página de listagem
        result.redirectTo(AtividadeController.class).list(EventoId);
    }
    
    @Get(value = {"/evento/{EventoId}/atividade/apagar/{AtividadeId}"})
    public Atividade delete(int AtividadeId) {
        return atividadeDAO.getById(AtividadeId);
    }
    
    @Post(value = {"/evento/{EventoId}/atividade/apagar/{AtividadeId}"})
    public void delete(int EventoId, Atividade atividade) {
        atividadeDAO.delete(atividade);
        result.forwardTo(this.getClass()).list(EventoId);
    }
}
