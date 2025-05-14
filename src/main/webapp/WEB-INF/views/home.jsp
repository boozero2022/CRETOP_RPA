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

<!-- main -->
<main class="container d-flex flex-column justify-content-center align-items-center">
    <!-- alert -->
    <section id="alertBox" class="alert alert-danger align-items-center mb-0" role="alert" tabindex="-1" aria-hidden="true" style="display: none; opacity: 1;transition: opacity 3s ease;">
        <svg class="bi flex-shrink-0 me-2" role="img" aria-label="Danger:"><use xlink:href="#bi-exclamation-triangle-fill"/></svg>
        <div id="alertMsg"></div>
    </section>

    <!-- card -->
    <div class="card w-75 my-5 mx-auto">
        <div class="card-body p-5">
            <form id="rpaForm" class="needs-validation" method="post" action="/submit" novalidate>
                <!-- 사업자 등록번호 섹션 -->
                <section id="businessNumberSection" class="mb-4">
                    <label class="form-label">🏢 사업자 등록번호</label>
                    <div class="business-number-wrapper mb-3">
                        <div class="d-flex flex-row gap-2 align-items-start">
                            <div class="flex-grow-1">
                                <input type="text" name="businessNumbers" class="form-control me-2" placeholder="xxx-xx-xxxxx" pattern="\d{3}-\d{2}-\d{5}" required>
                                <div class="invalid-feedback">
                                    사업자 등록번호 형식이 올바르지 않습니다. 예: 123-45-67890
                                </div>
                            </div>
                            
                            <button type="button" class="btn btn-outline-primary me-2 opacity-75 addBusinessNumberBtn">
                                <svg class="bi"><use href="#bi-plus"></use></svg>
                            </button>
                            <button type="button" class="btn btn-outline-danger opacity-75 removeBusinessNumberBtn">
                                <svg class="bi"><use href="#bi-dash"></use></svg>
                            </button>
                        </div>
                    </div>  
                </section>
    
                <!-- 이메일 주소 섹션 -->
                <section id="emailSection" class="mb-4">
                    <label class="form-label">📧 이메일 주소</label>
                    <div class="email-wrapper mb-3">
                        <div class="d-flex flex-row gap-2 align-items-start">
                            <div class="flex-grow-1">
                                <input type="text" name="emailAddresses" class="form-control me-2" placeholder="recipient@kkpc.com" pattern="^[a-zA-Z0-9._%+\-]+@kkpc\.com$" required>
                                <div class="invalid-feedback">
                                    이메일 주소 형식이 올바르지 않습니다. 예: example@kkpc.com
                                </div>
                            </div>
                            
                            <button type="button" class="btn btn-outline-primary me-2 opacity-75 addEmailBtn">
                                <svg class="bi"><use href="#bi-plus"></use></svg>
                            </button>
                            <button type="button" class="btn btn-outline-danger opacity-75 removeEmailBtn">
                                <svg class="bi"><use href="#bi-dash"></use></svg>
                            </button>
                        </div>
                    </div>
                </section>

                <!-- 업체 요약표 섹션 -->
                <section id="summarySection" class="mb-4 form-check">
                    <input type="checkbox" class="form-check-input" id="summaryCheck">
                    <label class="form-check-label" for="summaryCheck">📊 업체 요약표 포함</label>
                </section>
        
                <button type="submit" class="btn btn-primary w-100 opacity-75">실행</button>
            </form>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmationLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="confirmationLabel">✅ 입력하신 내용이 맞습니까?</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <h1 class="fs-6">🏢 사업자 등록번호</h1>
                    <ul id="businessNumberList" class="mb-3"></ul>
                    <hr>
                    
                    <h1 class="fs-6">📧 이메일 주소</h1>
                    <ul id="emailList" class="mb-3"></ul>
                    <hr>

                    <h1 class="fs-6">📊 업체 요약표</h1>
                    <ul id="summaryInfo" class="mb-3"></ul>

                    <div class="modal-footer">
                        <button type="button" id="confirmSubmitBtn" class="btn btn-primary opacity-75 w-100">확인</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<!-- footer -->
<jsp:include page="/WEB-INF/views/common/footer.jsp" />

<!-- alert 관련 로직 -->
<script>
function showAlert(message) {
    const alertBox = document.getElementById("alertBox");
    const alertMessage = alertBox.querySelector("#alertMsg");

    alertMessage.textContent = message;

    alertBox.style.display = "flex";
    alertBox.style.opacity = "1"; 
    alertBox.focus();

    // 1초 후 opacity를 0으로 줄여서 3초간 fade-out
    setTimeout(() => {
        alertBox.style.opacity = "0";
    }, 1000);

    // 애니메이션이 끝난 후 display를 없애기
    setTimeout(() => {
        alertBox.style.display = "none";
    }, 3100); // 3초 + 약간의 margin
}
</script>

