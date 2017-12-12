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
@Path("/")
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
    
    @Post(value = {"/instituicao/add"})
    @Public
    public void add(@Valid @LoginAvailable Instituicao instituicao) {
        validator.onErrorUsePageOf(HomeController.class).login();

        instituicaoDAO.save(instituicao);

        // you can add objects to result even in redirects. Added objects will
        // survive one more request when redirecting.
        result.include("notice", "Instituicao " + instituicao.getNome()+ " adicionado com sucesso");
        result.redirectTo(HomeController.class).login();
    }

    @Get(value = {"/instituicao/novo", "/instituicao/editar/{instituicaoId}"})
    public Instituicao form(int instituicaoId) {
        return (instituicaoId > 0) ? instituicaoDAO.getById(instituicaoId) : null;
    }
    
    @Get(value = {"/instituicao"})
    public List<Instituicao> list() {
        
        return instituicaoDAO.findAll();
    }
    
    @Get(value = {"/instituicao/{instituicaoId}"})
    public Instituicao view(int instituicaoId) {
        return instituicaoDAO.getById(instituicaoId);
    }

    @Post(value = {"/instituicao"})
    public Instituicao form(Instituicao instituicao) {
        return instituicao;
    }

    @IncludeParameters
    @Path(value = {"/instituicao/save"})
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
    
    @Get(value = {"/instituicao/apagar/{instituicaoId}"})
    public Instituicao delete(int instituicaoId) {
        return instituicaoDAO.getById(instituicaoId);
    }
    
    @Post(value = {"/instituicao/apagar/{instituicaoId}"})
    public void delete(Instituicao instituicao) {
        instituicaoDAO.delete(instituicao);
        
        result.forwardTo(this.getClass()).list();
    }
}
