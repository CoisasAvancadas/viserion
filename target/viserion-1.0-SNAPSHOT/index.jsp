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
        </div>


        <div class="container">
            <div class="section">

                <!--   Icon Section   -->
                <div class="row">
                    <div class="col s12 m4">
                        <div class="icon-block">
                            <h2 class="center light-blue-text"><i class="material-icons">flash_on</i></h2>
                            <h5 class="center">Speeds up development</h5>

                            <p class="light">We did most of the heavy lifting for you to provide a default stylings that incorporate our custom components. Additionally, we refined animations and transitions to provide a smoother experience for developers.</p>
                        </div>
                    </div>

                    <div class="col s12 m4">
                        <div class="icon-block">
                            <h2 class="center light-blue-text"><i class="material-icons">group</i></h2>
                            <h5 class="center">User Experience Focused</h5>

                            <p class="light">By utilizing elements and principles of Material Design, we were able to create a framework that incorporates components and animations that provide more feedback to users. Additionally, a single underlying responsive system across all platforms allow for a more unified user experience.</p>
                        </div>
                    </div>

                    <div class="col s12 m4">
                        <div class="icon-block">
                            <h2 class="center light-blue-text"><i class="material-icons">settings</i></h2>
                            <h5 class="center">Easy to work with</h5>

                            <p class="light">We have provided detailed documentation as well as specific code examples to help new users get started. We are also always open to feedback and can answer any questions a user may have about Materialize.</p>
                        </div>
                    </div>
                </div>

            </div>
            <br><br>
        </div>

<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %> 
<%@ include file="/WEB-INF/jsp/inc/scripts.jsp" %> 