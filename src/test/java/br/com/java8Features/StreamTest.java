package br.com.java8Features;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class StreamTest {

	// private static final int QUANTIDADE_DE_NUMEROS_PARES = 5;

	@Test
	void imprimirNumerosParesDe1a10__quandoSucesso() {

		List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		assertEquals(10, numeros.size());

	}

}
