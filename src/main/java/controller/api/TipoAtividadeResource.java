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
import dao.TipoAtividadeDAO;
import interceptor.Public;
import model.TipoAtividade;
import javax.inject.Inject;

/**
 *
 * @author divensi
 */
@Controller
@Path("/api/TipoAtividades")
public class TipoAtividadeResource {

    @Inject
    private TipoAtividadeDAO dao;
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
        TipoAtividade x = dao.getById(id);

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
    public void add(TipoAtividade TipoAtividade) {
        try {
            dao.save(TipoAtividade);
            result.nothing();
            result.use(Results.json())
                    .withoutRoot()
                    .from(TipoAtividade)
                    .serialize();
        } catch (Exception e) {
            result.use(Results.status()).badRequest(e.getMessage());
        }
    }

    @Public
    @Logado
    @Consumes("application/json")
    @Put("{id}")
    public void update(TipoAtividade TipoAtividade, int id) {
        if (dao.getById(id) == null) {
            result.notFound();
            return;
        }
        try {
            TipoAtividade.setId(id);
            dao.update(TipoAtividade);
            result.nothing();
            result.use(Results.json())
                    .withoutRoot()
                    .from(TipoAtividade)
                    .serialize();
        } catch (Exception e) {
            result.use(Results.status()).badRequest(e.getMessage());
        }
    }

    @Public
    @Logado
    @Delete("{id}")
    public void delete(int id) {
        TipoAtividade x = dao.getById(id);

        if (x == null) {
            result.notFound();
            return;
        }
        dao.delete(x);
        result.nothing();
    }

}
