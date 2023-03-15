package br.com.java8Features;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class StreamTest {

/* 
	*Stream - loops implícitos*
 */
/* 
	*Operações Intermediárias*
	- skip(n) - ignora os dois primeiros elementos
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
	*Stream - Collect*
	- toList - armazena o resultado em um list.
	- toSet - armazena o resultado em um set.
	- toCollection - passa a implementação da collection.
 */
/* 
	*Reduce*

	- reduce - pega os elementos do stream e transforma em um elemento através de um critério
	usar apenas em operações associativa, como por exemplo soma, multiplicação, concatenação. Subtração não é associativa, por isso não deve ser utilizada no reduce.

	- reduce(n) com valor de identidade
	- reduce com função de combinação - só funciona no stream usando o parallel
 */
/* 
	Stream
	- referência método criado no próprio modelo
 */

	// private static final int QUANTIDADE_DE_NUMEROS_PARES = 5;

	@Test
	void imprimirNumerosParesDe1a10__quandoSucesso() {

		List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		assertEquals(10, numeros.size());

	}

}
