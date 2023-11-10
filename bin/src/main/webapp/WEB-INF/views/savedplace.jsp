<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp" %>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=40b23bfe4b90f18883b428591d313e05&libraries=services"></script>
<script src="${root}/assets/js/savedPlace.js"></script>
<c:if test="${empty member}">
    <script>
        alert("로그인이 필요합니다.");
        location.href = "${root}/";
    </script>
</c:if>
<c:if test="${not empty member}">
<div class="container mt-5">
    <h4 class="fw-bold fs-2 p-3 text-center text-primary">나만의 핫플 보기</h4>
    <div class="row d-flex justify-content-around">
        <div id="map" style="width:700px;height:500px;"></div>
        <div class="m-3" style="width:500px;height:500px;">
            <ul class="list-group">
                <c:forEach var="place" items="${hotplaces}">
                    <li class="list-group-item d-flex justify-content-end">
                        <button type="button" class = "btn btn-secondary btn-sm justify-item" onclick="printMySavedPlace(`${place.placeName}`)">${place.placeName}</button>
                        <button type="button" class = "btn btn-primary btn-sm justify-item" onclick="openPopup(this, ${place.placeNo})">조회</button>
                        <button type="button" id="delBtn" class = "btn btn-danger btn-sm" onclick="ajaxDeleteHotplace(${place.placeNo})">삭제</button>
                    </li>
                </c:forEach>
            </ul>
            <!-- 레이어 팝업 구현-->
            <div id="popup" style="display:none;">
                <h2>핫플레이스 세부 조회</h2>
                <div id="popup-content">
                    <p id="popup-detail"></p>
                </div>
                <button type="button" id="closePopup" class="btn-close" aria-label="Close" onclick="closePopup(this)"></button>
            </div>
        </div>

        <!-- 인기글 & 최신글 end -->
    </div>
</c:if>
<%@ include file="include/footer.jsp"%>