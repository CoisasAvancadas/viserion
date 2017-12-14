<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/header.jsp" %>

<div class="page-header">
    <div class="row">
        <div class="col 12">
            <h1><fmt:message key="papel" /> <small class="text-muted" style="text-transform: lowercase"><fmt:message key="register" /></small></h1>
        </div>
    </div>
</div>

<div class="row">
    <form class="col s12">
        <div class="row">
            <div class="col s12">
                <div class="input-field ${not empty errors.from('papel.nome') ? "has-danger" : ""}">
                    <label class="" for="inputName"><fmt:message key="papel.nome" /></label>
                    <input disabled class="" id="inputName" type="text" name="papel.nome" value="${papel.nome}" />
                </div>
                <small class="form-control-feedback">${errors.from('papel.nome')}</small>
            </div>
        </div>
    </form>
</div>

<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %> 
<%@ include file="/WEB-INF/jsp/inc/scripts.jsp" %>
