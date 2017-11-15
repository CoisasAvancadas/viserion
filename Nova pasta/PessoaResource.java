package br.edu.utfpr.webapp.vraptor.api;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.edu.utfpr.webapp.vraptor.annotation.Logado;
import br.edu.utfpr.webapp.vraptor.dao.PessoaDAO;
import br.edu.utfpr.webapp.vraptor.model.Pessoa;
import io.swagger.annotations.Api;
import javax.inject.Inject;

/*
    Token válido para de testes:
    eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjIzODUzMTc4MzQsImlhdCI6MTQzOTIzNzgzNCwidXNlciI6MX0.fN75EaS5yyFtxZTUr-qvjtDH-Ku7TIfybWsdmdTk7Q0
 */
@Api
@Controller
@Path("/api/pessoa")
public class PessoaResource {

    @Inject
    private PessoaDAO pessoaDAO;

    @Inject
    private Result result;

    @Consumes(value = "application/json")
    @Post(value = {"/", ""})
    public void save(Pessoa pessoa) {
        try {
            pessoaDAO.save(pessoa);

            result.nothing();
            result.use(Results.json())
                    .withoutRoot()
                    .from(pessoa)
                    .serialize();
        } catch (Exception e) {
            e.printStackTrace();
            result.notFound();
        }
    }

    @Put(value = {"/", ""})
    public void update(Pessoa pessoa) {
        if (pessoa.getId() > 0) {
            pessoaDAO.update(pessoa);
        }

    }

    @Delete("{id}")
    public void delete(int id) {
        pessoaDAO.delete(pessoaDAO.getById(id));
    }

    @Get("{id}")
    public void get(int id) {
        result.use(Results.json())
                .withoutRoot()
                .from(pessoaDAO.getById(id))
                .serialize();
    }

    @Get(value = {"", "/"})
    public void todosJson() {
        result.use(Results.json())
                .withoutRoot()
                .from(pessoaDAO.findAll())
                .serialize();
    }

    @Logado
    @Get(value = "/xml")
    public void todosXml() {
        result.use(Results.xml())
                .from(pessoaDAO.findAll())
                .serialize();
    }
}
