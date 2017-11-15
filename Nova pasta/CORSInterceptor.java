
package br.edu.utfpr.webapp.vraptor.controller;

import br.com.caelum.vraptor.BeforeCall;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Intercepts
public class CORSInterceptor {
     
    private HttpServletRequest request;
    private HttpServletResponse response;

    public CORSInterceptor() {}
 
    @Inject
    public CORSInterceptor(HttpServletRequest request,
                            HttpServletResponse response ) {
        this.request = request;
        this.response = response;
    }
 
    @BeforeCall
    public void intercept() throws InterceptionException {
         
        String origin = request.getHeader("origin") != null ? request.getHeader("origin") : "*";
         
        response.addHeader("Access-Control-Allow-Origin", origin);
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Expose-Headers", "Content-Type, Location");
    }
 
}