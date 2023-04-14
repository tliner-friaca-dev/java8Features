package br.com.java8Features;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StreamTest {

/* 
	*Stream - loops implícitos*
 */
/* 
	*Operações Intermediárias*
	- skip(n) - ignora os n primeiros elementos
	- limit(n) - limita o número de elementos a serem processados
	- distinct - não processa elementos repetidos (equals e hashcode)
	- filter - implementa filtro
	- map - promove uma transformação nos dados
*/
/* 
	*Operações Finais*
	- count - quantidade de elementos 
	- min - seleciona o menor valor
	- max - seleciona o maior valor
	- collect toList - armazena o resultado do stream em uma lista 
	- collect groupingBy - agrupa valores por um determinado critério
	- collect joining - junção de string em uma única string
	- forEach - percorre o stream
	- forEach - tentar executar em um stream já fechado - IllegalStateException - ERRO
 */
/* 
	*Reduce*

	- reduce - pega os elementos do stream e transforma em um elemento através de um critério
	usar apenas em operações associativa, como por exemplo soma, multiplicação, concatenação. Subtração não é associativa, por isso não deve ser utilizada no reduce.

	- reduce(n) com valor de identidade
	- reduce com função de combinação - só funciona no stream usando o parallel
 */
/* 
	*Stream - Collect*
	- toList - armazena o resultado em um list.
	- toSet - armazena o resultado em um set.
	- toCollection - passa a implementação da collection.
 */
/* 
	Stream
	- referência método criado no próprio modelo
 */

 	List<Integer> numeros;

	@BeforeEach
	public void setUp() {
		numeros = Arrays.asList(1, 2, 2, 3, 3, 4, 5, 6, 7, 8, 9, 10);
	}

    @Test
	void skip_ignoraOs2PrimeirosElementos_retorna8Elementos_quandoSucesso() {

		numeros = numeros.stream()
				.skip(3)
				.collect(Collectors.toList());

		assertEquals(9, numeros.size());
	}

    @Test
	void limit_limitaONumeroDeElementosASeremProcessados_retorna4Elementos_quandoSucesso() {

		numeros = numeros.stream()
				.limit(4)
				.collect(Collectors.toList());

		assertEquals(4, numeros.size());
	}
	
    @Test
	void distinct_NaoProcessaElementosRepetidos_retorna10Elementos_quandoSucesso() {

		numeros = numeros.stream()
				.distinct()
				.collect(Collectors.toList());

		assertEquals(10, numeros.size());
	}
	
    @Test
	void filter_implementaUmFiltro_retorna6NumerosPares_quandoSucesso() {

		numeros = numeros.stream()
				.filter(e -> e % 2 == 0)
				.collect(Collectors.toList());

		assertEquals(6, numeros.size());
	}

    @Test
	void map_promoveUmaTransformacaoNoDados_retorna12NumerosPares_quandoSucesso() {

		numeros = numeros.stream()
				.map(e -> e * 2)
				.filter(e -> e % 2 == 0)
				.collect(Collectors.toList());

		assertEquals(12, numeros.size());
	}

    @Test
	void count_contaAQuantidadeDeElementos_retorna6NumerosPares_quandoSucesso() {

		long resultado = numeros.stream()
								.filter(e -> e % 2 == 0)
								.count();

		assertEquals(6, resultado);
	}

    @Test
	void min_selecionaOMenorValor_retornaONumero1_quandoSucesso() {

		Optional<Integer> resultado = numeros.stream()
								.min(Comparator.naturalOrder());

		assertEquals(1, resultado.get());
	}

    @Test
	void max_selecionaOMaiorValor_retornaONumero10_quandoSucesso() {

		Optional<Integer> resultado = numeros.stream()
								.max(Comparator.naturalOrder());

		assertEquals(10, resultado.get());
	}

    @Test
	void toList_armazenaOResultadoDoStreamEmUmaNovaLista_retornaNumerosPares_quandoSucesso() {

		numeros = numeros.stream()
				.filter(e -> e % 2 == 0)
				.collect(Collectors.toList());
		assertEquals(Arrays.asList(2, 2, 4, 6, 8, 10), numeros);
	}

    @Test
	void groupingBy_agrupaValoresPorUmDeterminadoCriterio_retornaUmaListaDeImparesEOutraListaDePares_quandoSucesso() {

		Map<Boolean, List<Integer>> resultado = numeros.stream()
				.collect(Collectors.groupingBy(e -> e % 2 == 0));

		System.out.println(resultado);

		assertAll("resultado", () -> assertEquals(Arrays.asList(1, 3, 3, 5, 7, 9), resultado.get(Boolean.FALSE)),
										() -> assertEquals(Arrays.asList(2, 2, 4, 6, 8, 10), resultado.get(Boolean.TRUE)));
	}

    @Test
	void joining_juncaoDeStringEmUmaUnicaString_retornaOsValoresConcatenadosSeparadosPorPontoEVirgula_quandoSucesso() {

		String retorno = numeros.stream()
				.map(e -> String.valueOf(e))
				.collect(Collectors.joining(";"));

		assertEquals("1;2;2;3;3;4;5;6;7;8;9;10", retorno);
	}







}
