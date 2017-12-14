package controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.download.FileDownload;
import br.com.caelum.vraptor.observer.upload.UploadSizeLimit;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.validator.Validator;
import dao.UsuarioDAO;
import interceptor.Public;
import interceptor.UserInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
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

    protected UsuarioController() {
        this(null, null, null, null);
    }

    @Inject
    public UsuarioController(UsuarioDAO usuarioDAO, UserInfo userInfo, Result result, Validator validator) {
        this.usuarioDAO = usuarioDAO;
        this.result = result;
        this.validator = validator;
        this.userInfo = userInfo;
    }
    
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

    @Get(value = {"/novo", "/editar/{usuarioId}"})
    public Usuario form(int usuarioId) {
        return (usuarioId > 0) ? usuarioDAO.getById(usuarioId) : null;
    }
    
    @Get(value = {"", "/"})
    public List<Usuario> list() {
        return usuarioDAO.findAll();
    }
    
    @Get(value = {"/{usuarioId}"})
    public Usuario view(int usuarioId) {
        return usuarioDAO.getById(usuarioId);
    }

    @Post
    public Usuario form(Usuario usuario) {
        return usuario;
    }

//    @Path(value = {"/save"})
//    public void save(@NotNull Usuario usuario) {
//        //if(person.getNome() == null || person.getNome().trim().equals(""))
//
//        //System.out.println(usuario.getFoto());
//        usuario.setFoto(null);
//
//        validator.onErrorForwardTo(this).form(usuario);
//
//        if (usuario.getId() > 0) {
//            usuarioDAO.update(usuario);
//        } else {
//            usuarioDAO.save(usuario);
//        }
//
//        // Redireciona para a página de listagem
//        result.redirectTo(UsuarioController.class).list();
//    }

    @Path(value = {"/save"})
    @UploadSizeLimit(sizeLimit=40 * 1024 * 1024, fileSizeLimit=10 * 1024 * 1024)
    public void save(Usuario usuario, UploadedFile photo) {
        System.out.println("------------------------------------------------------------------------------------------AQUI CASSETA");
        System.out.println("Usuario = " + usuario.getId() + " Nome=" + usuario.getNome() + "file "+ photo.getContentType() + photo.getFileName());
        
        System.out.println();
        
        if (photo != null) {
            File dir = new File("files/");

            // create multiple directories at one time
            boolean successful = dir.mkdirs();
            File savedPhoto = new File("files", photo.getFileName());
            System.out.println(savedPhoto.getAbsolutePath());
            
            try {
                photo.writeTo(savedPhoto);
            } catch (IOException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }

            usuario.setFoto(savedPhoto.getPath());

            System.out.println(savedPhoto.getPath());
            System.out.println("=============================================================================================AEHOOOOOOOOO");
        }
        
        validator.onErrorForwardTo(this).form(usuario);

        if (usuario.getId() > 0) {
            usuarioDAO.update(usuario);
        } else {
            usuarioDAO.save(usuario);
        }

        // Redireciona para a página de listagem
        result.redirectTo(UsuarioController.class).list();
    }

    @Get(value = {"/avatar/{usuarioId}"})
    public Download avatar(int usuarioId) {
        Usuario usuario = usuarioDAO.getById(usuarioId);

        File file = new File(usuario.getFoto());
        String contentType = "image/jpg";
        String filename = usuario.getNome() + ".jpg";

        try {
            return new FileDownload(file, contentType, filename);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Get(value = {"/apagar/{usuarioId}"})
    public Usuario delete(int usuarioId) {
        return usuarioDAO.getById(usuarioId);
    }
    
    @Post(value = {"/apagar"})
    public void delete(Usuario usuario) {
        usuarioDAO.delete(usuario);
        
        result.forwardTo(this.getClass()).list();
    }
    
    
}
