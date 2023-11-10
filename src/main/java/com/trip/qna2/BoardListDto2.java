package com.trip.qna2;

import java.util.List;
import lombok.*;

@Getter
@Setter
public class BoardListDto2 {

	private List<BoardQnaDto2> articles;
	private int currentPage;
	private int totalPageCount;

}
