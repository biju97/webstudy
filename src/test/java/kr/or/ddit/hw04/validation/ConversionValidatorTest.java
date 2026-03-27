package kr.or.ddit.hw04.validation;

import org.junit.jupiter.api.Test;

import kr.or.ddit.hw04.dto.ConversionRequest;

class ConversionValidatorTest {

	@Test
	void testValidateInvalid() {

		try {
			ConversionValidator.validate("abc","KM","C");
		}catch(IllegalArgumentException e) {
			System.err.println("400에러,"+e.getMessage());
		}
	}
	
	
	@Test
	void testValidateValid() {

		try {
			ConversionRequest reqDto =ConversionValidator.validate("1234","KM","MILE");
			System.out.println(reqDto);
		}catch(IllegalArgumentException e) {
			System.err.println("400에러,"+e.getMessage());
		}
	}
}
