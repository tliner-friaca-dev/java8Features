package br.com.java8Features.Boleto;

import br.com.java8Features.model.Boleto;
import br.com.java8Features.model.Cliente;

@FunctionalInterface
public interface BoletoImprimir {

    public void gerar();
    
    default Boleto gerar(Boleto boleto, Cliente cliente) {
        
        Boleto boletoReturn = new Boleto.BoletoBuilder()
                                .comCodigo(boleto.getCodigo())
                                .comValor(boleto.getValor())
                                .comCliente(cliente)
                                .construir();

        return boletoReturn;
    };

}
