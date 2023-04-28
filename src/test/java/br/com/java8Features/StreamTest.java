package br.com.java8Features;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.java8Features.model.Cliente;
import br.com.java8Features.util.EnviadorEmail;

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
	- joining - agrupa os elementos de uma lista em uma string
	- counting - contar o número de elementos
	- maxBy - maior elemento obedecendo um critério
	- minBy - menor elemento obedecendo um critério
 */
/* 
	- groupingBy - agrupa valores em um map por um determinado critério
	- partitioningBy - agrupa valores em um map por um determinado critério retornando sempre um bolean
	- toMap - forma mais personalizada de se criar um map
 */
/* 
	- averaging - retorna a média de todos os valores
	- summing - retorna a soma de todos os valores
	- summarizing - retorna uma sumarização de todos os valores
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

    @Test
	void forEach_percorreOStream_flagEmailAniversarioEnviadoAlteradoParaTrue_quandoSucesso() {

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

		clientes.forEach(c -> assertTrue(c.getEmailAniversarioEnviado()));

	}
 
	@Test
	void reduce_soma_transformaEmUmElementoAtravesDeUmCriterio_retornaASomaDosValoresIgualA60_quandoSucesso() {

		Optional<Integer> retorno = numeros.stream()
										.reduce((n1, n2) -> n1 + n2);

		assertEquals(60, retorno.get());
	}
 
	@Test
	void reduce_multiplicacao_transformaEmUmElementoAtravesDeUmCriterio_retornaAMultiplicacaoDosValoresIgualA21772800_quandoSucesso() {

		Optional<Integer> retorno = numeros.stream()
										.reduce((n1, n2) -> n1 * n2);

		assertEquals(21772800, retorno.get());
	}

	@Test
	void reduce_concatenacao_transformaEmUmElementoAtravesDeUmCriterio_retornaAStringConcatenadaSemEspacos_quandoSucesso() {

		String frase = "retorna a string concatenada sem espaços";
		String[] fraseSplit = frase.split(" ");
		List<String> listaString = Arrays.asList(fraseSplit);

		System.out.println(frase);
		System.out.println(fraseSplit);

		Optional<String> retorno = listaString.stream()
												.reduce((s1, s2) -> s1.concat(s2));

		assertEquals("retornaastringconcatenadasemespaços", retorno.get());
	}

	@Test
	void reduce_identidade_soma_transformaEmUmElementoAtravesDeUmCriterio_retorna0SomandoUmaListaVazia_quandoSucesso() {

		numeros = new ArrayList<>();

		Integer retorno = numeros.stream()
									.reduce(0, (n1, n2) -> n1 + n2);

		assertEquals(0, retorno);
	}
 
	@Test
	void reduce_identidade_multiplicacao_transformaEmUmElementoAtravesDeUmCriterio_retornaZeroMultiplicandoUmaListaVazia_quandoSucesso() {

		numeros = new ArrayList<>();

		Integer retorno = numeros.stream()
									.reduce(1, (n1, n2) -> n1 * n2);

		assertEquals(1, retorno);
	}

	@Test
	void reduce_identidade_concatenacao_transformaEmUmElementoAtravesDeUmCriterio_retornaVazioConcatenandoUmaStringVazia_quandoSucesso() {

		String frase = "";
		String[] fraseSplit = frase.split(" ");
		List<String> listaString = Arrays.asList(fraseSplit);

		System.out.println(frase);
		System.out.println(fraseSplit);

		String retorno = listaString.stream()
										.reduce("", (s1, s2) -> s1.concat(s2));

		assertEquals("", retorno);
	}

	@Test
	void reduce_concatenacao_combinacao_transformaEmUmElementoAtravesDeUmCriterio_retornaAStringConcatenadaSemEspacos_quandoSucesso() {

		String frase = "retorna a string concatenada sem espaços";
		String[] fraseSplit = frase.split(" ");
		List<String> listaString = Arrays.asList(fraseSplit);

		System.out.println(frase);
		System.out.println(fraseSplit);

		String retorno = listaString.stream()
										.reduce(
											"", 
											(s1, s2) -> s1.toString().concat(s2.toString()),
											(s1, s2) -> s1.concat(s2)
										);

		assertEquals("retornaastringconcatenadasemespaços", retorno);
	}

 	@Test
	void collect_armazenaOResultadoEmUmaLista_quandoSucesso() {

		Set<Integer> retorno = numeros.stream()
										.collect(
											() -> new HashSet<>(),
											(lista, elemento) -> lista.add(elemento),
											(lista1, lista2) -> lista1.addAll(lista2)
										);
										
		assertEquals(("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]"), retorno.toString());

	}

	@Test
	void collect_toList_retornaOResultadoEmUmaLista_quandoSucesso() {

		List<Integer> retorno = numeros.stream()
										.collect(Collectors.toList());
										
		assertEquals(Arrays.asList(1, 2, 2, 3, 3, 4, 5, 6, 7, 8, 9, 10), retorno);

	}

	@Test
	void collect_toSet_retornaOResultadoEmUmSet_quandoSucesso() {

		Set<Integer> retorno = numeros.stream()
										.collect(Collectors.toSet());
										
		assertEquals(("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]"), retorno.toString());

	}

	@Test
	void collect_toCollection_retornaUmaImplementacaoDaCollection_quandoSucesso() {

		Deque<Integer> retorno = numeros.stream()
										.collect(Collectors.toCollection(() -> new ArrayDeque<Integer>()));
		
		assertEquals("[1, 2, 2, 3, 3, 4, 5, 6, 7, 8, 9, 10]", retorno.toString());

	}

	@Test
	void collect_joining_retornaOsElementosDeUmaListaEmUmaString_quandoSucesso() {

		String retorno = numeros.stream()
									.map( n -> n.toString())
									.collect(Collectors.joining("-"));
		
		assertEquals("1-2-2-3-3-4-5-6-7-8-9-10", retorno);

	}

	@Test
	void collect_counting_retornaAQuantidadeDeElementosDeUmaLista_quandoSucesso() {

		Long retorno = numeros.stream()
								.collect(Collectors.counting());
		
		assertEquals(12, retorno);

	}

	@Test
	void collect_maxBy_retornaOMariorDeElementosDeUmaListaObedecendoUmCriterio_quandoSucesso() {

		Optional<Integer> retorno = numeros.stream()
								.collect(Collectors.maxBy(Comparator.naturalOrder()));
		
		assertEquals(10, retorno.get());

	}

	@Test
	void collect_minBy_retornaOMenorDeElementosDeUmaListaObedecendoUmCriterio_quandoSucesso() {

		Optional<Integer> retorno = numeros.stream()
								.collect(Collectors.minBy(Comparator.naturalOrder()));
		
		assertEquals(1, retorno.get());

	}
 
	@Test
	void averaging_retornaAMediaDeTodosOsValores_quandoSucesso() {

		Double retorno = numeros.stream()
									.collect(Collectors.averagingInt(n -> n.intValue()));

		assertEquals(5.0, retorno);
	}

	@Test
	void summing_retornaASomaDeTodosOsValores_quandoSucesso() {

		Integer retorno = numeros.stream()
									.collect(Collectors.summingInt(n -> n.intValue()));

		assertEquals(60, retorno);
	}

	@Test
	void summarizing_retornaUmaSumarizacaoDeTodosOsValores_quandoSucesso() {

		IntSummaryStatistics retorno = numeros.stream()
									.collect(Collectors.summarizingInt(n -> n.intValue()));
		
		assertAll("retorno", () -> assertEquals(5.0, retorno.getAverage()),
									() -> assertEquals(12, retorno.getCount()),
									() -> assertEquals(10, retorno.getMax()),
									() -> assertEquals(1, retorno.getMin()),
									() -> assertEquals(60, retorno.getSum())
					);
	}
/* 
	- groupingBy - agrupa valores em um map por um determinado critério
	- partitioningBy - agrupa valores em um map por um determinado critério retornando sempre um bolean
	- toMap - forma mais personalizada de se criar um map
 */

	@Test
	void groupingBy_agrupaValoresPorUmDeterminadoCriterio_retornaTresListasDoRestoDaDivisaoPorTres_quandoSucesso() {

		Map<Integer, List<Integer>> retorno = numeros.stream()
														.collect(Collectors.groupingBy((n) -> n % 3 ));

		assertEquals("[0=[3, 3, 6, 9], 1=[1, 4, 7, 10], 2=[2, 2, 5, 8]]", retorno.entrySet().toString());
		
	}

	@Test
	void partitioningBy_agrupaValoresPorUmDeterminadoCriterioRetornandoSempreBolean_retornaDuasListasDoRestoDaDivisaoPorTres_quandoSucesso() {

		Map<Boolean, List<Integer>> retorno = numeros.stream()
									.collect(Collectors.partitioningBy((n) -> n % 3 == 0 ));

		assertEquals("[false=[1, 2, 2, 4, 5, 7, 8, 10], true=[3, 3, 6, 9]]", retorno.entrySet().toString());


	}

	@Test
	void toMap_formaMaisPersonalizadaDeSeCriarUmMap_retornaUmMapCujoOValorEhODobroChave_quandoSucesso() {

		Map<Integer, Integer> retorno = numeros.stream()
									.distinct()
									.collect(Collectors.toMap(n -> n, n -> n * 2));

		assertEquals("[1=2, 2=4, 3=6, 4=8, 5=10, 6=12, 7=14, 8=16, 9=18, 10=20]", retorno.entrySet().toString());


	}











}
