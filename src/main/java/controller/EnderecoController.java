package controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;
import dao.EnderecoDAO;
import interceptor.Public;
import interceptor.UserInfo;
import java.util.List;
import model.Endereco;
import validation.LoginAvailable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@Path("/endereco")
public class EnderecoController {

    private final Result result;
    private final Validator validator;
    private final UserInfo userInfo;
    private final EnderecoDAO enderecoDAO;

    protected EnderecoController() {
        this(null, null, null, null);
    }

    @Inject
    public EnderecoController(EnderecoDAO enderecoDAO, UserInfo userInfo, Result result, Validator validator) {
        this.enderecoDAO = enderecoDAO;
        this.result = result;
        this.validator = validator;
        this.userInfo = userInfo;
    }
    
    @Post
    @Public
    public void add(@Valid @LoginAvailable Endereco endereco) {
        validator.onErrorUsePageOf(HomeController.class).login();

        enderecoDAO.save(endereco);

        // you can add objects to result even in redirects. Added objects will
        // survive one more request when redirecting.
        result.include("notice", "Endereco adicionado com sucesso");
        result.redirectTo(HomeController.class).login();
    }

    @Get(value = {"/novo", "/editar/{id}"})
    public Endereco form(int id) {
        return (id > 0) ? enderecoDAO.getById(id) : null;
    }
    
    @Get(value = {"", "/"})
    public List<Endereco> list() {
        
        return enderecoDAO.findAll();
    }

    
    @Get(value = {"/{id}"})
    public Endereco view(int id) {
        return enderecoDAO.getById(id);
    }

    @Post
    public Endereco form(Endereco endereco) {
        return endereco;
    }

    @IncludeParameters
    public void save(@NotNull @Valid Endereco endereco) {
        //if(person.getNome() == null || person.getNome().trim().equals(""))
        //validator.add(new SimpleMessage("nome", "O nome deve ser preenchido"));
        validator.onErrorForwardTo(this).form(endereco);

        if (endereco.getId() > 0) {
            enderecoDAO.update(endereco);
        } else {
            enderecoDAO.save(endereco);
        }

        // Redireciona para a página de listagem
        result.redirectTo(EnderecoController.class).list();
    }
    
    @Get(value = {"/apagar/{id}"})
    public Endereco delete(int id) {
        return enderecoDAO.getById(id);
    }
    
    @Post(value = {"/apagar/{id}"})
    public void delete(Endereco endereco) {
        enderecoDAO.delete(endereco);
        
        result.forwardTo(this.getClass()).list();
    }
}
