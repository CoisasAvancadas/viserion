<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/header.jsp" %>

<div class="page-header">
    <div class="row">
        <div class="col 12">
            <h1><fmt:message key="atividade" /> <small class="text-muted" style="text-transform: lowercase"><fmt:message key="register" /></small></h1>
        </div>
    </div>
</div>

<div class="row">
    <form class="col s12" action="${linkTo[AtividadeController].save(EventoId)}" method="POST">
        <c:if test="${not empty atividade and atividade.id > 0}">
            <input id="inputId" type="hidden" name="atividade.id" value="${atividade.id}" />
        </c:if>
        <div class="row">
            <div class="col s8">
                <div class="input-field">
                    <label class="" for="inputName"><fmt:message key="atividade.nome" /></label>
                    <input class="" id="inputName" type="text" name="atividade.nome" value="${atividade.nome}" />
                </div>
                <small class="form-control-feedback">${errors.from('atividade.nome')}</small>
            </div>

            <div class="col s4">
                <div class="input-field">
                    <label class="" for="inputdata"><fmt:message key="atividade.data" /></label>
                    <input class="datepicker" id="inputdata" name="atividade.data" type="text" value="${atividade.data}" />
                </div>
                <small class="form-control-feedback">${errors.from('atividade.data')}</small>
            </div>
        </div>

        <div class="row">
            <div class="col s6">
                <div class="input-field">
                    <label class="" for="inputhoraInicio"><fmt:message key="atividade.horaInicio" /></label>
                    <input class="timepicker" id="inputhoraInicio" name="atividade.horaInicio" type="text" value="${atividade.horaInicio}" />
                </div>
                <small class="form-control-feedback">${errors.from('atividade.horaInicio')}</small>
            </div>

            <div class="col s6">
                <div class="input-field">
                    <label class="" for="inputhoraFim"><fmt:message key="atividade.horaFim" /></label>
                    <input class="timepicker" id="inputhoraFim" name="atividade.horaFim" type="text" value="${atividade.horaFim}" />
                </div>
                <small class="form-control-feedback">${errors.from('atividade.horaFim')}</small>
            </div>
        </div>
        
        <div class="row">
            <div class="col s6">
                <div class="input-field ${not empty errors.from('atividade.tipoAtividade.id') ? "has-danger" : ""}">
                    <select name="atividade.tipoAtividade.id" value="${atividade.tipoAtividade.id}">
                        <c:forEach items="${tipoAtividades}" var="tipoAtividade">  
                            <option value="${tipoAtividade.id}" ${(atividade.tipoAtividade.id==tipoAtividade.id) ? "selected" : " "} >${tipoAtividade.nome}</option>
                        </c:forEach>  
                    </select>
                    <label><fmt:message key="atividade.tipoAtividade" /></label>
                </div>
                <small class="form-control-feedback">${errors.from('atividade.tipoAtividade')}</small>
            </div>
            <div class="col s6">
                <div class="input-field ${not empty errors.from('atividade.sala.id') ? "has-danger" : ""}">
                    <select name="atividade.sala.id" value="${atividade.sala.id}">
                        <c:forEach items="${salas}" var="sala">
                            <option value="${sala.id}" ${(atividade.sala.id==sala.id) ? "selected" : " "} >${sala.nome}</option>
                        </c:forEach>  
                    </select>
                    <label><fmt:message key="atividade.sala" /></label>
                </div>
                <small class="form-control-feedback">${errors.from('atividade.sala')}</small>
            </div>
        </div>
       
        <input class="" id="inputEventoId" name="atividade.evento.id" type="hidden" value="${EventoId}" />

        <button type="submit" class="btn btn-primary">Salvar</button>
        <button type="reset" class="btn btn-flat">Resetar</button>

    </form>
</div>

<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %> 
<%@ include file="/WEB-INF/jsp/inc/scripts.jsp" %>
<script>
    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 60, // Creates a dropdown of 15 years to control year,
        today: 'Hoje',
        clear: 'Limpar',
        close: 'Fechar',
        closeOnSelect: false, // Close upon selecting a date,
        format: 'mm/dd/yyyy'
    });

    $('.timepicker').pickatime({
        default: 'now', // Set default time: 'now', '1:30AM', '16:30'
        fromnow: 0, // set default time to * milliseconds from now (using with default = 'now')
        twelvehour: false, // Use AM/PM or 24-hour format
        donetext: 'OK', // text for done-button
        cleartext: 'Clear', // text for clear-button
        canceltext: 'Cancel', // Text for cancel-button
        autoclose: false, // automatic close timepicker
        ampmclickable: true // make AM PM clickable
    });
    $(".timepicker").on("change", function() {
        this.value = this.value + ":00";
    });
    
    $(document).ready(function () {
        $('select').material_select();
    });

</script>