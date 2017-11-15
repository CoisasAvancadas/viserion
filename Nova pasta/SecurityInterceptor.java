package br.edu.utfpr.webapp.vraptor.security;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.AcceptsWithAnnotations;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.view.Results;
import br.edu.utfpr.webapp.vraptor.annotation.Logado;

import com.auth0.jwt.JWTVerifyException;
import java.util.ResourceBundle;

@Intercepts
@RequestScoped
@AcceptsWithAnnotations(Logado.class)
public class SecurityInterceptor {

    private HttpServletRequest request;
    private Result result;
    private ResourceBundle bundle;

    public SecurityInterceptor() {
        this(null, null, null);
    }

    @Inject
    public SecurityInterceptor(HttpServletRequest request, Result result, ResourceBundle bundle) {
        this.request = request;
        this.result = result;
        this.bundle = bundle;
    }

    @AroundCall
    public void intercept(SimpleInterceptorStack stack) {

        String token = request.getHeader("authorization");

        Map<String, Object> claims;
        try {
            claims = JWTUtil.decode(token);
            System.out.println(claims);

            Integer userId = (Integer) claims.get("user");

            if (userId != 1) {
                result.use(Results.http()).setStatusCode(401);
                result.use(Results.json())
                        .from("This user does not exist", "msg").serialize();
            } else {
                result.use(Results.http()).addHeader("Authorization", token);

                stack.next();
            }
        } catch (InvalidKeyException | NoSuchAlgorithmException | IllegalStateException | SignatureException | IOException | JWTVerifyException e) {
            result.use(Results.http()).setStatusCode(401);
            result.use(Results.json()).from(e.getMessage(), "msg").serialize();
        }
    }
}
