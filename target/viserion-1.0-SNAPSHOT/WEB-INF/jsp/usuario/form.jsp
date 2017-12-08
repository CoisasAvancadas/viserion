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
            <div class="form-group ${not empty errors.from('usuario.nome') ? "has-danger" : ""}">
                <label class="form-control-label" for="inputName"><fmt:message key="usuario.nome" /></label>
                <input class="form-control ${not empty errors.from('usuario.nome') ? "form-control-danger" : ""}" id="inputName" type="text" placeholder="<fmt:message key="usuario.nome" />" name="usuario.nome" value="${usuario.nome}" />
                <small class="form-control-feedback">${errors.from('usuario.nome')}</small>
            </div>
        </div>
        <div class="row">

            <div class="form-group ${not empty errors.from('usuario.username') ? "has-danger" : ""}">
                <label class="form-control-label"><fmt:message key="usuario.username" /></label>
                <input class="form-control ${not empty errors.from('usuario.username') ? "form-control-danger" : ""}" type="text" placeholder="<fmt:message key="usuario.username" />" name="usuario.username" value="${usuario.username}" describedby="inputLoginStatus" />
                <small class="form-control-feedback">${errors.from('usuario.username')}</small>
            </div>
        </div>
        <div class="row">

            <div class="form-group ${not empty errors.from('usuario.password') ? "has-danger" : ""}">
                <label class="form-control-label"><fmt:message key="usuario.password" /></label>
                <input class="form-control ${not empty errors.from('usuario.password') ? "form-control-danger" : ""}" type="password" placeholder="<fmt:message key="usuario.password" />" name="usuario.password" value="${usuario.password}" describedby="inputPasswordStatus" />
                <small class="form-control-feedback">${errors.from('usuario.password')}</small>
            </div>
        </div>

            <button type="submit" class="btn btn-primary">Salvar</button>
            <button type="reset" class="btn btn-default">Resetar</button>

    </form>
</div>

<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %> 