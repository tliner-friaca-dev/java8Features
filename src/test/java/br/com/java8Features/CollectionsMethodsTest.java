package br.com.java8Features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CollectionsMethodsTest {
/* 
	*Collecitons Methods
	*List*
	- forEach - percorre todos os elementos do stream
	- removeIf - remove da lista mediante um critério
	- replaceAll - substitui cada elemento da lista mediante um critério

	*Map*
	- forEach - percorre todos os elementos do stream
	- compute - operação que será realizada em algum elemento do map
	- merge - substitui o valor associado pelos resultados da função
	- replaceAll - retorna um novo valor para a chave
 */	

	List<Integer> numeros;
	HashMap<Integer, String> map;
	HashMap<Integer, String> mapCompute;
	HashMap<Integer, String> mapMerge;
	HashMap<Integer, String> mapReplaceAll;

	@BeforeEach
	public void setUp() {
		numeros = new ArrayList<>();
		numeros.add(1);
		numeros.add(2);
		numeros.add(2);
		numeros.add(3);
		numeros.add(3);
		numeros.add(4);
		numeros.add(5);
		numeros.add(6);
		numeros.add(7);
		numeros.add(8);
		numeros.add(9);
		numeros.add(10);

		map = new HashMap<>();
		map.put(0, "zero");
		map.put(1, "um");
		map.put(2, "dois");

		mapCompute = new HashMap<>();
		mapCompute.put(0, "zero");
		mapCompute.put(1, "um Compute");
		mapCompute.put(2, "dois");

		mapMerge = new HashMap<>();
		mapMerge.put(0, "zero");
		mapMerge.put(1, "um");
		mapMerge.put(2, "dois Merge");

		mapReplaceAll = new HashMap<>();
		mapReplaceAll.put(0, "zero ReplaceAll");
		mapReplaceAll.put(1, "um ReplaceAll");
		mapReplaceAll.put(2, "dois ReplaceAll");
	}

	@Test
	void removeIf_list_removeDaListaMedianteUmCriterio_retornaOsNumerosImpares_quandoSucesso() {
		
		numeros.forEach(n -> System.out.println(n));
		numeros.removeIf(n -> n % 2 == 0);

		assertEquals(Arrays.asList(1, 3, 3, 5, 7, 9), numeros);

	}

	@Test
	void replaceAll_list_substituiCadaElementoDaListaMedianteUmCriterio_retornaOsNumerosMultiplicadoPorDois_quandoSucesso() {
		
		numeros.replaceAll(n -> n * 2);

		assertEquals(Arrays.asList(2, 4, 4, 6, 6, 8, 10, 12, 14, 16, 18, 20), numeros);

	}

	@Test
	void compute_map_operacaoQueSeraRealizadaEmAlgumElementoDoMap_retornaOsNumerosImpares_quandoSucesso() {
		
		map.forEach((k, v) -> System.out.println(String.valueOf(k) + " " + v));
		map.compute(1, (k, v) -> v + " Compute");

		map.forEach((k, v) -> System.out.println(String.valueOf(k) + " " + v));

		assertEquals(map, mapCompute);

	}

	@Test
	void merge_map_substituiOValorAssociadoPelosResultadosDaFuncao_quandoSucesso() {
		
		map.merge(2, " Merge", (v1, v2) -> v1 + v2);

		assertEquals(map, mapMerge);

	}
	
	@Test
	void replaceAll_map_retornaUmNovoValorParaAChave_quandoSucesso() {
		
		map.replaceAll((k, v) -> v + " ReplaceAll");

		assertEquals(map, mapReplaceAll);

	}

}
