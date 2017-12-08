<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/header.jsp" %>

<div class="page-header">
    <h1><fmt:message key="usuario" /> <small class="text-muted" style="text-transform: lowercase"><fmt:message key="register" /></small></h1>
</div>

<div class="row">
    <form class="col s12" action="${linkTo[UsuarioController].save}" method="POST">
        <div class="row">
            <c:if test="${not empty usuario and usuario.id > 0}">
                <div class="form-group">
                    <label class="control-label" for="inputId">ID</label>
                    <input class="form-control" id="inputId" type="text" name="usuario.id" value="${usuario.id}" readonly="readonly" />
                    <input type="hidden" name="_method" value="PUT" />
                </div>
            </c:if>
        </div>
        <div class="row">
            <div class="input-field ${not empty errors.from('usuario.nome') ? "has-danger" : ""}">
                <label class="" for="inputName"><fmt:message key="usuario.nome" /></label>
                <input class="" id="inputName" type="text" name="usuario.nome" value="${usuario.nome}" />
            </div>
            <small class="form-control-feedback">${errors.from('usuario.nome')}</small>
        </div>
        
        <div class="row">
            <div class="input-field ${not empty errors.from('usuario.ra') ? "has-danger" : ""}">
                <label class="" for="inputra"><fmt:message key="usuario.ra" /></label>
                <input class="" id="inputra" type="text" name="usuario.ra" value="${usuario.ra}" />
            </div>
            <small class="form-control-feedback">${errors.from('usuario.ra')}</small>
        </div>
        
        <div class="row">
            <div class="input-field ${not empty errors.from('usuario.nascimento') ? "has-danger" : ""}">
                <label class="" for="inputnascimento"><fmt:message key="usuario.nascimento" /></label>
                <input class="" id="inputnascimento" type="text" name="usuario.nascimento" value="${usuario.nascimento}" />
            </div>
            <small class="form-control-feedback">${errors.from('usuario.nascimento')}</small>
        </div>

        <div class="row">
            <div class="input-field ${not empty errors.from('usuario.cpf') ? "has-danger" : ""}">
                <label class="" for="inputcpf"><fmt:message key="usuario.cpf" /></label>
                <input class="" id="inputcpf" type="text" name="usuario.cpf" value="${usuario.cpf}" />
            </div>
            <small class="form-control-feedback">${errors.from('usuario.cpf')}</small>
        </div>

        <div class="row">
            <div class="input-field ${not empty errors.from('usuario.cpf') ? "has-danger" : ""}">
                <label class="" for="inputcpf"><fmt:message key="usuario.cpf" /></label>
                <input class="" id="inputcpf" type="text" name="usuario.cpf" value="${usuario.cpf}" />
            </div>
            <small class="form-control-feedback">${errors.from('usuario.cpf')}</small>
        </div>
        
        <div class="row">
            <div class="input-field ${not empty errors.from('usuario.rg') ? "has-danger" : ""}">
                <label class="" for="inputrg"><fmt:message key="usuario.rg" /></label>
                <input class="" id="inputrg" type="text" name="usuario.rg" value="${usuario.rg}" />
            </div>
            <small class="form-control-feedback">${errors.from('usuario.rg')}</small>
        </div>
        
        <div class="row">
            <div class="input-field ${not empty errors.from('usuario.rg') ? "has-danger" : ""}">
                <label class="" for="inputrg"><fmt:message key="usuario.rg" /></label>
                <input class="" id="inputrg" type="text" name="usuario.rg" value="${usuario.rg}" />
            </div>
            <small class="form-control-feedback">${errors.from('usuario.rg')}</small>
        </div>
        
        
        <div class="row">

            <div class="input-field ${not empty errors.from('usuario.password') ? "has-danger" : ""}">
                <label class=""><fmt:message key="usuario.password" /></label>
                <input class="" type="password" name="usuario.password" value="${usuario.password}" describedby="inputPasswordStatus" />
            </div>
            <small class="form-control-feedback">${errors.from('usuario.password')}</small>

        </div>

            <button type="submit" class="btn btn-primary">Salvar</button>
            <button type="reset" class="btn btn-default">Resetar</button>

    </form>
</div>

<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %> 