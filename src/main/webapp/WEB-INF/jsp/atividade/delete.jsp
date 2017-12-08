<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/header.jsp" %>

<div class="page-header">
    <div class="row">
        <div class="col 12">
            <h1><fmt:message key="atividade.delete" /> <small class="text-muted" style="text-transform: lowercase"><fmt:message key="register" /></small></h1>
        </div>
    </div>
</div>

<div class="row">
    <form class="col s12" action="${linkTo[AtividadeController].delete}" method="POST">
        <c:if test="${not empty atividade and atividade.id > 0}">
            <div class="col s12">
                <div class="form-group">
                    <label class="control-label" for="inputId">ID</label>
                    <input class="form-control" id="inputId" type="text" name="atividade.id" value="${atividade.id}" disabled readonly="readonly" />
                    <input type="hidden" name="_method" value="PUT" />
                </div>
            </div>
        </c:if>
        <div class="col s12">
            <div class="input-field ${not empty errors.from('atividade.nome') ? "has-danger" : ""}">
                <label class="" for="inputName"><fmt:message key="atividade.nome" /></label>
                <input disabled class="" id="inputName" type="text" name="atividade.nome" value="${atividade.nome}" />
            </div>
            <small class="form-control-feedback">${errors.from('atividade.nome')}</small>
        </div>

        <div class="row">
            <div class="col s6">
                <div class="input-field ${not empty errors.from('atividade.horaInicio') ? "has-danger" : ""}">
                    <label class="" for="inputhoraInicio-date"><fmt:message key="atividade.horaInicio" /></label>
                    <input disabled class="datetime" id="inputhoraInicio-date" type="text" value="${atividade.horaInicio}" />
                </div>
                <small class="form-control-feedback">${errors.from('atividade.horaInicio')}</small>
            </div>

            <div class="col s6">
                <div class="input-field ${not empty errors.from('atividade.horaInicio') ? "has-danger" : ""}">
                    <label class="" for="inputhoraInicio-time"><fmt:message key="atividade.horaInicio" /></label>
                    <input disabled class="timepicker" id="inputhoraInicio-time" type="text" value="${atividade.horaInicio}" />
                </div>
                <small class="form-control-feedback">${errors.from('atividade.horaInicio')}</small>
            </div>

            <input type="datetime" name="atividade.horaInicio" value="${atividade.horaInicio}" style="display:none">
        </div>
        
        <div class="row">
            <div class="col s6">
                <div class="input-field ${not empty errors.from('atividade.horaFim') ? "has-danger" : ""}">
                    <label class="" for="inputhoraFim-date"><fmt:message key="atividade.horaInicio" /></label>
                    <input disabled class="datetime" id="inputhoraFim-date" type="text" value="${atividade.horaFim}" />
                </div>
                <small class="form-control-feedback">${errors.from('atividade.horaFim')}</small>
            </div>

            <div class="col s6">
                <div class="input-field ${not empty errors.from('atividade.horaFim') ? "has-danger" : ""}">
                    <label class="" for="inputhoraFim-time"><fmt:message key="atividade.horaFim" /></label>
                    <input disabled class="timepicker" id="inputhoraFim-time" type="text" value="${atividade.horaFim}" />
                </div>
                <small class="form-control-feedback">${errors.from('atividade.horaFim')}</small>
            </div>

            <input type="datetime" name="atividade.horaFim" value="${atividade.horaFim}" style="display:none;">
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