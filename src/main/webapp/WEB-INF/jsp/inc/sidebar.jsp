<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<ul id="slide-out" class="side-nav">
    <li>
        <div class="user-view">
            <div class="background">
                <img src="<c:url value="/images/AHMED.jpg" />"/>
            </div>
            <a href="#!user"><img class="circle" src="<c:url value="/images/AHMED.jpg" />"></a>
            <a href="#!name"><span class="white-text name">Ahmed</span></a>
            <a href="#!email"><span class="white-text email">ahmed@hotmail.com</span></a>
        </div>
    </li>
    
    <li class="no-padding">
        <ul class="collapsible collapsible-accordion">
            <li class="bold"><a class="collapsible-header waves-effect waves-teal"><i class="material-icons">person</i>Usuário</a>
                <div class="collapsible-body">
                    <ul>
                        <li><a href="<c:url value="/usuario" />"><i class="material-icons">list</i>Listar</a></li>
                        <li><a href="<c:url value="/usuario/novo" />"><i class="material-icons">add_box</i>Cadastrar</a></li>
                    </ul>
                </div>
            </li>
            <li class="bold"><a class="collapsible-header waves-effect waves-teal"><i class="material-icons">description</i>Currículo</a>
                <div class="collapsible-body">
                    <ul>
                        <li><a href="<c:url value="/curriculo" />"><i class="material-icons">list</i>Listar</a></li>
                        <li><a href="<c:url value="/curriculo/novo" />"><i class="material-icons">add_box</i>Cadastrar</a></li>
                    </ul>
                </div>
            </li>
            <li class="bold"><a class="collapsible-header waves-effect waves-teal"><i class="material-icons">share</i>Redes sociais</a>
                <div class="collapsible-body">
                    <ul>
                        <li><a href="<c:url value="/redesocial" />"><i class="material-icons">list</i>Listar</a></li>
                        <li><a href="<c:url value="/redesocial/novo" />"><i class="material-icons">add_box</i>Cadastrar</a></li>
                    </ul>
                </div>
            </li>
            
            <li><div class="divider"></div></li>
            
            <li class="bold"><a class="collapsible-header waves-effect waves-teal"><i class="material-icons">assignment_turned_in</i>Atividade</a>
                <div class="collapsible-body">
                    <ul>
                        <li><a href="<c:url value="/atividade" />"><i class="material-icons">list</i>Listar</a></li>
                        <li><a href="<c:url value="/atividade/novo" />"><i class="material-icons">add_box</i>Cadastrar</a></li>
                    </ul>
                </div>
            </li>
            <li class="bold"><a class="collapsible-header waves-effect waves-teal"><i class="material-icons">chrome_reader_mode</i>Tipo Atividade</a>
                <div class="collapsible-body">
                    <ul>
                        <li><a href="<c:url value="/tipoatividade" />"><i class="material-icons">list</i>Listar</a></li>
                        <li><a href="<c:url value="/tipoatividade/novo" />"><i class="material-icons">add_box</i>Cadastrar</a></li>
                    </ul>
                </div>
            </li>
            
            <li class="bold"><a class="collapsible-header waves-effect waves-teal"><i class="material-icons">school</i>Instituição</a>
                <div class="collapsible-body">
                    <ul>
                        <li><a href="<c:url value="/instituicao" />"><i class="material-icons">list</i>Listar</a></li>
                        <li><a href="<c:url value="/instituicao/novo" />"><i class="material-icons">add_box</i>Cadastrar</a></li>
                    </ul>
                </div>
            </li>
            <li class="bold"><a class="collapsible-header waves-effect waves-teal"><i class="material-icons">event_seat</i>Salas</a>
                <div class="collapsible-body">
                    <ul>
                        <li><a href="<c:url value="/sala" />"><i class="material-icons">list</i>Listar</a></li>
                        <li><a href="<c:url value="/sala/novo" />"><i class="material-icons">add_box</i>Cadastrar</a></li>
                    </ul>
                </div>
            </li>
            <li class="bold"><a class="collapsible-header waves-effect waves-teal"><i class="material-icons">event</i>Eventos</a>
                <div class="collapsible-body">
                    <ul>
                        <li><a href="<c:url value="/evento" />"><i class="material-icons">list</i>Listar</a></li>
                        <li><a href="<c:url value="/evento/novo" />"><i class="material-icons">add_box</i>Cadastrar</a></li>
                    </ul>
                </div>
            </li>
            
            <li><div class="divider"></div></li>
            
            <li class="bold"><a class="collapsible-header waves-effect waves-teal"><i class="material-icons">assignment</i>Papéis</a>
                <div class="collapsible-body">
                    <ul>
                        <li><a href="<c:url value="/papel" />"><i class="material-icons">list</i>Listar</a></li>
                        <li><a href="<c:url value="/papel/novo" />"><i class="material-icons">add_box</i>Cadastrar</a></li>
                    </ul>
                </div>
            </li>
            <li class="bold"><a class="collapsible-header waves-effect waves-teal"><i class="material-icons">assignment_ind</i>Permissões</a>
                <div class="collapsible-body">
                    <ul>
                        <li><a href="<c:url value="/permissao" />"><i class="material-icons">list</i>Listar</a></li>
                        <li><a href="<c:url value="/permissao/novo" />"><i class="material-icons">add_box</i>Cadastrar</a></li>
                    </ul>
                </div>
            </li>
            
            <li><div class="divider"></div></li>
            
            <li class="bold"><a class="collapsible-header waves-effect waves-teal"><i class="material-icons">place</i>Endereços</a>
                <div class="collapsible-body">
                    <ul>
                        <li><a href="<c:url value="/endereco" />"><i class="material-icons">list</i>Listar</a></li>
                        <li><a href="<c:url value="/endereco/novo" />"><i class="material-icons">add_box</i>Cadastrar</a></li>
                    </ul>
                </div>
            </li>
        </ul>
    </li>
</ul>
