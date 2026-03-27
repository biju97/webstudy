package kr.or.ddit.hw01;

import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class LocaleTimeZoneTest {
	
	@Test
	void test4() {
		Set<String>zoneSet=ZoneId.getAvailableZoneIds();
		Map<String,String>zoneMap= new HashMap<>();
		for(String singleZone:zoneSet) {
			System.out.println(singleZone);
			ZoneId tz =ZoneId.of(singleZone);
			String name=tz.getDisplayName(TextStyle.FULL, Locale.CANADA_FRENCH);
		System.out.printf("%s :%s\n",singleZone,name);
		zoneMap.put(singleZone,name);
		}
	}
	
	@Test
	void test3() {
		
		//이코드를 java8 이후의 stream api 의 파이프 라인 구조로 변경해봐
		Locale[] locales = Locale.getAvailableLocales();
		Map<String,String>localeMap= new HashMap<>();
		System.out.println(locales.length);
		for (Locale locale : locales) {
			String lanugaeTag=locale.toLanguageTag();
			String name=locale.getDisplayName(locale);
			if(name.isBlank()) continue;
			System.out.printf("%s: %s\n",lanugaeTag,name);
			localeMap.put(lanugaeTag,name);
		}
	}
	

	//@Test
	void test1() {
		System.out.println("test case1");
	}
	
	//@Test
	void test2() {
		System.out.println("test case2");
	}

}
