<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.Year" %>
<%
    int currentYear = Year.now().getValue();
    request.setAttribute("currentYear", currentYear);
%>

<div class="container">
    <footer class="py-3 my-4 border-top">
        <p class="text-center text-body-secondary">© ${currentYear} KKPC Group. All rights reserved</p>
    </footer>
</div>

<script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/color-mode-toggler.js"></script>