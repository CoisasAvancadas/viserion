<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/header.jsp" %>

<div class="page-header">
    <div class="row">
        <div class="col 12">
            <h1><fmt:message key="tipoatividade" /> <small class="text-muted" style="text-transform: lowercase"><fmt:message key="register" /></small></h1>
        </div>
    </div>
</div>

<div class="row">
    <form class="col s12">
        <div class="row">
            <div class="col s12">
                <div class="input-field ${not empty errors.from('tipoAtividade.nome') ? "has-danger" : ""}">
                    <label class="" for="inputName"><fmt:message key="tipoAtividade.nome" /></label>
                    <input disabled class="" id="inputName" type="text" name="tipoAtividade.nome" value="${tipoAtividade.nome}" />
                </div>
                <small class="form-control-feedback">${errors.from('tipoAtividade.nome')}</small>
            </div>
        </div>
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