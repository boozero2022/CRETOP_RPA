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

<main class="container text-center d-flex flex-column justify-content-center align-items-center"  style="height : 75vh;">
    <h1 class="display-1 fw-bold text-secondary">422</h1>
    <p class="fs-3">🤔 <span class="text-muted">요청을 처리할 수 없습니다.</span></p>
    <p class="lead">요청한 내용을 이해했지만, 서버에서 처리할 수 없는 형식이거나 조건을 만족하지 않습니다.</p>
    <a href="/" class="btn btn-primary mt-3 opacity-75">🏠 홈으로 돌아가기</a>
</main>

<!-- footer -->
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
