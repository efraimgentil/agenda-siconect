<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="label" required="true" type="java.lang.String"%>
<%@ attribute name="name" required="true" %>
<%@ attribute name="type" required="false" %>
<%@ attribute name="value" required="false" %>
<%@ attribute name="readonly" required="false" type="java.lang.Boolean"%>
<c:set var="type" value="${type == null ? 'text' : type}" />
<c:set var="readonly" value="${readonly == null ? false : readonly}" />
<div class="form-group">
	<label class="col-sm-2 control-label">${label}</label>
	<div class="col-sm-10">
		<input name="${name}" class="form-control" type="${type}" value="${value}"
		 	${readonly == true ? 'readonly="readonly"' : '' }	 />
	</div>
</div>