package kr.or.ddit.hw05.validate;

import java.util.Currency;

import kr.or.ddit.hw05.dto.ExchangeRequest;

public class ExchangeValidator {
	/**
	 * @param params
	 * @return
	 * @throws RuntimeException
	 */
	public ExchangeRequest validate(String ...params) throws RuntimeException{
		if(params==null ||params.length<3) {
			throw new IllegalArgumentException("환전에는 3개의 파라미터가 필요");
		}
		double amount=-1;
		try {
			amount=Double.parseDouble(params[0]);
		}catch(RuntimeException e) {
			throw new IllegalArgumentException("amount 금액은 숫자로만");		//예외전환처리
		}
		try {
			
			Currency from=Currency.getInstance(params[1]);
			Currency to=Currency.getInstance(params[2]);
			
			return new ExchangeRequest(amount, from, to);
		}catch(RuntimeException e) {
			throw new IllegalArgumentException("amount 금액은 숫자로만");		//예외전환처리
		}
	
	}
}
