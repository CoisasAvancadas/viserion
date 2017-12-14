<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/header.jsp"%>


<div class="page-header">
    <h1>${instituicao.nome} - <fmt:message key="salas" /> <small class="text-muted" style="text-transform: lowercase"><fmt:message key="list" /></small>
        <a href="<c:url value="/instituicao/${instituicao.id}/sala/novo" />" class="waves-effect waves-light btn"><i class="material-icons left">add</i><fmt:message key="add"/></a>
    </h1>
</div>

<div class="row">
    <div class="col s12">
        <table class="stripped highlight">
            <thead>
                <tr>
                    <th><a href="?ordem=id">#</a></th>
                    <th><a href="?ordem=nome"><fmt:message key="sala.nome"/></a></th>
                    <th><a href="?ordem=nome"><fmt:message key="sala.tipo"/></a></th>
                    <th><a href="?ordem=nome"><fmt:message key="sala.capacidade"/></a></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${instituicao.salas}" varStatus="row">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.nome}</td>
                    <td>${item.tipo}</td>
                    <td>${item.capacidade}</td>
                    <td>
                        <a href="<c:url value="/instituicao/${instituicao.id}/sala/${item.id}"/>" class="dropdown-item btn btn-link"><fmt:message key="view"/></a> 
                        <a href="<c:url value="/instituicao/${instituicao.id}/sala/editar/${item.id}"/>" class="dropdown-item btn btn-link"><fmt:message key="edit"/></a> 
                        <a href="<c:url value="/instituicao/${instituicao.id}/sala/apagar/${item.id}"/>" class="dropdown-item btn btn-link"><fmt:message key="delete"/></a> 
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %> 
<%@ include file="/WEB-INF/jsp/inc/scripts.jsp" %> 