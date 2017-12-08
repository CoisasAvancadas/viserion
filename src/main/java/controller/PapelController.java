package controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;
import dao.PapelDAO;
import interceptor.Public;
import interceptor.UserInfo;
import java.util.List;
import model.Papel;
import validation.LoginAvailable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@Path("/papel")
public class PapelController {

    private final Result result;
    private final Validator validator;
    private final UserInfo userInfo;
    private final PapelDAO papelDAO;

    protected PapelController() {
        this(null, null, null, null);
    }

    @Inject
    public PapelController(PapelDAO papelDAO, UserInfo userInfo, Result result, Validator validator) {
        this.papelDAO = papelDAO;
        this.result = result;
        this.validator = validator;
        this.userInfo = userInfo;
    }
    
    @Post
    @Public
    public void add(@Valid @LoginAvailable Papel papel) {
        validator.onErrorUsePageOf(HomeController.class).login();

        papelDAO.save(papel);

        // you can add objects to result even in redirects. Added objects will
        // survive one more request when redirecting.
        result.include("notice", "Papel " + papel.getNome()+ " adicionado com sucesso");
        result.redirectTo(HomeController.class).login();
    }

    @Get(value = {"/novo", "/editar/{id}"})
    public Papel form(int id) {
        return (id > 0) ? papelDAO.getById(id) : null;
    }
    
    @Get(value = {"", "/"})
    public List<Papel> list() {
        
        return papelDAO.findAll();
    }
    
    @Get(value = {"/{id}"})
    public Papel view(int id) {
        return papelDAO.getById(id);
    }

    @Post
    public Papel form(Papel papel) {
        return papel;
    }

    @IncludeParameters
    public void save(@NotNull @Valid Papel papel) {
        //if(person.getNome() == null || person.getNome().trim().equals(""))
        //validator.add(new SimpleMessage("nome", "O nome deve ser preenchido"));
        validator.onErrorForwardTo(this).form(papel);

        if (papel.getId() > 0) {
            papelDAO.update(papel);
        } else {
            papelDAO.save(papel);
        }

        // Redireciona para a página de listagem
        result.redirectTo(PapelController.class).list();
    }
    
    @Get(value = {"/apagar/{id}"})
    public Papel delete(int id) {
        return papelDAO.getById(id);
    }
    
    @Post(value = {"/apagar/{id}"})
    public void delete(Papel papel) {
        papelDAO.delete(papel);
        
        result.forwardTo(this.getClass()).list();
    }
}
