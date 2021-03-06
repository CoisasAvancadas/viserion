<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/header.jsp" %>

<div class="page-header">
    <div class="row">
        <div class="col 12">
            <h1><fmt:message key="usuario.delete" /> <small class="text-muted" style="text-transform: lowercase"><fmt:message key="register" /></small></h1>
        </div>
    </div>
</div>

<div class="row">
    <form class="col s12" action="<c:url value="/usuario/apagar"/>" method="POST">
        <c:if test="${not empty usuario and usuario.id > 0}">
            <div class="row">
                <div class="col s12">
                    <div class="form-group">
                        <label class="control-label" for="inputId">ID</label>
                        <input class="form-control" id="inputId" type="text" name="usuario.id" value="${usuario.id}" disabled readonly="readonly" />
                    </div>
                </div>
            </div>
        </c:if>
        <div class="row">
            <div class="col s8">
                <div class="input-field ${not empty errors.from('usuario.nome') ? "has-danger" : ""}">
                    <label class="" for="inputName"><fmt:message key="usuario.nome" /></label>
                    <input disabled class="" id="inputName" type="text" name="usuario.nome" value="${usuario.nome}" />
                </div>
                <small class="form-control-feedback">${errors.from('usuario.nome')}</small>
            </div>
            
            <div class="col s4">
                <div class="input-field ${not empty errors.from('usuario.ra') ? "has-danger" : ""}">
                    <label class="" for="inputra"><fmt:message key="usuario.ra" /></label>
                    <input disabled class="" id="inputra" type="text" name="usuario.ra" value="${usuario.ra}" />
                </div>
                <small class="form-control-feedback">${errors.from('usuario.ra')}</small>
            </div>
        </div>

        <div class="row">
            <div class="col s6">
                <div class="input-field ${not empty errors.from('usuario.nascimento') ? "has-danger" : ""}">
                    <label class="" for="inputnascimento"><fmt:message key="usuario.nascimento" /></label>
                    <input disabled class="datepicker" id="inputnascimento" type="text" name="usuario.nascimento" value="${usuario.nascimento}" />
                </div>
                <small class="form-control-feedback">${errors.from('usuario.nascimento')}</small>
            </div>
            
            <div class="col s6">
                <div class="input-field ${not empty errors.from('usuario.password') ? "has-danger" : ""}">
                    <label class=""><fmt:message key="usuario.password" /></label>
                    <input disabled class="" type="password" name="usuario.password" value="${usuario.password}" describedby="inputPasswordStatus" />
                </div>
                <small class="form-control-feedback">${errors.from('usuario.password')}</small>
            </div>
        </div>

        <div class="row">
            <div class="col s6">
                <div class="input-field ${not empty errors.from('usuario.cpf') ? "has-danger" : ""}">
                    <label class="" for="inputcpf"><fmt:message key="usuario.cpf" /></label>
                    <input disabled class="" id="inputcpf" type="text" name="usuario.cpf" value="${usuario.cpf}" />
                </div>
                <small class="form-control-feedback">${errors.from('usuario.cpf')}</small>
            </div>
        
            <div class="col s6">
                <div class="input-field ${not empty errors.from('usuario.rg') ? "has-danger" : ""}">
                    <label class="" for="inputrg"><fmt:message key="usuario.rg" /></label>
                    <input disabled class="" id="inputrg" type="text" name="usuario.rg" value="${usuario.rg}" />
                </div>
                <small class="form-control-feedback">${errors.from('usuario.rg')}</small>
            </div>
        </div>

        <div class="file-field input-field">
            <div class="btn">
                <span>Selecionar foto</span>
                <input type="file" name="usuario.foto">
            </div>
            <div class="file-path-wrapper">
                <input disabled class="file-path validate" type="text">
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Apagar</button>

    </form>
</div>

<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %> 
<%@ include file="/WEB-INF/jsp/inc/scripts.jsp" %>
<script>
    $('.datepicker').pickadate({
        max: true,
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 60, // Creates a dropdown of 15 years to control year,
        today: 'Hoje',
        clear: 'Limpar',
        close: 'Fechar',
        closeOnSelect: false, // Close upon selecting a date,
        format: 'mm/dd/yyyy'
    });
</script>