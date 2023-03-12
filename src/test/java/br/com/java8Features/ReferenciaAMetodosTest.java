package br.com.java8Features;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.java8Features.model.Cliente;

@SpringBootTest
class ReferenciaAMetodosTest {

	private static final String NUMEROS_CONVERTIDOS_E_MULTIPLICADOS = "[2, 4, 6]";
	private static final String TIPO_DA_VARIAVEL_BIG_DECIMAL = "class java.math.BigDecimal";
	private static final int QUANTIDADE_DE_CLIENTES_COM_DATA_DE_NASCIMENTO_NULO = 2;

	@Test
	void exibirQuantidadeDeClientesComDataDeNascimentoNulo_referenciaAMetodoEstatico_retornar2Objetos_quandoSucesso() {

		// O primeiro registro contém data de nascimento
		// Já os registro 2 e 3 não contém data de nascimento

		Cliente cliente1 = new Cliente.ClienteBuilder()
							.comNome("Renato")
							.comEmail("renato@email.com")
							.comDataAniversario(LocalDate.now().minusYears(30))
							.comEmailAniversarioEnviado(Boolean.TRUE)
							.construir();

		Cliente cliente2 = new Cliente.ClienteBuilder()
							.comNome("Carla")
							.comEmail("carla@email.com")
							.comEmailAniversarioEnviado(Boolean.TRUE)
							.construir();

		Cliente cliente3 = new Cliente.ClienteBuilder()
							.comNome("Romário")
							.comEmail("romario@email.com")
							.comEmailAniversarioEnviado(Boolean.TRUE)
							.construir();

		List <Cliente> clientes = new ArrayList<>();

		clientes = Arrays.asList(cliente1, cliente2, cliente3);
		
		clientes.forEach(c -> 
			System.out.println("Campo emailAniversarioEnviado, antes da manipulação : " + c.isEmailAniversarioEnviado()));

		clientes = clientes.stream()
					.map(Cliente::atualizarEmailAniversarioEnviado)
					.filter(c -> c.getEmailAniversarioEnviado() == Boolean.FALSE)
					.collect(Collectors.toList());
		
		clientes.forEach(c -> 
			System.out.println("Campo emailAniversarioEnviado, após da manipulação : " + c.isEmailAniversarioEnviado()));

		assertEquals(QUANTIDADE_DE_CLIENTES_COM_DATA_DE_NASCIMENTO_NULO, clientes.size());

	}
			
	@Test
	void transformarNumerosInteirosEmBigDecimal_referenciaAMetodoConstrutor_quandoSucesso() {

		List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		List<BigDecimal> numerosConvertidos = numeros.stream()
												.map(BigDecimal::new)
												.collect(Collectors.toList());

		numerosConvertidos.forEach(n -> assertEquals(TIPO_DA_VARIAVEL_BIG_DECIMAL, n.getClass().toString()));

	}

			
	@Test
	void transformarNumerosInteirosEmBigDecimalEMultiplicarPor2_referenciaAMetodoDeUmaInstancia_quandoSucesso() {

		// 1 * 2 = 2
		// 2 * 2 = 4
		// 3 * 2 = 6

		List<Integer> numeros = Arrays.asList(1, 2, 3);

		BigDecimal dois = new BigDecimal(2) ;

		List<BigDecimal> numerosConvertidos = numeros.stream()
												.map(BigDecimal::new)
												.map(dois::multiply)
												.collect(Collectors.toList());

		
		assertEquals(NUMEROS_CONVERTIDOS_E_MULTIPLICADOS, numerosConvertidos.toString());

	}

}