<!-- submit 관련 로직 -->
<script>
document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('rpaForm');
    const confirmModal = new bootstrap.Modal(document.getElementById('confirmModal'));
    const confirmSubmitBtn = document.getElementById('confirmSubmitBtn');

    form.addEventListener('submit', (event) => {
        event.preventDefault(); // 기본 제출 막기

        if (!form.checkValidity()) {
            event.stopPropagation(); // 유효하지 않은 경우 모달 띄우지 않음
            form.classList.add('was-validated');
            return;
        }

        // 유효할 경우 → 입력 데이터 추출하여 모달에 표시
        const businessNumbers = form.querySelectorAll('input[name="businessNumbers"]');
        const businessNumberList = document.getElementById('businessNumberList');
        businessNumberList.innerHTML = '';
        businessNumbers.forEach(input => {
            const li = document.createElement('li');
            li.textContent = input.value.trim();
            businessNumberList.appendChild(li);
        });

        const emailAddresses = form.querySelectorAll('input[name="emailAddresses"]');
        const emailList = document.getElementById('emailList');
        emailList.innerHTML = '';
        emailAddresses.forEach(input => {
            const li = document.createElement('li');
            li.textContent = input.value.trim();
            emailList.appendChild(li);
        });

        const summaryCheck = document.getElementById('summaryCheck');
        const summaryInfo = document.getElementById('summaryInfo');
        summaryInfo.innerHTML = '';

        const li = document.createElement('li');
        li.textContent = summaryCheck.checked ? '포함' : '미포함';
        summaryInfo.appendChild(li);

        // 모달 표시
        confirmModal.show();
    });

    // 모달 내 확인 버튼 클릭 → 실제 form 제출
    confirmSubmitBtn.addEventListener('click', () => {
        form.submit();
    });
});
</script>

<!-- 사용자 입력란 추가/삭제 로직 -->
<script>
    const maxInputs = 10;
    const minInputs = 1;

    // 사업자 등록번호
    const businessNumberSection = document.getElementById("businessNumberSection");
    const businessNumberWrapper = document.querySelector(".business-number-wrapper");

    // 이메일 주소
    const emailSection = document.getElementById("emailSection");
    const emailWrapper = document.querySelector(".email-wrapper");

    // 사업자 등록번호 추가/제거
    businessNumberSection.addEventListener("click", (event) => {
        event.stopPropagation();

        const addBtn = event.target.closest(".addBusinessNumberBtn");
        if (addBtn) {
            const totalInputs = businessNumberSection.querySelectorAll(".business-number-wrapper").length;

            if (totalInputs >= maxInputs) {
                showAlert("최대 10개까지 입력할 수 있습니다.");
                return;
            }

            const currentWrapper = addBtn.closest('.business-number-wrapper');
            const clone = currentWrapper.cloneNode(true);
            clone.querySelector("input").value = "";
            businessNumberSection.appendChild(clone);
            return;
        }
        
        const removeBtn = event.target.closest(".removeBusinessNumberBtn");
        if (removeBtn) {
            const currentWrapper = removeBtn.closest('.business-number-wrapper');
            const totalInputs = businessNumberSection.querySelectorAll(".business-number-wrapper").length;

            if (totalInputs === minInputs) {
                showAlert("최소 1개의 입력란은 필요합니다.");
                return;
            }

            currentWrapper.remove();
        }
    });

    // 이메일 주소 추가/제거
    emailSection.addEventListener("click", (event) => {
        event.stopPropagation();

        const addBtn = event.target.closest(".addEmailBtn");
        if (addBtn) {
            const totalInputs = emailSection.querySelectorAll(".email-wrapper").length;

            if (totalInputs >= maxInputs) {
                showAlert("최대 10개까지 입력할 수 있습니다.");
                return;
            }

            const currentWrapper = event.target.closest('.email-wrapper');
            const clone = currentWrapper.cloneNode(true);
            clone.querySelector("input").value = "";
            emailSection.appendChild(clone);
            return;
        }
        
        const removeBtn = event.target.closest(".removeEmailBtn");
        if (removeBtn) {
            const currentWrapper = event.target.closest('.email-wrapper');
            const totalInputs = emailSection.querySelectorAll(".email-wrapper").length;
            
            if (totalInputs ===  minInputs) {
                showAlert("최소 1개의 입력란은 필요합니다.");
                return;
            }

            currentWrapper.remove();
        }
    });
</script>
</body>
</html>
