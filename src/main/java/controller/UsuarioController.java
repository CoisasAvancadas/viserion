package controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;
import dao.UsuarioDAO;
import interceptor.Public;
import interceptor.UserInfo;
import java.util.List;
import model.Usuario;
import validation.LoginAvailable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@Path("/usuario")
public class UsuarioController {

    private final Result result;
    private final Validator validator;
    private final UserInfo userInfo;
    private final UsuarioDAO usuarioDAO;

    /**
     * @deprecated CDI eyes only
     */
    protected UsuarioController() {
        this(null, null, null, null);
    }

    /**
     * You can receive any dependency on constructor. If VRaptor knows all
     * dependencies, this class will be created with no problem. You can use as
     * dependencies: - all VRaptor components, e.g {@link Result} and
     * {@link Validator} - all of your CDI classes, e.g {@link DefaultUserDao}
     */
    @Inject
    public UsuarioController(UsuarioDAO usuarioDAO, UserInfo userInfo, Result result, Validator validator) {
        this.usuarioDAO = usuarioDAO;
        this.result = result;
        this.validator = validator;
        this.userInfo = userInfo;
    }

    /**
     * Accepts HTTP POST requests.
     *
     * URL:	/users View: /WEB-INF/jsp/user/add.jsp
     *
     * The "user" parameter will be populated with the request parameters, for
     * example:
     *
     * POST	/user user.name=Nico user.login=555555
     *
     * automatically populates the name and login parameters on the user object
     * with values Nico and 555555.
     *
     * Adds new users to the database.
     *
     * @param person
     */
    
    @Post
    @Public
    public void add(@Valid @LoginAvailable Usuario usuario) {
        validator.onErrorUsePageOf(HomeController.class).login();

        usuarioDAO.save(usuario);

        // you can add objects to result even in redirects. Added objects will
        // survive one more request when redirecting.
        result.include("notice", "Usuario " + usuario.getNome()+ " adicionado com sucesso");
        result.redirectTo(HomeController.class).login();
    }

    @Get(value = {"/novo", "/editar/{id}"})
    public Usuario form(int id) {
        return (id > 0) ? usuarioDAO.getById(id) : null;
    }
    
    @Get(value = {"", "/"})
    public List<Usuario> list() {
        
        return usuarioDAO.findAll();
    }
    
    @Get(value = {"/{id}"})
    public Usuario view(int id) {
        return usuarioDAO.getById(id);
    }

    @Post
    public Usuario form(Usuario usuario) {
        return usuario;
    }

    @IncludeParameters
    public void save(@NotNull @Valid Usuario usuario) {
        //if(person.getNome() == null || person.getNome().trim().equals(""))
        //validator.add(new SimpleMessage("nome", "O nome deve ser preenchido"));
        validator.onErrorForwardTo(this).form(usuario);

        if (usuario.getId() > 0) {
            usuarioDAO.update(usuario);
        } else {
            usuarioDAO.save(usuario);
        }

        // Redireciona para a página de listagem
        result.redirectTo(UsuarioController.class).list();
    }
    
    @Get(value = {"/apagar/{id}"})
    public void delete(int id) {
        result.forwardTo(UsuarioController.class).list();
    }
    
}
