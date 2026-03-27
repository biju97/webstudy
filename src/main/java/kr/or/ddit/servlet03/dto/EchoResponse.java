package kr.or.ddit.servlet03.dto;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data ///-> 빌더 패턴 적용
@Builder
public class EchoResponse implements Serializable{
	private String original;
	private String echoed;
	private int length;
	private String receivedAt;
	
}
