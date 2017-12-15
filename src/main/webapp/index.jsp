<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/header.jsp"%>

<div class="section no-pad-bot" id="index-banner">
    <div class="container">
        <h1 class="header center orange-text">Viserion</h1>
        <div class="row center">
            <h5 class="header col s12 light">Aplicação de eventos da UTFPR</h5>
        </div>
        <div class="row center">
            <c:if test="${empty usuario}">
                <a href="<c:url value="/home/login"/>" id="download-button" class="btn-large waves-effect waves-light orange">Entrar</a>
                <a href="<c:url value="/usuario/registrar"/>" id="download-button" class="btn-large waves-effect waves-light orange">Inscrever-se</a>
            </c:if>
            <c:if test="${not empty usuario and usuario.id > 0}">
                <a href="<c:url value="/instituicao"/>" id="download-button" class="btn-large waves-effect waves-light orange">Insituições</a>
                <a href="<c:url value="/evento"/>" id="download-button" class="btn-large waves-effect waves-light orange">Eventos</a>
            </c:if>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %> 
<%@ include file="/WEB-INF/jsp/inc/scripts.jsp" %> 