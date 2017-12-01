/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package api;

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
import dao.PapelSistemaDAO;
import model.PapelSistema;
import javax.inject.Inject;

/**
 *
 * @author divensi
 */
@Controller
@Path("/api/PapelSistemas")
public class PapelSistemaResource {

    @Inject
    private PapelSistemaDAO dao;
    @Inject
    private Result result;
    
    @Logado
    @Get(value = {"", "/"})
    public void all() {
        result.use(Results.json())
                .withoutRoot()
                .from(dao.findAll())
                .serialize();
    }
    
    @Get("{id}")
    public void one(int id) {
        PapelSistema x = dao.getById(id);
        
        if(x == null) {
            result.notFound();
        } else {
            result.use(Results.json())
                .withoutRoot()
                .from(x)
                .serialize();
        }
    }
    
    @Consumes("application/json")
    @Post(value = {"","/"})
    public void add(PapelSistema PapelSistema) {
        try {
            dao.save(PapelSistema);
            result.nothing();
            result.use(Results.json())
                .withoutRoot()
                .from(PapelSistema)
                .serialize();
        } catch (Exception e) {
            result.use(Results.status()).badRequest(e.getMessage());
        }
    }
    
    @Consumes("application/json")
    @Put("{id}")
    public void update(PapelSistema PapelSistema, int id) {
        if (dao.getById(id) == null) {
            result.notFound();
            return;
        }
        try {
            PapelSistema.setId(id);
            dao.update(PapelSistema);
            result.nothing();
            result.use(Results.json())
                .withoutRoot()
                .from(PapelSistema)
                .serialize();
        } catch (Exception e) {
            result.use(Results.status()).badRequest(e.getMessage());
        }
    }
    
    @Delete("{id}")
    public void delete(int id) {
        PapelSistema x = dao.getById(id);
        
        if (x == null) {
            result.notFound();
            return;
        }
        dao.delete(x);
        result.nothing();
    }

}
