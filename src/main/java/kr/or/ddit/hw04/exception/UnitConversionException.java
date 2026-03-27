package kr.or.ddit.hw04.exception;

/*
 *  단위 변환 과정에서 발생하는 모든 예외적인 상황에 대한 표현 
 */

public class UnitConversionException extends IllegalArgumentException {

	public UnitConversionException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UnitConversionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UnitConversionException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	public UnitConversionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
 
}
