<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" 
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" 
crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js" 
        integrity="sha256-uWtSXRErwH9kdJTIr1swfHFJn/d/WQ6s72gELOHXQGM=" 
crossorigin="anonymous"></script>
<script src="<c:url value="/js/init.js"/>"></script>
<c:if test="${not empty errors}">
    <script>
        <c:forEach items="${errors}" var="error">
        Materialize.toast(
            "<fmt:message key="${error.category}"/> - ${error.message} \n"
        , 4000);
        </c:forEach>
    </script>
</c:if>
<c:if test="${not empty notice}">
    <script>
        Materialize.toast(
                "${notice}"
        , 4000);
    </script>
</c:if>
    
</body>
</html>