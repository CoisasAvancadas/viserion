<%@page contentType="text/html" pageEncoding="UTF-8"%>
﻿<%@ include file="/WEB-INF/jsp/inc/header.jsp" %> 

<div class="row">
    <div class="col-lg-5 mx-auto">
        <h3>Sign in</h3>
        <form action="${linkTo[HomeController].login}" method="post" class="form-horizontal">
            <div class="form-group">
                <label class="sr-only" for="username"><fmt:message key="usuario.username"/></label>
                <input type="text" class="form-control" id="username" name="username" placeholder="<fmt:message key="usuario.username"/>"/>
            </div>
            <div class="form-group">
                <label class="sr-only" for="password"><fmt:message key="usuario.password"/></label>
                <input type="password" class="form-control" name="password" placeholder="<fmt:message key="usuario.password"/>" />
                <small class="text-muted"><fmt:message key="user.default"/></small>
            </div>
            <div class="form-group pull-right">	
                <button type="submit" id="submit" class="btn btn-large btn-primary">
                    <fmt:message key="send"/>
                </button>
            </div>	
        </form>
    </div>	
</div>
<br/>

<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %> 