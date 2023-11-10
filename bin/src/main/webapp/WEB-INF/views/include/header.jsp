<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "root" value = "${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Enjoy Trip </title>
    <link rel="shortcut icon" href="${root}/assets/img/trip.png" />
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
            crossorigin="anonymous"
    />
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"
    />
</head>
<body>
<!-- 상단 navbar start -->
<nav class="navbar navbar-expand-sm navbar-light bg-light shadow">
    <div class="container d-flex justify-content-around">
        <a class="navbar-brand text-primary fw-bold" href="${root}/">
            <img src="${root}/assets/img/trip.png" alt="" width="60" />
            Enjoy Trip
        </a>
        <button
                class="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent"
                aria-expanded="false"
                aria-label="Toggle navigation"
        >
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse flex-row-reverse" id="navbarSupportedContent">
            <!-- 로그인 전 -->
        <c:if test="${empty member}">
            <div id="nav_confirm_off" class="navbar-nav mb-2 me-2 mb-lg-0">
                <div class="d-flex">
                    <div class="nav-item">
                        <a class="nav-link" aria-current="page" href="${root}/user/register">회원가입</a>
                    </div>
                    <div class="nav-item">
                        <a class="nav-link" aria-current="page" href="${root}/user/login">로그인</a>
                    </div>
                </div>
            </div>
        </c:if>

        <!-- 로그인 후 -->
        <c:if test="${not empty member}">
            <div id="nav_confirm_on" class="navbar-nav mb-2 me-2 mb-lg-0">
                <div class="d-flex">
                    <div class="nav-item">
                        <a class="nav-link" aria-current="page" href="${root}/board/">게시판</a>
                    </div>
                    <div class="nav-item">
                        <a class="nav-link" aria-current="page" href="${root}/user/logout">로그아웃</a>
                    </div>
                    <div class="nav-item dropdown">
                        <a
                                class="nav-link dropdown-toggle"
                                href=""
                                id="navbarDropdown"
                                role="button"
                                data-bs-toggle="dropdown"
                                aria-expanded="false"
                        >
                            마이페이지
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li>
                                <a
                                        class="dropdown-item"
                                        href="${root}/user/mypage"
                                >회원정보수정</a>
                            </li>
                            <li>
                                <a
                                        class="dropdown-item"
                                        href="${root}/attraction/"
                                >나만의 핫플 보기</a>
                            </li>
                            <li><a class="dropdown-item text-bg-success" href="${root}/attraction/regist">나만의 핫플 추가</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </c:if>
        </div>
    </div>
</nav>
<!-- 상단 navbar end -->
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"
></script>
<script>
    let msg = "${msg}";
    if(msg){
        alert(msg);
    }
</script>

