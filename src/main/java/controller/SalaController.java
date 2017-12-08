package controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;
import dao.SalaDAO;
import interceptor.Public;
import interceptor.UserInfo;
import java.util.List;
import model.Sala;
import validation.LoginAvailable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@Path("/sala")
public class SalaController {

    private final Result result;
    private final Validator validator;
    private final UserInfo userInfo;
    private final SalaDAO salaDAO;

    protected SalaController() {
        this(null, null, null, null);
    }

    @Inject
    public SalaController(SalaDAO salaDAO, UserInfo userInfo, Result result, Validator validator) {
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

    @Get(value = {"/novo", "/editar/{id}"})
    public Sala form(int id) {
        return (id > 0) ? salaDAO.getById(id) : null;
    }
    
    @Get(value = {"", "/"})
    public List<Sala> list() {
        
        return salaDAO.findAll();
    }
    
    @Get(value = {"/{id}"})
    public Sala view(int id) {
        return salaDAO.getById(id);
    }

    @Post
    public Sala form(Sala sala) {
        return sala;
    }

    @IncludeParameters
    public void save(@NotNull @Valid Sala sala) {
        //if(person.getNome() == null || person.getNome().trim().equals(""))
        //validator.add(new SimpleMessage("nome", "O nome deve ser preenchido"));
        validator.onErrorForwardTo(this).form(sala);

        if (sala.getId() > 0) {
            salaDAO.update(sala);
        } else {
            salaDAO.save(sala);
        }

        // Redireciona para a página de listagem
        result.redirectTo(SalaController.class).list();
    }
    
    @Get(value = {"/apagar/{id}"})
    public Sala delete(int id) {
        return salaDAO.getById(id);
    }
    
    @Post(value = {"/apagar/{id}"})
    public void delete(Sala sala) {
        salaDAO.delete(sala);
        
        result.forwardTo(this.getClass()).list();
    }
}
