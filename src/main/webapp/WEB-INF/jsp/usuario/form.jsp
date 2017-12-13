<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/header.jsp" %>

<div class="page-header">
    <div class="row">
        <div class="col 12">
            <h1><fmt:message key="usuario" /> <small class="text-muted" style="text-transform: lowercase"><fmt:message key="register" /></small></h1>
        </div>
    </div>
</div>

<div class="row">
    <form class="col s12" action="${linkTo[UsuarioController].save}" method="post" enctype="application/x-www-form-urlencoded">
        <c:if test="${not empty usuario and usuario.id > 0}">
            <input class="form-control" id="inputId" type="hidden" name="usuario.id" value="${usuario.id}" />
        </c:if>
        <div class="row">
            <div class="col s8">
                <div class="input-field ${not empty errors.from('usuario.nome') ? "has-danger" : ""}">
                    <label class="" for="inputName"><fmt:message key="usuario.nome" /></label>
                    <input  class="" id="inputName" type="text" name="usuario.nome" value="${usuario.nome}" />
                </div>
                <small class="form-control-feedback">${errors.from('usuario.nome')}</small>
            </div>
            
            <div class="col s4">
                <div class="input-field ${not empty errors.from('usuario.ra') ? "has-danger" : ""}">
                    <label class="" for="inputra"><fmt:message key="usuario.ra" /></label>
                    <input  class="" id="inputra" type="text" name="usuario.ra" value="${usuario.ra}" />
                </div>
                <small class="form-control-feedback">${errors.from('usuario.ra')}</small>
            </div>
        </div>

        <div class="row">
            <div class="col s4">
                <div class="input-field ${not empty errors.from('usuario.nascimento') ? "has-danger" : ""}">
                    <label class="" for="inputnascimento"><fmt:message key="usuario.nascimento" /></label>
                    <input  class="datepicker" id="inputnascimento" type="text" name="usuario.nascimento" value="${usuario.nascimento}" />
                </div>
                <small class="form-control-feedback">${errors.from('usuario.nascimento')}</small>
            </div>
            
            <div class="col s4">
                <div class="input-field ${not empty errors.from('usuario.password') ? "has-danger" : ""}">
                    <label class=""><fmt:message key="usuario.password" /></label>
                    <input  class="" type="password" name="usuario.password" value="${usuario.password}" describedby="inputPasswordStatus" />
                </div>
                <small class="form-control-feedback">${errors.from('usuario.password')}</small>
            </div>
            <div class="col s4">
                <div class="input-field ${not empty errors.from('usuario.username') ? "has-danger" : ""}">
                    <label class="" for="inputUserName"><fmt:message key="usuario.username" /></label>
                    <input  class="" id="inputUserName" type="text" name="usuario.username" value="${usuario.username}" />
                </div>
                <small class="form-control-feedback">${errors.from('usuario.username')}</small>
            </div>
        </div>

        <div class="row">
            <div class="col s6">
                <div class="input-field ${not empty errors.from('usuario.cpf') ? "has-danger" : ""}">
                    <label class="" for="inputcpf"><fmt:message key="usuario.cpf" /></label>
                    <input  class="" id="inputcpf" type="text" name="usuario.cpf" value="${usuario.cpf}" />
                </div>
                <small class="form-control-feedback">${errors.from('usuario.cpf')}</small>
            </div>
        
            <div class="col s6">
                <div class="input-field ${not empty errors.from('usuario.rg') ? "has-danger" : ""}">
                    <label class="" for="inputrg"><fmt:message key="usuario.rg" /></label>
                    <input  class="" id="inputrg" type="text" name="usuario.rg" value="${usuario.rg}" />
                </div>
                <small class="form-control-feedback">${errors.from('usuario.rg')}</small>
            </div>
        </div>

        <div class="file-field input-field">
            <div class="btn">
                <span>Selecionar foto</span>
                <input id="inputPictura" type="file">
            <!--    <input id="inputFoto" type="text" name="usuario.foto"> --> 
            </div>
            <div class="file-path-wrapper">
                <input class="file-path validate" type="text">
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Apagar</button>
            
        <button type="submit" class="btn btn-primary">Salvar</button>
        <button type="reset" class="btn btn-flat">Resetar</button>

    </form>
</div>

<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %> 
<%@ include file="/WEB-INF/jsp/inc/scripts.jsp" %>

<script>
//    function getBase64(file) {
//        var reader = new FileReader();
//        reader.readAsDataURL(file);
//        reader.onload = function () {
//            console.log(reader.result);
//            return reader.result;
//        };
//        reader.onerror = function (error) {
//            console.log('Error: ', error);
//            return null;
//       };
//    }
//


//    $("#inputPictura").on("change", function() {
//        var file = document.getElementById('inputPictura').files[0];
//        document.getElementById("inputFoto").value = getBase64(file); // prints the base64 string
//    });
//    
//    
//    
    function getBase64(file) {
        return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => resolve(reader.result);
            reader.onerror = error => reject(error);
        });
    }
    $("#inputPictura").on("change", function() {
        var file = document.getElementById('inputPictura').files[0];
        getBase64(file).then(
            data => console.log(data)
        );
        getBase64(file).then(
            data => document.getElementById("inputFoto").value = (data)
        );
    });

</script>