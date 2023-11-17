<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp" %>

<body>
    <form action="register" method="post">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h4>회원가입</h4>
            </div>
            <div class="modal-body">
                    <div class="mb-3">
                        <label>아이디</label><input type="text" class="form-control" id="userId" name="userId" />
                    </div>
                    <div class="mb-3">
                        <label>비밀번호</label><input type="password" class="form-control" id="password" name="userPass" />
                    </div>
                    <div class="mb-3">
                        <label>이름</label>
                        <input class="form-control" type="text" id="name" name="userName" placeholder="이름 입력" />
                    </div>
                    <div class="mb-3">
                        <label>이메일</label>
                        <input class="form-control" type="text" id="email" name="userEmail" placeholder="example@dot.com" />
                    </div>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary w-100">회원가입</button>
            </div>
        </div>
    </div>
    </form>
</body>

<%@ include file="include/footer.jsp"%>