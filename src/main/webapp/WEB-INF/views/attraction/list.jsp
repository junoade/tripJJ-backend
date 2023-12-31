<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Enjoy Trip</title>
    <link rel="shortcut icon" href="./assets/img/favicon.ico" />
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
            crossorigin="anonymous"
    />
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"
    />
    <script src="${root}/assets/js/auth.js"></script>
</head>

<body>
<%@ include file="../include/header.jsp" %>
<!-- 중앙 content start -->
<div class="container mt-5">
    <div class="row">

        <!-- 중앙 left content end -->
        <!-- 중앙 center content end -->
        <div class="col-md-6">
            <div id="localAreaInfo" class="alert alert-primary mt-3 text-center fw-bold" role="alert">
            </div>
            <!-- 관광지 검색 start -->
            <form class="d-flex my-3" onsubmit="return false;" role="search">
                <select id="search-area" class="form-select me-2">
                    <option value="0" selected>검색 할 지역 선택</option>
                </select>
                <select id="search-content-id" class="form-select me-2">
                    <option value="0" selected>관광지 유형</option>
                    <option value="12">관광지</option>
                    <option value="14">문화시설</option>
                    <option value="15">축제공연행사</option>
                    <option value="25">여행코스</option>
                    <option value="28">레포츠</option>
                    <option value="32">숙박</option>
                    <option value="38">쇼핑</option>
                    <option value="39">음식점</option>
                </select>
                <input
                        id="search-keyword"
                        class="form-control me-2"
                        type="search"
                        placeholder="검색어"
                        aria-label="검색어"
                />
                <button id="btn-search" class="btn btn-outline-success" type="button">검색</button>
            </form>
            <!-- kakao map start -->
            <div id="map" class="mt-3" style="width: 100%; height: 400px"></div>
            <!-- kakao map end -->

            <!-- 관광지 검색 end -->
        </div>
        <div class="row col-6">
            <table class="table table-striped" style="display: none">
                <thead>
                <tr>
                    <th>대표이미지</th>
                    <th>관광지명</th>
                    <th>주소</th>
                    <th>위도</th>
                    <th>경도</th>
                </tr>
                </thead>
                <tbody id="trip-list"></tbody>
            </table>
        </div>
    </div>
    <!-- 중앙 content end -->
</div>
<%@ include file="../include/footer.jsp"%>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"
></script>
<script
        type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=40b23bfe4b90f18883b428591d313e05&libraries=services,clusterer,drawing"
></script>
<script>
    const serviceKey = "9VoECIoZUsw/ZqVw5mHTphOZt6lezsg08OeiQLyYMlvltAOZPj2aaJXMhvPsguElvdiSSsO4PT5IrfsI35YvNg==";
    const kakaoKey = "40b23bfe4b90f18883b428591d313e05";
    // 특정 지역에 맞게 수정
    // var user_area = getParameterByName("area");
    var user_area = '${area}';
    document.querySelector("#localAreaInfo").innerText = `\${user_area} 관광지 정보`;

    // 쿼리파라미터 파싱
    /* function getParameterByName(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
        return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    } */

    // ~~index page 로딩 후 전국의 시도 설정.~~
    // 특정 지역의 정보 선택
    let areaUrl =
        "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
        serviceKey +
        "&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";

    // fetch(areaUrl, { method: "GET" }).then(function (response) { return response.json() }).then(function (data) { makeOption(data); });
    fetch(areaUrl, { method: "GET" })
        .then((response) => response.json())
        .then((data) => makeOption(data));

    function makeOption(data) {
        let areas = data.response.body.items.item;
        let sel = document.getElementById("search-area");
        areas.forEach((area) => {
            let opt = document.createElement("option");
            opt.setAttribute("value", area.code);
            if(area.name == user_area) {
                opt.selected = true;
            }
            opt.appendChild(document.createTextNode(area.name));
            sel.appendChild(opt);
        });
    }

    // 검색 버튼을 누르면..
    // 지역, 유형, 검색어 얻기.
    // 위 데이터를 가지고 공공데이터에 요청.
    // 받은 데이터를 이용하여 화면 구성.
    document.getElementById("btn-search").addEventListener("click", () => {
        let baseUrl = `https://apis.data.go.kr/B551011/KorService1/`;

        let queryString = `serviceKey=\${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;
        let areaCode = document.getElementById("search-area").value;
        let contentTypeId = document.getElementById("search-content-id").value;
        let keyword = document.getElementById("search-keyword").value;

        if (parseInt(areaCode)) queryString += `&areaCode=\${areaCode}`;
        if (parseInt(contentTypeId)) queryString += `&contentTypeId=\${contentTypeId}`;
        // if (!keyword) {
        //   alert("검색어 입력 필수!!!");
        //   return;
        // } else searchUrl += `&keyword=${keyword}`;
        let service = ``;
        if (keyword) {
            service = `searchKeyword1`;
            queryString += `&keyword=\${keyword}`;
        } else {
            service = `areaBasedList1`;
        }
        let searchUrl = baseUrl + service + "?" + queryString;

        fetch(searchUrl)
            .then((response) => response.json())
            .then((data) => makeList(data));
    });

    var positions; // marker 배열.
    function makeList(data) {
        document.querySelector("table").setAttribute("style", "display: ;");
        let trips = data.response.body.items.item;
        let tripList = ``;
        positions = [];
        trips.forEach((area) => {
            tripList += `
            <tr onclick="moveCenter(\${area.mapy}, \${area.mapx});">
              <td><img src="\${area.firstimage}" width="100px"></td>
              <td>\${area.title}</td>
              <td>\${area.addr1} \${area.addr2}</td>
              <td>\${area.mapy}</td>
              <td>\${area.mapx}</td>
            </tr>
          `;

            let markerInfo = {
                title: area.title,
                latlng: new kakao.maps.LatLng(area.mapy, area.mapx),
            };
            positions.push(markerInfo);
        });
        document.getElementById("trip-list").innerHTML = tripList;
        displayMarker();
    }

    // 카카오지도
    var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
            level: 5, // 지도의 확대 레벨
        };

    // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
    var map = new kakao.maps.Map(mapContainer, mapOption);

    function displayMarker() {
        // 마커 이미지의 이미지 주소입니다
        var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

        for (var i = 0; i < positions.length; i++) {
            // 마커 이미지의 이미지 크기 입니다
            var imageSize = new kakao.maps.Size(24, 35);

            // 마커 이미지를 생성합니다
            var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

            // 마커를 생성합니다
            var marker = new kakao.maps.Marker({
                map: map, // 마커를 표시할 지도
                position: positions[i].latlng, // 마커를 표시할 위치
                title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                image: markerImage, // 마커 이미지
            });
        }

        // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
        map.setCenter(positions[0].latlng);
    }

    function moveCenter(lat, lng) {
        map.setCenter(new kakao.maps.LatLng(lat, lng));
    }
</script>
</body>
</html>
