<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ attribute name="message" required="true" type="br.com.caelum.vraptor.validator.Message" %>
<c:if test="${message != null}" >
	<c:set var="severity" value="text-success"  />
	<c:set var="alert" value="alert-success"  />
	<c:if test="${message.severity.toString() == 'ERROR'}">
		<c:set var="severity" value="text-danger"  />
		<c:set var="alert" value="alert-danger"  />
	</c:if>
	<div class="alert ${alert}" role="alert">
		<p class="${severity}">${message.message}</p>
	</div>
</c:if>