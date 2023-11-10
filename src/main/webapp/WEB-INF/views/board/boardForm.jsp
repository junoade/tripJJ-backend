<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="container mt-5">
	<h4 class="fw-bold fs-2 p-3 text-center text-primary">게시판 글쓰기</h4>
	<div class="row d-flex justify-content-around">
		<div>
			<div class="p-3">
				<form action="${root}/board/" method="post">
					<div class="mb-3">
						<label>제목</label>
						<input type="text" class="form-control"  id="title" name="title" value="제목" >
					</div>
					<div class="mb-3">
						<label>방문 지역</label>
						<input type="text" class="form-control" id="placeName" name="visitedArea" value="방문한 지역" >
					</div>
					<div class="mb-3">
						<label>다녀온 날짜</label>
						<input type="date" class="form-control" id="visitedDate" name="visitedDate" >
					</div>
					<div class="mb-3">
						<label>세부사항</label>
						<textarea name="content" id="content" class="form-control" cols="30" rows="10"></textarea>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary w-100" id="#">글쓰기</button>
					</div>
				</form>
			</div>

		</div>
	</div>
</div>
<%@ include file="../include/footer.jsp"%>
