<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file="include/header.jsp" %>
<c:if test="${empty member}">
    <script>
        alert("로그인이 필요합니다.");
        location.href = "${root}/";
    </script>
</c:if>
<c:if test="${not empty member}">
    <div class="container d-flex justify-content-center">
        <div class="row m-5 w-50">
            <div class="modal-header">
                <h4 class="m-3">회원정보수정</h4>
            </div>
            <div class="modal-body">
                <form action="memberUpdate" method="post">
                    <div class="mb-3">
                        <label>아이디</label><input type="text" class="form-control" id="id" name="userId" value="${member.userId}" readonly />
                    </div>
                    <div class="mb-3">
                        <label>현재 비밀번호</label><input type="password" class="form-control" id="pwd" name="userPass"/>
                    </div>
                    <div class="mb-3">
                        <label>새로운 비밀번호</label><input type="password" class="form-control" id="pwd" name="newPass"/>
                    </div>
                    <div class="mb-3">
                        <label>새로운 비밀번호 확인</label><input type="password" class="form-control" id="pwd" name="newPass2"/>
                    </div>
                    <div class="mb-3">
                        <label>이름</label>
                        <input class="form-control" type="text" id="name" name="userName" placeholder="이름 입력" value="${member.userName}"/>
                    </div>
                    <div class="mb-3">
                        <label>이메일</label>
                        <input class="form-control" type="text" id="email" name="userEmail" placeholder="example@dot.com" value="${member.userEmail}"/>
                    </div>
		            <div class="modal-footer">
		                <button type="submit" class="btn btn-primary w-100 mb-3">완료</button>
		            </div>
		        </form>
		        <form action="memberDelete" method="post">
		        	<button type="submit" class="btn btn-danger w-100">회원탈퇴</button>
				</form>
            </div>
        </div>
    </div>
</c:if>
<%@ include file="include/footer.jsp"%>