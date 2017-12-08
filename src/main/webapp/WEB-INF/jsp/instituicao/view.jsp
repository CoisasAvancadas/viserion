<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/header.jsp" %>

<div class="page-header">
    <div class="row">
        <div class="col 12">
            <h1><fmt:message key="instituicao" /> <small class="text-muted" style="text-transform: lowercase"><fmt:message key="register" /></small></h1>
        </div>
    </div>
</div>

<div class="row">
    <form class="col s12">
        <c:if test="${not empty instituicao and instituicao.id > 0}">
            <div class="col s12">
                <div class="form-group">
                    <label class="control-label" for="inputId">ID</label>
                    <input class="form-control" id="inputId" type="text" name="instituicao.id" value="${instituicao.id}" disabled readonly="readonly" />
                    <input type="hidden" name="_method" value="PUT" />
                </div>
            </div>
        </c:if>
        <div class="col s12">
            <div class="input-field ${not empty errors.from('instituicao.nome') ? "has-danger" : ""}">
                <label class="" for="inputName"><fmt:message key="instituicao.nome" /></label>
                <input disabled class="" id="inputName" type="text" name="instituicao.nome" value="${instituicao.nome}" />
            </div>
            <small class="form-control-feedback">${errors.from('instituicao.nome')}</small>
        </div>

        <div class="col s12">
            <div class="input-field ${not empty errors.from('instituicao.cnpj') ? "has-danger" : ""}">
                <label class="" for="inputcnpj"><fmt:message key="instituicao.cnpj" /></label>
                <input disabled class="" id="inputcnpj" type="text" name="instituicao.cnpj" value="${instituicao.cnpj}" />
            </div>
            <small class="form-control-feedback">${errors.from('instituicao.cnpj')}</small>
        </div>
    </form>
</div>

<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %> 
<%@ include file="/WEB-INF/jsp/inc/scripts.jsp" %>
<script>
</script>