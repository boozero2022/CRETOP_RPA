<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>크레탑 RPA</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>

<body>
<!-- header -->
<jsp:include page="/WEB-INF/views/common/header.jsp" />
    
<main class="container text-center d-flex flex-column justify-content-center align-items-center" style="height : 75vh;">
    <h1 class="display-1 fw-bold text-warning">400</h1>
    <p class="fs-3">⚠️ <span class="text-muted">잘못된 요청입니다.</span></p>
    <p class="lead">입력값이 유효하지 않거나 요청 형식이 올바르지 않습니다.</p>
    <a href="/" class="btn btn-primary mt-3 opacity-75">🏠 홈으로 돌아가기</a>
</main>  

<!-- footer -->
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>