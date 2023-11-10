//function deleteBoard(event) {
//	event.preventDefault();
//	console.log("root : ${root}");
//	console.log("articleNo : ${board.articleNo}");
//	
//	fetch("${root}/board/${board.articleNo}", {
//		method: "DELETE"
//	})
//	.then((response) => {
//		if(response.status == 200) {
//			window.location.href="${root}/board/";
//		}
//	})
//	.catch(error => {
//		alert("삭제 실패");
//	});
//}

//document.getElementById("delLink").addEventListener("click", function(event) {
//	event.preventDefault();
//	console.log("root : ${root}");
//	console.log("articleNo : ${board.articleNo}");
//	
//	fetch("${root}/board/${board.articleNo}", {
//		method: "DELETE"
//	})
//	.then((response) => {
//		if(response.status == 200) {
//			window.location.href="${root}/board/";
//		}
//	})
//	.catch(error => {
//		alert("삭제 실패");
//	});
//});