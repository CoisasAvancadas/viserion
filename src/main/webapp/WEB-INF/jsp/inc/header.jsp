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
        <header>
        <div class="navbar-fixed">
            <nav class="light-blue lighten-1" role="navigation">
                <div class="nav-wrapper">
                    <c:if test="${not empty userInfo.usuario}" >

                        <li href="#" data-activates="slide-out" class="button-collapse" style="cursor: pointer"><i class="material-icons">menu</i></li>
                    </c:if>
                    <a id="logo-container" href="${path}" class="brand-logo">Viserion</a>
                    <ul class="right hide-on-med-and-down">

                        <c:if test="${empty userInfo.usuario}" >
                            <li><a href="<c:url value="/home/login" />">login</a></li>
                        </c:if>

                        <c:if test="${not empty userInfo.usuario}" >
                            <li><a class="dropdown-button" href="#!" data-activates="dropdown1">Bem Vindo, ${userInfo.usuario.username}<i class="material-icons right">arrow_drop_down</i></a></li>
                            <ul id="dropdown1" class="dropdown-content">
                                <li><a href="<c:url value="/usuario/${userInfo.usuario.id}" />"><fmt:message key="header.meu_cadastro" /></a></li>
                                <li><a href="<c:url value="/home/logout" />"><fmt:message key="logout"/></a></li>
                            </ul>
                        </c:if>
                    </ul>
                </div>
            </nav>
        </div>
        </header>
        <c:if test="${not empty userInfo.usuario}" >
            <%@ include file="/WEB-INF/jsp/inc/sidebar.jsp" %>
        </c:if>
        <div class="container">