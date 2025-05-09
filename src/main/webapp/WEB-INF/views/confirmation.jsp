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

<main class="container">
    <div class="card w-75 my-5 mx-auto">
        <img src="${pageContext.request.contextPath}/resources/img/robot.png" class="card-img-top" alt="working robot">
        <div class="card-body p-5">
            <h1 class="card-title fs-3 mb-3">✨ 제출완료</h5>
            <p class="card-text">
                CRETOP RPA를 실행합니다.<br/>
                실행결과는 요청하신 메일로 전달됩니다.
            </p>
            <a href="/" class="btn btn-primary w-100 opacity-75">🏠 홈으로 돌아가기</a>
        </div>
    </div>
</main>

<!-- footer -->
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
