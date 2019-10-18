package com.travelmaker.dto.enumeration;
/**
 * <p>현재 동행 게시물의 상태를 나타내는 enum
 * <p>APPLY, CANCEL, START, END 네가지 속성이 존재함
 * @author 김선일
 *
 */
public enum Status {
	/**
	 * 동행 신청중
	 */
	APPLY,
	/**
	 * 동행 취소됨
	 */
	CANCEL,
	/**
	 * 동행 진행중
	 */
	START,
	/**
	 * 동행 종료됨
	 */
	END,
	/**
	 * 게시물 삭제 상태
	 */
	Y,
	/**
	 * 게시물 게시 상태
	 */
	N
	
}
