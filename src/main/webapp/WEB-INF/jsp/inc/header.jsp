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

        <title>VRaptor-WebAPP</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>

        <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css"/>
        <!--[if lt IE 7]>
        <script src="http://ie7-js.googlecode.com/svn/version/2.0(beta3)/IE7.js" 
            type="text/javascript"></script>
        <![endif]-->
    </head>

    <body>
        <c:set var="path"><c:url value="/"/></c:set>

        <c:if test="${not empty param.language}">
            <c:set var="lang" value="${param.language}" scope="session"/>
            <fmt:setLocale value="${param.language}" scope="session"/>
        </c:if>

        <nav class="navbar navbar-toggleable-md navbar-inverse fixed-top bg-inverse">
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <a class="navbar-brand" href="${path}">WebApp-VRaptor</a>

            <div class="collapse navbar-collapse" id="navbarsExampleDefault">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><fmt:message key="entities"/></a>
                        <div class="dropdown-menu" aria-labelledby="dropdown01">
                            <a class="dropdown-item" href="<c:url value="/person" />"><fmt:message key="menu.person"/> REST</a>
                            <a class="dropdown-item" href="<c:url value="/pessoa/list" />"><fmt:message key="menu.person"/></a>
                            <a class="dropdown-item disabled" href="#"><fmt:message key="menu.other"/>1</a>
                            <a class="dropdown-item disabled" href="#"><fmt:message key="menu.other"/>2</a>
                        </div>
                    </li>
                </ul>
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
            </div>
        </nav>


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