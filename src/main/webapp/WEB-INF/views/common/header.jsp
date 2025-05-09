<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- svg 아이콘 포함 -->
<jsp:include page="/WEB-INF/views/common/svg.jsp" />

<!-- 반응형 헤더 -->
<div class="container border-bottom mb-4">
    <div class="row py-3 px-3 gy-2">

        <!-- lg 이상: 좌측 로고 + 담당자 / 우측 메뉴얼 + 테마 -->
        <div class="d-none d-lg-flex justify-content-between align-items-center">
            <div class="d-flex align-items-center text-nowrap">
                <a href="/" class="d-flex align-items-center link-body-emphasis text-decoration-none text-nowrap">
                    <svg class="bi logo me-3 fs-2 flex-shrink-0"><use href="#robot"></use></svg>
                    <span class="fs-2 me-4">CRETOP RPA</span>
                </a>
                <div class="flex-shrink-1 text-nowrap small text-secondary">
                    담당자 : 김석진 사원 (02-6961-1334), 양지인 사원 (02-6961-1335)
                </div>
            </div>

            <div class="d-flex align-items-center gap-3">
                <a href="/manual" class="nav-link d-flex align-items-center px-2 py-1">
                    <svg class="bi me-2" aria-hidden="true"><use href="#bi-file-earmark-arrow-down-fill"></use></svg>
                    <span>메뉴얼</span>
                </a>
                <div class="dropdown">
                    <button class="btn btn-link nav-link dropdown-toggle d-flex align-items-center px-2 py-1" id="bd-theme" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <svg class="bi theme-icon-active" aria-hidden="true"><use href="#moon-stars-fill"></use></svg>
                        <span class="d-lg-none ms-2" id="bd-theme-text"></span>
                    </button>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="bd-theme-text">
                        <li><button type="button" class="dropdown-item d-flex align-items-center" data-bs-theme-value="light"><svg class="bi me-2 opacity-50"><use href="#sun-fill"></use></svg>Light<svg class="bi ms-auto d-none"><use href="#check2"></use></svg></button></li>
                        <li><button type="button" class="dropdown-item d-flex align-items-center active" data-bs-theme-value="dark"><svg class="bi me-2 opacity-50"><use href="#moon-stars-fill"></use></svg>Dark<svg class="bi ms-auto d-none"><use href="#check2"></use></svg></button></li>
                        <li><button type="button" class="dropdown-item d-flex align-items-center" data-bs-theme-value="auto"><svg class="bi me-2 opacity-50"><use href="#circle-half"></use></svg>Auto<svg class="bi ms-auto d-none"><use href="#check2"></use></svg></button></li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- md 이상 ~ lg 미만: 상단 로고 / 중간 담당자 / 하단 메뉴얼+테마 -->
        <div class="d-none d-md-block d-lg-none">
            <div class="d-flex justify-content-center mb-2 text-nowrap">
                <a href="/" class="d-flex align-items-center justify-content-center link-body-emphasis text-decoration-none">
                    <svg class="bi logo me-2 fs-2 flex-shrink-0"><use href="#robot"></use></svg>
                    <span class="fs-2">CRETOP RPA</span>
                </a>
            </div>
            <div class="text-center small text-secondary mb-2">
                담당자 : 김석진 사원 (02-6961-1334), 양지인 사원 (02-6961-1335)
            </div>
            <div class="d-flex justify-content-center align-items-center gap-3">
                <a href="/manual" class="nav-link d-flex align-items-center px-2 py-1">
                    <svg class="bi me-2" aria-hidden="true"><use href="#bi-file-earmark-arrow-down-fill"></use></svg>
                    <span>메뉴얼</span>
                </a>
                <div class="dropdown">
                    <button class="btn btn-link nav-link dropdown-toggle d-flex align-items-center px-2 py-1" id="bd-theme" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <svg class="bi theme-icon-active" aria-hidden="true"><use href="#moon-stars-fill"></use></svg>
                        <span class="d-lg-none ms-2" id="bd-theme-text"></span>
                    </button>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="bd-theme-text">
                        <li><button type="button" class="dropdown-item d-flex align-items-center" data-bs-theme-value="light"><svg class="bi me-2 opacity-50"><use href="#sun-fill"></use></svg>Light<svg class="bi ms-auto d-none"><use href="#check2"></use></svg></button></li>
                        <li><button type="button" class="dropdown-item d-flex align-items-center active" data-bs-theme-value="dark"><svg class="bi me-2 opacity-50"><use href="#moon-stars-fill"></use></svg>Dark<svg class="bi ms-auto d-none"><use href="#check2"></use></svg></button></li>
                        <li><button type="button" class="dropdown-item d-flex align-items-center" data-bs-theme-value="auto"><svg class="bi me-2 opacity-50"><use href="#circle-half"></use></svg>Auto<svg class="bi ms-auto d-none"><use href="#check2"></use></svg></button></li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- sm 이하: 상단 로고 / 하단 메뉴얼 + 테마 -->
        <div class="d-block d-md-none text-center">
            <div class="mb-3">
                <a href="/" class="d-flex align-items-center justify-content-center link-body-emphasis text-decoration-none text-nowrap">
                    <svg class="bi logo me-2 fs-2 flex-shrink-0"><use href="#robot"></use></svg>
                    <span class="fs-2">CRETOP RPA</span>
                </a>
            </div>
            <div class="d-flex justify-content-center align-items-center gap-3">
                <a href="/manual" class="nav-link d-flex align-items-center px-2 py-1">
                    <svg class="bi me-2" aria-hidden="true"><use href="#bi-file-earmark-arrow-down-fill"></use></svg>
                    <span>메뉴얼</span>
                </a>
                <div class="dropdown">
                    <button class="btn btn-link nav-link dropdown-toggle d-flex align-items-center px-2 py-1" id="bd-theme" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <svg class="bi theme-icon-active" aria-hidden="true"><use href="#moon-stars-fill"></use></svg>
                        <span class="d-lg-none ms-2" id="bd-theme-text"></span>
                    </button>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="bd-theme-text">
                        <li><button type="button" class="dropdown-item d-flex align-items-center" data-bs-theme-value="light"><svg class="bi me-2 opacity-50"><use href="#sun-fill"></use></svg>Light<svg class="bi ms-auto d-none"><use href="#check2"></use></svg></button></li>
                        <li><button type="button" class="dropdown-item d-flex align-items-center active" data-bs-theme-value="dark"><svg class="bi me-2 opacity-50"><use href="#moon-stars-fill"></use></svg>Dark<svg class="bi ms-auto d-none"><use href="#check2"></use></svg></button></li>
                        <li><button type="button" class="dropdown-item d-flex align-items-center" data-bs-theme-value="auto"><svg class="bi me-2 opacity-50"><use href="#circle-half"></use></svg>Auto<svg class="bi ms-auto d-none"><use href="#check2"></use></svg></button></li>
                    </ul>
                </div>
            </div>
        </div>

    </div>
</div>