<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="container mt-5">
    <div class="row d-flex justify-content-evenly">
        <div
                class="card shadow-1-strong col-3"
                style="background-image: url(assets/img/city/서울.jpg); width: 17em; height: 20rem; border-color: white;"
        >
            <div class="card-body text-white">
                <h5 class="card-title fw-bold fs-2 p-3">서울</h5>
                <p class="card-text fw-bold fs-4 p-3">
                    350여개의 <br/>여행 관광지
                </p>
                <a href="${root}/attraction/list?area=서울" class="btn btn-light mt-4 fw-bolder">둘러보기</a>
            </div>
        </div>
        <div
                class="card shadow-1-strong col-3"
                style="background-image: url(assets/img/city/부산.jpg); width: 17em; height: 20rem; border-color: white;"
        >
            <div class="card-body text-white">
                <h5 class="card-title fw-bold fs-2 p-3">부산</h5>
                <p class="card-text fw-bold fs-4 p-3">
                    230여개의 <br/>여행 관광지
                </p>
                <a href="${root}/attraction/list?area=부산" class="btn btn-light mt-4 fw-bolder">둘러보기</a>
            </div>
        </div>
        <div
                class="card shadow-1-strong col-3"
                style="background-image: url(assets/img/city/광주.jpg); width: 17em; height: 20rem; border-color: white;"
        >
            <div class="card-body text-white">
                <h5 class="card-title fw-bold fs-2 p-3">광주</h5>
                <p class="card-text fw-bold fs-4 p-3">
                    170여개의 <br/>여행 관광지
                </p>
                <a href="${root}/attraction/list?area=광주" class="btn btn-light mt-4 fw-bolder">둘러보기</a>
            </div>
        </div>
        <div
                class="card shadow-1-strong col-3"
                style="background-image: url(assets/img/city/제주도.jpg); width: 17em; height: 20rem; border-color: white;"
        >
            <div class="card-body text-white">
                <h5 class="card-title fw-bold fs-2 p-3">제주도</h5>
                <p class="card-text fw-bold fs-4 p-3">
                    200여개의 <br/>여행 관광지
                </p>
                <a href="${root}/attraction/list?area=제주도" class="btn btn-light mt-4 fw-bolder">둘러보기</a>
            </div>
        </div>

    </div>
    <div class="row mt-5">
        <div class="col-md-6 ">
            <h4>[ 인기글 ]</h4>

            <table class="table table-hover">
                <thead>
                <tr class="text-center">
                    <th>제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                </tr>
                </thead>
                <tbody id="top-n-body">
                <script>
                    fetch("${root}/board/top/5")
                        .then(response => response.json())
                        .then((data) => makeList(data, "top-n-body"))
                        .catch(error => console.log("error", error));

                    function makeList(boards, id) {
                        let tbody = '';
                        console.log("data : " + boards);
                        const root = "${root}";
                        boards.forEach(function(board) {
                            console.log(board);
                            const url = root + "/board/";
                            console.log(url);
                            tbody += `
                                <tr>
                                    <td class="text-center">
                                        <a href="\${url}/\${board.articleNo}">\${board.title}</a>
                                    </td>
                                    <td class="text-center">\${board.userId}</td>
                                    <td class="text-center">\${board.hit}</td>
                                </tr>
                            `;
                       });
                        document.getElementById(id).innerHTML = tbody;
                    }
                </script>
                </tbody>
            </table>
        </div>
        <div class="col-md-6">
            <h4><a href="${root}/board/">[ 최신글 ]</a></h4>
            <table class="table table-hover">
                <thead>
                <tr class="text-center">
                    <th>제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                </tr>
                </thead>
                <tbody id="recent-n-body">
                <script>
                    fetch("${root}/board/recent/5")
                        .then(response => response.json())
                        .then((data) => makeList(data, "recent-n-body"))
                        .catch(error => console.log("error", error));
                </script>
                <%--<tr>
                    <td>히주니에요</td>
                    <td class="text-center">전희준</td>
                    <td class="text-center">11</td>
                </tr>
                <tr>
                    <td>성난키보드!!! <span class="badge bg-danger">20</span></td>
                    <td class="text-center">정원영</td>
                    <td class="text-center">12</td>
                </tr>
                <tr>
                    <td>왜안될까???</td>
                    <td class="text-center">정은영</td>
                    <td class="text-center">7</td>
                </tr>--%>
                </tbody>
            </table>
        </div>
    </div>
    <!-- 인기글 & 최신글 end -->
</div>
