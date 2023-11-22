package com.trip.search.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AttractionInfo {
    private Integer contentId;
    private String title;
    private String addr1; // KAKAO API의 도로명 주소(road_address_name)와 DB의 addr1이 맵핑됨
    private Double latitude; // KAKAO API의 y좌표(위도)
    private Double longitude; // KAKAO API의 x좌표(경도)
}
