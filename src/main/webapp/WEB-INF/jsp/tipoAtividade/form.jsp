<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/header.jsp" %>

<div class="page-header">
    <div class="row">
        <div class="col 12">
            <h1><fmt:message key="tipoAtividade" /> <small class="text-muted" style="text-transform: lowercase"><fmt:message key="register" /></small></h1>
        </div>
    </div>
</div>

<div class="row">
    <form class="col s12" action="${linkTo[TipoAtividadeController].save}" method="POST">
        <c:if test="${not empty tipoAtividade and tipoAtividade.id > 0}">
            <input id="inputId" type="hidden" name="tipoAtividade.id" value="${tipoAtividade.id}" />
        </c:if>
        <div class="row">
            <div class="col s12">
                <div class="input-field ${not empty errors.from('tipoAtividade.nome') ? "has-danger" : ""}">
                    <label class="" for="inputName"><fmt:message key="tipoAtividade.nome" /></label>
                    <input class="" id="inputName" type="text" name="tipoAtividade.nome" value="${tipoAtividade.nome}" />
                </div>
                <small class="form-control-feedback">${errors.from('tipoAtividade.nome')}</small>
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Salvar</button>
        <button type="reset" class="btn btn-flat">Resetar</button>

    </form>
</div>

<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %> 
<%@ include file="/WEB-INF/jsp/inc/scripts.jsp" %>
