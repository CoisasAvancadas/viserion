<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/header.jsp"%>


<div class="page-header">
    <h1><fmt:message key="instituicao" /> <small class="text-muted" style="text-transform: lowercase"><fmt:message key="list" /></small>
        <a href="<c:url value="/instituicao/novo" />" class="waves-effect waves-light btn"><i class="material-icons left">add</i><fmt:message key="add"/></a>
    </h1>
</div>

<div class="row">
    <div class="col s12">
        <table class="stripped highlight">
            <thead>
                <tr>
                    <th><a href="?ordem=id">#</a></th>
                    <th><a href="?ordem=nome"><fmt:message key="instituicao.nome"/></a></th>
                    <th><fmt:message key="instituicao.cnpj"/></th>
                    <th><fmt:message key="salas"/></th>
                    <th><fmt:message key="eventos"/></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${instituicaoList}" varStatus="row">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.nome}</td>
                    <td>${item.cnpj}</td>
                    <td><a class="dropdown-item btn btn-link" href="<c:url value="/instituicao/${item.id}/sala"/>"><fmt:message key="salas"/></a></td>
                    <td><a class="dropdown-item btn btn-link" href="<c:url value="/instituicao/${item.id}/evento"/>"><fmt:message key="eventos"/></a></td>
                    <td>
                        <a class="dropdown-item btn btn-link" href="<c:url value="/instituicao/${item.id}"/>"><fmt:message key="view"/></a> 
                        <a class="dropdown-item btn btn-link" href="<c:url value="/instituicao/editar/${item.id}"/>"><fmt:message key="edit"/></a> 
                        <a class="dropdown-item btn btn-link" href="<c:url value="/instituicao/apagar/${item.id}"/>"><fmt:message key="delete"/></a> 
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %> 
<%@ include file="/WEB-INF/jsp/inc/scripts.jsp" %> 