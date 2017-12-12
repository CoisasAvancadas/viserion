<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/header.jsp"%>


<div class="page-header">
    <c:if test="${not empty instituicao and instituicao.id > 0}">
        <h1>${instituicao.nome} - <fmt:message key="evento" /> <small class="text-muted" style="text-transform: lowercase"><fmt:message key="list" /></small>
            <a href="<c:url value="/evento/novo" />" class="waves-effect waves-light btn"><i class="material-icons left">add</i><fmt:message key="add"/></a>
        </h1>
    </c:if>
    <c:if test="${empty instituicao}">
        <h1><fmt:message key="evento" /> <small class="text-muted" style="text-transform: lowercase"><fmt:message key="list" /></small>
            <a href="<c:url value="/evento/novo" />" class="waves-effect waves-light btn"><i class="material-icons left">add</i><fmt:message key="add"/></a>
        </h1>
    </c:if>
</div>

<div class="row">
    <div class="col s12">
        <table class="stripped highlight">
            <thead>
                <tr>
                    <th><a href="?ordem=id">#</a></th>
                    <th><a href="?ordem=nome"><fmt:message key="evento.nome"/></a></th>
                    <th><fmt:message key="evento.atividades"/></th>
                    <th><fmt:message key="evento.acoes"/></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${eventos}" varStatus="row">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.nome}</td>
                    <td>
                        <a class="dropdown-item btn btn-link" href="<c:url value="/evento/${item.id}/atividade" />"> Atividades</a>
                    </td>
                    <td>
                        <a href="<c:url value="/evento/editar/${item.id}"/>" class="dropdown-item btn btn-link"><fmt:message key="edit"/></a> 
                        <a href="<c:url value="/evento/apagar/${item.id}"/>" class="dropdown-item btn btn-link"><fmt:message key="delete"/></a> 
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %> 
<%@ include file="/WEB-INF/jsp/inc/scripts.jsp" %> 