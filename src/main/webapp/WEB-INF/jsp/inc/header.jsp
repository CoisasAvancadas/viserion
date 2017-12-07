<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="author" content="UTFPR - Câmpus Medianeira"/>
        <meta name="reply-to" content="ricardosobjak@utfpr.edu.br"/>
        <meta name="description" content="<fmt:message key="meta.description"/>"/>
        <meta name="keywords" content="educacao, web, desenvolvimento, development, java, opensource"/>
        <title>Viserion</title>
        <!-- Compiled and minified CSS -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css" 
              integrity="sha256-e22BQKCF7bb/h/4MFJ1a4lTRR2OuAe8Hxa/3tgU5Taw=" 
              crossorigin="anonymous" />        
        <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:set var="path"><c:url value="/"/></c:set>
        <c:if test="${not empty param.language}">
            <c:set var="lang" value="${param.language}" scope="session"/>
            <fmt:setLocale value="${param.language}" scope="session"/>
        </c:if>
        <div class="navbar-fixed">
            <nav class="light-blue lighten-1" role="navigation">
                <div class="nav-wrapper container"><a id="logo-container" href="${path}" class="brand-logo">Viserion</a>
                    <ul class="right hide-on-med-and-down">
                        <ul id="dropdown1" class="dropdown-content">
                            <li><a href="<c:url value="/person" />"><fmt:message key="menu.person"/> REST</a></li>
                            <li><a href="<c:url value="/pessoa/list" />"><fmt:message key="menu.person"/></a></li>
                            <li class="divider"></li>
                            <li><a href="#"><fmt:message key="menu.other"/>1</a></li>
                            <li><a href="#"><fmt:message key="menu.other"/>2</a></li>
                        </ul>
                        <li><a class="dropdown-button" href="#!" data-activates="dropdown1">Dropdown<i class="material-icons right">arrow_drop_down</i></a></li>  

                        <li><a href="<c:url value="/home/login" />">login</a></li>
                        
                        <ul class="navbar-nav ml-auto">
                           <li class="nav-item dropdown ${not empty userInfo.usuario ? '' : 'hidden'}">
                               <a class="nav-link dropdown-toggle" id="dropdown02" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${userInfo.usuario.username}</a>
                               <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdown02">
                                   <a class="dropdown-item" href="<c:url value="/usuario/${userInfo.usuario.id}" />">Meu Cadastro</a>
                                   <div class="dropdown-divider"></div>
                                   <a class="dropdown-item" href="${linkTo[HomeController].logout}"><fmt:message key="logout"/></a>
                               </div>
                           </li>
                       </ul>
                        
                    </ul>

                    <ul id="nav-mobile" class="side-nav">
                        <li><a href="<c:url value="/home/login" />">login</a></li>
                    </ul>
                    
                    
                    <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
                </div>
            </nav>
        </div>


        <div id="pageTitle" class="jumbotron text-center">
            <div class="container">
                <h1 class="display-3">VRaptor-WebAPP</h1>
            </div>
        </div>

        <c:if test="${not empty errors}">
            <div class="alert alert-danger alert-dismissible fade show" style="z-index: 900">
                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
                <c:forEach items="${errors}" var="error">
                    <b><fmt:message key="${error.category}"/></b> - ${error.message}<br/>
                </c:forEach>
            </div>
        </c:if>

        <c:if test="${not empty notice}">
            <div class="alert alert-success alert-dismissible fade show" style="z-index: 900"> 
                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
                ${notice} 
            </div>
        </c:if>

        <div class="container">