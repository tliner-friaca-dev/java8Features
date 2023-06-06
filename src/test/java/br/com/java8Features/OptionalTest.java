package br.com.java8Features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import br.com.java8Features.model.Boleto;

class OptionalTest {
/* 
	*Criando um Optional
	- of
	- empty
	- ofNullable
 */	
/* 
	*Usando o Optional
	- isPresent
	- get
	- ifPresent
	- orElse
	- orElseGet
	- orElseThrow
 */

	/**
	 *
	 */
	private static final int DEZ = 10;
	private static final String CINQUENTA = "50";

	@Test
	void of_criandoUmOprional_quandoSucesso() {

		Boleto boleto = new Boleto.BoletoBuilder()
									.comValor(new BigDecimal(CINQUENTA))
									.construir();

		Optional<String> valorDoBoleto = Optional.of(boleto.getValor().toString());

		assertEquals(CINQUENTA, valorDoBoleto.get());

	}

	@Test
	void empty_criandoUmOprional_quandoSucesso() {

		Boleto boleto = new Boleto.BoletoBuilder()
									.construir();

		Optional<String> valorDoBoleto = boleto.getValor() == null ? Optional.empty() : Optional.of(boleto.getValor().toString());

		assertEquals(Optional.empty(), valorDoBoleto);

	}

	@Test
	void ofNullable_criandoUmOprional_quandoSucesso() {

		Boleto boleto = new Boleto.BoletoBuilder()
									.construir();

		Optional<String> valorDoBoleto = boleto.getValor() == null ? Optional.ofNullable(null) : Optional.of(boleto.getValor().toString());

		assertEquals(Optional.empty(), valorDoBoleto);

	}

 	@Test
	void isPresent_retornaBoleano_quandoSucesso() {

		Optional<String> valor = Optional.ofNullable(null);

		boolean valorPresente = valor.isPresent();

		assertEquals(Boolean.FALSE, valorPresente);

	}

	@Test
	void get_retornaOValorDentroDoOptional_quandoSucesso() {

		Boleto boleto = new Boleto.BoletoBuilder()
									.comValor(new BigDecimal(CINQUENTA))
									.construir();

		Optional<String> valorDoBoleto = Optional.of(boleto.getValor().toString());

		assertEquals(CINQUENTA, valorDoBoleto.get());


	}
 
	@Test
	void ifPresent_fazUmaOperacaoSeExistirUmValor_retornaOValorDoBoletoComDescontoDe10PorCento_quandoSucesso() {

		Boleto boleto = new Boleto.BoletoBuilder()
									.comValor(new BigDecimal(CINQUENTA))
									.construir();

		Optional<String> valorDoBoleto = Optional.of(boleto.getValor().toString());

		valorDoBoleto.ifPresent(n -> boleto.setValor(boleto.getValor().multiply(new BigDecimal("0.90"))));

		assertEquals(new BigDecimal("45.00"), boleto.getValor());


	}

	@Test
	void orElse_retornaUmValorDefaultCasoOcorraExececao_retornaOValor10_quandoSucesso() {

		Boleto boleto = new Boleto.BoletoBuilder()
									.construir();

		Integer valor = converteEmInteiro(boleto.getValor()).orElse(10);

		assertEquals(DEZ, valor);

	}
	
	@Test
	void orElseGet_retornaUmValorDefaultAtravesDeUmaExpressaoLambdaCasoOcorraExececao_retornaOValor10_quandoSucesso() {

		Boleto boleto = new Boleto.BoletoBuilder()
									.construir();

		Integer valor = converteEmInteiro(boleto.getValor()).orElseGet(() -> 5 * 2);

		assertEquals(DEZ, valor);

	}

	public Optional<Integer> converteEmInteiro(BigDecimal valor) {
		try {
			Integer retorno = Integer.valueOf(valor.toString());
			return Optional.of(retorno);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	@Test
	void orElseThrow_retornaUmaExcecaoCasoOOptionalEstejaVazio_quandoErro() {

		Boleto boleto = new Boleto.BoletoBuilder()
									.construir();

		assertThrows(NullPointerException.class, () -> converteEmInteiro(boleto.getValor()).orElseThrow(() -> new NullPointerException()));

	}

}
