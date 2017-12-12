package controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;
import dao.EventoDAO;
import dao.AtividadeDAO;
import dao.InstituicaoDAO;
import interceptor.Public;
import interceptor.UserInfo;
import java.util.List;
import model.Evento;
import validation.LoginAvailable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import model.Instituicao;

@Controller
@Path("/")
public class EventoController {

    private final Result result;
    private final Validator validator;
    private final UserInfo userInfo;
    private final EventoDAO eventoDAO;
    private final InstituicaoDAO instituicaoDAO;
    private final AtividadeDAO atividadeDAO;

    protected EventoController() {
        this(null, null, null, null, null, null);
    }

    @Inject
    public EventoController(EventoDAO eventoDAO, AtividadeDAO atividadeDAO,
            InstituicaoDAO instituicaoDAO,
            UserInfo userInfo, Result result, Validator validator) {
        this.instituicaoDAO = instituicaoDAO;
        this.eventoDAO = eventoDAO;
        this.atividadeDAO = atividadeDAO;
        this.result = result;
        this.validator = validator;
        this.userInfo = userInfo;
    }
    
    @Post(value = "/evento")
    @Public
    public void add(@Valid @LoginAvailable Evento evento) {
        eventoDAO.save(evento);

        // you can add objects to result even in redirects. Added objects will
        // survive one more request when redirecting.
        result.include("notice", "Evento " + evento.getNome()+ " adicionado com sucesso");
    }

    @Get(value = {"/evento/novo", "/evento/editar/{id}"})
    public Evento form(int id) {
        result.include("instituicoes", instituicaoDAO.findAll());
        return (id > 0) ? eventoDAO.getById(id) : null;
    }
    
    @Get(value = {"/evento", "/evento/"})
    public void list() {
        result.include("eventos", eventoDAO.findAll());
    }
    
    @Get(value = {"/instituicao/{InstituicaoId}/evento", "/instituicao/{InstituicaoId}/evento/"})
    public void list(int InstituicaoId) {
        Instituicao instituicao = instituicaoDAO.getById(InstituicaoId);
        result.include("instituicao", instituicao);
        result.include("eventos", instituicao.getEventos());
    }

    
    @Get(value = {"/evento/{id}"})
    public void view(int id) {
        result.include("evento", eventoDAO.getById(id));
    }

    @Post("/evento")
    public void form(Evento evento) {
        result.include("instituicoes", instituicaoDAO.findAll());
        result.include("evento", evento);
    }

    @Path("/evento/save")
    @IncludeParameters
    public void save(@NotNull @Valid Evento evento) {
        //if(person.getNome() == null || person.getNome().trim().equals(""))
        //validator.add(new SimpleMessage("nome", "O nome deve ser preenchido"));
        validator.onErrorForwardTo(this).form(evento);

        Evento eventoOld = eventoDAO.getById(evento.getId());
        
        if (eventoOld != null) {
            eventoOld = evento;
            eventoDAO.update(eventoOld);
        } else {
            eventoDAO.save(evento);
        }
        
//        if (evento.getId() > 0) {
//            eventoDAO.update(evento);
//        } else {
//            eventoDAO.save(evento);
//            //Instituicao instituicao = instituicaoDAO.getById(evento.getInstituicao().getId());
//            //instituicao.AddEventos(evento);
//            //instituicaoDAO.update(instituicao);
//        }

        // Redireciona para a página de listagem
        result.redirectTo(EventoController.class).list();
    }
    
    @Get(value = {"/evento/apagar/{EventoId}"})
    public Evento delete(int EventoId) {
        return eventoDAO.getById(EventoId);
    }
    
    @Post(value = {"/evento/apagar/{EventoId}"})
    public void delete(int EventoId, Evento evento) {
        eventoDAO.delete(evento);
        
        result.forwardTo(this.getClass()).list();
    }

}
