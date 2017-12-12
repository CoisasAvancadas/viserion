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
    <form class="col s12">
        <c:if test="${not empty sala and sala.id > 0}">
            <div class="row">
                <div class="col s12">
                    <div class="form-group">
                        <label class="control-label" for="inputId">ID</label>
                        <input class="form-control" id="inputId" type="text" name="sala.id" value="${sala.id}" disabled readonly="readonly" />
                    </div>
                </div>
            </div>
        </c:if>
        <div class="row">
            <div class="col s5">
                <div class="input-field ${not empty errors.from('sala.nome') ? "has-danger" : ""}">
                    <label for="inputName"><fmt:message key="sala.nome" /></label>
                    <input disabled class="" id="inputName" type="text" name="sala.nome" value="${sala.nome}" />
                </div>
                <small class="form-control-feedback">${errors.from('sala.nome')}</small>
            </div>
            <div class="col s5">
                <div class="input-field ${not empty errors.from('sala.tipo') ? "has-danger" : ""}">
                    <label for="inputTipo"><fmt:message key="sala.tipo" /></label>
                    <input disabled id="inputTipo" type="text" name="sala.tipo" value="${sala.tipo}" />
                </div>
                <small class="form-control-feedback">${errors.from('sala.tipo')}</small>
            </div>
            <div class="col s2">
                <div class="input-field ${not empty errors.from('sala.capacidade') ? "has-danger" : ""}">
                    <label for="inputCapacidade"><fmt:message key="sala.capacidade" /></label>
                    <input disabled id="inputCapacidade" type="text" name="sala.capacidade" value="${sala.capacidade}" />
                </div>
                <small class="form-control-feedback">${errors.from('sala.capacidade')}</small>
            </div>
        </div>
    </form>
</div>

<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %> 
<%@ include file="/WEB-INF/jsp/inc/scripts.jsp" %>
