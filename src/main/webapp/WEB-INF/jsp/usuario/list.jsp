<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/header.jsp"%>


<div class="page-header">
    <h1><fmt:message key="usuario" /> <small class="text-muted" style="text-transform: lowercase"><fmt:message key="list" /></small>
        <a href="<c:url value="/usuario/form" />" class="btn btn-primary btn-sm"><fmt:message key="add"/></a>
    </h1>
</div>

<div class="row">
    <div class="col-lg-12 mx-auto">
        <table class="table">
            <tr>
                <th><a href="?ordem=id">#</a></th>
                <th><a href="?ordem=nome"><fmt:message key="usuario.nome"/></a></th>
                <th><fmt:message key="usuario.username"/></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${usuarioList}" varStatus="row">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.nome}</td>
                    <td>${item.username}</td>
                    <td>
                        <ul class="navbar-nav">
                            <li class="nav-item dropdown">
                                <a class="dropdown-toggle" id="options${item.id}" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Options</a>
                                <div class="dropdown-menu" aria-labelledby="options${item.id}">
                                    <form action="<c:url value="/usuario/${item.id}"/>" method="POST">
                                        <button class="dropdown-item btn btn-link" name="_method" value="GET"><fmt:message key="edit"/></button> 
                                        <button class="dropdown-item btn btn-link" name="_method" value="DELETE"><fmt:message key="delete"/></button> 
                                    </form>
                                </div>
                            </li>
                        </ul>

                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %> 