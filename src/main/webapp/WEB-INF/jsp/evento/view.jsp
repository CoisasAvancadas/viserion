<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/header.jsp" %>

<div class="page-header">
    <div class="row">
        <div class="col 12">
            <h1><fmt:message key="evento" /> <small class="text-muted" style="text-transform: lowercase"><fmt:message key="register" /></small></h1>
        </div>
    </div>
</div>

<div class="row">
    <form class="col s12">
        <div class="row">
            <div class="col s8">
                <div class="input-field ${not empty errors.from('evento.nome') ? "has-danger" : ""}">
                    <label class="" for="inputName"><fmt:message key="evento.nome" /></label>
                    <input disabled class="" id="inputName" type="text" name="evento.nome" value="${evento.nome}" />
                </div>
                <small class="form-control-feedback">${errors.from('evento.nome')}</small>
            </div>

            <div class="col s4">
                <div class="input-field ${not empty errors.from('evento.descricao') ? "has-danger" : ""}">
                    <label class="" for="inputra"><fmt:message key="evento.descricao" /></label>
                    <input disabled class="" id="inputra" type="text" name="evento.descricao" value="${evento.descricao}" />
                </div>
                <small class="form-control-feedback">${errors.from('evento.descricao')}</small>
            </div>
        </div>

        <div class="row">
            <div class="col s6">
                <div class="input-field ${not empty errors.from('evento.dataInicio') ? "has-danger" : ""}">
                    <label class="" for="inputdataInicio"><fmt:message key="evento.dataInicio" /></label>
                    <input disabled class="datepicker" id="inputdataInicio" type="text" name="evento.dataInicio" value="${evento.dataInicio}" />
                </div>
                <small class="form-control-feedback">${errors.from('evento.dataInicio')}</small>
            </div>

            <div class="col s6">
                <div class="input-field ${not empty errors.from('evento.dataFim') ? "has-danger" : ""}">
                    <label class="" for="inputdataFim"><fmt:message key="evento.dataFim" /></label>
                    <input disabled class="datepicker" id="inputdataFim" type="text" name="evento.dataFim" value="${evento.dataFim}" />
                </div>
                <small class="form-control-feedback">${errors.from('evento.dataFim')}</small>
            </div>
        </div>

        <div class="row">
            <div class="col s6">
                <div class="input-field ${not empty errors.from('evento.instituicao.id') ? "has-danger" : ""}">
                    <select disabled name="evento.instituicao.id">
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
                        <input disabled type="checkbox" name="evento.ativo">
                        <span class="lever"></span>
                    </label>
                </div>
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