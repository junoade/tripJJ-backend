<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<div class="container mt-5">
    <div class="row d-flex justify-content-center">
        <table class="table align-middle mb-0 bg-white">
            <thead class="bg-light">
            <tr>
                <th>게시글 번호</th>
                <th>방문 지역</th>
                <th>글쓴이</th>
                <th>제목</th>
                <th>방문 날짜</th>
                <th>등록 날짜</th>
                <th>조회수</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="board" items="${boards}">
            <tr>
            	<td>
            		<span>${board.articleNo}</span>
            	</td>
            	<td>
<%--            		<p class="fw-normal mb-1">${board.userId}</p>--%>
                    <p class="text-muted mb-0">${board.visitedArea}</p>
            	</td>
            	<td>
            	<div class="d-flex align-items-center">
                        <img
                                src="https://mdbootstrap.com/img/new/avatars/8.jpg"
                                alt=""
                                style="width: 45px; height: 45px"
                                class="rounded-circle"
                        />
                        <div class="ms-3">
                            <p class="fw-bold mb-1">${board.userId}</p>
                            <p class="text-muted mb-0">john.doe@gmail.com</p>
                        </div>
                    </div>
            	</td>
            	<td><a href="${root}/board/${board.articleNo}">${board.title}</a></td>
                <td>
                    <p class="fw-bold mb-1">${board.visitedDate}</p>
                </td>
                <td>
                    <p class="fw-bold mb-1">${board.publishedDate}</p>
                </td>
                <td><p class="fw-bold mb-1">${board.hit}</p></td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="m-5 d-flex justify-content-end" aria-label="Page navigation example">
            <div class="pagination me-3">
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </div>
            <button type="button" class="btn btn-primary" onclick="location.href='${root}/board/create'">글쓰기</button>
        </div>
    </div>

</div>


<%@ include file="../include/footer.jsp"%>
