package controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;
import dao.InstituicaoDAO;
import interceptor.Public;
import interceptor.UserInfo;
import java.util.List;
import model.Instituicao;
import validation.LoginAvailable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@Path("/instituicao")
public class InstituicaoController {

    private final Result result;
    private final Validator validator;
    private final UserInfo userInfo;
    private final InstituicaoDAO instituicaoDAO;

    protected InstituicaoController() {
        this(null, null, null, null);
    }

    @Inject
    public InstituicaoController(InstituicaoDAO instituicaoDAO, UserInfo userInfo, Result result, Validator validator) {
        this.instituicaoDAO = instituicaoDAO;
        this.result = result;
        this.validator = validator;
        this.userInfo = userInfo;
    }
    
    @Post
    @Public
    public void add(@Valid @LoginAvailable Instituicao instituicao) {
        validator.onErrorUsePageOf(HomeController.class).login();

        instituicaoDAO.save(instituicao);

        // you can add objects to result even in redirects. Added objects will
        // survive one more request when redirecting.
        result.include("notice", "Instituicao " + instituicao.getNome()+ " adicionado com sucesso");
        result.redirectTo(HomeController.class).login();
    }

    @Get(value = {"/novo", "/editar/{id}"})
    public Instituicao form(int id) {
        return (id > 0) ? instituicaoDAO.getById(id) : null;
    }
    
    @Get(value = {"", "/"})
    public List<Instituicao> list() {
        
        return instituicaoDAO.findAll();
    }
    
    @Get(value = {"/{id}"})
    public Instituicao view(int id) {
        return instituicaoDAO.getById(id);
    }

    @Post
    public Instituicao form(Instituicao instituicao) {
        return instituicao;
    }

    @IncludeParameters
    public void save(@NotNull @Valid Instituicao instituicao) {
        //if(person.getNome() == null || person.getNome().trim().equals(""))
        //validator.add(new SimpleMessage("nome", "O nome deve ser preenchido"));
        validator.onErrorForwardTo(this).form(instituicao);

        if (instituicao.getId() > 0) {
            instituicaoDAO.update(instituicao);
        } else {
            instituicaoDAO.save(instituicao);
        }

        // Redireciona para a página de listagem
        result.redirectTo(InstituicaoController.class).list();
    }
    
    @Get(value = {"/apagar/{id}"})
    public Instituicao delete(int id) {
        return instituicaoDAO.getById(id);
    }
    
    @Post(value = {"/apagar/{id}"})
    public void delete(Instituicao instituicao) {
        instituicaoDAO.delete(instituicao);
        
        result.forwardTo(this.getClass()).list();
    }
}
