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
    <form class="col s12" action="${linkTo[AtividadeController].save}" method="POST">
        <c:if test="${not empty atividade and atividade.id > 0}">
            <div class="col s12">
                <div class="form-group">
                    <label class="control-label" for="inputId">ID</label>
                    <input class="form-control" id="inputId" type="text" name="atividade.id" value="${atividade.id}" disabled readonly="readonly" />
                    <input type="hidden" name="_method" value="PUT" />
                </div>
            </div>
        </c:if>
        <div class="row">
            <div class="col s12">
                <div class="input-field ${not empty errors.from('atividade.nome') ? "has-danger" : ""}">
                    <label class="" for="inputName"><fmt:message key="atividade.nome" /></label>
                    <input class="" id="inputName" type="text" name="atividade.nome" value="${atividade.nome}" />
                </div>
                <small class="form-control-feedback">${errors.from('atividade.nome')}</small>
            </div>
        </div>

        <div class="row">
            <div class="col s6">
                <div class="input-field ${not empty errors.from('atividade.horaInicio') ? "has-danger" : ""}">
                    <label class="" for="inputhoraInicio-date"><fmt:message key="atividade.horaInicio" /></label>
                    <input class="datepicker" id="inputhoraInicio-date" type="text" name="atividade.horaInicio-date" value="${atividade.horaInicio}" />
                </div>
                <small class="form-control-feedback">${errors.from('atividade.horaInicio')}</small>
            </div>

            <div class="col s6">
                <div class="input-field ${not empty errors.from('atividade.horaInicio') ? "has-danger" : ""}">
                    <label class="" for="inputhoraInicio-time"><fmt:message key="atividade.horaInicio" /></label>
                    <input class="timepicker" id="inputhoraInicio-time" type="text" name="atividade.horaInicio-time" value="${atividade.horaInicio}" />
                </div>
                <small class="form-control-feedback">${errors.from('atividade.horaInicio')}</small>
            </div>

            <input type="datetime" id="horaInicio" name="atividade.horaInicio" value="${atividade.horaInicio}" style="display:none">
        </div>

        <div class="row">
            <div class="col s6">
                <div class="input-field ${not empty errors.from('atividade.horaFim') ? "has-danger" : ""}">
                    <label class="" for="inputhoraFim-date"><fmt:message key="atividade.horaInicio" /></label>
                    <input class="datepicker" id="inputhoraFim-date" name="atividade.horaFim-date" type="text" value="${atividade.horaFim}" />
                </div>
                <small class="form-control-feedback">${errors.from('atividade.horaFim')}</small>
            </div>

            <div class="col s6">
                <div class="input-field ${not empty errors.from('atividade.horaFim') ? "has-danger" : ""}">
                    <label class="" for="inputhoraFim-time"><fmt:message key="atividade.horaFim" /></label>
                    <input class="timepicker" id="inputhoraFim-time" name="atividade.horaFim-time" type="text" value="${atividade.horaFim}" />
                </div>
                <small class="form-control-feedback">${errors.from('atividade.horaFim')}</small>
            </div>

            <input type="datetime" id="horaFim" name="atividade.horaFim" value="${atividade.horaFim}" style="display:none;">
        </div>

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
        format: 'mm/dd/yyyy',
        onClose: function () {
            updateHoraInicio();
            updateHoraFim();
        }
    });

    $('.timepicker').pickatime({
        default: 'now', // Set default time: 'now', '1:30AM', '16:30'
        fromnow: 0, // set default time to * milliseconds from now (using with default = 'now')
        twelvehour: false, // Use AM/PM or 24-hour format
        donetext: 'OK', // text for done-button
        cleartext: 'Clear', // text for clear-button
        canceltext: 'Cancel', // Text for cancel-button
        autoclose: false, // automatic close timepicker
        ampmclickable: true, // make AM PM clickable
        onClose: function () {
            updateHoraInicio();
            updateHoraFim();
        } //Function for after opening timepicker
    });
    
    function updateHoraInicio() {
        document.getElementById("horaInicio").value = 
            document.getElementById("inputhoraInicio-date").value + "T" + 
            document.getElementById("inputhoraInicio-time").value;
    }
    
    function updateHoraFim() {
        document.getElementById("horaFim").value = 
            document.getElementById("inputhoraFim-date").value + "T" + 
            document.getElementById("inputhoraFim-time").value;
    }

</script>