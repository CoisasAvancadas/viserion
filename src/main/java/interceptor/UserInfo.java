package interceptor;

import model.Usuario;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1232435511714L;
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void login(Usuario usuario) {
        this.usuario = usuario;
    }

    public void logout() {
        this.usuario = null;
    }
}
