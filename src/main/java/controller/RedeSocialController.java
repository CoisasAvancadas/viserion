package controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;
import dao.RedeSocialDAO;
import interceptor.Public;
import interceptor.UserInfo;
import java.util.List;
import model.RedeSocial;
import validation.LoginAvailable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@Path("/redeSocial")
public class RedeSocialController {

    private final Result result;
    private final Validator validator;
    private final UserInfo userInfo;
    private final RedeSocialDAO redeSocialDAO;

    protected RedeSocialController() {
        this(null, null, null, null);
    }

    @Inject
    public RedeSocialController(RedeSocialDAO redeSocialDAO, UserInfo userInfo, Result result, Validator validator) {
        this.redeSocialDAO = redeSocialDAO;
        this.result = result;
        this.validator = validator;
        this.userInfo = userInfo;
    }
    
    @Post
    @Public
    public void add(@Valid @LoginAvailable RedeSocial redeSocial) {
        validator.onErrorUsePageOf(HomeController.class).login();

        redeSocialDAO.save(redeSocial);

        // you can add objects to result even in redirects. Added objects will
        // survive one more request when redirecting.
        result.include("notice", "RedeSocial " + redeSocial.getNome()+ " adicionado com sucesso");
        result.redirectTo(HomeController.class).login();
    }

    @Get(value = {"/novo", "/editar/{id}"})
    public RedeSocial form(int id) {
        return (id > 0) ? redeSocialDAO.getById(id) : null;
    }
    
    @Get(value = {"", "/"})
    public List<RedeSocial> list() {
        
        return redeSocialDAO.findAll();
    }
    
    @Get(value = {"/{id}"})
    public RedeSocial view(int id) {
        return redeSocialDAO.getById(id);
    }

    @Post
    public RedeSocial form(RedeSocial redeSocial) {
        return redeSocial;
    }

    @IncludeParameters
    public void save(@NotNull @Valid RedeSocial redeSocial) {
        //if(person.getNome() == null || person.getNome().trim().equals(""))
        //validator.add(new SimpleMessage("nome", "O nome deve ser preenchido"));
        validator.onErrorForwardTo(this).form(redeSocial);

        if (redeSocial.getId() > 0) {
            redeSocialDAO.update(redeSocial);
        } else {
            redeSocialDAO.save(redeSocial);
        }

        // Redireciona para a página de listagem
        result.redirectTo(RedeSocialController.class).list();
    }
    
    @Get(value = {"/apagar/{id}"})
    public RedeSocial delete(int id) {
        return redeSocialDAO.getById(id);
    }
    
    @Post(value = {"/apagar/{id}"})
    public void delete(RedeSocial redeSocial) {
        redeSocialDAO.delete(redeSocial);
        
        result.forwardTo(this.getClass()).list();
    }
}
