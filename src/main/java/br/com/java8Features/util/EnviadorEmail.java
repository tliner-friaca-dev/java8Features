package br.com.java8Features.util;

import br.com.java8Features.model.Cliente;

public class EnviadorEmail {

    public void enviar(Cliente cliente) {

        System.out.println("\n*Enviador de E-mail*");
        System.out.printf("E-mail enviado para o(a) cliente %s com o endereço %s.\n", cliente.getNome(), cliente.getEmail());

    }
    
}
