package com.travelmaker.dto.enumeration;
/**
 * <p>각 채팅에 해당하는 Type을 나타내는 Enum
 * <p>SYSTEM, MSG의 두가지 속성이 존재함
 * @author 김선일
 *
 */
public enum Type {
	/**
	 * 시스템 메세지. 입, 퇴장 알림 메세지 등.
	 */
	SYSTEM,
	/**
	 * 사용자가 송신한 메세지
	 */
	MSG
	/**
	 * 동행 진행중
	 */
}