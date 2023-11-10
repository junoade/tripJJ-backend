<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../include/header.jsp" %>

<div class="container mt-5">
<h4 class="fw-bold fs-2 p-3 text-center text-primary">게시글 수정</h4>
<c:if test="${not empty board}">
	<div class="row d-flex justify-content-around">
		<div>
			<div class="p-3">
				<form action="${root}/board/modify" method="post">
					<div class="mb-3">
						<label>게시글 번호</label>
						<input type="number" id="articleNo" name="articleNo" value="${board.articleNo}" readonly>
					</div>
					<div class="mb-3">
						<label>제목</label>
						<input type="text" class="form-control"
							id="title" name="title" value="${board.title}" >
					</div>
					<div class="mb-3">
						<label>방문 지역</label><input type="text" class="form-control"
												   id="placeName" name="visitedArea" value="${board.visitedArea}" >
					</div>
					<div class="mb-3">
						<label>다녀온 날짜</label><input type="date" class="form-control"
													id="visitedDate" name="visitedDate" value="${board.visitedDate}" >
					</div>
					<div class="mb-3">
						<label>조회수</label>
						<span>${board.hit}</span>
					</div>
					<div class="mb-3">
						<label>글쓴이</label>
						<span>${board.userId}</span>
					</div>
					<div class="mb-3">
						<label>등록 날짜</label>
						<span>${board.publishedDate}</span>
					</div>
					<div class="mb-3">
						<label>내용</label>
						<textarea name="content" id="content" class="form-control" cols="30"
							rows="10" placeholder="${board.content}"></textarea>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary w-100" id="#">수정하기</button>
					</div>
				</form>
				<a href = "#" id="delLink">삭제하기</a>
				<a href = "${root}/board/">게시판으로 돌아가기</a>
				<a href = "${root}">메인으로</a>
			</div>
		</div>
	</div>
</c:if>
<c:if test="${empty board}">
		<p>조회된 게시글이 없습니다.</p>	
</c:if>


<!-- TODO future work -->
<%-- <script type="text/javascript" src="${root}/assets/js/ajax/ajax_board.js"/> --%>
<!--  현재 header.jsp의 스크립트 코드 -->
</div>
<%@ include file="../include/footer.jsp"%>