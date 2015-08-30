<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/views/include.jsp" %>
<%@ include file="/views/header.jsp" %>

<c:if test="${contenido.menu != null}">
	<%@ include file="/views/menu.jsp"%>	
</c:if>


<jsp:include page="${contenido.vista}" />
<%@ include file="/views/footer.jsp" %>