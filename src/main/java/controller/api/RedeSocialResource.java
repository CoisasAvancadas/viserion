/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.api;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import annotation.Logado;
import dao.RedeSocialDAO;
import interceptor.Public;
import model.RedeSocial;
import javax.inject.Inject;

/**
 *
 * @author divensi
 */
@Controller
@Path("/api/RedeSocials")
public class RedeSocialResource {

    @Inject
    private RedeSocialDAO dao;
    @Inject
    private Result result;

    @Public
    @Logado
    @Get(value = {"", "/"})
    public void all() {
        result.use(Results.json())
                .withoutRoot()
                .from(dao.findAll())
                .serialize();
    }

    @Public
    @Logado
    @Get("{id}")
    public void one(int id) {
        RedeSocial x = dao.getById(id);

        if (x == null) {
            result.notFound();
        } else {
            result.use(Results.json())
                    .withoutRoot()
                    .from(x)
                    .serialize();
        }
    }

    @Public
    @Logado
    @Consumes("application/json")
    @Post(value = {"", "/"})
    public void add(RedeSocial RedeSocial) {
        try {
            dao.save(RedeSocial);
            result.nothing();
            result.use(Results.json())
                    .withoutRoot()
                    .from(RedeSocial)
                    .serialize();
        } catch (Exception e) {
            result.use(Results.status()).badRequest(e.getMessage());
        }
    }

    @Public
    @Logado
    @Consumes("application/json")
    @Put("{id}")
    public void update(RedeSocial RedeSocial, int id) {
        if (dao.getById(id) == null) {
            result.notFound();
            return;
        }
        try {
            RedeSocial.setId(id);
            dao.update(RedeSocial);
            result.nothing();
            result.use(Results.json())
                    .withoutRoot()
                    .from(RedeSocial)
                    .serialize();
        } catch (Exception e) {
            result.use(Results.status()).badRequest(e.getMessage());
        }
    }

    @Public
    @Logado
    @Delete("{id}")
    public void delete(int id) {
        RedeSocial x = dao.getById(id);

        if (x == null) {
            result.notFound();
            return;
        }
        dao.delete(x);
        result.nothing();
    }

}
