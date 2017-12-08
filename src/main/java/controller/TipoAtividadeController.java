package controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;
import dao.TipoAtividadeDAO;
import interceptor.Public;
import interceptor.UserInfo;
import java.util.List;
import model.TipoAtividade;
import validation.LoginAvailable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@Path("/tipoAtividade")
public class TipoAtividadeController {

    private final Result result;
    private final Validator validator;
    private final UserInfo userInfo;
    private final TipoAtividadeDAO tipoAtividadeDAO;

    protected TipoAtividadeController() {
        this(null, null, null, null);
    }

    @Inject
    public TipoAtividadeController(TipoAtividadeDAO tipoAtividadeDAO, UserInfo userInfo, Result result, Validator validator) {
        this.tipoAtividadeDAO = tipoAtividadeDAO;
        this.result = result;
        this.validator = validator;
        this.userInfo = userInfo;
    }
    
    @Post
    @Public
    public void add(@Valid @LoginAvailable TipoAtividade tipoAtividade) {
        validator.onErrorUsePageOf(HomeController.class).login();

        tipoAtividadeDAO.save(tipoAtividade);

        // you can add objects to result even in redirects. Added objects will
        // survive one more request when redirecting.
        result.include("notice", "TipoAtividade " + tipoAtividade.getNome()+ " adicionado com sucesso");
        result.redirectTo(HomeController.class).login();
    }

    @Get(value = {"/novo", "/editar/{id}"})
    public TipoAtividade form(int id) {
        return (id > 0) ? tipoAtividadeDAO.getById(id) : null;
    }
    
    @Get(value = {"", "/"})
    public List<TipoAtividade> list() {
        
        return tipoAtividadeDAO.findAll();
    }
    
    @Get(value = {"/{id}"})
    public TipoAtividade view(int id) {
        return tipoAtividadeDAO.getById(id);
    }

    @Post
    public TipoAtividade form(TipoAtividade tipoAtividade) {
        return tipoAtividade;
    }

    @IncludeParameters
    public void save(@NotNull @Valid TipoAtividade tipoAtividade) {
        //if(person.getNome() == null || person.getNome().trim().equals(""))
        //validator.add(new SimpleMessage("nome", "O nome deve ser preenchido"));
        validator.onErrorForwardTo(this).form(tipoAtividade);

        if (tipoAtividade.getId() > 0) {
            tipoAtividadeDAO.update(tipoAtividade);
        } else {
            tipoAtividadeDAO.save(tipoAtividade);
        }

        // Redireciona para a página de listagem
        result.redirectTo(TipoAtividadeController.class).list();
    }
    
    @Get(value = {"/apagar/{id}"})
    public TipoAtividade delete(int id) {
        return tipoAtividadeDAO.getById(id);
    }
    
    @Post(value = {"/apagar/{id}"})
    public void delete(TipoAtividade tipoAtividade) {
        tipoAtividadeDAO.delete(tipoAtividade);
        
        result.forwardTo(this.getClass()).list();
    }
}
