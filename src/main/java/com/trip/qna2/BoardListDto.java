package com.trip.qna2;

import java.util.List;
import lombok.*;

@Getter
@Setter
public class BoardListDto {

	private List<BoardQnaDto> articles;
	private int currentPage;
	private int totalPageCount;

}
