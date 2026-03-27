package kr.or.ddit.servlet03.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.RequiredArgsConstructor;

//@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class EchoRequest implements Serializable {

	private final String message;
	private  final String language;
	private   String dummy;
}
