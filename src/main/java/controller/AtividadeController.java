package controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;
import dao.AtividadeDAO;
import interceptor.Public;
import interceptor.UserInfo;
import java.util.List;
import model.Atividade;
import validation.LoginAvailable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@Path("/atividade")
public class AtividadeController {

    private final Result result;
    private final Validator validator;
    private final UserInfo userInfo;
    private final AtividadeDAO atividadeDAO;

    protected AtividadeController() {
        this(null, null, null, null);
    }

    @Inject
    public AtividadeController(AtividadeDAO atividadeDAO, UserInfo userInfo, Result result, Validator validator) {
        this.atividadeDAO = atividadeDAO;
        this.result = result;
        this.validator = validator;
        this.userInfo = userInfo;
    }
    
    @Post
    @Public
    public void add(@Valid @LoginAvailable Atividade atividade) {
        validator.onErrorUsePageOf(HomeController.class).login();

        atividadeDAO.save(atividade);

        // you can add objects to result even in redirects. Added objects will
        // survive one more request when redirecting.
        result.include("notice", "Atividade " + atividade.getNome()+ " adicionado com sucesso");
        result.redirectTo(HomeController.class).login();
    }

    @Get(value = {"/novo", "/editar/{id}"})
    public Atividade form(int id) {
        return (id > 0) ? atividadeDAO.getById(id) : null;
    }
    
    @Get(value = {"", "/"})
    public List<Atividade> list() {
        
        return atividadeDAO.findAll();
    }
    
    @Get(value = {"/{id}"})
    public Atividade view(int id) {
        return atividadeDAO.getById(id);
    }

    @Post
    public Atividade form(Atividade atividade) {
        return atividade;
    }

    @IncludeParameters
    public void save(@NotNull @Valid Atividade atividade) {
        //if(person.getNome() == null || person.getNome().trim().equals(""))
        //validator.add(new SimpleMessage("nome", "O nome deve ser preenchido"));
        validator.onErrorForwardTo(this).form(atividade);

        if (atividade.getId() > 0) {
            atividadeDAO.update(atividade);
        } else {
            atividadeDAO.save(atividade);
        }

        // Redireciona para a página de listagem
        result.redirectTo(AtividadeController.class).list();
    }
    
    @Get(value = {"/apagar/{id}"})
    public Atividade delete(int id) {
        return atividadeDAO.getById(id);
    }
    
    @Post(value = {"/apagar/{id}"})
    public void delete(Atividade atividade) {
        atividadeDAO.delete(atividade);
        
        result.forwardTo(this.getClass()).list();
    }
}
