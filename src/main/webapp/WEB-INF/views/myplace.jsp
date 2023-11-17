<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp" %>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=40b23bfe4b90f18883b428591d313e05&libraries=services"></script>
<script src="${root}/assets/js/myplace.js"></script>
<c:if test="${empty member}">
    <script>
        alert("로그인이 필요합니다.");
        location.href = "${root}/index.jsp";
    </script>
</c:if>
<c:if test="${not empty member}">
<div class="container mt-5">
    <h4 class="fw-bold fs-2 p-3 text-center text-primary">나만의 핫플 기록하기</h4>
    <div class="row d-flex justify-content-around">
        <div id="map" style="width:700px;height:500px;"></div>
        <div class="m-3" style="width:500px;height:500px;">
            <form action="${root}/attraction/" method="post">
                <input type="hidden" name="action" value="save">
                <div class="mb-3">
                    <label>핫플 이름</label><input type="text" class="form-control" id="hotPlaceName" name="hotPlaceName" value="이태원 맛집"/>
                    <button type="button" class="btn btn-primary w-100 my-3" onclick="javascript:printMap();">검색하기</button>
                </div>
                <div class="mb-3">
                    <label>다녀온 날짜</label><input type="date" class="form-control" id="hotPlaceDate" name="hotPlaceDate" />
                </div>
                <div class="mb-3">
                    <label>메모</label>
                    <textarea name="memo" id="memo" class="form-control" cols="30" rows="10"></textarea>
            <div class="p-3"></div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-secondary w-100">저장하기</button>
            </div>
        </div>
        </form>
    </div>

    <!-- 인기글 & 최신글 end -->
</div>
</c:if>

<%@ include file="include/footer.jsp"%>