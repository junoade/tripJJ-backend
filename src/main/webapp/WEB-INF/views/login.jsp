<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp" %>
<form action="login" method="post">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">로그인</h4>
            </div>
            <div class="modal-body">
				<div class="mb-3">
                    <label>아이디</label><input type="text" class="form-control" id="id" name="userId" />
                </div>
                <div class="mb-3">
                    <label>비밀번호</label><input type="password" class="form-control" id="password" name="userPass" />
                </div>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary w-100">로그인</button>
            </div>
        </div>
    </div>
</form>
<script>

    let msg = "${msg}";
    if(msg){
        alert(msg);
    }
    
</script>