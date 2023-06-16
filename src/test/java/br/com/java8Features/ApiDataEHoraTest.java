package br.com.java8Features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApiDataEHoraTest {
/* 
	- LocalDate - representa uma data: 03/05/2023
	- LocalTime - representa uma hora: 16:47:22
	- LocalDateTime - representa uma data com hora: 03/05/2023 16:47:22

	- Instant - representa um instante/momento na linha do tempo
	- ZonedDateTime - representa um LocalDateTime com TimeZone

	- Contrutor - não existe construtor
	- Now
	- Of
	- Month - inicializar um mês
	- Manipulaçao
		* plus e minus
	- Valores Inválidos - iniciar uma data com valor inválido

	*Period*
	- valores baseados em datas
	- Of
	- Between
	- Plus/Minus
	- LocalTime - não consegue adicionar Period no LocalTime que contem hora

	*Duration*
	- valores baseados em tempo
	- Of
	- Between
	- Plus/Minus
	- LocalDate - não consegue adicionar Duration no LocalTime que contem hora

	*DateTimeFormatter*
	- formartar a exibição do valor
	- DateTimeFormatter.ISO
	- FormatStyle
	- Pattern
	- Parse

*/	

/* 
	- LocalDate - representa uma data: 03/05/2023
	- LocalTime - representa uma hora: 16:47:22
	- LocalDateTime - representa uma data com hora: 03/05/2023 16:47:22

	- Instant - representa um instante/momento na linha do tempo
	- ZonedDateTime - representa um LocalDateTime com TimeZone
 */
	@Test
	void localDate_data_quandoSucesso() {

		LocalDate localDateNow = LocalDate.now();
		System.out.println(localDateNow);
		
		LocalDate localDateFixo = LocalDate.of(2023, Month.MAY, 3);

		assertEquals(Boolean.TRUE, localDateFixo.isBefore(localDateNow));

	}
	
	@Test
	void localTime_hora_quandoSucesso() {

		LocalTime localTimeNow = LocalTime.now();

		System.out.println(localTimeNow);
		LocalTime localTimeFixo = LocalTime.of(16,47, 22,896);
		
		assertEquals(Boolean.TRUE, localTimeFixo.isBefore(localTimeNow));

	}
	
	@Test
	void localDateTime_hora_quandoSucesso() {
		
		LocalDateTime localDateTimeNow = LocalDateTime.now();
		System.out.println(localDateTimeNow);

		LocalDateTime localDateTimeFixo = LocalDateTime.of(2023, Month.MAY, 3, 16, 47, 22, 896);
		
		assertEquals(Boolean.TRUE, localDateTimeFixo.isBefore(localDateTimeNow));

	}

	@Test
	void instant_hora_quandoSucesso() {
		
		Instant instantNow = Instant.now();
		System.out.println(instantNow);

		Instant instantFixo = Instant.ofEpochMilli(989879876546L);
		System.out.println(instantFixo);
		
		assertEquals(Boolean.TRUE, instantFixo.isBefore(instantNow));

	}

	@Test
	void zonedDateTime_hora_quandoSucesso() {
		
		ZonedDateTime zonedDateTimeNow = ZonedDateTime.now();
		System.out.println(zonedDateTimeNow);

		ZonedDateTime zonedDateTimeFixo = ZonedDateTime.of(2023, 5, 3, 16, 47, 22, 896, ZoneId.of("America/Sao_Paulo"));

		assertEquals(Boolean.TRUE, zonedDateTimeFixo.isBefore(zonedDateTimeNow));

	}

	@Test
	void localDate_manipulacao_quandoSucesso() {

		LocalDate localDateNow = LocalDate.now();
		System.out.println(localDateNow);
		
		LocalDate localDateFixo = LocalDate.of(2023, Month.MAY, 3);
		localDateFixo = localDateFixo.plusYears(100).plusMonths(50).minusDays(20);

		assertEquals(Boolean.FALSE, localDateFixo.isBefore(localDateNow));

	}

	@Test
	void localDate_retornaUmaExcecaoCasoUseUmParametroErrado_quandoErro() {

		LocalDate localDateNow = LocalDate.now();
		System.out.println(localDateNow);
		
		assertThrows(DateTimeException.class, () -> instaciarLocalDate().orElseThrow(() -> new DateTimeException("")) );

	}

	public Optional<LocalDate> instaciarLocalDate(){
		return Optional.of(LocalDate.of(2023, 13, 3));
	}

	// Period
	
	@Test
	void period_of_quandoSucesso() {

		Period period = Period.of(1, 1, 1);

		System.out.println(period);

		assertEquals("P1Y1M1D", period.toString());

	}
		
	@Test
	void period_between_quandoSucesso() {

		LocalDate localDateUm = LocalDate.of(2023, Month.MAY, 3);
		LocalDate localDateDois = LocalDate.of(2024, Month.JUNE, 4);

		System.out.println(Period.between(localDateUm, localDateDois));

		assertEquals(Period.of(1,1,1), Period.between(localDateUm, localDateDois));

	}
	
	@Test 
	void period_plus_minus_quandoSucesso() {

		Period period = Period.of(1, 1, 1);

		period = period.plusYears(2).plusMonths(1).plusDays(1).minusYears(1);

		System.out.println(period);

		assertEquals(Period.of(2,2,2), period);

	}

	// Duration
	
	@Test
	void duration_of_quandoSucesso() {

		Duration duration = Duration.ofDays(1);

		System.out.println(duration);

		assertEquals("PT24H", duration.toString());

	}
		
	@Test
	void duration_between_quandoSucesso() {

		LocalTime LocalTimeUm = LocalTime.of(1, 1, 1);
		LocalTime LocalTimeDois = LocalTime.of(2, 2, 2);

		System.out.println(Duration.between(LocalTimeUm, LocalTimeDois));

		assertEquals("PT1H1M1S", Duration.between(LocalTimeUm, LocalTimeDois).toString());

	}
	
	@Test 
	void duration_plus_minus_quandoSucesso() {

		Duration duration = Duration.ofHours(10);

		duration = duration.plusDays(1).minusHours(24).plusHours(1);

		System.out.println(duration);

		assertEquals(Duration.ofHours(11), duration);

	}

	// DateTimeFormatter
	
	@Test
	void dateTimeFormatter_formatarDataIso_quandoSucesso() {

		DateTimeFormatter formatar = DateTimeFormatter.ISO_DATE;
		
		LocalDateTime localDateTime = LocalDateTime.of(2023, Month.MAY, 3, 16, 47, 22, 896);

		String formatado = formatar.format(localDateTime);

		System.out.println(formatado);
		
		assertEquals("2023-05-03", formatado);

	}
	
	@Test
	void dateTimeFormatter_formatarHoraIso_quandoSucesso() {

		DateTimeFormatter formatar = DateTimeFormatter.ISO_TIME;
		
		LocalDateTime localDateTime = LocalDateTime.of(2023, Month.MAY, 3, 16, 47, 22, 896);

		String formatado = formatar.format(localDateTime);

		System.out.println(formatado);
		
		assertEquals("16:47:22.000000896", formatado);

	}

	@Test
	void dateTimeFormatter_formatarDataStyle_quandoSucesso() {

		DateTimeFormatter formatar = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		
		LocalDateTime localDateTime = LocalDateTime.of(2023, Month.MAY, 3, 16, 47, 22, 896);

		String formatado = formatar.format(localDateTime);

		System.out.println(formatado);
		
		assertEquals("03/05/2023", formatado);

	}
	
	@Test
	void dateTimeFormatter_formatarHoraStyle_quandoSucesso() {

		DateTimeFormatter formatar = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
		
		LocalDateTime localDateTime = LocalDateTime.of(2023, Month.MAY, 3, 16, 47, 22, 896);

		String formatado = formatar.format(localDateTime);

		System.out.println(formatado);
		
		assertEquals("16:47:22", formatado);

	}

	@Test
	void dateTimeFormatter_formatarDataHoraStyle_quandoSucesso() {

		DateTimeFormatter formatar = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		
		LocalDateTime localDateTime = LocalDateTime.of(2023, Month.MAY, 3, 16, 47, 22, 896);

		String formatado = formatar.format(localDateTime);

		System.out.println(formatado);
		
		assertEquals("03/05/2023 16:47:22", formatado);

	}

	@Test
	void dateTimeFormatter_patternData_quandoSucesso() {

		DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		LocalDateTime localDateTime = LocalDateTime.of(2023, Month.MAY, 3, 16, 47, 22, 896);

		String formatado = formatar.format(localDateTime);

		System.out.println(formatado);
		
		assertEquals("03-05-2023", formatado);

	}

	@Test
	void dateTimeFormatter_patternDataHora_quandoSucesso() {

		DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		
		LocalDateTime localDateTime = LocalDateTime.of(2023, Month.MAY, 3, 16, 47, 22, 896);

		String formatado = formatar.format(localDateTime);

		System.out.println(formatado);
		
		assertEquals("03-05-2023 16:47:22", formatado);

	}

	@Test
	void dateTimeFormatter_parse_quandoSucesso() {

		DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		
		TemporalAccessor temporalAccessor = formatar.parse("03-05-2023 16:47:22");
		
		LocalDateTime localDateTime = LocalDateTime.from(temporalAccessor);

		System.out.println(localDateTime);
		
		assertEquals(LocalDateTime.of(2023, Month.MAY, 3, 16, 47, 22), localDateTime);

	}

}
