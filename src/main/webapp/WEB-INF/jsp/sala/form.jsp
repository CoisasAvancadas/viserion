<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/header.jsp" %>

<div class="page-header">
    <div class="row">
        <div class="col 12">
            <h1><fmt:message key="sala" /> <small class="text-muted" style="text-transform: lowercase"><fmt:message key="register" /></small></h1>
        </div>
    </div>
</div>

<div class="row">
    <form class="col s12" action="${linkTo[SalaController].save(instituicaoId)}" method="POST">
        <c:if test="${not empty sala and sala.id > 0}">
            <input id="inputId" type="hidden" name="sala.id" value="${sala.id}" />
        </c:if>
        <div class="row">
            <div class="col s5">
                <div class="input-field ${not empty errors.from('sala.nome') ? "has-danger" : ""}">
                    <label for="inputName"><fmt:message key="sala.nome" /></label>
                    <input class="" id="inputName" type="text" name="sala.nome" value="${sala.nome}" />
                </div>
                <small class="form-control-feedback">${errors.from('sala.nome')}</small>
            </div>
            <div class="col s5">
                <div class="input-field ${not empty errors.from('sala.tipo') ? "has-danger" : ""}">
                    <label for="inputTipo"><fmt:message key="sala.tipo" /></label>
                    <input id="inputTipo" type="text" name="sala.tipo" value="${sala.tipo}" />
                </div>
                <small class="form-control-feedback">${errors.from('sala.tipo')}</small>
            </div>
            <div class="col s2">
                <div class="input-field ${not empty errors.from('sala.capacidade') ? "has-danger" : ""}">
                    <label for="inputCapacidade"><fmt:message key="sala.capacidade" /></label>
                    <input id="inputCapacidade" type="text" name="sala.capacidade" value="${sala.capacidade}" />
                </div>
                <small class="form-control-feedback">${errors.from('sala.capacidade')}</small>
            </div>
        </div>
            <input type="hidden" value="${instituicaoId}" name="sala.instituicao.id">
            
        <button type="submit" class="btn btn-primary">Salvar</button>
        <button type="reset" class="btn btn-flat">Resetar</button>

    </form>
</div>

<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %> 
<%@ include file="/WEB-INF/jsp/inc/scripts.jsp" %>
