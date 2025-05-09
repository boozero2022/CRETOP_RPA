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
    
    <h1 class="display-1 fw-bold text-danger">500</h1>
    <p class="fs-3">💥 <span class="text-muted">서버 오류가 발생했습니다.</span></p>
    <p class="lead">요청을 처리하는 중 예상치 못한 문제가 발생했습니다. 관리자에게 문의해주세요.</p>
    <a href="/" class="btn btn-primary mt-3 opacity-75">🏠 홈으로 돌아가기</a>
    
</main>  

<!-- footer -->
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>