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
import dao.SalaDAO;
import interceptor.Public;
import interceptor.UserInfo;
import model.Sala;
import validation.LoginAvailable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import model.Instituicao;

@Controller
@Path("/")
public class SalaController {

    private final Result result;
    private final Validator validator;
    private final UserInfo userInfo;
    private final SalaDAO salaDAO;
    private final InstituicaoDAO instituicaoDAO;

    protected SalaController() {
        this(null, null, null, null, null);
    }

    @Inject
    public SalaController(InstituicaoDAO instituicaoDAO, SalaDAO salaDAO, UserInfo userInfo, Result result, Validator validator) {
        this.instituicaoDAO = instituicaoDAO;
        this.salaDAO = salaDAO;
        this.result = result;
        this.validator = validator;
        this.userInfo = userInfo;
    }
    
    @Post
    @Public
    public void add(@Valid @LoginAvailable Sala sala) {
        validator.onErrorUsePageOf(HomeController.class).login();

        salaDAO.save(sala);

        // you can add objects to result even in redirects. Added objects will
        // survive one more request when redirecting.
        result.include("notice", "Sala " + sala.getNome()+ " adicionado com sucesso");
        result.redirectTo(HomeController.class).login();
    }

    @Get(value = {"/instituicao/{instituicaoId}/sala/novo", "/instituicao/{instituicaoId}/sala/editar/{salaId}"})
    public Sala form(int instituicaoId, int salaId) {
        result.include("instituicaoId", instituicaoId);
        return (salaId > 0) ? salaDAO.getById(salaId) : null;
    }
    
    @Get(value = {"/instituicao/{instituicaoId}/sala"})
    public void list(int instituicaoId) {
        Instituicao instituicao = instituicaoDAO.getById(instituicaoId);
        instituicaoDAO.refresh(instituicao);

        result.include("instituicao", instituicao);
    }
    
    @Get(value = {"/instituicao/{instituicaoId}/sala/{salaId}"})
    public Sala view(int salaId) {
        return salaDAO.getById(salaId);
    }

    @Post
    public Sala form(Sala sala) {
        return sala;
    }

    @IncludeParameters
    @Path(value = {"/instituicao/{instituicaoId}/sala/save"})
    public void save(int instituicaoId, @NotNull @Valid Sala sala) {
        //if(person.getNome() == null || person.getNome().trim().equals(""))
        //validator.add(new SimpleMessage("nome", "O nome deve ser preenchido"));
        validator.onErrorForwardTo(this).form(sala);

        if (sala.getId() > 0) {
            salaDAO.update(sala);
        } else {
            salaDAO.save(sala);
        }

        // Redireciona para a página de listagem
        result.redirectTo(SalaController.class).list(instituicaoId);
    }
    
    @Get(value = {"/instituicao/{instituicaoId}/sala/delete/{salaId}"})
    public Sala delete(int salaId) {
        return salaDAO.getById(salaId);
    }
    
    @Post(value = {"/instituicao/{instituicaoId}/sala/delete/{salaId}"})
    public void delete(int instituicaoId, Sala sala) {
        salaDAO.delete(sala);
        
        result.forwardTo(this.getClass()).list(instituicaoId);
    }
}
