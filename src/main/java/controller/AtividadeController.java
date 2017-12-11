package controller;

import br.com.caelum.vraptor.AroundCall;
import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;
import dao.AtividadeDAO;
import dao.EventoDAO;
import interceptor.Public;
import interceptor.UserInfo;
import java.util.Collection;
import java.util.List;
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
        validator.onErrorUsePageOf(HomeController.class).login();

        atividadeDAO.save(atividade);

        // you can add objects to result even in redirects. Added objects will
        // survive one more request when redirecting.
        result.include("notice", "Atividade " + atividade.getNome()+ " adicionado com sucesso");
        result.redirectTo(HomeController.class).login();
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
    @Path(value = {"/evento/{EventoId}/atividade/save"})
    public void save(int EventoId, @NotNull @Valid Atividade atividade) {
        //if(person.getNome() == null || person.getNome().trim().equals(""))
        //validator.add(new SimpleMessage("nome", "O nome deve ser preenchido"));
        validator.onErrorForwardTo(this).form(atividade);

        if (atividade.getId() > 0) {
            atividadeDAO.update(atividade);
        } else {
            Evento evento = eventoDAO.getById(EventoId);
            evento.addAtividade(atividade);
            eventoDAO.update(evento);
            //atividadeDAO.save(atividade);
        }

        // Redireciona para a página de listagem
        result.redirectTo(AtividadeController.class).list(EventoId);
    }
    
    @Get(value = {"/evento/{EventoId}/atividade/apagar/{id}"})
    public Atividade delete(int id) {
        return atividadeDAO.getById(id);
    }
    
    @Post(value = {"/evento/{EventoId}/atividade/apagar/{id}"})
    public void delete(int EventoId, Atividade atividade) {
        atividadeDAO.delete(atividade);
        
        result.forwardTo(this.getClass()).list(EventoId);
    }
}
