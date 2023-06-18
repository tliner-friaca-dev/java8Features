package br.com.java8Features;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import org.junit.jupiter.api.Test;

import br.com.java8Features.Boleto.BoletoImprimir;
import br.com.java8Features.Repository.ClienteRepository;
import br.com.java8Features.model.Boleto;
import br.com.java8Features.model.Cliente;
import br.com.java8Features.util.EnviadorEmail;

class InterfaceFuncionalTest {

	// Supplier = Representa um fornecedor de resultados.

	// Consumer = Representa uma operação que aceita um único argumento de entrada e não retorna nenhum resultado.

	// BiConsumer = Representa uma operação que aceita dois argumentos de entrada e não retorna nenhum resultado.

	// Predicate = Representa um predicado (função de valor booleano) de um argumento.

	// BiPredicate = Representa um predicado (função de valor booleano) de dois argumentos. Isso é a especialização de duas aridades de {@link Predicate}.

	// Function = Representa uma função que aceita um argumento e produz um resultado.

	// BiFunction = Representa uma função que aceita dois argumentos e produz um resultado. Esta é a especialização de duas aridades de {@link Function}.

	// UnaryOperator = Representa uma operação em um único operando que produz um resultado do mesmo tipo do seu operando. Esta é uma especialização para o caso em que o operando e o resultado são do mesmo tipo.

	// BinaryOperator = Representa uma operação sobre dois operandos do mesmo tipo, produzindo um resultado do mesmo tipo dos operandos. Esta é uma especialização para o caso em que os operandos e o resultado são todos iguais do mesmo tipo.

	private static final String VALOR_BOLETO = "10";
	private static final long CODIGO_BOLETO = 123l;

	@Test
	void supplier_gerarBoletoPelaInterfaceFuncional_quandoSucesso() {
		Boleto boleto = new Boleto();
		BoletoImprimir b = () -> {
			boleto.setCodigo(CODIGO_BOLETO);
			boleto.setValor(new BigDecimal(VALOR_BOLETO));
		};
		b.gerar();
		assertAll("boleto", () -> assertEquals(CODIGO_BOLETO, boleto.getCodigo()),
									() -> assertEquals(VALOR_BOLETO, boleto.getValor().toString()));
	}

	@Test
	void consumer_enviaEmailEAtualizaFlagEmailAniversarioEnviadoParaTruePelaInterfaceFuncional_quandoSucesso() {

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
	void predicate_filtrarClientesQueJaReceberamEmailDeAniversarioPelaInterfaceFuncional_quandoSucesso() {

		Cliente cliente1 = new Cliente.ClienteBuilder()
							.comNome("Renato")
							.comEmailAniversarioEnviado(Boolean.FALSE)
							.construir();

		Cliente cliente2 = new Cliente.ClienteBuilder()
							.comNome("Carla")
							.comEmailAniversarioEnviado(Boolean.TRUE)
							.construir();

		Cliente cliente3 = new Cliente.ClienteBuilder()
							.comNome("Romário")
							.comEmailAniversarioEnviado(Boolean.TRUE)
							.construir();

		List <Cliente> clientes = new ArrayList<>();

		clientes = Arrays.asList(cliente1, cliente2, cliente3);

		clientes = ClienteRepository.filterCliente(clientes, c -> c.getEmailAniversarioEnviado() == Boolean.TRUE);

		clientes.forEach(c -> assertTrue(c.getEmailAniversarioEnviado()));

	}
	
	@Test
	void function_RetornarOsNomesDosClientesDosBoletosPelaInterfaceFuncional_quandoSucesso() {

		Cliente cliente1 = new Cliente.ClienteBuilder()
							.comNome("Renato")
							.comEmailAniversarioEnviado(Boolean.FALSE)
							.construir();

		Cliente cliente2 = new Cliente.ClienteBuilder()
							.comNome("Carla")
							.comEmailAniversarioEnviado(Boolean.TRUE)
							.construir();

		Cliente cliente3 = new Cliente.ClienteBuilder()
							.comNome("Romario")
							.comEmailAniversarioEnviado(Boolean.TRUE)
							.construir();
		
		Boleto boleto1 = new Boleto.BoletoBuilder()
									.comValor(new BigDecimal("10"))
									.comCliente(cliente1)
									.construir();
		
		Boleto boleto2 = new Boleto.BoletoBuilder()
									.comValor(new BigDecimal("20"))
									.comCliente(cliente2)
									.construir();
		
		Boleto boleto3 = new Boleto.BoletoBuilder()
									.comValor(new BigDecimal("30"))
									.comCliente(cliente3)
									.construir();

		List <Boleto> boletos = new ArrayList<>();
		boletos = Arrays.asList(boleto1, boleto2, boleto3);

		List<String> nomes = new ArrayList<>();

		Function<Boleto, String> functionNomeCliente = b -> b.getCliente().getNome();
		nomes = mapString(functionNomeCliente, boletos);
		
		assertEquals(Arrays.asList("Renato, Carla, Romario").toString(), nomes.toString());

	}

	public static List<String> mapString(Function<Boleto, String> function, List<Boleto> boletos) {
		List<String> retorno = new ArrayList<>();
		for (Boleto b : boletos) {
			String s = function.apply(b);
			retorno.add(s);
		}
		return retorno;
	}
	
	@Test
	void unaryOperator_RetornarOsNomesDosClientesDosBoletosPelaInterfaceFuncionalxxxxxxxxxxx_quandoSucesso() {

		Cliente cliente1 = new Cliente.ClienteBuilder()
							.comNome("Renato")
							.comEmailAniversarioEnviado(Boolean.FALSE)
							.construir();

		Boleto boleto1 = new Boleto.BoletoBuilder()
									.comValor(new BigDecimal("10"))
									.comCliente(cliente1)
									.construir();
		
		UnaryOperator<Boleto> functionBoletoValorDesconto0_90 = b -> b;
		boleto1 = mapBoletoValorDesconto0_90(functionBoletoValorDesconto0_90, boleto1);
		assertEquals(new BigDecimal("9.0"), boleto1.getValor());

	}

	public static Boleto mapBoletoValorDesconto0_90(Function<Boleto, Boleto> function, Boleto boleto) {
		boleto.setValor(boleto.getValor().multiply(new BigDecimal("0.9")));
		return boleto;
	}
}
