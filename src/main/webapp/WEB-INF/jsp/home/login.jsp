<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/header.jsp"%>
<div class="row">
    <center>
        <div class="section"></div>

        <div class="container">
            <div class="z-depth-1 grey lighten-4 row" style="display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE;">
                <form class="col s12" action="${linkTo[HomeController].login}" method="post" >
                    <div class='row'>
                        <div class='col s12'>
                        </div>
                    </div>
                    <div class='row'>
                        <div class='input-field col s12'>
                            <input class='validate' type='text' name='username' id='email' />
                            <label for='email'><fmt:message key="usuario.username"/></label>
                        </div>
                    </div>
                    <div class='row'>
                        <div class='input-field col s12'>
                            <input class='validate' type='password' name='password' id='password'  />
                            <label for='password'><fmt:message key="usuario.password"/></label>
                        </div>
                        <label style='float: right;'>
                            <a class='pink-text' href='#!'><b>Forgot Password?</b></a>
                        </label>
                    </div>

                    <br />
                    <center>
                        <div class='row'>
                            <button type='submit' id="submit" name='btn_login' class='col s12 btn btn-large waves-effect indigo'><fmt:message key="send"/></button>
                        </div>
                    </center>
                </form>
            </div>
        </div>
        <a href="<c:url value="/usuario/form" />">Create account</a>
    </center>
</div>
<br/>

<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %> 