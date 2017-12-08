package controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;
import dao.CurriculoDAO;
import interceptor.Public;
import interceptor.UserInfo;
import java.util.List;
import model.Curriculo;
import validation.LoginAvailable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@Path("/curriculo")
public class CurriculoController {

    private final Result result;
    private final Validator validator;
    private final UserInfo userInfo;
    private final CurriculoDAO curriculoDAO;

    protected CurriculoController() {
        this(null, null, null, null);
    }

    @Inject
    public CurriculoController(CurriculoDAO curriculoDAO, UserInfo userInfo, Result result, Validator validator) {
        this.curriculoDAO = curriculoDAO;
        this.result = result;
        this.validator = validator;
        this.userInfo = userInfo;
    }
    
    @Post
    @Public
    public void add(@Valid @LoginAvailable Curriculo curriculo) {
        validator.onErrorUsePageOf(HomeController.class).login();

        curriculoDAO.save(curriculo);

        // you can add objects to result even in redirects. Added objects will
        // survive one more request when redirecting.
        result.include("notice", "Curriculo " + curriculo.getTitulo()+ " adicionado com sucesso");
        result.redirectTo(HomeController.class).login();
    }

    @Get(value = {"/novo", "/editar/{id}"})
    public Curriculo form(int id) {
        return (id > 0) ? curriculoDAO.getById(id) : null;
    }
    
    @Get(value = {"", "/"})
    public List<Curriculo> list() {
        
        return curriculoDAO.findAll();
    }
    
    @Get(value = {"/{id}"})
    public Curriculo view(int id) {
        return curriculoDAO.getById(id);
    }

    @Post
    public Curriculo form(Curriculo curriculo) {
        return curriculo;
    }

    @IncludeParameters
    public void save(@NotNull @Valid Curriculo curriculo) {
        //if(person.getNome() == null || person.getNome().trim().equals(""))
        //validator.add(new SimpleMessage("nome", "O nome deve ser preenchido"));
        validator.onErrorForwardTo(this).form(curriculo);

        if (curriculo.getId() > 0) {
            curriculoDAO.update(curriculo);
        } else {
            curriculoDAO.save(curriculo);
        }

        // Redireciona para a página de listagem
        result.redirectTo(CurriculoController.class).list();
    }
    
    @Get(value = {"/apagar/{id}"})
    public Curriculo delete(int id) {
        return curriculoDAO.getById(id);
    }
    
    @Post(value = {"/apagar/{id}"})
    public void delete(Curriculo curriculo) {
        curriculoDAO.delete(curriculo);
        
        result.forwardTo(this.getClass()).list();
    }
}
