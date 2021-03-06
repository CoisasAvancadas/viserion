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
    <form class="col s12" action="${linkTo[EventoController].save}" method="POST">
        <c:if test="${not empty evento and evento.id > 0}">
            <input id="inputId" type="hidden" name="evento.id" value="${evento.id}" />
        </c:if>
        <div class="row">
            <div class="col s8">
                <div class="input-field ${not empty errors.from('evento.nome') ? "has-danger" : ""}">
                    <label class="" for="inputName"><fmt:message key="evento.nome" /></label>
                    <input class="" id="inputName" type="text" name="evento.nome" value="${evento.nome}" />
                </div>
                <small class="form-control-feedback">${errors.from('evento.nome')}</small>
            </div>

            <div class="col s4">
                <div class="input-field ${not empty errors.from('evento.descricao') ? "has-danger" : ""}">
                    <label class="" for="inputra"><fmt:message key="evento.descricao" /></label>
                    <input class="" id="inputra" type="text" name="evento.descricao" value="${evento.descricao}" />
                </div>
                <small class="form-control-feedback">${errors.from('evento.descricao')}</small>
            </div>
        </div>

        <div class="row">
            <div class="col s6">
                <div class="input-field ${not empty errors.from('evento.dataInicio') ? "has-danger" : ""}">
                    <label class="" for="inputdataInicio"><fmt:message key="evento.dataInicio" /></label>
                    <input class="datepicker" id="inputdataInicio" type="text" name="evento.dataInicio" value="${evento.dataInicio}" />
                </div>
                <small class="form-control-feedback">${errors.from('evento.dataInicio')}</small>
            </div>

            <div class="col s6">
                <div class="input-field ${not empty errors.from('evento.dataFim') ? "has-danger" : ""}">
                    <label class="" for="inputdataFim"><fmt:message key="evento.dataFim" /></label>
                    <input class="datepicker" id="inputdataFim" type="text" name="evento.dataFim" value="${evento.dataFim}" />
                </div>
                <small class="form-control-feedback">${errors.from('evento.dataFim')}</small>
            </div>
        </div>

        <div class="row">
            <div class="col s6">
                <div class="input-field ${not empty errors.from('evento.instituicao.id') ? "has-danger" : ""}">
                    <select name="evento.instituicao.id">
                        <c:forEach items="${instituicoes}" var="instituicao">  
                            <option value="${instituicao.id}">${instituicao.nome}</option>
                        </c:forEach>  
                    </select>
                    <label><fmt:message key="evento.instituicao" /></label>
                </div>
                <small class="form-control-feedback">${errors.from('evento.instituicao')}</small>
            </div>
            <div class="col s6">
                <div class="switch">
                    <br>
                    <label>
                        <fmt:message key="evento.ativo" />
                        <input type="checkbox" name="evento.ativo">
                        <span class="lever"></span>
                    </label>
                </div>
            </div>
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
    $(document).ready(function () {
        $('select').material_select();
    });

</script>