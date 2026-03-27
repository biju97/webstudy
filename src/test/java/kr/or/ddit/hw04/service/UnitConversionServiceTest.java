package kr.or.ddit.hw04.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.or.ddit.hw04.domain.Unit;
import kr.or.ddit.hw04.dto.ConversionRequest;
import kr.or.ddit.hw04.dto.ConversionResponse;

class UnitConversionServiceTest {

    private UnitConversionService service;
    private final double DELTA = 0.0001; // 부동 소수점 오차 허용 범위

    @BeforeEach
    void setUp() {
        service = new UnitConversionService();
    }
    
    @Test
    void testSameTypeInvalid() {
    	 ConversionRequest req = new ConversionRequest(10, Unit.KM, Unit.FT);
    	service.convert(req, Locale.KOREAN);
    }

    @Test
    @DisplayName("1. km -> mile 변환 결과가 올바른가 (10 km = 6.2137 mile)")
    void testKmToMile() {
        ConversionRequest req = new ConversionRequest(10, Unit.KM, Unit.MILE);
        ConversionResponse res = service.convert(req, Locale.US);

        // 결과값 검증 (0.621371 비율 적용 시 약 6.2137)
        assertEquals(6.21371, res.getResult(), DELTA);
        assertEquals("6.214", res.getFormattedResult()); // US 로케일 포맷팅
    }

    @Test
    @DisplayName("2. 역방향 변환 (mile -> km)도 올바른가")
    void testMileToKm() {
        // 6.21371 마일을 다시 km로 변환하면 10이 나와야 함
        ConversionRequest req = new ConversionRequest(6.21371, Unit.MILE, Unit.KM);
        ConversionResponse res = service.convert(req, Locale.KOREA);

        assertEquals(10.0, res.getResult(), DELTA);
        assertEquals("10", res.getFormattedResult());
    }

    @Test
    @DisplayName("3. 경계값: 0 입력 (0 km -> 0 mile)")
    void testZeroValue() {
        ConversionRequest req = new ConversionRequest(0, Unit.KM, Unit.MILE);
        ConversionResponse res = service.convert(req, Locale.US);

        assertEquals(0.0, res.getResult(), DELTA);
        assertEquals("0", res.getFormattedResult());
    }

    @Test
    @DisplayName("4. 경계값: 음수 입력 (-10 C -> 14 F)")
    void testNegativeTemperature() {
        // 공식: -10 * 9/5 + 32 = 14
        ConversionRequest req = new ConversionRequest(-10, Unit.C, Unit.F);
        ConversionResponse res = service.convert(req, Locale.US);

        assertEquals(14.0, res.getResult(), DELTA);
        assertEquals("14", res.getFormattedResult());
    }

    @Test
    @DisplayName("5. 경계값: 매우 큰 수 (999,999,999 km)")
    void testLargeValue() {
        double largeValue = 999999999.0;
        ConversionRequest req = new ConversionRequest(largeValue, Unit.KM, Unit.MILE);
        ConversionResponse res = service.convert(req, Locale.US);

        double expected = largeValue * 0.621371;
        assertEquals(expected, res.getResult(), DELTA);
        // 천단위 구분자(,) 포함 여부 확인
        assertTrue(res.getFormattedResult().contains(","));
    }

    @Test
    @DisplayName("6. 경계값: 매우 작은 소수 (0.0001 m -> ? ft)")
    void testSmallDecimal() {
        // 공식: 0.0001 * 3.28084 = 0.000328
        ConversionRequest req = new ConversionRequest(0.0001, Unit.M, Unit.FT);
        ConversionResponse res = service.convert(req, Locale.US);

        assertEquals(0.000328, res.getResult(), DELTA);
    }
}