package validation.impl;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import dao.UsuarioDAO;
import model.Usuario;
import validation.LoginAvailable;

public class LoginAvailableValidator
        implements ConstraintValidator<LoginAvailable, Usuario> {

    @Inject
    private UsuarioDAO usuarioDAO;

    @Override
    public void initialize(LoginAvailable constraintAnnotation) {

    }

    @Override
    public boolean isValid(Usuario usuario, ConstraintValidatorContext context) {
        return !usuarioDAO.containsUserWithLogin(usuario.getUsername());
    }
}
