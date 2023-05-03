package br.com.java8Features;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StreamParalelloTest {
/* 
	- forEachOrdered - executa de forma ordenada
	- findAny - encontra algum elemento

	*Unordered* - executa outros comandos sem priorizar a ordenação do stream
	- skip - pula o elemento do stream
	- limit - limita quantos elementos processados
	- distinct - não processa elementos repetidos

	*Reduce*
	- reduce - pega os elementos do stream e transforma em um elemento através de um critério
	usar apenas em operações associativa, como por exemplo soma, multiplicação, concatenação. Subtração não é associativa, por isso não deve ser utilizada no reduce.

	*Collect*
	- toMap / toConcurrentMap - forma mais personalizada de se criar um map / um único mapa acessado por todas as treds
	- groupingBy / groupingByConcurrent - agrupa valores em um map por um determinado critério / um único mapa acessado por todas as treds
 */	

	
	List<Integer> numeros;
	Integer retornoFindAny;
	HashMap<Integer, Boolean> map;

	@BeforeEach
	public void setUp() {
		numeros = Arrays.asList(1, 2, 2, 3, 3, 4, 5, 6, 7, 8, 9, 10);
		
		map = new HashMap<>();
		map.put(1, false);
		map.put(2, true);
		map.put(3, false);
		map.put(4, true);
		map.put(5, false);
		map.put(6, true);
		map.put(7, false);
		map.put(8, true);
		map.put(9, false);
		map.put(10, true);
	}

	@Test
	void findAny_encontraAlgumElemento_quandoSucesso() {

		numeros.parallelStream()
				.forEachOrdered(System.out::println); // imprime de forma ordenada
		
		Optional<Integer> retorno = numeros.parallelStream()
									.findAny();

		assertEquals(retorno, retorno);

	}
	
	@Test
	void skip_pulaOElementoDoStream_retornaAListaSemOsDoisPrimeirosElementos_quandoSucesso() {

		numeros = numeros.parallelStream()
							.skip(2).collect(Collectors.toList());

		assertEquals(Arrays.asList(2, 3, 3, 4, 5, 6, 7, 8, 9, 10), numeros);

	}
	
	@Test
	void limit_limitaQuantosElementosProcessados_retornaOsTresPrimeirosElementosDaLista_quandoSucesso() {

		numeros = numeros.parallelStream()
							.limit(3).collect(Collectors.toList());

		assertEquals(Arrays.asList(1, 2, 2), numeros);

	}
	
	@Test
	void distinct_naoProcessaElementosRepetidos_quandoSucesso() {

		numeros = numeros.parallelStream()
							.distinct().collect(Collectors.toList());

		assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), numeros);

	}

	@Test
	void reduce_transformaEmUmElementoAtravesDeUmCriterio_retornaASomaDosValoresIgualA60_quandoSucesso() {

		Optional<Integer> resultado = numeros.parallelStream()
												.reduce((n1, n2) -> n1 + n2);

		assertEquals(60, resultado.get());

	}

	@Test
	void toConcurrentMap_umUnicoMapaAcessadoPorTodasAsTreds_quandoSucesso() {

		Map<Integer, Boolean > resultado = numeros.parallelStream()
													.distinct()
		 											.collect(Collectors.toConcurrentMap(n -> n, n -> n % 2 == 0));

		assertEquals(map, resultado);

	}

	@Test
	void groupingByConcurrent_umUnicoMapaAcessadoPorTodasAsTreds_quandoSucesso() {

		ConcurrentMap<Object, List<Integer>> resultado = numeros.parallelStream()
		 														 .collect(Collectors.groupingByConcurrent(n -> n % 2 == 0));

		List<Integer> numerosFalse = resultado.get(Boolean.FALSE);
		List<Integer> numerosTrue = resultado.get(Boolean.TRUE);

		Collections.sort(numerosFalse);
		Collections.sort(numerosTrue);

		assertAll("resultado", () -> assertEquals(Arrays.asList(1, 3, 3, 5, 7, 9), numerosFalse),
										() -> assertEquals(Arrays.asList(2, 2, 4, 6, 8, 10), numerosTrue));
	}

}
