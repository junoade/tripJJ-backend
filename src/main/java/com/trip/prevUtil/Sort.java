package com.trip.prevUtil;

import java.util.Arrays;
import java.util.List;

import com.trip.board.BoardDto;

public class Sort {
	public static List<BoardDto> sort(List<BoardDto> list, String sortBy) throws Exception {
		List<BoardDto> result;
		
		switch(sortBy) {
			case "articleNo":
				result = sortByArticleNo(list);
				break;
			case "userId":
				result = sortByUserId(list);
				break;
			case "hit":
				result = sortByHit(list);
				break;
			default: {
				throw new Exception("지원되지 않는 정렬 방식입니다.");
			}
		}
		
		return result;
	}
	
	/**
	 * BoardDto의 userId를 기준으로 정렬 구현
	 * @param list
	 */
	private static List<BoardDto> sortByArticleNo(List<BoardDto> list) {
		BoardDto[] a = list.toArray(new BoardDto[0]);
		quicksort(a, 0, a.length - 1);
		return Arrays.asList(a);
	}
	
	private static List<BoardDto> sortByUserId(List<BoardDto> list) {
		BoardDto[] a = list.toArray(new BoardDto[0]);
		quicksort2(a, 0, a.length - 1);
		return Arrays.asList(a);
	}
	
	private static List<BoardDto> sortByHit(List<BoardDto> list) {
		BoardDto[] a = list.toArray(new BoardDto[0]);
		quicksort3(a, 0, a.length - 1);
		return Arrays.asList(a);
	}
	
	private static void quicksort(BoardDto[] a, int from, int to) {

		if(from >= to) {
			return;
		}
		
		int pivot = partition(a, from, to);
		
		quicksort(a, from, pivot - 1);
		quicksort(a, pivot + 1, to);
	}
	
	private static void quicksort2(BoardDto[] a, int from, int to) {

		if(from >= to) {
			return;
		}
		
		int pivot = partition2(a, from, to);
		
		quicksort2(a, from, pivot - 1);
		quicksort2(a, pivot + 1, to);
	}
	
	private static void quicksort3(BoardDto[] a, int from, int to) {

		if(from >= to) {
			return;
		}
		
		int pivot = partition3(a, from, to);
		
		quicksort3(a, from, pivot - 1);
		quicksort3(a, pivot + 1, to);
	}
	
	/**
	 * 피봇을 끝으로 잡는 퀵소트
	 * 오름차순 정렬 수행
	 * @param a
	 * @param from
	 * @param to
	 * @return
	 */
	private static int partition(BoardDto[] a, int from, int to) {
	
		int pivot = a[to].getArticleNo();
		int counter = from;
		
		// partition 수행
		for(int i = from; i < to; i++) {
			if(a[i].getArticleNo() < pivot) {
				swap(a, i, counter);
				counter++;
			}
		}
		
		// partition 이후 swap
		swap(a, to, counter);
		return counter;
	}
	
	private static int partition2(BoardDto[] a, int from, int to) {
		
		String pivot = a[to].getUserId();
		int counter = from;
		
		// partition 수행
		for(int i = from; i < to; i++) {
			if(a[i].getUserId().compareTo(pivot) < 1) {
				swap(a, i, counter);
				counter++;
			}
		}
		
		// partition 이후 swap
		swap(a, to, counter);
		return counter;
	}
	
	private static int partition3(BoardDto[] a, int from, int to) {
		
		int pivot = a[to].getHit();
		int counter = from;
		
		// partition 수행
		for(int i = from; i < to; i++) {
			if(a[i].getHit() > pivot) {
				swap(a, i, counter);
				counter++;
			}
		}
		
		// partition 이후 swap
		swap(a, to, counter);
		return counter;
	}
	
	private static void swap(BoardDto[] a, int i, int j) {
		BoardDto temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
