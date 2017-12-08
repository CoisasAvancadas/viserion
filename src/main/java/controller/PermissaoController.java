package controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;
import dao.PermissaoDAO;
import interceptor.Public;
import interceptor.UserInfo;
import java.util.List;
import model.Permissao;
import validation.LoginAvailable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@Path("/permissao")
public class PermissaoController {

    private final Result result;
    private final Validator validator;
    private final UserInfo userInfo;
    private final PermissaoDAO permissaoDAO;

    protected PermissaoController() {
        this(null, null, null, null);
    }

    @Inject
    public PermissaoController(PermissaoDAO permissaoDAO, UserInfo userInfo, Result result, Validator validator) {
        this.permissaoDAO = permissaoDAO;
        this.result = result;
        this.validator = validator;
        this.userInfo = userInfo;
    }
    
    @Post
    @Public
    public void add(@Valid @LoginAvailable Permissao permissao) {
        validator.onErrorUsePageOf(HomeController.class).login();

        permissaoDAO.save(permissao);

        // you can add objects to result even in redirects. Added objects will
        // survive one more request when redirecting.
        result.include("notice", "Permissao " + permissao.getNome()+ " adicionado com sucesso");
        result.redirectTo(HomeController.class).login();
    }

    @Get(value = {"/novo", "/editar/{id}"})
    public Permissao form(int id) {
        return (id > 0) ? permissaoDAO.getById(id) : null;
    }
    
    @Get(value = {"", "/"})
    public List<Permissao> list() {
        
        return permissaoDAO.findAll();
    }
    
    @Get(value = {"/{id}"})
    public Permissao view(int id) {
        return permissaoDAO.getById(id);
    }

    @Post
    public Permissao form(Permissao permissao) {
        return permissao;
    }

    @IncludeParameters
    public void save(@NotNull @Valid Permissao permissao) {
        //if(person.getNome() == null || person.getNome().trim().equals(""))
        //validator.add(new SimpleMessage("nome", "O nome deve ser preenchido"));
        validator.onErrorForwardTo(this).form(permissao);

        if (permissao.getId() > 0) {
            permissaoDAO.update(permissao);
        } else {
            permissaoDAO.save(permissao);
        }

        // Redireciona para a página de listagem
        result.redirectTo(PermissaoController.class).list();
    }
    
    @Get(value = {"/apagar/{id}"})
    public Permissao delete(int id) {
        return permissaoDAO.getById(id);
    }
    
    @Post(value = {"/apagar/{id}"})
    public void delete(Permissao permissao) {
        permissaoDAO.delete(permissao);
        
        result.forwardTo(this.getClass()).list();
    }
}
