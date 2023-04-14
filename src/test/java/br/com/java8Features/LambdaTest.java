package br.com.java8Features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import br.com.java8Features.model.Cliente;
import br.com.java8Features.util.EnviadorEmail;

class LambdaTest {

	private static final int QUANTIDADE_DE_NUMEROS_PARES = 5;

	@Test
	void enviadorEmail_enviaEmailEAtualizaFlagEmailAniversarioEnviadoParaTrue_quandoSucesso() {

		EnviadorEmail enviadorEmail = new EnviadorEmail();

		Cliente cliente1 = new Cliente.ClienteBuilder()
							.comNome("Renato")
							.comEmail("renato@email.com")
							.comDataAniversario(LocalDate.now().minusYears(30))
							.comEmailAniversarioEnviado(Boolean.FALSE)
							.construir();

		Cliente cliente2 = new Cliente.ClienteBuilder()
							.comNome("Carla")
							.comEmail("carla@email.com")
							.comDataAniversario(LocalDate.now().minusYears(40))
							.comEmailAniversarioEnviado(Boolean.FALSE)
							.construir();

		Cliente cliente3 = new Cliente.ClienteBuilder()
							.comNome("Romário")
							.comEmail("romario@email.com")
							.comDataAniversario(LocalDate.now().minusYears(50))
							.comEmailAniversarioEnviado(Boolean.FALSE)
							.construir();

		List <Cliente> clientes = new ArrayList<>();

		clientes = Arrays.asList(cliente1, cliente2, cliente3);
		
		clientes.forEach(c -> {
			enviadorEmail.enviar(c);
			c.setEmailAniversarioEnviado(Boolean.TRUE);
		});

		clientes.forEach(c -> assertTrue(c.getemailAniversarioEnviado()));

	}

	@Test
	void imprimirNumerosParesDe1a10__quandoSucesso() {

		List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		numeros = numeros.stream()
					.filter(n -> n % 2 == 0)
					.collect(Collectors.toList());

		assertEquals(QUANTIDADE_DE_NUMEROS_PARES, numeros.size());

	}

}
