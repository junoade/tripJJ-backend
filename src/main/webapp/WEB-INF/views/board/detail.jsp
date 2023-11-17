<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../include/header.jsp" %>

<div class="container mt-5">
<h4 class="fw-bold fs-2 p-3 text-center text-primary">게시글 세부정보</h4>
<c:if test="${not empty board}">
	<div class="row d-flex justify-content-around">
		<div>
			<div class="p-3">	
				<div class="mb-3">
					<label>게시글 번호</label>
					<span>${board.articleNo}</span>
				</div>
				<div class="mb-3">
					<label>방문 지역</label>
					<span>${board.visitedArea}</span>
				</div>
				<div class="mb-3">
					<label>방문 날짜</label>
					<span>${board.visitedDate}</span>
				</div>
				<div class="mb-3">
					<label>게시글 제목</label>
					<span>${board.title}</span>
				</div>
				<div class="mb-3">
					<label>글쓴이</label>
					<span>${board.userId}</span>
				</div>
				<div class="mb-3">
					<label>조회수</label>
					<span>${board.hit}</span>
				</div>
				<div class="mb-3">
					<label>작성 날짜</label>
					<span>${board.publishedDate}</span>
				</div>
				<div class="mb-3">
					<label>내용</label>
					<span>${board.content}</span>
				</div>
			</div>
		</div>
	</div>
</c:if>
<c:if test="${empty board}">
		<p>조회된 게시글이 없습니다.</p>	
</c:if>

<a href = "${root}/board/modify/${board.articleNo}">수정하기</a>
<a href = "#" id="delLink">삭제하기</a>
<a href = "${root}/board/">게시판으로 돌아가기</a>
<a href = "${root}">메인으로</a>

</div>
<!-- TODO future work -->
<!-- 현재 header.jsp의 스크립트 코드에 의존 -->
<%@ include file="../include/footer.jsp"%>