package com.trip.attraction;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HotplaceDto {
    int placeNo;
    String placeName;
    String visitedDate;
    String detail;
    String createDate;
    String userId;

}
