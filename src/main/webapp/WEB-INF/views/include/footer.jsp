<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!-- <html lang="ko">
<head>
    <meta charset="UTF-8" />
</head> -->
<!-- TODO -->
<script>
	document.getElementById("delLink").addEventListener("click", function(event) {
		event.preventDefault();
		console.log("root : ${root}");
		console.log("articleNo : ${board.articleNo}");
		
		fetch("${root}/board/${board.articleNo}", {
			method: "DELETE"
		})
			.then((response) => {
				if(response.status == 200) {
					window.location.href="${root}/board/";
				}
			})
			.catch(error => {
				alert("삭제 실패");
			});
	});

	function ajaxDeleteHotplace(no) {
		console.log("핫플레이스 삭제 요청");
		fetch("${root}/attraction/" + no, {
			method: "DELETE"
		})
			.then((response) => {
				if(response.status == 200) {
					window.location.href="${root}/attraction/";
				}
			})
			.catch(error => {
				alert("삭제 실패");
			});
	}

    function openPopup(button, no) {

        document.getElementById('popup').style.display='block';
        // 비동기 데이터 조회
        fetch("${root}/attraction/"+ no)
            .then((response) => response.json())
            .then(data => {
                // var popupContent = document.getElementById('popup-content');
                // var element = document.createElement('p');
                // element.textContent = data.detail;
                // popupContent.appendChild(element);
                var popContent = document.getElementById("popup-detail");
                popContent.innerText = data.detail;
            }).catch(error => console.error("데이터 가져오기 오류 ", error));

        // button.onclick = null;
    }

    function closePopup(button) {
        document.getElementById('popup').style.display='none';
    }
</script>
</body>

<footer class="navbar navbar-expand-sm navbar-light bg-light fixed-bottom d-flex justify-content-end">
    <div>
        <ul class="navbar-nav">
            <li><a href="#" class="nav-link text-secondary">카페소개</a></li>
            <li><a href="#" class="nav-link text-secondary">개인정보처리방침</a></li>
            <li><a href="#" class="nav-link text-secondary">이용약관</a></li>
            <li><a href="#" class="nav-link text-secondary">오시는길</a></li>
            <li><label class="nav-link text-secondary">&copy; Enjoy Trip</label></li>
        </ul>
    </div>
</footer>
<!-- TODO future work -->
</html>