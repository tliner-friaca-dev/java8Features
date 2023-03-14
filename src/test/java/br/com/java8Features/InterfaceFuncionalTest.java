package br.com.java8Features;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.java8Features.Boleto.BoletoImprimir;
import br.com.java8Features.model.Boleto;

@SpringBootTest
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

}
